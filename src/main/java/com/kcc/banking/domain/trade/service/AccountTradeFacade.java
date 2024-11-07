package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.common.util.TransactionService;
import com.kcc.banking.domain.account.dto.request.*;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountCloseResult;
import com.kcc.banking.domain.account.dto.response.CloseSavingsFlexibleAccountTotal;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferList;
import com.kcc.banking.domain.auto_transfer.service.AutoTransferService;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferValidation;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferValidationResult;
import com.kcc.banking.domain.bulk_transfer.service.BulkTransferService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.dto.request.SearchReserve;
import com.kcc.banking.domain.reserve_transfer.mapper.ReserveTransferMapper;
import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.trade.dto.response.CloseCancelDetail;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TransferDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
@EnableScheduling
public class AccountTradeFacade {


    private final InterestService interestService;
    private final AccountService accountService;
    private final TradeService tradeService;
    private final BusinessDayCloseService businessDayCloseService;
    private final BulkTransferService bulkTransferService;
    private final CommonService commonService;
    private final AutoTransferService autoTransferService;

    private final ReserveTransferService reserveTransferService;
    private final ReserveTransferMapper reserveTransferMapper;
    private final TransactionService transactionService;

    /**
     *   @Description - 보통 예금계좌 개설
     *
     *   1. 계좌 개설 거래 내역 생성
     *   2. 마감 입금액 상태 변경
     *   3. 계좌 생성
     *   
     */
    public String openAccount(AccountOpen accountOpen) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 계좌 개설
        accountService.createAccount(accountOpen, tradeNumber, currentData);

        // 계좌 개설 거래내역 생성
        tradeService.createOpenTrade(accountOpen,currentData, tradeNumber);

        // 행원 마감 입금액 변경
        businessDayCloseService.updateTradeAmount(accountOpen.getBalance(), currentData, accountOpen.getTradeType());

        return accountOpen.getId();
    }


    //---------------------------------------------------------
    /**
     *   @Description - 정기적금 예금계좌 개설
     *      1. 계좌 개설 거래 내역 생성
     *      2. 마감 입금액 상태 변경
     *      3. 계좌 생성
     *      4. 자동이체 여부 및 자동이체 정보 생성
     *      5. 자동이체 즉시 출금
     *      6. 성공 여부
     */

    public String openSavingsAccount(AccountOpen accountOpen, AutoTransferCreate autoTransferCreate) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 계좌 개설
        accountService.createAccount(accountOpen, tradeNumber, currentData);

        // 계좌 개설 거래내역 생성
        tradeService.createOpenTrade(accountOpen,currentData, tradeNumber);

        // 행원 마감 입금액 변경
        businessDayCloseService.updateTradeAmount(accountOpen.getBalance(), currentData, accountOpen.getTradeType());

        // 자동이체 등록시
        autoTransferService.createAutoTransferInfo(autoTransferCreate);

        return accountOpen.getId();
    }
    /**
     *   @Description - 정기적금 예금계좌 해지
     *      1. 계좌 해지 거래 내역 생성
     *      2. 계좌 잔액 및 상태 변경
     *      3. 자동이체 및 해지 금액 계산
     *
     */

    @Transactional(rollbackFor = Exception.class)
    public String addCloseSavingsTrade(AccountClose accountClose){

        StringBuilder resultText = new StringBuilder("SUCCESS");

        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        TradeCreate tradeResult = tradeService.createCloseTrade(accountClose, currentData, tradeNumber);
        AccountUpdate accountUpdate = accountService.updateByCloseTrade(accountClose, currentData);


        // 행원 마감 출금액 변경
        businessDayCloseService.updateTradeAmount(accountClose.getAmount(), currentData, accountClose.getTradeType());

        // 적금 계좌 해지 정보 return
        return null;

    }

//    public CloseSavingsAccountTotal findCloseSavingAccountTotal(String accountId){
//
//        Optional<CloseSavingsAccountTotal> optCloseSavingsAccount = Optional.ofNullable(accountService.getCloseSavingsAccount(accountId));
//        CloseSavingsAccountTotal closeSavingsAccount = optCloseSavingsAccount.orElse(new CloseSavingsAccountTotal());
//
//        CloseSavingsAccountTotal closeSavingsAccountTotal = CloseSavingsAccountTotal.builder()
//                .accountId(closeSavingsAccount.getAccountId())
//                .accountStatus(closeSavingsAccount.getAccountStatus())
//                .customerName(closeSavingsAccount.getCustomerName())
//                .customerId(closeSavingsAccount.getCustomerId())
//                .productName(closeSavingsAccount.getProductName())
//                .accountInterestRate(closeSavingsAccount.getAccountInterestRate())
//                .productInterestRate(closeSavingsAccount.getProductInterestRate())
//                .accountBalance(closeSavingsAccount.getAccountBalance())
//                .productTaxRate(closeSavingsAccount.getProductTaxRate()) // 자동이체 관련 써야됨
//                .build();
//
//        return closeSavingsAccountTotal;
//    }

    //---------------------------------------------------------
    /**
     *   @Description - 자유적금 계좌 해지
     *
     *   1. 계좌 해지 거래 내역 생성
     *   2. 계좌 잔액 및 상태 변경
     *   3. 이자 지급일 및 상태 변경
     */



    
    

    //---------------------------------------------------------
    /**
     *   @Description - 보통예금 계좌 해지
     *   1. 해지 계좌 정보 조회
     *   2. 계좌 해지 거래 내역 생성
     *   3. 계좌 잔액 및 상태 변경
     *   4. 이자 지급일 및 상태 변경
     */
    @Transactional(rollbackFor = Exception.class)
    public AccountCloseResult addCloseTrade(AccountClose accountClose) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        TradeCreate tradeResult = tradeService.createCloseTrade(accountClose, currentData, tradeNumber);
        AccountUpdate accountUpdate = accountService.updateByCloseTrade(accountClose, currentData);
        int paymentStatusResult = interestService.updateByClose(accountClose, currentData);

        // 행원 마감 출금액 변경
        businessDayCloseService.updateTradeAmount(accountClose.getAmount(), currentData, accountClose.getTradeType());

        // 해지 계좌 정보 조회
        CloseSavingsFlexibleAccountTotal closeSavingsFlexibleAccountById = accountService.getCloseSavingsFlexibleAccountById(accountClose.getAccId());

        return AccountCloseResult.builder()
                .accountId(accountClose.getAccId())
                .accountStatus(accountUpdate.getStatus())
                .openDate(Timestamp.valueOf(LocalDateTime.parse(closeSavingsFlexibleAccountById.getOpenDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .accountPreInterRate(closeSavingsFlexibleAccountById.getPreferentialInterestRate())
                .accountBal(accountUpdate.getBalance())
                .customerName(closeSavingsFlexibleAccountById.getCustomerName())
                .customerId(closeSavingsFlexibleAccountById.getCustomerId())
                .productName(closeSavingsFlexibleAccountById.getProductName())
                .productInterRate(closeSavingsFlexibleAccountById.getInterestRate())
                .productTaxRate(closeSavingsFlexibleAccountById.getTaxRate())
                .amountSum(accountClose.getAmount())
                .build();
    }


    /**
     *   @Description - 보통예금 해지 취소
     *
     *   1. 계좌 해지 취소 거래 내역 생성
     *   2. 계좌 잔액 및 상태 변경(Rollback)
     *   3. 이자 지급일 및 상태 변경(Rollback)
     */
    @Transactional(rollbackFor = Exception.class)
    public CloseCancelDetail rollbackAccountCancel(String accId){
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 계좌 해지 날짜
        String expireDate = accountService.getExpireDateById(accId);

        CurrentData currentData = commonService.getCurrentData();

        AccountIdWithExpireDate awe = AccountIdWithExpireDate.builder()
                .expireDate(expireDate)
                .accountId(accId).build();

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 해지 계좌 조회
        CloseAccount closeAccount = Optional.ofNullable(accountService.getCloseAccount(accId)).orElse(new CloseAccount());

        // 세율
        BigDecimal productTaxRate = closeAccount.getProductTaxRate();

        // 세전 이자 합계
        InterestSum preTaxInterest = interestService.getPreTaxInterestSum(awe);
        BigDecimal preTaxInterestAmount = preTaxInterest == null ? new BigDecimal("0") : preTaxInterest.getAmountSum();

        // 지급됐던 금액(계좌 잔액 + 세후 이자)
        BigDecimal paidAmount = Optional.ofNullable(tradeService.getPaidAmount(awe)).orElse(new BigDecimal("0"));

        // 세후이자 = (세전 이자 합계) * (1 - 세율)
        BigDecimal afterTaxInterest = preTaxInterestAmount.multiply(BigDecimal.ONE.subtract(productTaxRate));

        // 이자를 제외한 잔액(되돌려야할 계좌 잔액) = 지급됐던 금액 - 세후 이자
        BigDecimal balanceToRollback = paidAmount.subtract(afterTaxInterest);



        //계좌상태 변경
        int resultAccount = accountService.updateByCloseCancelTrade(accId, currentData, balanceToRollback);
        // 이자 테이블 상태변경 rollbackPaymentStatus 사용
        int resultInterest = interestService.updateByCloseCancel(accId, currentData, expireDate);
        // 해지 취소 거래 등록 addCancelTrade
        int resultTrade = tradeService.createCloseCancelTrade(accId, currentData, balanceToRollback,tradeNumber);

        // 행원 마감 입금액 변경
        businessDayCloseService.updateTradeAmount(paidAmount, currentData, "CLOSE_CANCEL");

        return CloseCancelDetail.builder()
                .customerName(closeAccount.getCustomerName())
                .accountId((closeAccount.getAccountId()))
                .productName(closeAccount.getProductName())
                .interRate(closeAccount.getProductInterRate())
                .preInterRate(closeAccount.getAccountPreInterRate())
                .taxRate(closeAccount.getProductTaxRate())
                .preTaxInterest(preTaxInterestAmount)
                .afterTaxInterest(afterTaxInterest)
                .balanceToRollback(balanceToRollback)
                .build();
    }


    /**
     *   @Description - 해지 계좌 내역 조회
     *
     *   1. 해지된 계좌 정보
     *   2. 해지 계좌 이자 계산 정보
     */
    public AccountCloseResult findCloseAccountTotal(String accountId) {

        InterestSum interestSum = interestService.getInterestSum(accountId);

        Optional<CloseAccount> optCloseAccount = Optional.ofNullable(accountService.getCloseAccount(accountId));
        CloseAccount closeAccount = optCloseAccount.orElse(new CloseAccount());

        AccountCloseResult cat = AccountCloseResult.builder()
                .accountId(closeAccount.getAccountId())
                .accountStatus(closeAccount.getAccountStatus())
                .customerName(closeAccount.getCustomerName())
                .customerId(closeAccount.getCustomerId())
                .productName(closeAccount.getProductName())
                .openDate(closeAccount.getOpenDate())
                .accountPreInterRate(closeAccount.getAccountPreInterRate())
                .productInterRate(closeAccount.getProductInterRate())
                .accountBal(closeAccount.getAccountBal())
                .productTaxRate(closeAccount.getProductTaxRate())
                .amountSum(interestSum.getAmountSum()).build();

        return cat;
    }

    /**
     * @Description - 계좌 이체
     *
     * 1. 입출금 계좌 조회
     * 2. 예외 처리
     *      - 출금 계좌가 없을 때
     *      - 출금 계좌가 해지됐을 때
     *      - 비밀번호 검증에 실패했을 때
     * 3. 출금 계좌 잔액 조회
     * 4. 예외 처리
     *      - 이체 금액이 잔액보다 큰 경우
     * 5. 상대 계좌 조회 -> 입금 계좌 잔액 조회
     * 6. 거래번호 조회
     * 7. 입금 거래내역 추가 + 계좌 잔액 변경
     * 8. 출금 거래내역 추가 + 계좌 잔액 변경
     * 9. 상세보기 모달을 위한 입출금 결과 데이터 반환
     *
     */

    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransfer(TransferTradeCreate transferTradeCreate) {
        log.info("트랜잭션 시작: 계좌 {}에서 계좌 {}로 이체 금액 {}", transferTradeCreate.getAccId(), transferTradeCreate.getTargetAccId(), transferTradeCreate.getTransferAmount());

        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 입출금 계좌 조회
        // 출금 계좌: accId
        // 입금 계좌: targetAccId

        AccountDetail withdrawalAccount = null;
        AccountDetail depositAccount = null;

        if(transferTradeCreate.getAccId().compareTo(transferTradeCreate.getTargetAccId()) < 0)
        {
            withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
            depositAccount = accountService.getAccountDetail(transferTradeCreate.getTargetAccId());
        }
        else{
            depositAccount = accountService.getAccountDetail(transferTradeCreate.getTargetAccId());
            withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
        }

        log.info("{} 스레드 계좌 조회 완료", Thread.currentThread().getName());

        if(withdrawalAccount == null) {  // 출금 계좌가 없을 때
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        else if(withdrawalAccount.getStatus().equals("CLS")){  // 출금 계좌가 해지됐을 때
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }
        else{   // 비밀번호 검증에 실패했을 때
            accountService.validatePassword(new PasswordValidation(transferTradeCreate.getAccId(), transferTradeCreate.getAccountPassword()));
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountBalance = withdrawalAccount.getBalance();
        // 오늘의 이체 출금총액 조회
        BigDecimal transferAmountOfToday = tradeService.getTransferAmountOfToday(TradeSearch.builder().accId(withdrawalAccount.getId()).tradeDate(currentData.getCurrentBusinessDate()).build());
        transferAmountOfToday = (transferAmountOfToday != null) ? transferAmountOfToday : BigDecimal.ZERO;


        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_TRANSFER_AMOUNT);
        }
        if (transferTradeCreate.getTransferAmount().add(transferAmountOfToday).compareTo(withdrawalAccount.getDailyLimit()) > 0){
            throw new BadRequestException(ErrorCode.OVER_DAILY_LIMIT);
        } else if (transferTradeCreate.getTransferAmount().compareTo(withdrawalAccount.getPerTradeLimit()) > 0) {
            throw new BadRequestException(ErrorCode.OVER_PER_TRADE_LIMIT);
        }

        // 상대 계좌 조회 -> 입금 계좌 잔액 조회
        BigDecimal depositAccountBalance = accountService.getAccountDetail(transferTradeCreate.getTargetAccId()).getBalance();


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        // 출금 거래내역 추가
        TransferDetail withdrawalTrade = tradeService.createTransferTrade(transferTradeCreate, withdrawalAccount.getCustomerName(), currentData, withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()), tradeNumber, "WITHDRAWAL");
        // 출금 계좌 잔액 업데이트
        accountService.updateByTransferTrade(withdrawalAccount, currentData, withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()));


        // 입금 계좌 조회 시
        if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TARGET_ACCOUNT);
        }

        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 계좌번호 바꾸기
        transferTradeCreate.setAccId(depositAccount.getId());
        transferTradeCreate.setTargetAccId(withdrawalAccount.getId());

        // 입금 거래내역 추가
        TransferDetail depositTrade = tradeService.createTransferTrade(transferTradeCreate,depositAccount.getCustomerName() ,currentData, depositAccountBalance.add(transferTradeCreate.getTransferAmount()), tradeNumber, "DEPOSIT");
        // 입금 계좌 잔액 업데이트
        accountService.updateByTransferTrade(depositAccount, currentData, depositAccountBalance.add(transferTradeCreate.getTransferAmount()));

        // 출금 내역과 입금 내역 반환
        return Arrays.asList(withdrawalTrade, depositTrade);
    }


    /**
     * @return
     * @Description - 계좌 이체 취소
     * 1. 거래 내역 조회 + 거래 계좌 잔액 조회
     * 1-1 예외처리: 비밀번호 검증
     * 2. 거래 번호 내역의 상태 값 CAN으로 변경
     * 3. 역거래 내역 추가
     */
    @Transactional(rollbackFor = {Exception.class})
    public List<TransferDetail> updateTransferCancel(TradeCancelRequest tradeCancelRequest) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래 내역 조회
        List<TransferDetail> tradeList = tradeService.getTradeByTradeNumber(tradeCancelRequest.getTradeNumber());

        // 거래번호가 없다면
        if(tradeList == null || tradeList.isEmpty()) {
            throw new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER);
        }

        // status 값 CAN 으로 변경
        int updateCancelStatus = tradeService.updateTradeStatusToCancel(tradeCancelRequest.getTradeNumber());

        // status 값 변경 실패
        if(updateCancelStatus == 0){
            throw new BadRequestException(ErrorCode.REQUIRED_UPDATE_TRANSFER_CANCEL);
        }


        // 입금된 계좌 번호
        String depositAccId = tradeList.stream().filter(t -> t.getTradeType().equals("DEPOSIT")).map(t -> t.getAccId()).findFirst().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT));
        // 입금된 계좌 정보 
        AccountDetail depositAccountDetail = accountService.getAccountDetail(depositAccId);

        // 계좌 비밀번호 판별
        accountService.validatePassword(new PasswordValidation(depositAccountDetail.getId(), tradeCancelRequest.getPassword()));

        // 출금된 계좌 번호
        String withdrawalAccId = tradeList.stream().filter(t -> t.getTradeType().equals("WITHDRAWAL")).map(t -> t.getAccId()).findFirst().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT));
        // 출금된 계좌 정보
        AccountDetail withdrawalAccountDetail = accountService.getAccountDetail(withdrawalAccId);

        // 거래 금액
        BigDecimal amount = tradeList.stream().filter(t -> t.getTradeType().equals("WITHDRAWAL")).map(t -> t.getAmount()).findFirst().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_TRADE_NUMBER));

        // 입금 -> 출금 역거래 생성 내역
        // 입금된 계좌에서 출금
        TransferDetail reverseWithdrawalTrade = tradeService.createTransferCancelTrade(
                TransferTradeCreate.builder()
                        .accId(depositAccountDetail.getId())
                        .targetAccId(withdrawalAccountDetail.getId())
                        .transferAmount(amount).description("거래 취소")
                        .build(),
                withdrawalAccountDetail.getCustomerName(), currentData, depositAccountDetail.getBalance().subtract(amount), tradeCancelRequest.getTradeNumber(), "WITHDRAWAL");
        
        // 입금된 계좌에서 출금 후 잔액 업데이트
        accountService.updateByTransferTrade(depositAccountDetail, currentData, depositAccountDetail.getBalance().subtract(amount));
        
        // 출금 -> 입금 역거래 생성 내역
        // 출금된 계좌에서 입금
        TransferDetail reverseDepositTrade = tradeService.createTransferCancelTrade(
                TransferTradeCreate.builder()
                        .accId(withdrawalAccountDetail.getId())
                        .targetAccId(depositAccountDetail.getId())
                        .transferAmount(amount)
                        .description("거래 취소")
                        .build(),
                depositAccountDetail.getCustomerName() ,currentData, withdrawalAccountDetail.getBalance().add(amount), tradeCancelRequest.getTradeNumber(), "DEPOSIT");

        // 출금된 계좌에서 입금 후 잔액 업데이트
        accountService.updateByTransferTrade(withdrawalAccountDetail, currentData, withdrawalAccountDetail.getBalance().add(amount));

        return Arrays.asList(reverseWithdrawalTrade, reverseDepositTrade);
    }

    /**
     * @Description - 현금 거래
     *
     * 1. 거래 계좌 조회 + 거래 계좌 잔액 조회
     * 2. 거래번호 조회
     * 3. 거래내역 추가 + 계좌 잔액 변경
     * 4. 상세보기 모달을 위한 거래내역 결과 데이터 반환
     */
    @Transactional(rollbackFor = {Exception.class})
    public TradeDetail createCashTrade(CashTradeCreate cashTradeCreate){
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래 계좌 조회 + 거래 계좌 잔액 조회
        AccountDetail cashTradeAccount = accountService.getAccountDetail(cashTradeCreate.getAccId());


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 현금 거래 내역 생성
        TradeDetail tradeDetail = tradeService.createCashTrade(cashTradeCreate, currentData, cashTradeAccount ,tradeNumber);

        // 잔액 업데이트
        accountService.updateByCashTrade(cashTradeAccount, currentData, tradeDetail.getBalance());


        /**
         * 현금 마감 업데이트
         * 변동될 금액 : cashTradeCreate.getAmount()
         * 거래 유형 : cashTradeCreate.getTradeType()
         * 1. DEPOSIT 이면 마감에는  + getAmount()
         * 2. WITHDRAWAL 이면 마감에는 -getAmount()
         */

//        businessDayCloseService.updateTotalCash(cashTradeCreate.getTradeType(), cashTradeCreate.getAmount());

        // 행원 마감 입출금액 변경
        businessDayCloseService.updateTradeAmount(cashTradeCreate.getAmount(), currentData, cashTradeCreate.getTradeType());

        return tradeDetail;
    }

    public Long processBulkTransfer(List<TransferTradeCreate> transferTradeCreateList) {
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();
        CurrentData currentData = commonService.getCurrentData();
        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        Long bulkTransferId = bulkTransferService.getNextId();

        BulkTransferCreate bulkTransferCreate = BulkTransferCreate.builder()
                .id(bulkTransferId)
                .registrantId(currentData.getEmployeeId())
                .registeredAmount(transferTradeCreateList.stream().map(TransferTradeCreate::getTransferAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
                .amount(BigDecimal.ZERO)
                .branchId(currentData.getBranchId())
                .accId(transferTradeCreateList.get(0).getAccId())
                .tradeDate(currentData.getCurrentBusinessDate())
                .status("PROCESSING")
                .successCnt(0)
                .failureCnt(0)
                .totalCnt(transferTradeCreateList.size())
                .description(transferTradeCreateList.get(0).getDescription())
                .build();

        bulkTransferService.createBulkTransfer(bulkTransferCreate);
        bulkTransferService.createProgress(bulkTransferId, transferTradeCreateList.size());


        transferTradeCreateList.forEach(transferTradeCreate -> {
            transactionService.invokeAsync(() -> {
                try {
                    transferTradeCreate.setBulkTransferId(bulkTransferId);
                    processTransfer(transferTradeCreate);

                    BulkTransferUpdate bulkTransferUpdate = BulkTransferUpdate.builder()
                            .id(bulkTransferId)
                            .perTradeStatus("SUCCESS")
                            .amount(transferTradeCreate.getTransferAmount())
                            .modifierId(currentData.getEmployeeId())
                            .build();
                    bulkTransferService.updateBulkTransfer(bulkTransferUpdate);
                    bulkTransferService.increaseSuccessCnt(bulkTransferId, currentData.getEmployeeId());
                } catch (CustomException e) {
                    transferTradeCreate.setFailureReason(e.getErrorCode().getMessage());
                    processFailTransfer(transferTradeCreate);

                    BulkTransferUpdate bulkTransferUpdate = BulkTransferUpdate.builder()
                            .id(bulkTransferId)
                            .perTradeStatus("FAIL")
                            .modifierId(currentData.getEmployeeId())
                            .build();
                    bulkTransferService.updateBulkTransfer(bulkTransferUpdate);
                    bulkTransferService.increaseFailureCnt(bulkTransferId, currentData.getEmployeeId());

                }
                return null;
            });

        });


        return bulkTransferId;
    }

    public void processFailTransfer(TransferTradeCreate transferTradeCreate) {
        CurrentData currentData = commonService.getCurrentData();
        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        AccountDetail withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
        BigDecimal withdrawalAccountBalance = withdrawalAccount.getBalance();

        tradeService.createTransferFailureTrade(transferTradeCreate, currentData, withdrawalAccountBalance, tradeNumber, "WITHDRAWAL");

    }

    public BulkTransferValidationResult validateBulkTransfer(List<BulkTransferValidation> bulkTransferValidationList) {

        int inconsistencyCnt = 0;
        int errorCnt = 0;

        for(BulkTransferValidation bulkTransferValidation: bulkTransferValidationList){
            AccountDetail depositAccount = accountService.getAccountDetail(bulkTransferValidation.getTargetAccId());



            String status = "";
            // 입금 계좌 오류
            if(depositAccount == null || depositAccount.getStatus().equals("CLS")){
                status = "계좌 오류";
                errorCnt++;

            }
            else if (!depositAccount.getCustomerName().equals(bulkTransferValidation.getDepositor())){
                status = "예금주 불일치";
                inconsistencyCnt++;
                bulkTransferValidation.setValidDepositor(depositAccount.getCustomerName());
            }
            else
            {
                status = "정상";
                bulkTransferValidation.setValidDepositor(depositAccount.getCustomerName());
            }
            bulkTransferValidation.setStatus(status);
        }

        BulkTransferValidationResult result = BulkTransferValidationResult.builder()
                .bulkTransferValidationList(bulkTransferValidationList)
                .totalCnt(bulkTransferValidationList.size())
                .inconsistencyCnt(inconsistencyCnt)
                .errorCnt(errorCnt)
                .normalCnt(bulkTransferValidationList.size() - inconsistencyCnt - errorCnt)
                .build();

        return result;
    }





    /**
     * @Description
     *  자동이체 관련 : 동일한 금액을 동일한 계좌에 이체하는 거래 서비스
     *  주말 x : 자동이체는 주말에 안나감
     *
     *  크론 표현식 @Scheduled(cron = "0 0 0 * * MON-FRI")
     *  0 0 9: 매일 자정 (0시 0분 0초)
     *  *: 모든 일
     *  *: 모든 월
     *  MON-FRI: 월요일부터 금요일까지 (주말 제외)
     *
     *
     **/

    /**
     *   자동이체
     *  1. 잔액부족시 미납 횟수 +1 (다음 달 이체일 변경 x)
     *  2. 다음 달에도 안내면 미납횟수 +1 (다음 달 이체일 변경 x)
     *  3-1. 돈 몰아서 낼 수 있으면 내기 (다음 달 이체일 변경 0)
     *  3-2. 다음 달에도 안내면 미납횟수 +1
     *  4. 3회 초과시 status stop으로 바꾸면서 자동이체 해지
     *  5.
     * */

    /**
     * @Description
     * 1. 자동이체 해당날짜(당일) 내역을 예약이체에 등록된다 -> 스케줄러 돌려서
     *  자동이체는 영업일에만 이체됨 따라서 오픈 후 차례대로 실행
     *  시간 설정 x -> 날짜 주기 정함 -> 자동이체됨
     *
     */
  //  @Scheduled(fixedRate = 6000)
  @Scheduled(cron = "0 0 0 * * MON-FRI")
    public void scheduleAutoTransfers(){
        System.out.println("scheduleReserveTransfers >>>>>> ");

        //1. 활성 상태의(wait)이고, 다음 영업일 = 영업일 당일인 자동이체 리스트 조회
        List<AutoTransferList> todayAutoList = autoTransferService.findScheduledAutoTransferList();

        // 2. 오늘의 자동이체 리스트가 비어 있는지 확인하고 로그 출력
        if (todayAutoList.isEmpty()) {
            System.out.println("No scheduled auto transfers found for today.");
        } else {
            System.out.println("Scheduled auto transfers for today:");
            for (AutoTransferList autoTransfer : todayAutoList) {
                System.out.println("조회된 자동이체 리스트>>>>"+autoTransfer); // toString() 메서드가 정의되어 있어야 출력이 제대로 됩니다.
            }
        }
        //2. 조회된 자동이체 리스트 -> 예약이체 등록
        if (!todayAutoList.isEmpty()) {
            System.out.println("조회된 자동이체 리스트>>>>");
            registerReserveTransfers(todayAutoList);
        }
    }




    // 자동이체 리스트 -> 예약이체등록하기
    // 예약이체에 자동이체거래내역 넣기,
    private void registerReserveTransfers(List<AutoTransferList> todayAutoList) {
        List<ReserveTransferCreate> reserveTransfers = new ArrayList<>();

        System.out.println("Created ReserveTransfer: >>>>>>>>>>>>>  for (AutoTransferList autoTransfer : todayAutoList)");
        for (AutoTransferList autoTransfer : todayAutoList) {
            System.out.println("for (AutoTransferList >>>>>"+autoTransfer.getAccId());
            ReserveTransferCreate reserveTransfer = new ReserveTransferCreate();

            reserveTransfer.setAutoTransferId(autoTransfer.getId().toString());
            reserveTransfer.setAccId(autoTransfer.getAccId());
            reserveTransfer.setTargetAccId(autoTransfer.getTargetAccId());
            reserveTransfer.setAmount(autoTransfer.getAmount());
            reserveTransfer.setStatus("WAIT"); // 초기 상태를 대기(pending)로 설정
            reserveTransfer.setRegistrantId(autoTransfer.getRegistrantId());
            reserveTransfer.setTransferType("AUTO");
            reserveTransfer.setTransferDate(autoTransfer.getNextTransferDate());
            reserveTransfer.setMissedCount(autoTransfer.getMissedCount());



            // 필요시 추가 필드 설정

            System.out.println("Created ReserveTransfer List: " +
                    "autoTransferId = " + reserveTransfer.getAutoTransferId() +
                    ", accId = " + reserveTransfer.getAccId() +
                    ", targetAccId = " + reserveTransfer.getTargetAccId() +
                    ", amount = " + reserveTransfer.getAmount() +
                    ", status = " + reserveTransfer.getStatus() +
                    ", registrantId = " + reserveTransfer.getRegistrantId() +
                    ", transferType = " + reserveTransfer.getTransferType());
            reserveTransfers.add(reserveTransfer);
        }


        // 데이터베이스에 삽입
        if (!reserveTransfers.isEmpty()) {
            for (ReserveTransferCreate transfer : reserveTransfers) {
                try {
                    reserveTransferMapper.insertScheduledAutoTransfer(transfer);
                } catch (Exception e) {
                    System.err.println("Error inserting scheduled transfer for ID " + transfer.getId() + ": " + e.getMessage());
                }
            }
        }


    }




    /**
     * @Description
     * 예약 이체 :특정주기 단위가 아니라 특정일에 잊지 않고 한번 자금을 이체하는 서비스
     * 대기 중인(wait) 예약 이체 목록을 조회
     * 30분 마다 조회 -> 조회이유 : 예약 취소가 날수 있음. 반영해야댐
     *
     */

  /*
  *
  *
   *  조건 :
   *      현재 영업일 = 예약일
   *      & 상태 = WAIT
   *      & 예약 시간대
   *         ->  start time > 현재 실행 시간 > end time
   *  실행 해야 하는 전체 예약이체 정보 리스트 가져오기

   * */


    // 오늘 실행할 거래 조회 기능 0
  // @Scheduled(fixedRate = 10000)
    public void todayScheduleReserveTransfers() {

        SearchReserve searchWait = new SearchReserve();
        searchWait.setStatus("WAIT");

        // 대기 중인 예약 이체 목록 조회
        List<TransferTradeCreate> waitTransfers = reserveTransferService.getPendingTransfers(searchWait);
        System.out.println("Wait reserve Transfers: " + waitTransfers); // 대기 중인 이체 로그
        for (TransferTradeCreate transfer : waitTransfers) {
            System.out.println("대기 이체 정보>>>>>>>>>>>>>>>>"+transfer.toString());
        }

        // 대기 중인 이체 처리
        if (!waitTransfers.isEmpty()) {
            processReserveTransfer(waitTransfers);
        }

    }

    /**
     * @Description
     * - 스케줄러가 돌았을 때 처리해야하는 작업내역을 해당 메서드로 받아온다 0 / todayScheduleReserveTransfers()
     * - 성공했을 때
     *    1. 예약 이체의 상태를 SUCCESS로 변경한다. 0
     *    2. 거래내역에 예약이체 내역을 쌓는다. 0
     * - 실패했을 때는
     *    1. 예약 이체의 상태는 FAIL로 변경한다.0
     *    2. type이 자동이체이면 예약이체 생성로직 시작한다.
     *      (transferType = AUTO)
     *    3. 미납 횟수조회 후 횟수에 따른 분기처리한다.
     *    - 3이하 (!missedCount > 3)
     *      1. missCount +1 변경한다. 0
     *      2. 새로운 예약 이체를 거래내역를 생성한다. 0
     *    - 3초과 (missedCount > 3)
     *      3. 최대 미납 횟수 초과 시 자동이체 상태를 STOP / missCount +1 X
     *      4. 메일 전송 X
     */
    public void processReserveTransfer(List<TransferTradeCreate> transferTradeCreateList) {

        for (TransferTradeCreate transferTradeCreate : transferTradeCreateList) {
            try {
                System.out.println("Processing transfer for Reserve ID: " + transferTradeCreate.getReserveTransferId());

                AutoProcessTransfer(transferTradeCreate); // 이체 거래 시도

                // 성공 시 예약 이체의 상태를 SUCCESS로 변경
                String autoId = transferTradeCreate.getAutoTransferId();
                reserveTransferService.updateReserveTransferStatus(autoId, "SUCCESS", null);
                System.out.println("Transfer successful for Reserve ID: " + autoId);

            } catch (CustomException e) {
                System.out.println("거래실패내역 >>>>>>>>>>>>>");

                // 예약이체 실패 상태 변경
                String autoId = transferTradeCreate.getAutoTransferId();
                transferTradeCreate.setFailureReason(e.getErrorCode().getMessage());

                // 실패 상태로 변경 + ( missedCount +1)
                reserveTransferService.updateReserveTransferStatus(autoId, "FAIL", transferTradeCreate.getFailureReason());
                
                // 실패했을 때 자동이체 로직 시작 
                //   1. 미납 횟수 조회
                //   2. 미납 횟수에따른 분기처리
                //   -> 미납 횟수 카운팅 및 새로운 예약 이체 생성 로직
                if(transferTradeCreate.getTransferType().equals("AUTO")){
                    System.out.println("예약이체 실패시 로직 시작 >>>>>>>>"+transferTradeCreate.getMissedCount());

                    // 미납 횟수 조회
                    Long currentMissedCount = transferTradeCreate.getMissedCount();

                     //미납이 3 초과이면
                    if(currentMissedCount > 3){
                        System.out.println("자동이체 중지 stop 로직 >>>>> ");

                        // 최대 미납 횟수 초과 시 자동이체 상태를 STOP / missCount +1
                        System.out.println("Transfer STOPPED 로직 확인 >>>>>");
                        reserveTransferService.updateReserveTransferStatus(autoId, "STOP", "자동이체 미납 횟수 초과");
                        reserveTransferService.updateMissedTransferOfAutoTransfer(autoId);

                        System.out.println(" sns or 메일 전송 >>>>> ");

                    } else {
                        System.out.println("예약이체 실패 로직 진행 >>>");
                        
                        // missCount = +1
                        System.out.println("예약이체 update missCount +1 >>>");
                        reserveTransferService.updateMissedTransferOfAutoTransfer(autoId);

                        // 새로운 예약이체 생성 (한달 후)
                        //1. 자동이체 예약일 구해오기
                        Timestamp nextReserveDate = reserveTransferService.findAutoReserveDate(autoId);


                        //2. 새로운 예약이체 쌓기
                        ReserveTransferCreate reserveTransferCreate = ReserveTransferCreate.builder()
                                .accId(transferTradeCreate.getAccId())
                                .targetAccId(transferTradeCreate.getTargetAccId())
                                .amount(transferTradeCreate.getTransferAmount())
                                .transferDate(nextReserveDate)
                                .transferStartTime(transferTradeCreate.getTransferStartTime())
                                .transferEndTime(transferTradeCreate.getTransferEndTime())
                                .transferType(transferTradeCreate.getTransferType())
                                .description("자동이체 실패 후 예약이체")
                                .status("WAIT")
                                .autoTransferId(transferTradeCreate.getAutoTransferId())
                                .transferType(transferTradeCreate.getTransferType())
                                .retryCount(transferTradeCreate.getRetryCount())
                                .registrantId(transferTradeCreate.getRegistrantId())
                                .branchId(transferTradeCreate.getBranchId())
                                .build();
                        System.out.println("새로운 예약이체 쌓기: >>>>>>>>>>>>>>>>>>>" );

                        // 예약이체 쌓기
                        reserveTransferService.createReserveTransferBySystem(reserveTransferCreate);


                    }
                }

            }
        }
    }


    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> AutoProcessTransfer(TransferTradeCreate transferTradeCreate) {
        log.info("트랜잭션 시작: 계좌 {}에서 계좌 {}로 이체 금액 {}", transferTradeCreate.getAccId(), transferTradeCreate.getTargetAccId(), transferTradeCreate.getTransferAmount());

        BusinessDay businessDay = new BusinessDay();
        businessDay.setBusinessDate("OPEN");
        businessDay.setIsCurrentBusinessDay("TRUE");


        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(currentTimestamp);

        // 시스템 id 0 , branchid 가져오기
        CurrentData currentData = new CurrentData(1L,1L,time);
        // 입출금 계좌 조회
        // 출금 계좌: accId
        // 입금 계좌: targetAccId

        AccountDetail withdrawalAccount = null;
        AccountDetail depositAccount = null;

        if(transferTradeCreate.getAccId().compareTo(transferTradeCreate.getTargetAccId()) < 0)
        {
            withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
            depositAccount = accountService.getAccountDetail(transferTradeCreate.getTargetAccId());
        }
        else{
            depositAccount = accountService.getAccountDetail(transferTradeCreate.getTargetAccId());
            withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
        }

        log.info("{} 스레드 계좌 조회 완료", Thread.currentThread().getName());

        if(withdrawalAccount == null) {  // 출금 계좌가 없을 때
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        else if(withdrawalAccount.getStatus().equals("CLS")){  // 출금 계좌가 해지됐을 때
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountBalance = withdrawalAccount.getBalance();
        // 오늘의 이체 출금총액 조회
        BigDecimal transferAmountOfToday = tradeService.getTransferAmountOfToday(TradeSearch.builder().accId(withdrawalAccount.getId()).tradeDate(currentData.getCurrentBusinessDate()).build());
        transferAmountOfToday = (transferAmountOfToday != null) ? transferAmountOfToday : BigDecimal.ZERO;


        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_TRANSFER_AMOUNT);
        }
        if (transferTradeCreate.getTransferAmount().add(transferAmountOfToday).compareTo(withdrawalAccount.getDailyLimit()) > 0){
            throw new BadRequestException(ErrorCode.OVER_DAILY_LIMIT);
        } else if (transferTradeCreate.getTransferAmount().compareTo(withdrawalAccount.getPerTradeLimit()) > 0) {
            throw new BadRequestException(ErrorCode.OVER_PER_TRADE_LIMIT);
        }

        // 상대 계좌 조회 -> 입금 계좌 잔액 조회
        BigDecimal depositAccountBalance = accountService.getAccountDetail(transferTradeCreate.getTargetAccId()).getBalance();


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        // 출금 거래내역 추가
        TransferDetail withdrawalTrade = tradeService.createTransferTrade(transferTradeCreate, withdrawalAccount.getCustomerName(), currentData, withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()), tradeNumber, "WITHDRAWAL");
        // 출금 계좌 잔액 업데이트
        accountService.updateByTransferTrade(withdrawalAccount, currentData, withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()));


        // 입금 계좌 조회 시
        if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TARGET_ACCOUNT);
        }

        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 계좌번호 바꾸기
        transferTradeCreate.setAccId(depositAccount.getId());
        transferTradeCreate.setTargetAccId(withdrawalAccount.getId());

        // 입금 거래내역 추가
        TransferDetail depositTrade = tradeService.createTransferTrade(transferTradeCreate,depositAccount.getCustomerName() ,currentData, depositAccountBalance.add(transferTradeCreate.getTransferAmount()), tradeNumber, "RESERVE");
        // 입금 계좌 잔액 업데이트
        accountService.updateByTransferTrade(depositAccount, currentData, depositAccountBalance.add(transferTradeCreate.getTransferAmount()));

        // 출금 내역과 입금 내역 반환
        return Arrays.asList(withdrawalTrade, depositTrade);
    }
}
