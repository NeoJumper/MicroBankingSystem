// 페이지가 로드되면 자동으로 차트를 그리기 위해 실행
$(document).ready(function() {
    // AJAX 요청을 통해 서버에서 데이터 가져오기
    const registrantId = 2; // 예시로 행원의 ID를 2로 설정
    $.ajax({
        url: '/api/dashboard/accountOpenRatioChart/' + registrantId, // 문자열 연결 방식으로 변경
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            // 서버에서 받아온 데이터로 차트 렌더링
            console.log("성공적으로 데이터를 받았습니다: ", response);
            renderChart(response);
        },
        error: function(xhr, status, error) {
            console.error("데이터를 가져오는 데 실패했습니다:", error);
        }
    });
});

let delayed = false;  // 전역 변수로 딜레이 상태를 추적

// 데이터를 받아와 차트를 그리는 함수
function renderChart(data) {
    const labels = [];
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth() + 1; // 1부터 12로 표시 (0부터 시작하므로 +1)

    // 올해 이번 달부터 작년 같은 달까지 12개월의 라벨 생성
    for (let i = 0; i < 12; i++) {
        let year = currentYear;
        let month = currentMonth - i;

        if (month <= 0) {
            month += 12;
            year -= 1;
        }

        // 월이 10보다 작을 때 앞에 0을 붙여 형식을 맞춤
        const monthLabel = year + '-' + (month < 10 ? '0' + month : month);
        labels.unshift(monthLabel); // 최신 달이 맨 앞에 오도록 unshift 사용
    }

    // 서버에서 가져온 데이터를 월별로 매핑
    const myOpenCounts = labels.map(label => {
        const foundData = data.find(item => item.tradeMonth === label);
        return foundData ? foundData.openCount : 0; // 나의 개설 수
    });

    // 전체 계좌 개설 수 매핑
    const totalOpenCounts = labels.map(label => {
        const foundData = data.find(item => item.tradeMonth === label);
        return foundData ? foundData.totalOpenCount : 0; // 전체 계좌 개설 수
    });

    // 차트 데이터를 설정
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

    // 애니메이션 및 옵션을 설정
    const chartOptions = {
        responsive: true,  // 반응형 옵션 활성화
        maintainAspectRatio: true, // 비율을 유지하도록 설정
        animation: {
            duration: 1000,  // 애니메이션 지속 시간을 1초로 설정
            easing: 'easeInOutQuart',  // 부드럽게 시작하고 끝나도록 설정
            delay: (context) => {
                let delay = 0;
                if (context.type === 'data' && context.mode === 'default' && !delayed) {
                    delay = context.dataIndex * 100 + context.datasetIndex * 50; // 지연 시간 줄임
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

    // 차트를 렌더링
    const ctx = document.getElementById('myAccountOpenRatioChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',  // 차트 유형
        data: chartData,
        options: chartOptions
    });
}
