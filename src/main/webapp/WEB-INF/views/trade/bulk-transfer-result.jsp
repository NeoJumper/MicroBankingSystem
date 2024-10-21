<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<%--    <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/exceljs/4.4.0/exceljs.min.js" integrity="sha512-dlPw+ytv/6JyepmelABrgeYgHI0O+frEwgfnPdXDTOIZz+eDgfW07QXG02/O8COfivBdGNINy+Vex+lYmJ5rxw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div>
        <h5>계좌관리 > </h5>
        <h5>&nbsp 계좌이체 > </h5>
        <h5>&nbsp&nbsp;대량이체 > </h5>
        <h5>&nbsp&nbsp;결과확인</h5>

    </div>
    <div>
        <h3>대량이체</h3>

    </div>
    <div class="progress-container">
        <div class="step">
            <div class="circle active">
                <div class="inner-circle"><i class="bi bi-check"></i></div>
            </div>
            <p>1. 이체정보 입력</p>
        </div>
        <div class="line"></div>
        <div class="step">
            <div class="circle active">
                <div class="inner-circle"><i class="bi bi-check"></i></div>
            </div>
            <p>2. 이체정보 확인</p>
        </div>
        <div class="line"></div>
        <div class="step active">
            <div class="circle active">
                <div class="inner-circle active"></div>
            </div>
            <p>3. 이체결과 확인</p>
        </div>
    </div>
    <%--  result-confirmation-page  --%>
    <container id="result-confirmation-page">
        <%--  계좌선택 섹션  --%>
        <section id="sectionA">
            <h4>대량이체 결과확인</h4>
            <hr>
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info-result">
                    <div><span>업무계좌</span> <br> <span>${bulkTransfer.accId}</span></div>
                    <hr>
                    <div class="account-result-contents">
                        <div>
                            <div>등록금액 <span><fmt:formatNumber value="${bulkTransfer.amount}" pattern="#,###"/></span>원</div>
                            <div>등록자 <span>${bulkTransfer.registrantName}</span></div>
                        </div>
                        <div>
                            <div>비고 <span>${bulkTransfer.description}</span></div>
                            <div>
                                등록건수 <span>${bulkTransfer.totalCnt}</span>건
                                &nbsp &nbsp 성공건수 <span>${bulkTransfer.successCnt}</span>건
                                &nbsp &nbsp 실패건수 <span class="text-color-point-red">${bulkTransfer.failureCnt}</span>건
                            </div>
                        </div>
                    </div>
                </div>

                <div class="account-balance">
                    등록일시 <span id="register-date">${bulkTransfer.registrationDate}</span>
                </div>
            </div>
        </section>
        <%--    입금계좌정보 테이블    --%>
        <section id="sectionB">
            <h4>입금계좌정보</h4>
            <div class="table-top-btns">
                <div>
                    <input type="button" value="파일등록">
                    <input type="button" value="인쇄">
                </div>
                <div>
                    <select id="searchCondition">
                        <option value="" disabled selected>검색조건</option>
                        <option value="targetAccId">입금계좌번호</option>
                        <option value="depositor">받는분</option>
                    </select>
                    <input type="text" id="searchInput" placeholder="검색어 입력">
                </div>
            </div>
            <table id="bulk-transfer-info" class="common-table">
                <thead>
                <tr>
                    <th><label><input type="checkbox"></label></th>
                    <th><label>NO.</label></th>
                    <th><label>처리결과</label></th>
                    <th><label>입금계좌번호</label></th>
                    <th><label>이체금액(원)</label></th>
                    <th><label>받는분</label></th>
                    <th><label>받는분 통장표시</label></th>
                    <th><label>비고</label></th>
                </tr>
                </thead>
                <tbody id="bulk-transfer-info-list-body">
                <%--  동적으로 직원계좌정보 생성됨 --%>
                </tbody>
            </table>
            <div class="table-top-btns">
                <div>
                   총 <span>${bulkTransfer.totalCnt}</span> 건
                </div>
                <div>
                    총 이체금액 <span><fmt:formatNumber value="${bulkTransfer.amount}" pattern="#,###"/></span> 원
                </div>
            </div>

        </section>
        <section class="submit-btns">
            <input class="basic-btn" type="button" value="오류건재전송">
            <input class="basic-btn" type="button" value="이체확인증">
            <input class="basic-btn" type="button" value="이체확인증(일괄)">
        </section>
    </container>
</div>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/bulk-transfer-result.js" ></script>
</body>

</html>
