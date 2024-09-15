package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/api/employees")
    public List<EmployeeDetail> getAllEmployees() {
        return employeeService.getEmployeeList();
    }
}
