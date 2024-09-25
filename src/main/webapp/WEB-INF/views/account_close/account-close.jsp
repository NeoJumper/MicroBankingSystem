<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌 해지 신청</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="mainArea">
    <div>
        <h5>예금 관리 >&nbsp</h5>
        <h5>계좌 해지</h5>
    </div>
        <hr style="display: block; margin-bottom: 8px; " class="m-0 border border-dark border-2">
    <div>
        <h3>계좌 해지 신청</h3>
        <div style="margin : 0 200px;">
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">고객 이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="input-customer-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">계좌번호</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="input-account-num" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">상품이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="input-product-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">

            <div class="d-flex justify-content-end mt-4 mx-0">

            </div>
        </div>
    </div>

</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/account-transfer.js"></script>
<script src="/resources/js/footer.js"></script>
</body>

</html>
