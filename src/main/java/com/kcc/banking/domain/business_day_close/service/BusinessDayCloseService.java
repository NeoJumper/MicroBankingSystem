package com.kcc.banking.domain.business_day_close.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.EmployeeClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessDayCloseService {

    private final BusinessDayCloseMapper businessDayCloseMapper;
    private final BusinessDayService businessDayService;
    private final EmployeeService employeeService;

    public EmployeeClosingData getClosingData() {

        String currentBusinessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        BusinessDateAndEmployeeId businessDateAndEmployeeId = BusinessDateAndEmployeeId.builder()
                .businessDate(currentBusinessDate)
                .employeeId(loginMemberId)
                .build();


        ClosingData closingData = businessDayCloseMapper.findClosingData(businessDateAndEmployeeId);

        EmployeeClosingData employeeClosingData = EmployeeClosingData.builder()
                .closingData(closingData)
                .build();

        return employeeClosingData;
    }

    public ManagerClosingData getClosingDataList() {

        String currentBusinessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        String branchId = employeeService.getAuthData().getBranchId();

        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(currentBusinessDate)
                .branchId(branchId)
                .build();

        List<ClosingData> closingDataList = businessDayCloseMapper.findClosingDataList(businessDateAndBranchId);
        return ManagerClosingData.of(closingDataList);

    }
}
