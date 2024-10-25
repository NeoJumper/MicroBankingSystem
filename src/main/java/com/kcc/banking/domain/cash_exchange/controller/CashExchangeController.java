package com.kcc.banking.domain.cash_exchange.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CashExchangeController {

    @GetMapping("/page/manager/cash-exchange")
    public String cashExchange(Model model) {
        return "cash-exchange/cash-exchange";
    }

    @GetMapping("/page/manager/cash-exchange-close")
    public String cashExchangeClose() {
        return "cash-exchange/cash-exchange-close";
    }
}
