<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/cash-exchange-close.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>시재 관리 ></h5>
        <h5>&nbsp인수도 거래 마감</h5>
    </div>

    <h3>시재금 거래 내역</h3>
    <table class="common-table">
        <thead>
        <tr>
            <th>순번</th>
            <th>금액</th>
            <th>유형</th>
            <th>사번</th>
            <th>행원 이름</th>
            <th>거래 후 행원 잔액</th>
            <th>거래 후 시재금 잔액</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cashExchange" items="${cashExchangeCloseData.cashExchangeList}">
            <tr>
                <td>${cashExchange.id}</td>
                <td>
                    <input type="text" class="amount ${cashExchange.exchangeType}"
                           value="<fmt:formatNumber value='${cashExchange.amount}' type='number'/>"
                           data-amount="${cashExchange.amount}" disabled>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${cashExchange.exchangeType == 'HANDOVER'}">
                            <i class="trade-type-withdrawal bi bi-circle-fill me-2"></i>
                            인도거래
                        </c:when>
                        <c:when test="${cashExchange.exchangeType == 'RECEIVE'}">
                            <i class="trade-type-deposit bi bi-circle-fill me-2"></i>
                            인수거래
                        </c:when>
                        <c:otherwise>
                            기타
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${cashExchange.empId}</td>
                <td>${cashExchange.empName}</td>
                <td>
                    <input type="text" value="<fmt:formatNumber value='${cashExchange.empCashBalance}' type='number'/>"
                           disabled>
                </td>
                <td>
                    <input type="text"
                           value="<fmt:formatNumber value='${cashExchange.managerCashBalance}' type='number'/>"
                           disabled>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 합계 테이블 -->
    <h3>마감 금액 확인</h3>
    <table class="common-table">
        <tbody>
        <tr>
            <th>인도액</th>
            <td>
                <input type="text" id="handoverTotal" value="0" disabled> 원
            </td>
        </tr>
        <tr>
            <th>인수액</th>
            <td>
                <input type="text" id="receiptTotal" value="0" disabled> 원
            </td>
        </tr>
        <tr>
            <th>금일 시재금 마감 금액</th>
            <td>
                <input id="lastManagerCash"
                       type="text"
                       value="<fmt:formatNumber value='${cashExchangeCloseData.lastManagerCash}' type='number'/>"
                       disabled>
                원
            </td>
        </tr>
        </tbody>
    </table>
    <div style="display: flex; justify-content: center">
        <button id="cash-exchange-close" class="basic-btn">시재금 거래 마감</button>
    </div>
    <%@ include file="/resources/components/close-overlay.jsp" %>
</div>

<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/cash-exchange-close.js"></script>
</body>
</html>
