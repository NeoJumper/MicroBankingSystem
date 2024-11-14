var securityLevel = '';
var customerId = null;
var otpInputModal;
var accountType = "";
var perTradeLimit = 0;
var dailyLimit = 0;

var perTradeLimitMax = 0;
var dailyLimitMAX = 0;

$(document).ready(function () {

    isClosed();

    clickWithdrawalAccountCheckBtn(); // 출금 계좌 조회 버튼

    clickAccountSelectBtn(); // 계좌 선택 버튼

    handleDailyLimitInput() // 입력 시 숫자 제외한 문자 모두 제거, 계좌 잔액 초과하는 입력 제거, 0 이상은 입력못함(00000 등)
    handlePerTradeLimitInput();

    removeErrorMessage();    // 입력 필드를 벗어났을 때 경고 메시지를 제거

    clickValidatePasswordBtn();

    handleOtpInput(); // OTP 입력란 자동 이동

    clickOtpAuthenticationModalBtn(); // OTP 인증 모달 띄우는 버튼 클릭

    clickOtpAuthenticationBtn(); // OTP 인증 버튼 클릭

    clickAccountUpdateBtn(); // 변경하기 버튼 클릭

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
            console.log("1회 한도:  " +  data.perTradeLimit);
            console.log("1일 한도:  " +  data.dailyLimit);
            securityLevel = data.securityLevel;
            customerId = data.customerId;
            // 출금계좌 처리

            $('#account-number-input').val(data.accId);
            $('#customer-name-input').val(data.customerName);
            $('#per-trade-limit-input').val(comma(data.perTradeLimit));
            $('#daily-limit-input').val(comma(data.dailyLimit));

            perTradeLimit = data.perTradeLimit;
            dailyLimit = data.dailyLimit;
            accountType = data.accountType;

            highlightTransferLimit();
            setTransferLimits();

            // 모달 닫기
            $('#search-modal-account').modal('hide');
            console.log("OTP 레벨 : " + securityLevel);
            handleOtpBtn(); // OTP or 이체하기 버튼 활성화
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}

function highlightTransferLimit() {
    // 구분에 따른 row 인덱스 설정
    console.log("계좌유형 : " + accountType);
    console.log("보안등급 : " + securityLevel);
    let rowIndex = accountType === "PRIVATE" ? 1 : 2;
    // 보안등급에 따른 column 인덱스 설정
    let colIndex = securityLevel === "1등급" ? 2 : 3;

    // 모든 셀의 스타일 초기화
    $(".transfer-limit-table tbody td")
        .css("background-color", "")   // 기본 배경색으로 초기화
        .css("color", "")              // 기본 글자색으로 초기화
        .css("font-weight", "");       // 기

    // 색칠할 셀 선택 및 강조 스타일 적용
    $(".transfer-limit-table tbody tr:nth-child(" + rowIndex + ") td:nth-child(" + colIndex + ")")
        .css("background-color", "#f1f8ff")
        .css("color", "#3f5ba9")
        .css("font-weight", "bold")// 원하는 색상으로 변경 가능
}

function setTransferLimits() {

    if (accountType === "PRIVATE" && securityLevel === "1등급") {
        dailyLimitMAX = 500000000;      // 개인 1등급: 1일 이체한도 5억원
        perTradeLimitMax = 100000000;   // 개인 1등급: 1회 이체한도 1억원
    } else if (accountType === "PRIVATE" && securityLevel === "2등급") {
        dailyLimitMAX = 10000000;       // 개인 2등급: 1일 이체한도 1천만원
        perTradeLimitMax = 5000000;     // 개인 2등급: 1회 이체한도 5백만원
    } else if (accountType === "CORPORATION" && securityLevel === "1등급") {
        dailyLimitMAX = 5000000000;     // 기업 1등급: 1일 이체한도 50억원
        perTradeLimitMax = 1000000000;  // 기업 1등급: 1회 이체한도 10억원
    } else if (accountType === "CORPORATION" && securityLevel === "2등급") {
        dailyLimitMAX = 3000000;        // 기업 2등급: 1일 이체한도 3백만원
        perTradeLimitMax = 3000000;     // 기업 2등급: 1회 이체한도 3백만원
    }

    console.log("1일 이체한도 MAX:", dailyLimitMAX);
    console.log("1회 이체한도 MAX:", perTradeLimitMax);
}


// 비밀번호 검증 클릭 시
function validateAccountPassword() {
    var accountNumber = $('#account-number-input').val();
    var accountPassword = $('#account-password').val();


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

            $('#account-update-btn').prop('disabled', false);
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

function clickAccountUpdateBtn(){

    $('#account-update-btn').click(function() {
        updateAccount();

    });
}

// 이체하기 버튼 클릭 시
function updateAccount() {
    var accountId = $('#account-number-input').val();

    $.ajax({
        url: "/api/employee/accounts/" + accountId + "/transfer-limit",
        contentType: "application/json",
        type: "PATCH",
        data: JSON.stringify({
            targetAccId: accountId,
            perTradeLimit : convertNumber($('#per-trade-limit-input').val()),
            dailyLimit : convertNumber($('#daily-limit-input').val()),
        }),

        success: function (data) {
            console.log(data);
            swal({
                title: "이체 한도 변경 성공",
                text: "이체 한도 변경 작업이 성공적으로 처리되었습니다.",
                icon: "success",
            }).then(() => {
                // swal의 닫기 버튼이 클릭된 후 실행
                window.location.href = '/page/employee/transfer-limit-update';
            });

        },
        error: function (error) {
            // 예외 처리 알림
            swal({
                title: "이체 한도 변경 실패",
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
                $('#account-update-btn').show();
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

function handleDailyLimitInput() {
    $(document).on('input', '#daily-limit-input', function () {
        $(this).val(comma(convertNumber($(this).val())));

        var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환

        if (inputAmount > dailyLimitMAX) {
            $('#over-daily-limit-amount').text("최대 이체 한도 내 금액을 입력해주세요.");
            $(this).val(comma(dailyLimitMAX));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-daily-limit-amount').text("");  // 경고 메시지 제거
        }
    });
}
function handlePerTradeLimitInput() {
    $(document).on('input', '#per-trade-limit-input', function () {
        $(this).val(comma(convertNumber($(this).val())));

        var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환


        if (inputAmount > perTradeLimitMax) {
            $('#over-per-trade-limit-amount').text("최대 이체 한도 내 금액을 입력해주세요.");
            $(this).val(comma(perTradeLimitMax));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-per-trade-limit-amount').text("");  // 경고 메시지 제거
        }
    });
}

function removeErrorMessage() {
    $(document).on('blur', '#daily-limit-input', function () {
        $('#over-daily-limit-amount').text("");
    });

    $(document).on('blur', '#per-trade-limit-input', function () {
        $('#over-per-trade-limit-amount').text("");
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
        $('#account-update-btn').hide();
        $('#otp-authentication-modal-btn').show();
    }
    else{
        $('#account-update-btn').show();
        $('#otp-authentication-modal-btn').hide();
    }
}

