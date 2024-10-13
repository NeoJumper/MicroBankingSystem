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
        <div class="col-6">
            <span>작년 대비 계좌 개설 수 증감 현황</span>
            <canvas id="myChart" width="400" height="200"></canvas>
        </div>
        <div class="col-6">
            <span>시간대별 거래량 수 현황</span>
            <canvas id="myChart" width="400" height="200"></canvas>
        </div>
    </div>

</div>
<script src="/resources/js/footer.js"></script>
<script>
    // 차트 데이터를 설정
    const data = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June'],
        datasets: [{
            label: 'Sales',
            data: [10, 20, 30, 40, 50, 60],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    };

    // 차트 옵션을 설정
    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    };

    // 차트를 렌더링
    const myChart = new Chart(
        document.getElementById('myChart'),
        config
    );
</script>
<script></script>
</body>

</html>
