package com.kcc.banking.domain.account_transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {

    @GetMapping("/page/employee/account-transfer")
    public String employeeAccountTransfer() {
        return "transfer/account-transfer";
    }
}
