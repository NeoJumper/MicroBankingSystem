package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/page/employee/account-close")
    public String accountClose() {
        return "/account-close";
    }

    @GetMapping("/page/employee/account-close/cancel")
    public String accountCloseCancel() {
        return "/account-close-cancel";
    }
}
