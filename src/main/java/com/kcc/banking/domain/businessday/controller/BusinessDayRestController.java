package com.kcc.banking.domain.businessday.controller;

import com.kcc.banking.domain.businessday.dto.CurrentBusinessDay;
import com.kcc.banking.domain.businessday.service.BusinessDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusinessDayRestController {

    private final BusinessDayService businessDayService;

    @GetMapping("/api/current-business-day")
    public CurrentBusinessDay getCurrentBusinessDay(){
        CurrentBusinessDay currentBusinessDay = businessDayService.getCurrentBusinessDay();
        return currentBusinessDay;
    }


}
