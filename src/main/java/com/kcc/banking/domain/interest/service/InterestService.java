package com.kcc.banking.domain.interest.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.interest.dto.request.PaymentStatusRollback;
import com.kcc.banking.domain.interest.dto.request.PaymentStatusUpdate;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
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


    /**
     *  @Description
     *  세전 이자액 합계(해지 시 사용)
     */
    public InterestSum getInterestSum(String accountId){
        Optional<InterestSum> optInterestSum = Optional.ofNullable(interestMapper.findInterestSum(accountId));

        return optInterestSum.orElse(InterestSum.builder()
                .accountId(accountId)
                .amountSum(new BigDecimal("0")).build());
    }

    /**
     *  @Description
     *  세전 이자액 합계(해지 취소 시 사용)
     */
    public InterestSum getPreTaxInterestSum(AccountIdWithExpireDate awe) {
        return interestMapper.findPreTaxInterestSum(awe);
    }

    /**
     *  @Description
     *  해지에 의한 이자 테이블 상태 및 지급일 변경
     */
    public int updateByClose(StatusWithTrade statusWithTrade, CurrentData currentData) {
        PaymentStatusUpdate paymentStatusUpdate = PaymentStatusUpdate.builder()
                .branchId(currentData.getBranchId())
                .payDate(currentData.getCurrentBusinessDate())
                .modifierId(currentData.getEmployeeId())
                .accId(statusWithTrade.getAccId())
                .build();

        return interestMapper.updateByClose(paymentStatusUpdate);
    }

    /**
     *  @Description
     *  해지 취소에 의한 이자 테이블 상태 및 지급일 변경
     */
    public int updateByCloseCancel(String accId, CurrentData currentData, String expireDate) {

        PaymentStatusRollback paymentStatusRollback = PaymentStatusRollback.builder()
                .branchId(currentData.getBranchId())
                .modifierId(currentData.getEmployeeId())
                .accId(accId)
                .expireDate(expireDate).build();

        return interestMapper.updateByCloseCancel(paymentStatusRollback);
    }

    public void deleteInterest(CurrentData currentData, String prevBusinessDate) {
        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .branchId(String.valueOf(currentData.getBranchId()))
                .businessDate(prevBusinessDate).build();

        interestMapper.deleteInterest(businessDateAndBranchId);
    }

}
