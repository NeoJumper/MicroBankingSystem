$(document).ready(function () {
    accountType = "";

    // 계좌 조회 버튼 클릭 시
    $('#check-cash-trade-account-btn').click(function () {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });

    // *자신의 페이지에 맞게 구현
    $('#search-modal-select-account-btn').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });

    // 영업일 지정
    setNowDate();
    // 담당자 지정
    setAuthData();

    // *자신의 페이지에 맞게 구현
    $('#cash-trade-submit').click(function() {
        cashTradeSubmit();  // 선택된 계좌 처리 함수 호출
    });
});

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');  // 쉼표 제거
}


function cashTradeSubmit(){
    var accId = $('#cash-trade-account-number').val();
    var tradeType = "DEPOSIT";
    var amount = parseInt(uncomma($('#cash-trade-amount').val()));

    $.ajax({
        url: "/api/employee/account-cash-trade",
        data: JSON.stringify({accId: accId, tradeType: tradeType, amount: amount}),
        type: "POST",
        contentType: "application/json",
        success: function (data) {

            swal({
                title: "거래 완료",
                text: "입/출금 성공",
                icon: "success",
            })
            showCashTradeResultModal(data);

        },
        error: function (error) {
            console.log("Error:", error);
        }
    });
}

function showCashTradeResultModal(data){
    // 모달에 데이터를 설정
    $('#result-modal-cash-trade-acc-id').text(data.accId);
    $('#result-modal-cash-trade-amount').text(comma(data.amount));  // 쉼표 추가
    $('#result-modal-cash-trade-balance').text(comma(data.balance));  // 쉼표 추가
    $('#result-modal-cash-trade-registration-date').text(data.registrationDate.substring(0, 10));  // 날짜 부분만 표시
    $('#result-modal-cash-trade-registrant-id').text(data.registrantId);  // 실제 담당자 이름이 필요하면 추가 처리 필요
    $('#result-modal-cash-trade-trade-type').text(data.tradeType);

    // 모달 띄우기
    $('#result-modal-cash-trade').modal('show');
}


function setAuthData(){
    $.ajax({
        url: "/api/auth-data",
        type: "GET",
        success: function(data) {
            $('#cash-trade-employee').val(data.name);
        }, error: function(data) {
            console.log(data);
        }
    });
  }

function setNowDate() {
    $.ajax({
        url: '/api/current-business-day',
        type: 'GET',
        success: function(data) {
            var formattedDate = data.businessDate.substring(0, 10);
            $('#cash-trade-registration-date').val(formattedDate);
        },
        error: function(error) {
            console.error('error:', error);
        }
    });
}

function selectAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        alert("계좌를 선택해 주세요.");
        return;
    }

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/account",
        data: {accId: selectedAccountId, productName: null},
        type: "GET",
        success: function (data) {
            if (accountType === "withdrawal") {
                // 출금계좌 처리

            } else if (accountType === "deposit") {
                console.log(data);
                // 입금계좌 처리
                $('#cash-trade-account-number').val(data[0].accId);
                $('#cash-trade-customer-name').val(data[0].customerName);
                $('#cash-deposit-trade-balance').val(data[0].balance.toLocaleString('ko-KR'));

                // 입금 승인 버튼 활성화
                $('#cash-trade-submit').prop('disabled', false);
            }
            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}