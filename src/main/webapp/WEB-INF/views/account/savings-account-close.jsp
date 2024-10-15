<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css" />

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
                <button id="check-withdrawal-account-btn" class="basic-btn" type="button" data-account-type="withdrawal" data-bs-toggle="modal" data-bs-target="#search-modal-account">계좌조회</button>
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


    <h3>해지 적용 이율</h3>
    <table class="common-table">
        <thead>
        <tr>
            <th>예치 기간</th>
            <th>해지 이율</th>
            <th>가입 상품 적용 이율</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1개월 미만</td>
            <td>약정 당시의 정기 적금 금리</td>
            <td>2%</td>
        </tr>
        <tr>
            <td>1개월 이상 ~ 6개월 미만</td>
            <td>약정 금리의 50%</td>
            <td>1.0%</td>
        </tr>
        <tr>
            <td>6개월 이상 ~ 12개월 미만</td>
            <td>약정 금리의 60%</td>
            <td>1.2%</td>
        </tr>
        <tr>
            <td>12개월 이상</td>
            <td>약정 금리의 70%</td>
            <td>1.4%</td>
        </tr>
        <tr>
            <td>만기일 달성</td>
            <td>약정 당시의 정기 적금 금리 + 우대 이율 적용</td>
            <td>(2.0 + 0.5)%</td>
        </tr>
        <tr>
            <td>만기 후 기간</td>
            <td>약정 당시의 만기 후 이율 적용</td>
            <td>0.5%</td>
        </tr>
        </tbody>
    </table>
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
        <tbody>
        <tr>
            <td style="background-color: red; color: white;">중도 해지</td>
            <td>200일</td>
            <td>2024-10-01</td>
            <td>2025-10-01</td>
            <td>2025-01-15</td>
            <td>1.2%</td>
            <td>100,010,000</td>
        </tr>
        </tbody>
    </table>

    <h3>예금 예상 이자 및 총 금액</h3>
    <table class="common-table">
        <thead>

        <tr>
            <th>이자 계산 방식</th>
            <th>이율</th>
            <th>우대 이율</th>
            <th>이자 (세전)</th>
            <th>이자 (세후)</th>
            <th>세율</th>
            <th>지급 총 금액</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>단리</td>
            <td>1.3%</td>
            <td>0%</td>
            <td>10,000</td>
            <td>9,500</td>
            <td>15.4%</td>
            <td>100,010,000</td>
        </tr>
        </tbody>
    </table>

    <table class="common-table">
        <tbody>
        <tr>
            <th><label for="savings-account-close-password">해지계좌 비밀번호</label></th>
            <td><input id="savings-account-close-password" type="password"><button class="basic-btn" id="savings-account-close-validate">비밀번호 인증</button></td>
        </tr>
        </tbody>
    </table>
</div>
<script src="/resources/js/footer.js"></script>
</body>

</html>
