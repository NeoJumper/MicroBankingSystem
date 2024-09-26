package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.dto.request.EmployeeCreate;
import com.kcc.banking.domain.employee.dto.request.EmployeeSearch;
import com.kcc.banking.domain.employee.dto.request.EmployeeUpdate;
import com.kcc.banking.domain.employee.dto.response.CreatedEmployee;
import com.kcc.banking.domain.employee.dto.response.EmployeeDataOfList;
import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import com.kcc.banking.domain.employee.dto.response.UpdatedEmployee;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/api/manager/employee/{id}")
    public EmployeeDetail getEmployeeDetail(@PathVariable(value = "id", required = false) Long id) {
        return employeeService.getEmployeeDetail(id);
    }

    @PostMapping("/api/manager/employee")
    public CreatedEmployee createEmployee(@RequestBody EmployeeCreate employeeCreate) {
        return employeeService.createEmployee(employeeCreate);

    }
    @PutMapping("/api/manager/employee")
    public UpdatedEmployee updateEmployee(@RequestBody EmployeeUpdate employeeUpdate) {
        return employeeService.updateEmployee(employeeUpdate);

    }

    @GetMapping("/api/manager/employee")
    public List<EmployeeDataOfList> getEmployeeList(@ModelAttribute EmployeeSearch employeeSearch) {
        return employeeService.getEmployeeListByOption(employeeSearch);

    }
}
