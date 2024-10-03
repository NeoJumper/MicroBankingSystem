package com.kcc.banking.domain.account_transfer.controller;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.domain.account_transfer.dto.request.TradeCancelRequest;
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

    // 취소 페이지에서, 거래번호를 통해 취소하려는 거래 내역 GET
    @GetMapping("/api/employee/account-transfer/cancel/{tradeNumber}")
    public ResponseEntity<List<TransferDetail>> transferCancel(@PathVariable(value = "tradeNumber", required = false) Long tradeNumber) {
        List<TransferDetail> tradeDetails = transferService.getTradeByTradeNumber(tradeNumber);
        System.out.println("TradeDetail::"+tradeDetails);
        return ResponseEntity.ok(tradeDetails);
    }

    // 취소 신청 -
    @PostMapping("/api/employee/account-transfer/cancel")
    public ResponseEntity<List<TransferDetail>> updateCancelTransferCAN(@RequestBody TradeCancelRequest tradeCancelRequest) {
        List<TransferDetail> tradeDetails = transferService.updateCancelTransferCAN(tradeCancelRequest);
        return ResponseEntity.ok(tradeDetails);
    }
}
