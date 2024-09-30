package com.kcc.banking.domain.businessday.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BusinessDayController {

    @GetMapping("/page/manager/business-day-management")
    public String businessDayPage(){
        return "business-day/business-day-management";
    }

    @GetMapping("/page/employee/business-day-close")
    public String businessDayCloseOfEmployeePage(){
        return "business-day/employee-close";
    }

    @GetMapping("/page/manager/business-day-close")
    public String businessDayCloseOfManagerPage(){
        return "business-day/manager-close";
    }


}
