<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Result Modal</title>
    <link rel="stylesheet" href="/resources/css/modal/transfer-result-modal.css">
    <script src="/resources/js/modal/transfer-result-modal.js"></script>
</head>
<body>
<!-- Transfer Result Modal -->
<div class="modal fade" id="transfer-result-modal" tabindex="-1" aria-labelledby="transferResultModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="transfer-modal-title">이체 결과</h1>
                <button id="result-modal-close-btn" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="transfer-result-img-container">
                    <img  src="/resources/assets/transfer-result.png" alt="Logo" id="transfer-result-img"  />
                </div>
                <div class="mt-2 d-flex flex-column justify-content-center align-items-center">
                    <div>
                        총
                        <span class="fw-bold"> 1 </span>
                        건의
                    </div>

                    <div class="d-flex">
                        <span style="font-weight: bold; color: #073082;" id="modal-result-deposit-amount"></span>
                        &nbsp 원
                    </div>
                    <span>이체가 완료 되었습니다.</span>
                </div>

                <h5>출금 내역</h5>
                <table class="common-table">
                    <tr>
                        <th>출금 계좌 번호</th>
                        <td><span id="modal-result-withdrawal-account"></span></td>
                    </tr>
                    <tr>
                        <th>출금 계좌 고객명</th>
                        <td><span id="modal-result-withdrawal-customer-name"></span></td>
                    </tr>
                    <tr>
                        <th>출금 후 잔액</th>
                        <td><span id="modal-result-withdrawal-balance"></span> 원</td>
                    </tr>
                </table>
                <h5>입금 내역</h5>
                <table class="common-table">
                    <tr>
                        <th>입금 계좌 번호</th>
                        <td><span id="modal-result-deposit-account"></span></td>
                    </tr>
                    <tr>
                        <th>입금 계좌 고객명</th>
                        <td><span id="modal-result-deposit-customer-name"></span></td>
                    </tr>
                    <tr>
                        <th>입금 후 잔액</th>
                        <td><span id="modal-result-deposit-balance"></span> 원</td>
                    </tr>

                </table>

            </div>
        </div>
    </div>
</div>
</body>
</html>
