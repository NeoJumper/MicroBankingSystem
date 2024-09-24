package com.kcc.banking.domain.businessday.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BusinessDayController {

    @GetMapping("/page/businessday-management")
    public String businessDayPage(){
        return "businessday-management";
    }

}
