<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>적금 계좌 조회</title>
    <link rel="stylesheet" href="/resources/css/modal/transfer-account-search-modal.css">
    <script src="/resources/js/modal/transfer-account-search-modal.js"></script>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="search-modal-transfer-account" tabindex="-1" aria-labelledby="transferAccountSearchModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="transferAccountSearchModalLabel">적금 계좌 조회</h1> <!-- Modal Title -->
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
<%--                <h5>계좌정보 입력</h5>--%>
<%--                <hr>--%>

<%--                <div id="search-modal-input-box">--%>
<%--                    <input id="modal-search-input" type="text" placeholder="입력하시오.">--%>
<%--                </div>--%>
<%--                <hr>--%>
<%--                <div id="search-modal-center-box">--%>
<%--                    <button id="modal-check-account-btn" class="btn update-btn" type="button">계좌 검색</button> <!-- Added "btn" class -->--%>
<%--                    <button id="modal-check-account-reset-btn" class="btn reset-btn" type="button">초기화</button> <!-- Added "btn" class -->--%>
<%--                </div>--%>

                <h5>계좌정보</h5>
                <hr>
                <table id="search-modal-common-table" class="common-table">
                    <thead>
                    <th><label>선택</label></th>
                    <th><label id="search-modal-product-id">계좌번호</label></th>
                    <th><label id="search-modal-open-date">개설일</label></th>
                    <th><label id="search-modal-customer-name">고객명</label></th>
                    <th><label id="search-modal-product-name">상품 종류</label></th>
                    <th><label id="search-modal-balance">현재 잔액</label></th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <div id="search-modal-select-button">
                    <button id="search-modal-select-transfer-account-btn" class="btn basic-btn">계좌 선택</button> <!-- Added "btn" class -->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
