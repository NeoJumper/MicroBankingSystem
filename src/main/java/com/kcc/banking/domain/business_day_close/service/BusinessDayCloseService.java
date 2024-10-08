package com.kcc.banking.domain.business_day_close.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day_close.dto.request.BranchClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.service.InterestService;
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


    public Long createClosingData(BusinessDayChange businessDayChange) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        Long tradeNumber = businessDayCloseMapper.getNextTradeNumberVal();

        createEmployeeClosing(businessDayChange.getWorkerDataList(),businessDayChange.getBusinessDateToChange(), currentBusinessDateAndBranchId, tradeNumber);
        createBranchClosing(businessDayChange.getBusinessDateToChange(), businessDayChange.getPrevCashBalanceOfBranch(), tradeNumber, currentBusinessDateAndBranchId);

        return tradeNumber;
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
                .registrationDate(businessDateAndBranchId.getBusinessDate())
                .version(1L)
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

    public List<ClosingData> getClosingDataList(BusinessDateAndBranchId currentBusinessDateAndBranchId) {
        return businessDayCloseMapper.findClosingDataList(currentBusinessDateAndBranchId);
    }


    public void employeeDeadlineStatusToClosed(BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId) {
        businessDayCloseMapper.employeeDeadlineStatusToClosed(currentBusinessDateAndEmployeeId);
    }

    public void branchDeadlineStatusToClosed(BusinessDateAndBranchId businessDateAndBranchId) {
        businessDayCloseMapper.branchDeadlineStatusToClosed(businessDateAndBranchId);
    }

    public String getClosingTradeNumber(BusinessDateAndBranchId businessDateAndBranchId) {
        return businessDayCloseMapper.findClosingTradeNumber(businessDateAndBranchId);
    }
}
