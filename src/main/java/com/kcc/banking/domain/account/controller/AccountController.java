package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/page/employee/savings-account-open")
    public String getSavingsAccountCreatePage(){
        return "account/savings-account-open";
    }

    @GetMapping("/page/employee/account-open")
    public String getAccountCreatePage(Model model){

        CurrentData registerInfo = accountService.getRegistrantInfo();

        model.addAttribute("branchId", registerInfo.getBranchId());
        model.addAttribute("employeeName", registerInfo.getEmployeeName());
        model.addAttribute("employeeId", registerInfo.getEmployeeId());
        model.addAttribute("tradeDate", registerInfo.getCurrentBusinessDate());

        return "account/account-open";
    }

    @GetMapping("/page/employee/account-update")
    public String getAccountUpdatePage(){
        return "account/account-update";
    }

    @GetMapping("/page/employee/account-close")
    public String getAccountClosePage() {
        return "account/account-close";
    }

    @GetMapping("/page/employee/account-close-cancel")
    public String getAccountCloseCancelPage() {

        return "account/account-close-cancel";
    }
}
