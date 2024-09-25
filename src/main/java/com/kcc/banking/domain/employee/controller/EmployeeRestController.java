package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.response.CreatedEmployee;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/api/manager/employee")
    public CreatedEmployee createEmployee(@RequestBody EmployeeCreate employeeCreate) {
        return employeeService.createEmployee(employeeCreate);

    }
}
