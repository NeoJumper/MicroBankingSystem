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
        <button class="off-toggle-btn"><a style="text-decoration-line: none;" href="/page/employee/bulk-transfer">등록</a></button>
        <%--  결과확인 버튼 클릭시 result-confirmation-page 열림 --%>
        <button class="active-toggle-btn">결과확인</button>
    </div>
    <%--  result-confirmation-page  --%>
    <container id="result-confirmation-page">
        <%--  계좌선택 섹션  --%>
        <section>
            <h4>대량이체 결과확인</h4>
            <hr>
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info-result">
                    <div><span>업무계좌</span> <br> <span>0001-000xxxxxx-xxx</span></div>
                    <hr>
                    <div class="account-result-contents">
                        <div>
                            <div>등록금액 <span>50,000,000</span>원</div>
                            <div>등록자 <span>김00(ABCD)</span></div>
                        </div>
                        <div>
                            <div>비고 <span>상여금</span></div>
                            <div>등록건수 <span>3</span>건</div>
                        </div>
                    </div>
                </div>

                <div class="account-balance">
                    등록일시 <span id="register-date">2024.10.10 15:30:00</span>
                </div>
            </div>
        </section>
        <%--    입금계좌정보 테이블    --%>
        <section>
            <h4>입금계좌정보</h4>
            <hr>
            <div class="table-top-btns">
                <div>
                    <input type="button" value="파일등록">
                    <input type="button" value="인쇄">
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
                    <th><label><input type="checkbox"></label></th>
                    <th><label>처리결과</label></th>
                    <th><label>입금계좌번호</label></th>
                    <th><label>이체금액(원)</label></th>
                    <th><label>받는분</label></th>
                    <th><label>받는분 통장표시</label></th>
                </tr>
                </thead>
                <tbody>
                <%--  동적으로 직원계좌정보 생성됨 --%>
                </tbody>
            </table>
            <div class="table-top-btns">
                <div>
                   총 <span>3</span> 건
                </div>
                <div>
                    총 이체금액 <span>10000</span> 원
                </div>
            </div>

        </section>
        <section class="submit-btns">
            <input class="basic-btn" type="button" value="오류건재전송"
                   style="background-color: white; color: black; border: 1px solid #D5D5D5">
            <input class="basic-btn" type="button" value="이체확인증">
            <input class="basic-btn" type="button" value="이체확인증(일괄)">
        </section>
    </container>
</div>
<script src="/resources/js/footer.js"></script>
</body>

</html>
