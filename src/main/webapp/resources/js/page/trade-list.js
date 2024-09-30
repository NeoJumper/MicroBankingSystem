
$(document).ready(function () {
    
    customPeriodBtnDisplay();
    searchPeriodTypeDisplay();
    tradeStatusSelectDisplay();
    tradePeriodSelectDisplay();
    accIdOfSearchAccountModal();
    SearchResultOfTradeList();
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

function accIdOfSearchAccountModal(){
    $('#search-modal-select-account-btn').on("click",function (){
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

// 날짜 조건 계산 함수
function searchPeriodCalculation() {


}

// $('.trade-period-search-btn.active').val();
// 거래내역 조회 api
function SearchResultOfTradeList() {
    $("#trade-list-search-btn").on("click",function(){

        alert("거래내역 조회 api");
        const constAccId = $('#acc-id-input').val();
        const activeButtonValue = $('.trade-status-search-btn.active').val();

        const selectedValue = $('input[name="search-sort"]:checked').val();
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();
        console.log("constAccId"+constAccId);
        console.log(activeButtonValue);
        console.log("selectedValue"+selectedValue);
        console.log("startDate"+startDate);
        console.log("endDate"+endDate);

        $.ajax({
           url: '/api/employee/trade/search/result',
           method: 'GET',
           data: {
               accId: constAccId,
               tradeType: activeButtonValue,
               startDate: startDate,
               endDate: endDate,
               sortOrder: selectedValue
           },
           success: function(data){
               renderSearchResults(data);

           },error: function(error) {
               alert('검색 요청에 실패했습니다: ' + error);
           }
       })

    })
}

// 거래내역 동적 테이블 생성 : 검색결과 뿌리기
function renderSearchResults(data) {
    const tradeResultsTableBody = $('#trade-result-tbody');
    tradeResultsTableBody.empty();

   $.each(data, function(index,trade){
       var row = $('<tr>')
           .append($('<td>').text(index+1))
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