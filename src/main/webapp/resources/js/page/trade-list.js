$(document).ready(function () {

    customPeriodBtnDisplay();
    searchPeriodTypeDisplay();
    tradeStatusSelectDisplay();
    tradePeriodSelectDisplay();
    accIdOfSearchAccountModal();
    searchResultOfTradeList();
    calculateOfPeriodDate();
    calculateOfMonthDate();
    

    // 검색 버튼 클릭 시 거래내역 조회
    $("#trade-list-search-btn").on("click", function () {
        searchResultOfTradeList();
    });

    // 라디오 버튼에 EventListeners 등록
    //registerRadioButtonEventListeners();

});

// 라디오 버튼에 따른 value 변경
// function registerRadioButtonEventListeners() {
//     document.querySelectorAll('input[name="search-period-type"]').forEach(function (radio) {
//         radio.addEventListener('change', function () {
//             const selectedType = this.value;
//             console.log("선택된 조회 방식: " + selectedType);
//         });
//     });
// }


//직접입력 display 설정 함수
function customPeriodBtnDisplay() {
    document.getElementById('custom-period-btn').addEventListener('click', function () {
        document.getElementById('period-search-area').style.display = 'block';
    });

    document.querySelectorAll('.trade-period-search-btn').forEach(button => {
        button.addEventListener('click', function () {
            if (this.id !== 'custom-period-btn') {
                document.getElementById('period-search-area').style.display = 'none';
            }
        });
    });
}

// 조회 기간 조건 display 설정 함수
function searchPeriodTypeDisplay() {

    const radios = document.querySelectorAll('input[name="search-period-type"]');

    // 해당하는 row 보여주기
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

    // 기본값 tr 보여주기
    toggleSearchRows();

    // 라디오 버튼 클릭시 이벤트 라스너 변경
    radios.forEach(radio => {
        radio.addEventListener('change', toggleSearchRows);
    });
}


// 거래 상태 버튼 클릭 css
function tradeStatusSelectDisplay() {

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
function tradePeriodSelectDisplay() {

    const buttons = document.querySelectorAll('.trade-period-search-btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            // 해당 버튼 class active 추가 / 이전 버튼 class active
            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        });
    });
}

function accIdOfSearchAccountModal() {
    $('#search-modal-select-account-btn').on("click", function () {
        alert("accIdOfSearchAccountModal  시작")
        const selectedAccount = $("input[name='select-account']:checked");

        if (selectedAccount.length > 0) {
            const selectedRow = selectedAccount.closest("tr");
            const accountId = selectedRow.find("td:nth-child(2)").text();

            $('#acc-id-input').val(accountId);
            $('#search-modal-account').modal('hide');
        } else {
            alert("계좌를 선택하세요.");
        }
    });
}


// 라디오 버튼 value 값 가져오기
function calculationResultOfDateType() {
    let resultStartDate;
    let resultEndDate;

    const selectedType = document.querySelector('input[name="search-period-type"]:checked').value;

    if (selectedType === 'period') {
        console.log("기간 조회가 선택되었습니다.");

        const periodDates = calculateOfPeriodDate();
        resultStartDate = periodDates.periodStartDate;
        resultEndDate = periodDates.periodEndDate;
        console.log("기간 조회 resultStartDate: " + resultStartDate);
        console.log("기간 조회 resultEndDate: " + resultEndDate);

    } else if (selectedType === 'monthly') {
        console.log("월별 조회가 선택되었습니다.");

        const monthDates = calculateOfMonthDate();
        resultStartDate = monthDates.monthlyStartDate;
        resultEndDate = monthDates.monthlyEndDate;
        console.log("월별 조회 resultStartDate: " + resultStartDate);
        console.log("월별 조회 resultEndDate: " + resultEndDate);
    }

    return {
        resultStartDate: resultStartDate,
        resultEndDate: resultEndDate
    };
}



// 거래내역 조회 api
function searchResultOfTradeList() {


        alert("거래내역 조회 api");
        const constAccId = $('#acc-id-input').val();
        const tradeTypeButtonValue = $('.trade-status-search-btn.active').val();
        const selectSort = $('input[name="search-sort"]:checked').val();

        const searchDates  = calculationResultOfDateType();

        const searchStartDate = searchDates.resultStartDate;
        const searchEndDate = searchDates.resultEndDate;

        console.log("constAccId" + constAccId);
        console.log("tradeTypeButtonValue"+tradeTypeButtonValue);
        console.log("selectSort" + selectSort);

        console.log("searchStartDate" + searchStartDate);
        console.log("searchEndDate" + searchEndDate);

        $.ajax({
            url: '/api/employee/trade/search/result',
            method: 'GET',
            data: {
                accId: constAccId,
                tradeType: tradeTypeButtonValue,
                startDate: searchStartDate,
                endDate: searchEndDate,
                sortOrder: selectSort
            },
            success: function (data) {
                renderOfSearchResults(data);

            }, error: function (error) {
                alert('검색 요청에 실패했습니다: ' + error);
            }
        })
}

// 거래내역 동적 테이블 생성 : 검색결과 뿌리기
function renderOfSearchResults(data) {
    const tradeResultsTableBody = $('#trade-result-tbody');
    tradeResultsTableBody.empty();

    $('#total-deposit-input').val(data.totalDeposit);
    $('#total-withdraw-input').val(data.totalWithDraw);

    const tradeList = data.tradeList;

    $.each(tradeList, function (index, trade) {
        var row = $('<tr>')
            .append($('<td>').text(index + 1))
            .append($('<td>').text(trade.tradeDate))
            .append($('<td>').text(trade.accId))
            .append($('<td>').text(trade.targetAccId))
            .append($('<td>').text(trade.cashIndicator))
            .append($('<td>').text(trade.amount))
            .append($('<td>').text(trade.balance))
            .append($('<td>').text(trade.tradeType))
            .append($('<td>').text(trade.status))

        tradeResultsTableBody.append(row);
    })
}

function calculateOfMonthDate() {


    const selectedYear = $('#year-search-btn').val();
    const selectedMonth = $('#month-search-btn').val();

    console.log("calculateStartDateBefore" + selectedYear);
    console.log("calculateStartDateBefore" + selectedMonth);

    const monthStartDate = new Date(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1
    const monthEndDate = new Date(selectedYear, selectedMonth, 0); // 다음 월의 0일로 설정하면 마지막 날을 얻을 수 있음

    // 로컬 시간대로 변환
    const monthlyStartDate = monthStartDate.toLocaleDateString('en-CA'); // 'yyyy-MM-dd' 형식
    const monthlyEndDate = monthEndDate.toLocaleDateString('en-CA');
    // 결과 확인
    console.log("시작일: " + monthlyStartDate);
    console.log("종료일: " + monthlyEndDate);

    return {
        monthlyStartDate: monthlyStartDate,
        monthlyEndDate: monthlyEndDate
    };


}


// 기간조회 : 시작일 구하기 함수
function startDateOfPeriod(currentDate, period) {
    const endDate = new Date(currentDate);
    let calculatedStartDate;

    switch (period) {
        case '1d':
            calculatedStartDate = new Date(endDate);
            break;
        case '1w':
            calculatedStartDate = new Date(endDate);
            calculatedStartDate.setDate(endDate.getDate() - 7); // 1주일 전
            break;
        case '1m':
            calculatedStartDate = new Date(endDate);
            calculatedStartDate.setMonth(endDate.getMonth() - 1); // 1개월 전
            break;
        case '3m':
            calculatedStartDate = new Date(endDate);
            calculatedStartDate.setMonth(endDate.getMonth() - 3); // 3개월 전
            break;
        case '6m':
            calculatedStartDate = new Date(endDate);
            calculatedStartDate.setMonth(endDate.getMonth() - 6); // 6개월 전
            break;
        default:
            throw new Error("지원하지 않는 기간입니다."); // 지원하지 않는 기간 처리
    }
    return calculatedStartDate.toLocaleDateString('en-CA');

}

function calculateOfPeriodDate() {

    /*
        getCurrentDate
        - 시작일자 : 검색 당일, 영업일
        - db 영업일 호출 (#businessDay input에 hidden 바인딩)
     */
    const getCurrentDate = $('#businessDay').val(); // 현재 영업일 가져오기

    const selectPeriod = $('.trade-period-search-btn.active').val(); // 선택된 기간

    let periodStartDate
    let periodEndDate

    if (!selectPeriod) {
        console.log("직접 기간 입력 선택 >>>>");
        periodStartDate = $('#period-start-input').val();
        periodEndDate = $('#period-end-input').val();

    } else {
        console.log("선택된 기간: " + selectPeriod);
        periodStartDate = startDateOfPeriod(getCurrentDate, selectPeriod); // 선택된 기간으로 시작일 계산
        console.log("계산된 시작일: " + periodStartDate);
        periodEndDate = getCurrentDate; // 종료일은 현재 영업일
        console.log("종료일: " + periodEndDate);
    }

    return {
        periodStartDate: periodStartDate,
        periodEndDate: periodEndDate
    };

}



