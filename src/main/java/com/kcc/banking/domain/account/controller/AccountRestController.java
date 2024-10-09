package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.request.PasswordValidation;
import com.kcc.banking.domain.account.dto.request.SearchAccountOfModal;
import com.kcc.banking.domain.account.dto.request.AccountCreate;
import com.kcc.banking.domain.account.dto.response.AccountDetail;
import com.kcc.banking.domain.account.dto.response.AccountOfModal;
import com.kcc.banking.domain.account.dto.response.AccountOpenResultOfModal;
import com.kcc.banking.domain.account.dto.response.AccountProductInfo;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.account_close.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.account_close.dto.request.StatusWithTrade;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.service.AccountCloseService;
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
public class AccountRestController {

    private final AccountService accountService;
    private final AccountCloseService accountCloseService;

    @PostMapping("/api/employee/account-validate")
    public ResponseEntity<Void> validatePassword(@ModelAttribute PasswordValidation passwordValidation) {
        accountService.validatePassword(passwordValidation);

        return ResponseEntity.ok().build();
    }

    /**
     * @Description
     * 계좌 이체, 현금 입출금, 해지 거래 등 계좌를 찾는검색하는 모달에서 사용
     */
    @GetMapping("/api/employee/accounts")
    public ResponseEntity<List<AccountOfModal>> getAccountsBySearchOption(@ModelAttribute SearchAccountOfModal searchAccountOfModal) {
        List<AccountOfModal> accounts = accountService.getAccountsBySearchOption(searchAccountOfModal);
        return ResponseEntity.ok(accounts);
    }

    /**
     * @Description
     * - 상품테이블 정보 및 기존이율 가져오기
     */

    @GetMapping("/api/employee/account/product-interest")
    public AccountProductInfo getProductInterest() {
        System.out.println("getAccountProductInfo" + accountService.getAccountProductInfo().getName());
        return accountService.getAccountProductInfo();
    }
    /**
     * @Description
     * - 계좌 개설
     */
    @Transactional
    @PostMapping("/api/employee/account/open")
    public ResponseEntity<String> openAccount(@RequestBody AccountCreate accountCreate) {

        System.out.println("accountCreate.getPreferentialInterestRate();" + accountCreate.getPreferentialInterestRate());
        accountService.openAccount(accountCreate);
        System.out.println(accountCreate.getId() + "accountCreate>>>>>>>>>>>>> getId();");
        return ResponseEntity.ok(accountCreate.getId());

    }
    /**
     * @Description
     * - 계좌 개설 완료 시 개설된 계좌의 상세정보 조회
     */
    // 계좌 정보 조회 API
    @GetMapping("/api/employee/account/open/{accountId}")
    public ResponseEntity<AccountOpenResultOfModal> getAccountInfo(@PathVariable String accountId) {
        AccountOpenResultOfModal accountInfo = accountService.getAccountOpenResultOfModal(accountId);
        if (accountInfo != null) {
            return ResponseEntity.ok(accountInfo);  // 조회된 계좌 정보를 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 계좌 정보가 없는 경우 404 처리
        }
    }

    // 해지 페이지에 필요한 Customer DETAIL 데이터
    @GetMapping("/api/employee/account-close-details/{accountId}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable("accountId") String accountId) {
        CloseAccountTotal cat = accountCloseService.findCloseAccountTotal(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(cat);
    }

    // DemandDeposit테이블의  status컬럼 상태 변경 기능
//    @PatchMapping("/api/employee/account/status")
//    public ResponseEntity<?> updateDemandDepositStatus(@RequestBody AccountStatus accountStatus) {
//        AccountStatus result = accountCloseService.updateStatus(accountStatus);
//
//        if(result == null){
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @PostMapping("/api/employee/close-trade")
    public ResponseEntity<?> addCloseTrade(@RequestBody StatusWithTrade statusWithTrade) {
        String result = accountCloseService.addCloseTrade(statusWithTrade);

        if(result.equals("FAIL")) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("계좌해지 거래 실패");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/api/employee/close-cancel-trade")
    public void cancelCloseTrade(@RequestBody AccountIdWithExpireDate accountIdWithExpireDate) {

        accountCloseService.rollbackAccountCancel(accountIdWithExpireDate.getAccountId());
    }
}

