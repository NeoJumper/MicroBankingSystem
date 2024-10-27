package com.kcc.banking.common.otp;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base32;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class OtpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encodeKey = generateOtpAndGetKey();

        String url = getQRBarcodeURL("gj","aaa",encodeKey);

        String view = "/WEB-INF/views/otp.jsp";

        req.setAttribute("encodeKey", encodeKey);
        req.setAttribute("url", url);

        req.getRequestDispatcher(view).forward(req,resp);

    }
    public static String generateOtpAndGetKey() {
        byte[] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);

        Base32 codec = new Base32();

        byte[] secretKey = Arrays.copyOf(buffer, 5);

        String generateKey = new String(codec.encode(secretKey));
        return generateKey;
    }

    public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "https://chart.googleapis.com/chart?cht=qr&chs=300x300&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";

        return String.format(format, user, host, secret);
    }

    public static String getQRCodeURL(String displayName, String secret) throws Exception {

            String format = "otpauth://totp/" + URLEncoder.encode(displayName,
                    String.valueOf(StandardCharsets.UTF_8))
                    .replace("+","%20")
                    + "?secret=" + secret;

            return generateQRCodeImage(format);

    }
    public static String generateQRCodeImage(String barcodeText) throws Exception{

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream);

        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
    }
}
