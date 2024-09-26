<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/employee-list.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>행원 관리 > </h5>
        <h5>&nbsp 행원 목록</h5>
    </div>
    <div>
        <h3>행원 목록</h3>
        <hr>
    </div>
    <div class="d-flex justify-content-end mb-3">
        <div style="width: 10%; margin-left: 15px;">
            <select id="empSearchOption">
                <option selected value="" disabled>검색조건</option>
                <option value="id">사번</option>
                <option value="name">이름</option>
                <option value="birthDate">생년월일</option>
                <option value="phoneNumber">전화번호</option>
                <option value="email">이메일</option>
                <option value="roles">직책</option>
            </select>
        </div>
        <div style="width: 20%; margin-left: 15px;">
            <input id="empSearchValue" style="width: 100%; font-size: 1.0rem;" type="text" placeholder="검색어 입력" >
        </div>
    </div>

    <div>
        <table class="table">
            <thead>
            <tr>
                <th style="width: 10%;">사번</th>
                <th style="width: 10%;">이름</th>
                <th style="width: 20%;">생년월일</th>
                <th style="width: 20%;">전화번호</th>
                <th style="width: 20%;">이메일</th>
                <th style="width: 10%;">직책</th>
            </tr>
            </thead>
        </table>

        <div id="employee-add-list" style="overflow-y: auto; height: 470px;">
            <table class="table table-hover">
                <tbody id="employeeTableBody">
                <c:forEach var="employee" items="${employeeList}">
                    <tr class="employee-element">
                        <td style="width: 10%;">${employee.id}</td>
                        <td style="width: 10%;">${employee.name}</td>
                        <td style="width: 20%;"><fmt:formatDate value="${employee.birthDate}" pattern="yyyy-MM-dd" /></td>
                        <td style="width: 20%;">${employee.phoneNumber}</td>
                        <td style="width: 20%;">${employee.email}</td>
                        <td style="width: 10%;">${employee.roles}</td>
                    </tr>
                </c:forEach>
                <!-- 추가 행들 -->
                </tbody>
            </table>
        </div>

    </div>
</div>


<script src="/resources/js/footer.js"></script>
<script src="/resources/js/employee-list.js"></script>
</body>
</html>