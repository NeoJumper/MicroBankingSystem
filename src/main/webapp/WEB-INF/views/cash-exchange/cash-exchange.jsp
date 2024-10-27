<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/cash-exchange.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>시재 관리 ></h5>
        <h5>&nbsp시재금 거래</h5>
    </div>

    <div class="mb-3" style="display: flex; gap: 5px">
        <label class="radio-label" style="width: 50%">
            <input type="radio" name="exchangeType" value="HANDOVER" onclick="updateTransactionTitle()" checked>
            <span data-label="인도 거래" style="width: 100%; text-align: center; padding: 10px 15px;">인도 거래</span>
        </label>
        <label class="radio-label" style="width: 50%">
            <input type="radio" name="exchangeType" value="RECEIVE" onclick="updateTransactionTitle()">
            <span data-label="인수 거래" style="width: 100%; text-align: center; padding: 10px 15px;">인수 거래</span>
        </label>
    </div>


    <h3 id="transactionTitle" class="mt-3">인도 거래</h3>

    <div class="d-flex align-items-center mb-3">
        <input type="text" style="width: 30%" id="customer-id-text" placeholder="사원 번호를 입력하세요">
        <button id="employee-search-btn" type="button" class="basic-btn"
                style="display: flex; align-items: center; justify-content: center">
            <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
        </button>
    </div>

    <table class="common-table" id="selected-employee-table">
        <thead>
        <tr>
            <th>사번</th>
            <th>이름</th>
            <th>현재 시재금</th>
            <th>거래금</th>
            <th>거래 후 시재금</th>
        </tr>
        </thead>
        <tbody>
        <tr id="empty-message">
            <td colspan="5" style="text-align: center; color: gray; border-bottom: none; height: 250px">
                행원 검색 버튼을 눌러 행원을 선택하여 주십시오
            </td>
        </tr>
        </tbody>
    </table>



    <table class="common-table">
        <tr>
            <th>잔여 시재금</th>
            <td>
                <input id="lastManagerCash"
                       type="text"
                       value="<fmt:formatNumber value='${currentCashBalanceForManager}' type='number'/>"
                       disabled>
                원
            </td>
            <th>거래 후 시재금</th>
            <td>
                <input id="afterManagerCash"
                       type="text"
                       value="<fmt:formatNumber value='${currentCashBalanceForManager}' type='number'/>"
                       disabled>
                원
            </td>
        </tr>
    </table>

    <div style="display: flex; justify-content: center">
        <button id="cash-exchange-accept" class="basic-btn">시재금 거래 승인</button>
    </div>
</div>
<%@ include file="/resources/components/modal/employee-search-modal.jsp" %>
<%@ include file="/resources/components/modal/cash-exchange-result-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/cash-exchange.js"></script>
</body>
</html>
