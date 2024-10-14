package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/employee")
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/trade-list")
    public String getTradeListPage(Model businessDay){
        String getBusinessDay = tradeService.getBusinessDay();
        System.out.println("tradeList Controller >>>>>>>>>>"+getBusinessDay);
        businessDay.addAttribute("businessDay", getBusinessDay);

        return("trade/trade-list");
    }

    @GetMapping("/cash-trade")
    public String getCashTradePage() {
        return "trade/cash-trade";
    }

    @GetMapping("/account-transfer")
    public String getAccountTransferPage() {
        return "trade/account-transfer";
    }

    @GetMapping("/account-transfer-cancel")
    public String getAccountTransferCancelPage(@RequestParam(value="tradeNumber", required = false) long tradeNumber, Model model) {
        model.addAttribute("tradeNumber", tradeNumber);
        return "trade/account-transfer-cancel";
    }

    @GetMapping("/bulk-transfer")
    public String getBulkTransferPage() {
        return "trade/bulk-transfer";
    }

    @GetMapping("/bulk-transfer-result")
    public String getBulkTransferResultPage(@RequestParam String accId, Model model) {
        model.addAttribute("accId", accId);
        return "trade/bulk-transfer-result";
    }
}
