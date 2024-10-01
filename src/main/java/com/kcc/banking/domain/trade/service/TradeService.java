package com.kcc.banking.domain.trade.service;

import com.kcc.banking.domain.businessday.service.BusinessDayService;
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
    private final BusinessDayService businessDayService;

    public List<TradeOfList> findTradeListOfAccId(TradeSearch tradeSearch) {

        return tradeMapper.findTradeListOfAccId(tradeSearch);
    }

    public String getBusinessDay() {
        String getDay = businessDayService.getCurrentBusinessDay().getBusinessDate();
        System.out.println("businessDayService.getCurrentBusinessDay().getBusinessDate()"+ getDay);
        return businessDayService.getCurrentBusinessDay().getBusinessDate();
    }
}
