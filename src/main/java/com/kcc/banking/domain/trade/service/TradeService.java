package com.kcc.banking.domain.trade.service;

import com.kcc.banking.domain.trade.dto.request.TradeSearch;
import com.kcc.banking.domain.trade.dto.response.TradeOfList;
import com.kcc.banking.domain.trade.mapper.TradeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeMapper tradeMapper;

    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch) {

        return tradeMapper.findTradeListOfAccId(tradeSearch);
    }
}
