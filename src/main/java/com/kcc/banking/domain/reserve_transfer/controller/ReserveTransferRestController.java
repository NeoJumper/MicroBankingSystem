package com.kcc.banking.domain.reserve_transfer.controller;

import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReserveTransferRestController {

    private final ReserveTransferService reserveTransferService;
}
