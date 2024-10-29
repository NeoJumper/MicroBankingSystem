<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="otp-detail-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="min-width: 580px;">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">OTP 인증 키 안내</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>


            <div class="modal-body">
                <div id="comment-area">
                    <ul>
                        <li>1인 1기기만 이용할 수 있습니다.</li>
                        <li>기기 변경, 어플 삭제 후 재설치, 데이터 초기화 등을 했다면 재발급해 주세요.</li>
                        <li>
                            OTP를 발급 받으셨으므로 이체 금액 증액이 가능합니다.<br>
                            <br>
                            <span style="font-weight: bold">개인 &nbsp </span> | &nbsp 1회 1억원, 1일 5억원<br>
                            <span style="font-weight: bold">기업 &nbsp </span> | &nbsp 1회 10억원, 1일 50억원<br>
                            <br>
                        </li>
                        <li>계좌 정보 변경, 입출금 이체 시에 OTP 인증이 되어야만 서비스 이용이 가능합니다.</li>
                    </ul>
                </div>
                <hr>
                <table class="common-table">
                    <tr>
                        <th style="text-align: center; align-items: center">QR코드 스캔</th>
                        <td style="text-align: center; align-items: center">
                            <img src="data:image/jpeg;base64, ${qr}" alt="QR Code">
                        </td>
                    </tr>

                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
