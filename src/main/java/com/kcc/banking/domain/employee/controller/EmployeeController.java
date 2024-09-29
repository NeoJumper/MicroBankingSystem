package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/page/auth/login-form")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,  Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "auth/login-form";
    }

    @GetMapping("/page/manager/employee-save")
    public String employeeSavePage(Model model) {

        return "employee/employee-save";
    }

    @GetMapping("/page/manager/employee-list")
    public String employeeListPage(Model model) {
        model.addAttribute("employeeList",employeeService.getEmployeeListOfBranch());
        return "employee/employee-list";
    }
    @GetMapping("/page/manager/employee-update")
    public String employeeUpdatePage(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("id", id);
        return "employee/employee-update";
    }
    @GetMapping("/page/employee/deadline-management")
    public String employeeDeadlineManagement(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("id", id);
        return "deadline-management";
    }
    @GetMapping("/page/manager/deadline-management")
    public String managerDeadlineManagement(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("id", id);
        return "deadline-management2";
    }


}
