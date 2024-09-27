package com.kcc.banking.domain.employee.service;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.*;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<EmployeeDataOfList> getEmployeeList() {
        return employeeMapper.findAll();
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
        List<EmployeeDataOfList> employees = employeeMapper.findBySearchOption(employeeSearch);
        return employees;
    }

    public EmployeeDetail getEmployeeDetail(Long id) {
        return employeeMapper.findById(id);
    }

    public AuthData getAuthData() {
        long loginMemberId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        return employeeMapper.findAuthDataById(loginMemberId);
    }
}
