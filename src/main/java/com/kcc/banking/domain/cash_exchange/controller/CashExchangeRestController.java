package com.kcc.banking.domain.cash_exchange.controller;

import com.kcc.banking.domain.cash_exchange.dto.request.ManagerCashBalanceRequest;
import com.kcc.banking.domain.cash_exchange.service.CashExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CashExchangeRestController {

    private final CashExchangeService cashExchangeService;

    @PatchMapping("/api/manager/cash-exchange-close")
    public void cashExchangeClose(@RequestBody ManagerCashBalanceRequest managerCashBalance){
        cashExchangeService.closeCashExchange(managerCashBalance);
    }
}
