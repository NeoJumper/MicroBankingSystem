package com.kcc.banking.domain.employee.controller;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CommonService commonService;

    @GetMapping("/page/anonymous/login-form")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,  Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/auth/login-form";
    }

    @GetMapping("/page/manager/employee-save")
    public String employeeSavePage(Model model) {
        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("businessDate", currentData.getCurrentBusinessDate());
        model.addAttribute("branchName", currentData.getBranchName());
        return "employee/employee-save";
    }

    @GetMapping("/page/manager/employee-list")
    public String employeeListPage(Model model) {
        return "employee/employee-list";
    }
    @GetMapping("/page/manager/employee-update")
    public String employeeUpdatePage(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("id", id);
        return "employee/employee-update";
    }


}
