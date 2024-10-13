package com.kcc.banking.domain.dashboard.controller;


import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import com.kcc.banking.domain.dashboard.dto.response.YearlyTransactionComparisonChart;
import com.kcc.banking.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
