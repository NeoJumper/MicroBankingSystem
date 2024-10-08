package com.kcc.banking.domain.common.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.dto.request.RegistrantNameAndInfoAndDate;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final BusinessDayMapper businessDayMapper;
    private final EmployeeMapper employeeMapper;



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

    public RegistrantNameAndInfoAndDate getDateAndBranchIdAndEmpIdAndEmpName() {

        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        // 지점 번호
        String branchId = employeeMapper.findAuthDataById(loginMemberId).getBranchId();

        // 행원 이름
        String employeeName = employeeMapper.findAuthDataById(loginMemberId).getName();

        // 등록 일자
        String currentBusinessDate = businessDayMapper.findCurrentBusinessDay().getBusinessDate();

        return RegistrantNameAndInfoAndDate.builder()
                .employeeName(employeeName)
                .employeeId(loginMemberId)
                .tradeDate(currentBusinessDate)
                .branchId(branchId).build();
    }

}
