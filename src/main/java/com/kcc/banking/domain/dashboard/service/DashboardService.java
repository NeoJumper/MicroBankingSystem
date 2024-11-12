package com.kcc.banking.domain.dashboard.service;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.dashboard.dto.response.*;
import com.kcc.banking.domain.dashboard.mapper.DashboardMapper;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;
    private final CommonService commonService;

    public List<AccountOpenRatioChart> getAccountOpenRatioChart() {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        return dashboardMapper.findAccountOpenRatioByRegistrantId(currentBusinessDateAndEmployeeId.getEmployeeId());
    }

    public List<YearlyTransactionComparisonChart> getYearlyTransactionComparison(Map<String, Object> params) {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        params.put("registrantId", currentBusinessDateAndEmployeeId.getEmployeeId());
        return dashboardMapper.findYearlyTransactionComparison(params);
    }

    // 특정 날짜에 대한 일별 거래 유형 조회
    public List<DailyTransactionTypeChart> getDailyTransactionTypes(Map<String, Object> params) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        params.put("branchId", currentBusinessDateAndBranchId.getBranchId());
        return dashboardMapper.findDailyTransactionTypes(params);
    }

// DashboardService.java
    public List<TransactionTypeCount> getCurrentMonthTransactionTypes(String today) {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        Long employeeId = currentBusinessDateAndEmployeeId.getEmployeeId();
        String businessDate = currentBusinessDateAndEmployeeId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findCurrentMonthTransactionTypes(employeeId, businessDate);
    }




    // 일별 거래량 조회 (오늘 날짜가 포함된 한 달간)
    public List<DailyTransactionVolumeChart> getDailyTransactionVolume() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        String businessDate = currentBusinessDateAndBranchId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findDailyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), businessDate);
    }

    // 주간별 거래량 조회 (12주)
    public List<WeeklyTransactionVolumeChart> getWeeklyTransactionVolume() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        String businessDate = currentBusinessDateAndBranchId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findWeeklyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), businessDate);
    }

    // 월별 거래량 조회 (1월부터 12월까지)
    public List<MonthlyTransactionVolumeChart> getMonthlyTransactionVolume() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        String businessDate = currentBusinessDateAndBranchId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findMonthlyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), businessDate);
    }

    // 직원별 거래량 비교
    public List<EmployeeTransactionVolumeChart> getEmployeeTransactionByBranchId(Long branchId) {
        return dashboardMapper.findEmployeeTransactionByBranchId(branchId);
    }

    public List<EmployeeTransactionVolumeChart> getEmployeeTransactionTypes(Long branchId) {
        return dashboardMapper.findEmployeeTransactionTypes(branchId);
    }


    public List<DailyTransactionVolumeChart> getEmployeeDailyTransactionVolume() {
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();
        Long employeeId = currentBusinessDateAndEmployeeId.getEmployeeId();
        String businessDate = currentBusinessDateAndEmployeeId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findEmployeeDailyTransactionVolume(employeeId, businessDate);
    }

    public List<TransactionTypeCount> getMonthlyTransactionTypes() {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        Long branchId = Long.valueOf(currentBusinessDateAndBranchId.getBranchId());
        String businessDate = currentBusinessDateAndBranchId.getBusinessDate().substring(0, 10);
        return dashboardMapper.findMonthlyTransactionTypes(branchId, businessDate);
    }
}

