package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/api/employees")
    public List<EmployeeDetail> getAllEmployees() {
        return employeeService.getEmployeeList();
    }

    // 해지 페이지에 필요한 Customer DETAIL 데이터
    @GetMapping("/api/employee/account-close-details/{accountNumber}")
    public void getEmployeeDetail(@PathVariable("accountNumber") String accountNumber) {}

    // DemandDeposit테이블의  status컬럼 상태 변경 기능
    @PatchMapping("/api/employee/account/status")
    public void updateDemandDepositStatus(@RequestBody EmployeeDetail employeeDetail) {}
}
