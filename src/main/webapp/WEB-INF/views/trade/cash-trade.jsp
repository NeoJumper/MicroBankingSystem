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

    <div id = "reserve-button-group" class="button-group mb-2">
        <label>거래 유형 &nbsp;&nbsp;</label>
        <input type="radio" id="deposit" name="trade-type" value="deposit" checked>
        <label for="deposit">입금</label>
        <input class="ms-3" type="radio" id="withdrawal" name="trade-type" value="withdrawal">
        <label for="withdrawal">출금</label>
    </div>

    <div>
        <h3>계좌 정보</h3>
        <hr>
    </div>

    <%--계좌선택--%>
    <div id="select-account-form">
        <div class="account-info">
            <div>
                <span id="cash-trade-customer-name"></span>
                <span id="cash-trade-product-name"></span>
                <br>
                <span id="cash-trade-account-number">계좌를 선택해주세요.</span>
            </div>
            <div>
                <button id="check-cash-trade-account-btn" class="basic-btn" type="button"
                        data-bs-toggle="modal" data-bs-target="#search-modal-account">
                    계좌조회
                </button>
            </div>
        </div>
        <div class="account-balance">
            계좌잔액 <span id="cash-deposit-trade-balance"> &nbsp  &nbsp  &nbsp  &nbsp  &nbsp 0</span> 원 | 이체가능금액 <span
                id="transferable-amount"> &nbsp  &nbsp  &nbsp  &nbsp  &nbsp 0</span> 원
        </div>
    </div>

    <div>
        <h3>거래정보</h3>
    </div>
    <table class="common-table">
        <tr>
            <th><label for="cash-trade-amount">이체금액</label></th>
            <td>
                <div><span id="over-account-balance"></span></div>
                <input disabled type="text" id="cash-trade-amount"> 원
                <div class="button-group">
                    <button type="button" class="amount-btn" disabled>100만</button>
                    <button type="button" class="amount-btn" disabled>50만</button>
                    <button type="button" class="amount-btn" disabled>10만</button>
                    <button type="button" class="amount-btn" disabled>5만</button>
                    <button type="button" class="amount-btn" disabled>1만</button>
                    <button type="button" class="amount-btn" disabled>전액</button>
                </div>
            </td>
        </tr>
        <tr>
            <th><label for="description">비고</label></th>
            <td><input type="text" id="description"></td>
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

    <!-- 출금 시 비밀번호 입력란 추가 -->
    <div id="withdrawal-password-section" style="display:none;">
        <h3>비밀번호 인증</h3>
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
