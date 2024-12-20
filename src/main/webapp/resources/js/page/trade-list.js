
$(document).ready(function () {

    customPeriodBtnDisplay();
    searchPeriodTypeDisplay();
    tradeStatusSelectDisplay();
    tradePeriodSelectDisplay();
    accIdOfSearchAccountModal();
    calculateOfPeriodDate();
    calculateOfMonthDate();
    majorCategoryDisplay();

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

// 대분류 설정 함수
function majorCategoryDisplay() {

    $('input[name="major-category"]').on('change', function() {
        toggleButtonStyles();
        toggleTradeTypeDisplay();
    });

    function toggleButtonStyles() {
        // 모든 라벨에서 active 클래스를 제거
        $('#major-category-button-group label').removeClass('active');

        // 선택된 라디오 버튼에 해당하는 라벨에 active 클래스 추가
        $('input[name="major-category"]:checked').next('label').addClass('active');
    }
    function toggleTradeTypeDisplay() {
        const selectedValue = $('input[name="major-category"]:checked').val();

        // 조건별 조회 버튼 보이기/숨기기
        if (selectedValue === 'common') {
            // 계좌 이체 선택 시
            $('#trade-type-row').show(); // 조건별조회 버튼 보여주기
        } else if (selectedValue === 'bulk') {
            // 대량 이체 선택 시
            $('#trade-type-row').hide();
            // 추가적인 로직이 필요하면 여기에 작성
        }
    }
}


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
            console.log(radio);
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

// 계좌 선택 값 넣게
function accIdOfSearchAccountModal() {
    $('#search-modal-select-account-btn').on("click", function () {

        const selectedAccount = $("input[name='select-account']:checked");

        if (selectedAccount.length > 0) {
            const selectedRow = selectedAccount.closest("tr");
            const accountId = selectedRow.find("td:nth-child(2)").text();

            $('#acc-id-input').val(accountId);
            $('#search-modal-account').modal('hide');
        } else {
            swal({
                title: "계좌를 선택하세요",
                icon: "warning",
                button: "닫기",
            })
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
function searchResultOfTradeList(pageNum = 1) {

    const majorCategoryValue = $('input[name="major-category"]:checked').val();
    const constAccId = $('#acc-id-input').val();
    const tradeTypeButtonValue = $('.trade-status-search-btn.active').val();
    const selectSort = $('input[name="search-sort"]:checked').val();

    const searchDates = calculationResultOfDateType();

    const searchStartDate = searchDates.resultStartDate;
    const searchEndDate = searchDates.resultEndDate;

    console.log("constAccId" + constAccId);
    console.log("tradeTypeButtonValue" + tradeTypeButtonValue);
    console.log("selectSort" + selectSort);

    console.log("searchStartDate" + searchStartDate);
    console.log("searchEndDate" + searchEndDate);
    // 페이지 번호와 항목 수 설정

    const amount = 8; // 페이지당 항목 수


    if (majorCategoryValue === 'common'){
        $.ajax({
            url: '/api/employee/trade/search/result',
            method: 'GET',
            data: {
                accId: constAccId,
                tradeType: tradeTypeButtonValue,
                startDate: searchStartDate,
                endDate: searchEndDate,
                sortOrder: selectSort,
                'criteria.pageNum': pageNum, // Criteria의 pageNum
                'criteria.amount': amount
            },
            success: function (data) {
                renderOfTradeSearchResults(data);
                registerClickEventOfTradeCancelBtn();
                updatePagination(data.pageDTO);
                $('#common-transfer-select-result').show(); // 계좌 이체 결과 보여주기
                $('#bulk-transfer-select-result').hide(); // 대량 이체 결과 숨기기
            }, error: function (error) {
                alert('검색 요청에 실패했습니다: ' + error);
            }
        })
    }
    else{
        console.log("대량조회");

        $.ajax({
            url: '/api/employee/bulk-transfer',
            method: 'GET',
            data: {
                accId: constAccId,
                tradeType: tradeTypeButtonValue,
                startDate: searchStartDate,
                endDate: searchEndDate,
                sortOrder: selectSort,
                'criteria.pageNum': pageNum, // Criteria의 pageNum
                'criteria.amount': amount
            },
            success: function (data) {
                renderOfBulkTransferSearchResults(data.bulkTransferDetailList);
                updatePagination(data.pageDTO);
                $('#common-transfer-select-result').hide(); // 계좌 이체 결과 보여주기
                $('#bulk-transfer-select-result').show(); // 대량 이체 결과 숨기기
            }, error: function (error) {
                alert('검색 요청에 실패했습니다: ' + error);
            }
        })
    }

}

// paging 버튼 동적 생성 및 버튼 클릭시 동작
function updatePagination(pageDTO) {
    const paginationContainer = $('#pagination'); // 페이지네이션을 넣을 컨테이너
    paginationContainer.empty(); // 기존 내용 제거

    console.log("pageDTO.startPage" + pageDTO.startPage);
    if (pageDTO.prev) {
        const prevButton = $('<a href="#" data-page="' + (pageDTO.startPage - 1) + '" class="pagination-btn">이전</a>');
        paginationContainer.append(prevButton);
    }

    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        const pageButton = $('<a href="#" data-page="' + i + '" class="pagination-btn ' + (pageDTO.criteria.pageNum === i ? 'active' : '') + '">' + i + '</a>');
        paginationContainer.append(pageButton);
    }

    if (pageDTO.next) {
        const nextButton = $('<a href="#" data-page="' + (pageDTO.endPage + 1) + '" class="pagination-btn">다음</a>');
        paginationContainer.append(nextButton);
    }

    // 페이지 가장 아래로
    $('html, body').animate({ scrollTop: $(document).height() }, 'slow');

    // 페이지 버튼 클릭 이벤트 처리
    paginationContainer.find('a').on('click', function (e) {
        e.preventDefault(); // 기본 링크 동작 방지
        const selectedPage = $(this).data('page'); // 클릭한 페이지 번호 가져오기
        searchResultOfTradeList(selectedPage); // 페이지 번호를 전달하여 다시 검색
    });
}

// 거래상태별 css 적용
function getTradeTypeInfo(tradeType) {
    switch (tradeType) {
        case 'OPEN':
            return {type: '개설', cssClass: 'trade-type-open'}; // 개설
        case 'CLOSE':
            return {type: '해지', cssClass: 'trade-type-close'}; // 해지
        case 'WITHDRAWAL':
            return {type: '출금', cssClass: 'trade-type-withdrawal'}; // 출금
        case 'DEPOSIT':
            return {type: '입금', cssClass: 'trade-type-deposit'}; // 입금
        default:
            return {type: tradeType, cssClass: ''};
    }
}

// 거래내역 동적 테이블 생성 : 검색결과 뿌리기
function renderOfTradeSearchResults(data) {
    const tradeResultsTableBody = $('#trade-result-tbody');
    tradeResultsTableBody.empty();

    $('#total-deposit-input').val(comma(data.totalDeposit == null ? 0 : data.totalDeposit) + '원');
    $('#total-withdraw-input').val(comma(data.totalWithDraw == null ? 0 : data.totalWithDraw) + '원');

    const tradeList = data.tradeList;


    $.each(tradeList, function (index, trade) {
        // 영업일 포맷
        const businessDayValue = $('#businessDay').val();
        const businessDate = new Date(businessDayValue);
        const formattedBusinessDay = `${businessDate.toLocaleDateString('ko-KR')}`;

        // 거래 날짜 포맷
        const tradeDate = new Date(trade.tradeDate);
        const formattedTradeDate = `${tradeDate.toLocaleDateString('ko-KR')}`;


        // 현금 이체 css 표시
        const cashIndicatorText = trade.cashIndicator === 'TRUE' ? '현금' : '';
        const cashIndicatorClass = trade.cashIndicator === 'TRUE' ? 'cash-text' : '';

        const statusMap = {
            'NOR': {text: '정상'},
            'CAN': {text: '거래취소'},
            'RVK': {text: '거래정정'}
        };

        // 상태에 따른 텍스트와 클래스 가져오기
        const statusInfo = statusMap[trade.status] || {text: '', class: ''};

        // 유형 가져오기
        const tradeTypeInfo = getTradeTypeInfo(trade.tradeType);



        let amountText = '';
        let amountClass = '';
        let balance = trade.balance;
        if (tradeTypeInfo.type ==='출금' || tradeTypeInfo.type ==='해지')
        {
            amountText = -trade.amount;
            amountClass = 'withdrawal-trade-text'
        }
        else{
            amountText = trade.amount;
            amountClass = 'deposit-trade-text'
        }





        var row = $('<tr>')
            .append($('<td class="hidden-trade-number">')
                .addClass('text-center')
                .attr('hidden', true) // hidden 속성 추가
                .text(trade.tradeNumber))
            .append($('<td id="trade-list-trade-type">')
                .addClass('text-center')
                .addClass(tradeTypeInfo.cssClass)
                .append($('<i>').addClass('bi bi-circle-fill me-2'))
                .append(tradeTypeInfo.type))
            .append($('<td id="trade-list-trade-date">')
                .addClass('text-center')
                .text(formattedTradeDate))
            .append($('<td id="trade-list-trade-acc-id">')
                .addClass('text-center')
                .text(trade.accId))
            .append($('<td id="trade-list-trade-target-acc-id">')
                .addClass('text-center')
                .text(trade.targetAccId))
            .append($('<td id="trade-list-trade-type-amount">')
                .addClass('text-right')
                .addClass(amountClass)
                .text(comma(amountText) + '원'))
            .append($('<td id="trade-list-trade-balance">')
                .addClass('text-right')
                .text(comma(balance) + '원'))
            .append($('<td id="trade-list-trade-cash-indicator">')
                .addClass('text-center')
                .addClass(cashIndicatorClass)
                .text(cashIndicatorText))
            .append($('<td id="trade-list-trade-status">')
                    .addClass('text-center')
                    .text(statusInfo.text)
                    .addClass(statusInfo.class));

        if (formattedBusinessDay === formattedTradeDate && trade.status === 'NOR' && (trade.tradeType === 'CLOSE' || trade.tradeType === 'WITHDRAWAL') && trade.cashIndicator ==='TRUE') {
            // 영업일과 거래일이 동일하고 거래 유형이 'OPEN'이 아닐 경우 버튼 추가
            row.append($('<td>')
                .addClass('text-center')
                .append($('<button>')
                    .text('취소하기')
                    .addClass('status-nor')
                )
            );
        } else {
            // 조건에 맞지 않으면 빈 td 추가
            row.append($('<td>')
                .text('')
                .addClass('text-center')); // 빈 td 추가
        }

        tradeResultsTableBody.append(row);
    })
}
function renderOfBulkTransferSearchResults(transferList){
    console.log(transferList);
    const bulkTransferResultTbody = $('#bulk-transfer-result-tbody');
    bulkTransferResultTbody.empty(); // 기존 내용을 비움

    $.each(transferList, function (index, transfer) {
        console.log(transfer);
        // 거래 날짜 포맷
        const transferDate = new Date(transfer.registrationDate); // ISO 8601 포맷 사용
        const formattedTransferDate = `${transferDate.toLocaleString('ko-KR')}`; // 한국 시간 포맷

        var transferStatus;

        switch (transfer.status) {
            case "NOR":
                transferStatus = "이체완료";
                break;
            case "WAIT":
                transferStatus = "이체대기";
                break;
            case "PROCESSING":
                transferStatus = "이체진행중";
                break;
            case "FAIL":
                transferStatus = "실패";
                break;
            default:
                transferStatus = "알 수 없는 상태"; // 예외 처리
                break;
        }
        // 행 생성
        var row = $('<tr>')
            .append($('<td>').text(transfer.rn)) // 순번
            .append($('<td>').text(transferStatus)) // 상태
            .append($('<td>').text(transfer.accId)) // 비고 (여기선 계좌 ID)
            .append($('<td>').text(formattedTransferDate)) // 거래일시
            .append($('<td>').text(transfer.totalCnt)) // 총건수
            .append($('<td>').text(transfer.failureCnt)) // 실패건수
            .append($('<td>').text(transfer.successCnt)) // 성공건수
            .append($('<td>').text(comma(transfer.registeredAmount) + ' 원' )) // 총 등록금액
            .append($('<td>').text(comma(transfer.amount) + ' 원' )) // 총 이체금액
            .append($('<td>').append($('<a>')
                .text('상세보기')
                .attr('href', '/page/employee/bulk-transfer-result?bulkTransferId=' + transfer.id)
                .addClass('basic-btn')
                .css({'text-decoration': 'none'})// Bootstrap 클래스 추가
            )); // 상태



        bulkTransferResultTbody.append(row); // 생성된 행을 tbody에 추가
    });
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
        periodStartDate = formatDateToYYYYMMDD($('#period-start-input').val());
        periodEndDate = formatDateToYYYYMMDD($('#period-end-input').val());

    } else {
        console.log("선택된 기간: " + selectPeriod);
        periodStartDate = formatDateToYYYYMMDD(startDateOfPeriod(getCurrentDate, selectPeriod)); // 선택된 기간으로 시작일 계산
        console.log("계산된 시작일: " + periodStartDate);
        periodEndDate = formatDateToYYYYMMDD(getCurrentDate); // 종료일은 현재 영업일
        console.log("종료일: " + periodEndDate);
    }

    return {
        periodStartDate: periodStartDate,
        periodEndDate: periodEndDate
    };

}

function registerClickEventOfTradeCancelBtn() {
    $('.status-nor').on('click', function() {

        // 버튼이 클릭된 트리거의 부모인 <tr> 요소 찾기
        const $row = $(this).closest('tr');

        const tradeTypeClose = $row.find('.trade-type-close');
        const tradeTypeWithdrawal = $row.find('.trade-type-withdrawal');
        // hidden-trade-number 클래스의 값을 가져오기
        const tradeNumber = $row.find('.hidden-trade-number').text();
        const accountId = $row.find('#trade-list-trade-acc-id').text();



        if (tradeTypeWithdrawal.length > 0){
            // 결과 출력
            console.log('추출된 거래 번호:', tradeNumber);

            // AJAX 요청
            const url = '/page/employee/account-transfer-cancel?tradeNumber=' + tradeNumber; // 요청할 URL 설정
            window.location.href = url; // 지정한 URL로 이동


        }
        else if (tradeTypeClose.length > 0){
            // 결과 출력
            console.log('추출된 계좌 번호:', accountId);

            // AJAX 요청
            const url = '/page/employee/account-close-cancel?accountId=' + accountId; // 요청할 URL 설정
            window.location.href = url; // 지정한 URL로 이동
        }




    });
}


function formatDateToYYYYMMDD(dateString) {
    var date = new Date(dateString);  // 문자열로부터 Date 객체 생성
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 +1 해줍니다.
    var day = ("0" + date.getDate()).slice(-2);
    return year + "-" + month + "-" + day;
}
