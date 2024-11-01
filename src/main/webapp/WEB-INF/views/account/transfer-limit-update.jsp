<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/transfer-limit-update.css"/>

    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">

    <div id="title">
        <h5>예금 관리 ></h5>
        <h5>&nbsp이체한도 조회/변경 </h5>
    </div>

    <div>
        <h3>이체한도 변경</h3>
    </div>
    <table class="common-table">
        <tr>
            <th>계좌번호</th>
            <td class="d-flex">
                <input type="text" id="account-number-input" style="text-align: right" disabled>
                <div>
                    <button id="withdrawal-account-check-btn" class="basic-btn" type="button"
                            data-account-type="withdrawal" data-bs-toggle="modal"
                            data-bs-target="#search-modal-account">
                        계좌조회
                    </button>
                </div>
            </td>

            <th>고객명</th>
            <td><input type="text" id="customer-name-input" style="text-align: right" disabled></td>
        </tr>
        <tr>
        </tr>
        <tr>
            <th>1회 이체한도</th>
            <td>
                <div><span id="over-per-trade-limit"></span></div>
                <input type="text" id="per-trade-limit-input" value="0" style="text-align: right;" class="balance-input">&nbsp원<span class="max-amount-span" style="color: #5F5F5F"></span>

            </td>


            <th>1일 이체한도</th>
            <td>
                <div><span id="over-daily-limit"></span></div>
                <input type="text" id="daily-limit-input" value="0" style="text-align: right;" class="balance-input">&nbsp원<span class="max-amount-span" style="text-align: right; color: #5F5F5F"></span>
            </td>
        </tr>
    </table>
    <div>
        <h3 class="mt-3">비밀번호 인증</h3>

    </div>

    <table class="common-table account-transfer-page-table">
        <tbody>
            <tr>
                <th><label for="account-password">계좌 비밀번호</label></th>
                <td>
                    <input id="account-password" style="text-align: right" type="password">
                    <button class="basic-btn" id="account-transfer-validate">비밀번호 인증</button>
                </td>

            </tr>
        </tbody>
    </table>

    <div  style="text-align:center;">
        <button class="basic-btn col-1" id="account-update-btn" disabled>변경하기</button>
        <button id="otp-authentication-modal-btn" class="col-1 basic-btn" style="display: none;" disabled >OTP 인증</button>
    </div>


    <div>
        <h3>이체한도 안내</h3>
    </div>

    <div class="d-flex justify-content-between">
        <div style="width: 49%">
            <h6 class="my-2" style="font-weight: bold">1일 이체한도</h6>
            <table class="common-table transfer-limit-table">
                <thead>
                <tr>
                    <th rowspan="2">구분</th>
                    <th style="text-align: center; border-left: 1px solid var(--little-dark-gray);" colspan="2">보안등급</th>
                </tr>
                <tr>
                    <th style="width: 40%; border-left: 1px solid var(--little-dark-gray);">1등급</th>
                    <th style="width: 40%; border-left: 1px solid var(--little-dark-gray);">2등급</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>개인</th>
                    <td>5억원 이하</td>
                    <td>1천만원</td>
                </tr>
                <tr>
                    <th>기업</th>
                    <td>50억원 이하</td>
                    <td>-</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div style="width: 49%">
            <h6 class="my-2" style="font-weight: bold">1회 이체한도</h6>
            <table class="common-table transfer-limit-table">
                <thead>
                <tr>
                    <th rowspan="2">구분</th>
                    <th style="text-align: center; border-left: 1px solid var(--little-dark-gray);" colspan="2">보안등급</th>
                </tr>
                <tr>
                    <th style="width: 40%; border-left: 1px solid var(--little-dark-gray);" >1등급</th>
                    <th style="width: 40%; border-left: 1px solid var(--little-dark-gray);">2등급</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>개인</th>
                    <td>1억원 이하</td>
                    <td>5백만원</td>
                </tr>
                <tr>
                    <th>기업</th>
                    <td>10억원 이하</td>
                    <td>-</td>
                </tr>
                </tbody>
            </table>
        </div>


    </div>



</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/otp-input-modal.jsp" %>
<script src="/resources/js/page/transfer-limit-update.js"></script>
<script src="/resources/js/footer.js"></script>

</body>
</html>
