package com.kcc.banking.domain.cash_exchange.controller;

import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeCloseData;
import com.kcc.banking.domain.cash_exchange.service.CashExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class CashExchangeController {

    private final CashExchangeService cashExchangeService;

    @GetMapping("/page/manager/cash-exchange")
    public String cashExchange(Model model) {
        BigDecimal currentCashBalanceForManager = cashExchangeService.getCurrentCashBalanceForManager();
        model.addAttribute("currentCashBalanceForManager", currentCashBalanceForManager);
        return "cash-exchange/cash-exchange";
    }

    @GetMapping("/page/manager/cash-exchange-close")
    public String cashExchangeClose(Model model) {
        CashExchangeCloseData cashExchangeCloseData = cashExchangeService.getCashExchangeDataForManager();
        model.addAttribute("cashExchangeCloseData", cashExchangeCloseData);
        return "cash-exchange/cash-exchange-close";
    }
}
