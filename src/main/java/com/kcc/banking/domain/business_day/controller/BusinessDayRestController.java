package com.kcc.banking.domain.business_day.controller;

import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.service.BusinessDayManagementFacade;
import com.kcc.banking.domain.business_day.service.BusinessDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BusinessDayRestController {

    private final BusinessDayService businessDayService;
    private final BusinessDayManagementFacade businessDayManagementFacade;

    @GetMapping("/api/common/current-business-day")
    public BusinessDay getCurrentBusinessDay(){
        BusinessDay currentBusinessDay = businessDayService.getCurrentBusinessDay();
        return currentBusinessDay;
    }
    @GetMapping("/api/common/next-business-day")
    public BusinessDay getNextBusinessDay(){
        BusinessDay nextBusinessDay = businessDayService.getNextBusinessDay();
        return nextBusinessDay;
    }
    @PatchMapping("/api/manager/business-day-change")
    public void changeBusinessDay(@RequestBody BusinessDayChange businessDayChange){
        businessDayManagementFacade.changeBusinessDay(businessDayChange);
    }
    @PatchMapping("/api/manager/business-day-reset")
    public void resetBusinessDay(){
        businessDayManagementFacade.resetBusinessDay();
    }

    @GetMapping("/api/manager/full-business-day")
    public List<BusinessDay> getFullBusinessDay(){
        return businessDayManagementFacade.getFullBusinessDay();
    }

}
