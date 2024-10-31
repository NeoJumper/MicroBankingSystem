<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>

</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌 관리 > </h5>
        <h5>&nbsp 정기 적금 ></h5>
        <h3>&nbsp 계좌 해지 </h3>
    </div>

    <h3>계좌 정보</h3>
    <table class="common-table">
        <tr>
            <th><label for="savings-account-close-number">해지 계좌 번호</label></th>
            <td><input disabled type="text" id="savings-account-close-number">
                <button id="check-withdrawal-account-btn" class="basic-btn" type="button" data-bs-toggle="modal"
                        data-bs-target="#search-modal-account">계좌조회
                </button>
            </td>
        </tr>
        <tr>
            <th><label for="savings-account-product-name">상품명</label></th>
            <td><input disabled type="text" id="savings-account-product-name"></td>
        </tr>
        <tr>
            <th><label for="customer-name">고객명</label></th>
            <td><input disabled type="text" id="customer-name"></td>
        </tr>
    </table>

    <div id="close-savings-account-interest">
        <h3>해지 적용 이율</h3>

        <table class="common-table">
            <tr>
                <th>예치 기간</th>
                <th>해지 이율(차등율)</th>
                <th>가입 상품 적용 이율(경과율)</th>
            </tr>
            <tr id="under-1m">
                <td>1개월 미만</td>
                <td>연 0.1%</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="under-3m">
                <td>1개월 이상 ~ 3개월 미만</td>
                <td>연 0.15%</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="under-6m">
                <td>3개월 이상 ~ 6개월 미만</td>
                <td>연 0.2%</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="over-6m">
                <td>6개월 이상 ~ 9개월 미만</td>
                <td>60% (기본금리 x 차등율 x 경과일수 / 계약일수)</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="between-9-11m">
                <td>9개월 이상 ~ 11개월 미만</td>
                <td>70% (기본금리 x 차등율 x 경과일수 / 계약일수)</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="over-11m">
                <td>11개월 이상</td>
                <td>90% (기본금리 x 차등율 x 경과일수 / 계약일수)</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="maturity">
                <td>만기 시</td>
                <td>약정 당시의 정기 적금 금리 + 우대 이율</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="post-maturity-1m">
                <td>만기 후 1개월 이내</td>
                <td>지급 당시 기본금리의 1/2</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="post-maturity-1m-plus">
                <td>만기 후 1개월 초과</td>
                <td>지급 당시 기본금리의 1/4</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
        </table>

    </div>

    <div id="fixed-account-area">
        <h3>계좌 해지 정보</h3>

        <table class="common-table">
            <thead>
            <tr>
                <th>해지 종류</th>
                <th>총 납입일</th>
                <th>개설일</th>
                <th>만기일</th>
                <th>만기 신청일</th>
                <th>최종 적용 이율</th>
                <th>지급 총 금액</th>
            </tr>
            </thead>
            <tbody id="savings-account-close-info">
           <%-- <tr>
                <td style="background-color: red; color: white;">중도 해지</td>
                <td>200일</td>
                <td>2024-10-01</td>
                <td>2025-10-01</td>
                <td>2025-01-15</td>
                <td>1.2%</td>
                <td>100,010,000</td>
            </tr>--%>
            </tbody>
        </table>

        <h3>예금 예상 이자 및 총 금액</h3>
        <table class="common-table" >
            <thead>
            <tr>
                <th>이자 계산 방식</th>
                <th>이율</th>
                <th>우대 이율</th>
                <th>세율</th>
                <th>이자 (세전)</th>
                <th>이자 (세후)</th>
                <th>지급 총 이자</th>
                <th>잔액</th>
                <th>지급 총 금액</th>
            </tr>
            </thead>
            <tbody id="savings-account-total-cash">


            </tbody>
        </table>
    </div>

    <table class="common-table">
        <tbody>
        <tr>
            <th><label for="savings-account-close-password">해지계좌 비밀번호</label></th>
            <td>
                <input id="savings-account-close-password" type="password">
                <button class="basic-btn" id="savings-account-close-validate">비밀번호 인증</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script src="/resources/js/footer.js"></script>

<script src="/resources/js/page/savings-account-close.js"></script>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>

</body>

</html>
