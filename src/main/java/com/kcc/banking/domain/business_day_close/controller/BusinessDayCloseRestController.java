package com.kcc.banking.domain.business_day_close.controller;


import com.kcc.banking.domain.business_day.service.BusinessDayManagementFacade;
import com.kcc.banking.domain.business_day_close.dto.request.VaultCashRequest;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class BusinessDayCloseRestController {

    private final BusinessDayCloseService businessDayCloseService;
    private final BusinessDayManagementFacade businessDayManagementFacade;

    @PatchMapping("/api/employee/business-day-close")
    public void businessDayCloseOfEmployee(@RequestBody VaultCashRequest vaultCashRequest) {
        businessDayManagementFacade.closeByEmployee(vaultCashRequest);
    }

    @PatchMapping("/api/manager/business-day-close")
    public void businessDayCloseOfManager(@RequestBody VaultCashRequest vaultCashRequest) {
        businessDayManagementFacade.closeByManager(vaultCashRequest);
    }

    @GetMapping("/api/manager/business-day-close")
    public ManagerClosingData getBusinessDayChangeDataOfManager() {
        return businessDayCloseService.getManagerClosingData();
    }

    @GetMapping("/api/employee/cash-balance")
    public BigDecimal getEmployeeCashBalanceOfCurrent() {
        BigDecimal employeeCashBalance = businessDayCloseService.getEmployeeCashBalance();
        return employeeCashBalance;
    }

    @GetMapping("/api/common/business-day-close/status")
    public String getBusinessDayCloseStatus() {
        return businessDayCloseService.getEmployeeStatus();
    }
}
