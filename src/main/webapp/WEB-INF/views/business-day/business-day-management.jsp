<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/business-day-management.css"/>

</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>영업일 관리 > </h5>
        <h5> 영업일 변경</h5>
    </div>
    <div>
        <h3>영업일 달력</h3>
        <div class="row">
            <div id="calendar" class="col-5">
                <div id="calendar-header">
                    <button id="prev" class="basic-btn">
                        <i class="bi bi-chevron-left"></i>
                    </button>
                    <span id="month-year"></span>
                    <button id="next" class="basic-btn">
                        <i class="bi bi-chevron-right"></i>
                    </button>
                </div>
                <table class="common-table calendar">
                    <thead >
                    <tr>
                        <th>일</th>
                        <th>월</th>
                        <th>화</th>
                        <th>수</th>
                        <th>목</th>
                        <th>금</th>
                        <th>토</th>
                    </tr>
                    </thead>
                    <tbody id="calendar-body">
                    <!-- 날짜가 동적으로 삽입됩니다 -->
                    </tbody>
                </table>
            </div>

            <div id="business-day-change-area" class="col-6">
                <table class="common-table">
                    <tbody>
                    <tr>
                        <th style="width: 30%">
                            현재 영업일
                        </th>
                        <td>
                            <input id="current-business-day" type="text" disabled>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 30%">
                            다음 영업일
                        </th>
                        <td>
                            <input id="next-business-day" type="text" disabled>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div id="btn-area">
                    <button id="business-day-reset-modal-btn" class="basic-btn" type="button" data-bs-toggle="modal"
                            data-bs-target="#business-day-reset-modal">영업일 되돌리기
                    </button>


                    <button id="business-day-update-modal-btn" class="basic-btn" type="button" data-bs-toggle="modal"
                            data-bs-target="#business-day-update-modal">영업일 변경
                    </button>
                </div>

            </div>

        </div>


    </div>

</div>




<%@ include file="/resources/components/modal/business-day-update-modal.jsp" %>
<%@ include file="/resources/components/modal/business-day-reset-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/business-day-management.js"></script>
</body>
</html>