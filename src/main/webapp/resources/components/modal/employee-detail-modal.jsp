<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/resources/css/modal/employee-detail-modal.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="employee-detail-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">행원 상세</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div>
                    <h3>행원 정보</h3>
                    <hr>
                </div>
                <table class="common-table">
                    <tr>
                        <th>사원번호</th>
                        <td><input type="text" id="detail-modal-emp-id" disabled></td>
                        <th>비밀번호</th>
                        <td><input type="password" id="detail-modal-emp-password" disabled></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" id="detail-modal-emp-name" disabled></td>
                        <th>생년월일</th>
                        <td><input type="date" id="detail-modal-emp-birth-date" disabled></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" id="detail-modal-emp-email" disabled></td>
                        <th>전화번호</th>
                        <td><input type="text" id="detail-modal-emp-phone-number" disabled></td>
                    </tr>
                    <tr>
                        <th>지점명</th>
                        <td>
                            <input type="text" id="detail-modal-emp-branch-name" disabled >
                        </td>
                        <th>직급</th>
                        <td>
                            <input type="text" id="detail-modal-emp-roles" disabled>
                        </td>
                    </tr>

                </table>
                <div class="d-flex justify-content-center mt-5">
                    <div >
                        <button id="detail-modal-emp-update-btn" class ="update-btn">
                            수정하기
                        </button>
                    </div>
                    <div >
                        <button id="detail-modal-emp-delete-btn" class ="cancel-btn">
                            삭제하기
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="/resources/js/modal/employee-detail-modal.js"></script>

</body>
</html>
