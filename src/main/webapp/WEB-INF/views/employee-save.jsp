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
        <h5>&nbsp 행원 등록 </h5>
    </div>
    <div>
        <h3>행원 추가</h3>
        <hr>
    </div>
    <table class="commonTable">

        <tr>
            <th>이름</th>
            <td><input type="text"></td>
            <th>생년월일</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>직급</th>
            <td><input type="date"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><input type="text"></td>

            <th>이메일</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>지점명</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
    </table>
    <div class= "d-flex justify-content-end mt-4 mb-4">
        <button class ="update-btn">
            추가하기
        </button>
    </div>
    <div>
        <h3>행원 추가 대기 목록</h3>
        <hr>
        <table class="table">
            <thead>
            <tr>
                <th style="width: 10%;">사번</th>
                <th style="width: 10%;">이름</th>
                <th style="width: 20%;">생년월일</th>
                <th style="width: 20%;">전화번호</th>
                <th style="width: 20%;">이메일</th>
                <th style="width: 10%;">지점</th>
                <th style="width: 10%;">직책</th>
            </tr>
            </thead>
        </table>

        <div id="employee-add-list" style="overflow-y: auto; height: 250px;">
            <table class="table table-hover">
                <tbody>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <!-- 추가 행들 -->
                </tbody>
            </table>
        </div>
        <div class= "d-flex justify-content-end mt-4 mb-4">
            <button class ="update-btn">
                등록하기
            </button>
        </div>
    </div>
</div>


<script src="/resources/js/footer.js"></script>
</body>
</html>