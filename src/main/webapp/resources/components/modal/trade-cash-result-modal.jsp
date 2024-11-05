<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Result Modal</title>
    <link rel="stylesheet" href="/resources/css/modal/cash-trade-result-modal.css">
</head>
<body>
<!-- 거래내역 확인 모달 -->
<div class="modal fade" id="result-modal-cash-trade" tabindex="-1" aria-labelledby="resultModalCashTradeLabel" aria-hidden="true">
    <div class="modal-dialog modal-md modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resultModalCashTradeLabel">거래 내역 확인</h5>
            </div>
            <div class="modal-body">
                <div id="cash-trade-result-img-container">
                    <img  src="/resources/assets/banker.png" alt="Logo" id="cash-trade-result-img"  />
                </div>
                <div class="result-modal-text-area my-2 d-flex flex-column justify-content-center align-items-center">
                    <div>
                        총
                        <span class="fw-bold"> 1 </span>
                        건의
                    </div>

                    <div class="d-flex">
                        <span style="font-weight: bold; color: #073082;" id="result-modal-cash-trade-amount"></span>
                        &nbsp 원
                    </div>
                    <span>입/출금이 완료 되었습니다.</span>
                </div>
                <h5>출금 내역</h5>
                <table class="common-table">
                    <tr>
                        <th>계좌번호</th>
                        <td id="result-modal-cash-trade-acc-id"></td>
                    </tr>
                    <tr>
                        <th>예금주</th>
                        <td id="result-modal-cash-trade-customer-name"></td>
                    </tr>
                    <tr>
                        <th>잔액</th>
                        <td id="result-modal-cash-trade-balance"></td>
                    </tr>
                    <tr>
                        <th>승인일자</th>
                        <td id="result-modal-cash-trade-registration-date"></td>
                    </tr>

                    <tr>
                        <th>거래 유형</th>
                        <td id="result-modal-cash-trade-trade-type"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button id="cash-trade-result-modal-close-btn" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
