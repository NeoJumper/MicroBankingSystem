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
    <div>
        <h5>마감 관리 ></h5>
        <h5>&nbsp마감 상태 관리</h5>
    </div>
    <div id="manager-close-page-user">
        <h3 class="mb-0">마감 관리(매니저)</h3>
        <span id="manager-close-page-user-branch-name">${managerClosingData.branchName}</span>
        <h5 class="mb-0">2024-09-24</h5>
    </div>

    <div class="row">
        <div id="table-container" class="col-9">
            <table id="employee-add-list-thead" class="common-table no-margin">
                <thead>
                <tr>
                    <th style="width: 8%;">사번</th>
                    <th style="width: 8%;">이름</th>
                    <th style="width: 20%;">전일자 현금 잔액</th>
                    <th style="width: 16%;">입금액</th>
                    <th style="width: 20%;">출금액</th>
                    <th style="width: 16%;">금일 마감 금액</th>
                    <th>마감 상태</th>
                </tr>
                </thead>
            </table>

            <div id="employee-add-list" style="overflow-y: auto;">
                <table id="employee-list-table" class="common-table">
                    <tbody>
                    <c:forEach var="closingData" items="${managerClosingData.closingDataList}">
                        <tr class="branch-close-employee-data">
                            <td style="width: 8%;">${closingData.id}</td>
                            <td style="width: 8%;">${closingData.name}</td>
                            <td style="width: 16%;">
                                <input class="employee-prev-cash-balance" type="text"
                                       value="<fmt:formatNumber value='${closingData.prevCashBalance}' type='number'/>"
                                       disabled>
                            </td>
                            <td style="width: 16%;">
                                <input class="employee-total-deposit" type="text"
                                       value="<fmt:formatNumber value='${closingData.totalDeposit}' type='number'/>"
                                       disabled>
                            </td>
                            <td style="width: 16%;">
                                <input class="employee-total-withdrawal" type="text"
                                       value="<fmt:formatNumber value='${closingData.totalWithdrawal}' type='number'/>"
                                       disabled>
                            </td>
                            <td style="width: 16%;">
                                <input class="employee-vaultCash" type="text"
                                       value="<fmt:formatNumber value='${closingData.vaultCash}' type='number'/>"
                                       disabled>
                            </td>
                            <td style="width: 20%;" class="${closingData.status == 'OPEN' ? 'status-open' : closingData.status == 'CLOSED' ? 'status-closed' : ''}">
                                    ${closingData.status}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-3">
            <table id="manager-close-table" class="common-table">
                <tbody>
                <tr>
                    <th>현금 입금액</th>
                    <td>
                        <input id="manager-close-total-deposit" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.totalDepositOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>현금 출금액</th>
                    <td>
                        <input id="manager-close-total-withdrawal" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.totalWithdrawalOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>전일자 현금</th>
                    <td>
                        <input id="manager-close-prev-cash-balance" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.prevCashBalanceOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>금일 마감 금액</th>
                    <td>
                        <c:choose>
                            <c:when test="${managerClosingData.vaultCashOfBranch != null}">
                                <input id="manager-close-vault-cash" type="text"
                                       value="<fmt:formatNumber value='${managerClosingData.vaultCashOfBranch}' type='number'/>"
                                       disabled>
                            </c:when>
                            <c:otherwise>
                                <input id="manager-close-vault-cash" type="text"
                                       placeholder="처리중..." disabled>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="display: flex; justify-content: center; align-items: center; margin: 20px">
                <button id="manager-business-day-close-btn">
                </button>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/page/manager-close.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>