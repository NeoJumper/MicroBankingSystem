<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/commonTable.css"/>
    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        function customerSearchModalPopup() {
            $("#showSearchCustomerModal").modal("show");
        }

        $(document).ready(function(){
            $("#customerNumber").on("click", function() {
                customerSearchModalPopup();
            });
        });

    </script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<%@ include file="/resources/components/account/showSearchCustomerModal.jsp" %>
<div id="mainArea">
    <h3 style="font-weight:bold; color:#5F5F5F;">계좌 개설</h3>

    <br><br>


    <table class="commonTable">
        <tr>
            <th>고객번호</th>
            <td><input type="text" id="customerNumber"></td>
            <th>비밀번호</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>계약일자</th>
            <td><input type="date"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>잔액(KRW)</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>기준이율</th>
            <td><input type="text" placeholder="%"></td>

            <th>우대이율</th>
            <td><input type="text" placeholder="%"></td>
        </tr>
        <tr>
            <th>축 이자율</th>
            <td><input type="text" placeholder="%"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
    </table>

    <div id="modalArea">

    </div>
</div>
<script src="/resources/js/footer.js"></script>
</body>
</html>
