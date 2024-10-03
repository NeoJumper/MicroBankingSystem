package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.trade.dto.request.TradeCreate;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeCashOfPerAccount;
import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class TradeRestController {

    private final TradeService tradeService;

    @GetMapping("/trade/search/result")
    public ResponseEntity<TradeCashOfPerAccount> getSearchResultOfTradeList(@ModelAttribute TradeSearch tradeSearch){

        String accId = tradeSearch.getAccId();
        String tradeType = tradeSearch.getTradeType();
        String startDate = tradeSearch.getStartDate();
        String endDate = tradeSearch.getEndDate();
        String sortOrder = tradeSearch.getSortOrder();

        // 디버깅용 출력
        System.out.println("Account ID: " + accId);
        System.out.println("Trade Type: " + tradeType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Sort Order: " + sortOrder);

        TradeCashOfPerAccount tradeCashOfPerAccount = tradeService.findTradeListOfAccId(tradeSearch);

        return  ResponseEntity.ok(tradeCashOfPerAccount);
    }

    // 현금 거래 생성
    @PostMapping("/account-cash-trade")
    public ResponseEntity<TradeDetail> createCashTrade(@RequestBody TradeCreate tradeCreate){
        TradeDetail tradeDetail = tradeService.createCashTrade(tradeCreate);
        return ResponseEntity.ok(tradeDetail);
    }


}
