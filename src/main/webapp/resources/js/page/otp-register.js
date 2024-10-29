var timerInterval = 0;

$(document).ready(function () {

    customerSearchInputEnterEvent();
    customerSearchBtnClickEvent();
    insertCustomerId();

    var otpDetailModal = new bootstrap.Modal(document.getElementById('otp-detail-modal'));
    otpDetailModal.show();

    clickSendBtn();
    clickResendBtn();
    clickAuthenticateBtn();
    clickOtpRegisterBtn();
});

// ------------------------------- START customerSearchModalEvent()------------------------------------------
// 모달 내 고객 검색 버튼 클릭시 함수
function customerSearchInputEnterEvent() {

    $('#search-modal-search-btn').keypress(function(event) {

        if (event.which === 13) {
            const searchOption = $('#search-modal-select').val(); // 드롭다운에서 선택된 값
            const searchValue = $('#search-modal-input').val(); // 입력 필드의 값 가져오기
            searchCustomer(searchOption, searchValue);
        }
    });
}

// 모달 내 고객 검색 버튼 클릭시 함수
function customerSearchBtnClickEvent() {

    $('#search-modal-search-btn').on('click', function () {

        const searchOption = $('#search-modal-select').val(); // 드롭다운에서 선택된 값

        const searchValue = $('#search-modal-input').val(); // 입력 필드의 값 가져오기

        searchCustomer(searchOption, searchValue);
    });
}

function searchCustomer(searchOption, searchValue) {
    $.ajax({
        url: '/api/common/customer',
        type: 'GET',
        data: {
            searchOption: searchOption,
            searchValue: searchValue,
        },
        success: function (response) {
            var customerTableBody = $("#search-modal-customer-information");
            customerTableBody.empty();

            $.each(response.customerSearchInfoList, function(index, customer){
                var row = $('<tr class="customer-element">')
                    .append($('<td style="width: 10%">').append($('<input class="form-check-input row-radio" type="radio" name="selected-customer">')))
                    .append($('<td style="width: 10%">').text(customer.customerId))
                    .append($('<td style="width: 10%">').text(customer.customerName))
                    .append($('<td style="width: 20%">').text(customer.phoneNumber))
                    .append($('<td style="width: 15%">').text(customer.securityLevel))
                    .append($('<td style="width: 15%">').text(customer.branchId));

                customerTableBody.append(row);
            })

            $('.customer-element').on('click', function() {
                // 해당 tr 안의 라디오 버튼을 체크
                $(this).find('.row-radio').prop('checked', true);
            });

            // 라디오 버튼이 클릭되었을 때도 체크되도록 설정
            $('.row-radio').on('click', function(e) {
                e.stopPropagation();  // 이벤트 전파 중단 (tr 클릭이 중복 처리되지 않도록)
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {

            console.error('검색 실패:', errorThrown);
            alert('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
        }
    });

}

// ------------------------------- END customerSearchModalEvent()------------------------------------------

// ------------------------------- START insertCustomerId()------------------------------------------
/*
    insertCustomerId() 함수 설명
    - 모달 값을 넣어줄 id명 맞춰주기
    - (5가지 요소 다 가능 / 고객아이디, 이름, 생일 등등)

    예) 경우 : 고객 검색 후 고객 id를 input값에 할당해야할 경우

        input의 id -> customer-name-input로 설정한다.
        ex ) <input type="text" id="customer-name-input" disabled>
*/

// 모달내 선택 버튼 클릭시 input 입력 함수
function insertCustomerId() {

    $('#search-modal-select-btn').on('click', function () {

        const selectedCustomer = $('input[name="selected-customer"]:checked');

        if (selectedCustomer.length > 0) {
            const insertValueMappings = [
                { selector: '#customer-id-input', columnIndex: 2 },
                { selector: '#customer-name-input', columnIndex: 3 },
                { selector: '#customer-phone-number-input', columnIndex: 4 },
                { selector: '#customer-security-level-input', columnIndex: 5},
            ];

            const selectedRow = selectedCustomer.closest('tr');

            insertValueMappings.forEach(item => {
                const value = selectedRow.find(`td:nth-child(${item.columnIndex})`).text();
                const inputElement = $(item.selector);
                
                // 존재하는 요소에 필요한 값 넣어주기
                if (inputElement.length > 0 && value && value.trim() !== "") {
                    inputElement.val(value);
                }

            });

            $('#search-customer-modal').modal('hide');
            $('#account-open-info').fadeIn();


        } else {
            swal({
                title: "고객을 선택해 주세요.",
                // text: "비밀번호 인증 성공",
                icon: "warning",
            });
        }
    });

}

function clickSendBtn(){
    $('#authentication-number-send-btn').click(function() {
        swal({
            title: "SMS 전송 성공",
            text: "인증 번호가 성공적으로 전송되었습니다.",
            icon: "success",
            button: "닫기",
        })
        registerAuthenticationTimer();
    });
}

function clickResendBtn(){
    $('#authentication-number-resend-btn').click(function() {
        swal({
            title: "SMS 재전송 성공",
            text: "인증 번호가 성공적으로 재전송되었습니다.",
            icon: "success",
            button: "닫기",
        })
        clearInterval(timerInterval);
        registerAuthenticationTimer();
    });

}

function registerAuthenticationTimer() {

    $('#phone-authentication-number').prop('disabled', false);
    $('#phone-authentication-number').attr('placeholder', '인증번호 6자리를 입력해주세요');
    // 타이머 표시
    $('#timer').show();
    $('#authentication-number-send-btn').addClass('hidden');
    $('#authentication-number-resend-btn').removeClass('hidden');
    $('#authentication-number-check-btn').removeClass('hidden');


    // 3분 타이머 설정
    let timeLeft = 180; // 3분을 초로 표현
    timerInterval = setInterval(function() {
        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;

        // 시간 표시 형식
        $('#timer').text(`제한 시간 : ${minutes}:${seconds < 10 ? '0' + seconds : seconds}`);

        // 시간 감소
        timeLeft--;

        // 시간이 다 되었을 때
        if (timeLeft < 0) {
            clearInterval(timerInterval);
            $('#timer').hide(); // 타이머 숨기기
            $('#authentication-number-resend-btn').addClass('hidden'); // 재전송 버튼 숨기기
            $('#phone-authentication-number').prop('disabled', true); // 입력 필드 비활성화
        }
    }, 1000); // 1초마다 업데이트
}

function clickAuthenticateBtn() {
    $('#authentication-number-check-btn').click(function() {
        swal({
            title: "SMS 인증 성공",
            text: "SMS 인증이 성공적으로 수행되었습니다.",
            icon: "success",
            button: "닫기",
        })
        $('#otp-register-btn').prop('disabled', false);

    });
}

function clickOtpRegisterBtn()
{

    $('#otp-register-btn').click(function() {
        swal({
            title: "OTP 발급/재발급 완료",
            text: "OTP 발급이 성공적으로 수행되었습니다.",
            icon: "success",
            button: "닫기",
        })
        var otpDetailModal = new bootstrap.Modal(document.getElementById('otp-detail-modal'));
        otpDetailModal.show();

    });

}
