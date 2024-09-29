package com.kcc.banking.domain.employee.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.businessday.service.BusinessDayService;
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

    public List<EmployeeDataOfList> getEmployeeListOfBranch() {
        return employeeMapper.findAllOfBranch(AuthenticationUtils.getLoginMemberId());
    }


    /**
     * @return
     * @Description 비밀번호 암호화 해야함
     */
    public CreatedEmployee createEmployee(EmployeeCreate employeeCreate) {

        employeeCreate.setPassword(passwordEncoder.encode(employeeCreate.getPassword()));

        employeeMapper.save(employeeCreate);
        return new CreatedEmployee(employeeCreate, "은평 1지점", "매니저");

    }


    public UpdatedEmployee updateEmployee(EmployeeUpdate employeeUpdate) {
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
        return employeeMapper.findAuthDataById(loginMemberId);
    }

    /**
     * @Description
     * 영업일 변경 모달 내에서 근무 인원 지정 시 이용
     * 영업일이 마감된 상태에서만 접근 가능
     * 현재 영업일의 현금 잔액이 전일자 현금 잔액으로 표기됨
     */
    public List<CashBalanceOfEmployee> getCashBalanceOfEmployees() {

        String businessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        String branchId = getAuthData().getBranchId();

        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(businessDate)
                .branchId(branchId)
                .build();

        return employeeMapper.findCashBalanceOfEmployees(businessDateAndBranchId);
    }

    public BigDecimal getCashBalanceOfBranch() {
        String businessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        String branchId = getAuthData().getBranchId();

        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(businessDate)
                .branchId(branchId)
                .build();

        return employeeMapper.findCashBalanceOfBranch(businessDateAndBranchId);
    }
}
