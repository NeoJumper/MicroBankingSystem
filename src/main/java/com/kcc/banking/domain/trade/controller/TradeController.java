package com.kcc.banking.domain.trade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/employee")
public class TradeController {

    @GetMapping("/trade-list")
    public String tradeList(){
        return("/trade/trade-list");
    }
}
