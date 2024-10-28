<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/resources/css/modal/customer-detail-modal.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="customer-detail-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">고객 상세</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div>
                    <h3>고객 정보</h3>
                    <hr>
                </div>
                <table class="common-table">
                    <tr>
                        <th>고객번호</th>
                        <td><input type="text" id="detail-modal-customer-id" disabled></td>
                        <th>이름</th>
                        <td><input type="text" id="detail-modal-customer-name" disabled></td>
                    </tr>
                    <tr>
                        <th>보안등급</th>
                        <td><input type="text" id="detail-modal-customer-security-level" disabled></td>

                        <th>주민번호</th>
                        <td style="position: relative;">
                            <input type="text" id="detail-modal-customer-identification-code" disabled>
                            <div class="toggle-visibility"><i class="bi bi-eye"></i></div>
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" id="detail-modal-customer-email" disabled></td>
                        <th>전화번호</th>
                        <td><input type="text" id="detail-modal-customer-phone-number" disabled></td>
                    </tr>
                    <tr>
                        <th>성별</th>
                        <td>
                            <input type="text" id="detail-modal-customer-gender" disabled >
                        </td>
                        <th>지점명</th>
                        <td>
                            <input type="text" id="detail-modal-customer-branch-name" disabled >
                        </td>
                    </tr>
                    <tr>
                        <th>담당자</th>
                        <td><input type="text" id="detail-modal-customer-registrant-name" disabled></td>
                        <th>등록일자</th>
                        <td>
                            <input type="text" id="detail-modal-customer-start-date"  pattern='yyyy-MM-dd' disabled>
                        </td>
                    </tr>

                </table>
                <div class="d-flex justify-content-center mt-5">
                    <div >
                        <button id="detail-modal-customer-update-btn" class ="basic-btn">
                            수정하기
                        </button>
                    </div>
                    <div >
                        <button id="detail-modal-customer-delete-btn" class ="cancel-btn">
                            삭제하기
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="/resources/js/modal/customer-detail-modal.js"></script>

</body>
</html>
