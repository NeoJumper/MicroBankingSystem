<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <h5>&nbsp 현금 입출금</h5>
    </div>





    <div id = "select-trade-type-button-group" class="button-group my-4">
        <div style="width: 100%">
            <input type="radio" id="deposit-radio-btn" class="select-trade-type-radio-btn" name="trade-type" value="deposit" checked>
            <label for="deposit-radio-btn">현금 입금</label>
        </div>
        <div style="width: 100%">
            <input type="radio" id="withdrawal-radio-btn" class="select-trade-type-radio-btn ms-3" name="trade-type" value="withdrawal">
            <label for="withdrawal-radio-btn">현금 출금</label>
        </div>

    </div>


    <div class="d-flex">
        <h3 id="account-info-h3">입금 계좌 정보</h3>

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
                <button id="cash-trade-account-check-btn" class="basic-btn" type="button"
                        data-bs-toggle="modal" data-bs-target="#search-modal-account">
                    계좌조회
                </button>
            </div>
        </div>
        <div class="account-balance d-flex">
            <div class="me-2">
                계좌잔액<span id="cash-trade-balance" style="margin-left: 20px;">0</span> 원
            </div>
        </div>
    </div>


    <div class="d-flex">
        <h3 id="employee-info-h3">담당자 정보</h3>

        <hr>
    </div>



    <table class="common-table no-margin">
        <tr>
            <th style="width: 18%"><label for="cash-trade-employee">담당자</label></th>
            <td><input type="text" id="cash-trade-employee" value="${employeeName}" disabled></td>
        </tr>
        <tr>
            <th style="width: 18%"><label>현금 잔액</label></th>
            <td>
                <input class="emp-close-prev-cash-balance" type="text" value="0" disabled>
            </td>
        </tr>
    </table>




    <div class="mt-4">
        <h3>거래정보</h3>
    </div>
    <table class="common-table">
        <tr>
            <th><label for="cash-trade-amount">입금액</label></th>
            <td>
                <div><span id="over-account-balance"></span></div>
                <input disabled type="text" style="text-align: right" id="cash-trade-amount"> 원
                <div class="button-group trade-amount-button-group  mb-2">
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
            <th>등록일자</th>
            <td>
                <fmt:parseDate value="${tradeDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedTradeDate" />
                <input type="text" id="cash-trade-registration-date" value="<fmt:formatDate value='${parsedTradeDate}' pattern='yyyy-MM-dd' />" disabled>
            </td>
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
        <button disabled id="otp-authentication-modal-btn" class="col-1 basic-btn" style="display: none;">OTP 인증</button>
    </div>
</div>
<%@ include file="/resources/components/close-overlay.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/trade-cash-result-modal.jsp" %>
<%@ include file="/resources/components/modal/otp-input-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/trade-cash.js"></script>
</body>

</html>
