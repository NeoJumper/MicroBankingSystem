package com.kcc.banking.domain.trade.mapper;

import com.kcc.banking.domain.interest.dto.request.AccountIdWithExpireDate;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.trade.dto.request.TradeCreate;
import com.kcc.banking.domain.trade.dto.request.TradeUpdate;
import com.kcc.banking.domain.trade.dto.response.*;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TradeMapper {

    // 페이징 처리한 거래내역표 정보들
    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch);

    // 한계좌에대한 총 입출금 합계
    List<TradeInfoOfPerAccount> getTotalSumOfTradeList(TradeSearch tradeSearch);

    int getTradeCount(TradeSearch tradeSearch);


    List<TradeByCash> findTradeByCashList(BusinessDateAndEmployeeId businessDateAndEmployeeId);

    Long findNextTradeNumberVal();

    BigDecimal findPaidAmount(AccountIdWithExpireDate accountIdWithExpireDate);

    BigDecimal findTransferAmountOfToday(TradeSearch tradeSearch);

    // 거래 번호로 내역 조회
    List<TransferDetail> getTradeDetailsByTradeNumber(Long tradeNumber);

    // 거래 취소 요청
    int updateTradeStatusToCancel(Long tradeNumber);

    // 거래 내역 추가
    int insertTrade(TradeCreate withdrawalTrade);

    List<TradeByBulkTransfer> findTradeListByBulkTransfer(Long bulkTransferId);

    // trade 테이블 업데이트
    int updateAllTrade(TradeUpdate tradeUpdate);
}
