<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/resources/css/modal/account-search-modal.css">
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="accountSearchModal" tabindex="-1" aria-labelledby="accountSearchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="accountSearchModalLabel">계좌 검색</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>계좌정보 입력</h5>
                <hr>
                <div id="inputBox">
                    <label>계좌번호</label>
                    <input type="text">
                </div>
                <hr>
                <div id="modalCenterBox">
                    <button id="check-account" class="button-main" type="button">계좌 검색</button>
                    <button id="check-account-reset" class="btn btn-secondary" type="button">초기화</button>
                </div>

                <h5>계좌정보</h5>
                <hr>
                <table class="commonTable">
                    <thead>
                    <th><label>선택</label></th>
                    <th><label id="modal-account-id">계좌번호</label></th>
                    <th><label id="modal-open-date">개설일</label></th>
                    <th><label id="modal-customer-name">고객명</label></th>
                    <th><label id="modal-product-name">상품 종류</label></th>
                    <th><label id="modal-total-amount">현재 잔액</label></th>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
