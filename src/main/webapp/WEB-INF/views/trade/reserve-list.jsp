<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/reserve-list.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>계좌 조회 > </h5>
        <h5>&nbsp 예약 내역 조회 </h5>
    </div>

    <div>
        <h3 class="mt-3">예약내역조회</h3>

    </div>

    <table class="common-table">
        <tr>
            <th>대분류</th>
            <td>
                <div id = "major-category-button-group" class="button-group">
                    <input type="radio" id="reserve-transfer" value="reserve" name="major-category" checked>
                    <label for="reserve-transfer">예약 이체</label>
                    <input type="radio" id="auto-transfer" value="auto" name="major-category">
                    <label for="auto-transfer">자동 이체</label>
                </div>
            </td>
        </tr>

        <tr>
            <th>정렬조건</th>
            <td>
                <div class="button-group">
                    <input type="radio" id="latest" name="search-sort" value="latest" checked>
                    <label for="latest">최신순</label>
                    <input type="radio" id="oldest" name="search-sort" value="oldest">
                    <label for="oldest">오래된 순</label>
                </div>

            </td>
        </tr>
        <%--<tr>
            <th>시간대 조건</th>
            <td id = "select-time-td">
                <select id="start-time-btn">
                    <option value="1">9:00</option>
                    <option value="1">10:00</option>
                    <option value="1">11:00</option>
                    <option value="1">12:00</option>
                    <option value="1">13:00</option>
                    <option value="1">14:00</option>
                    <option value="1">15:00</option>
                    <option value="1">16:00</option>
                    <option value="1">17:00</option>
                    <option value="1">18:00</option>
                    <option value="1">19:00</option>
                    <option value="1">20:00</option>
                    <option value="1">21:00</option>
                    <option value="1">22:00</option>
                </select>
                ~
                <select id="end-time-btn">
                    <option value="1">9:00</option>
                    <option value="1" selected>10:00</option>
                    <option value="1">11:00</option>
                    <option value="1">12:00</option>
                    <option value="1">13:00</option>
                    <option value="1">14:00</option>
                    <option value="1">15:00</option>
                    <option value="1">16:00</option>
                    <option value="1">17:00</option>
                    <option value="1">18:00</option>
                    <option value="1">19:00</option>
                    <option value="1">20:00</option>
                    <option value="1">21:00</option>
                    <option value="1">22:00</option>
                </select>
            </td>

        </tr>
        <tr>
            <th>고객 검색</th>

        </tr>
--%>
    </table>


    <div class="row justify-content-center mb-5">
        <button class="col-1 basic-btn" id="trade-list-search-btn">조회하기</button>
    </div>

    <!--  이체 조회 결과 -->
    <table class="common-table" id="transfer-result">
        <thead>
        <th style="width: 8%"><label id="transfer-type">이체 유형</label></th>
        <th style="width: 15%"><label id="trade-date">출금계좌</label></th>
        <th style="width: 15%"><label id="acc-id">입금계좌</label></th>
        <th style="width: 15%"><label id="target-acc-id">실행날짜 및 시간</label></th>
        <th style="width: 14%"><label id="amount">이체 금액</label></th>
        <th style="width: 14%"><label id="balance">비고</label></th>
        <th style="width: 10%"><label id="cash-indicator">성공 여부</label></th>
        <th style="width: 10%"><label id="status">재전송 횟수</label></th>
        </thead>
        <tbody id="reserve-trade-result-tbody">
        <tr class="reserve-empty-message">
            <td colspan="9" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                조회 조건을 입력하시오
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" id="businessDay" value="${businessDay}">
</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/reserve-list.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>