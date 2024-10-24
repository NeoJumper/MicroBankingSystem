package com.kcc.banking.domain.branch.service;

import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.branch.dto.response.*;
import com.kcc.banking.domain.branch.mapper.DashboardMapper;
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


    // 일별 거래량 조회 (오늘 날짜가 포함된 한 달간)
    public List<DailyTransactionVolumeChart> getDailyTransactionVolume(String today) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        return dashboardMapper.findDailyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), today);
    }

    // 주간별 거래량 조회 (12주)
    public List<WeeklyTransactionVolumeChart> getWeeklyTransactionVolume(String today) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        return dashboardMapper.findWeeklyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), today);
    }

    // 월별 거래량 조회 (1월부터 12월까지)
    public List<MonthlyTransactionVolumeChart> getMonthlyTransactionVolume(String today) {
        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        return dashboardMapper.findMonthlyTransactionVolume(currentBusinessDateAndBranchId.getBranchId(), today);
    }

    // 직원별 거래량 비교
    public List<EmployeeTransactionVolumeChart> getEmployeeTransactionByBranchId(Long branchId) {
        return dashboardMapper.findEmployeeTransactionByBranchId(branchId);
    }

    public List<EmployeeTransactionVolumeChart> getEmployeeTransactionTypes(Long branchId) {
        return dashboardMapper.findEmployeeTransactionTypes(branchId);
    }
}

