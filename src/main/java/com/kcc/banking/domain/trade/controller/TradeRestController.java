package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class TradeRestController {

    private final TradeService tradeService;

    @GetMapping("/trade/search/result")
    public List<TradeOfList> getSearchResultOfTradeList(@ModelAttribute TradeSearch tradeSearch){

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

        return tradeService.findTradeListOfAccId(tradeSearch);
    }


}
