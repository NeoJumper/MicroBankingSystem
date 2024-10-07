package com.kcc.banking.domain.account_close.service;

import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.request.CloseTrade;
import com.kcc.banking.domain.account_close.dto.request.PaymentStatus;
import com.kcc.banking.domain.account_close.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.account_close.mapper.AccountCloseMapper;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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
                .modifierDate(tradeDate)
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

}
