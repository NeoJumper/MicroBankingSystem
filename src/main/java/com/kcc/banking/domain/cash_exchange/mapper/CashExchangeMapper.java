package com.kcc.banking.domain.cash_exchange.mapper;


import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CashExchangeMapper {

    List<CashExchangeData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId);
}
