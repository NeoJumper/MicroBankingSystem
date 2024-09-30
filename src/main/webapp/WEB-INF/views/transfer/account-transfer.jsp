<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>계좌관리 >
        계좌이체 >
        즉시이체</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-transfer.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
        <div>
            <h5>계좌관리 > </h5>
            <h5>&nbsp;계좌이체 > </h5>
            <h5>&nbsp;즉시이체</h5>

        </div>
        <div>
            <h3>출금계좌정보</h3>
            <hr>
        </div>
        <table class="common-table">
            <tr>
                <th><label for="withdrawal-account-number">출금계좌번호</label></th>
                <td><input disabled type="text" id="withdrawal-account-number">
                    <button id="check-withdrawal-account-btn" class="update-btn" type="button" data-account-type="withdrawal" data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회</button>
                </td>
            </tr>
            <tr>
                <th><label for="withdrawal-product-name">상품명</label></th>
                <td><input disabled type="text" id="withdrawal-product-name"></td>
            </tr>
            <tr>
                <th><label for="withdrawal-customer-name">고객명</label></th>
                <td><input disabled type="text" id="withdrawal-customer-name"></td>
            </tr>
            <tr>
                <th><label for="transfer-amount">이체금액</label></th>
                <td>
                    <div><span id="over-account-balance"></span></div>
                    <input disabled type="text" id="transfer-amount"> 원
                    <label id="account-balance-label" style="display: none">
                        | 계좌 잔액: <span id="account-balance"></span> 원
                </label>
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
                <th><label for="execution-date">이체 실행일자</label></th>
                <td><input disabled type="date" id="execution-date"></td>
            </tr>
            <tr>
                <th><label for="description">비고</label></th>
                <td><input type="text" id="description"></td>
            </tr>
        </table>
        <div>
            <h3 class="mt-3">입금계좌정보</h3>
            <hr>
        </div>
        <table class="common-table">
            <tbody>
            <tr>
                <th><label for="deposit-account-number">입금계좌번호</label></th>
                <td><input disabled type="text" id="deposit-account-number">
                    <button id="check-deposit-account-btn" class="update-btn" type="button" data-account-type="deposit" data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회</button>
                </td>
            </tr>
            <tr>
                <th><label for="deposit-customer-name">고객명</label></th>
                <td><input disabled type="text" id="deposit-customer-name"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <h3 class="mt-3">출금계좌 비밀번호</h3>
            <hr>
        </div>
        <table class="common-table">
            <tbody>
            <tr>
                <th><label>출금계좌 비밀번호</label></th>
                <td><input type="password">   <button>비밀번호 인증</button></td>
            </tr>
            </tbody>
        </table>
    <div class="row justify-content-center mb-5">
        <button id="account-transfer-submit" class="col-1 update-btn">이체하기</button>
    </div>
    </div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/transfer-result-modal.jsp" %>
<script src="/resources/js/page/account-transfer.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>