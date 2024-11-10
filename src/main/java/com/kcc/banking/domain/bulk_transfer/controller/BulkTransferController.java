package com.kcc.banking.domain.bulk_transfer.controller;

import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.service.BulkTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("bulkTransfer", bulkTransfer);

        return "trade/bulk-transfer-result";
    }
}
