<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>계좌관리 >
        계좌이체 >
        즉시/예약 이체</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-transfer.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌관리 > </h5>
        <h5>&nbsp 계좌이체 > </h5>
        <h5>&nbsp&nbsp;즉시/예약 이체</h5>
    </div>

    <container id="registration-page">
        <div>
            <h3>출금계좌정보</h3>
            <hr>
        </div>
        <%--  계좌선택 섹션  --%>
        <section>

            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info">
                    <div>
                        <span id="withdrawal-customer-name"></span>
                        <span id="withdrawal-product-name"></span>
                        <br>
                        <span id="withdrawal-account-number">계좌를 선택해주세요.</span>
                    </div>
                    <div>
                        <button id="withdrawal-account-check-btn" class="basic-btn" type="button"
                                data-account-type="withdrawal" data-bs-toggle="modal"
                                data-bs-target="#search-modal-account">
                            계좌조회
                        </button>
                    </div>
                </div>
                <div class="account-balance d-flex align-items-center">
                    <div class="me-2">
                        계좌잔액<span id="account-balance" style="margin-left: 20px;">0</span> 원
                    </div>
                    <div class="mx-3 transfer-possible-amount">|</div>
                    <div class="ms-2 transfer-possible-amount">
                        이체가능금액<span id="transferable-amount" style="margin-left: 20px">0</span> 원
                    </div>
                    <div id="select-transfer-limit">
                        <span class="tooltip-link">이체한도조회
                        </span>
                    </div>
                    <div id="select-transfer-limit-tooltip">
                        <div><span
                                style="width: 110px; display: inline-block">1회 이체 한도  </span><span>:&nbsp &nbsp </span><span
                                id="per-trade-limit" class="amount-span">0 &nbsp 원</span></div>
                        <div><span
                                style="width: 110px; display: inline-block">1일 이체 한도  </span><span>:&nbsp &nbsp </span><span
                                id="daily-limit" class="amount-span">0 &nbsp 원</span></div>
                        <div><span style="width: 110px; display: inline-block">금일 출금액  </span><span>:&nbsp &nbsp </span><span
                                id="transfer-amount-of-today" class="amount-span">0 &nbsp 원</span></div>
                        <div><span style="width: 110px; display: inline-block">금일 이체 한도</span><span>:&nbsp &nbsp </span><span
                                id="transferable-amount-limit-of-today" class="amount-span">0 &nbsp 원</span></div>
                    </div>
                </div>


            </div>
            <div>
                <h3>거래정보</h3>
            </div>
            <%--계좌비밀번호 table--%>
            <table class="common-table account-transfer-page-table">
                <tr>
                    <th><label for="transfer-amount">이체금액</label></th>
                    <td>
                        <div><span id="over-account-balance"></span></div>
                        <input disabled type="text" id="transfer-amount" style="text-align: right"> 원
                        <div class="button-group">
                            <button type="button" class="amount-btn" disabled>100만</button>
                            <button type="button" class="amount-btn" disabled>50만</button>
                            <button type="button" class="amount-btn" disabled>10만</button>
                            <button type="button" class="amount-btn" disabled>5만</button>
                            <button type="button" class="amount-btn" disabled>1만</button>
                            <button type="button" class="amount-btn" disabled>전액</button>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th><label>예약 여부</label></th>
                    <td>
                        <div id="reserve-button-group" class="button-group mb-2">
                            <input type="radio" id="immediate-transfer-btn" name="scheduled-status" checked>
                            <label for="immediate-transfer-btn">즉시 이체</label>
                            <input class="ms-3" type="radio" id="scheduled-transfer-btn" name="scheduled-status">
                            <label for="scheduled-transfer-btn">예약 이체</label>
                        </div>
                        <div id="reserve-time-select-div" style="overflow: hidden; transition: height 0.5s ease;">
                            <input type="date" value="1234" style="margin-right: 20px; height: 50px">
                            <div id="time-search-container">
                                <select id="time-search-btn">
                                    <option value="1">9:30 ~ 10:30</option>
                                    <option value="1">10:30 ~ 11:30</option>
                                    <option value="1">11:30 ~ 12:30</option>
                                    <option value="1">12:30 ~ 13:30</option>
                                    <option value="1">13:30 ~ 14:30</option>
                                    <option value="1">14:30 ~ 15:30</option>
                                    <option value="1">15:30 ~ 16:00</option>
                                </select>
                                <img class="search-icon" type="submit" src="/resources/assets/timer.jpg">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="description">비고</label></th>
                    <td><input type="text" id="description"></td>
                </tr>
                <tr>
                    <th>담당자</th>
                    <td><input type="text" id="emp-name-input" value="${employeeName}" disabled></td>
                </tr>
                <tr>
                    <th>등록일자</th>
                    <td>
                        <fmt:parseDate value="${tradeDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedTradeDate"/>
                        <input type="text" id="start-date-input"
                               value="<fmt:formatDate value='${parsedTradeDate}' pattern='yyyy-MM-dd' />" disabled>
                    </td>
                </tr>
            </table>
        </section>


        <div>
            <h3 class="mt-3">입금계좌정보</h3>

        </div>
        <table class="common-table account-transfer-page-table">
            <tbody>
            <tr>
                <th><label for="deposit-account-number">입금계좌번호</label></th>
                <td><input disabled type="text" id="deposit-account-number">
                    <button id="deposit-account-check-btn" class="basic-btn" type="button" data-account-type="deposit"
                            data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회
                    </button>
                </td>
            </tr>
            <tr>
                <th><label for="deposit-customer-name">고객명</label></th>
                <td><input disabled type="text" id="deposit-customer-name"></td>
            </tr>
            </tbody>
        </table>

        <div>
            <h3 class="mt-3">비밀번호 인증</h3>

        </div>
        <table class="common-table account-transfer-page-table">
            <tbody>
            <tr>
                <th><label for="transfer-account-password">출금계좌 비밀번호</label></th>
                <td>
                    <input id="transfer-account-password" type="password">
                    <button class="basic-btn" id="account-transfer-validate">비밀번호 인증</button>
                </td>

            </tr>
            </tbody>
        </table>
    </container>


    <div class="row justify-content-center mb-5">
        <button disabled id="account-transfer-submit" class="col-1 basic-btn">이체하기</button>
    </div>
</div>

<%@ include file="/resources/components/close-overlay.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/transfer-result-modal.jsp" %>
<script src="/resources/js/page/account-transfer.js"></script>
<script src="/resources/js/footer.js"></script>
</body>
</html>