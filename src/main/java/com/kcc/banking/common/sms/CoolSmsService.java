package com.kcc.banking.common.sms;

import com.kcc.banking.common.exception.CustomException;
import com.kcc.banking.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoolSmsService {



    //@Value("${coolsms.api.key}")
    private String apiKey;

    //@Value("${coolsms.api.secret}")
    private String apiSecret;

    //@Value("${coolsms.api.number}")
    private String fromPhoneNumber;

    // 단일 메시지 발송
    public void sendSMS(String to, HttpServletRequest request){

/*        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(
                apiKey,
                apiSecret,
                "https://api.coolsms.co.kr");
        String randomNumber = generateRandomNumber();
        Message message = new Message();
        message.setFrom(fromPhoneNumber);
        message.setTo(to);
        log.info(to);
        log.info(fromPhoneNumber);
        message.setText("인증번호는 [" + randomNumber + "] 입니다.");

        try {
            // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
            messageService.send(message);
            HttpSession session = request.getSession();
            session.setAttribute(to, randomNumber);
            session.setMaxInactiveInterval(60 * 3); // 세션만료시간 3분
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }*/

        String randomNumber = generateRandomNumber();
        HttpSession session = request.getSession();
        session.setAttribute(to, randomNumber);
        session.setMaxInactiveInterval(60 * 3); // 세션만료시간 3분
        log.info("수신자 : " + to);
        log.info("인증번호 : " + randomNumber);

    }

    public void verifySMS(PhoneNumber phoneNumber, HttpServletRequest request){
        String certificationNumber = String.valueOf(request.getSession().getAttribute(phoneNumber.getNumber()));

        if(certificationNumber.isEmpty()){
            log.info("인증번호가 만료되었습니다.");
            throw new CustomException(ErrorCode.EXPIRED_CERTIFICATION_NUMBER);
        }
        if(!certificationNumber.equals(phoneNumber.getCertificationNumber()))
        {
            log.info("인증번호가 일치하지 않습니다.");
            throw new CustomException(ErrorCode.INVALID_CERTIFICATION_NUMBER);
        }

        log.info("인증이 완료되었습니다.");
    }

    // 랜덤한 4자리 숫자 생성 메서드
    private String generateRandomNumber() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            numStr.append(rand.nextInt(10));
        }
        return numStr.toString();
    }
}