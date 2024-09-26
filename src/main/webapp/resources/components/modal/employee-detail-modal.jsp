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
<div class="modal fade" id="employeeDetailModal" tabindex="-1" aria-labelledby="employeeDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="employeeDetailModalLabel">행원 상세</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div>
                    <h3>행원 정보</h3>
                    <hr>
                </div>
                <table class="commonTable">
                    <tr>
                        <th>사원번호</th>
                        <td><input type="text" id="registrantIdOfDetailModal" disabled></td>
                        <th>비밀번호</th>
                        <td><input type="password" id="empPasswordOfDetailModal" disabled></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" id="empNameOfDetailModal" disabled></td>
                        <th>생년월일</th>
                        <td><input type="date" id="empBirthDateOfDetailModal" disabled></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" id="empEmailOfDetailModal" disabled></td>
                        <th>전화번호</th>
                        <td><input type="text" id="empPhoneNumberOfDetailModal" disabled></td>
                    </tr>
                    <tr>
                        <th>지점명</th>
                        <td>
                            <input type="text" id="empBranchNameOfDetailModal" disabled >
                        </td>
                        <th>직급</th>
                        <td>
                            <input type="text" id="empRolesOfDetailModal" disabled>
                        </td>
                    </tr>

                </table>
                <div class="d-flex justify-content-center mt-5">
                    <div >
                        <button class ="update-btn">
                            수정하기
                        </button>
                    </div>
                    <div >
                        <button class ="cancel-btn">
                            삭제하기
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
