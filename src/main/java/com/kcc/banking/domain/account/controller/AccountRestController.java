package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDetail>> getAllAccounts(Model model) {
        List<AccountDetail> accounts = accountService.getAccountList();
        return ResponseEntity.ok(accounts);
    }

}
