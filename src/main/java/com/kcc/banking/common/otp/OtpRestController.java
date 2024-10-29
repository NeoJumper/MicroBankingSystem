package com.kcc.banking.common.otp;

import com.kcc.banking.domain.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class OtpRestController {

    private final CustomerService customerService;

    @GetMapping(value = "/api/common/otp-info")
    public String registerOtp(@RequestParam(value = "customerId") Long customerId) throws Exception {

        // OtpServlet을 사용하여 OTP 키를 생성하고 URL을 얻어옵니다.
        String encodedKey = OtpServlet.generateOtpAndGetKey();

        String user = "newJumper";
        String host = "newBank";

        String qrCodeUrl = OtpServlet.getQRBarcodeURL(user, host, encodedKey);
        String qrCode = OtpServlet.getQRCodeURL("NEO_Bank", encodedKey);

        customerService.registerOtp(customerId, encodedKey);

        return qrCode;
    }

    /**
     * 사용자가 폼에 입력받은 OTP를 검증하는 메서드.
     * @param userCode 사용자 입력값 OTP 코드
     * @param encodedKey 세션에 저장되어 생성된 암호 키
     * @param request HttpServletRequest 객체
     * @param model ModelMap 객체
     * @return 검증 결과에 따라 이동할 페이지의 경로
     */
    @RequestMapping(value = "/otpVerification.do")
    public String adminOtpVerification(@RequestParam("user_code") String userCode,
                                       @RequestParam("encodedKey") String encodedKey,
                                       HttpServletRequest request,
                                       ModelMap model) {
        if (encodedKey == null || encodedKey.isEmpty()) {
            model.addAttribute("error", "OTP 키가 유효하지 않습니다. 다시 시도해 주세요.");
            return "/otpVerificationErrorPage"; // 오류 페이지로 이동
        }

        boolean otpVerificationResult = false;
        try {
            otpVerificationResult = OtpResultServlet.check_code(encodedKey, Long.parseLong(userCode), new Date().getTime() / 30000);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        if (otpVerificationResult) {
            // OTP 검증 성공 시
            return "success"; // 성공 페이지로 리다이렉트 예시
        } else {
            // OTP 검증 실패 시
            model.addAttribute("error", "OTP 검증에 실패했습니다. 다시 시도해 주세요.");
            return "fail"; // 오류 페이지 예시
        }
    }

}
