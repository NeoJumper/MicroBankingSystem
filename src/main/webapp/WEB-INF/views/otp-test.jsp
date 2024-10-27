<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />

  </head>

  <body>
    <%@ include file="/resources/components/header.jsp" %>
    <%@ include file="/resources/components/sidebar.jsp" %>
    <div id="main-area">
      <!-- LoginController의 adminOtpVerification 메서드로 요청을 보내도록 합니다. -->
      <form action="${pageContext.request.contextPath}/otpVerification.do" method="post">
        <!-- 사용자에게 OTP 코드를 입력받습니다. -->
        code : <input name="user_code" type="text"><br><br>

        <!-- 숨겨진 필드로 encodedKey를 전달합니다. -->
        <input name="encodedKey" type="hidden" value="${encodedKey}"><br><br>

        <!-- 전송 버튼을 제공합니다. -->
        <input type="submit" value="전송!">
      </form>

      <!-- QR 코드를 표시합니다. -->
      <div>
        <h4>Google Authenticator 앱에서 QR 코드를 스캔해주세요.</h4>

        <!-- QR 코드 이미지를 표시합니다. -->
        <img src="data:image/jpeg;base64, ${qr}" alt="QR Code">
      </div>

    </div>
    <script src="/resources/js/footer.js"></script>
  </body>

</html>
