package com.kcc.banking.domain.cash_exchange.controller;

import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.cash_exchange.dto.request.CashExchangeCreate;
import com.kcc.banking.domain.cash_exchange.dto.request.ManagerCashBalance;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeResultData;
import com.kcc.banking.domain.cash_exchange.service.CashExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CashExchangeRestController {

    private final CashExchangeService cashExchangeService;

    @PatchMapping("/api/manager/cash-exchange-close")
    public void cashExchangeClose(@RequestBody ManagerCashBalance managerCashBalance){
        cashExchangeService.closeCashExchange(managerCashBalance);
    }

    @GetMapping("/api/manager/cash-exchange")
    public ClosingData cashExchange(@RequestParam Long employeeId){
        return cashExchangeService.getEmployeeData(employeeId);
    }

    @PostMapping("/api/manager/cash-exchange")
    public ResponseEntity<?> cashExchange(@RequestBody CashExchangeCreate cashExchangeCreate){
        CashExchangeResultData cashExchangeResultDataResult = cashExchangeService.createCashExchangeAndUpdate(cashExchangeCreate);
        if(cashExchangeResultDataResult != null){
            return ResponseEntity.status(HttpStatus.OK).body(cashExchangeResultDataResult);
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("인수도 거래 실패");
        }
    }
}
