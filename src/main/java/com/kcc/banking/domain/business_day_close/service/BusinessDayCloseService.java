package com.kcc.banking.domain.business_day_close.service;

import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day_close.dto.request.*;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.common.dto.request.CurrentData;
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
     *      - 지점의 현금 입출금액, 금일 마감 금액(사원들의 현금 입금 합산 - 출금 합산)
     */
    public ManagerClosingData getManagerClosingData() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId= commonService.getCurrentBusinessDateAndBranchId();
        String branchName = commonService.getCurrentData().getBranchName();

        List<ClosingData> closingDataList = businessDayCloseMapper.findClosingDataList(currentBusinessDateAndBranchId);
        // if branchClosingVaultCash == null : ManagerClosingData 내부에서 처리

        BigDecimal branchClosingVaultCash = businessDayCloseMapper.findBranchClosingVaultCash(currentBusinessDateAndBranchId);
        return ManagerClosingData.of(closingDataList, branchClosingVaultCash, branchName);
    }



    public void closeEmployeeBusinessDay(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId, VaultCashRequest vaultCashRequest) {
        EmployeeClosingUpdate employeeClosingUpdate = EmployeeClosingUpdate.builder()
                .targetClosingDate(currentBusinessDateAndEmployeeId.getBusinessDate())
                .targetEmployeeId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .modifierId(currentBusinessDateAndEmployeeId.getEmployeeId())
                .status("CLOSED")
                .vaultCash(vaultCashRequest.getVaultCash())
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

    public void updateTradeAmount(BigDecimal amount, CurrentData currentData, String tradeType) {
        BusinessDateAndEmployeeId businessDateAndEmployeeId = BusinessDateAndEmployeeId.builder()
                .employeeId(currentData.getEmployeeId())
                .businessDate(currentData.getCurrentBusinessDate()).build();

        EmployeeClosingUpdate employeeClosingUpdate = EmployeeClosingUpdate.builder()
                .targetClosingDate(currentData.getCurrentBusinessDate())
                .targetEmployeeId(currentData.getEmployeeId())
                .modifierId(currentData.getEmployeeId())
                .status("CLOSED")
                .build();

        // 출금, 해지일 경우
        if(tradeType.equals("WITHDRAWAL") || tradeType.equals("CLOSE")){
            BigDecimal employeeClosingTotalWithdrawal = businessDayCloseMapper.findEmployeeClosingTotalWithdrawal(businessDateAndEmployeeId);

            employeeClosingUpdate.setTotalWithdrawal(employeeClosingTotalWithdrawal.add(amount));
        }
        // 입금, 개설일 경우
        else if(tradeType.equals("DEPOSIT") || tradeType.equals("OPEN") || tradeType.equals("CLOSE_CANCEL")){
            BigDecimal employeeClosingTotalDeposit = businessDayCloseMapper.findEmployeeClosingTotalDeposit(businessDateAndEmployeeId);

            employeeClosingUpdate.setTotalDeposit(employeeClosingTotalDeposit.add(amount));
        }


        businessDayCloseMapper.updateEmployeeClosing(employeeClosingUpdate);
    }

    public void deleteEmployeeClosing(String tradeNumber) {

        businessDayCloseMapper.deleteEmployeeClosing(tradeNumber);
    }

    public void deleteBranchClosing(String tradeNumber) {

        businessDayCloseMapper.deleteBranchClosing(tradeNumber);
    }


    /**
     * @Description
     * 이전 영업일의 현재 지점에 있는 모든 사원의 마감 상태를 되돌린다.
     */
    public void resetEmployeeClosing(CurrentData currentData, String prevBusinessDate) {
        BusinessDateAndBranchId prevBusinessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(prevBusinessDate)
                .branchId(String.valueOf(currentData.getBranchId()))
                .build();

        List<ClosingData> closingDataList = businessDayCloseMapper.findClosingDataList(prevBusinessDateAndBranchId);
        List<String> employeeIdList = closingDataList.stream().map(ClosingData::getId).toList();


        employeeIdList.forEach(employeeId -> {

            EmployeeClosingUpdate employeeClosingUpdate = EmployeeClosingUpdate.builder()
                    .targetClosingDate(prevBusinessDate)
                    .targetEmployeeId(Long.valueOf(employeeId))
                    .modifierId(currentData.getEmployeeId())
                    .status("OPEN")
                    .vaultCash(BigDecimal.ZERO)
                    .build();

            businessDayCloseMapper.updateEmployeeClosing(employeeClosingUpdate);
        });

    }

    public void resetBranchClosing(CurrentData currentData, String prevBusinessDate) {
        BranchClosingUpdate branchClosingUpdate = BranchClosingUpdate.builder()
                .targetClosingDate(prevBusinessDate)
                .targetBranchId(currentData.getBranchId())
                .modifierId(currentData.getEmployeeId())
                .status("OPEN")
                .vaultCash(BigDecimal.ZERO)
                .build();

        businessDayCloseMapper.updateBranchClosing(branchClosingUpdate);
    }
}
