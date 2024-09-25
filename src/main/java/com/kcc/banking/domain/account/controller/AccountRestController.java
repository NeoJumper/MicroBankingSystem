package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import com.kcc.banking.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 상품테이블 정보 및 기존이율 가져오기
    @GetMapping("/account/productInterest")
    public AccountProductInfo getProductInterest(){
        System.out.println("getAccountProductInfo"+accountService.getAccountProductInfo().getName());
        return accountService.getAccountProductInfo();
    }

    // 계좌 개설하기
    @PostMapping("/open")
    public String openAccount(@RequestBody AccountCreate accountCreate) {

        accountService.openAccount(accountCreate);

        return " " + accountCreate.getId();
    }



}
