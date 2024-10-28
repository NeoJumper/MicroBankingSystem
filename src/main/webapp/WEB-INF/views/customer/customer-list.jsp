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
    <link rel="stylesheet" type="text/css" href="/resources/css/page/customer-list.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>고객 관리 > </h5>
        <h5>&nbsp 고객 목록</h5>
    </div>
    <div>
        <h3>고객 목록</h3>
    </div>
    <div class="d-flex justify-content-end mb-3">
        <div style="width: 10%; margin-left: 15px;">
            <select id="customer-search-option">
                <option selected value="" disabled>검색조건</option>
                <option value="id">고객번호</option>
                <option value="name">이름</option>
                <option value="phoneNumber">전화번호</option>
                <option value="birthDate">생년월일</option>
                <option value="email">이메일</option>
                <option value="roles">직책</option>
            </select>
        </div>
        <div style="width: 20%; margin-left: 15px;">
            <input id="customer-search-value" style="width: 100%; font-size: 1.0rem;" type="text" placeholder="검색어 입력" >
        </div>
    </div>



    <div >
        <table class="common-table">
            <thead>
            <tr>
                <th style="text-align: center;">고객번호</th>
                <th style="text-align: center;">이름</th>
                <th style="text-align: center;">전화번호</th>
                <th style="text-align: center;">보안등급</th>
                <th style="text-align: center;">이메일</th>

            </tr>
            </thead>
            <tbody id="customer-table-body">
            </tbody>
        </table>
    </div>
    <div id="pagination">
</div>


<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/customer-list.js"></script>
</body>
</html>