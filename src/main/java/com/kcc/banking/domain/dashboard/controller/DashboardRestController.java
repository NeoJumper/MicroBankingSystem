package com.kcc.banking.domain.dashboard.controller;


import com.kcc.banking.domain.dashboard.dto.response.AccountOpenRatioChart;
import com.kcc.banking.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardRestController {

    private final DashboardService dashboardService;

    @GetMapping("/api/dashboard/accountOpenRatioChart/{registrantId}")
    public ResponseEntity<List<AccountOpenRatioChart>> accountOpenRatioChart(@PathVariable Long registrantId) {
        List<AccountOpenRatioChart> chartData = dashboardService.getAccountOpenRatioChart(registrantId);
        return ResponseEntity.ok(chartData);
    }
}
