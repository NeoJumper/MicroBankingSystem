package com.kcc.banking.domain.cash_exchange.service;


import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeAdditionalUpdate;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingUpdate;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.cash_exchange.dto.request.CashExchangeCreate;
import com.kcc.banking.domain.cash_exchange.dto.request.ManagerCashBalance;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeCloseData;
import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeResultData;
import com.kcc.banking.domain.cash_exchange.mapper.CashExchangeMapper;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashExchangeService {
    private final CashExchangeMapper cashExchangeMapper;
    private final CommonService commonService;
    private final BusinessDayCloseMapper businessDayCloseMapper;


    // employee
    public List<CashExchangeResultData> getCashExchangeData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        return cashExchangeMapper.getCashExchangeData(currentBusinessDateAndEmployeeId);
    }

    public ClosingData getEmployeeData(Long employeeId) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        BusinessDateAndEmployeeId selectEmployee = BusinessDateAndEmployeeId.builder().businessDate(currentBusinessDateAndBranchId.getBusinessDate()).employeeId(employeeId).build();
        return businessDayCloseMapper.findClosingData(selectEmployee);
    }

    // manager

    /**
     * @Discription 1. 날짜와 로그인한 유저 확인
     * 2. Employee_closing 테이블에서 매니저의 시재금 확인
     */

    public BigDecimal getCurrentCashBalanceForManager() {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        ClosingData closingData = businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);
        return closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal());
    }

    /**
     * @Discription 1. 시재금 거래 내역 불러오기
     * 2. 매니저의 현재 시점 시재 계산
     */
    public CashExchangeCloseData getCashExchangeDataForManager() {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        // 1
        List<CashExchangeResultData> cashExchangeList = cashExchangeMapper.getCashExchangeDataForManager(currentBusinessDateAndEmployeeId);
        // 2
        ClosingData closingData = businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);

        BigDecimal currentCashBalance = closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal());

        /*        if (cashExchangeList != null && !cashExchangeList.isEmpty()) {
            lastManagerCash = cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance();
            cashExchangeCloseData.setLastManagerCash(cashExchangeList.get(cashExchangeList.size() - 1).getManagerCashBalance());
        }else{
            cashExchangeCloseData.setLastManagerCash(lastManagerCash);
        }*/

        return CashExchangeCloseData.builder()
                .cashExchangeList(cashExchangeList)
                .lastManagerCash(currentCashBalance)
                .build();
    }

    // manager clicked cash exchange close btn

    /**
     * @param managerCashBalance
     * @Discription 1. 매니저 마감 금액 확인 - employee_close 테이블의 prev_cash_balance + total_deposit - total_withdrawal 과 동일한 금액인지 확인
     * 2. 예외 처리
     * 3. 매니저 마감
     */
    public void closeCashExchange(ManagerCashBalance managerCashBalance) {
        // 1
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        ClosingData closingData = businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);
        BigDecimal expectedCashBalance = closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal());

        // 2
        if (!expectedCashBalance.equals(managerCashBalance.getManagerCashBalance())) {
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


    /**
     * @param cashExchangeCreate
     * @Discription 1. 인수도 거래내역 테이블에 새로운 거래내역 생성
     * 2. 매니저의 출금/입금액 변경
     * 3. 행원의 출금/입금액 변경
     */
    @Transactional(rollbackFor = Exception.class)
    public CashExchangeResultData createCashExchangeAndUpdate(CashExchangeCreate cashExchangeCreate) {
        // init
        CurrentData currentData = commonService.getCurrentData();
        Long cashExchangeId = cashExchangeMapper.getNextCashExchangeSeq();

        // create cashExchange
        cashExchangeCreate.setRegistrantId(currentData.getEmployeeId());
        cashExchangeCreate.setBranchId(currentData.getBranchId());
        cashExchangeCreate.setExchangeDate(currentData.getCurrentBusinessDate());
        cashExchangeCreate.setId(cashExchangeId);

        // insert new cashExchange
        int cashExchangeResult = cashExchangeMapper.createCashExchange(cashExchangeCreate);

        // init result value
        int managerUpdateResult = 0;
        int employeeUpdateResult = 0;

        if ("HANDOVER".equals(cashExchangeCreate.getExchangeType())) {
            managerUpdateResult = updateManagerAndEmployee(cashExchangeCreate, currentData, "SUBTRACT", "ADD");
        } else if ("RECEIVE".equals(cashExchangeCreate.getExchangeType())) {
            managerUpdateResult = updateManagerAndEmployee(cashExchangeCreate, currentData, "ADD", "SUBTRACT");
        }

        //return (cashExchangeResult + managerUpdateResult + employeeUpdateResult) == 3 ? "SUCCESS" : "FAIL";

        if((cashExchangeResult + managerUpdateResult + employeeUpdateResult) == 3){
            CashExchangeResultData cashExchangeResultData = cashExchangeMapper.getCashExchangeDataByID(cashExchangeId);
            return cashExchangeResultData;
        }else{
            return null;
        }
    }

    private int updateManagerAndEmployee(CashExchangeCreate cashExchangeCreate, CurrentData currentData, String managerTransactionType, String employeeTransactionType) {
        EmployeeAdditionalUpdate managerUpdate = createEmployeeUpdate(
                cashExchangeCreate.getRegistrantId(),
                cashExchangeCreate.getAmount(),
                currentData,
                managerTransactionType,
                cashExchangeCreate.getRegistrantId()
        );

        EmployeeAdditionalUpdate employeeUpdate = createEmployeeUpdate(
                cashExchangeCreate.getEmpId(),
                cashExchangeCreate.getAmount(),
                currentData,
                employeeTransactionType,
                cashExchangeCreate.getRegistrantId()
        );

        int managerUpdateResult = businessDayCloseMapper.updateAdditionalCashExchange(managerUpdate);
        int employeeUpdateResult = businessDayCloseMapper.updateAdditionalCashExchange(employeeUpdate);

        return managerUpdateResult + employeeUpdateResult;
    }

    private EmployeeAdditionalUpdate createEmployeeUpdate(Long registrantId, BigDecimal amount, CurrentData currentData, String transactionType, Long modifierId) {
        return EmployeeAdditionalUpdate.builder()
                .registrantId(registrantId)
                .amount(amount)
                .closingDate(currentData.getCurrentBusinessDate())
                .transactionType(transactionType)
                .modifierId(modifierId)
                .build();
    }

}
