package com.kcc.banking.domain.account_transfer.controller;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import com.kcc.banking.domain.account_transfer.service.TransferService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransferRestController {

    private final TransferService transferService;

    @PostMapping("/api/employee/account-transfer")
    public ResponseEntity<List<TransferDetail>> transfer(@RequestBody TransferCreate transferCreate) {
            List<TransferDetail> tradeDetails = transferService.processTransfer(transferCreate);
            return ResponseEntity.ok(tradeDetails);
    }

    @GetMapping("/api/employee/account-transfer/cancel/{tradeNumber}")
    public ResponseEntity<List<TransferDetail>> transferCancel(@PathVariable(value = "tradeNumber", required = false) Long tradeNumber) {
        List<TransferDetail> tradeDetails = transferService.getTradeByTradeNumber(tradeNumber);
        System.out.println("TradeDetail::"+tradeDetails);
        return ResponseEntity.ok(tradeDetails);
    }

}
