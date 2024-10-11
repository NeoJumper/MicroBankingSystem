package com.kcc.banking.domain.business_day_close.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day_close.dto.request.*;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessDayCloseService {

    private final BusinessDayCloseMapper businessDayCloseMapper;
    private final CommonService commonService;


    public void createClosingData(BusinessDayChange businessDayChange) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        Long tradeNumber = businessDayCloseMapper.getNextTradeNumberVal();

        createEmployeeClosing(businessDayChange.getWorkerDataList(),businessDayChange.getBusinessDateToChange(), currentBusinessDateAndBranchId, tradeNumber);
        createBranchClosing(businessDayChange.getBusinessDateToChange(), businessDayChange.getPrevCashBalanceOfBranch(), tradeNumber, currentBusinessDateAndBranchId);

    }

    private void createBranchClosing(String businessDateToChange, BigDecimal prevCashBalanceOfBranch, Long tradeNumber, BusinessDateAndBranchId businessDateAndBranchId) {
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        BranchClosingCreate branchClosingCreate = BranchClosingCreate.builder()
                .closingDate(businessDateToChange)
                .branchId(businessDateAndBranchId.getBranchId())
                .status("OPEN")
                .prevCashBalance(prevCashBalanceOfBranch)
                .tradeNumber(tradeNumber)
                .registrantId(String.valueOf(loginMemberId))
                .build();

        businessDayCloseMapper.insertBranchClosing(branchClosingCreate);
    }

    public void createEmployeeClosing(List<WorkerData> workerDataList, String businessDateToChange, BusinessDateAndBranchId businessDateAndBranchId, Long tradeNumber) {

        List<EmployeeClosingCreate> employeeClosingCreateList = workerDataList.stream().map(workerData -> EmployeeClosingCreate.of(workerData,businessDateToChange, businessDateAndBranchId, tradeNumber))
                .toList();

        businessDayCloseMapper.batchInsertEmployeeClosing(employeeClosingCreateList);

    }

    public String getBranchClosingStatusByDateAndId(BusinessDateAndBranchId businessDateAndBranchId) {
        return businessDayCloseMapper.findBranchClosingStatusByDate(businessDateAndBranchId);
    }

    public ClosingData getClosingData(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        return businessDayCloseMapper.findClosingData(currentBusinessDateAndEmployeeId);
    }

    /**
     * @Description
     * 1. 개인 마감 데이터 조회(거래내역과 마감 데이터)
     *      - 사원들의 마감 내역(전일자 현금 잔액, 입출금액, 금일 마감 금액 등)
     *      - 지점의 전일자 현금 잔액
     *      - 지점의 현금 입출금액, 금일 마감 금액(사원들의 )
     */
    public ManagerClosingData getManagerClosingData() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId= commonService.getCurrentBusinessDateAndBranchId();
        List<ClosingData> closingDataList = businessDayCloseMapper.findClosingDataList(currentBusinessDateAndBranchId);
        BigDecimal branchClosingVaultCash = businessDayCloseMapper.findBranchClosingVaultCash(currentBusinessDateAndBranchId);

        return ManagerClosingData.of(closingDataList, branchClosingVaultCash);
    }



    public void closeEmployeeBusinessDay(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId, VaultCashRequest vaultCashRequest) {
        EmployeeClosingUpdate employeeClosingUpdate = EmployeeClosingUpdate.builder()
                .targetClosingDate(currentBusinessDateAndEmployeeId.getBusinessDate())
                .targetEmployeeId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .modifierId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .status("CLOSED")
                .vaultCash(vaultCashRequest.getVaultCash()) // 임의의 값 수정해줘야함
                .build();

        businessDayCloseMapper.updateEmployeeClosing(employeeClosingUpdate);
    }

    public void closeBranchBusinessDay(BusinessDateAndBranchId businessDateAndBranchId, VaultCashRequest vaultCashRequest) {
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();
        BranchClosingUpdate branchClosingUpdate = BranchClosingUpdate.builder().targetClosingDate(businessDateAndBranchId.getBusinessDate())
                .targetBranchId(Long.valueOf(businessDateAndBranchId.getBranchId()))
                .modifierId(loginMemberId)
                .status("CLOSED")
                .vaultCash(vaultCashRequest.getVaultCash()) // 임의의 값 수정해줘야함
                .build();

        businessDayCloseMapper.updateBranchClosing(branchClosingUpdate);
    }

    public String getClosingTradeNumber(BusinessDateAndBranchId businessDateAndBranchId) {
        return businessDayCloseMapper.findClosingTradeNumber(businessDateAndBranchId);
    }
}
