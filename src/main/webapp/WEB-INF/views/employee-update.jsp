<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/commonTable.css"/>
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
    </div>
    <div>
        <h3>행원 정보</h3>
        <hr>
    </div>
    <table class="commonTable">

        <tr>
            <th>이름</th>
            <td><input type="text" ></td>
            <th>생년월일</th>
            <td><input type="text" ></td>
        </tr>
        <tr>
            <th>직급</th>
            <td><input type="date" ></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><input type="text" ></td>

            <th>이메일</th>
            <td><input type="text" ></td>
        </tr>
        <tr>
            <th>지점명</th>
            <td><input type="text" ></td>
            <td colspan="2"></td>
        </tr>
    </table>
    <div class="d-flex justify-content-center">
        <div >
            <button class ="update-btn">
                수정사항 저장
            </button>
        </div>

    </div>

</div>


<script src="/resources/js/footer.js"></script>
</body>
</html>