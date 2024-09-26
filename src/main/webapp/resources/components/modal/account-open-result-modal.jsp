<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>



    </style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="account-open-result-modal" tabindex="-1" role="dialog"
     aria-labelledby="modal-search-showSearchCustomerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-search-label" style="margin-right: 10px">계좌 개설 정보</h5>
                <p style="margin-bottom: 0px" id="modal-search-status"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="commonTable">
                    <tr>
                        <th>계좌번호</th>
                        <td style="display: flex; align-items: center;">
                            <input type="text" id="open-result-accId" disabled >

                        </td>
                    </tr>
                    <tr>
                        <th>고객명</th>
                        <td><input type="text" id="open-result-customerName" disabled></td>
                        <th>고객번호</th>
                        <td><input type="text" id="open-result-customerId" disabled></td>

                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input type="text" id="open-result-customerPhone" disabled></td>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td><input type="text" id="open-result-productName" disabled></td>
                        <th>이자시작일</th>
                        <td><input type="text" id="open-result-startDate" disabled></td>
                    </tr>
                    <tr>
                        <th>총 이자율</th>
                        <td><input type="text" id="open-result-totalInterest" disabled> %</td>
                        <th>잔액</th>
                        <td><input type="text" id="open-result-balance" disabled> %</td>
                    </tr>
                    <tr>
                        <th>관리지점명</th>
                        <td><input type="text" id="open-result-branch" disabled></td>
                        <th>담당자</th>
                        <td><input type="text" id="open-result-empName" disabled></td>
                    </tr>

                </table>


            </div>
            <div class="modal-footer">
                통장이 개설됐습니다.
                <input type="text" id="accountOpenResultText">
            </div>
        </div>
    </div>
</div>

</body>
</html>
