package com.kcc.banking.domain.trade.mapper;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMapper {

    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch);

    List<TradeByCash> findTradeByCashList(BusinessDateAndEmployeeId businessDateAndEmployeeId);
}
