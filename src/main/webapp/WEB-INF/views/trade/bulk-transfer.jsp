<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌관리 >
        계좌이체 >
        대량이체</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/bulk-transfer.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌관리 > </h5>
        <h5>&nbsp 계좌이체 > </h5>
        <h5>&nbsp&nbsp;대량이체</h5>

    </div>
    <div>
        <h3>대량이체</h3>
        <hr>
    </div>
    <div id="register-result-toggle-btn">
        <%--  등록 버튼 클릭시 registration-page 열림 --%>
        <button  class="active-toggle-btn">등록</button>
        <%--  결과확인 버튼 클릭시 result-confirmation-page 열림 --%>
        <button class="off-toggle-btn">결과확인</button>
    </div>
    <%--  registration-page  --%>
    <container id="registration-page">
        <%--  계좌선택 섹션  --%>
        <section>
            <h4>대량이체출금정보</h4>
            <hr>
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info">
                    <div><span>업무계좌</span> <br> <span>0001-000xxxxxx-xxx</span></div>
                    <div>
                        <input type="button" value="계좌조회 >">
                    </div>
                </div>
                <div class="account-balance">
                    계좌잔액 <span id="account-balance"> &nbsp  &nbsp  &nbsp  &nbsp  &nbsp</span> 원 | 이체가능금액 <span id="transferable-amount"> &nbsp  &nbsp  &nbsp  &nbsp  &nbsp </span> 원
                </div>
            </div>
            <%--계좌비밀번호 table--%>
            <table class="common-table">
                <tr>
                    <th><label for="withdrawal-account-number">계좌비밀번호</label></th>
                    <td>
                        <input placeholder="비밀번호 입력" type="password" id="withdrawal-account-number">
                        <button id="check-withdrawal-account-btn" class="update-btn" type="button" data-account-type="withdrawal" data-bs-toggle="modal" data-bs-target="#search-modal-account">확인</button>
                    </td>
                </tr>
                <tr>
                    <th><label for="withdrawal-product-name">이체일</label></th>
                    <td>
                        <input type="date" id="withdrawal-product-name">
                    </td>
                </tr>
                <tr>
                    <th><label for="description">비고</label></th>
                    <td>
                        <div >
                            <label><input checked type="radio" name="salaryType" value="directInput"> 직접입력 &nbsp &nbsp</label>
                            <label><input type="radio" name="salaryType" value="monthlySalary"> 월급여 &nbsp &nbsp</label>
                            <label><input type="radio" name="salaryType" value="bonus"> 상여금</label>
                        </div>
                        <input placeholder="10자 이내  입력" type="text" id="description">
                    </td>
                </tr>
            </table>
        </section>
        <%--    입금계좌정보 테이블    --%>
        <section>
            <h4>입금계좌정보</h4>
            <hr>
            <div class="table-top-btns">
                <div>
                    <input type="button" value="파일등록">
                    <input type="button" value="개별추가">
                </div>
                <div>
                    <select>
                        <option value="" disabled selected>검색조건</option>
                        <option value="조건1">조건 1</option>
                        <option value="조건2">조건 2</option>
                        <option value="조건3">조건 3</option>
                    </select>
                    <input type="text" placeholder="검색어 입력">
                </div>
            </div>
            <table id="bulk-transfer-info" class="common-table">
                <thead>
                <tr>
                    <th><label>NO.</label></th>
                    <th><label>입금계좌번호</label></th>
                    <th><label>이체금액(원)</label></th>
                    <th><label>한글금액표시(원)</label></th>
                    <th><label>받는분</label></th>
                    <th><label>받는분 통장표시</label></th>
                </tr>
                </thead>
                <tbody>
                <%--  동적으로 직원계좌정보 생성됨 --%>
                </tbody>
            </table>

            <div class="result-content">
                <div>
                    총 등록건수(건) <br>
                    <span>302</span>(건)
                </div>
                <div>
                    받는분 정상(건) <br>
                    <span>300</span>(건)
                </div>
                <div>
                    받는분 불일치(건) <br>
                    <span>1</span>(건)
                </div>
                <div>
                    받는분 오류(건) <br>
                    <span>1</span>(건)
                </div>
            </div>
            <ul class="warning-writer">
                <li>입력하신 받는분 정보가 조회 결과와 일치하는지 꼭 확인하세요.</li>
                <li>받는분 조회 결과에 ‘오류’ 건이 있는 경우, 잘못 입력한 내용이 없는지 다시 한번 계좌정보를 확인해 주세요.</li>
            </ul>
        </section>
    </container>

    <%--  result-confirmation-page  --%>
    <container id="result-confirmation-page">result</container>
</div>
<script src="/resources/js/footer.js"></script>
</body>

</html>
