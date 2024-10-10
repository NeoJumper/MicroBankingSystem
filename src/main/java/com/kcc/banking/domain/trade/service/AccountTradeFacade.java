package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountUpdate;
import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.request.*;
import com.kcc.banking.domain.trade.dto.response.CloseCancelDetail;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TransferDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountTradeFacade {
    private final InterestService interestService;
    private final AccountService accountService;
    private final TradeService tradeService;
    private final CommonService commonService;

    //계좌해지신청
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
        int statusResult = accountService.updateByClose(statusWithTrade, currentData);
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
        return (tradeResult + statusResult + paymentStatusResult) > 0 ? resultText.toString() : "FAIL";
    }



    //    해지 취소 되돌리기
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
        int resultAccount = accountService.updateByCloseCancel(accId, currentData, balanceToRollback);
        // 이자 테이블 상태변경 rollbackPaymentStatus 사용
        int resultInterest = interestService.updateByCloseCancel(accId, currentData, expireDate);
        // 해지 취소 거래 등록 addCancelTrade
        int resultTrade = tradeService.createCloseCancelTrade(accId, currentData, balanceToRollback,tradeNumber);


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
     * @Description
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
        AccountDetail depositAccount = accountService.getAccountDetail(transferTradeCreate.getDepositAccount());
        AccountDetail withdrawalAccount = accountService.getAccountDetail(transferTradeCreate.getWithdrawalAccount());


        if(withdrawalAccount == null) {  // 출금 계좌가 없을 때
            throw new BadRequestException(ErrorCode.NOT_FOUND_ACCOUNT);
        }
        else if(withdrawalAccount.getStatus().equals("CLS")){  // 출금 계좌가 해지됐을 때
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }
        else{   // 비밀번호 검증에 실패했을 때
            accountService.validatePassword(new PasswordValidation(transferTradeCreate.getWithdrawalAccount(), transferTradeCreate.getWithdrawalAccountPassword()));
        }

        // 출금 계좌 잔액 조회
        BigDecimal withdrawalAccountBalance = withdrawalAccount.getBalance();

        // 이체 금액이 잔액보다 큰 경우
        if (withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(ErrorCode.OVER_TRANSFER_AMOUNT);
        }

        // 상대 계좌 조회 -> 입금 계좌 잔액 조회
        BigDecimal depositAccountBalance = accountService.getAccountDetail(transferTradeCreate.getDepositAccount()).getBalance();


        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();


        // 출금 거래내역 추가
        TransferDetail withdrawalTrade = tradeService.createWithdrawalTrade(transferTradeCreate, currentData, withdrawalAccountBalance, tradeNumber);

        // 출금 계좌 잔액 업데이트
        accountService.updateBalance(AccountUpdate.builder()
                .targetAccId(withdrawalAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(withdrawalAccountBalance.subtract(transferTradeCreate.getTransferAmount())) // 이체 후 잔액
                .build()
        );

        // 입금 계좌 조회 시
        if(depositAccount == null){
            throw new BadRequestException(ErrorCode.NOT_FOUND_TARGET_ACCOUNT);
        }
        //입금 계좌 상태 확인
        else if(depositAccount.getStatus().equals("CLS")){
            throw new BadRequestException(ErrorCode.ACCOUNT_CLOSED_FOR_TRANSFER);
        }

        // 입금 거래내역 추가
        TransferDetail depositTrade = tradeService.createDepositTrade(transferTradeCreate, currentData, depositAccountBalance, tradeNumber);
        // 입금 계좌 잔액 업데이트
        accountService.updateBalance(AccountUpdate.builder()
                .targetAccId(depositAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(depositAccountBalance.add(transferTradeCreate.getTransferAmount())) // 이체 후 잔액
                .build()
        );


        // 출금 내역과 입금 내역 반환
        return Arrays.asList(withdrawalTrade, depositTrade);
    }


    /**
     * @Description
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
        accountService.updateBalance(AccountUpdate.builder()
                .targetAccId(cashTradeAccount.getId())
                .modifierId(currentData.getEmployeeId())
                .balance(tradeDetail.getBalance()) // 거래 후 잔액
                .build()
        );

        return tradeDetail;
    }

}
