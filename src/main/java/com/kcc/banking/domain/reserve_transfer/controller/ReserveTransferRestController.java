package com.kcc.banking.domain.reserve_transfer.controller;

import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReserveTransferRestController {

    private final ReserveTransferService reserveTransferService;

    @PostMapping("/api/employee/reserve-transfer")
    public void createReserveTransfer(@RequestBody ReserveTransferCreate reserveTransferCreate){
        reserveTransferService.createReserveTransfer(reserveTransferCreate);
    }
}
