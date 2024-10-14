$(document).ready(function () {
    let dailyTransactionChartInstance = null;
    loadDailyTransactionVolumeChart();
    loadWeeklyTransactionVolumeChart();
    loadMonthlyTransactionVolumeChart();
    // 예시로 branchId 1로 호출
    loadEmployeeTransactionByBranch(1);

    // 페이지 로드 시 일별 거래량 차트 불러오기
    const today = new Date('2024-08-01');
    const formattedToday = today.toISOString().split('T')[0];  // 오늘 날짜 (YYYY-MM-DD)
    loadTransactionData(formattedToday);


    // 거래 유형 차트를 불러오는 함수 (일별)
    function loadTransactionData(date) {
        $.ajax({
            url: '/api/dashboard/dailyTransactionTypes',
            method: 'GET',
            data: { date: date },
            dataType: 'json',
            success: function (response) {
                renderDailyTransactionChart(response);
            },
            error: function (xhr, status, error) {
                console.error("데이터를 가져오는 데 실패했습니다:", error);
            }
        });
    }

    // 차트를 렌더링하는 함수들 (일별, 주간별, 월별)
    function renderDailyTransactionChart(data) {
        const transactionTypes = ['입금', '출금', '송금', '가입', '해지'];
        const transactionCounts = transactionTypes.map(type => {
            const transactionData = data.find(item => item.transactionType === type);
            return transactionData ? transactionData.transactionCount : 0;
        });

        const chartData = {
            labels: transactionTypes,
            datasets: [{
                type: 'doughnut',
                label: '거래 유형별 거래 수',
                data: transactionCounts,
                backgroundColor: [
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        };

        const ctx = document.getElementById('dailyTransactionChart').getContext('2d');

        // 기존 차트가 있으면 제거
        if (dailyTransactionChartInstance) {
            dailyTransactionChartInstance.destroy();
        }

        dailyTransactionChartInstance = new Chart(ctx, {
            type: 'doughnut',
            data: chartData
        });
    }
});

// 일별 거래량 차트 불러오기 함수
function loadDailyTransactionVolumeChart() {
    $.ajax({
        url: '/api/dashboard/dailyTransactionVolume',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            renderDailyTransactionVolumeChart(response);
        },
        error: function (xhr, status, error) {
            console.error("일별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 주간별 거래량 차트 불러오기 함수
function loadWeeklyTransactionVolumeChart() {
    $.ajax({
        url: '/api/dashboard/weeklyTransactionVolume',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            renderWeeklyTransactionVolumeChart(response);
        },
        error: function (xhr, status, error) {
            console.error("주간별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 월별 거래량 차트 불러오기 함수
function loadMonthlyTransactionVolumeChart() {
    $.ajax({
        url: '/api/dashboard/monthlyTransactionVolume',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            renderMonthlyTransactionVolumeChart(response);
        },
        error: function (xhr, status, error) {
            console.error("월별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 일별 거래량 차트 렌더링
function renderDailyTransactionVolumeChart(data) {
    const dates = data.map(item => item.tradeDate);
    const transactionCounts = data.map(item => item.transactionCount);

    const chartData = {
        labels: dates,
        datasets: [{
            label: '일별 거래량',
            data: transactionCounts,
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderColor: 'rgba(153, 102, 255, 1)',
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('dailyTransactionVolumeChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: chartData,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// 주간별 거래량 차트 렌더링
function renderWeeklyTransactionVolumeChart(data) {
    const allWeeks = generateWeekLabels(); // 주 라벨 생성
    const transactionCounts = allWeeks.map(week => {
        const foundData = data.find(item => item.weekOfYear === week);
        return foundData ? foundData.transactionCount : 0; // 값이 없는 주는 0
    });

    const chartData = {
        labels: allWeeks,
        datasets: [{
            label: '주간별 거래량',
            data: transactionCounts,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('weeklyTransactionVolumeChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// 월별 거래량 차트 렌더링
function renderMonthlyTransactionVolumeChart(data) {
    const allMonths = generateMonthLabels(); // 월 라벨 생성
    const transactionCounts = allMonths.map(month => {
        const foundData = data.find(item => item.monthOfYear === month);
        return foundData ? foundData.transactionCount : 0; // 값이 없는 달은 0
    });

    const chartData = {
        labels: allMonths,
        datasets: [{
            label: '월별 거래량',
            data: transactionCounts,
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderColor: 'rgba(153, 102, 255, 1)',
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('monthlyTransactionVolumeChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// 주 라벨을 생성하는 함수 (오늘 기준 이전 12주, YYYY-WW 형식으로)
function generateWeekLabels() {
    const weeks = [];
    const today = new Date();
    for (let i = 0; i < 12; i++) {
        const week = new Date(today);
        week.setDate(today.getDate() - i * 7); // 7일씩 감소
        const weekLabel = `${week.getFullYear()}-${getWeekNumber(week)}`; // 주 라벨 생성
        weeks.unshift(weekLabel); // 주 번호 앞에 추가
    }
    return weeks;
}

// 월 라벨을 생성하는 함수 (YYYY-MM 형식으로)
function generateMonthLabels() {
    return ['2024-01', '2024-02', '2024-03', '2024-04', '2024-05', '2024-06', '2024-07', '2024-08', '2024-09', '2024-10', '2024-11', '2024-12'];
}

// 주 번호 계산하는 함수
function getWeekNumber(date) {
    const firstDayOfYear = new Date(date.getFullYear(), 0, 1);
    const pastDaysOfYear = (date - firstDayOfYear) / 86400000; // 하루는 86400000ms
    return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
}


// 직원별 거래량 차트 불러오기
function loadEmployeeTransactionByBranch(branchId) {
    $.ajax({
        url: '/api/dashboard/employeeTransactionByBranch',
        method: 'GET',
        data: { branchId: branchId },
        dataType: 'json',
        success: function (response) {
            renderEmployeeTransactionChart(response);
        },
        error: function (xhr, status, error) {
            console.error("데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 차트 렌더링 함수
function renderEmployeeTransactionChart(data) {
    const employeeNames = data.map(item => item.employeeName);
    const transactionCounts = data.map(item => item.transactionCount);

    const chartData = {
        labels: employeeNames,
        datasets: [{
            label: '직원별 거래량',
            data: transactionCounts,
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('employeeTransactionChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}
