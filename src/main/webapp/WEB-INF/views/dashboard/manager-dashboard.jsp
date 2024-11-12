<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지점 관리 > 지점 운영</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/manager-dashboard.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>

<div id="main-area">
    <div id="title">
        <h5>지점 관리 ></h5>
        <h5>&nbsp지점 운영</h5>
    </div>

    <div style="display: flex; align-items: center">
  <%--      <input type="date" style="border-radius: 5px; color: var(--dark-gray); width: 300px; margin-left: 10px">
        <button class="basic-btn">
            조회
        </button>--%>
        <button class="basic-btn" onclick="saveChartsAsPDF()">차트 저장</button>
    </div>

<%--    <div id="cash-transfer-amount" class="rounded-boxes">
        <div class="rounded-box red">
            현금 입금: <span>54,368,000,000</span>원
        </div>
        <div class="rounded-box yellow">
            현금 출금: <span>345,609,000</span>원
        </div>
        <div class="rounded-box green">
            현금 잔액: <span>913,133,000,000</span>원
        </div>
        <div class="rounded-box blue">
            현재 거래량: <span>3,131,132</span> 건
        </div>
    </div>--%>


    <div class="row">
        <div class="col-5 chart-container">
            <h3 class="tab-title">직원별 거래량 비교</h3>
            <canvas id="employeeTransactionChart"></canvas>
        </div>
        <div class="col-5 chart-container">
            <h3 class="tab-title">사원별 거래 유형 및 거래량</h3>
            <canvas id="employeeTransactionTypeChart"></canvas>
        </div>
    </div>

    <!-- 탭 구성: 일별, 주간별, 월별 거래량 -->
    <ul class="nav nav-tabs" id="transactionTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="daily-tab" data-bs-toggle="tab" data-bs-target="#daily" type="button"
                    role="tab" aria-controls="daily" aria-selected="true">일별 거래량
            </button>
        </li>
<%--        <li class="nav-item" role="presentation">
            <button class="nav-link" id="weekly-tab" data-bs-toggle="tab" data-bs-target="#weekly" type="button"
                    role="tab" aria-controls="weekly" aria-selected="false">주간별 거래량
            </button>
        </li>--%>
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
                <div class="col-5 chart-container">
                    <h3 class="tab-title">일별 거래량</h3>
                    <canvas id="dailyTransactionVolumeChart"></canvas>
                </div>
                <div class="col-5 chart-container chart-table-combo">
                    <div>
                        <h3 class="tab-title">일별 거래 유형</h3>
                        <canvas id="dailyTransactionChart"></canvas>
                    </div>
                    <div>
                        <table id="daily-transaction-table" class="common-table">
                            <tbody>
                            <tr>
                                <th>입금</th>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th>출금</th>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th>송금</th>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th>가입</th>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th>해지</th>
                                <td>test</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

        <!-- 주간별 거래량 탭 -->
        <div class="tab-pane" id="weekly" role="tabpanel" aria-labelledby="weekly-tab">
            <div class="row">
                <div class="col-5 chart-container">
                    <h3 class="tab-title">주간별 거래량</h3>
                    <canvas id="weeklyTransactionVolumeChart"></canvas>
                </div>
            </div>
        </div>

        <!-- 월별 거래량 탭 -->
        <div class="tab-pane" id="monthly" role="tabpanel" aria-labelledby="monthly-tab">
            <div class="row">
                <div class="col-5 chart-container">
                    <h3 class="tab-title">월별 거래량</h3>
                    <canvas id="monthlyTransactionVolumeChart" width="400" height="200"></canvas>
                </div>
                <div class="col-5 chart-container chart-table-combo">
                    <div>
                    <h3 class="tab-title">월별 거래 유형</h3>
                    <canvas id="monthlyTransactionChart"></canvas>
                    </div>
                    <div>
                    <table id="monthly-transaction-table" class="common-table">

                        <tbody>
                        <tr>
                            <th>입금</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>출금</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>송금</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>가입</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>해지</th>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/manager-dashboard.js"></script>

</body>
</html>
