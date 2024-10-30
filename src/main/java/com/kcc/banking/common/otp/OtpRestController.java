package com.kcc.banking.common.otp;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.domain.customer.dto.response.CustomerDetail;
import com.kcc.banking.domain.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
     */
    @PostMapping(value = "/api/common/otp-authentication")
    public void adminOtpVerification(@ModelAttribute OtpAuthentication otpAuthentication) {

        CustomerDetail customer = customerService.findCustomer(otpAuthentication.getUserId());
        String encodedKey = customer.getOtpKey();
        long userCode = Long.parseLong(otpAuthentication.getUserCode());

        try {
            boolean isValid = OtpResultServlet.check_code(encodedKey, userCode, new Date().getTime() / 30000);
            if (!isValid)
                throw new InvalidKeyException("Invalid OTP");

        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(ErrorCode.NO_SUCH_ALGORITHM);
        } catch (InvalidKeyException e) {
            throw new CustomException(ErrorCode.INVALID_CODE);
        }

    }

}
