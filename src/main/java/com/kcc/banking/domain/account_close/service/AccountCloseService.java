package com.kcc.banking.domain.account_close.service;

import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.request.CloseTrade;
import com.kcc.banking.domain.account_close.dto.response.CloseAccount;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.account_close.mapper.AccountCloseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCloseService {
    private final AccountCloseMapper accountCloseMapper;

    public AccountStatus updateStatus(AccountStatus accountStatus) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String modifier = authentication.getName();
//        accountStatus.setModifier(modifier);
        accountStatus.setModifier("김영진");

        int resultNum = accountCloseMapper.updateStatus(accountStatus);
        if (resultNum>0){
            return accountStatus;
        }
        return null;
    }

    public CloseTrade addCloseTrade(CloseTrade closeTrade) {
        closeTrade.setEmpId(1L);
        closeTrade.setBranchId(1L);
        System.out.println("closeTrade.getClass() = " + closeTrade.getClass());
        int resultNum = accountCloseMapper.addCancelTrade(closeTrade);
        if(resultNum>0){
            return closeTrade;
        }
        return null;
    }

    public CloseAccountTotal findCloseAccountTotal(String accountId) {
        InterestSum interestSum = accountCloseMapper.findInterestSum(accountId);
        CloseAccount closeAccount = accountCloseMapper.findCloseAccount(accountId);

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
