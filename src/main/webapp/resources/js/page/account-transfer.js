var securityLevel = '';
var customerId = null;



$(document).ready(function () {

    isClosed();

    accountType = "";

    clickReserveTransferBtn(); // 예약이체 버튼

    clickWithdrawalAccountCheckBtn(); // 출금 계좌 조회 버튼

    clickDepositAccountCheckBtn(); // 입금 계좌 조회 버튼

    clickAccountSelectBtn(); // 계좌 선택 버튼

    clickTransferAmountBtn(); // 100, 50, 10, 5, 1, 전액 버튼

    handleAmount(); // 입력 시 숫자 제외한 문자 모두 제거, 계좌 잔액 초과하는 입력 제거, 0 이상은 입력못함(00000 등)

    removeErrorMessage();    // 입력 필드를 벗어났을 때 경고 메시지를 제거

    clickTransferBtn();    // 이체하기 버튼 클릭 시

    clickValidatePasswordBtn();

    handleOtpInput(); // OTP 입력란 자동 이동

    clickOtpAuthenticationModalBtn() // OTP 인증 모달 띄우는 버튼 클릭

    clickOtpAuthenticationBtn() // OTP 인증 버튼 클릭

    handleTransferLimitTooltip(); // 이체한도 툴팁 마우스 호버 이벤트 관리

    // 모달 내 계좌 검색 버튼 클릭 시 검색 처리 후 중복 계좌 비활성화
    $(document).on('ajaxSuccess', function (event, xhr, settings) {
        if (settings.url.includes("/api/employee/account")) {
            disableDuplicateAccounts();  // AJAX 성공 후 중복 계좌 처리 함수 호출
        }
    });



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


// 중복 계좌를 비활성화하는 함수
function disableDuplicateAccounts() {
    var withdrawalAccountId = $('#withdrawal-account-number').text();  // 출금 계좌 번호 가져오기

    if (withdrawalAccountId) {
        // 모달에 있는 모든 라디오 버튼을 순회하면서 출금 계좌와 동일한 계좌를 찾아 비활성화
        $('#search-modal-common-table tbody tr').each(function () {
            var accountId = $(this).find('td:eq(1)').text();  // 각 계좌의 계좌 번호 추출

            //console.log("입금계좌 : " + accountId);
            if (accountId === withdrawalAccountId) {
                // 동일한 계좌일 경우 라디오 버튼 비활성화
                $(this).find('input[name="select-account"]').prop('disabled', true);
            }
        });
    }
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
        url: "/api/employee/accounts/" + selectedAccountId,
        type: "GET",
        success: function (data) {
            if (accountType === "withdrawal") {

                let transferAmountOfToday = data.transferAmountOfToday || 0;
                securityLevel = data.securityLevel;
                customerId = data.customerId;
                // 출금계좌 처리
                $('#withdrawal-customer-name').text(data.customerName +'(' + securityLevel + ')  |   ');
                $('#withdrawal-product-name').text(data.productName);
                $('#withdrawal-account-number').text(data.accId);
                $('#per-trade-limit').replaceWith(`<span id="per-trade-limit" class="amount-span">${comma(data.perTradeLimit)}&nbsp; 원</span>`);
                $('#daily-limit').replaceWith(`<span id="per-trade-limit" class="amount-span">${comma(data.dailyLimit)}&nbsp; 원</span>`);
                $('#transfer-amount-of-today').replaceWith(`<span id="transfer-amount-of-today" class="amount-span">${comma(transferAmountOfToday)}&nbsp; 원</span>`);


                // 금일 이체 한도
                var transferableAmountLimitOfToday = data.dailyLimit - transferAmountOfToday;


                // 현재 거래에서의 이체 가능 한도 = 1회 이체 한도 > 금일 이체 한도 ? 1회 이체 한도 : 금일 이체 한도;
                var currentTransferableLimit = data.perTradeLimit < transferableAmountLimitOfToday
                    ? data.perTradeLimit : transferableAmountLimitOfToday;

                // 현재 거래에서의 이체 가능 금액 = 한도와 잔액중 작은 금액을 사용
                var transferableAmount = currentTransferableLimit < data.balance ? currentTransferableLimit : data.balance;

                $('#transferable-amount-limit-of-today').replaceWith(`<span id="transferable-amount-limit-of-today" class="amount-span">${comma(transferableAmountLimitOfToday)}&nbsp; 원</span>`);
                $('#transferable-amount').replaceWith(`<span id="transferable-amount" style="margin-left: 20px;">${comma(transferableAmount)}&nbsp;</span>`);


                console.log(data);

                // 계좌 잔액 라벨을 표시하고 금액 업데이트
                $('#account-balance').text(data.balance.toLocaleString('ko-KR'));

                enableAmountButtons(transferableAmount); // 금액 입력 버튼 활성화
            } else if (accountType === "deposit") {
                // 입금계좌 처리
                $('#deposit-account-number').val(data.accId);
                $('#deposit-customer-name').val(data.customerName);
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

                swal({
                    title: "OTP 인증 성공",
                    text: "OTP 인증이 성공적으로 수행되었습니다.",
                    icon: "success",
                    button: "닫기",
                })
                var otpDetailModal = new bootstrap.Modal(document.getElementById('otp-detail-modal'));
                otpDetailModal.hide();

                $('#account-transfer-submit').show();
                $('#otp-authentication-modal-btn').hide();

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

/**
 *   @Description
 *   예약이체시에만 날짜/시간 선택가능
 *   라디오 버튼이 변경될 때 이벤트 처리
 *   초기 설정: 숨김 상태로 시작 (height를 0으로)
 */
function clickReserveTransferBtn() {
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
}

function clickWithdrawalAccountCheckBtn() {
    // 출금계좌 조회 버튼 클릭 시
    $('#withdrawal-account-check-btn').click(function () {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });
}

function clickDepositAccountCheckBtn() {

    // 입금계좌 조회 버튼 클릭 시
    $('#deposit-account-check-btn').click(function () {
        accountType = $(this).data('account-type'); // "deposit" 저장
    });
}

function clickTransferAmountBtn() {
    $('.amount-btn').click(function () {
        setAmount(this);
    });
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

function clickTransferBtn() {
    $('#account-transfer-submit').click( function (){
            transferSubmit();
    })

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
    var otpInputModal = new bootstrap.Modal(document.getElementById('otp-input-modal'));
    otpInputModal.show();
}
function handleTransferLimitTooltip() {
    $('#select-transfer-limit').hover(
        function() {
            // 마우스가 올라갔을 때
            $('#select-transfer-limit-tooltip').css('opacity', '1');
        },
        function() {
            // 마우스가 벗어났을 때
            $('#select-transfer-limit-tooltip').css('opacity', '0');
        }
    );
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