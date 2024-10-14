<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/trade-list.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌 조회 > </h5>
        <h5>&nbsp 거래 내역 조회 </h5>
    </div>

    <div>
        <h3 class="mt-3">거래내역조회</h3>
        <hr>
    </div>
    <table class="common-table">
        <tbody>
        <tr>

            <th><label for="acc-id-input">계좌번호</label></th>
            <td class="acc-id-input">
                <input disabled type="text" id="acc-id-input">
                <button type="button"  data-bs-toggle="modal" data-bs-target="#search-modal-account" class="basic-btn" id="search-account-btn">계좌검색</button>
            </td>
        </tr>
        </tbody>
    </table>

    <div>
        <h3 class="mt-3">상세조건입력</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>대분류</th>
            <td>
                <div id = "major-category-button-group" class="button-group">
                    <input type="radio" id="common-transfer" value="common" name="major-category" checked>
                    <label for="common-transfer">계좌 이체</label>
                    <input type="radio" id="bulk-transfer" value="bulk" name="major-category">
                    <label for="bulk-transfer">대량 이체</label>
                </div>
            </td>
        </tr>
        <tr id="trade-type-row">
            <th>조건별조회</th>
            <td>
                <div class="button-group" id ="trade-type-div">
                    <button type="button" class="trade-status-search-btn active" >전체</button>
                    <button type="button" class="trade-status-search-btn" value="DEPOSIT">입금</button>
                    <button type="button" class="trade-status-search-btn" value="WITHDRAWAL">출금</button>
                    <button type="button" class="trade-status-search-btn" value="CLOSE">해지</button>
                    <button type="button" class="trade-status-search-btn" value="OPEN">가입</button>
                </div>

            </td>
        </tr>
        <tr>
            <th>조회기간 설정</th>
            <td>
                <div class="button-group">
                    <input type="radio" id="period" name="search-period-type" value="period" checked>
                    <label for="period">기간 조회</label>
                    <input type="radio" id="monthly" name="search-period-type" value="monthly">
                    <label for="monthly">월별 조회</label>
                </div>

            </td>
        </tr>
        <tr id="search-monthly-tr">
            <th><label for="year-search-btn">월별 조회</label></th>
            <td>

                <select id="year-search-btn">
                    <option value="2024">2024</option>
                    <option value="2023">2023</option>
                    <option value="2022">2022</option>
                    <option value="2021">2021</option>
                    <option value="2020">2020</option>
                </select>
                년
                &nbsp &nbsp &nbsp

                <select id="month-search-btn">
                    <option value="1">1월</option>
                    <option value="2">2월</option>
                    <option value="3">3월</option>
                    <option value="4">4월</option>
                    <option value="5">5월</option>
                    <option value="6">6월</option>
                    <option value="7">7월</option>
                    <option value="8">8월</option>
                    <option value="9">9월</option>
                    <option value="10">10월</option>
                    <option value="11">11월</option>
                    <option value="12">12월</option>
                </select>
                <label for="month-search-btn">월</label>&nbsp &nbsp
            </td>
        </tr>
        <tr id="search-period-tr">
            <th><label for="period-start-input">기간조회</label></th>
            <td>
                <div class="button-group">
                    <button type="button" class="trade-period-search-btn" value="1d">당일</button>
                    <button type="button" class="trade-period-search-btn" value="1w">1주일</button>
                    <button type="button" class="trade-period-search-btn" value="1m">1개월</button>
                    <button type="button" class="trade-period-search-btn active" value="3m">3개월</button>
                    <button type="button" class="trade-period-search-btn" value="6m">6개월</button>
                    <button type="button" class="trade-period-search-btn" id="custom-period-btn">직접입력</button>
                </div>
                <div id="period-search-area">
                    <div class="period-search-container" id="period-search-container">
                        <label for="period-start-input">시작일 :</label>
                        <input type="date" id="period-start-input">
                        <label for="period-end-input">종료일 :</label>
                        <input type="date" id="period-end-input">

                    </div>
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
    </table>


    <div class="row justify-content-center mb-5">
        <button class="col-1 basic-btn" id="trade-list-search-btn">조회하기</button>
    </div>

    <!--  이체 조회 결과 -->
    <div id="common-transfer-select-result" style="display: none;">
        <div>
            <h3 class="mt-3">총 입출금 금액</h3>
            <hr>
            <div id="trade-total-balance">
                <label for="total-deposit-input" >입금액</label>
                <input type="text" id="total-deposit-input" disabled>

                <label for="total-withdraw-input">출금액</label>
                <input type="text" id="total-withdraw-input" disabled>

            </div>
        </div>
        <div>
            <h3 class="mt-3">총 거래 내역</h3>
            <hr>
            <table class="common-table">
                <thead>

                <th>순번</th>
                <th><label id="trade-date">거래일시</label></th>
                <th><label id="acc-id">대상계좌</label></th>
                <th><label id="target-acc-id">상대계좌</label></th>
                <th><label id="amount">거래액</label></th>
                <th><label id="balance">잔액</label></th>
                <th><label id="cash-indicator">현금여부</label></th>
                <th><label id="trade-type">거래유형</label></th>
                <th><label id="status">상태</label></th>
                </thead>
                <tbody id="trade-result-tbody">

                </tbody>
            </table>
        </div>

        <hr>
        <div id="pagination">


        </div>
    </div>

    <!--  대량이체 조회 결과 -->
    <div id="bulk-transfer-select-result" style="display: none;">
        <div>
            <h3 class="mt-3">총 거래 내역</h3>
            <hr>
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
                </thead>
                <tbody id="bulk-transfer-result-tbody">

                </tbody>
            </table>
        </div>
    </div>


    <input type="hidden" id="businessDay" value="${businessDay}">
</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/trade-list.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>