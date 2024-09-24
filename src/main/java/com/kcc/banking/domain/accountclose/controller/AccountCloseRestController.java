package com.kcc.banking.domain.accountclose.controller;

import com.kcc.banking.domain.accountclose.dto.request.AccountStatus;
import com.kcc.banking.domain.accountclose.service.AccountCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountCloseRestController {

    private final AccountCloseService accountCloseService;

    // 해지 페이지에 필요한 Customer DETAIL 데이터
    @GetMapping("/api/employee/account-close-details/{accountNumber}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK).body("상태 업데이트 성공"+accountNumber);
    }

    // DemandDeposit테이블의  status컬럼 상태 변경 기능
    @PatchMapping("/api/employee/account/status")
    public ResponseEntity<AccountStatus> updateDemandDepositStatus(@RequestBody AccountStatus accountStatus) {
        AccountStatus result = accountCloseService.updateStatus(accountStatus);

        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
