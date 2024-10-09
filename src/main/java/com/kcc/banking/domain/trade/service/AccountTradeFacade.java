package com.kcc.banking.domain.trade.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountStatus;
import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.request.PaymentStatus;
import com.kcc.banking.domain.interest.dto.request.RollbackPaymentStatus;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.request.CloseAccount;
import com.kcc.banking.domain.trade.dto.request.CloseTrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
        // 현재 영업일이 아닐 경우 FAIL 리턴하며 메서드 종료
        if (currentBusinessDay.getStatus().equals("CLOSED")) {
            throw new BadRequestException(ErrorCode.CLOSED_BUSINESS_DAY);
        }



        //  CloseTrade, AccountStatus에 분배
        CloseTrade closeTrade = CloseTrade.builder()
                .accId(statusWithTrade.getAccId())
                .registrantId(currentData.getEmployeeId())
                .branchId(currentData.getBranchId())
                .amount(statusWithTrade.getAmount())
                .description(statusWithTrade.getDescription())
                .balance(statusWithTrade.getBalance())
                .tradeType(statusWithTrade.getTradeType())
                .businessDay(currentData.getCurrentBusinessDate()).build();

        AccountStatus accountStatus = AccountStatus.builder()
                .id(statusWithTrade.getAccId())
                .status(statusWithTrade.getStatus())
                .modifierId(currentData.getEmployeeId())
                .balance(statusWithTrade.getBalance())
                .businessDay(Timestamp.valueOf(currentData.getCurrentBusinessDate())).build();

        PaymentStatus paymentStatus = PaymentStatus.builder()
                .branchId(currentData.getBranchId())
                .payDate(Timestamp.valueOf(currentData.getCurrentBusinessDate()))
                .modifierId(currentData.getEmployeeId())
                .accId(statusWithTrade.getAccId()).build();


        int tradeResult = tradeService.addCancelTrade(closeTrade);
        int statusResult = accountService.updateStatus(accountStatus);
        int paymentStatusResult = interestService.updatePaymentStatus(paymentStatus);
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

    //    해지 취소 되돌리기
    @Transactional(rollbackFor = Exception.class)
    public String rollbackAccountCancel(String accId){
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();
        // 현재 영업일이 아닐 경우 FAIL 리턴하며 메서드 종료
        if (currentBusinessDay.getStatus().equals("CLOSED")) {
            throw new BadRequestException(ErrorCode.CLOSED_BUSINESS_DAY);
        }

        // 계좌 해지 날짜
        Timestamp expireDate = accountService.getExpireDateById(accId);

        System.out.println("expireDate ===================!! 컨트롤러 안에서 해지일 " + expireDate);

        CurrentData currentData = commonService.getCurrentData();

        AccountIdWithExpireDate awe = AccountIdWithExpireDate.builder()
                .expireDate(expireDate)
                .accountId(accId).build();


        CloseAccount closeAccount = Optional.ofNullable(accountService.getCloseAccount(accId)).orElse(new CloseAccount());

        // 세율
        BigDecimal productTaxRate = closeAccount.getProductTaxRate();
        // 총 이자액
//         BigDecimal amountSum = Optional.ofNullable(accountCloseMapper.rollbackInterestSum(awe).getAmountSum()).orElse(new BigDecimal("0"));

        InterestSum rollbackInterestSum = interestService.getRollbackInterestSum(awe);
        BigDecimal amountSum = rollbackInterestSum == null ? new BigDecimal("0") : rollbackInterestSum.getAmountSum();

        // 세후이자 + 잔액
        BigDecimal rollbackAmount = Optional.ofNullable(tradeService.rollbackAmount(awe)).orElse(new BigDecimal("0"));
        // 잔액 == (거래테이블총액) - (총 이자합)*(1-세율)
        // (총 이자합) * (1 - 세율) 계산
        BigDecimal interestAfterTax = amountSum.multiply(BigDecimal.ONE.subtract(productTaxRate));
        // 잔액 계산: rollbackAmount - (총 이자합 * (1 - 세율))
        BigDecimal balance = rollbackAmount.subtract(interestAfterTax);

        // 이자 테이블 업데이트 파라미터 타입
        RollbackPaymentStatus rollbackPaymentStatus = RollbackPaymentStatus.builder()
                .branchId(currentData.getBranchId())
                .modifierId(currentData.getEmployeeId())
                .accId(accId)
                .expireDate(expireDate).build();

        // 계좌 업데이트 파라미터 타입
        AccountStatus accountStatus = AccountStatus.builder()
                .id(accId)
                .status("OPN")
                .modifierId(currentData.getEmployeeId())
                .balance(balance).build();

        CloseTrade closeTrade = CloseTrade.builder()
                .accId(accId)
                .registrantId(currentData.getEmployeeId())
                .branchId(currentData.getBranchId())
                .amount(new BigDecimal("0"))
                .description("계좌해지취소")
                .balance(balance)
                .tradeType("OPEN")
                .businessDay(currentData.getCurrentBusinessDate()).build();

        //계좌상테 변경
        int resultAccount = accountService.updateStatus(accountStatus);
        // 이자 테이블 상태변경 rollbackPaymentStatus 사용
        int resultInterest = interestService.rollbackPaymentStatus(rollbackPaymentStatus);
        // 해지 취소 거래 등록 addCancelTrade
        int resultTrade = tradeService.addCancelTrade(closeTrade);

        // 예외처리문

        return "SUCCESS";
    }
}
