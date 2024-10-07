<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌 해지 취소</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-close.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div style="margin-bottom: 17px;">
        <h5>예금 관리 >&nbsp</h5>
        <h5>계좌 해지 취소 완료</h5>
    </div>
    <hr class="m-0 border border-dark border-2">
    <div>

        <div style="margin : 0 100px;">
            <h3>계좌 해지 취소 신청</h3>
            <div id="search-content">
                <div><input style="height: 100%;" placeholder="계좌번호 조회" id="search-input" disabled="TRUE"></div>
                <button id="check-withdrawal-account-btn" class="update-btn" type="button"
                        data-account-type="withdrawal" data-bs-toggle="modal" data-bs-target="#search-modal-account">
                    계좌조회
                </button>
            </div>
            <h4 style="margin-top: 10px">계좌 정보</h4>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">고객 이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="customer-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-1">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">계좌번호</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="account-number" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-1">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">상품이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="product-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">

            <h4>계좌 비밀번호</h4>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5; margin-right: 10px;">계좌 비밀번호</div>
                <div id="account-pw-container">
                    <input type="password" id="account-pw-input" style=" display:block; width:100%; padding: 2px;">
                    <input id="input-confirm" type="submit" value="확인">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">
            <div id="submit">
                <input style="background-color: gray; opacity: 0.5;" id="cancel-submit-btn" type="submit" value="취소 신청" disabled>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/page/account-close-cancel.js"></script>
<%@ include file="/resources/components/modal/account-close-cancel-result-modal.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>
