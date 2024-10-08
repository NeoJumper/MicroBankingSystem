<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Close Result</title>
    <link rel="stylesheet" href="/resources/css/modal/account-search-modal.css">
</head>
<body>
<!-- Transfer Result Modal -->
<div class="modal fade" id="transfer-result-modal" tabindex="-1" aria-labelledby="transferResultModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="transfer-modal-title">계좌 해지 취소</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="common-table">
                    <tr>
                        <th>예금주명</th>
                        <td><span id="modal-account-close-customerName"></span></td>
                    </tr><tr>
                    <th>계좌 번호</th>
                    <td><span id="modal-account-close-accountId"></span></td>
                </tr>
                    <tr>
                        <th>상품 이름</th>
                        <td><span id="modal-account-close-productName"></span></td>
                    </tr>
                    <tr>
                        <th>기본 이율</th>
                        <td><span id="modal-account-close-interRate"></span> %</td>
                    </tr>
                    <tr>
                        <th>우대 이율</th>
                        <td><span id="modal-account-close-preInterRate"></span> %</td>
                    </tr>
                    <tr>
                        <th>세율</th>
                        <td><span id="modal-account-close-taxRate"></span> %</td>
                    </tr>
                    <tr>
                        <th>세전 이자</th>
                        <td><span id="modal-account-close-preTaxInterest"></span> 원</td>
                    </tr>
                    <tr>
                        <th>세후 이자</th>
                        <td><span id="modal-account-close-afterTaxInterest"></span> 원</td>
                    </tr>
                    <tr>
                        <th>취소 후 잔액</th>
                        <td><span id="modal-account-close-balanceToRollback"></span> 원</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/page/account-close-cancel.js"></script>
</body>
</html>
