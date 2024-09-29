
$(document).ready(function () {
    
    customPeriodBtnDisplay();
    searchPeriodTypeDisplay();
    tradeStatusSelectDisplay();
    tradePeriodSelectDisplay();
});

//직접입력 display 설정 함수
function customPeriodBtnDisplay() {
    document.getElementById('custom-period-btn').addEventListener('click', function() {
        document.getElementById('period-search-area').style.display = 'block';
    });

    document.querySelectorAll('.trade-period-search-btn').forEach(button => {
        button.addEventListener('click', function() {
            if (this.id !== 'custom-period-btn') {
                document.getElementById('period-search-area').style.display = 'none';
            }
        });
    });
}

// 조회 기간 조건 display 설정 함수
function searchPeriodTypeDisplay(){
    const radios = document.querySelectorAll('input[name="search-period-type"]');

    function toggleSearchRows() {
        const monthlyRow = document.getElementById('search-monthly-tr');
        const periodRow = document.getElementById('search-period-tr');


        monthlyRow.style.display = 'none';
        periodRow.style.display = 'none';

        // 선택된 라디오 버튼에 따라 해당 tr 보이기
        radios.forEach(radio => {
            if (radio.checked) {
                const targetRow = document.getElementById(`search-${radio.value}-tr`);
                if (targetRow) {
                    targetRow.style.display = 'table-row';
                }
            }
        });
    }
    toggleSearchRows();

    // 라디오 버튼 클릭 이벤트 리스너 추가
    radios.forEach(radio => {
        radio.addEventListener('change', toggleSearchRows);
    });
}


// 거래 상태 버튼 클릭 css
function tradeStatusSelectDisplay(){

    const buttons = document.querySelectorAll('.trade-status-search-btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            // 해당 버튼 class active 추가 / 이전 버튼 class active
            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        });
    });
}

// 거래 기간 조회 버튼 클릭 css
function tradePeriodSelectDisplay(){

    const buttons = document.querySelectorAll('.trade-period-search-btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            // 해당 버튼 class active 추가 / 이전 버튼 class active
            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        });
    });
}