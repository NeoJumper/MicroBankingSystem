<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/page/trade-cash.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌관리 > </h5>
        <h5>&nbsp 거래 내역 조회 > </h5>
        <h5>&nbsp 계좌 입출금</h5>
    </div>

    <div>
        <%--@declare id="trade-type"--%><label for="trade-type">거래 유형 선택: </label>
        <input type="radio" id="deposit" name="trade-type" value="deposit" checked>
        <label for="deposit">입금</label>
        <input type="radio" id="withdrawal" name="trade-type" value="withdrawal">
        <label for="withdrawal">출금</label>
    </div>

    <div>
        <h3>계좌 정보</h3>
        <hr>
    </div>

    <table class="common-table">
        <tr>
            <th><label for="cash-trade-account-number">계좌번호</label></th>
            <td><input disabled type="text" id="cash-trade-account-number">
                <button id="check-cash-trade-account-btn" class="basic-btn" type="button" data-account-type="deposit" data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회</button>
            </td>
        </tr>

        <tr>
            <th><label for="cash-trade-customer-name">고객명</label></th>
            <td><input disabled type="text" id="cash-trade-customer-name"></td>
        </tr>

        <tr>
            <th><label for="cash-deposit-trade-balance">계좌 잔액</label></th>
            <td><input disabled type="text" id="cash-deposit-trade-balance"></td>
        </tr>

        <tr>
            <th><label for="cash-trade-employee">담당자</label></th>
            <td><input disabled type="text" id="cash-trade-employee"></td>
        </tr>

        <tr>
            <th><label for="cash-trade-registration-date">승인일자</label></th>
            <td><input disabled type="text" id="cash-trade-registration-date"></td>
        </tr>
    </table>

    <div>
        <h3>금액</h3>
        <hr>
    </div>

    <table class="common-table">
        <tbody>
            <tr>
                <th><label for="cash-trade-amount">금액
                </label></th>
                <td><input id="cash-trade-amount" type="text"></td>
            </tr>
        </tbody>
    </table>

    <!-- 출금 시 비밀번호 입력란 추가 -->
    <div id="withdrawal-password-section" style="display:none;">
        <h3>비밀번호 입력</h3>
        <hr>
        <table class="common-table">
            <tbody>
            <tr>
                <th><label for="cash-trade-password">비밀번호</label></th>
                <td><input id="cash-trade-password" type="password"> <button class="basic-btn" id="cash-trade-validate">비밀번호 인증</button></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="row justify-content-center mb-5">
        <button disabled id="cash-trade-submit" class="col-1 basic-btn">현금 거래 승인</button>
    </div>
</div>

<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/trade-cash-result-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/trade-cash.js"></script>
</body>

</html>
