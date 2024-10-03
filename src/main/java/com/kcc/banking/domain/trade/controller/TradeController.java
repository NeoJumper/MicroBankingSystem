package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/employee")
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/trade-list")
    public String tradeList(Model businessDay){
        String getBusinessDay = tradeService.getBusinessDay();
        System.out.println("tradeList Controller >>>>>>>>>>"+getBusinessDay);
        businessDay.addAttribute("businessDay", getBusinessDay);
        return("trade/trade-list");
    }

    @GetMapping("/trade-cash")
    public String employeeAccountTransfer() {
        return "trade/trade-cash";
    }
}
