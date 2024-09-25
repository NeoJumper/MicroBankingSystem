package com.kcc.banking.domain.accountclose.controller;

import com.kcc.banking.domain.accountclose.service.AccountCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountCloseController {
    private final AccountCloseService accountCloseService;

    @GetMapping("/page/employee/account-close")
    public String accountClose() {
        return "/account-close";
    }

    @GetMapping("/page/employee/account-close/cancel")
    public String accountCloseCancel() {
        return "/account-close-cancel";
    }
}
