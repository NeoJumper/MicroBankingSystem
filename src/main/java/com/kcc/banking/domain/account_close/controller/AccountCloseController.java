package com.kcc.banking.domain.account_close.controller;

import com.kcc.banking.domain.account_close.service.AccountCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AccountCloseController {
    private final AccountCloseService accountCloseService;

    @GetMapping("/page/employee/account-close")
    public String accountClose() {
        return "account_close/account-close";
    }

    @GetMapping("/page/employee/account-close/cancel")
    public String accountCloseCancel() {
        return "account_close/account-close-cancel";
    }
}
