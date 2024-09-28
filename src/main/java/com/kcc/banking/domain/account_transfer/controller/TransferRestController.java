package com.kcc.banking.domain.account_transfer.controller;

import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransferRestController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferCreate transferCreate) {
        try {
            // 트랜잭션 처리
            transferService.processTransfer(transferCreate);
            return ResponseEntity.ok("Transfer successful");

        } catch (Exception e) {
            // 예외가 발생했을 때 예외 처리 (예: 로그 남기기, 사용자에게 메시지 반환)
            System.err.println("Transfer failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Transfer failed: " + e.getMessage());
        }
    }
}
