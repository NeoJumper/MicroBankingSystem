package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.*;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.auto_transfer.service.AutoTransferService;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferCreate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferUpdate;
import com.kcc.banking.domain.bulk_transfer.dto.request.BulkTransferValidation;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferValidationResult;
import com.kcc.banking.domain.bulk_transfer.service.BulkTransferService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.trade.dto.response.CloseCancelDetail;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TransferDetail;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class AccountTradeFacade {
    private final InterestService interestService;
    private final AccountService accountService;
    private final TradeService tradeService;
    private final BusinessDayCloseService businessDayCloseService;
    private final BulkTransferService bulkTransferService;
    private final CommonService commonService;
    private final AutoTransferService autoTransferService;
    private final TradeMapper tradeMapper;

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


    /**
     *   @Description - 정기적금 예금계좌 개설
     *      1. 계좌 개설 거래 내역 생성
     *      2. 마감 입금액 상태 변경
     *      3. 계좌 생성
     *      4. 자동이체 여부 및 자동이체 정보 생성
     *      5. 자동이체 즉시 출금
     *      6. 성공 여부
     */



    /**
     *   @Description - 계좌 해지
     *
     *   1. 계좌 해지 거래 내역 생성
     *   2. 계좌 잔액 및 상태 변경
     *   3. 이자 지급일 및 상태 변경
     */
    @Transactional(rollbackFor = Exception.class)
    public String addCloseTrade(StatusWithTrade statusWithTrade) {
        StringBuilder resultText = new StringBuilder("SUCCESS");

        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();



        int tradeResult = tradeService.createCloseTrade(statusWithTrade, currentData, tradeNumber);
        int statusResult = accountService.updateByCloseTrade(statusWithTrade, currentData);
        int paymentStatusResult = interestService.updateByClose(statusWithTrade, currentData);


        if (tradeResult > 0) {
            resultText.append(" 'tradeResult' ");
        }
        if (statusResult > 0) {
            resultText.append(" 'statusResult' ");
        }
        if (paymentStatusResult > 0) {
            resultText.append(" 'paymentStatusResult' ");
        }

        // 행원 마감 출금액 변경
        businessDayCloseService.updateTradeAmount(statusWithTrade.getAmount(), currentData, statusWithTrade.getTradeType());

        return (tradeResult + statusResult + paymentStatusResult) > 0 ? resultText.toString() : "FAIL";
    }


    /**
     *   @Description - 계좌 해지 취소
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
    public CloseAccountTotal findCloseAccountTotal(String accountId) {

        InterestSum interestSum = interestService.getInterestSum(accountId);


        Optional<CloseAccount> optCloseAccount = Optional.ofNullable(accountService.getCloseAccount(accountId));
        CloseAccount closeAccount = optCloseAccount.orElse(new CloseAccount());

        CloseAccountTotal cat = CloseAccountTotal.builder()
                .accountId(closeAccount.getAccountId())
                .accountStatus(closeAccount.getAccountStatus())
                .customerName(closeAccount.getCustomerName())
                .customerId(closeAccount.getCustomerId())
                .productName(closeAccount.getProductName())
                .amountDate(closeAccount.getAmountDate())
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
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 입출금 계좌 조회
        // 출금 계좌: accId
        // 입금 계좌: targetAccId
        AccountDetail withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getAccId());
        AccountDetail depositAccount = accountService.getAccountDetail(transferTradeCreate.getTargetAccId());

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
    public TradeDetail createCashTrade(CashTradeCreate cashTradeCreate){
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래 계좌 조회 + 거래 계좌 잔액 조회
        AccountDetail cashTradeAccount = accountService.getAccountDetail(cashTradeCreate.getAccId());
        BigDecimal cashTradeAccountBalance = cashTradeAccount.getBalance();

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 현금 거래 내역 생성
        TradeDetail tradeDetail = tradeService.createCashTrade(cashTradeCreate, currentData, cashTradeAccountBalance ,tradeNumber);

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
        int successCnt = 0;
        int failureCnt = 0;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(TransferTradeCreate transferTradeCreate : transferTradeCreateList){
            try{
                transferTradeCreate.setBulkTransferId(bulkTransferId);
                processTransfer(transferTradeCreate);
                successCnt++;
                BigDecimal transferAmount = transferTradeCreate.getTransferAmount();
                totalAmount = totalAmount.add(transferAmount);
            }catch (CustomException e){
                transferTradeCreate.setFailureReason(e.getErrorCode().getMessage());
                processFailTransfer(transferTradeCreate);
                failureCnt++;
            }
        }

        BulkTransferCreate bulkTransferCreate = BulkTransferCreate.builder()
                .id(bulkTransferId)
                .registrantId(currentData.getEmployeeId())
                .branchId(currentData.getBranchId())
                .accId(transferTradeCreateList.get(0).getAccId())
                .tradeDate(currentData.getCurrentBusinessDate())
                .amount(totalAmount)
                .status("NOR")
                .successCnt(successCnt)
                .failureCnt(failureCnt)
                .description(transferTradeCreateList.get(0).getDescription())
                .build();


        bulkTransferService.createBulkTransfer(bulkTransferCreate);
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
            String customerName = depositAccount.getCustomerName();


            String status = "";
            // 입금 계좌 오류
            if(depositAccount == null || depositAccount.getStatus().equals("CLS")){
                status = "계좌 오류";
                errorCnt++;
            }
            else if (!customerName.equals(bulkTransferValidation.getDepositor())){
                status = "예금주 불일치";
                inconsistencyCnt++;
            }
            bulkTransferValidation.setStatus(status);
            bulkTransferValidation.setValidDepositor(customerName);
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
     * @Description - 대량이체 오류건 재전송
     * 1- 대량이체 테이블 수정: bulkTransferService.updateAllBulkTransfer(BulkTransferUpdate)
     * 2- 거래 테이블 수정: tradeMapper.updateAllTrade(TradeUpdate)
     * **/
    public Long processBulkTransferRetryErrors(List<TransferTradeUpdate> transferTradeUpdateList, Long bulkTransferId){
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();
        CurrentData currentData = commonService.getCurrentData();
        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 기존 대량거래 정보
        BulkTransferDetail bulkTransfer = bulkTransferService.getBulkTransfer(bulkTransferId);

        int successCnt = bulkTransfer.getSuccessCnt(); // 기존 successCnt로 초기화
        int failureCnt = bulkTransfer.getFailureCnt(); // 기존 failureCnt로 초기화
        BigDecimal totalAmount = bulkTransfer.getAmount(); // 기존 총금액으로 초기화

        for (TransferTradeUpdate transferTradeUpdate : transferTradeUpdateList){
            try {
                // 거래내역 업데이트
                successCnt++;
            }catch (CustomException e){
                transferTradeUpdate.setFailureReason(e.getErrorCode().getMessage());
                failureCnt++;
            }
        }

        BulkTransferUpdate bulkTransferUpdate = BulkTransferUpdate.builder()
                .id(bulkTransferId)
                .registrantId(bulkTransfer.getRegistrantId())
                .accId(transferTradeUpdateList.get(0).getAccId())
                .branchId(currentData.getBranchId())
                .tradeDate(currentData.getCurrentBusinessDate())
                .amount(totalAmount) //기존 amount + totalAmount
                .successCnt(successCnt)
                .failureCnt(failureCnt)
                .status("NOR")
                .description(bulkTransfer.getDescription()) // 기존
                .modifierId(currentData.getEmployeeId()).build();

        // 대량이체 테이블 update
        bulkTransferService.updateAllBulkTransfer(bulkTransferUpdate);

        return 1L;
    }

    /**
     * @Description - 대량이체 오류건 재전송 수정
     * **/
    @Transactional(rollbackFor = {Exception.class})  // 모든 예외 발생 시 롤백
    public List<TransferDetail> processTransferUpdate(TransferTradeUpdate transferTradeUpdate){

        return new LinkedList<>();
    }

    /**
     * @Description - 대량이체 오류건 재전송 수정 실패
     * **/
    public void processFailTransferUpdate(TransferTradeUpdate transferTradeUpdate) {

    }

}
