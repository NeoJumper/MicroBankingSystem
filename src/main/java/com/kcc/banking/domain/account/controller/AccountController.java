package com.kcc.banking.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/page/employee/account-open")
    public String accountCreate(){
        return "account/account-open";

    }
}
