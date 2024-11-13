package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/employee")
public class TradeController {

    private final TradeService tradeService;
    private final CommonService commonService;

    @GetMapping("/trade-list")
    public String getTradeListPage(Model businessDay){
        Timestamp getBusinessDay = tradeService.getBusinessDay();
        System.out.println("tradeList Controller >>>>>>>>>>"+getBusinessDay);
        businessDay.addAttribute("businessDay", getBusinessDay);

        return("trade/trade-list");
    }

    @GetMapping("/cash-trade")
    public String getCashTradePage(Model model) {
        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("tradeDate", currentData.getCurrentBusinessDate());

        return "trade/cash-trade";
    }

    @GetMapping("/account-transfer")
    public String getAccountTransferPage(Model model) {
        CurrentData currentData = commonService.getCurrentData();

        model.addAttribute("branchId", currentData.getBranchId());
        model.addAttribute("employeeName", currentData.getEmployeeName());
        model.addAttribute("employeeId", currentData.getEmployeeId());
        model.addAttribute("tradeDate", currentData.getCurrentBusinessDate());
        return "trade/account-transfer";
    }

    @GetMapping("/account-transfer-cancel")
    public String getAccountTransferCancelPage(@RequestParam(value="tradeNumber", required = false) long tradeNumber, Model model) {
        model.addAttribute("tradeNumber", tradeNumber);
        return "trade/account-transfer-cancel";
    }

}
