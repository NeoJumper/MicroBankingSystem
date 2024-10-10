package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.account.dto.request.StatusWithTrade;
import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.trade.dto.request.TradeCancelRequest;
import com.kcc.banking.domain.trade.dto.request.CashTradeCreate;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.request.TransferTradeCreate;
import com.kcc.banking.domain.trade.dto.response.*;

import com.kcc.banking.domain.trade.service.AccountTradeFacade;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final AccountTradeFacade accountTradeFacade;

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
    public ResponseEntity<TradeDetail> createCashTrade(@RequestBody CashTradeCreate cashTradeCreate){
        TradeDetail tradeDetail = accountTradeFacade.createCashTrade(cashTradeCreate);
        return ResponseEntity.ok(tradeDetail);
    }

    // 계좌 이체
    @PostMapping("/account-transfer")
    public ResponseEntity<List<TransferDetail>> transfer(@RequestBody TransferTradeCreate transferTradeCreate) {
        List<TransferDetail> tradeDetails = accountTradeFacade.processTransfer(transferTradeCreate);
        return ResponseEntity.ok(tradeDetails);
    }

    // 취소 페이지에서, 거래번호를 통해 취소하려는 거래 내역 GET
    @GetMapping("/account-transfer-cancel/{tradeNumber}")
    public ResponseEntity<List<TransferDetail>> transferCancel(@PathVariable(value = "tradeNumber", required = false) Long tradeNumber) {
        List<TransferDetail> tradeDetails = tradeService.getTradeByTradeNumber(tradeNumber);
        System.out.println("TradeDetail::"+tradeDetails);
        return ResponseEntity.ok(tradeDetails);
    }
    // 취소 신청 - 실제 status 변경
    @PostMapping("/account-transfer-cancel")
    public ResponseEntity<List<TransferDetail>> updateCancelTransferCAN(@RequestBody TradeCancelRequest tradeCancelRequest) {
        List<TransferDetail> tradeDetails = tradeService.updateCancelTransferCAN(tradeCancelRequest);
        return ResponseEntity.ok(tradeDetails);
    }

    @PostMapping("/close-trade")
    public ResponseEntity<?> addCloseTrade(@RequestBody StatusWithTrade statusWithTrade) {
        String result = accountTradeFacade.addCloseTrade(statusWithTrade);

        if(result.equals("FAIL")) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("계좌해지 거래 실패");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/close-cancel-trade")
    public ResponseEntity<CloseCancelDetail> cancelCloseTrade(@RequestBody AccountIdWithExpireDate accountIdWithExpireDate) {

        return ResponseEntity.ok().body(accountTradeFacade.rollbackAccountCancel(accountIdWithExpireDate.getAccountId()));
    }
}
