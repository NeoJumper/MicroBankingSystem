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
    <div id="manager-close-page-user">
        <h3 class="mb-0">마감 관리(매니저)</h3>
        <span id="manager-close-page-user-branch-name">${managerClosingData.branchName}</span>
    </div>

        <div id="table-container" class="mb-5" style="border-bottom: 1px solid var(--little-dark-gray)">
            <table id="employee-add-list-thead" class="common-table no-margin">
                <thead>
                <tr>
                    <th style="width: 5%">사번</th>
                    <th style="width: 5%">이름</th>
                    <th style="width: 5%">직책</th>
                    <th style="width: 18%;">현금 초기액</th>
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
                            <td style="width: 5%; text-align: center">${closingData.id}</td>
                            <td>${closingData.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${closingData.roles == 'ROLE_MANAGER'}">
                                        매니저
                                    </c:when>
                                    <c:when test="${closingData.roles == 'ROLE_EMPLOYEE'}">
                                        행원
                                    </c:when>
                                    <c:otherwise>
                                        기타
                                    </c:otherwise>
                                </c:choose>
                            </td>
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
                            <td style="width: 20%;"
                                class="${closingData.status == 'OPEN' ? 'status-open' : closingData.status == 'CLOSED' ? 'status-closed' : ''} branch-close-employee-status">
                                    ${closingData.status}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div>
            <table id="manager-close-table" class="common-table no-margin">
                <tbody>
                <tr>
                    <th>전일자 현금 총액</th>
                    <td>
                        <input id="manager-close-prev-cash-balance" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.prevCashBalanceOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>현금 총 입금액</th>
                    <td style="display: flex; align-items: center">
                        <i class="bi bi-plus-circle-fill" style="font-size: 1.5rem; margin-right: 10px; color: var(--little-dark-gray)"></i>
                        <input id="manager-close-total-deposit" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.totalDepositOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>현금 총 출금액</th>
                    <td style="display: flex; align-items: center">
                        <i class="bi  bi-dash-circle-fill" style="font-size: 1.5rem; margin-right: 10px; color: var(--little-dark-gray)"></i>
                        <input id="manager-close-total-withdrawal" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.totalWithdrawalOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>

                <tr>
                    <th>금일 마감 금액</th>
                    <td>
                        <input id="manager-close-vault-cash" type="text"
                               value="<fmt:formatNumber value='${managerClosingData.vaultCashOfBranch}' type='number'/>"
                               disabled>
                    </td>
                </tr>
                </tbody>
            </table>
            <c:choose>
                <c:when test="${managerClosingData.isWaitingEmployeeClose == 'TRUE'}">
                    <div class="warning-text mt-2 mb-2" style="font-size: 15px">* 현재 마감 대기중이며, 금액 변동이 있을 수 있습니다.</div>
                </c:when>
            </c:choose>
            <div style="display: flex; justify-content: center; align-items: center; margin: 20px">
                <button id="manager-business-day-close-btn">
                </button>
            </div>
        </div>

</div>

<script src="/resources/js/page/manager-close.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>