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
                    <input type="radio" id="common-transfer" value="common" name="major-category" checked>
                    <label for="common-transfer">전체</label>
                    <input type="radio" id="reserve-transfer" value="reserve" name="major-category" >
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
        <tr>
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
                <select id="end-time-btn">
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
            </td>

        </tr>
        <tr>
            <th>고객 검색</th>

        </tr>
    </table>


    <div class="row justify-content-center mb-5">
        <button class="col-1 basic-btn" id="trade-list-search-btn">조회하기</button>
    </div>

    <!--  이체 조회 결과 -->
    <div id="common-transfer-select-result" style="display: none;">
        <div>
            <h3 class="mt-3">총 입출금 금액</h3>

            <div id="trade-total-balance">
                <label for="total-deposit-input" >입금액</label>
                <input type="text" id="total-deposit-input" style="text-align: right;" disabled >

                <label for="total-withdraw-input">출금액</label>
                <input type="text" id="total-withdraw-input" style="text-align: right;" disabled>

            </div>
        </div>
        <div>
            <h3 class="mt-3">총 거래 내역</h3>

            <table class="common-table">
                <thead>
                <th style="width: 8%"><label id="trade-type">거래유형</label></th>
                <th style="width: 10%"><label id="trade-date">거래일</label></th>
                <th style="width: 15%"><label id="acc-id">대상계좌</label></th>
                <th style="width: 15%"><label id="target-acc-id">상대계좌</label></th>
                <th style="width: 14%"><label id="amount">거래액</label></th>
                <th style="width: 14%"><label id="balance">잔액</label></th>
                <th style="width: 7%"><label id="cash-indicator">현금여부</label></th>
                <th style="width: 7%"><label id="status">상태</label></th>
                <th style="width: 20%"><label></label></th>
                </thead>
                <tbody id="trade-result-tbody">

                </tbody>
            </table>
        </div>


        <div id="pagination">


        </div>
    </div>

    <!--  대량이체 조회 결과 -->
    <div id="bulk-transfer-select-result" style="display: none;">
        <div>
            <h3 class="mt-3">총 거래 내역</h3>

            <table class="common-table">
                <thead>

                <th>순번</th>
                <th><label id="bulk-transfer-trade-date">거래일시</label></th>
                <th><label id="bulk-transfer-acc-id">비고</label></th>
                <th><label id="bulk-transfer-total-amount">총 이체금액</label></th>
                <th><label id="bulk-transfer-failure-cnt">실패건수</label></th>
                <th><label id="bulk-transfer-success-cnt">성공건수</label></th>
                <th><label id="bulk-transfer-total-cnt">총건수</label></th>
                <th><label id="bulk-transfer-status">상태</label></th>
                <th><label id="bulk-transfer-detail"></label></th>
                </thead>
                <tbody id="bulk-transfer-result-tbody">

                </tbody>
            </table>
        </div>
    </div>


    <input type="hidden" id="businessDay" value="${businessDay}">
</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/reserve-list.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>