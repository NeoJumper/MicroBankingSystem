package com.kcc.banking.domain.account_transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferController {

    @GetMapping("/page/employee/account-transfer")
    public String employeeAccountTransfer() {
        return "transfer/account-transfer";
    }

    @GetMapping("/page/employee/account-transfer-cancel")
    public String employeeAccountTransferCancel(@RequestParam(value="tradeNumber", required = false) long tradeNumber, Model model) {
        model.addAttribute("tradeNumber", tradeNumber);
        return "transfer/account-transfer-cancel";
    }

}
