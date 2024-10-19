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
        <h5>마감 관리</h5>
    </div>
        <div id="manager-close-page-user">
            <h3 class="mb-0">마감 관리(매니저)</h3>
            <span id="manager-close-page-user-branch-name">${managerClosingData.branchName}</span>
            <h5 class="mb-0">2024-09-24</h5>
        </div>

    <div class="row">
        <div id="table-container" class="col-9">
            <table class="common-table no-margin">
                <tbody>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>전일자 현금 잔액</th>
                    <th>입금액</th>
                    <th>출금액</th>
                    <th>금일 마감 금액</th>
                    <th>마감 상태</th>
                </tr>
                </tbody>
            </table>

            <div id="employee-add-list" style="overflow-y: auto;">
                <table class="table">
                    <tbody>
                    <tr>
                        <td>test</td>
                        <td>test</td>
                        <td>test</td>
                        <td>test</td>
                        <td>test</td>
                        <td>test</td>
                        <td>test</td>

                    </tr>
                    <c:forEach var="closingData" items="${managerClosingData.closingDataList}">
                        <tr class="branch-close-employee-data">
                            <td>${closingData.id}</td>
                            <td>${closingData.name}</td>
                            <td>
                                <input class="employee-prev-cash-balance" type="text"
                                       value="<c:out value='${closingData.prevCashBalance}' />" disabled>
                            </td>
                            <td>
                                <input class="employee-total-deposit" type="text"
                                       value="<c:out value='${closingData.totalDeposit}' />" disabled>
                            </td>
                            <td>
                                <input class="employee-total-withdrawal" type="text"
                                       value="<c:out value='${closingData.totalWithdrawal}' />" disabled>
                            </td>
                            <td>
                                <input class="employee-vaultCash" type="text"
                                       value="<c:out value='${closingData.vaultCash}' />" disabled>
                            </td>
                            <td class="branch-close-employee-status">${closingData.status}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="col-3">
                <table class="common-table">
                    <tbody>
                        <tr>
                            <th>현금 입금액</th>
                            <td>
                                <input id="manager-close-total-deposit"  type="text"
                                       value="<c:out value='${managerClosingData.totalDepositOfBranch}' />" disabled>
                            </td>
                        </tr>
                        <tr>
                            <th>현금 출금액</th>
                            <td>
                                <input id="manager-close-total-withdrawal" type="text"
                                       value="<c:out value='${managerClosingData.totalWithdrawalOfBranch}' />" disabled>
                            </td>
                        </tr>
                        <tr>
                            <th>전일자 현금</th>
                            <td>
                                <input id="manager-close-prev-cash-balance"  type="text"
                                       value="<c:out value='${managerClosingData.prevCashBalanceOfBranch}' />" disabled>
                            </td>
                        </tr>
                        <tr>
                            <th>금일 마감 금액</th>
                            <td>

                                <c:choose>
                                    <c:when test="${managerClosingData.vaultCashOfBranch != null}">
                                        <input id="manager-close-vault-cash"  type="text"
                                               value="<c:out value='${managerClosingData.vaultCashOfBranch}' />"
                                               disabled>
                                    </c:when>

                                    <c:otherwise>
                                        <input id="manager-close-vault-cash" type="text"
                                               placeholder="처리중..."
                                               disabled>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                    </tbody>
                </table>
        </div>
    </div>

        <div>
            <button id="manager-business-day-close-btn">
            </button>
        </div>

</div>

<script src="/resources/js/page/manager-close.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>