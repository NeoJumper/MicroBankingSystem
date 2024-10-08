package com.kcc.banking.domain.account_close.service;

import com.kcc.banking.domain.account_close.dto.request.*;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.account_close.mapper.AccountCloseMapper;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCloseService {
    private final AccountCloseMapper accountCloseMapper;
    private final BusinessDayMapper businessDayMapper;
    private final EmployeeService employeeService;

    //계좌해지신청
    @Transactional(rollbackFor = Exception.class)
    public String addCloseTrade(StatusWithTrade statusWithTrade) {
        StringBuilder resultText = new StringBuilder("SUCCESS");

        BusinessDay businessDay = businessDayMapper.findCurrentBusinessDay();
        // 현재 영업일이 아닐 경우 FAIL 리턴하며 메서드 종료
        if (businessDay.getIsCurrentBusinessDay().equals("FALSE")) {
            return "FAIL";
        }
        // 지점번호
        Long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
        // 행원번호
        Long employeeId = Long.parseLong(employeeService.getAuthData().getId());
        // 영업일자
        Timestamp tradeDate = Timestamp.valueOf(businessDay.getBusinessDate());

        //  CloseTrade, AccountStatus에 분배
        CloseTrade closeTrade = CloseTrade.builder()
                .accId(statusWithTrade.getAccId())
                .registrantId(employeeId)
                .branchId(branchId)
                .amount(statusWithTrade.getAmount())
                .description(statusWithTrade.getDescription())
                .balance(statusWithTrade.getBalance())
                .tradeType(statusWithTrade.getTradeType())
                .businessDay(tradeDate).build();

        AccountStatus accountStatus = AccountStatus.builder()
                .id(statusWithTrade.getAccId())
                .status(statusWithTrade.getStatus())
                .modifierId(employeeId)
                .balance(statusWithTrade.getBalance())
                .businessDay(tradeDate).build();

        PaymentStatus paymentStatus = PaymentStatus.builder()
                .branchId(branchId)
                .payDate(tradeDate)
                .modifierId(employeeId)
                .accId(statusWithTrade.getAccId()).build();

        int tradeResult = accountCloseMapper.addCancelTrade(closeTrade);
        int statusResult = accountCloseMapper.updateStatus(accountStatus);
        int paymentStatusResult = accountCloseMapper.updatePaymentStatus(paymentStatus);
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
        Optional<InterestSum> optInterestSum = Optional.ofNullable(accountCloseMapper.findInterestSum(accountId));
        InterestSum interestSum = optInterestSum.orElse(InterestSum.builder()
                .accountId(accountId)
                .amountSum(new BigDecimal("0")).build());

        Optional<CloseAccount> optCloseAccount = Optional.ofNullable(accountCloseMapper.findCloseAccount(accountId));
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
        BusinessDay businessDay = businessDayMapper.findCurrentBusinessDay();
        // 현재 영업일이 아닐 경우 FAIL 리턴하며 메서드 종료
        if (businessDay.getIsCurrentBusinessDay().equals("FALSE")) {
            return "FAIL";
        }

        // 계좌 해지 날짜
        Timestamp expireDate = accountCloseMapper.findExpireDateById(accId);

        System.out.println("expireDate ===================!! 컨트롤러 안에서 해지일 " + expireDate);
        
        AccountIdWithExpireDate awe = AccountIdWithExpireDate.builder()
                .expireDate(expireDate)
                .accountId(accId).build();
        
        // 영업일
        Timestamp tradeDate = Timestamp.valueOf(businessDay.getBusinessDate());
        // 지점번호
        Long branchId = Long.parseLong(employeeService.getAuthData().getBranchId());
        // 행원번호
        Long employeeId = Long.parseLong(employeeService.getAuthData().getId());

        CloseAccount closeAccount = Optional.ofNullable(accountCloseMapper.findCloseAccount(accId)).orElse(new CloseAccount());

        // 세율
        BigDecimal productTaxRate = closeAccount.getProductTaxRate();
        // 총 이자액
//         BigDecimal amountSum = Optional.ofNullable(accountCloseMapper.rollbackInterestSum(awe).getAmountSum()).orElse(new BigDecimal("0"));
        BigDecimal amountSum = accountCloseMapper.rollbackInterestSum(awe) == null ? new BigDecimal("0") : accountCloseMapper.rollbackInterestSum(awe).getAmountSum();

        // 세후이자 + 잔액
        BigDecimal rollbackAmount = Optional.ofNullable(accountCloseMapper.rollbackAmount(awe)).orElse(new BigDecimal("0"));
        // 잔액 == (거래테이블총액) - (총 이자합)*(1-세율)
        // (총 이자합) * (1 - 세율) 계산
        BigDecimal interestAfterTax = amountSum.multiply(BigDecimal.ONE.subtract(productTaxRate));
        // 잔액 계산: rollbackAmount - (총 이자합 * (1 - 세율))
        BigDecimal balance = rollbackAmount.subtract(interestAfterTax);

        // 이자 테이블 업데이트 파라미터 타입
        RollbackPaymentStatus rollbackPaymentStatus = RollbackPaymentStatus.builder()
                .branchId(branchId)
                .modifierId(employeeId)
                .accId(accId)
                .expireDate(expireDate).build();

        // 계좌 업데이트 파라미터 타입
        AccountStatus accountStatus = AccountStatus.builder()
                .id(accId)
                .status("OPN")
                .modifierId(employeeId)
                .balance(balance).build();

        CloseTrade closeTrade = CloseTrade.builder()
                .accId(accId)
                .registrantId(employeeId)
                .branchId(branchId)
                .amount(new BigDecimal("0"))
                .description("계좌해지취소")
                .balance(balance)
                .tradeType("OPEN")
                .businessDay(tradeDate).build();

        //계좌상테 변경
        int resultAccount = accountCloseMapper.updateStatus(accountStatus);
        // 이자 테이블 상태변경 rollbackPaymentStatus 사용
        int resultInterest = accountCloseMapper.rollbackPaymentStatus(rollbackPaymentStatus);
        // 해지 취소 거래 등록 addCancelTrade
        int resultTrade = accountCloseMapper.addCancelTrade(closeTrade);
        
        // 예외처리문

        return "SUCCESS";
    }

}
