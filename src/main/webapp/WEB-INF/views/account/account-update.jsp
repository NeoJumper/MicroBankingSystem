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
        <h5>계좌 관리 > </h5>
        <h5>&nbsp 계좌 수정 </h5>
    </div>
    <div>
        <h3>계좌 수정</h3>
        <hr>
        <div class="d-flex align-items-center">
            <input style="width:30%;" type="text" id="search-account-id-input" placeholder="계좌 번호를 입력하세요" readonly>
            <button id="check-deposit-account-btn" class="basic-btn" type="button" data-account-type="deposit" data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회</button>
        </div>
    </div>
    <br><br>


    <div>
        <h3>계좌 개설 정보</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>고객번호</th>
            <td style="display: flex; align-items: center;">
                <input type="text" id="customer-id-input" disabled >

            </td>
            <th>비밀번호</th>
            <td><input type="text" id="password-input"></td>

        </tr>
        <tr>
            <th>고객명</th>
            <td><input type="text" id="customer-name-input" disabled></td>
            <th>잔액</th>
            <td><input type="text" id="balance-input" disabled></td>
        </tr>
        <tr>
            <th>계좌번호</th>
            <td><input type="text" id="acc-id-input" disabled></td>
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
            <td><input type="text" id="emp-name-input"  disabled></td>
            <th>개설지점</th>
            <td><input type="text" id="branch-name"  disabled></td>

        </tr>

    </table>

    <div style="display: flex; justify-content : right">
        <button id="update-account-btn" class="basic-btn" type="button" >계좌 수정</button>
    </div>

</div>

<%@ include file="/resources/components/modal/account-search-modal.jsp" %>

<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/account-update.js"></script>
</body>
</html>
