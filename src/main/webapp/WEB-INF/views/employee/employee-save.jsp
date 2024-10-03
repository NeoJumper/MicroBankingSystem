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
        <h5>&nbsp 행원 등록 </h5>
    </div>
    <div>
        <h3>행원 추가</h3>
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
        <button class="update-btn" id="emp-save-btn">추가하기</button>
    </div>

</div>

<%@ include file="/resources/components/modal/employee-detail-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/employee-save.js"></script>
</body>
</html>