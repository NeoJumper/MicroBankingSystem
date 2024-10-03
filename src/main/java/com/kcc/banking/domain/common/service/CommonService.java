package com.kcc.banking.domain.common.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.mapper.CommonMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final CommonMapper commonMapper;
    private final BusinessDayMapper businessDayMapper;
    private final EmployeeMapper employeeMapper;


    public String getBranchClosingStatus(){
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        String currentBusinessDate = businessDayMapper.findCurrentBusinessDay().getBusinessDate();
        String branchId = employeeMapper.findAuthDataById(loginMemberId).getBranchId();


        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(currentBusinessDate)
                .branchId(branchId)
                .build();

        return commonMapper.findClosingStatus(businessDateAndBranchId);
    }
    public BusinessDateAndEmployeeId getCurrentBusinessDateAndEmployeeId(){
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();
        String currentBusinessDate = businessDayMapper.findCurrentBusinessDay().getBusinessDate();

        return BusinessDateAndEmployeeId.builder()
                .businessDate(currentBusinessDate)
                .employeeId(loginMemberId).build();
    }
    public BusinessDateAndBranchId getCurrentBusinessDateAndBranchId(){
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();
        String currentBusinessDate = businessDayMapper.findCurrentBusinessDay().getBusinessDate();
        String branchId = employeeMapper.findAuthDataById(loginMemberId).getBranchId();


        return BusinessDateAndBranchId.builder()
                .businessDate(currentBusinessDate)
                .branchId(branchId).build();
    }

}
