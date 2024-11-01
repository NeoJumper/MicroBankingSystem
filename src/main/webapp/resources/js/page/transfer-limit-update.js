var securityLevel = '';
var customerId = null;
var otpInputModal;
var accountType = "";

$(document).ready(function () {

    isClosed();

    clickWithdrawalAccountCheckBtn(); // 출금 계좌 조회 버튼

    clickAccountSelectBtn(); // 계좌 선택 버튼

    handleAmount(); // 입력 시 숫자 제외한 문자 모두 제거, 계좌 잔액 초과하는 입력 제거, 0 이상은 입력못함(00000 등)

    removeErrorMessage();    // 입력 필드를 벗어났을 때 경고 메시지를 제거

    clickValidatePasswordBtn();

    handleOtpInput(); // OTP 입력란 자동 이동

    clickOtpAuthenticationModalBtn() // OTP 인증 모달 띄우는 버튼 클릭

    clickOtpAuthenticationBtn() // OTP 인증 버튼 클릭

});

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

function convertNumber(str) {
    str = String(str.replace(/[^\d]+/g, ''));  // 숫자를 제외한 모든 문자 제거


    if (/^0{2,}/.test(str)) {
        // 두 번 이상 연속된 0을 잘라내고 나머지 부분 반환
        return str.replace(/^0{2,}/, '0');
    }
    return str;
}



// 선택한 계좌 처리 함수
function selectAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        alert("계좌를 선택해 주세요.");
        return;
    }
    console.log(selectedAccountId)

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts/" + selectedAccountId,
        type: "GET",
        success: function (data) {
            if (accountType === "withdrawal") {

                let transferAmountOfToday = data.transferAmountOfToday || 0;
                securityLevel = data.securityLevel;
                customerId = data.customerId;
                // 출금계좌 처리

                $('#account-number-input').val(data.accId);
                $('#customer-name-input').val(data.customerName);
                $('#per-trade-limit-input').val(comma(data.perTradeLimit));
                $('#daily-limit-input').val(comma(data.dailyLimit));


            }
            // 모달 닫기
            $('#search-modal-account').modal('hide');
            handleOtpBtn(); // OTP or 이체하기 버튼 활성화
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
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
            $('#otp-authentication-modal-btn').prop('disabled', false);

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
    var withdrawalAccountId = $('#withdrawal-account-number').text();
    var depositAccountId = $('#deposit-account-number').val();
    var transferAmount = parseInt(convertNumber($('#transfer-amount').val()));
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
            console.log(data);
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

// 인증하기 버튼 클릭
function clickOtpAuthenticationBtn() {

    $('#otp-authenticate-btn').click(function() {

        var values = $('.auth-code-input').map(function() {
            return $(this).val();
        }).get(); // 배열로 변환

        // 배열을 문자열로 합치기
        var combinedValue = values.join('');

        $.ajax({
            url: "/api/common/otp-authentication",
            data:{
                userId : customerId,
                userCode : combinedValue
            },
            type: "POST",
            success: function() {

                otpInputModal.hide();
                $('#account-transfer-submit').show();
                $('#otp-authentication-modal-btn').hide();


                swal({
                    title: "OTP 인증 성공",
                    text: "OTP 인증이 성공적으로 수행되었습니다.",
                    icon: "success",
                    button: "닫기",
                })



            },
            error: function(xhr, status, error) {
                swal({
                    title: "OTP 인증 실패",
                    text: "OTP 인증에 실패했습니다.",
                    icon: "error",
                    button: "닫기",
                })
            }
        });




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

function clickWithdrawalAccountCheckBtn() {
    // 출금계좌 조회 버튼 클릭 시
    $('#withdrawal-account-check-btn').click(function () {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });
}

function clickAccountSelectBtn() {
    $('#search-modal-select-account-btn').click(function () {
        selectAccount();  // 선택된 계좌 처리 함수 호출

    });
}

function handleAmount() {
    $(document).on('input', '#transfer-amount', function () {
        $(this).val(comma(convertNumber($(this).val())));

        var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
        var transferableAmount = parseFloat(convertNumber($('#transferable-amount').text()));  // 계좌 잔액에서 쉼표 제거 후 숫자로 변환

        if (inputAmount > transferableAmount) {
            $('#over-account-balance').text("이체 가능 금액을 초과했습니다.");
            $(this).val(comma(transferableAmount));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-account-balance').text("");  // 경고 메시지 제거
        }
    });
}

function removeErrorMessage() {
    $(document).on('blur', '#transfer-amount', function () {
        $('#over-account-balance').text("");
    });
}

function clickValidatePasswordBtn() {
    $('#account-transfer-validate').click(function(){
        validateAccountPassword();
    })
}
function handleOtpInput() {
    $('.auth-code-input').on('input', function() {
        // 숫자 외의 입력은 제거
        var value = $(this).val();
        if (!/^\d$/.test(value)) {
            $(this).val(''); // 숫자 외의 입력은 비움
            return;
        }

        // 다음 인풋으로 자동 포커스 이동
        var nextInput = $(this).next('.auth-code-input');
        if (nextInput.length && value !== '') {
            nextInput.focus();
        }
    });

    $('.auth-code-input').on('keydown', function(e) {
        // 백스페이스 시 이전 칸으로 이동
        if (e.key === 'Backspace' && $(this).val() === '') {
            var prevInput = $(this).prev('.auth-code-input');
            if (prevInput.length) {
                prevInput.focus();
            }
        }
    });
}
function clickOtpAuthenticationModalBtn(){
    $('#otp-authentication-modal-btn').click(function () {
        showOtpInputModal();
    });
}

function showOtpInputModal() {
    otpInputModal = new bootstrap.Modal(document.getElementById('otp-input-modal'));
    otpInputModal.show();
}

function handleOtpBtn() {
    console.log(securityLevel);
    if(securityLevel === '1등급'){
        $('#account-transfer-submit').hide();
        $('#otp-authentication-modal-btn').show();
    }
    else{
        $('#account-transfer-submit').show();
        $('#otp-authentication-modal-btn').hide();
    }
}