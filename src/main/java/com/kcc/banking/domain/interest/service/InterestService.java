package com.kcc.banking.domain.interest.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.account_close.dto.request.PaymentStatus;
import com.kcc.banking.domain.account_close.dto.request.RollbackPaymentStatus;
import com.kcc.banking.domain.account_close.dto.response.InterestSum;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import com.kcc.banking.domain.interest.mapper.InterestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestMapper interestMapper;
    private final AccountService accountService;

    public void createInterest(String tradeNumber, BusinessDateAndBranchId businessDateAndBranchId){
        String branchId = businessDateAndBranchId.getBranchId();
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();


        List<AccountDetailForInterest> accountList = accountService.getAccountListByBranchId(Long.parseLong(branchId));
        List<InterestCreate> interestCreateList = accountList.stream().map(account -> InterestCreate.of(account, loginMemberId, businessDateAndBranchId, tradeNumber)).toList();

        interestCreateList.forEach(interestMapper::createInterest);
    }
    public InterestSum getInterestSum(String accountId){
        Optional<InterestSum> optInterestSum = Optional.ofNullable(interestMapper.findInterestSum(accountId));

        return optInterestSum.orElse(InterestSum.builder()
                .accountId(accountId)
                .amountSum(new BigDecimal("0")).build());
    }

    public int rollbackPaymentStatus(RollbackPaymentStatus rollbackPaymentStatus) {
        return interestMapper.rollbackPaymentStatus(rollbackPaymentStatus);
    }

    public int updatePaymentStatus(PaymentStatus paymentStatus) {
        return interestMapper.updatePaymentStatus(paymentStatus);
    }
}
