<%@ page import="java.math.BigDecimal" %>
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
    <div id="title">
        <h5>계좌관리 > </h5>
        <h5>&nbsp 계좌이체 > </h5>
        <h5>&nbsp&nbsp;대량이체 > </h5>
        <h5>&nbsp&nbsp;결과확인</h5>

    </div>
    <div>
        <h4>대량이체</h4>
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
            <h4>대량이체 등록정보 및 진행현황</h4>
            <hr>
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info-result">

                    <div class="account-result-contents">
                        <div style="width: 45%">
                            <div><span>업무계좌</span> <br> <span id="withdrawal-account-number">${bulkTransfer.accId}</span></div>
                            <hr>
                            <div class="d-flex flex-column" style="height: 60%">

                                <div class="d-flex">
                                    <span style="width: 120px">등록자</span>
                                    <span>${bulkTransfer.registrantName}</span>
                                </div>
                                <div class="d-flex my-3">
                                    <span style="width: 120px">등록금액</span>
                                    <span><fmt:formatNumber value="${bulkTransfer.registeredAmount}" pattern="#,###"/></span>원
                                </div>
                                <div class="d-flex">
                                    <span style="width: 120px">비고</span>
                                    <span>${bulkTransfer.description}</span>
                                </div>
                            </div>

                        </div>
                        <div style="width: 45%" class="d-flex flex-column align-items-center">
                            <div id="progress-text-area" class="w-100 d-flex justify-content-start">
                           </div>
                            <div class="progress-circle">
                                <svg width="180" height="180" viewBox="0 0 100 100">
                                    <circle class="bg-circle" cx="50" cy="50" r="45"></circle>
                                    <circle class="progress-circle-bar" cx="50" cy="50" r="45"></circle>
                                </svg>
                                <div class="progress-text" id="progress-text">0%</div>
                            </div>

                            <div id="progress-count-area" class="d-flex">
                                <div>등록건수 <span class="fw-bold">${bulkTransfer.totalCnt}</span>&nbsp 건</div>
                                <div>&nbsp &nbsp 성공건수 &nbsp  <span id="success-count" class="text-color-basic">${bulkTransfer.successCnt}</span>&nbsp  건</div>
                                <div>&nbsp &nbsp 실패건수 &nbsp <span id="failure-count" class="text-color-point-red fw-bold">${bulkTransfer.failureCnt}</span>&nbsp  건</div>
                                <div id="progress-top-text">
                                    <span>처리 현황</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="account-balance">
                    등록일시 <span id="register-date">${bulkTransfer.registrationDate}</span>
                </div>

            </div>
        </section>

        <%-- 처리결과 내역서 --%>
        <section id="sectionC">

            <div>
                <h3>처리결과 내역서</h3>
            </div>
            <table class="common-table">
                <tr>
                    <th>거래명</th>
                    <td>
                        <span>대량이체</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>계좌번호</th>
                    <td>
                        <span>${bulkTransfer.accId}</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>등록자</th>
                    <td>
                        <span>${bulkTransfer.registrantName}</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>등록일시</th>
                    <td>
                        <span>${bulkTransfer.registrationDate}</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>등록 금액</th>
                    <td>
                        <span><fmt:formatNumber value="${bulkTransfer.registeredAmount}" pattern="#,###"/></span> 원</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>입금계좌 표시</th>
                    <td>
                        <span>${bulkTransfer.description}</span>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr >
                    <th rowspan="2" style="width: 25%">총 이체건수</th>
                    <td rowspan="2" style="width: 25%"><span class="fw-bold">${bulkTransfer.totalCnt}</span>&nbsp 건</td>

                    <th style="width: 25%">성공건수</th>
                    <td><span class="text-color-basic">${bulkTransfer.successCnt}</span>&nbsp 건</td>

                </tr>
                <tr>
                    <th style="width: 25%">실패건수</th>
                    <td><span class="text-color-point-red fw-bold">${bulkTransfer.failureCnt}</span>&nbsp 건</td>
                </tr>
                <tr >
                    <th rowspan="2" style="width: 25%">총 이체금액</th>
                    <td rowspan="2" style="width: 25%"><span><fmt:formatNumber value="${bulkTransfer.amount}" pattern="#,###"/></span> 원</td>

                    <th style="width: 25%">정상</th>
                    <td><span><fmt:formatNumber value="${bulkTransfer.amount}" pattern="#,###"/></span> 원</td>

                </tr>
                <%
                    BigDecimal registeredAmount = (BigDecimal) request.getAttribute("bulkTransfer.registeredAmount");
                    BigDecimal amount = (BigDecimal) request.getAttribute("bulkTransfer.amount");
                    BigDecimal result = registeredAmount.subtract(amount);
                    request.setAttribute("calculatedAmount", result);
                %>
                <tr>
                    <th style="width: 25%">오류</th>
                    <td><span><fmt:formatNumber value="${calculatedAmount}" pattern="#,###"/></span> 원</td>
                </tr>


            </table>



            </section>

        <%--    입금계좌정보 테이블    --%>
        <section id="sectionB" style="display: none;">
            <h4>대량이체 결과확인</h4>
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
            <div class="tableWrapper">
                <table id="bulk-transfer-info" class="common-table  bulk-insert-table">
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
            </div>

            <div class="table-top-btns">
                <div>
                   총 <span>${bulkTransfer.totalCnt}</span> 건
                </div>
                <div>
                    총 이체금액 <span id="total-transfer-amount" ><fmt:formatNumber value="${bulkTransfer.amount}" pattern="#,###"/></span> 원
                </div>
            </div>

        </section>
        <section class="submit-btns">
            <input id="resend-error-item" class="basic-btn" type="button" value="오류건재전송" disabled>
            <input id="back-btn" class="basic-btn" type="button" value="이전 페이지" disabled>
        </section>
    </container>
</div>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/bulk-transfer-result.js" ></script>
</body>

</html>
