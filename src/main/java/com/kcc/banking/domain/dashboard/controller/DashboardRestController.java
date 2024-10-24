package com.kcc.banking.domain.dashboard.controller;


import com.kcc.banking.domain.dashboard.dto.response.*;
import com.kcc.banking.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class DashboardRestController {

    private final DashboardService dashboardService;

    @GetMapping("/api/dashboard/accountOpenRatioChart")
    public ResponseEntity<List<AccountOpenRatioChart>> accountOpenRatioChart() {
        List<AccountOpenRatioChart> chartData = dashboardService.getAccountOpenRatioChart();
        return ResponseEntity.ok(chartData);
    }

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
        String today = "2024-02-20"; // 변경해야 할 날짜
        List<DailyTransactionVolumeChart> chartData = dashboardService.getDailyTransactionVolume(today);
        return ResponseEntity.ok(chartData);
    }

    // 주간별 거래량 조회 (오늘 날짜 포함한 주 + 12주)
    @GetMapping("/api/dashboard/weeklyTransactionVolume")
    public ResponseEntity<List<WeeklyTransactionVolumeChart>> getWeeklyTransactionVolume() {
        String today = "2024-02-20"; // 변경해야 할 날짜
        List<WeeklyTransactionVolumeChart> chartData = dashboardService.getWeeklyTransactionVolume(today);
        return ResponseEntity.ok(chartData);
    }

    // 월별 거래량 조회 (1월부터 12월까지)
    @GetMapping("/api/dashboard/monthlyTransactionVolume")
    public ResponseEntity<List<MonthlyTransactionVolumeChart>> getMonthlyTransactionVolume() {
        String today = "2024-02-20"; // 변경해야 할 날짜
        List<MonthlyTransactionVolumeChart> chartData = dashboardService.getMonthlyTransactionVolume(today);
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

