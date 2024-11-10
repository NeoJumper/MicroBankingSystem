<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/employee-update.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>행원 관리 > </h5>
        <h5>&nbsp 행원 수정(${id}) </h5>
    </div>
    <div>
        <h3>행원 검색</h3>

        <div class="d-flex align-items-center">
            <input style="width:30%;" type="text" id="customer-id-text" placeholder="사원 번호를 입력하세요">
            <button id="employee-search-btn" type="button" class="basic-btn" style="display: flex; align-items: center; justify-content: center">
                <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
            </button>
        </div>


    </div>
    <div class="mt-4">
        <h3>행원 정보</h3>

    </div>
    <table class="common-table">
        <tr>
            <th>이름</th>
            <td><input type="text" id="emp-name"></td>
            <th>생년월일</th>
            <td><input type="date" id="emp-birth-date"></td>

        </tr>
        <tr>
            <th>비밀번호</th>
            <td>
                <input type="password" id="emp-password">
                <div><span id="password-error-message"></span></div>
            </td>
            <th>주민번호</th>
            <td style="position: relative;">
                <input type="text" id="emp-resident-number" placeholder="000000-0000000" maxlength="14">
                <div class="toggle-visibility"><i class="bi bi-eye"></i></div>
            </td>

        </tr>
        <tr>

            <th>주소</th>
            <td>
                <div class="d-flex my-2">
                    <input  type="text" id="emp-address" placeholder="주소"><br>
                    <input class="ms-2" style="height: 42px;" type="button" onclick="sample6_execDaumPostcode()" value="주소 검색"><br>
                </div>
                <input type="text" id="emp-detail-address" placeholder="상세주소">

            </td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <th>이메일</th>
            <td><input type="text" id="emp-email"></td>
            <th>전화번호</th>
            <td><input type="text" id="emp-phone-number" maxlength="13"></td>
        </tr>
        <tr>
            <th>지점명</th>
            <td>
                <input type="text" id="emp-branch-id" disabled>
            </td>
            <th>직급</th>
            <td>
                <select id="emp-roles">
                    <option disabled selected>직급 선택</option>
                    <option value="ROLE_EMPLOYEE">행원</option>
                    <option value="ROLE_MANAGER">매니저</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text" id="emp-name-input" value="${employeeName}" disabled></td>
            <th>등록일자</th>
            <td>
                <input type="text" id="start-date-input" pattern='yyyy-MM-dd'  disabled/>
            </td>
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