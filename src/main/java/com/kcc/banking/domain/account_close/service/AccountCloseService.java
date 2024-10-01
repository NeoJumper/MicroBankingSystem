package com.kcc.banking.domain.account_close.service;

import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.request.CloseTrade;
import com.kcc.banking.domain.account_close.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.account_close.mapper.AccountCloseMapper;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCloseService {
    private final AccountCloseMapper accountCloseMapper;
    private final BusinessDayMapper businessDayMapper;

    //계좌해지신청
    @Transactional
    public String addCloseTrade(StatusWithTrade statusWithTrade) {

        BusinessDay businessDay = businessDayMapper.findCurrentBusinessDay();
        // 현재 영업일이 아닐 경우 FAIL 리턴하며 메서드 종료
        if (businessDay.getIsCurrentBusinessDay().equals("FALSE")) {
            return "FAIL";
        }

        Timestamp tradeDate = Timestamp.valueOf(businessDay.getBusinessDate());
        //  CloseTrade, AccountStatus에 분배
        CloseTrade closeTrade = CloseTrade.builder()
                .accId(statusWithTrade.getAccId())
                .registrantId(1L)
                .branchId(1L)
                .amount(statusWithTrade.getAmount())
                .description(statusWithTrade.getDescription())
                .balance(statusWithTrade.getBalance())
                .tradeType(statusWithTrade.getTradeType())
                .businessDay(tradeDate).build();

        AccountStatus accountStatus = AccountStatus.builder()
                .id(statusWithTrade.getAccId())
                .status(statusWithTrade.getStatus())
                .modifierId(1L)
                .balance(statusWithTrade.getBalance())
                .businessDay(tradeDate).build();

        int tradeResult = accountCloseMapper.addCancelTrade(closeTrade);
        int statusResult = accountCloseMapper.updateStatus(accountStatus);
        if (tradeResult > 0 && statusResult > 0) {
            return "SUCCESS";
        }
        return null;
    }

    public CloseAccountTotal findCloseAccountTotal(String accountId) {
        Optional<InterestSum> optInterestSum = Optional.ofNullable(accountCloseMapper.findInterestSum(accountId));
        InterestSum interestSum = optInterestSum.orElse(new InterestSum());

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
                .accountBal(closeAccount.getAccountBal())
                .productTaxRate(closeAccount.getProductTaxRate())
                .interestRateSum(interestSum.getInterestRateSum())
                .amountSum(interestSum.getAmountSum()).build();

        return cat;
    }

}
