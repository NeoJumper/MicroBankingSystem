package com.kcc.banking.domain.trade.controller;

import com.kcc.banking.domain.account.dto.request.AccountClose;
import com.kcc.banking.domain.account.dto.response.AccountCloseResult;
import com.kcc.banking.domain.account.dto.response.CloseSavingsFlexibleAccountTotal;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferDetail;
import com.kcc.banking.domain.bulk_transfer.dto.response.BulkTransferSearchResult;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class TradeRestController {

    private final TradeService tradeService;
    private final AccountTradeFacade accountTradeFacade;

    @GetMapping("/trade/search/result")
    public ResponseEntity<PagingInfoOfTradeList> getSearchResultOfTradeList(@ModelAttribute TradeSearch tradeSearch){

        PagingInfoOfTradeList tradeCashOfPerAccount = tradeService.findTradeListOfAccId(tradeSearch);

        return  ResponseEntity.ok(tradeCashOfPerAccount);
    }
    @GetMapping("/bulk-transfer/fail-trades")
    public ResponseEntity<List<TradeDetail>> getFailTradeList(@RequestParam("bulkTransferId") Long bulkTransferId){
        List<TradeDetail> failTradeDetailList = tradeService.bulkTransferFailTradeList(bulkTransferId);

        return  ResponseEntity.ok(failTradeDetailList);
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
    public ResponseEntity<List<TransferDetail>> getTransferCancel(@PathVariable(value = "tradeNumber", required = false) Long tradeNumber) {
        List<TransferDetail> tradeDetails = tradeService.getTradeByTradeNumber(tradeNumber);
        System.out.println("TradeDetail::"+tradeDetails);
        return ResponseEntity.ok(tradeDetails);
    }

    // 취소 신청 - 실제 status 변경 및 역거래
    @PostMapping("/account-transfer-cancel")
    public ResponseEntity<List<TransferDetail>> transferCancel(@RequestBody TradeCancelRequest tradeCancelRequest) {
        List<TransferDetail> transferCancelDetails = accountTradeFacade.updateTransferCancel(tradeCancelRequest);
        return ResponseEntity.ok(transferCancelDetails);
    }

    /**
     * @Discription 
     * - 보통예금 계좌 해지 프로세스
     * @param accountClose
     * @return ResponseEntity<AccountCloseResult>
     */
    @PostMapping("/close-trade")
    public ResponseEntity<AccountCloseResult> addCloseTrade(@RequestBody AccountClose accountClose) {
        AccountCloseResult accountCloseResult = accountTradeFacade.addCloseTrade(accountClose);
        return ResponseEntity.status(HttpStatus.OK).body(accountCloseResult);
    }

    /**
     * @Discription 
     * - 보통에금 해지 거래 취소 추가
     * @param accountIdWithExpireDate
     * @return ResponseEntity<CloseCancelDetail>
     */
    @PostMapping("/close-cancel-trade")
    public ResponseEntity<CloseCancelDetail> cancelCloseTrade(@RequestBody AccountIdWithExpireDate accountIdWithExpireDate) {
        return ResponseEntity.ok().body(accountTradeFacade.rollbackAccountCancel(accountIdWithExpireDate.getAccountId()));
    }

    // 대량 이체 상세 내역 조회
    @GetMapping("/bulk-transfer-trade")
    public ResponseEntity<List<TradeByBulkTransfer>> getBulkTransferTradeList(@RequestParam Long bulkTransferId) {
        List<TradeByBulkTransfer> tradeByBulkTransferList = tradeService.getTradeListByBulkTransfer(bulkTransferId);
        return ResponseEntity.ok().body(tradeByBulkTransferList);
    }

    // 금일 계좌 이체 출금액
    @GetMapping("/transfer-trades/daily-total")
    public ResponseEntity<BigDecimal> getTransferTradeOfToday(TradeSearch tradeSearch) {
        BigDecimal transferAmountOfToday = tradeService.getTransferAmountOfToday(tradeSearch);
        return ResponseEntity.ok().body(transferAmountOfToday);
    }


}
