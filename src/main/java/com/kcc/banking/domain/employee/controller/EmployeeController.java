package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

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
}
