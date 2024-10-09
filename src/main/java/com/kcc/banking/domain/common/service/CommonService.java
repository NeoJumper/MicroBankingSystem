package com.kcc.banking.domain.common.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.dto.response.AuthData;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /**
     * @Description
     * 1. 현재 거래일
     * 2. 현재 접속한 사용자 ID
     * 3. 현재 접속한 사용자 이름
     * 4. 현재 접속한 브랜치 ID
     * 5. 현재 접속한 브랜치 명
     */
    public CurrentData getCurrentData() {

        AuthData authData = employeeMapper.findAuthDataById(AuthenticationUtils.getLoginMemberId());

        // 등록 일자
        String currentBusinessDate = businessDayMapper.findCurrentBusinessDay().getBusinessDate();

        return CurrentData.builder()
                .employeeId(Long.valueOf(authData.getId()))
                .employeeName(authData.getName())
                .currentBusinessDate(currentBusinessDate)
                .branchId(Long.valueOf(authData.getBranchId()))
                .branchName(authData.getBranchName()).build();
    }
    public BusinessDay getCurrentBusinessDay(){
        return businessDayMapper.findCurrentBusinessDay();
    }
}
