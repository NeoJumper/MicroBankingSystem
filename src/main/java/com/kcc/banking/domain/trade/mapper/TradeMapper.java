package com.kcc.banking.domain.trade.mapper;

import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface TradeMapper {

    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch);
}
