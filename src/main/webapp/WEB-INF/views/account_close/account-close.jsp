<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌 해지 신청</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-close.css"/>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="mainArea">
    <div style="margin-bottom: 17px;">
        <h5>예금 관리 >&nbsp</h5>
        <h5>계좌 해지</h5>
    </div>
    <hr class="m-0 border border-dark border-2">
    <div>

        <div style="margin : 0 100px;">
            <h3>계좌 해지 신청</h3>
            <div id="search-content">
                <div><input placeholder="계좌번호 입력" id="search-bar"></div>
                <button id="check-withdrawal-account" class="update-btn" type="button" data-bs-toggle="modal" data-bs-target="#accountSearchModal">검색</button>
            </div>
            <h4 style="margin-top: 10px">계좌 정보</h4>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">고객 이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="withdrawal-customer-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-1">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">계좌번호</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="withdrawal-account-number" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-1">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5">상품이름</div>
                <div class="col-6 d-flex align-items-center ms-5">
                    <input id="withdrawal-product-name" style="direction: rtl; display:block; width:100%" disabled="">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">

            <div>
                <h4>예금 예상 이자 및 총 금액</h4>
                <hr class="m-0 border border-dark border-2">
                <table class="table">
                    <tbody>
                    <tr>
                        <th style="width: 5%;background-color: #F5F5F5;">총 기간</th>
                        <th style="width: 5%;background-color: #F5F5F5;">총 이율</th>
                        <th style="width: 10%;background-color: #F5F5F5;">예상이자</th>
                        <th style="width: 10%;background-color: #F5F5F5;">총 금액</th>
                        <th style="width: 10%;background-color: #F5F5F5;">세율</th>
                        <th style="width: 10%;background-color: #F5F5F5;">지급 총 금액</th>
                    </tr>
                    </tbody>
                </table>

                <div id="employee-add-list" style="overflow-y: auto; height: 53px;">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td style="width: 5%;">600일</td>
                            <td style="width: 5%;">4.01 %</td>
                            <td style="width: 10%;">10,000</td>
                            <td style="width: 10%;">100,000,000</td>
                            <td style="width: 10%;">14.5 %</td>
                            <td style="width: 10%;">10,010,000</td>
                        </tr>
                        </tbody>
                    </table>
                    <hr class="m-0 border border-dark border-2">
                </div>
            </div>
            <h4>계좌 비밀번호</h4>
            <hr class="m-0 border border-dark border-2">
            <div class="d-flex">
                <div class="col-3 text-center py-3" style="background-color: #F5F5F5; margin-right: 10px;">계좌 비밀번호</div>
                <div id="account-pw-container">
                    <input id="account-pw" style=" display:block; width:100%">
                    <input id="input-confirm" type="submit" value="확인">
                </div>
            </div>
            <hr class="m-0 border border-dark border-2">

            <div id="submit">
                <input type="submit" value="해지 신청">
            </div>
        </div>
    </div>

</div>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/account-transfer.js"></script>
<script src="/resources/js/footer.js"></script>
</body>

</html>