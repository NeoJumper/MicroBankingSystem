$(document).ready(function () {
    accountType = "";

    // 예약이체시에만 날짜/시간 선택가능

    // 라디오 버튼이 변경될 때 이벤트 처리

// 초기 설정: 숨김 상태로 시작
// 초기 설정: 숨김 상태로 시작 (height를 0으로)
// 초기 설정: 숨김 상태로 시작 (height를 0으로)
    $('#reserve-time-select-div').css({
        height: 0,
        transition: 'height 0.5s ease'
    });

// 라디오 버튼이 변경될 때 이벤트 처리
    $('input[name="scheduled-status"]').on('change', function() {
        if ($('#scheduled-transfer-btn').is(':checked')) {
            // 예약 이체가 체크됐을 때 height를 자동으로 변경하여 자연스럽게 보여줌
            $('#reserve-time-select-div').css({
                height: $('#reserve-time-select-div')[0].scrollHeight + 'px'
            });
        } else {
            // 즉시 이체가 체크됐을 때 height를 0으로 변경하여 숨김
            $('#reserve-time-select-div').css({
                height: 0
            });
        }
    });





    // 출금계좌 조회 버튼 클릭 시
    $('#check-withdrawal-account-btn').click(function () {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });

    // 입금계좌 조회 버튼 클릭 시
    $('#check-deposit-account-btn').click(function () {
        accountType = $(this).data('account-type'); // "deposit" 저장
    });


    $('.amount-btn').click(function () {
        setAmount(this);
    });

    $('#search-modal-select-account-btn').click(function () {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });

    $(document).on('input', '#transfer-amount', function () {
        $(this).val(comma(uncomma($(this).val())));

        var inputAmount = parseFloat(uncomma($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
        var accountBalance = parseFloat(uncomma($('#account-balance').text()));  // 계좌 잔액에서 쉼표 제거 후 숫자로 변환

        if (inputAmount > accountBalance) {
            $('#over-account-balance').text("계좌 잔액을 초과했습니다.");
            $(this).val(comma(accountBalance));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-account-balance').text("");  // 경고 메시지 제거
        }
    });


    // 입력 필드를 벗어났을 때 경고 메시지를 제거
    $(document).on('blur', '#transfer-amount', function () {
        $('#over-account-balance').text("");
    });

    // 모달 내 계좌 검색 버튼 클릭 시 검색 처리 후 중복 계좌 비활성화
    $(document).on('ajaxSuccess', function (event, xhr, settings) {
        if (settings.url.includes("/api/employee/account")) {
            disableDuplicateAccounts();  // AJAX 성공 후 중복 계좌 처리 함수 호출
        }
    });


    // 이체하기 버튼 클릭 시
    $('#account-transfer-submit').click( function (){
        transferSubmit();
    })

    $('#account-transfer-validate').click(function(){
        validateAccountPassword();
    })



});

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');  // 쉼표 제거
}


// 중복 계좌를 비활성화하는 함수
function disableDuplicateAccounts() {
    var withdrawalAccountId = $('#withdrawal-account-number').val();  // 출금 계좌 번호 가져오기

    if (withdrawalAccountId) {
        // 모달에 있는 모든 라디오 버튼을 순회하면서 출금 계좌와 동일한 계좌를 찾아 비활성화
        $('#search-modal-common-table tbody tr').each(function () {
            var accountId = $(this).find('td:eq(1)').text();  // 각 계좌의 계좌 번호 추출

            if (accountId === withdrawalAccountId) {
                // 동일한 계좌일 경우 라디오 버튼 비활성화
                $(this).find('input[name="select-account"]').prop('disabled', true);
            }
        });
    }
}


function setAmount(button) {
    $('.amount-btn').removeClass('active');
    $(button).addClass('active');

    var amountText = $(button).text().replace(/[^0-9]/g, ''); // 숫자만 추출
    var amount = parseInt(amountText) * 10000;  // 만 단위로 변환

    // 만약 '전액' 버튼을 누르면 전체 잔액을 설정할 수 있음
    if ($(button).text() === '전액') {
        amount = parseInt($('#account-balance').text().replace(/[^0-9]/g, ''));
    }

    $('#transfer-amount').val(comma(amount));  // 쉼표가 포함된 값으로 설정
}

// 선택한 계좌 처리 함수
function selectAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        alert("계좌를 선택해 주세요.");
        return;
    }

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: selectedAccountId, productName: null},
        type: "GET",
        success: function (data) {
            if (accountType === "withdrawal") {
                // 출금계좌 처리
                $('#withdrawal-customer-name').text(data[0].customerName + '  |   ');
                $('#withdrawal-product-name').text(data[0].productName);
                $('#withdrawal-account-number').text(data[0].accId);

                // 계좌 잔액 라벨을 표시하고 금액 업데이트
                $('#account-balance').text(data[0].balance.toLocaleString('ko-KR'));
                enableAmountButtons(data[0].balance); // 계좌 잔액을 넘겨줌
            } else if (accountType === "deposit") {
                // 입금계좌 처리
                $('#deposit-account-number').val(data[0].accId);
                $('#deposit-customer-name').val(data[0].customerName);
            }

            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}

// 출금 계좌가 불러와졌을 때 실행하는 함수
function enableAmountButtons(balance) {
    $('#transfer-amount').prop('disabled', false);
    $('.amount-btn').each(function () {
        var buttonText = $(this).text().replace(/[^0-9]/g, ''); // 숫자만 추출
        var buttonAmount = parseInt(buttonText) * 10000;  // 버튼 금액 만 단위로 변환

        if (buttonAmount > balance) {
            $(this).prop('disabled', true);  // 잔액보다 큰 금액 버튼 비활성화
        } else {
            $(this).prop('disabled', false);  // 잔액 이하인 금액 버튼은 활성화
        }
    });

    // 전액 버튼은 항상 활성화
    $('.amount-btn:contains("전액")').prop('disabled', false);
}


// 비밀번호 검증 클릭 시
function validateAccountPassword() {
    var accountNumber = $('#withdrawal-account-number').text();
    var accountPassword = $('#transfer-account-password').val();


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

            $('#account-transfer-submit').prop('disabled', false);

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


// 이체하기 버튼 클릭 시
function transferSubmit() {
    var withdrawalAccountId = $('#withdrawal-account-number').val();
    var depositAccountId = $('#deposit-account-number').val();
    var transferAmount = parseInt(uncomma($('#transfer-amount').val()));
    var description = $('#description').val();
    var accountPassword = $('#transfer-account-password').val();

    $.ajax({
        url: "/api/employee/account-transfer",
        contentType: "application/json",
        type: "POST",
        data: JSON.stringify({
            accId: withdrawalAccountId,
            targetAccId: depositAccountId,
            transferAmount: transferAmount,
            description: description,
            accountPassword: accountPassword
        }),

        success: function (data) {

            swal({
                title: "이체 완료",
                text: "계좌 이체 성공",
                icon: "success",
            })
            // 이체 성공 후 모달에 데이터를 채움
            showTransferResultModal(data);

        },
        error: function (error) {
            // 예외 처리 알림

            swal({
                title: "계좌 이체 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });

            console.log("Transfer failed", error);
        }
    });

}

function showTransferResultModal(data) {
    // 출금 내역
    var withdrawal = data[0];
    $('#modal-result-withdrawal-account').text(withdrawal.accId);
    $('#modal-result-withdrawal-customer-name').text(withdrawal.customerName);
    $('#modal-result-withdrawal-amount').text(comma(withdrawal.amount));
    $('#modal-result-withdrawal-balance').text(comma(withdrawal.balance));

    // 입금 내역
    var deposit = data[1];
    $('#modal-result-deposit-account').text(deposit.accId);
    $('#modal-result-deposit-customer-name').text(deposit.customerName);
    $('#modal-result-deposit-amount').text(comma(deposit.amount));
    $('#modal-result-deposit-balance').text(comma(deposit.balance));

    // 모달 띄우기
    $('#transfer-result-modal').modal('show');
}
