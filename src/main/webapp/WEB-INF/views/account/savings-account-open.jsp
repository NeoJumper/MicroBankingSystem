<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/savings-account-open.css"/>
    <!-- Flatpickr CSS 파일 포함 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <!-- Flatpickr JS 파일 포함 -->
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">
    <div id="title">
        <h5>계좌 관리 > </h5>
        <h5>&nbsp 정기 적금 ></h5>
        <h5>&nbsp 계좌 개설 </h5>
    </div>
    <br>

    <div>
        <h3>고객 개설 정보 입력</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th>고객번호</th>
            <td style="display: flex; align-items: center;">
                <input type="text" id="customer-id-input" readonly>
                <button data-bs-toggle="modal" data-bs-target="#search-customer-modal" type="button"
                        id="customer-id-search-btn" class="btn basic-btn">
                    <span class="bi bi-search search-icon-margin"></span> 찾기
                </button>
            </td>
            <th>고객인증</th>
            <td>
                <button class="basic-btn">고객인증</button>
            </td>

        </tr>
        <tr>
            <th>고객명</th>
            <td><input type="text" id="customer-name-input" disabled></td>
            <th>적금 통장 비밀번호</th>
            <td><input type="password" id="savings-account-password-input"></td>

        </tr>
    </table>
    <br>
    <div>


    </div>
    <div>
        <h3>적금 상품 정보</h3>
        <hr>
    </div>
    <table class="common-table">
        <tr>
            <th><label for="product-period-input">적금 상품 번호</label></th>
            <td>
                <input type="text" id="product-id-input" disabled>
                <button data-bs-toggle="modal" data-bs-target="#search-modal-product" type="button"
                        id="search-modal-product-btn" class="btn basic-btn">
                    <span class="bi bi-search search-icon-margin"></span> 상품조회
                </button>
            </td>
        </tr>
        <tr>
            <th><label for="product-period-input">적금 상품 이름</label></th>
            <td>
                <input type="text" id="product-name-input" disabled>
            </td>
        </tr>
        <tr>
            <th>적금이율</th>

            <td>
                <input type="text" id="savings-interest-input" disabled>
            </td>

        </tr>

        <tr>
            <th><label for="product-period-input">적금 가입 기간/ 약정 기간</label></th>
            <td>
                <input type="text" id="product-period-input" disabled>
            </td>
        </tr>
        <tr>
            <th>신규일자/가입일자</th>
            <td><input type="date" id="savings-account-start-date-input" disabled></td>
        </tr>
        <tr>
            <th>만기일자</th>
            <td><input type="text" id="savings-account-end-date-input" disabled></td>
        </tr>
    </table>
    <br>
    <br>

    <div>
        <h3>적금 계좌 개설 정보</h3>
        <hr>
    </div>

    <table class="common-table">
        <tr>
            <th>우대이율</th>
            <td><input type="text" id="preferred-interest-input" style="text-align: right"> %</td>
        </tr>
        <tr>
            <th>총 이자율</th>
            <td><input type="text" id="total-interest-input" disabled style="text-align: right"> %</td>
        </tr>
        <tr>
            <th>원금</th>
            <td>

                <input type="text" id="auto-transfer-amount-input" style="text-align: right">원
                <input id = "init-balance-span" class="mt-1 krw-amount-input" style="display: none">
                <div class="button-group">
                    <button type="button" class="amount-btn" data-amount="1000000">100만</button>
                    <button type="button" class="amount-btn" data-amount="500000">50만</button>
                    <button type="button" class="amount-btn" data-amount="300000">30만</button>
                    <button type="button" class="amount-btn" data-amount="100000">10만</button>
                    <button type="button" class="amount-btn" data-amount="50000">5만</button>
                    <button type="button" class="amount-btn" data-amount="10000">1만</button>

                </div>

            </td>
        </tr>

        <tr>
            <th>만기예상약정이자</th>
            <td><input type="text" id="expected-maturity-interest-input" disabled style="text-align: right">

            </td>
        </tr>
        <tr>
            <th>만기예상금액(세전)</th>
            <td>
                <input type="text" id="expected-maturity-totalAmount-input" disabled style="text-align: right">
            </td>
        </tr>
        <tr>
            <th>담당자</th>
            <td>
                <input type="text" id="emp-name-input" disabled>
                <input type="hidden" id="emp-id-hidden" disabled>
            </td>

        </tr>
        <tr>
            <th>개설지점</th>
            <td>
                <input type="text" id="branch-name-input" disabled>
                <input type="hidden" id="branch-id-hidden" disabled>
            </td>
        </tr>
    </table>
    <br>


    <div>
        <h3>자동이체 여부</h3>
        <hr>
    </div>
    <div class="auto-transfer-radio-container">
        <input type="radio" id="yes-automatic-transfer" name="option-automatic-transfer" value="y">
        <label for="yes-automatic-transfer">예</label>
        <input type="radio" id="no-automatic-transfer" name="option-automatic-transfer" value="n" checked>
        <label for="no-automatic-transfer">아니오</label>
    </div>

<%--    <div>--%>
<%--        <h3>첫달 현금 여부</h3>--%>
<%--        <hr>--%>
<%--    </div>--%>
<%--    <div class="auto-transfer-radio-container">--%>
<%--        <input type="radio" id="yes-cash-transfer" name="option-automatic-transfer" value="y-c">--%>
<%--        <label for="yes-cash-transfer">예</label>--%>
<%--        <input type="radio" id="no-cash-transfer" name="option-automatic-transfer" value="n-c" checked>--%>
<%--        <label for="no-cash-transfer">아니오</label>--%>
<%--    </div>--%>

    <br><br>

    <div id="automatic-transfer-info-div" style="display: none;">
        <div>
            <h3>자동이체 정보 입력</h3>
            <hr>
        </div>
        <table class="common-table">
            <tr>
                <th>이체 주기</th>
                <td>
                    <select id="auto-transfer-period">
                        <option value="01" selected>1개월</option>
                        <option value="02">2개월</option>
                        <option value="03">3개월</option>
                        <option value="06">6개월</option>
                    </select>
                </td>

                <th>자동이체 시작일</th>
                <td><input type="date" id="auto-transfer-start-date-input"></td>

            </tr>

        </table>


        <div>
            <h3>출금 계좌 인증</h3>
            <hr>
        </div>

        <table class="common-table">
            <tr>
                <th><label for="auto-transfer-account-number">출금계좌번호</label></th>
                <td><input disabled type="text" id="auto-transfer-account-number">
                    <button data-bs-toggle="modal" data-bs-target="#search-modal-transfer-account" type="button"
                            id="search-transfer-account-modal-btn" class="btn basic-btn">
                        <span class="bi bi-search search-icon-margin"></span> 찾기
                    </button>

                </td>
            </tr>
            <tr>
                <th><label for="auto-transfer-account-password">출금계좌 비밀번호</label></th>
                <td>
                    <input type="text" id="auto-transfer-account-password">
                    <button type="button" class="btn basic-btn"  id="check-transfer-account-btn"> 인증</button>

                </td>
            </tr>
        </table>

        <div id="checked-transfer-account-div" style="display: none">
            <div>
                <h3>출금 계좌 정보 입력</h3>
                <hr>
            </div>
            <table class="common-table">
                <tr>
                    <th><label for="auto-transfer-account-customer-name">예금주명</label></th>
                    <td><input disabled type="text" id="auto-transfer-account-customer-name"></td>
                </tr>
                <tr>
                    <th><label for="auto-transfer-amount">자동이체금액</label></th>
                    <td>
                        <div><span id="over-account-balance"></span></div>
                        <input  type="text" id="auto-transfer-amount"> 원
                        <label id="account-balance-label" style="display: none">
                            | 계좌 잔액: <span id="account-balance"></span> 원
                        </label>
                        <!-- 잔액 부족 경고 메시지 -->
                        <div id="balance-warning" style="display: none; color: red; font-weight: bold;">
                            잔액이 부족합니다.
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>출금(자동이체) 계좌 통장 표시</th>
                    <td><input value="정기적자동이체" type="text" id="auto-transfer-account-description-input" ></td>
                </tr>
                <tr>
                    <th>적금 통장 표시</th>
                    <td><input value="정기적자동이체" type="text" id="savings-account-description-input" ></td>
                </tr>


            </table>
        </div>
    </div>
    <div style="text-align:center;">
        <button class="btn basic-btn" id="savings-account-create-btn" >계좌 개설</button>

    </div>


    <input type="hidden" id="page-saving-account" value="saving">

</div>


<%@ include file="/resources/components/modal/transfer-account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/search-modal-customer.jsp" %>
<%@ include file="/resources/components/modal/search-modal-product.jsp" %>
<%@ include file="/resources/components/modal/result-modal-savings-open-account.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/savings-account-open.js"></script>
</body>
</html>
