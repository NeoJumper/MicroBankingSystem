<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="modal fade" id="cash-exchange-result-modal" tabindex="-1" aria-labelledby="cash-exchange-result-modal-Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cash-exchange-result-modal-Label">거래 결과</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="common-table">
                    <tbody>
                        <tr>
                            <th>사번</th>
                            <td><span id="resultEmpId"></span></td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td><span id="resultEmpName"></span></td>
                        </tr>
                        <tr>
                            <th>거래금</th>
                            <td><span id="resultAmount"></span> 원</td>
                        </tr>
                        <tr>
                            <th>거래 후 행원 시재금</th>
                            <td><span id="resultEmpCashBalance"></span> 원</td>
                        </tr>
                        <tr>
                            <th>거래 후 매니저 시재금</th>
                            <td><span id="resultManagerCashBalance"></span> 원</td>
                        </tr>
                    </tbody>

                </table>
            </div>
            <div class="modal-footer">
                <button id="cash-exchange-result-close-btn" type="button" class="basic-btn" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>