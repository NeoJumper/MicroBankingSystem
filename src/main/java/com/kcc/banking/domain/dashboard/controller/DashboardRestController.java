package com.kcc.banking.domain.dashboard.controller;


import com.kcc.banking.domain.dashboard.dto.response.*;
import com.kcc.banking.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class DashboardRestController {

    private final DashboardService dashboardService;

    // - 직원용 계좌 개설 차트
    @GetMapping("/api/dashboard/accountOpenRatioChart")
    public ResponseEntity<List<AccountOpenRatioChart>> accountOpenRatioChart() {
        List<AccountOpenRatioChart> chartData = dashboardService.getAccountOpenRatioChart();
        return ResponseEntity.ok(chartData);
    }

    // - 직원용 작년-올해 나의 거래량 비교
    @GetMapping("/api/dashboard/yearlyTransactionComparison")
    public ResponseEntity<List<YearlyTransactionComparisonChart>> getYearlyTransactionComparison(
            @RequestParam("lastYear") String lastYear,
            @RequestParam("currentYear") String currentYear) {

        Map<String, Object> params = new HashMap<>();
        params.put("lastYear", lastYear);
        params.put("currentYear", currentYear);

        List<YearlyTransactionComparisonChart> comparisonData = dashboardService.getYearlyTransactionComparison(params);
        return ResponseEntity.ok(comparisonData);
    }

    // - 직원용 이번 달 나의 거래 유형 확인
    @GetMapping("/api/dashboard/currentMonthTransactionTypes")
    public ResponseEntity<List<TransactionTypeCount>> getCurrentMonthTransactionTypes() {
//        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String today = "2024-02-20";
        List<TransactionTypeCount> data = dashboardService.getCurrentMonthTransactionTypes(today);
        return ResponseEntity.ok(data);
    }

    // - 직원용 이번 달 일별 거래량 추이
    @GetMapping("/api/dashboard/dailyEmployeeTransactionVolume")
    public ResponseEntity<List<DailyTransactionVolumeChart>> getEmployeeDailyTransactionVolume() {
        List<DailyTransactionVolumeChart> data = dashboardService.getEmployeeDailyTransactionVolume();
        return ResponseEntity.ok(data);
    }


    // 특정 날짜에 대한 일별 거래 유형 조회
    @GetMapping("/api/dashboard/dailyTransactionTypes")
    public ResponseEntity<List<DailyTransactionTypeChart>> getDailyTransactionTypes(
            @RequestParam("date") String date) {

        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        List<DailyTransactionTypeChart> chartData = dashboardService.getDailyTransactionTypes(params);
        return ResponseEntity.ok(chartData);
    }

    // 일별 거래량 조회 (오늘 날짜 포함된 월의 데이터)
    @GetMapping("/api/dashboard/dailyTransactionVolume")
    public ResponseEntity<List<DailyTransactionVolumeChart>> getDailyTransactionVolume() {
        List<DailyTransactionVolumeChart> chartData = dashboardService.getDailyTransactionVolume();
        return ResponseEntity.ok(chartData);
    }

    // 주간별 거래량 조회 (오늘 날짜 포함한 주 + 12주)
    @GetMapping("/api/dashboard/weeklyTransactionVolume")
    public ResponseEntity<List<WeeklyTransactionVolumeChart>> getWeeklyTransactionVolume() {
        List<WeeklyTransactionVolumeChart> chartData = dashboardService.getWeeklyTransactionVolume();
        return ResponseEntity.ok(chartData);
    }

    // 월별 거래량 조회 (1월부터 12월까지)
    @GetMapping("/api/dashboard/monthlyTransactionVolume")
    public ResponseEntity<List<MonthlyTransactionVolumeChart>> getMonthlyTransactionVolume() {
        List<MonthlyTransactionVolumeChart> chartData = dashboardService.getMonthlyTransactionVolume();
        return ResponseEntity.ok(chartData);
    }

    // 직원별 거래량 비교 조회
    @GetMapping("/api/dashboard/employeeTransactionByBranch")
    public ResponseEntity<List<EmployeeTransactionVolumeChart>> getEmployeeTransactionByBranch(
            @RequestParam("branchId") Long branchId) {
        List<EmployeeTransactionVolumeChart> chartData = dashboardService.getEmployeeTransactionByBranchId(branchId);
        return ResponseEntity.ok(chartData);
    }

    // 직원별 거래 유형 비교
    @GetMapping("/api/dashboard/employeeTransactionTypes")
    public ResponseEntity<List<EmployeeTransactionVolumeChart>> getEmployeeTransactionTypes(@RequestParam("branchId") Long branchId) {
        List<EmployeeTransactionVolumeChart> chartData = dashboardService.getEmployeeTransactionTypes(branchId);
        return ResponseEntity.ok(chartData);
    }
}

