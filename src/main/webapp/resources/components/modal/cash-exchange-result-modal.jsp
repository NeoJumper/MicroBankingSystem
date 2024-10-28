<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="modal fade" id="resultModal" tabindex="-1" aria-labelledby="resultModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resultModalLabel">거래 결과</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><strong>사번:</strong> <span id="resultEmpId"></span></p>
                <p><strong>이름:</strong> <span id="resultEmpName"></span></p>
                <p><strong>거래금:</strong> <span id="resultAmount"></span> 원</p>
                <p><strong>거래 후 행원 시재금:</strong> <span id="resultEmpCashBalance"></span> 원</p>
                <p><strong>거래 후 매니저 시재금:</strong> <span id="resultManagerCashBalance"></span> 원</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>