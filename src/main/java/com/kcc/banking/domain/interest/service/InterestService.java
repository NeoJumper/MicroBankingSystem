package com.kcc.banking.domain.interest.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.service.EmployeeService;
import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import com.kcc.banking.domain.interest.mapper.InterestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
