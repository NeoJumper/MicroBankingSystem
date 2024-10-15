<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>

    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">
    <div>
        <h5>예금 관리 > </h5>
        <h5>&nbsp 계좌 개설 </h5>
    </div>

    <div>
        <h3>계좌 개설 정보 입력</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>고객번호</th>
            <td style="display: flex; align-items: center;">
                <input type="text" id="customer-id-input" readonly >
                <button data-bs-toggle="modal" data-bs-target="#search-customer-modal"  type="button" id="customer-id-search-btn" class="btn btn-primary" style="margin-left: 10px; padding: 5px; width:80px;height:40px">
                    <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
                </button>
            </td>
            <th>비밀번호</th>
            <td><input type="text" id="password-input"></td>

        </tr>
        <tr>
            <th>고객명</th>
            <td><input type="text" id="customer-name-input" disabled></td>
            <th>이자시작일자</th>
            <td><input type="text" id="start-date-input" value='${tradeDate}' disabled></td>

        </tr>
        <tr>
            <th>초기 예치금(KRW)</th>
            <td><input type="text" id="balance-input"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>기준이율</th>
            <td><input type="text" id="product-interest-input" disabled> %</td>

            <th>우대이율</th>
            <td><input type="text" id="preferred-interest-input"> %</td>
        </tr>
        <tr>
            <th>총 이자율</th>
            <td><input type="text" id="total-interest-input" disabled> %</td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text" id="emp-name-input" value="${employeeName}" disabled></td>
            <td colspan="2"></td>
        </tr>
        <input type="hidden" id="emp-id-hidden-input" value="${employeeId}" >
        <input type="hidden" id="branch-id-hidden-input" value="${branchId}" >
        <input type="hidden" id="product-id-hidden-input">
    </table>

    <div  style="text-align:center;">
        <button class="btn btn-primary" id="account-create-btn">계좌 개설</button>

    </div>

    <input type="hidden" id="page-account" value="00">



</div>

<%@ include file="/resources/components/modal/search-modal-customer.jsp" %>
<%@ include file="/resources/components/modal/result-modal-open-account.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/account-open.js"></script>

</body>
</html>
