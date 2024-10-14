package com.kcc.banking.domain.dashboard.controller;


import ch.qos.logback.core.model.Model;
import com.kcc.banking.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;


    @GetMapping("/page/employee/dashboard")
    public String employeeDashboard() {
        return "dashboard/employee-dashboard";
    }

    @GetMapping("/page/manager/dashboard")
    public String managerDashboard() {
        return "dashboard/manager-dashboard";
    }
}
