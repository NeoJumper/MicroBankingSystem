<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/otp-register.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>고객 관리 > </h5>
        <h5>&nbsp OTP 발급/재발급 </h5>
    </div>
    <div>
        <h3>고객 정보</h3>
    </div>
    <table class="common-table account-open-table">
        <tr>
            <th>고객번호</th>
            <td>
                <input type="text" id="customer-id-input" readonly >
                <button data-bs-toggle="modal" data-bs-target="#search-customer-modal"  type="button" id="customer-id-search-btn" class="basic-btn">
                    <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
                </button>
            </td>
            <th>고객명</th>
            <td><input type="text" id="customer-name-input" disabled></td>
        </tr>
        <tr>
            <th>휴대폰 번호</th>
            <td><input type="text" id="customer-phone-number-input" disabled></td>
            <th >보안등급</th>
            <td><input type="text" id="customer-security-level-input" disabled></td>
        </tr>


    </table>
    <div>
        <h3>본인 인증</h3>
    </div>
    <table class="common-table" style="margin-bottom: 10px !important;">

        <tr>
            <th>인증 번호</th>
            <td >
                <div class="d-flex">
                    <input type="text" id="phone-authentication-number" disabled maxlength="6">
                    <div class="d-flex justify-content-end">
                        <button id="authentication-number-send-btn" class="basic-btn">인증번호 전송</button>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button id="authentication-number-check-btn" class="basic-btn hidden">인증번호 확인</button>
                    </div>
                </div>
                <div class="d-flex justify-content-between" style="width: 65%">
                    <div id="timer">제한 시간 : 3:00</div>
                    <div class="d-flex justify-content-end">
                        <button id="authentication-number-resend-btn" class=" hidden">인증번호 재전송</button>
                    </div>
                </div>

            </td>
        </tr>
    </table>

    <div class="d-flex justify-content-center mt-4 mb-4">
        <button class="basic-btn px-2" id="otp-register-btn" disabled>OTP 발급</button>
    </div>

    <div>
        <h3>이체한도 안내</h3>
    </div>
    <h6 class="my-2" style="font-weight: bold">1일 이체한도</h6>
    <table class="common-table transfer-limit-table">
        <thead>
        <tr>
            <th rowspan="2">구분</th>
            <th style="text-align: center; border-left: 1px solid var(--little-dark-gray);" colspan="2">보안등급</th>
            <th style="border-left: 1px solid var(--little-dark-gray);" rowspan="2">1일 이체한도</th>
        </tr>
        <tr>
            <th style="width: 16%; border-left: 1px solid var(--little-dark-gray);">1등급</th>
            <th style="width: 16%; border-left: 1px solid var(--little-dark-gray);">2등급</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>개인</th>
            <td>5억원 이하</td>
            <td>1천만원</td>
            <td>
                <ul>
                    <li>미지정시 1천만원 이하</li>
                    <li>추가약정시 5억원 이하 지정 가능</li>
                </ul>
            </td>
        </tr>
        <tr>
            <th>기업</th>
            <td>50억원 이하</td>
            <td>-</td>
            <td>
                <ul>
                    <li>추가약정시 50억원 이하 지정 가능</li>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>

    <h6 class="my-2" style="font-weight: bold">1회 이체한도</h6>
    <table class="common-table transfer-limit-table">
        <thead>
        <tr>
            <th rowspan="2">구분</th>
            <th style="text-align: center; border-left: 1px solid var(--little-dark-gray);" colspan="2">보안등급</th>
            <th style="border-left: 1px solid var(--little-dark-gray);" rowspan="2">1회 이체한도</th>
        </tr>
        <tr>
            <th style="width: 16%; border-left: 1px solid var(--little-dark-gray);" >1등급</th>
            <th style="width: 16%; border-left: 1px solid var(--little-dark-gray);">2등급</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>개인</th>
            <td>1억원 이하</td>
            <td>5백만원</td>
            <td>
                <ul>
                    <li>미지정시 5백만원 이하</li>
                    <li>추가약정시 1억원 이하 지정 가능</li>
                </ul>
            </td>
        </tr>
        <tr>
            <th>기업</th>
            <td>10억원 이하</td>
            <td>-</td>
            <td>
                <ul>
                    <li>추가약정시 10억원 이하 지정 가능</li>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>

    <h6 class="my-2" style="font-weight: bold">보안등급별 거래이용수단</h6>
    <table class="common-table transfer-limit-table">
        <thead>
        <tr>
            <th style="width: 25%">보안등급</th>
            <th style="border-left: 1px solid var(--little-dark-gray);" >거래이용수단</th>
        </tr>

        </thead>
        <tbody>
        <tr>
            <th>1등급</th>
            <td>OTP 인증 + 계좌 비밀번호 인증</td>

        </tr>
        <tr>
            <th>2등급</th>
            <td>계좌 비밀번호 인증</td>

        </tr>
        </tbody>
    </table>

</div>

<%@ include file="/resources/components/modal/otp-detail-modal.jsp" %>
<%@ include file="/resources/components/modal/search-modal-customer.jsp" %>
<script src="/resources/js/page/otp-register.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>