package com.kcc.banking.domain.cash_exchange.service;


import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingUpdate;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.cash_exchange.dto.request.ManagerCashBalanceRequest;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeCloseData;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeData;
import com.kcc.banking.domain.cash_exchange.mapper.CashExchangeMapper;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashExchangeService {
    private final CashExchangeMapper cashExchangeMapper;
    private final CommonService commonService;
    private final BusinessDayCloseMapper businessDayCloseMapper;


    // employee
    public List<CashExchangeData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        return cashExchangeMapper.getCashExchangeData(currentBusinessDateAndEmployeeId);
    }

    // manager

    /**
     * @Discription
     *  1. 시재금 거래 내역 불러오기
     *  2. 매니저의 현재 시점 시재 계산
     */
    public CashExchangeCloseData getCashExchangeDataForManager(){
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        // 1
        List<CashExchangeData> cashExchangeList = cashExchangeMapper.getCashExchangeDataForManager(currentBusinessDateAndEmployeeId);
        // 2
        ClosingData closingData = businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);

        BigDecimal currentCashBalance = closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal());

        CashExchangeCloseData cashExchangeCloseData =  CashExchangeCloseData.builder()
                .cashExchangeList(cashExchangeList)
                .lastManagerCash(currentCashBalance)
                .build();
        /*        if (cashExchangeList != null && !cashExchangeList.isEmpty()) {
            lastManagerCash = cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance();
            cashExchangeCloseData.setLastManagerCash(cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance());
        }else{
            cashExchangeCloseData.setLastManagerCash(lastManagerCash);
        }*/

        return cashExchangeCloseData;
    }

    // manager clicked cash exchange close btn

    /**
     * @Discription
     * 1. 매니저 마감 금액 확인 - employee_close 테이블의 prev_cash_balance + total_deposit - total_withdrawal 과 동일한 금액인지 확인
     * 2. 예외 처리
     * 3. 매니저 마감
     * @param managerCashBalance
     */
    public void closeCashExchange(ManagerCashBalanceRequest managerCashBalance) {
        // 1
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        ClosingData closingData = businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);
        BigDecimal expectedCashBalance = closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal());

        // 2
        if(!expectedCashBalance.equals(managerCashBalance.getManagerCashBalance())){
            throw new BadRequestException(ErrorCode.INVALID_MANAGER_BALANCE);
        }

        //3
        EmployeeClosingUpdate cashExchangeCloseOfManager = EmployeeClosingUpdate.builder()
                .status("CLOSED")
                .totalDeposit(closingData.getTotalDeposit())
                .totalWithdrawal(closingData.getTotalWithdrawal())
                .vaultCash(managerCashBalance.getManagerCashBalance())
                .modifierId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .targetEmployeeId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .targetClosingDate(currentBusinessDateAndEmployeeId.getBusinessDate())
                .build();


        businessDayCloseMapper.updateEmployeeClosing(cashExchangeCloseOfManager);
    }
}
