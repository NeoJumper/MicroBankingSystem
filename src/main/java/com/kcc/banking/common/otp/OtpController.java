package com.kcc.banking.common.otp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Controller
public class OtpController {

    @GetMapping(value = "/page/common/otp-register")
    public String registerOtp(HttpServletRequest request, ModelMap model) throws Exception {
        return "customer/otp-register";
    }
}
