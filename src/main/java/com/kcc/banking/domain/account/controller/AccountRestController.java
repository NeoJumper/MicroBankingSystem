package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountOpenResultOfModal;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import com.kcc.banking.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/account-validate")
    public ResponseEntity<Void> validatePassword(@ModelAttribute PasswordValidation passwordValidation) {
        accountService.validatePassword(passwordValidation);

        return ResponseEntity.ok().build();
    }

    /**
     * @Description
     * 계좌 이체, 현금 입출금, 해지 거래 등 계좌를 찾는검색하는 모달에서 사용
     */
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountOfModal>> getAccountsBySearchOption(@ModelAttribute SearchAccountOfModal searchAccountOfModal) {
        List<AccountOfModal> accounts = accountService.getAccountsBySearchOption(searchAccountOfModal);
        return ResponseEntity.ok(accounts);
    }

    // 상품테이블 정보 및 기존이율 가져오기
    @GetMapping("/account/productInterest")
    public AccountProductInfo getProductInterest() {
        System.out.println("getAccountProductInfo" + accountService.getAccountProductInfo().getName());
        return accountService.getAccountProductInfo();
    }

    // 계좌 개설하기
    @Transactional
    @PostMapping("/account/open")
    public ResponseEntity<String> openAccount(@RequestBody AccountCreate accountCreate) {

        System.out.println("accountCreate.getPreferentialInterestRate();" + accountCreate.getPreferentialInterestRate());
        accountService.openAccount(accountCreate);
        System.out.println(accountCreate.getId() + "accountCreate>>>>>>>>>>>>> getId();");
        return ResponseEntity.ok(accountCreate.getId());

    }
    //AccountOpenResultOfModal

    // 계좌 정보 조회 API
    @GetMapping("/account/open/{accountId}")
    public ResponseEntity<AccountOpenResultOfModal> getAccountInfo(@PathVariable String accountId) {
        AccountOpenResultOfModal accountInfo = accountService.getAccountOpenResultOfModal(accountId);
        if (accountInfo != null) {
            return ResponseEntity.ok(accountInfo);  // 조회된 계좌 정보를 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 계좌 정보가 없는 경우 404 처리
        }
    }

}

