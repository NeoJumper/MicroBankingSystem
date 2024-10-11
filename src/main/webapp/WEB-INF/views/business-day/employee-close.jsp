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
            <h3 class="mb-0">마감일 관리(행원)</h3>
            <h4 id="employee-close-page-user-branch-name"  style="background-color :blue; color: white; font-size: 1.2rem; padding: 7px 10px; margin: 0 10px;"></h4>
            <h5 id="employee-close-page-user-name" class="mb-0">2024-09-24</h5>
        </div>
        <hr>
    </div>
    <div class="d-flex">
        <div class="col-9">
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">현금 입금액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.closingData.totalDeposit}' />" disabled>
                </div>
                <div>
                    <label class="amount-label2">거래내역 현금 입금액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.totalDepositOfTrade}' />" disabled>
                </div>
            </div>
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">현금 출금액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.closingData.totalWithdrawal}' />" disabled>
                </div>
                <div>
                    <label class="amount-label2">거래내역 현금 출금액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.totalWithdrawalOfTrade}' />" disabled>
                </div>
            </div>
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">전일자 현금 잔액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.closingData.prevCashBalance}' />" disabled>

                </div>
                <div>
                    <label class="amount-label2">금일 마감 금액</label>
                    <input class="amount-input" type="text" value="<c:out value='${employeeClosingData.closingData.vaultCash}' />" disabled>
                </div>
            </div>

        </div>
        <div class="col-3 d-flex justify-content-end">
            <div >
                <button id="trade-list-detail-btn" class ="basic-btn">
                    내역 상세보기
                </button>
            </div>
            <div >
                <button id="employee-business-day-close-btn"
                        class="${employeeClosingData.closingData.status == 'CLOSED' ? 'closed-btn' : 'update-btn'}"
                        <c:if test="${employeeClosingData.closingData.status == 'CLOSED'}">disabled</c:if>>
                    <c:choose>
                        <c:when test="${employeeClosingData.closingData.status == 'CLOSED'}">
                            마감 완료
                        </c:when>
                        <c:otherwise>
                            개인 마감
                        </c:otherwise>
                    </c:choose>

                </button>
            </div>
        </div>
    </div>


    <div class="mt-4" id="tradeDetails" style="display: none;">
        <div class="d-flex align-items-center">
            <h3 class="mb-0">거래 금액</h3>
        </div>
        <hr>
    </div>

    <div id="tradeDetailsContent" style="display: none;">
        <table class="table">
            <thead>
            <tr>
                <th style="width: 20%;">거래일시</th>
                <th style="width: 20%;">거래 계좌</th>
                <th style="width: 10%;">유형</th>
                <th style="width: 20%;">거래액</th>
                <th style="width: 10%;">담당자</th>
                <th style="width: 20%;">거래점</th>
            </tr>
            </thead>
        </table>

        <div id="employee-add-list" style="overflow-y: auto; height: 260px;">
            <table class="table table-hover">
                <tbody>
                <c:forEach var="trade" items="${employeeClosingData.tradeByCashList}">
                    <tr>
                        <td style="width: 20%;">${trade.tradeDate}</td>
                        <td style="width: 20%;">${trade.accId}</td>
                        <td style="width: 10%;">${trade.tradeType}</td>
                        <td style="width: 20%;">${trade.amount}</td>
                        <td style="width: 10%;">${trade.registrantName}</td>
                        <td style="width: 20%;">${trade.branchName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script src="/resources/js/page/employee-close.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>