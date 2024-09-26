package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.response.CreatedEmployee;
import com.kcc.banking.domain.employee.dto.response.EmployeeDataOfList;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/api/manager/employee")
    public CreatedEmployee createEmployee(@RequestBody EmployeeCreate employeeCreate) {
        return employeeService.createEmployee(employeeCreate);

    }

    @GetMapping("/api/manager/employee")
    public List<EmployeeDataOfList> getEmployeeList(@ModelAttribute EmployeeSearch employeeSearch) {
        return employeeService.getEmployeeListByOption(employeeSearch);

    }
}
