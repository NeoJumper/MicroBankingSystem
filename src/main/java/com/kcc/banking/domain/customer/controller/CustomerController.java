package com.kcc.banking.domain.customer.controller;


import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.customer.dto.request.CustomerSearch;
import com.kcc.banking.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CommonService commonService;

    @GetMapping("/page/common/customer-save")
    public String customerSavePage(Model model) {
        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("businessDate", currentData.getCurrentBusinessDate());
        model.addAttribute("branchName", currentData.getBranchName());
        return "customer/customer-save";
    }

    @GetMapping("/page/common/customer-list")
    public String customerListPage() {
        return "customer/customer-list";
    }

    @GetMapping("/page/common/customer-update")
    public String customerUpdatePage(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("id", id);

        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("businessDate", currentData.getCurrentBusinessDate());
        model.addAttribute("branchName", currentData.getBranchName());
        return "customer/customer-update";
    }

}
