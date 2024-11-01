package com.kcc.banking.domain.reserve_transfer.controller;


import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReserveTransferController {

    private final ReserveTransferService reserveTransferService;

    @GetMapping("/page/employee/reserve-list")
    public String getReserveListPage() {
        return "trade/reserve-list";
    }
}
