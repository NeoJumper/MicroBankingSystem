package com.kcc.banking.domain.business_day_close.controller;


import com.kcc.banking.domain.business_day.service.BusinessDayManagementFacade;
import com.kcc.banking.domain.business_day_close.dto.response.EmployeeClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusinessDayCloseRestController {

    private final BusinessDayCloseService businessDayCloseService;
    private final BusinessDayManagementFacade businessDayManagementFacade;

    @PatchMapping("/api/employee/business-day-close")
    public void businessDayCloseOfEmployee(Model model){
        businessDayManagementFacade.closeByEmployee();
    }

    @PatchMapping("/api/manager/business-day-close")
    public void businessDayCloseOfManager(Model model)
    {
        businessDayManagementFacade.closeByManager();
    }
    @GetMapping("/api/manager/business-day-close")
    public ManagerClosingData getBusinessDayChangeDataOfManager(Model model)
    {
        return businessDayManagementFacade.getManagerClosingData();
    }
}
