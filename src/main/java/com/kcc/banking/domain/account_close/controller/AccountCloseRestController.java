package com.kcc.banking.domain.account_close.controller;

import com.kcc.banking.domain.account_close.dto.request.AccountStatus;
import com.kcc.banking.domain.account_close.dto.request.CloseTrade;
import com.kcc.banking.domain.account_close.dto.response.CloseAccountTotal;
import com.kcc.banking.domain.account_close.service.AccountCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountCloseRestController {

    private final AccountCloseService accountCloseService;

    // 해지 페이지에 필요한 Customer DETAIL 데이터
    @GetMapping("/api/employee/account-close-details/{accountId}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable("accountId") String accountId) {
        CloseAccountTotal cat = accountCloseService.findCloseAccountTotal(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(cat);
    }

    // DemandDeposit테이블의  status컬럼 상태 변경 기능
    @PatchMapping("/api/employee/account/status")
    public ResponseEntity<?> updateDemandDepositStatus(@RequestBody AccountStatus accountStatus) {
        AccountStatus result = accountCloseService.updateStatus(accountStatus);

        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/api/employee/close-trade")
    public ResponseEntity<?> addCloseTrade(@RequestBody CloseTrade closeTrade) {
        CloseTrade result = accountCloseService.addCloseTrade(closeTrade);

        if(result == null){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("계좌해지 거래 실패");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
