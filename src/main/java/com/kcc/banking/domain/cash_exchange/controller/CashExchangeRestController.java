package com.kcc.banking.domain.cash_exchange.controller;

import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.cash_exchange.dto.request.ManagerCashBalanceRequest;
import com.kcc.banking.domain.cash_exchange.dto.response.EmployeeDataResponse;
import com.kcc.banking.domain.cash_exchange.service.CashExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CashExchangeRestController {

    private final CashExchangeService cashExchangeService;

    @PatchMapping("/api/manager/cash-exchange-close")
    public void cashExchangeClose(@RequestBody ManagerCashBalanceRequest managerCashBalance){
        cashExchangeService.closeCashExchange(managerCashBalance);
    }

    @GetMapping("/api/manager/cash-exchange")
    public ClosingData cashExchange(@RequestParam Long employeeId){
        return cashExchangeService.getEmployeeData(employeeId);
    }
}
