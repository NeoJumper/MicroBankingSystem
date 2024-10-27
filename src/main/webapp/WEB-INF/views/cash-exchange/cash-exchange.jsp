<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css" />
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>시재 관리 ></h5>
        <h5>&nbsp시재금 거래</h5>
    </div>

    <div>
        <label>
            <input type="radio" name="transactionType" value="handover" onclick="updateTransactionTitle()" checked>
            인도 거래
        </label>
        <label>
            <input type="radio" name="transactionType" value="receive" onclick="updateTransactionTitle()">
            인수 거래
        </label>
    </div>

    <div class="d-flex align-items-center">
        <input style="width:30%;" type="text" id="customer-id-text" placeholder="사원 번호를 입력하세요">
        <button id="employee-search-btn" type="button" class="basic-btn" style="display: flex; align-items: center; justify-content: center">
            <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
        </button>
    </div>

    <h3 id="transactionTitle">인도 거래</h3>

    <table class="common-table" >
        <tr>
            <th>잔여 시재금</th>
            <td>
                <input id="lastManagerCash"
                       type="text"
                       value="<fmt:formatNumber value='${currentCashBalanceForManager}' type='number'/>"
                       disabled>
                원
            </td>
        </tr>
    </table>

    <table class="common-table" id="selected-employee-table">
        <tbody>
            <tr>
                <th>사번</th>
                <th>이름</th>
                <th>현재 시재금</th>
                <th>거래금</th>
            </tr>
        </tbody>
    </table>
</div>
<%@ include file="/resources/components/modal/employee-search-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/cash-exchange.js"></script>
</body>
</html>
