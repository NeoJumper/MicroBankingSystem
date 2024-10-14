<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>행원 관리 > </h5>
        <h5>&nbsp 행원 수정(${id}) </h5>
    </div>
    <div>
        <h3>행원 검색</h3>
        <hr>
        <div class="d-flex align-items-center">
            <input style="width:30%;" type="text" id="customer-id-text" placeholder="사원 번호를 입력하세요">
            <button id="employee-search-btn" type="button" class="basic-btn" style="display: flex; align-items: center; justify-content: center">
                <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
            </button>
        </div>


    </div>
    <div class="mt-4">
        <h3>행원 정보</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>이름</th>
            <td><input type="text" id="emp-name"></td>
            <th>생년월일</th>
            <td><input type="date" id="emp-birth-date"></td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><input type="text" id="emp-email"></td>
            <th>비밀번호</th>
            <td><input type="password" id="emp-password"></td>
        </tr>
        <tr>
            <th>지점명</th>
            <td>
                <input type="text" id="emp-branch-id" disabled>
            </td>
            <th>전화번호</th>
            <td><input type="text" id="emp-phone-number"></td>
        </tr>
        <tr>
            <th>직급</th>
            <td>
                <select id="emp-roles">
                    <option disabled selected>직급 선택</option>
                    <option value="EMPLOYEE">행원</option>
                    <option value="MANAGER">매니저</option>
                </select>
            </td>
            <td colspan="2"></td>
        </tr>
    </table>
    <div class="d-flex justify-content-end mt-4 mb-4">
        <button class="basic-btn" id="emp-update-btn">수정사항 저장</button>
    </div>

</div>

<%@ include file="/resources/components/modal/employee-detail-modal.jsp" %>
<%@ include file="/resources/components/modal/employee-search-modal.jsp" %>
<script src="/resources/js/page/employee-update.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>