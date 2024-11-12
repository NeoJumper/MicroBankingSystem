<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>실적 관리 > 행원 실적 관리</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/page/employee-dashboard.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Canvas 크기 제한 */
        canvas {
            max-width: 100%;
            max-height: 400px; /* 최대 높이를 제한 */
        }
    </style>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
    <h5>실적 관리 ></h5>
        <h5>&nbsp 행원 실적 관리 </h5>
    </div>
    <div class="row">
        <div class="col-5 chart-container">
            <span>작년 대비 올해 나의 거래량 비교</span>
            <canvas id="myYearlyTransactionComparisonChart"></canvas>
        </div>
        <div class="col-5 chart-container">
            <span>전체 계좌 대비 나의 개설 거래 수</span>
            <canvas id="myAccountOpenRatioChart"></canvas>
        </div>

        <div class="col-5 chart-container">
            <span>이번 달 나의 거래 유형</span>
            <canvas id="myMonthlyTransactionTypeChart"></canvas>
        </div>

        <div class="col-5 chart-container">
            <span>이번 달 나의 일별 거래량 추이</span>
            <canvas id="myDailyTransactionVolumeChart"></canvas>
        </div>
    </div>
</div>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/employee-dashboard.js"></script>
</body>
</html>
