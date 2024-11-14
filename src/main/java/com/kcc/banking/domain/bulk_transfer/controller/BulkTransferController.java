package com.kcc.banking.domain.bulk_transfer.controller;

import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.service.BulkTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class BulkTransferController {

    private final BulkTransferService bulkTransferService;

    @GetMapping("/page/employee/bulk-transfer")
    public String getBulkTransferPage() {
        return "trade/bulk-transfer";
    }

    @GetMapping("/page/employee/bulk-transfer-result")
    public String getBulkTransferResultPage(@RequestParam Long bulkTransferId, Model model) {

        BulkTransferDetail bulkTransfer = bulkTransferService.getBulkTransfer(bulkTransferId);



        String registrationDate = bulkTransfer.getRegistrationDate().toString(); // 예: "2024-11-13 12:15:48"

// 밀리초를 제거한 후 "yyyy-MM-dd HH:mm" 형식으로 수정
        String dateWithoutMillis = registrationDate.substring(0, 16); // "2024-11-13 12:15"

// "yyyy-MM-dd HH:mm" 형식으로 파싱
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateWithoutMillis, formatter);

// Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        bulkTransfer.setRegistrationDate(timestamp);

        model.addAttribute("bulkTransfer", bulkTransfer);

        return "trade/bulk-transfer-result";
    }
}
