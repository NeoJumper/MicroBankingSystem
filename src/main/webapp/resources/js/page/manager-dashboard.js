// 전역 변수로 차트 인스턴스를 선언
let dailyTransactionVolumeChartInstance = null;
let weeklyTransactionVolumeChartInstance = null;
let monthlyTransactionVolumeChartInstance = null;
let dailyTransactionChartInstance = null;
let employeeTransactionChartInstance = null;
let employeeTransactionTypeChartInstance = null;

$(document).ready(function () {
    // 일별 거래량 차트
    loadDailyTransactionVolumeChart();
    // 주간별 거래량 차트
    loadWeeklyTransactionVolumeChart();
    // 월별 거래량 차트
    loadMonthlyTransactionVolumeChart();
    // 행원 거래량 비교 차트
    loadEmployeeTransactionByBranch(1); // 예시로 branchId 1로 호출
    // 행원별 거래유형 차트
    loadEmployeeTransactionTypesChart(1);

    const today = new Date('2024-02-01');
    const formattedToday = today.toISOString().split('T')[0]; // 오늘 날짜 (YYYY-MM-DD)
    loadTransactionData(formattedToday); // 일별 거래유형 차트

    // 탭 전환 시 해당 탭의 차트를 다시 불러오기
    $('button[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        const activeTab = $(e.target).attr('id'); // 활성화된 탭의 ID
        console.log("활성화된 탭:", activeTab); // 탭 확인을 위해 로그 출력

        if (activeTab === 'daily-tab') {
            if (dailyTransactionVolumeChartInstance ) {
                dailyTransactionVolumeChartInstance.destroy();
            } else if(dailyTransactionChartInstance){
                dailyTransactionChartInstance.destroy();
            }
            loadDailyTransactionVolumeChart();
            loadTransactionData(formattedToday);
        } else if (activeTab === 'weekly-tab') {
            if (weeklyTransactionVolumeChartInstance) {
                weeklyTransactionVolumeChartInstance.destroy();
            }
            loadWeeklyTransactionVolumeChart();
        } else if (activeTab === 'monthly-tab') {
            if (monthlyTransactionVolumeChartInstance) {
                monthlyTransactionVolumeChartInstance.destroy();
            }
            loadMonthlyTransactionVolumeChart();
        }
    });
});

// ---------------------------------------------------------------------
// 1. 일별 거래량 차트 불러오기 함수
function loadDailyTransactionVolumeChart() {
    $.ajax({
        url: '/api/dashboard/dailyTransactionVolume',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log("일별거래량",response);
            renderDailyTransactionVolumeChart(response);
        },
        error: function (xhr, status, error) {
            console.error("일별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 1-1. 일별 거래량 차트 렌더링
function renderDailyTransactionVolumeChart(data) {

    const dates = data.map(item => {
        const parts = item.tradeDate.split('-'); // ['YYYY', 'MM', 'DD']
        return `${parts[1]}-${parts[2]}`; // 'MM-DD'
    });
    const transactionCounts = data.map(item => item.transactionCount);

    const chartData = {
        labels: dates,
        datasets: [{
            label: '일별 거래량',
            data: transactionCounts,
            backgroundColor: getColor(5).background,
            borderColor: getColor(5).border,
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('dailyTransactionVolumeChart').getContext('2d');

    dailyTransactionVolumeChartInstance = new Chart(ctx, {
        type: 'line',
        data: chartData,
        options: {
            animation: {
                duration: 1000, // 애니메이션 지속 시간 (1초)
                easing: 'easeInOutQuart' // 애니메이션 효과
            }
        }
    });

}

// ---------------------------------------------------------------------
// 2. 거래 유형 차트를 불러오는 함수 (일별)
function loadTransactionData(date) {
    $.ajax({
        url: '/api/dashboard/dailyTransactionTypes',
        method: 'GET',
        data: { date: date },
        dataType: 'json',
        success: function (response) {
            renderDailyTransactionChart(response);

            // 테이블에 데이터를 넣는 함수 호출
            updateTransactionTable(response);
        },
        error: function (xhr, status, error) {
            console.error("데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 2-1. 차트를 렌더링하는 함수들 (일별)
function renderDailyTransactionChart(data) {
    const transactionTypes = ['입금', '출금', '송금', '가입', '해지'];
    const transactionCounts = transactionTypes.map(type => {
        const transactionData = data.find(item => item.transactionType === type);
        return transactionData ? transactionData.transactionCount : 0;
    });
    // getColor 함수를 사용하여 색상을 동적으로 설정
    const backgroundColors = transactionTypes.map((_, index) => getColor(index).background);
    const borderColors = transactionTypes.map((_, index) => getColor(index).border);

    const chartData = {
        labels: transactionTypes,
        datasets: [{
            type: 'doughnut',
            label: '거래 유형별 거래 수',
            data: transactionCounts,
            backgroundColor: backgroundColors,
            borderColor: borderColors,
            borderWidth: 1,
        }]
    };

    const ctx = document.getElementById('dailyTransactionChart').getContext('2d');
    if (dailyTransactionChartInstance) {
        dailyTransactionChartInstance.destroy();
    }
    dailyTransactionChartInstance = new Chart(ctx, {
        type: 'doughnut',
        data: chartData,
        options: {
            maintainAspectRatio: true, // 차트의 비율 유지
            responsive: true, // 창 크기에 따라 반응형으로 크기 조정
            legend: {
                display: true, // 레전드 표시
                position: 'right', // 레전드를 오른쪽에 세로로 표시
                labels: {
                    boxWidth: 20, // 각 항목 앞의 색상 상자 크기
                    padding: 15,  // 레전드 아이템 간의 간격
                }
            },
            plugins: {
                legend: {
                    position: 'right', // 오른쪽에 세로로 배치
                }
            }
        }
    });
}

// 2-2. 테이블에 데이터를 업데이트하는 함수
function updateTransactionTable(data) {
    const transactionTypes = ['입금', '출금', '송금', '가입', '해지'];

    transactionTypes.forEach(type => {
        const transactionData = data.find(item => item.transactionType === type);
        const count = transactionData ? transactionData.transactionCount : 0;

        // 해당하는 테이블의 행을 찾아서 값을 업데이트
        $(`#daily-transaction-table th:contains(${type})`).next('td').text(count);
    });
}


// ---------------------------------------------------------------------
// 3. 주간별 거래량 차트 불러오기 함수
function loadWeeklyTransactionVolumeChart() {
    $.ajax({
        url: '/api/dashboard/weeklyTransactionVolume',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log("주간별 거래량", response); // 디버깅을 위해 로그 출력
            renderWeeklyTransactionVolumeChart(response);
        },
        error: function (xhr, status, error) {
            console.error("주간별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 3-1. 주간별 거래량 차트 렌더링
function renderWeeklyTransactionVolumeChart(data) {
    const allWeeks = generateWeekLabels(data).reverse(); // weekStart ~ weekEnd
    const transactionCounts = data.map(item => item.transactionCount).reverse();

    console.log("주 라벨:", allWeeks);
    console.log("주 거래량:", transactionCounts);

    const chartData = {
        labels: allWeeks,
        datasets: [{
            label: '주간별 거래량',
            data: transactionCounts,
            backgroundColor: getColor(2).background,
            borderColor: getColor(2).border,
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('weeklyTransactionVolumeChart').getContext('2d');

    // 기존 차트가 있다면 파괴하고 새로 생성
    if (weeklyTransactionVolumeChartInstance) {
        weeklyTransactionVolumeChartInstance.destroy();
    }

    weeklyTransactionVolumeChartInstance = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            responsive: true,
            plugins: {
                tooltip: {
                    mode: 'index',
                    intersect: false,
                },
                legend: {
                    display: true,
                    position: 'top',
                },
            },
            scales: {
                x: {
                    title: {
                        display: true,
                        text: '주차'
                    }
                },
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: '거래량'
                    }
                }
            },
            animation: {
                duration: 1000, // 애니메이션 지속 시간 (1초)
                easing: 'easeInOutQuart' // 애니메이션 효과
            }
        }
    });
}


// ---------------------------------------------------------------------
// 4. 월별 거래량 차트 불러오기 함수
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

// 4-1. 월별 거래량 차트 렌더링
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
            backgroundColor: getColor(3).background,
            borderColor: getColor(3).border,
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('monthlyTransactionVolumeChart').getContext('2d');

    monthlyTransactionVolumeChartInstance = new Chart(ctx, {
        type: 'bar',
        data: chartData,
    });
}

// ---------------------------------------------------------------------
// 5. 직원별 거래량 차트 불러오기 함수
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
            console.error("직원별 거래량 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 5-1. 직원별 거래량 차트 렌더링
function renderEmployeeTransactionChart(data) {
    const employeeNames = data.map(item => item.employeeName);
    const transactionCounts = data.map(item => item.transactionCount);

    const chartData = {
        labels: employeeNames,
        datasets: [{
            label: '직원별 거래량',
            data: transactionCounts,
            backgroundColor: getColor(4).background,
            borderColor: getColor(4).border,
            borderWidth: 1
        }]
    };

    const ctx = document.getElementById('employeeTransactionChart').getContext('2d');
    if (employeeTransactionChartInstance) {
        employeeTransactionChartInstance.destroy();
    }
    employeeTransactionChartInstance = new Chart(ctx, {
        type: 'bar',
        data: chartData,
    });
}
// ---------------------------------------------------------------------
// 6. 사원별 거래 유형 차트
function loadEmployeeTransactionTypesChart(branchId) {
    $.ajax({
        url: '/api/dashboard/employeeTransactionTypes',
        method: 'GET',
        data: { branchId: branchId },
        dataType: 'json',
        success: function(response) {
            console.log("사원별 거래 유형 및 거래량", response); // 디버깅을 위해 로그 출력
            renderEmployeeTransactionTypesChart(response);
        },
        error: function(xhr, status, error) {
            console.error("사원별 거래 유형 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 6-1. 사원별 거래 유형 및 거래량 차트 렌더링
function renderEmployeeTransactionTypesChart(data) {
    // 직원 이름과 거래 유형을 추출
    const employees = [...new Set(data.map(item => item.employeeName))];
    const transactionTypes = [...new Set(data.map(item => item.transactionType))];

    // 각 거래 유형별로 데이터셋 생성
    const datasets = transactionTypes.map((type, index) => {
        const color = getColor(index); // 색상 할당 함수
        const dataPoints = employees.map(employee => {
            const found = data.find(item => item.employeeName === employee && item.transactionType === type);
            return found ? found.transactionCount : 0;
        });
        return {
            label: type,
            data: dataPoints,
            backgroundColor: color.background,
            borderColor: color.border,
            borderWidth: 1
        };
    });

    const chartData = {
        labels: employees,
        datasets: datasets
    };

    const ctx = document.getElementById('employeeTransactionTypeChart').getContext('2d');

    // 기존 차트가 있다면 파괴하고 새로 생성
    if (employeeTransactionTypeChartInstance) {
        employeeTransactionTypeChartInstance.destroy();
    }

    employeeTransactionTypeChartInstance = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            responsive: true,
            plugins: {
                tooltip: {
                    mode: 'index',
                    intersect: false,
                },
                legend: {
                    display: true,
                    position: 'top',
                },
            },
            scales: {
                x: {
                    stacked: true, // 누적 막대 그래프 설정
                    title: {
                        display: true,
                        text: '사원'
                    }
                },
                y: {
                    stacked: true, // 누적 막대 그래프 설정
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: '거래량'
                    }
                }
            },
            animation: {
                duration: 1000, // 애니메이션 지속 시간 (1초)
                easing: 'easeInOutQuart' // 애니메이션 효과
            }
        }
    });
}


// ---------------------------------------------------------------------
// +) 라벨용 함수들
// 주 라벨을 생성하는 함수 (MM월 N주차)
function generateWeekLabels(data) {
    return data.map(item => {
        const weekStart = item.weekStart;
        const weekEnd = item.weekEnd;
        return `${weekStart} ~ ${weekEnd}`;
    });
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
// 색상 할당 함수
function getColor(index) {
    const colors = [
        { background: '#EA0B0B66', border: '#c10000' },
        { background: '#ffce9a', border: '#ff9020' },
        { background: '#FFC10766', border: '#ffc637' },
        { background: '#8BC34A66', border: '#77c63e' },
        { background: '#42A5F566', border: '#1E90FF' },
        { background: '#B299FF', border: '#9966FFFF' }
        // 필요에 따라 색상을 추가하세요.
    ];
    return colors[index % colors.length];
}