var securityLevel = '';
var customerId = null;

$(document).ready(function () {
    isClosed();

    clickCashTradeAccountSelectBtn(); // 계좌선택 시

    clickTradeTypeSelectBtn();    // 거래 유형(입금/출금) 선택 시

    clickValidatePasswordBtn(); // 비밃번호 검증

    clickCashTradeBtn(); // 현금 거래 요청

    handleAmountInput(); // 입력 시 숫자 제외한 문자 모두 제거, 계좌 잔액 초과하는 입력 제거, 0 이상은 입력못함(00000 등)

    clickTradeAmountBtn(); // 100, 50, 10, 5, 1, 전액 버튼

    removeErrorMessage();    // 입력 필드를 벗어났을 때 경고 메시지를 제거

    clickOtpAuthenticationModalBtn() // OTP 인증 모달 띄우는 버튼 클릭

    handleOtpInput(); // OTP input 동작 핸들링

    clickOtpAuthenticationBtn(); // OTP인증하기 버튼

    getEmployeeCashBalance();

    $('.trade-amount-button-group').css({
        height: 0,
        overflow: 'hidden',  // 숨길 때 내용이 잘리도록 함
        transition: 'height 0.5s ease'  // 부드러운 애니메이션 적용
    });

    $('#result-modal-cash-trade').on('hidden.bs.modal', function () {
        window.location.href = `/page/employee/cash-trade`;
    })
});

// ----------------------------------------

// 거래 유형 변경 시 필드 초기화 함수
function resetTradeForm() {
    $('#cash-trade-account-number').text('계좌를 선택해주세요');
    $('#cash-trade-customer-name').text('');
    $('#cash-trade-product-name').text('');
    $('#limit-span').val(0);
    $('#cash-trade-balance').css('margin-left', '20px').text('0');
    $('#cash-trade-amount').val(0);
    $('.krw-amount-input').val('').hide();
    $('#cash-trade-password').val('');  // 출금일 경우 비밀번호도 초기화
    $('#cash-trade-submit').prop('disabled', true);  // 승인 버튼 비활성화
    enableAmountButtons(0); // 금액 입력 버튼 비활성화
    disableAmountButtons();
    handleAmount($('#cash-trade-amount'));
}

function handleAmountInput() {
    $(document).on('input', '#cash-trade-amount', function () {
        handleAmount($(this));
    });
}
function handleAmount(element) {
    let value = element.val();

    if(String(value) === '' || value === '0'){
        element.siblings('.krw-amount-input').val('').hide();
        value = 0;
        element.val(value);
        return;
    }

    if($('input[name="trade-type"]:checked').val() === 'withdrawal'){
        var inputAmount = parseFloat(convertNumber(element.val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
        var accountBalance = parseFloat(convertNumber($('#cash-trade-balance').text()));  // 계좌 잔액에서 쉼표 제거 후 숫자로 변환
        var employeeCashBalance =  parseFloat(convertNumber($('.emp-close-prev-cash-balance').val()));

        if(accountBalance < employeeCashBalance){ // 계좌잔액이 행원의 현금 잔액보다 작다면
            if (accountBalance < inputAmount) {
                $('#over-account-balance').text("계좌 잔액을 초과했습니다.");
                element.val(comma(accountBalance));  // 입력된 값을 계좌 잔액으로 제한
                value = accountBalance;
            }
            else {
                $('#over-account-balance').text("");  // 경고 메시지 제거
                value = parseFloat(convertNumber(value));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
            }
        }
        else
        {
            if (employeeCashBalance < inputAmount ) {
                $('#over-account-balance').text("현금 잔액을 초과했습니다. 시재금 거래를 진행해주세요!");
                element.val(comma(employeeCashBalance));  // 입력된 값을 현금 잔액으로 제한
                value = employeeCashBalance;
            }
            else {
                $('#over-account-balance').text("");  // 경고 메시지 제거
                value = parseFloat(convertNumber(value));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
            }
        }

    }
    else{
        value = parseFloat(convertNumber(value));  // 입력된 값에서 쉼표 제거 후 숫자로 변환
    }
    element.siblings('.krw-amount-input').val(convertToKoreanNumber(parseInt(value)) + '원').show();

    console.log(value);
    // 숫자를 한국 원화 형식으로 변환
    value = String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    // 변환된 값을 다시 input에 설정
    element.val(value);

}



// 비밀번호 검증 클릭 시
function validateAccountPassword() {

    var accountNumber = $('#cash-trade-account-number').text();
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


function cashTradeSubmit(){
    var accId = $('#cash-trade-account-number').text();
    var tradeType = $('input[name="trade-type"]:checked').val().toUpperCase();  // 입금/출금 구분
    var amount = parseInt(convertNumber($('#cash-trade-amount').val()));
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

            securityLevel = data[0].securityLevel;
            customerId = data[0].customerId;

            if (selectedTradeType === "withdrawal") {
                // 출금 계좌 처리
                $('#cash-trade-account-number').text(data[0].accId);
                $('#cash-trade-customer-name').text(data[0].customerName +'(' + securityLevel + ')  |   ');
                $('#cash-trade-product-name').text(data[0].productName);
                $('#cash-trade-balance').text(data[0].balance.toLocaleString('ko-KR'));

                // 출금일 경우, 비밀번호 인증 후에만 승인 버튼 활성화
                handleOtpBtn();
                $('#cash-trade-submit').prop('disabled', true); // 비밀번호 인증 전까지 비활성화

            } else if (selectedTradeType === "deposit") {
                // 입금 계좌 처리
                $('#cash-trade-account-number').text(data[0].accId);
                $('#cash-trade-customer-name').text(data[0].customerName + "  |  "   );
                $('#cash-trade-product-name').text(data[0].productName);
                $('#cash-trade-balance').text(data[0].balance.toLocaleString('ko-KR'));

                // 입금일 경우, 계좌 조회 후 바로 승인 버튼 활성화
                $('#cash-trade-submit').prop('disabled', false);
            }
            enableAmountButtons(data[0].balance); // 금액 입력 버튼 활성화

            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}
function clickValidatePasswordBtn() {
    // 비밀번호 검증
    $('#cash-trade-validate').click(function(){
        validateAccountPassword();
    })

}
function clickCashTradeBtn() {
    // *실제 거래 생성
    $('#cash-trade-submit').click(function() {
        cashTradeSubmit();  // 선택된 계좌 처리 함수 호출
    });
}
function clickTradeTypeSelectBtn() {
    $('input[name="trade-type"]').change(function () {
        var selectedTradeType = $('input[name="trade-type"]:checked').val();

        $('.transfer-possible-amount').toggle(); // 이체금액 토글

        // 거래 유형이 바뀔 때 입력된 값을 초기화
        resetTradeForm();

        if (selectedTradeType === 'withdrawal') {
            // 출금 선택 시 비밀번호 입력란을 부드럽게 보이게 함
            $('#account-info-h3').text('출금 계좌 정보');
            $('label[for="cash-trade-amount"]').text('출금액');
            $('#withdrawal-password-section').stop().slideDown();
            $('.trade-amount-button-group').css({
                height: '44px'
            });

        } else {
            // 입금 선택 시 비밀번호 입력란을 부드럽게 숨김
            $('#account-info-h3').text('입금 계좌 정보');
            $('label[for="cash-trade-amount"]').text('입금액');
            $('#withdrawal-password-section').stop().slideUp();
            $('.trade-amount-button-group').css({
                height: 0
            });
        }
    });
}
function clickCashTradeAccountSelectBtn() {
    $('#search-modal-select-account-btn').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });
}
// 출금 계좌가 불러와졌을 때 실행하는 함수
function enableAmountButtons(balance) {
    $('#cash-trade-amount').prop('disabled', false);
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

function removeErrorMessage() {
    $(document).on('blur', '#cash-trade-amount', function () {
        $('#over-account-balance').text("");
    });
}
// 금액 입력란과 버튼들 모두 비활성화
function disableAmountButtons(balance) {
    $('#cash-trade-amount').prop('disabled', true);

    $('.amount-btn').each(function () {
        $(this).prop('disabled', true).removeClass('active');  // 잔액보다 큰 금액 버튼 비활성화
    });

    // 전액 버튼은 항상 활성화
    $('.amount-btn:contains("전액")').prop('disabled', true).removeClass('active');
}
function clickTradeAmountBtn() {
    $('.amount-btn').click(function () {
        setAmount(this);
        handleAmount($('#cash-trade-amount'));
    });
}
function setAmount(button) {
    $('.amount-btn').removeClass('active');
    $(button).addClass('active');

    var amountText = $(button).text().replace(/[^0-9]/g, ''); // 숫자만 추출
    var amount = parseInt(amountText) * 10000;  // 만 단위로 변환

    // 만약 '전액' 버튼을 누르면 전체 잔액을 설정할 수 있음
    if ($(button).text() === '전액') {
        amount = parseInt($('#cash-trade-balance').text().replace(/[^0-9]/g, ''));
    }

    $('#cash-trade-amount').val(comma(amount));  // 쉼표가 포함된 값으로 설정
}
function handleOtpBtn() {
    console.log(securityLevel);
    if(securityLevel === '1등급'){
        $('#cash-trade-submit').hide();
        $('#otp-authentication-modal-btn').show();
    }
    else{
        $('#cash-trade-submit').show();
        $('#otp-authentication-modal-btn').hide();
    }
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
                $('#cash-trade-submit').show();
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
function getEmployeeCashBalance() {
    $.ajax({
        url: "/api/employee/cash-balance",

        type: "GET",
        success: function(employeeCashBalance) {
            $('.emp-close-prev-cash-balance').val(comma(employeeCashBalance));
        },
        error: function(xhr, status, error) {
            console.log('행원 마감 데이터 조회 실패');
        }
    });
}