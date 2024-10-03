package com.kcc.banking.domain.business_day_close.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day_close.dto.request.BranchClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import com.kcc.banking.domain.business_day_close.dto.request.EmployeeClosingCreate;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.EmployeeClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import com.kcc.banking.domain.business_day_close.mapper.BusinessDayCloseMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.employee.service.EmployeeService;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessDayCloseService {

    private final BusinessDayCloseMapper businessDayCloseMapper;
    private final BusinessDayService businessDayService;
    private final EmployeeService employeeService;
    private final TradeService tradeService;
    private final InterestService interestService;
    private final CommonService commonService;

    public EmployeeClosingData getEmployeeClosingData() {

        String currentBusinessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        BusinessDateAndEmployeeId businessDateAndEmployeeId = BusinessDateAndEmployeeId.builder()
                .businessDate(currentBusinessDate)
                .employeeId(loginMemberId)
                .build();


        ClosingData closingData = businessDayCloseMapper.findClosingData(businessDateAndEmployeeId);
        List<TradeByCash> tradeByCashList = tradeService.findTradeByCash(businessDateAndEmployeeId);


        return EmployeeClosingData.of(closingData, tradeByCashList);
    }

    public ManagerClosingData getManagerClosingData() {

        String currentBusinessDate = businessDayService.getCurrentBusinessDay().getBusinessDate();
        String branchId = employeeService.getAuthData().getBranchId();

        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .businessDate(currentBusinessDate)
                .branchId(branchId)
                .build();


        List<ClosingData> closingDataList = businessDayCloseMapper.findClosingDataList(businessDateAndBranchId);
        return ManagerClosingData.of(closingDataList);

    }

    public void closeByEmployee() {
        BusinessDay currentBusinessDay = businessDayService.getCurrentBusinessDay();
        Long loginMemberId = AuthenticationUtils.getLoginMemberId();

        BusinessDateAndEmployeeId businessDateAndEmployeeId = BusinessDateAndEmployeeId.builder()
                .businessDate(currentBusinessDay.getBusinessDate())
                .employeeId(loginMemberId)
                .build();


        ClosingData closingData = businessDayCloseMapper.findClosingData(businessDateAndEmployeeId);

        if(closingData.getStatus().equals("CLOSED"))
            throw new BadRequestException(ErrorCode.ALREADY_CLOSED_BUSINESS_DAY);


        businessDayCloseMapper.employeeDeadlineStatusToClosed(businessDateAndEmployeeId);

    }

    /**
     * @Description
     * - 한명이라도 개인 마감 상태가 OPEN이라면 REQUIRED_EMPLOYEE_CLOSING
     * - 마감하려고 하는 지점의 마감상태가 CLOSED라면 ALREADY_CLOSED_BUSINESS_DAY
     * - 지점 마감 상태를 CLOSED로 변경
     * - 영업일의 마감 상태도 CLOSED로 변경
     * - 지점마감의 거래번호를 가져와 이자 생성
     */
    public void closeByManager() {

        BusinessDateAndBranchId businessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        String currentBusinessDate = businessDateAndBranchId.getBusinessDate();
        ManagerClosingData managerClosingData = getManagerClosingData();

        if (managerClosingData.getClosingDataList().stream().map(ClosingData::getStatus).anyMatch("OPEN"::equals))
            throw new BadRequestException(ErrorCode.REQUIRED_EMPLOYEE_CLOSING);
        if(businessDayCloseMapper.findBranchClosingStatusByDate(businessDateAndBranchId).equals("CLOSED"))
            throw new BadRequestException(ErrorCode.ALREADY_CLOSED_BUSINESS_DAY);



        businessDayCloseMapper.branchDeadlineStatusToClosed(businessDateAndBranchId);
        businessDayService.businessDayStatusToClosed(currentBusinessDate);

        String tradeNumber = businessDayCloseMapper.findClosingTradeNumber(businessDateAndBranchId);
        interestService.createInterest(tradeNumber, businessDateAndBranchId);
    }

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
}
