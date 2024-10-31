<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>계좌 해지 신청</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-close.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>예금 관리 >&nbsp</h5>
        <h5>계좌 해지 완료</h5>
    </div>
    <div>
        <div>
            <h3>계좌 해지 신청</h3>

            <table id="search-content" class="common-table">
                <tbody>
                <tr>
                    <th>계좌번호</th>
                    <td>
                        <input id="search-input" type="text" placeholder="계좌번호 조회" value="" disabled>
                        <button id="check-withdrawal-account-btn" class="basic-btn" type="button"
                                data-account-type="withdrawal" data-bs-toggle="modal"
                                data-bs-target="#search-modal-account">
                            계좌조회
                        </button>
                    </td>

                </tr>
                </tbody>
            </table>

            <h3>계좌 정보</h3>

            <table class="common-table">
                <tbody>
                <tr>
                    <th><label for="customer-name">고객 이름</label></th>
                    <td><input id="customer-name" type="text" disabled></td>
                </tr>
                <tr>
                    <th><label for="account-number">계좌번호</label></th>
                    <td><input id="account-number" type="text" disabled></td>
                </tr>
                <tr>
                    <th><label for="product-name">상품 이름</label></th>
                    <td><input id="product-name" type="text" disabled></td>
                </tr>
                </tbody>
            </table>
            <h4>예금 예상 이자 및 총 금액</h4>

            <table id="table-content" class="common-table">
                <tbody>
                <thead>
                <th>계좌 잔액</th>
                <th>이율</th>
                <th>우대 이율</th>
                <th>세율</th>
                <th>세전 이자</th>
                <th>세후 이자</th>
                <th>지급 총 금액</th>
                </thead>
                </tbody>
            </table>
            <h4>계좌 비밀번호</h4>

            <table class="common-table">
                <tbody>
                <tr>
                    <th>계좌 비밀번호</th>
                    <td>
                        <input type="password" id="account-pw-input">
                        <input id="input-confirm" type="submit" class="basic-btn" value="확인">
                    </td>
                </tr>
                </tbody>
            </table>



            <div id="submit" class="row justify-content-center mb-5">
                <input id="submit-btn" class="basic-btn col-1" type="submit" value="해지 신청" disabled>
            </div>
        </div>
    </div>

</div>

<%@ include file="/resources/components/close-overlay.jsp" %>
<%@ include file="/resources/components/modal/account-close-result-modal.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/account-close.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>
