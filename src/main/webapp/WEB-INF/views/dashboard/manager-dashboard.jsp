<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>지점 관리 > 지점 운영</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/manager-dashboard.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">
    <div>
        <h5>지점 관리 ></h5>
        <h5>&nbsp지점 운영</h5>
    </div>

    <div class="row" id="cash-transfer-amount">
        <div class="rounded-boxes">
            <div class="rounded-box yellow">
                <span>내용 1</span>
            </div>
            <div class="rounded-box red">
                <span>내용 2</span>
            </div>
            <div class="rounded-box blue">
                <span>내용 3</span>
            </div>
            <div class="rounded-box green">
                <span>내용 4</span>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-6">
            <h3>직원별 거래량 비교</h3>
            <canvas id="employeeTransactionChart" width="400" height="200"></canvas>
        </div>
        <div class="col-6">
            <h3 class="tab-title">사원별 거래 유형 및 거래량</h3>
            <canvas id="employeeTransactionTypeChart" width="800" height="400"></canvas>
        </div>
    </div>

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
        <div class="tab-pane show active" id="daily" role="tabpanel" aria-labelledby="daily-tab">
            <div class="row">
                <div class="col-6">
                    <h3 class="tab-title">일별 거래량</h3>
                    <canvas id="dailyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
                <div class="col-6">
                    <h3 class="tab-title">일별 거래 유형</h3>
                    <canvas id="dailyTransactionChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>

        <!-- 주간별 거래량 탭 -->
        <div class="tab-pane" id="weekly" role="tabpanel" aria-labelledby="weekly-tab">
            <div class="row">
                <div class="col-6">
                    <h3 class="tab-title">주간별 거래량</h3>
                    <canvas id="weeklyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>

        <!-- 월별 거래량 탭 -->
        <div class="tab-pane" id="monthly" role="tabpanel" aria-labelledby="monthly-tab">
            <div class="row">
                <div class="col-6">
                    <h3 class="tab-title">월별 거래량</h3>
                    <canvas id="monthlyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/manager-dashboard.js"></script>

</body>
</html>
