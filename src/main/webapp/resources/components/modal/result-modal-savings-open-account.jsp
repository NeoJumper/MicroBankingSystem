<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/modal/result-modal-open-account.css"/>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="savings-result-modal" tabindex="-1" role="dialog"
     aria-labelledby="savings-result-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-search-label" style="margin-right: 10px">계좌 개설 정보</h5>
                <p style="margin-bottom: 0px" id="modal-search-status"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>개설 내역</h5>
                <table class="common-table account-open-result-modal-table">
                    <tr>
                        <th>계좌번호</th>
                        <td>
                            <input type="text" id="savings-result-account-id-input" disabled >
                        </td>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <th>고객명</th>
                        <td><input type="text" id="savings-result-customer-name-input" disabled></td>
                        <th>고객번호</th>
                        <td><input type="text" id="savings-result-customer-number-input" disabled></td>
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td><input type="text" id="savings-result-product-name-input" disabled></td>
                        <th>이자시작일</th>
                        <td><input type="text" id="savings-result-start-date-input" disabled></td>
                    </tr>
                    <tr>
                        <th>기준이율</th>
                        <td><input type="text" id="savings-result-product-interest-input" disabled> %</td>
                        <th>우대이율</th>
                        <td><input type="text" id="savings-result-preferred-interest-input" disabled> %</td>
                    </tr>

                    <tr>
                        <th>총 이자율</th>
                        <td><input type="text" id="savings-result-total-interest-input" disabled> %</td>
                        <th>잔액</th>
                        <td><input type="text" id="savings-result-balance-input" disabled> 원</td>
                    </tr>
                    <tr>
                        <th>관리지점명</th>
                        <td><input type="text" id="savings-result-branch-name-input" disabled></td>
                        <th>담당자</th>
                        <td><input type="text" id="savings-result-registrant-name-input" disabled></td>
                    </tr>

                </table>


            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/modal/account-open-result-modal.js"></script>
</body>
</html>