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
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/deadline-management.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>마감일 관리</h5>
    </div>
    <div>
        <div class="d-flex align-items-center">
            <h3 class="mb-0">마감일 관리(매니저)</h3>
            <h4 id="manager-close-page-user-branch-name">은평 제 1지점</h4>
            <h5 class="mb-0">2024-09-24</h5>
        </div>
        <hr>
    </div>

    <div>
        <table class="table">
            <tbody>
                <tr>
                    <th style="width: 5%;">사번</th>
                    <th style="width: 5%;">이름</th>
                    <th style="width: 10%;">전일자 현금 잔액</th>
                    <th style="width: 10%;">입금액</th>
                    <th style="width: 10%;">출금액</th>
                    <th style="width: 10%;">금일 마감 금액</th>
                    <th style="width: 6%;"></th>
                </tr>
            </tbody>
        </table>

        <div id="employee-add-list" style="overflow-y: auto; height: 200px;">
            <table class="table">
            <tbody>
                <c:forEach var="closingData" items="${managerClosingData.closingDataList}">
                <tr class = "branch-close-employee-data">
                    <td style="width: 5%;">${closingData.id}</td>
                    <td style="width: 5%;">${closingData.name}</td>
                    <td style="width: 10%;">
                        <input class="employee-prev-cash-balance" type="text" value="<c:out value='${closingData.prevCashBalance}' />" disabled>
                    </td>
                    <td style="width: 10%;">
                        <input class="employee-total-deposit" type="text" value="<c:out value='${closingData.totalDeposit}' />" disabled>
                    </td>
                    <td style="width: 10%;">
                        <input class="employee-total-withdrawal" type="text" value="<c:out value='${closingData.totalWithdrawal}' />" disabled>
                    </td>
                    <td style="width: 10%;">
                        <input class="employee-vaultCash" type="text" value="<c:out value='${closingData.vaultCash}' />" disabled>
                    </td>
                    <td class="branch-close-employee-status" style="width: 6%;">${closingData.status}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        </div>
    </div>


    <div class="d-flex justify-content-center mt-4">
        <div >
            <div class="d-flex w-100">
                <div class="ms-5 mb-2 ">
                    <label class="amount-label1">현금 입금액</label>
                    <input id ="manager-close-total-deposit" class="amount-input" type="text" value="<c:out value='${managerClosingData.totalDepositOfBranch}' />" disabled>
                </div>
                <div class="ms-5 mb-2">
                    <label class="amount-label1">현금 출금액</label>
                    <input id ="manager-close-total-withdrawal" class="amount-input" type="text"  value="<c:out value='${managerClosingData.totalWithdrawalOfBranch}' />" disabled>
                </div>
            </div>

            <div class="d-flex w-100">
                <div class="ms-5 mb-2">
                    <label class="amount-label1">전일자 현금</label>
                    <input id ="manager-close-prev-cash-balance" class="amount-input" type="text"  value="<c:out value='${managerClosingData.prevCashBalanceOfBranch}' />" disabled>
                </div>
                <div class="ms-5 mb-2">
                    <label class="amount-label1">금일 마감 금액</label>
                    <c:choose>
                        <c:when test="${managerClosingData.vaultCashOfBranch != null}">
                            <input id="manager-close-vault-cash" class="amount-input" type="text"
                                   value="<c:out value='${managerClosingData.vaultCashOfBranch}' />"
                                   disabled>
                        </c:when>

                        <c:otherwise>
                            <input id="manager-close-vault-cash" class="amount-input" type="text"
                                   placeholder="처리중..."
                                   disabled>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

        </div>


    </div>
    <div class="d-flex justify-content-center mt-4">
        <div >
            <button id="manager-business-day-close-btn">
            </button>
        </div>
    </div>

</div>


<script src="/resources/js/page/manager-close.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>