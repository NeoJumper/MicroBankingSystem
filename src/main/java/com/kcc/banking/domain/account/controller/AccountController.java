package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.common.dto.request.RegistrantNameAndInfoAndDate;
import com.kcc.banking.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/page/employee/account-open")
    public String accountCreate(Model model){

        RegistrantNameAndInfoAndDate registerInfo = accountService.getRegistarntInfo();

        model.addAttribute("branchId", registerInfo.getBranchId());
        model.addAttribute("employeeName", registerInfo.getEmployeeName());
        model.addAttribute("employeeId", registerInfo.getEmployeeId());
        model.addAttribute("tradeDate", registerInfo.getTradeDate());

        return "account/account-open";
    }

    @GetMapping("/page/employee/account-update")
    public String accountUpdate(){
        return "account/account-update";
    }
}
