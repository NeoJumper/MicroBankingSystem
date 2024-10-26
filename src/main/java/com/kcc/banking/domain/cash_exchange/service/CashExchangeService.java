package com.kcc.banking.domain.cash_exchange.service;


import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeCloseData;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeData;
import com.kcc.banking.domain.cash_exchange.mapper.CashExchangeMapper;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashExchangeService {
    private final CashExchangeMapper cashExchangeMapper;
    private final CommonService commonService;


    // employee
    public List<CashExchangeData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        return cashExchangeMapper.getCashExchangeData(currentBusinessDateAndEmployeeId);
    }

    // manager
    public CashExchangeCloseData getCashExchangeDataForManager(){
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        List<CashExchangeData> cashExchangeList = cashExchangeMapper.getCashExchangeDataForManager(currentBusinessDateAndEmployeeId);
        BigDecimal lastManagerCash = BigDecimal.ZERO;
        CashExchangeCloseData cashExchangeCloseData =  CashExchangeCloseData.builder().cashExchangeList(cashExchangeList).build();
        if (cashExchangeList != null && !cashExchangeList.isEmpty()) {
            lastManagerCash = cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance();
            cashExchangeCloseData.setLastManagerCash(cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance());
        }

        return cashExchangeCloseData;
    }


}
