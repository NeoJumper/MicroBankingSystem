<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>거래 취소</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-transfer-cancel.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌관리 > </h5>
        <h5>&nbsp 거래 내역 조회 > </h5>
        <h5>&nbsp 거래 취소</h5>
    </div>

    <div>
        <h3>출금 계좌 정보</h3>
        <hr>
    </div>

    <!-- 출금 계좌 정보 -->
    <table class="common-table">
        <tbody>
        <tr>
            <th>출금 계좌번호</th>
            <td id="cancel-transfer-withdrawal-acc-id"></td>
        </tr>
        <tr>
            <th>출금 계좌 고객명</th>
            <td id="cancel-transfer-withdrawal-customer-name"></td>
        </tr>
        <tr>
            <th>출금 금액</th>
            <td id="cancel-transfer-withdrawal-amount"></td>
        </tr>
        <tr>
            <th>잔액</th>
            <td id="cancel-transfer-withdrawal-balance"></td>
        </tr>
        <tr>
            <th>거래 날짜</th>
            <td id="cancel-transfer-withdrawal-trade-date"></td>
        </tr>
        <tr>
            <th>설명</th>
            <td id="cancel-transfer-withdrawal-description"></td>
        </tr>
        </tbody>
    </table>

    <div>
        <h3>입금 계좌 정보</h3>
        <hr>
    </div>

    <!-- 입금 계좌 정보 -->
    <table class="common-table">
        <tbody>
        <tr>
            <th>입금 계좌번호</th>
            <td id="cancel-transfer-deposit-acc-id"></td>
        </tr>
        <tr>
            <th>입금 계좌 고객명</th>
            <td id="cancel-transfer-deposit-customer-name"></td>
        </tr>
        <tr>
            <th>입금 금액</th>
            <td id="cancel-transfer-deposit-amount"></td>
        </tr>
        <tr>
            <th>잔액</th>
            <td id="cancel-transfer-deposit-balance"></td>
        </tr>
        </tbody>
    </table>

    <table class="common-table">
        <tbody>
        <tr>
            <th><label for="cancel-transfer-account-password">입금계좌 비밀번호</label></th>
            <td><input id="cancel-transfer-account-password" type="password"><button class="basic-btn" id="cancel-transfer-validate">비밀번호 인증</button></td>
        </tr>
        </tbody>
    </table>

    <div class="row justify-content-center mb-5">
        <button disabled id="cancel-transfer-submit" class="col-1 basic-btn">거래 취소 요청</button>
    </div>
</div>
<%@ include file="/resources/components/close-overlay.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/account-transfer-cancel.js"></script>
</body>

</html>
