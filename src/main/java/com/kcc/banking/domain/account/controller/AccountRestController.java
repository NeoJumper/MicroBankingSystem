package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountOpenResultOfModal;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import com.kcc.banking.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDetail>> getAllAccounts(Model model) {
        List<AccountDetail> accounts = accountService.getAccountList();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountOfModal>> getAccountById(@ModelAttribute SearchAccountOfModal searchAccountOfModal) {
        List<AccountOfModal> accounts = accountService.getAccount(searchAccountOfModal);
        return ResponseEntity.ok(accounts);
    }

    // 상품테이블 정보 및 기존이율 가져오기
    @GetMapping("/account/productInterest")
    public AccountProductInfo getProductInterest(){
        System.out.println("getAccountProductInfo"+accountService.getAccountProductInfo().getName());
        return accountService.getAccountProductInfo();
    }

    // 계좌 개설하기
    @PostMapping("/account/open")
    public void openAccount(@RequestBody AccountCreate accountCreate) {

        System.out.println("accountCreate.getPreferentialInterestRate();"+accountCreate.getPreferentialInterestRate());
        accountService.openAccount(accountCreate);
    }

    // 계좌 개설 완료 정보 함수
//    @GetMapping("/account/open/result")
//    public AccountOpenResultOfModal getAccountOpenResult(){
//        System.out.println();
//        return accountService;
//    }


}
