// 전역 변수로 차트 인스턴스를 선언
let dailyTransactionVolumeChartInstance = null;
let weeklyTransactionVolumeChartInstance = null;
let monthlyTransactionVolumeChartInstance = null;
let dailyTransactionChartInstance = null;
let employeeTransactionChartInstance = null;
let employeeTransactionTypeChartInstance = null;
let monthlyTransactionChartInstance = null;

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
            }else if(monthlyTransactionChartInstance){
                monthlyTransactionChartInstance.destroy();
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
    // 월별 거래량 차트 데이터 로드
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

    // 월별 거래 유형 차트 및 표 데이터 로드
    $.ajax({
        url: '/api/dashboard/monthlyTransactionTypes',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            renderMonthlyTransactionChart(response);
            updateMonthlyTransactionTable(response); // 표 업데이트 함수 호출
        },
        error: function (xhr, status, error) {
            console.error("월별 거래 유형 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

// 월별 거래 유형 표를 업데이트하는 함수
function updateMonthlyTransactionTable(data) {
    const transactionTypes = ['입금', '출금', '송금', '가입', '해지'];

    transactionTypes.forEach(type => {
        const transactionData = data.find(item => item.transactionType === type);
        const count = transactionData ? transactionData.transactionCount : 0;

        // 해당하는 테이블의 행을 찾아서 값을 업데이트
        $(`#monthly-transaction-table th:contains(${type})`).next('td').text(count);
    });
}


function renderMonthlyTransactionChart(data) {
    const transactionTypes = ['입금', '출금', '송금', '가입', '해지'];
    const transactionCounts = transactionTypes.map(type => {
        const transactionData = data.find(item => item.transactionType === type);
        return transactionData ? transactionData.transactionCount : 0;
    });

    // getColor 함수를 사용하여 색상 설정
    const backgroundColors = transactionTypes.map((_, index) => getColor(index).background);
    const borderColors = transactionTypes.map((_, index) => getColor(index).border);

    const chartData = {
        labels: transactionTypes,
        datasets: [{
            label: '거래 유형별 거래 수',
            data: transactionCounts,
            backgroundColor: backgroundColors,
            borderColor: borderColors,
            borderWidth: 1,
        }]
    };

    const ctx = document.getElementById('monthlyTransactionChart').getContext('2d');
    if (monthlyTransactionChartInstance) {
        monthlyTransactionChartInstance.destroy();
    }
    monthlyTransactionChartInstance = new Chart(ctx, {
        type: 'doughnut',
        data: chartData,
        options: {
            maintainAspectRatio: true,
            responsive: true,
            legend: {
                display: true,
                position: 'right',
                labels: {
                    boxWidth: 20,
                    padding: 15,
                }
            },
            plugins: {
                legend: {
                    position: 'right',
                }
            },
            animation: {
                duration: 1000,
                easing: 'easeInOutQuart',
            }
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

function saveChartsAsPDF() {
    const { jsPDF } = window.jspdf;
    const pdf = new jsPDF('p', 'mm', 'a4');
    const options = { scale: 2, useCORS: true };

    // 웹 폰트 로드 완료 후 실행
    document.fonts.ready.then(function () {
        // 1. 표지 생성
        const coverPage = document.createElement('div');
        coverPage.style.width = '210mm'; // A4 사이즈
        coverPage.style.height = '297mm';
        coverPage.style.display = 'flex';
        coverPage.style.flexDirection = 'column';
        coverPage.style.justifyContent = 'center';
        coverPage.style.alignItems = 'center';
        coverPage.style.fontFamily = "'Noto Sans KR', sans-serif";
        coverPage.style.fontSize = '14pt';
        coverPage.style.backgroundColor = '#f7f7f7'; // 배경색 추가
        coverPage.style.padding = '20px';

        const title = document.createElement('h1');
        title.innerText = '차트 보고서';
        title.style.fontSize = '32pt';
        title.style.marginBottom = '40px';
        title.style.color = '#333';

        const date = document.createElement('p');
        const today = new Date();
        const formattedDate = today.toISOString().split('T')[0];
        date.innerText = `생성 날짜: ${formattedDate}`;
        date.style.fontSize = '18pt';
        date.style.color = '#555';

        const description = document.createElement('p');
        description.innerText = '이 보고서는 지점의 거래 현황을 포함합니다.\n아래 차트는 다양한 거래량과 거래 유형을 시각화한 것입니다.';
        description.style.textAlign = 'center';
        description.style.marginTop = '30px';
        description.style.lineHeight = '1.5';
        description.style.color = '#555';

        coverPage.appendChild(title);
        coverPage.appendChild(date);
        coverPage.appendChild(description);

        // 보이지 않는 컨테이너 생성
        const hiddenContainer = document.createElement('div');
        hiddenContainer.style.position = 'absolute';
        hiddenContainer.style.left = '-9999px';
        hiddenContainer.style.top = '0';
        document.body.appendChild(hiddenContainer);

        hiddenContainer.appendChild(coverPage);

        html2canvas(coverPage, options).then(canvas => {
            const imgData = canvas.toDataURL('image/png');
            pdf.addImage(imgData, 'PNG', 0, 0, 210, 297);

            pdf.addPage();

            // 표지 제거
            hiddenContainer.removeChild(coverPage);

            // 2. 포함할 차트 및 표 구성
            const charts = [
                // 첫 번째 페이지: 항상 보이는 차트 2개
                [
                    { elementId: 'employeeTransactionChart', title: '직원별 거래량 비교' },
                    { elementId: 'employeeTransactionTypeChart', title: '사원별 거래 유형 및 거래량' }
                ],
                // 두 번째 페이지: 활성화된 탭의 차트 2개와 표
                [
                    { elementId: null, title: '' }, // 나중에 설정
                    { elementId: null, element: null, title: '' } // 나중에 설정
                ]
            ];

            // 활성화된 탭의 정보 가져오기
            const activeTabButton = $('button[data-bs-toggle="tab"].active');
            const activeTabContentId = activeTabButton.attr('data-bs-target');
            const activeTabContent = $(activeTabContentId);

            // 활성화된 탭의 차트와 표의 ID 가져오기
            const activeTabLineChart = activeTabContent.find('canvas').first();
            const activeTabLineChartId = activeTabLineChart.attr('id');
            const activeTabTransactionChart = activeTabContent.find('canvas').last();
            const activeTabTransactionChartId = activeTabTransactionChart.attr('id');
            const activeTabTransactionTable = activeTabContent.find('table');
            const activeTabTransactionTableId = activeTabTransactionTable.attr('id');

            // 디버깅 로그 추가
            console.log('Active Tab Line Chart ID:', activeTabLineChartId);
            console.log('Active Tab Transaction Chart ID:', activeTabTransactionChartId);
            console.log('Active Tab Transaction Table ID:', activeTabTransactionTableId);

            // ID가 정상적으로 가져와졌는지 확인
            if (!activeTabLineChartId || !activeTabTransactionChartId || !activeTabTransactionTableId) {
                alert('활성화된 탭의 차트나 표를 찾을 수 없습니다.');
                // 보이지 않는 컨테이너 제거
                document.body.removeChild(hiddenContainer);
                return;
            }

            // 두 번째 페이지의 차트 설정
            charts[1][0].elementId = activeTabLineChartId;
            charts[1][0].title = activeTabButton.text().trim() + ' - 거래량 추이';

            charts[1][1].element = createChartAndTableContainer(activeTabTransactionChartId, activeTabTransactionTableId);
            charts[1][1].title = activeTabButton.text().trim() + ' - 거래 유형 및 거래량 표';

            // 3. 각 페이지 처리
            let pageIndex = 0;

            function addPagesToPDF() {
                if (pageIndex >= charts.length) {
                    // 모든 페이지 처리가 완료되면 보이지 않는 컨테이너 제거
                    document.body.removeChild(hiddenContainer);

                    pdf.save('차트 보고서.pdf');
                    return;
                }

                const chartPair = charts[pageIndex];
                pageIndex++;

                const pageContainer = document.createElement('div');
                pageContainer.style.width = '800px';
                pageContainer.style.paddingTop = '100px';
                pageContainer.style.display = 'flex';
                pageContainer.style.flexDirection = 'column';
                pageContainer.style.alignItems = 'center';
                pageContainer.style.fontFamily = "'Noto Sans KR', sans-serif";
                pageContainer.style.marginBottom = '40px';

                // 첫 번째 차트 추가
                createChartSection(pageContainer, chartPair[0]);

                // 구분선 추가
                const separator = document.createElement('hr');
                separator.style.width = '80%';
                separator.style.border = '0';
                separator.style.borderTop = '1px solid #ccc';
                separator.style.margin = '30px 0';
                pageContainer.appendChild(separator);

                // 두 번째 차트 또는 차트+표 추가
                createChartSection(pageContainer, chartPair[1]);

                hiddenContainer.appendChild(pageContainer);

                html2canvas(pageContainer, options).then(canvas => {
                    const imgData = canvas.toDataURL('image/png');
                    const imgWidth = 200;
                    const imgHeight = canvas.height * imgWidth / canvas.width;

                    if (pageIndex > 1) pdf.addPage();
                    pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);

                    // 페이지 컨테이너 제거
                    hiddenContainer.removeChild(pageContainer);

                    // 다음 페이지 처리
                    addPagesToPDF();

                }).catch(error => {
                    console.error("캔버스 캡처 중 에러:", error);
                    hiddenContainer.removeChild(pageContainer);
                    // 다음 페이지 처리
                    addPagesToPDF();
                });
            }

            function createChartSection(container, chartObj) {
                const chartSection = document.createElement('div');
                chartSection.style.width = '100%';
                chartSection.style.display = 'flex';
                chartSection.style.flexDirection = 'column';
                chartSection.style.alignItems = 'center';

                const chartTitle = document.createElement('h2');
                chartTitle.innerText = chartObj.title;
                chartTitle.style.marginBottom = '20px';
                chartTitle.style.color = '#333';

                chartSection.appendChild(chartTitle);

                if (chartObj.element) {
                    chartSection.appendChild(chartObj.element);
                } else if (chartObj.elementId) {
                    // 차트 캔버스 복제
                    const chartElement = document.getElementById(chartObj.elementId);
                    if (!chartElement) {
                        alert(`ID가 "${chartObj.elementId}"인 차트를 찾을 수 없습니다.`);
                        return;
                    }
                    const chartCanvas = document.createElement('canvas');
                    chartCanvas.width = chartElement.width;
                    chartCanvas.height = chartElement.height;
                    const ctx = chartCanvas.getContext('2d');
                    ctx.drawImage(chartElement, 0, 0);

                    chartSection.appendChild(chartCanvas);
                }

                container.appendChild(chartSection);
            }

            // 차트와 표를 함께 담는 컨테이너 생성 함수
            function createChartAndTableContainer(chartId, tableId) {
                const container = document.createElement('div');
                container.style.display = 'flex';
                container.style.justifyContent = 'center';
                container.style.alignItems = 'flex-start';

                // 차트 캔버스 복제
                const chartElement = document.getElementById(chartId);
                if (!chartElement) {
                    alert(`ID가 "${chartId}"인 차트를 찾을 수 없습니다.`);
                    return null;
                }
                const chartCanvas = document.createElement('canvas');
                chartCanvas.width = chartElement.width;
                chartCanvas.height = chartElement.height;
                const ctx = chartCanvas.getContext('2d');
                ctx.drawImage(chartElement, 0, 0);

                // 표 복제
                const tableElement = document.getElementById(tableId);
                if (!tableElement) {
                    alert(`ID가 "${tableId}"인 표를 찾을 수 없습니다.`);
                    return null;
                }
                const clonedTable = tableElement.cloneNode(true);
                clonedTable.style.marginLeft = '20px';
                clonedTable.style.fontFamily = "'Noto Sans KR', sans-serif";
                clonedTable.style.fontSize = '12pt';
                clonedTable.style.borderCollapse = 'collapse';
                clonedTable.querySelectorAll('th, td').forEach(cell => {
                    cell.style.border = '1px solid #ccc';
                    cell.style.padding = '8px';
                });

                container.appendChild(chartCanvas);
                container.appendChild(clonedTable);

                return container;
            }

            // 시작
            addPagesToPDF();

        }).catch(error => {
            console.error("표지 캔버스 캡처 중 에러:", error);
            document.body.removeChild(coverPage);
            document.body.removeChild(hiddenContainer);
        });
    });
}
