<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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
                <button type="button"  data-bs-toggle="modal" data-bs-target="#search-modal-account" class="search-btn" id="search-account-btn">계좌검색</button>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="row justify-content-center mb-5">
        <button class="col-1 update-btn">조회하기</button>
        <button class="col-1 detail-search-btn">상세 검색</button>
    </div>

    <div>
        <h3 class="mt-3">상세조건입력</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>조건별조회</th>
            <td>
                <div class="button-group">
                    <button type="button" class="trade-status-search-btn active">전체</button>
                    <button type="button" class="trade-status-search-btn">입금</button>
                    <button type="button" class="trade-status-search-btn">출금</button>
                    <button type="button" class="trade-status-search-btn">해지</button>
                    <button type="button" class="trade-status-search-btn">가입</button>
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
                <button class="search-btn" id="month">월별 조회</button>
            </td>
        </tr>
        <tr id="search-period-tr">
            <th><label for="period-start-input">기간조회</label></th>
            <td>
                <div class="button-group">
                    <button type="button" class="trade-period-search-btn">당일</button>
                    <button type="button" class="trade-period-search-btn">1주일</button>
                    <button type="button" class="trade-period-search-btn">1개월</button>
                    <button type="button" class="trade-period-search-btn active">3개월</button>
                    <button type="button" class="trade-period-search-btn">6개월</button>
                    <button type="button" class="trade-period-search-btn" id="custom-period-btn">직접입력</button>
                </div>
                <div id="period-search-area">
                    <div class="period-search-container" id="period-search-container">
                        <label for="period-start-input">시작일 :</label>
                        <input type="date" id="period-start-input">
                        <label for="period-end-input">종료일 :</label>
                        <input type="date" id="period-end-input">
                        <button type="button" class="search-btn">날짜검색</button>
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
        <div id="trade-result-div"></div>
    </div>


</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/trade-list.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>