package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/page/employee/login-form")
    public String loginPage(Model model) {

        return "auth/login-form";
    }

    @GetMapping("/page/manager/employee-save")
    public String employeeSavePage(Model model) {

        return "employee-save";
    }
    @GetMapping("/page/manager/employee-list")
    public String employeeListPage(Model model) {

        return "employee-list";
    }
    @GetMapping("/page/manager/employee-list/{id}")
    public String employeeDetailPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("id", id);
        return "employee-detail";
    }
    @GetMapping("/page/manager/employee-update")
    public String employeeUpdatePage(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("id", id);
        return "employee-update";
    }
    @GetMapping("/page/employee/deadline-management")
    public String employeeDeadlineManagement(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("id", id);
        return "deadline-management";
    }


}
