package com.kcc.banking.domain.business_day.controller;


import com.kcc.banking.domain.business_day.service.BusinessDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BusinessDayController {

    private final BusinessDayService businessDayService;

    @GetMapping("/page/manager/business-day-management")
    public String businessDayPage(){
        return "business-day/business-day-management";
    }

}
