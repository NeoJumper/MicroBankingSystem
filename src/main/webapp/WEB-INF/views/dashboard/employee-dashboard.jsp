<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <h5>실적 관리 > 행원 실적 관리</h5>
    <div class="row">
        <div class="col-12">
            <span>전체 계좌 대비 나의 개설 거래 수</span>
            <canvas id="myAccountOpenRatioChart" width="400" height="200"></canvas>

        </div>
    </div>

</div>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/employee-dashboard.js"></script>

</body>

</html>
