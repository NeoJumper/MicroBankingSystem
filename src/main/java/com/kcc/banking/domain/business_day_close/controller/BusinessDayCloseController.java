package com.kcc.banking.domain.business_day_close.controller;

import com.kcc.banking.domain.business_day.service.BusinessDayManagementFacade;
import com.kcc.banking.domain.business_day_close.dto.response.EmployeeClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BusinessDayCloseController {

    private final BusinessDayCloseService businessDayCloseService;
    private final BusinessDayManagementFacade businessDayManagementFacade;

    @GetMapping("/page/employee/business-day-close")
    public String businessDayCloseOfEmployeePage(Model model){
        EmployeeClosingData employeeClosingData = businessDayManagementFacade.getEmployeeClosingData();
        model.addAttribute("employeeClosingData", employeeClosingData);
        return "business-day/employee-close";
    }

    @GetMapping("/page/manager/business-day-close")
    public String businessDayCloseOfManagerPage(Model model) {
        ManagerClosingData managerClosingData = businessDayCloseService.getManagerClosingData();
        model.addAttribute("managerClosingData", managerClosingData);
        return "business-day/manager-close";
    }
}
