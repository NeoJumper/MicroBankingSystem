<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/modal/otp-input-modal.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="otp-input-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="min-width: 350px;">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">OTP 인증</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="d-flex justify-content-center" id="lock-icon">
                    <i class="bi bi-file-lock"></i>
                </div>
                <div class="mb-4 d-flex justify-content-center align-items-center flex-column">
                    <span>OTP 사용이 설정된 고객입니다.</span>
                    <span>아래 OTP 인증 번호를 입력 후 </span>
                    <span>[인증하기]를 클릭하세요.</span>
                </div>

                <div id="auth-code-container">
                    <input type="text" maxlength="1" class="auth-code-input" />
                    <input type="text" maxlength="1" class="auth-code-input" />
                    <input type="text" maxlength="1" class="auth-code-input" />
                    <input type="text" maxlength="1" class="auth-code-input" />
                    <input type="text" maxlength="1" class="auth-code-input" />
                    <input type="text" maxlength="1" class="auth-code-input" />
                </div>

                <div id="otp-authentication-content2" class="my-4 d-flex justify-content-center align-items-center flex-column">
                    <div>Google Authenticator 어플을 실행한 뒤</div>
                    <div>NEO_Bank 란에 나타나는 숫자 6자리를 입력해주세요</div>

                </div>

                <div class="my-4 d-flex justify-content-center">
                    <button class="basic-btn" id="otp-authenticate-btn">인증하기</button>
                </div>
           </div>
        </div>
    </div>
</div>

</body>
</html>
