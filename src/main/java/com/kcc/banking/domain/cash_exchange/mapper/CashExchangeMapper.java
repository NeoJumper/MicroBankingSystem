package com.kcc.banking.domain.cash_exchange.mapper;


import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.cash_exchange.dto.request.CashExchangeCreate;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeResultData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CashExchangeMapper {

    // search for emp_id
    List<CashExchangeResultData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId);

    // search for registrant_id
    List<CashExchangeResultData> getCashExchangeDataForManager(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId);

    // create new cash exchange
    int createCashExchange(CashExchangeCreate cashExchangeCreate);

    // return seq
    Long getNextCashExchangeSeq();

    CashExchangeResultData getCashExchangeDataByID(Long cashExchangeId);
}
