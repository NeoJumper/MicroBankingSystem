package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.trade.dto.request.TradeCreate;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.Criteria;
import com.kcc.banking.domain.trade.dto.response.PageDTO;
import com.kcc.banking.domain.trade.dto.response.PagingInfoOfTradeList;

import com.kcc.banking.domain.trade.dto.response.TradeDetail;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class TradeRestController {

    private final TradeService tradeService;

    @GetMapping("/trade/search/result")
    public ResponseEntity<PagingInfoOfTradeList> getSearchResultOfTradeList(@ModelAttribute TradeSearch tradeSearch, Model model){

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


        Criteria criteria = tradeSearch.getCriteria();
        System.out.println("Page Number: " + criteria.getPageNum());
        System.out.println("Amount: " + criteria.getAmount());

        System.out.println(" tradeSearch getaccID >>>>>>>"+tradeSearch.getAccId());

        PagingInfoOfTradeList tradeCashOfPerAccount = tradeService.findTradeListOfAccId(tradeSearch);

        System.out.println("Page Number: " + tradeCashOfPerAccount.getPageDTO().getCriteria().getPageNum());
        System.out.println("Amount: " + tradeCashOfPerAccount.getPageDTO().getCriteria().getAmount());

        System.out.println("Page getStartPage: " + tradeCashOfPerAccount.getPageDTO().getStartPage());

        System.out.println("Page getEndPage: " + tradeCashOfPerAccount.getPageDTO().getEndPage());



        return  ResponseEntity.ok(tradeCashOfPerAccount);
    }

    // 현금 거래 생성
    @PostMapping("/trade-cash")
    public ResponseEntity<TradeDetail> createCashTrade(@RequestBody TradeCreate tradeCreate){
        TradeDetail tradeDetail = tradeService.createCashTrade(tradeCreate);
        return ResponseEntity.ok(tradeDetail);
    }


}
