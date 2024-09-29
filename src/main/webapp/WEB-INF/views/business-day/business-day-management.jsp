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
        <h5>영업일 관리</h5>
    </div>
    <div>

        <h3>영업일 관리</h3>

        <div style="margin : 0 200px;">
            <hr class="m-0 border border-dark border-2">
            <div class = "d-flex">
                <div class="col-3 text-center py-3" style = "background-color: #F5F5F5">현재 영업일</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="current-business-day"  style="direction: rtl; display:block; width:100%" disabled></input>
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">
            <div class = "d-flex">
                <div class="col-3 text-center py-3" style = "background-color: #F5F5F5">다음 영업일</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="next-business-day" style="direction: rtl; display:block; width:100%" disabled></input>
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">

            <div class="d-flex justify-content-end mt-4 mx-0">
                <div >
                    <button id="business-day-update-modal-btn" class="search-btn" type="button" data-bs-toggle="modal" data-bs-target="#business-day-update-modal">영업일 변경</button>
                </div>
            </div>
        </div>


    </div>

</div>

<%@ include file="/resources/components/modal/business-day-update-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/business-day-management.js"></script>
</body>
</html>