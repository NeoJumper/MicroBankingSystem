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
    <link rel="stylesheet" type="text/css" href="/resources/css/page/employee-list.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>행원 관리 > </h5>
        <h5>&nbsp 행원 목록</h5>
    </div>
    <div>
        <h3>행원 목록</h3>
    </div>
    <div class="d-flex justify-content-end mb-3">
        <div style="width: 10%; margin-left: 15px;">
            <select id="emp-search-option">
                <option selected value="" disabled>검색조건</option>
                <option value="id">사번</option>
                <option value="name">이름</option>
                <option value="email">이메일</option>
                <option value="phone_number">전화번호</option>
                <option value="address">주소</option>
                <option value="roles">직책</option>
            </select>
        </div>
        <div style="width: 20%; margin-left: 15px;">
            <input id="emp-search-value" style="width: 100%; font-size: 1.0rem;" type="text" placeholder="검색어 입력" >
        </div>
    </div>

    <div>
        <table class="common-table" style="margin-bottom: 0px">
            <thead>
            <tr>
                <th style="width: 10%; text-align: center;">사번</th>
                <th style="width: 10%; text-align: center;">이름</th>
                <th style="width: 20%; text-align: center;">이메일</th>
                <th style="width: 20%; text-align: center;">전화번호</th>
                <th style="width: 20%; text-align: center;">주소</th>
                <th style="width: 10%; text-align: center;">직책</th>
            </tr>
            </thead>
            <tbody id="emp-table-body">

            </tbody>
        </table>

    </div>
    <div id="pagination">
</div>


<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/employee-list.js"></script>
</body>
</html>