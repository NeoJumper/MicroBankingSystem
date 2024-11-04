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
    <div id="title">
        <h5>계좌 관리 > </h5>
        <h5>&nbsp 정기 적금 ></h5>
        <h5>&nbsp 계좌 해지 </h5>
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

    <div class="common-account-detail" style="display:none;">
        <h3>적금 상세 정보</h3>
        <table class="common-table">
            <tbody>
            <tr>
                <th>상품 종류</th>
                <td id="savings-account-product-type"></td>
                <th>적용 금리</th>
                <td id="savings-account-interest-calculation-method"></td>
            </tr>
            </tbody>
        </table>
    </div>


    <div id="account-interest-table">
        <h3>적금 해지 이율표</h3>
        <table class="common-table">
            <thead>
            <tr>
                <th>해지 종류</th>
                <th>예치 기간</th>
                <th>해지 이율(차등율)</th>
                <th>가입 상품 적용 이율(경과율)</th>
            </tr>
            <tr id="under-1m">
                <th rowspan="6" style="border-right: 1px solid var(--little-dark-gray)">중도 해지</th>
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
                <th style="border-right: 1px solid var(--little-dark-gray)">만기 해지</th>
                <td>만기 시</td>
                <td>약정 당시의 정기 적금 금리 + 우대 이율</td>
                <td class="rate"><span class="dynamic-rate"></span></td>
            </tr>
            <tr id="post-maturity-1m">
                <th rowspan="2" style="border-right: 1px solid var(--little-dark-gray)">만기 후 해지</th>
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

    <div id="common-account-area">
        <h3>계좌 해지 정보</h3>
        <table class="common-table">
            <thead>
            <tr>
                <th>해지 종류</th>
                <th>개설일</th>
                <th>만기일</th>
                <th>해지 신청일</th>
                <th>최종 적용 이율</th>
                <th>지급 총 금액</th>
            </tr>
            </thead>
            <tbody id="savings-account-close-info">
            <tr class="saving-account-close-empty-message">
                <td colspan="7" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                    해지할 계좌를 선택해 주십시오
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="fixed-account-area" style="display: none">
        <h3>자동이체 정보</h3>
        <table class="common-table">
            <thead>
            <tr>
                <th>자동이체 출금 계좌</th>
                <th>원금</th>
                <th>자동이체 총 횟수</th>
                <th>자동이체 시작일</th>
                <th>자동이체 종료일</th>


            </tr>
            </thead>
            <tbody id="savings-account-auto-transfer">
            <tr class="saving-account-close-empty-message">
                <td colspan="9" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                    해지할 계좌를 선택해 주십시오
                </td>
            </tr>
            </tbody>
        </table>

        <h3>예금 예상 금액 정보</h3>
        <table class="common-table">
            <thead>
            <tr>
                <th>이자 계산 방식</th>
                <th>이율</th>
                <th>우대 이율</th>
                <th>실제 적용 이율</th>
                <th>세율</th>
                <th>이자 (세전)</th>
                <th>이자 (세후)</th>
                <th>잔액</th>
                <th>지급 총 금액</th>
            </tr>
            </thead>
            <tbody id="savings-account-total-cash">
            <tr class="saving-account-close-empty-message">
                <td colspan="9" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                    해지할 계좌를 선택해 주십시오
                </td>
            </tr>
            </tbody>
        </table>
    </div>



    <div class="flexible-account-area" style="display: none">
        <h3>월별 이자 내역</h3>
        <table class="common-table" id="savings-account-flexible-monthly-interest-list">
            <thead>
            <th>생성일자</th>
            <th>계좌 잔액</th>
            <th>기본이율</th>
            <th>우대이율</th>
            <th>이자 금액</th>
            </thead>
            <tbody>
            <tr class="saving-account-close-empty-message">
                <td colspan="5" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                    해지할 계좌를 선택해 주십시오
                </td>
            </tr>
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

    <div class="row justify-content-center mb-5">
        <button id="saving-account-close-submit-btn" class ="basic-btn col-1" disabled>해지 신청</button>
    </div>
</div>
<script src="/resources/js/footer.js"></script>

<script src="/resources/js/page/savings-account-close.js"></script>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>

</body>

</html>
