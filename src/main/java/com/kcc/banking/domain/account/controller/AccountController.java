package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final CommonService commonService;

    @GetMapping("/page/employee/savings-account-open")
    public String getSavingsAccountCreatePage(){
        return "account/savings-account-open";
    }

    @GetMapping("/page/employee/account-open")
    public String getAccountCreatePage(Model model){

        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("tradeDate", currentData.getCurrentBusinessDate());

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

    @GetMapping("/page/employee/savings-account-close")
    public String getSavingsAccountClosePage() {
        return "account/savings-account-close";
    }

    @GetMapping("/page/employee/transfer-limit-update")
    public String getTransferLimitUpdatePage() {
        return "account/transfer-limit-update";
    }

}
