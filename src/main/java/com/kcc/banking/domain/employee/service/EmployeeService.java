package com.kcc.banking.domain.employee.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.NotFoundException;
import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.*;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BusinessDayService businessDayService;
    private final CommonService commonService;

    public List<EmployeeDataOfList> getEmployeeListOfBranch() {
        return employeeMapper.findAllOfBranch(AuthenticationUtils.getLoginMemberId());
    }


    /**
     * @return
     * @Description 비밀번호 암호화 해야함
     */
    public CreatedEmployee createEmployee(EmployeeCreate employeeCreate) {

        employeeCreate.setPassword(passwordEncoder.encode(employeeCreate.getPassword()));
        employeeCreate.setCommonColumn(commonService.getCurrentBusinessDateAndEmployeeId());


        employeeMapper.save(employeeCreate);
        return new CreatedEmployee(employeeCreate, "은평 1지점", "매니저");

    }


    public UpdatedEmployee updateEmployee(EmployeeUpdate employeeUpdate) {
        employeeUpdate.setCommonColumn(commonService.getCurrentBusinessDateAndEmployeeId());
        employeeMapper.update(employeeUpdate);
        return new UpdatedEmployee(employeeUpdate, "은평 1지점", "매니저");

    }

    public List<EmployeeDataOfList> getEmployeeListByOption(EmployeeSearch employeeSearch) {
        employeeSearch.setBranchId(AuthenticationUtils.getLoginMemberId());
        List<EmployeeDataOfList> employees = employeeMapper.findAllOfBranchBySearchOption(employeeSearch);
        return employees;
    }

    public EmployeeDetail getEmployeeDetail(Long id) {
        return employeeMapper.findById(id);
    }

    public AuthData getAuthData() {
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        if (loginMemberId == null) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_MEMBER);
        }

        return employeeMapper.findAuthDataById(loginMemberId);
    }
}
