package com.kcc.banking.domain.cash_exchange.service;


import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeData;
import com.kcc.banking.domain.cash_exchange.mapper.CashExchangeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashExchangeService {
    private final CashExchangeMapper cashExchangeMapper;

    public List<CashExchangeData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        return cashExchangeMapper.getCashExchangeData(currentBusinessDateAndEmployeeId);
    }
}
