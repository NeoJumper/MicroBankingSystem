$(document).ready(function () {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const lastYear = currentYear - 1;

    // 계좌 개설 차트 데이터 요청
    $.ajax({
        url: '/api/dashboard/accountOpenRatioChart',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            renderAccountOpenRatioChart(response);
        },
        error: function (xhr, status, error) {
            console.error("계좌 개설 데이터를 가져오는 데 실패했습니다:", error);
        }
    });

    // 거래량 비교 차트 데이터 요청
    $.ajax({
        url: '/api/dashboard/yearlyTransactionComparison',
        method: 'GET',
        data: {
            lastYear: lastYear.toString(),
            currentYear: currentYear.toString()
        },
        dataType: 'json',
        success: function (response) {
            renderYearlyTransactionComparisonChart(response, lastYear, currentYear);
        },
        error: function (xhr, status, error) {
            console.error("거래량 비교 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
});

// 계좌 개설 차트를 그리는 함수
function renderAccountOpenRatioChart(data) {
    const labels = generateMonthlyLabels();
    const myOpenCounts = labels.map(label => {
        const foundData = data.find(item => item.tradeMonth === label);
        return foundData ? foundData.openCount : 0;
    });
    const totalOpenCounts = labels.map(label => {
        const foundData = data.find(item => item.tradeMonth === label);
        return foundData ? foundData.totalOpenCount : 0;
    });

    const chartData = {
        labels: labels,
        datasets: [
            {
                label: '나의 계좌 개설 수',
                data: myOpenCounts,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                type: 'bar'
            },
            {
                label: '전체 계좌 개설 수',
                data: totalOpenCounts,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 2,
                type: 'line',
                fill: false
            }
        ]
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: true, // 비율 유지
        animation: {
            duration: 1000,
            easing: 'easeInOutQuart',
            delay: (context) => {
                let delay = 0;
                if (context.type === 'data' && context.mode === 'default') {
                    delay = context.dataIndex * 50 + context.datasetIndex * 100;
                }
                return delay;
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    };

    const ctx = document.getElementById('myAccountOpenRatioChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: chartOptions
    });
}

// 거래량 비교 차트를 그리는 함수
function renderYearlyTransactionComparisonChart(data, lastYear, currentYear) {
    const transactionTypes = ['현금 거래', '이체', '해지'];

    // 각 연도별로 데이터를 나누어서 매핑
    const lastYearCounts = [];
    const currentYearCounts = [];

    transactionTypes.forEach(type => {
        const lastYearData = data.find(item => item.TRANSACTIONYEAR === lastYear.toString() && item.TRANSACTIONTYPE === type);
        const currentYearData = data.find(item => item.TRANSACTIONYEAR === currentYear.toString() && item.TRANSACTIONTYPE === type);

        // 데이터가 없으면 0으로 설정
        lastYearCounts.push(lastYearData ? lastYearData.TRANSACTIONCOUNT : 0);
        currentYearCounts.push(currentYearData ? currentYearData.TRANSACTIONCOUNT : 0);
    });

    // 차트 데이터를 설정
    const chartData = {
        labels: transactionTypes,
        datasets: [
            {
                label: `${lastYear}년 거래량`,
                data: lastYearCounts,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            },
            {
                label: `${currentYear}년 거래량`,
                data: currentYearCounts,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }
        ]
    };

    // 차트 옵션을 설정
    const chartOptions = {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        responsive: true,
        animation: {
            duration: 1000,
            easing: 'easeInOutQuart'
        }
    };

    // 차트를 렌더링
    const ctx = document.getElementById('myYearlyTransactionComparisonChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: chartOptions
    });
}


// 월별 라벨을 생성하는 함수
function generateMonthlyLabels() {
    const labels = [];
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth() + 1;

    for (let i = 0; i < 12; i++) {
        let year = currentYear;
        let month = currentMonth - i;
        if (month <= 0) {
            month += 12;
            year -= 1;
        }
        const monthLabel = year + '-' + (month < 10 ? '0' + month : month);
        labels.unshift(monthLabel);
    }
    return labels;
}
