package com.kcc.banking.domain.trade.mapper;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeInfoOfPerAccount;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMapper {

    // 페이징 처리한 거래내역표 정보들
    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch);

    // 한계좌에대한 총 입출금 합계
    List<TradeInfoOfPerAccount> getTotalSumOfTradeList(TradeSearch tradeSearch);

    public int getTradeCount(TradeSearch tradeSearch);


    List<TradeByCash> findTradeByCashList(BusinessDateAndEmployeeId businessDateAndEmployeeId);

}
