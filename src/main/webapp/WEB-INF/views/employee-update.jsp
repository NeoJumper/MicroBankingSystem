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
<div id="mainArea">
    <div>
        <h5>행원 관리 > </h5>
        <h5>&nbsp 행원 수정(${id}) </h5>
    </div>
    <div>
        <h3>행원 검색</h3>
        <hr>
        <div class="d-flex align-items-center">
            <input style="width:30%;" type="text" id="customerIdText" placeholder="사원 번호를 입력하세요">
            <button type="button" class="search-btn" style="display: flex; align-items: center; justify-content: center">
                <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
            </button>
        </div>


    </div>
    <div class="mt-4">
        <h3>행원 정보</h3>
        <hr>
    </div>
    <table class="commonTable">
        <tr>
            <th>이름</th>
            <td><input type="text" id="empName"></td>
            <th>생년월일</th>
            <td><input type="date" id="empBirthDate"></td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><input type="text" id="empEmail"></td>
            <th>비밀번호</th>
            <td><input type="password" id="empPassword"></td>
        </tr>
        <tr>
            <th>지점명</th>
            <td>
                <select id="empBranchId">
                    <option disabled selected>지점 선택</option>
                    <option value="1">강남점</option>
                    <option value="2">은평점</option>
                    <option value="3">서초점</option>
                    <option value="4">마포점</option>
                    <option value="5">영등포점</option>
                </select>
            </td>
            <th>전화번호</th>
            <td><input type="text" id="empPhoneNumber"></td>
        </tr>
        <tr>
            <th>직급</th>
            <td>
                <select id="empRoles">
                    <option disabled selected>직급 선택</option>
                    <option value="EMPLOYEE">행원</option>
                    <option value="MANAGER">매니저</option>
                </select>
            </td>
            <td colspan="2"></td>
        </tr>
    </table>
    <div class="d-flex justify-content-end mt-4 mb-4">
        <button class="update-btn" id="empUpdateBtn">수정사항 저장</button>
    </div>

</div>

<%@ include file="/resources/components/modal/employee-detail-modal.jsp" %>
<%@ include file="/resources/components/modal/employee-search-modal.jsp" %>
<script src="/resources/js/employee-update.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>