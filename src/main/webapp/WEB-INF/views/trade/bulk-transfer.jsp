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
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info">
                    <div><span>업무계좌</span> <br> <span>0001-000xxxxxx-xxx</span></div>
                    <div>
                        <input type="button" value="계좌조회 >">
                    </div>
                </div>
                <div class="account-balance">
                    계좌잔액 <span></span> | 이체가능금액 <span></span>
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
                        <div>
                            <label><input type="radio" name="salaryType" value="directInput"> 직접입력 &nbsp &nbsp</label>
                            <label><input type="radio" name="salaryType" value="monthlySalary"> 월급여 &nbsp &nbsp</label>
                            <label><input type="radio" name="salaryType" value="bonus"> 상여금</label>
                        </div>
                        <input type="text" id="description">
                    </td>
                </tr>
            </table>

        </section>
        <%--    입금계좌정보 테이블    --%>
        <section></section>
    </container>

    <%--  result-confirmation-page  --%>
    <container id="result-confirmation-page">result</container>
</div>
<script src="/resources/js/footer.js"></script>
</body>

</html>
