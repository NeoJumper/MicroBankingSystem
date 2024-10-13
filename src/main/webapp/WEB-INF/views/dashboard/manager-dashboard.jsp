<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>매니저 대시보드</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">
    <h1>매니저 대시보드</h1>

    <!-- 탭 구성: 일별, 주간별, 월별 거래량 -->
    <ul class="nav nav-tabs" id="transactionTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="daily-tab" data-bs-toggle="tab" data-bs-target="#daily" type="button"
                    role="tab" aria-controls="daily" aria-selected="true">일별 거래량
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="weekly-tab" data-bs-toggle="tab" data-bs-target="#weekly" type="button"
                    role="tab" aria-controls="weekly" aria-selected="false">주간별 거래량
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="monthly-tab" data-bs-toggle="tab" data-bs-target="#monthly" type="button"
                    role="tab" aria-controls="monthly" aria-selected="false">월별 거래량
            </button>
        </li>
    </ul>

    <!-- 탭 내용 -->
    <div class="tab-content" id="transactionTabContent">
        <!-- 일별 거래량 탭 -->
        <div class="tab-pane fade show active" id="daily" role="tabpanel" aria-labelledby="daily-tab">
            <h3>일별 거래량</h3>
            <div class="row">
                <div class="col-6">
                    <canvas id="dailyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
                <div class="col-6">
                    <canvas id="dailyTransactionChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>

        <!-- 주간별 거래량 탭 -->
        <div class="tab-pane fade" id="weekly" role="tabpanel" aria-labelledby="weekly-tab">
            <div class="row">
                <div class="col-6">
                    <h3>주간별 거래량</h3>
                    <canvas id="weeklyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>

        <!-- 월별 거래량 탭 -->
        <div class="tab-pane fade" id="monthly" role="tabpanel" aria-labelledby="monthly-tab">
            <div class="row">
                <div class="col-6">
                    <h3>월별 거래량</h3>
                    <canvas id="monthlyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-6">
            <h3>직원별 거래량 비교</h3>
            <canvas id="employeeTransactionChart" width="400" height="200"></canvas>
        </div>
    </div>

</div>

<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/manager-dashboard.js"></script>

</body>
</html>
