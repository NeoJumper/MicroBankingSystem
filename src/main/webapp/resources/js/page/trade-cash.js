$(document).ready(function () {
    // 계좌 조회 버튼 클릭 시
    $('#check-cash-trade-account-btn').click(function () {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });

    // *자신의 페이지에 맞게 구현
    $('#search-modal-select-account-btn').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });

// 거래 유형(입금/출금) 선택 시
    $('input[name="trade-type"]').change(function () {
        var selectedTradeType = $('input[name="trade-type"]:checked').val();

        // 거래 유형이 바뀔 때 입력된 값을 초기화
        resetTradeForm();

        if (selectedTradeType === 'withdrawal') {
            // 출금 선택 시 비밀번호 입력란을 부드럽게 보이게 함
            $('#withdrawal-password-section').stop().slideDown();
        } else {
            // 입금 선택 시 비밀번호 입력란을 부드럽게 숨김
            $('#withdrawal-password-section').stop().slideUp();
        }
    });

    // 영업일 지정
    setNowDate();
    // 담당자 지정
    setAuthData();

    // 비밀번호 검증
    $('#cash-trade-validate').click(function(){
        validateAccountPassword();
    })
    
    // *실제 거래 생성
    $('#cash-trade-submit').click(function() {
        cashTradeSubmit();  // 선택된 계좌 처리 함수 호출
    });
});

// ----------------------------------------

// 거래 유형 변경 시 필드 초기화 함수
function resetTradeForm() {
    $('#cash-trade-account-number').val('');
    $('#cash-trade-customer-name').val('');
    $('#cash-deposit-trade-balance').val('');
    $('#cash-trade-amount').val('');
    $('#cash-trade-password').val('');  // 출금일 경우 비밀번호도 초기화
    $('#cash-trade-submit').prop('disabled', true);  // 승인 버튼 비활성화
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');  // 쉼표 제거
}

// 비밀번호 검증 클릭 시
function validateAccountPassword() {
    var accountNumber = $('#cash-trade-account-number').val();
    var accountPassword = $('#cash-trade-password').val();

    $.ajax( {
        url: '/api/employee/account-validate',
        contentType: "application/x-www-form-urlencoded",
        type: "POST",
        data: {
            accountNumber: accountNumber,
            password: accountPassword
        },
        success: function (response) {
            swal({
                title: "검증 완료",
                text: "비밀번호 인증 성공",
                icon: "success",
            })

            $('#cash-trade-submit').prop('disabled', false);

        }, error: function (error){
            swal({
                title: "검증 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });

            console.log("Transfer failed", error);
        }
    })

}


function cashTradeSubmit(){
    var accId = $('#cash-trade-account-number').val();
    var tradeType = $('input[name="trade-type"]:checked').val().toUpperCase();  // 입금/출금 구분
    var amount = parseInt(uncomma($('#cash-trade-amount').val()));
    var password = $('#cash-trade-password').val();

    $.ajax({
        url: "/api/employee/trade-cash",
        data: JSON.stringify({
            accId: accId,
            tradeType: tradeType,
            amount: amount,
            password: password
        }),
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
    $('#result-modal-cash-trade-registration-date').text(data.tradeDate);  // 날짜 부분만 표시
    $('#result-modal-cash-trade-registrant-id').text(data.registrantId);  // 실제 담당자 이름이 필요하면 추가 처리 필요
    $('#result-modal-cash-trade-trade-type').text(data.tradeType);

    // 모달 띄우기
    $('#result-modal-cash-trade').modal('show');
}


function setAuthData(){
    $.ajax({
        url: "/api/common/auth-data",
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
        url: '/api/common/current-business-day',
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

    var selectedTradeType = $('input[name="trade-type"]:checked').val();

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: selectedAccountId, productName: null},
        type: "GET",
        success: function (data) {
            if (selectedTradeType === "withdrawal") {
                // 출금 계좌 처리
                $('#cash-trade-account-number').text(data[0].accId);
                $('#cash-trade-customer-name').text(data[0].customerName + "  |  "   );
                $('#cash-trade-product-name').text(data[0].productName);
                $('#cash-deposit-trade-balance').text(data[0].balance.toLocaleString('ko-KR'));

                // 출금일 경우, 비밀번호 인증 후에만 승인 버튼 활성화
                $('#cash-trade-submit').prop('disabled', true); // 비밀번호 인증 전까지 비활성화

            } else if (selectedTradeType === "deposit") {
                // 입금 계좌 처리
                $('#cash-trade-account-number').text(data[0].accId);
                $('#cash-trade-customer-name').text(data[0].customerName + "  |  "   );
                $('#cash-trade-product-name').text(data[0].productName);
                $('#cash-deposit-trade-balance').text(data[0].balance.toLocaleString('ko-KR'));

                // 입금일 경우, 계좌 조회 후 바로 승인 버튼 활성화
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
