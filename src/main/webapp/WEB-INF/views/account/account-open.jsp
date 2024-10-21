<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-open.css"/>

    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">

    <div>
        <h5>예금 관리 ></h5>
        <h5>&nbsp계좌 개설 </h5>
    </div>

    <div class="mb-3">
        <div id = "account-type-button-group" class="button-group">
            <input type="radio" id="private-account" value="PRIVATE" name="major-category" checked>
            <label for="private-account">개인 계좌</label>
            <input class="ms-3" type="radio" id="corporation-account" value="CORPORATION" name="major-category">
            <label for="corporation-account">법인 계좌</label>
        </div>
    </div>
    <div>
        <h3>고객 정보</h3>
    </div>
    <table class="common-table account-open-table">
        <tr>
            <th>고객번호</th>
            <td>
                <input type="text" id="customer-id-input" readonly >
                <button data-bs-toggle="modal" data-bs-target="#search-customer-modal"  type="button" id="customer-id-search-btn" class="basic-btn">
                    <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
                </button>
            </td>
            <th>보안등급</th>
            <td><input type="text" id="customer-security-level-input" disabled></td>

        </tr>
        <tr>
            <th>고객명</th>
            <td><input type="text" id="customer-name-input" disabled></td>
            <td colspan="2"></td>

        </tr>

    </table>


    <div>
        <h3>계좌 개설 정보 입력</h3>
    </div>
    <table class="common-table account-open-table">
        <tr>
            <th>상품명</th>
            <td>
                <input type="text" id="deposit-product-name-input" readonly >
                <button data-bs-toggle="modal" data-bs-target="#search-deposit-product-modal"  type="button" id="deposit-product-search-btn" class="basic-btn">
                    <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
                </button>
            </td>
            <th>기준이율</th>
            <td><input type="text" id="product-interest-input" disabled> %</td>
        </tr>
        <tr>
            <th>우대이율</th>
            <td><input type="text" id="preferred-interest-input"> %</td>

            <th>총 이자율</th>
            <td><input type="text" id="total-interest-input" disabled> %</td>
        </tr>
        <tr>
            <th>초기 예치금(KRW)</th>
            <td><input type="text" id="init-balance-input" class="balance-input"></td>

            <fmt:parseDate value="${tradeDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedTradeDate" />
            <th>계좌 비밀번호</th>
            <td><input type="password" id="password-input"></td>

        </tr>
        <tr>
            <th>1회 이체한도</th>
            <td>
                <input type="text" id="per-trade-limit-input" value="0" class="balance-input">&nbsp원<span style="color: #5F5F5F"></span>

            </td>


            <th>1일 이체한도</th>
            <td>
                <input type="text" id="daily-limit-input" value="0" class="balance-input">&nbsp원<span style="color: #5F5F5F"></span>
            </td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text" id="emp-name-input" value="${employeeName}" disabled></td>
            <th>등록일자</th>
            <td>
                <input type="text" id="start-date-input" value="<fmt:formatDate value='${parsedTradeDate}' pattern='yyyy-MM-dd' />" disabled>
            </td>
        </tr>
        <input type="hidden" id="emp-id-hidden-input" value="${employeeId}" >
        <input type="hidden" id="branch-id-hidden-input" value="${branchId}" >
        <input type="hidden" id="product-id-hidden-input">
    </table>

    <div  style="text-align:center;">
        <button class="basic-btn" id="account-create-btn">계좌 개설</button>

    </div>

    <input type="hidden" id="page-account" value="00">



</div>

<%@ include file="/resources/components/modal/search-modal-customer.jsp" %>
<%@ include file="/resources/components/modal/search-modal-deposit-product.jsp" %>
<%@ include file="/resources/components/modal/result-modal-open-account.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/account-open.js"></script>

</body>
</html>
