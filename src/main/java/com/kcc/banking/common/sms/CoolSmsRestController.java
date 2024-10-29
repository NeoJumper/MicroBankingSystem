package com.kcc.banking.common.sms;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;

@RestController
@RequestMapping("/api/common/sms")
@RequiredArgsConstructor
public class CoolSmsRestController {


    private final CoolSmsService coolSmsService;

    @PostMapping("/send")
    public void sendSms(@ModelAttribute PhoneNumber phoneNumber, HttpServletRequest request) {
        coolSmsService.sendSMS(phoneNumber.getNumber(), request);
    }

    @GetMapping("/verify")
    public void verifySms(@ModelAttribute PhoneNumber phoneNumber, HttpServletRequest request) {
        coolSmsService.verifySMS(phoneNumber, request);
    }
}
