<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Result Modal</title>
    <link rel="stylesheet" href="/resources/css/modal/account-search-modal.css">
<%--    <script src="/resources/js/modal/transfer-result-modal.js"></script>--%>
</head>
<body>
<!-- Transfer Result Modal -->
<div class="modal fade" id="transfer-result-modal" tabindex="-1" aria-labelledby="transferResultModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="transfer-modal-title">이체 결과</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>출금 내역</h5>
                <hr>
                <p>출금 계좌 번호: <span id="modal-result-withdrawal-account"></span></p>
                <p>출금 금액: <span id="modal-result-withdrawal-amount"></span> 원</p>
                <p>출금 후 잔액: <span id="modal-result-withdrawal-balance"></span> 원</p>
                <hr>
                <h5>입금 내역</h5>
                <hr>
                <p>입금 계좌 번호: <span id="modal-result-deposit-account"></span></p>
                <p>입금 금액: <span id="modal-result-deposit-amount"></span> 원</p>
                <p>입금 후 잔액: <span id="modal-result-deposit-balance"></span> 원</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
