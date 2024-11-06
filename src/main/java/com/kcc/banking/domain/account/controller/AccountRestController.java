package com.kcc.banking.domain.account.controller;

import com.kcc.banking.domain.account.dto.request.*;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.account.service.AccountCloseFacade;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.service.AccountTradeFacade;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountRestController {

    private final CommonService commonService;
    private final AccountService accountService;
    private final TradeService tradeService;

    private final AccountTradeFacade accountTradeFacade;
    private final AccountCloseFacade accountCloseFacade;

    @PostMapping("/api/employee/account-validate")
    public ResponseEntity<Void> validatePassword(@ModelAttribute PasswordValidation passwordValidation) {
        accountService.validatePassword(passwordValidation);

        return ResponseEntity.ok().build();
    }

    /**
     * @Description
     * 계좌 이체, 현금 입출금, 해지 거래 등 계좌를 검색하는 모달에서 사용
     */
    @GetMapping("/api/employee/accounts")
    public ResponseEntity<List<AccountOfModal>> getAccountsBySearchOption(@ModelAttribute SearchAccountOfModal searchAccountOfModal) {
        List<AccountOfModal> accounts = accountService.getAccountsBySearchOption(searchAccountOfModal);
        return ResponseEntity.ok(accounts);
    }

    /**
     * @Description
     * - 보통예금 계좌 개설
     */
    @Transactional
    @PostMapping("/api/employee/accounts")
    public ResponseEntity<String> openAccount(@RequestBody AccountOpen accountOpen) {

        String accId = accountTradeFacade.openAccount(accountOpen);
        return ResponseEntity.ok(accId);

    }


    /**
     * @Description
     * - 보통예금 계좌 개설 완료 시 개설된 계좌의 상세정보 조회
     * - 보통예금 계좌 정보 조회 API
     */
    @GetMapping("/api/employee/accounts/{accountId}")
    public ResponseEntity<AccountOpenResultOfModal> getAccountInfo(@PathVariable String accountId) {
        AccountOpenResultOfModal accountInfo = accountService.getAccountOpenResultOfModal(accountId);
        BigDecimal transferAmountOfToday = tradeService.getTransferAmountOfToday(TradeSearch.builder()
                .tradeDate(commonService.getCurrentData().getCurrentBusinessDate())
                .accId(accountId)
                .build()
        );
        accountInfo.setTransferAmountOfToday(transferAmountOfToday);


        return ResponseEntity.ok().body(accountInfo);
    }

    /**
     * @Description
     * - 보통예금 이체한도 변경 시 이용
     */
    @PatchMapping("/api/employee/accounts/{accountId}/transfer-limit")
    public ResponseEntity<Void> partialUpdateAccount(@PathVariable String accountId, @RequestBody AccountUpdate accountUpdate) {
        accountService.updateByTransferLimitUpdate(accountUpdate);

        return ResponseEntity.ok().body(null);
    }

    /**
     * @Discription
     * - 보통예금 해지 페이지에 필요한 Customer DETAIL 데이터
     * @param accountId
     * @return
     */
    @GetMapping("/api/employee/account-close-details/{accountId}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable("accountId") String accountId) {
        AccountCloseResult cat = accountTradeFacade.findCloseAccountTotal(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(cat);
    }


    // 한 고객의 개설 상태의 입출금 통장 조회
    @GetMapping("/api/employee/transfer-accounts/{customerId}")
    public ResponseEntity<List<AccountOfModal>> getAllAccountOfOneCustomer(@PathVariable String customerId){
        System.out.println("getAllAccountOfOneCustomer >>>>> customerId" + customerId);
        List<AccountOfModal> accountsOfPerPerson = accountService.getAllAccountOfOneCustomer(customerId);
        return ResponseEntity.ok(accountsOfPerPerson);
    }

    // 예금/적금별 상품 전체 조회
    @GetMapping("/api/employee/products")
    public ResponseEntity<List<ProductOfModal>> getAllProductByAccountType(@ModelAttribute SearchProductOfModal searchProductOfModal){
        System.out.println("예금/ 적금별 상품 전체 조회 getAllProductByAccountType >>>>>>>>>>");
        List<ProductOfModal> allProductList = accountService.getAllProductList(searchProductOfModal);
        return ResponseEntity.ok(allProductList);
    }



    // 적금 계좌 개설
//    @Transactional
//    @PostMapping("/api/employee/savings-account/open")
//    public ResponseEntity<String> savingsOpenAccount(@RequestBody AccountOpen accountOpen) {
//
//        String accId = accountTradeFacade.openSavingsAccount(accountOpen);
//        return ResponseEntity.ok(accId);
//
//    }

    // 정기적금 계좌 정보 조회 API
//    @GetMapping("/api/employee/savings-account/open/{accountId}")
//    public ResponseEntity<SavingAccountOpenResultOfModal> getAccountInfo(@PathVariable String accountId) {
//        AccountOpenResultOfModal accountInfo = accountService.getAccountOpenResultOfModal(accountId);
//        if (accountInfo != null) {
//            return ResponseEntity.ok(accountInfo);  // 조회된 계좌 정보를 반환
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 계좌 정보가 없는 경우 404 처리
//        }
//    }


//    // 적금 해지
//    @GetMapping("/api/employee/savings-account-close-details/{accountId}")
//    public ResponseEntity<?> getSavingsAccountInfo(@PathVariable("accountId") String accountId) {
//        CloseSavingsAccountTotal savingsInfo = accountTradeFacade.findCloseSavingAccountTotal(accountId);
//        return ResponseEntity.status(HttpStatus.OK).body(savingsInfo);
//    }


    /**
     * @Discription
     * - 정기적금 해지를 위한 계좌 상세정보
     * - 자동이체 정보 불러오기
     * - 001-0000013-3687 번에 거래내역 존재
     * @param accountId
     * @return ResponseEntity<CloseSavingsAccountTotal>
     */
    @GetMapping("/api/employee/savings-account-close-total-info/{accountId}")
    public ResponseEntity<CloseSavingsAccountTotal> getCloseAccountInfo(@PathVariable("accountId") String accountId){
        CloseSavingsAccountTotal closeSavingsAccountInfo = accountService.getCloseSavingsAccount(accountId);
        return ResponseEntity.ok(closeSavingsAccountInfo);
    }

    /**
     * @Discription 
     * - 자유적금 해지를 위한 계좌 상세정보
     * @param accountId
     * @return ResponseEntity<CloseSavingsFlexibleAccountTotal>
     */
    @GetMapping("/api/employee/flexible-savings-account/{accountId}")
    public ResponseEntity<CloseSavingsFlexibleAccountTotal> getFlexibleCloseAccountInfo(@PathVariable("accountId") String accountId){
        CloseSavingsFlexibleAccountTotal closeSavingsFlexibleAccountTotal = accountCloseFacade.getFlexibleSavingsAccount(accountId);
        return ResponseEntity.ok(closeSavingsFlexibleAccountTotal);
    }

    /**
     * @Discription 
     * - 자유적금 해지 요청
     * @return
     */
    @PostMapping("/api/employee/flexible-savings-account-close")
    public ResponseEntity<?> closeFlexibleSavingsAccount(@RequestBody FlexibleSavingsAccountClose accountClose){
        AccountCloseResult accountCloseResult = accountCloseFacade.closeFlexibleSavingsAccount(accountClose);
        return ResponseEntity.ok(accountCloseResult);
    }

}

