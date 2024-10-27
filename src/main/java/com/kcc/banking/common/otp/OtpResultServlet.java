package com.kcc.banking.common.otp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class OtpResultServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String user_codeStr = req.getParameter("user_code");
        long user_code = Integer.parseInt(user_codeStr);

        String encodeKey = req.getParameter("encodeKey");

        long l = new Date().getTime();
        long ll = l / 30000;

        boolean check_code = false;
        try {
            check_code = check_code(encodeKey, user_code, ll);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        System.out.println("check_code : " + check_code);
    }

    protected static boolean check_code(String secret, long code, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        Base32 codec = new Base32();
        byte[] decodeKey = codec.decode(secret);

        int window = 3;

        for(int i = -window; i <= window; ++i) {
            long hash = verify_code(decodeKey, t + i);

            if(hash == code){
                return true;
            }
        }
        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;

        for(int i = 8; i-- > 0; value >>>= 8){
            data[i] = (byte) value;
        }


        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");

        mac.init(signKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[20 - 1] & 0xF;

        long truncatedHash = 0;

        for(int i = 0; i < 4; ++i) {
            truncatedHash  <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;

        return (int) truncatedHash;
    }

}
