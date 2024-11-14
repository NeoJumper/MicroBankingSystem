<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/deadline-management.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>마감 관리 ></h5>
        <h5>&nbsp마감 상태 관리</h5>
    </div>
    <div>
        <div class="d-flex align-items-center mb-3">
            <h3>마감 관리(행원)</h3>
            <h4 id="employee-close-page-user-branch-name"></h4>
            <h5 id="employee-close-page-user-name" class="mb-0">2024-09-24</h5>
        </div>
    </div>

    <div class="row" style="margin-bottom: 30px">
        <div class="col-12">
            <table class="common-table no-margin">
                <tbody>
                <tr>
                    <th><label>전일자 현금 잔액</label></th>
                    <td>
                        <input class="emp-close-prev-cash-balance" type="text"
                               value="<fmt:formatNumber value='${employeeClosingData.closingData.prevCashBalance}' type='number'/>"
                               disabled>


                        <button id="trade-list-detail-btn" class="basic-btn">
                            내역 새로고침
                        </button>


                        <button
                                id="employee-business-day-close-btn"
                                class="${employeeClosingData.closingData.status == 'CLOSED' ? 'closed-btn' : 'basic-btn'}"
                                <c:if test="${employeeClosingData.closingData.status == 'CLOSED'}">disabled</c:if>
                        >
                            <c:choose>
                                <c:when test="${employeeClosingData.closingData.status == 'CLOSED'}">
                                    마감 완료
                                </c:when>
                                <c:otherwise>
                                    개인 마감
                                </c:otherwise>
                            </c:choose>

                        </button>

                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-3" style="display: flex; align-items: center">

        </div>

    </div>


    <table class="common-table">
        <tbody>
        <tr>
            <%--            employee_closing 금액--%>
            <th><label>현금 입금액</label></th>
            <td>
                <input class="emp-close-cash-deposit" type="text"
                       value="<fmt:formatNumber value='${employeeClosingData.closingData.totalDeposit}' type='number'/>"
                       disabled>
            </td>
            <%--                cash_exchange 금액--%>
            <th><label>현금 인수액</label></th>
            <td>
                <input class="emp-close-cash-exchange-deposit"
                       value="<fmt:formatNumber value='${employeeClosingData.totalDepositOfCashExchange}' type='number'/>"
                       type="text" disabled/>
            </td>


            <%--             trade 금액   --%>
            <th><label>거래내역 현금 입금액</label></th>
            <td>
                <input class="emp-close-trade-list-deposit" type="text"
                       value="<fmt:formatNumber value='${employeeClosingData.totalDepositOfTrade}' type='number'/>"
                       disabled>
            </td>
        </tr>

        <tr>
            <th><label>현금 출금액</label></th>
            <td>
                <input class="emp-close-cash-withdrawal" type="text"
                       value="<fmt:formatNumber value='${employeeClosingData.closingData.totalWithdrawal}' type='number'/>"
                       disabled>
            </td>

            <%--                cash_exchange 금액--%>
            <th><label>현금 인도액</label></th>
            <td>
                <input class="emp-close-cash-exchange-withdrawal"
                       value="<fmt:formatNumber value='${employeeClosingData.totalWithdrawalOfCashExchange}' type='number'/>"
                       type="text" disabled/>
            </td>

            <th><label>거래내역 현금 출금액</label></th>
            <td>
                <input class="emp-close-trade-list-withdrawal" type="text"
                       value="<fmt:formatNumber value='${employeeClosingData.totalWithdrawalOfTrade}' type='number'/>"
                       disabled>
            </td>
        </tr>
        </tbody>
    </table>

    <c:choose>
        <c:when test="${employeeClosingData.expectedVaultCash != employeeClosingData.vaultCashOfTrade + employeeClosingData.vaultCashOfCashExchange}">
            <div class="warning-text mb-3" style="font-size: 15px">
                * 금일 현금 마감 금액과 금일 거래내역 마감 금액이 일치하지 않습니다. 내역 확인 후 누락된 거래 추가 또는 관리자에게 문의 바랍니다.
            </div>
        </c:when>
    </c:choose>

    <table class="common-table">
        <tbody>
        <tr>
            <label style="color: var(--basic-color); margin-bottom: 10px"> * 금일 현금 마감금액 = 인수도 거래 금액 + 금일 거래내역 마감 금액</label>
            <th style="width: 13.5%"><label>금일 현금 마감 금액</label></th>
            <td>
                <input id="emp-close-today-vault-cash"
                       <c:if test='${employeeClosingData.expectedVaultCash != employeeClosingData.vaultCashOfTrade + employeeClosingData.vaultCashOfCashExchange}'>warning-text</c:if>"
                type="text"
                value="<fmt:formatNumber value='${employeeClosingData.expectedVaultCash}' type='number'/>"
                disabled>
            </td>


            <th style="width: 13.5%"><label>인수도 거래 금액</label></th>
            <td>

                <input id="emp-close-cash-exchange-sum"
                       <c:if test='${employeeClosingData.expectedVaultCash != employeeClosingData.vaultCashOfTrade + employeeClosingData.vaultCashOfCashExchange}'>warning-text</c:if>"
                type="text"
                value="<fmt:formatNumber value='${employeeClosingData.vaultCashOfCashExchange}' type='number'/>"
                disabled>

            </td>


            <th style="width: 21%"><label>금일 거래내역 마감 금액</label></th>
            <td>

                <input
                        <c:if test='${employeeClosingData.expectedVaultCash != employeeClosingData.vaultCashOfTrade  + employeeClosingData.vaultCashOfCashExchange}'>warning-text</c:if>"
                type="text"
                value="<fmt:formatNumber value='${employeeClosingData.vaultCashOfTrade}' type='number'/>"
                disabled>

            </td>
        </tr>
        </tbody>
    </table>


    <div class="mt-4" id="tradeDetails">
        <div class="d-flex align-items-center">
            <h3 class="mb-3">거래 내역 목록</h3>
        </div>
    </div>

    <div id="tradeDetailsContent">
        <table class="common-table no-margin">
            <thead>
            <tr>
                <th style="width: 20%;">거래일시</th>
                <th style="width: 20%;">거래 계좌</th>
                <th style="width: 10%;">
                    <span class="tooltip-span" style="display: inline-block;" data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="이 필드는 거래 유형을 나타냅니다.">유형</span>
                </th>
                <th style="width: 20%;">거래액</th>
                <th style="width: 10%;">담당자</th>
                <th style="width: 20%;">거래점</th>
            </tr>
            </thead>
        </table>

        <div id="employee-add-list" class="mb-5">
            <table class="common-table table-hover no-margin">
                <tbody>

                <c:forEach var="trade" items="${employeeClosingData.tradeByCashList}">
                    <tr>
                        <td style="width: 20%;">${trade.tradeDate}</td>
                        <td style="width: 20%;">${trade.accId}</td>
                        <td style="width: 10%;">${trade.tradeType}</td>
                        <td style="width: 20%;"><fmt:formatNumber value="${trade.amount}" type="number"/></td>
                        <td style="width: 10%;">${trade.registrantName}</td>
                        <td style="width: 20%;">${trade.branchName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

</div>
<%@ include file="/resources/components/close-overlay.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/employee-close.js"></script>

</body>
</html>