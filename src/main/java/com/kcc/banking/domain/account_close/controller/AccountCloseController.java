package com.kcc.banking.domain.account_close.controller;

import com.kcc.banking.domain.account_close.service.AccountCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountCloseController {
    private final AccountCloseService accountCloseService;

    @GetMapping("/page/employee/account-close")
    public String accountClose() {
        return "account_close/account-close";
    }

    @GetMapping("/page/employee/account-close-cancel")
    public String accountCloseCancel(Model model, @RequestParam long accId) {
        model.addAttribute("accId", accId);
        System.out.println(accId+"================================");
        return "account_close/account-close-cancel";
    }
}
