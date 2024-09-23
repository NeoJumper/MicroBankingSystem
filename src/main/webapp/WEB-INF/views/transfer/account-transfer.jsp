<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
<%--    <link rel="stylesheet" type="text/css" href="/resources/css/account-transfer.css" />--%>
    <link rel="stylesheet" type="text/css" href="/resources/css/commonTable.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="mainArea">
    <div>
        <h3>즉시이체</h3>
        <hr>
        <h5>출금계좌정보</h5>
        <table class="commonTable">
            <tr>
                <th><label for="withdrawal-account-number">출금계좌번호</label></th>
                <td id="inputWithBtn"><input type="text" id="withdrawal-account-number"><button id="check-account" class="button-main">계좌조회</button></td>
            </tr>
            <tr>
                <th><label for="account-password">계좌비밀번호</label></th>
                <td><input type="password" id="account-password"></td>
            </tr>
            <tr>
                <th><label for="withdrawal-customer-name">고객명</label></th>
                <td><input disabled type="text" id="withdrawal-customer-name"></td>
            </tr>
            <tr>
                <th><label for="transfer-amount">이체금액</label></th>
                <td>
                    <input type="text" id="transfer-amount" placeholder="350,000 원">
                    <div class="button-group">
                        <button>100만</button>
                        <button>50만</button>
                        <button>10만</button>
                        <button>5만</button>
                        <button>1만</button>
                        <button>전액</button>
                    </div>
                </td>
            </tr>
            <tr>
                <th><label for="execution-date">이체 실행일자</label></th>
                <td><input type="date" id="execution-date"></td>
            </tr>
            <tr>
                <th><label for="remark">비고</label></th>
                <td><input type="text" id="remark"></td>
            </tr>
        </table>
        <h5 class="mt-3">입금계좌정보</h5>
        <hr>
        <table class="commonTable">
            <tbody>
            <tr>
                <th><label for="deposit-account-number">입금계좌번호</label></th>
                <td class="inputWithBtn"><input type="text" id="deposit-account-number"><button class="check-account button-main" >계좌조회</button></td>
            </tr>
            <tr>
                <th><label for="deposit-customer-name">고객명</label></th>
                <td><input disabled type="text" id="deposit-customer-name"></td>
            </tr>
            </tbody>
        </table>
    </div>

    <!-- 모달 -->
    <div id="modal" class="modal">
        <div class="modal-content">
            <span class="close-button">&times;</span>
            <h1>계좌 조회 결과</h1>
            <p>계좌 조회 내용이 여기에 표시됩니다.</p>
        </div>
    </div>
</div>
<script src="/resources/js/account-transfer.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>