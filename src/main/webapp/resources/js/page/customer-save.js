var isMasked = true; // 기본 마스킹 상태
var originalValue = ""; // 주민번호 원본 값 저장
var originalPhoneNumber = ""; // 주민번호 원본 값 저장

$(document).ready(function() {

    registerClickEventOfCustomerSaveBtn();
    handleResidentNumber();
    clickViewResidentNumber();
    handlePhoneNumber();
});

/**
 * 이벤트 등록 함수 목록
 */

function registerClickEventOfCustomerSaveBtn(){
    $('#customer-save-btn').click(function() {
        // 폼 데이터 수집
        var customerCreateData = {
            name: $('#customer-name').val(),
            birthDate: $('#customer-birth-date').val(),
            address: $('#customer-address').val(),
            detailAddress: $('#customer-detail-address').val(),
            email: $('#customer-email').val(),
            gender: $('#customer-gender').val(),
            phoneNumber: originalPhoneNumber,
            identificationCode: originalValue

        };

        createCustomer(customerCreateData);

    });
}


/**
 * 기능 함수 목록
 */


function createCustomer(customerCreateData){
    // Ajax 요청
    $.ajax({
        type: 'POST',
        url: '/api/employee/customer',  // 서버의 URL로 변경
        contentType: 'application/json',
        data: JSON.stringify({
            ...customerCreateData
        }),
        success: function(createdCustomer) {
            swal({
                title: "고객 추가 성공",
                text: "고객 정보가 성공적으로 추가되었습니다.",
                icon: "success",
                button: "닫기",
            });

            resetCustomerDataOfCreateForm();
            fillCustomerDataOfDetailModal(createdCustomer);

            var customerDetailModal = new bootstrap.Modal(document.getElementById('customer-detail-modal'));
            customerDetailModal.show();

            // 성공 시 추가 작업 (예: 페이지 리로드, 메시지 표시 등)
        },
        error: function(error) {
            alert('데이터 전송 중 오류가 발생했습니다.');
            console.error(error);
        }
    });
}



function resetCustomerDataOfCreateForm(){
    $('#customer-name').val('');
    $('#customer-birth-date').val('');
    $('#customer-email').val('');
    $('#customer-phone-number').val('');

}

function fillCustomerDataOfDetailModal(createdCustomer){
    $('#detail-modal-customer-id').val(createdCustomer.id);
    $('#detail-modal-customer-name').val(createdCustomer.name);
    $('#detail-modal-customer-birth-date').val(new Date(createdCustomer.birthDate).toISOString().split('T')[0]);
    $('#detail-modal-customer-email').val(createdCustomer.email);
    $('#detail-modal-customer-phone-number').val(createdCustomer.phoneNumber);
    $('#detail-modal-customer-gender').val(createdCustomer.gender);
    $('#detail-modal-customer-branch-name').val(createdCustomer.branchName); // 지점명은 id로 받음, 필요 시 변환
    $('#detail-modal-customer-security-level').val(createdCustomer.securityLevel); // 지점명은 id로 받음, 필요 시 변환
    $('#detail-modal-customer-registrant-name').val(createdCustomer.registrantName); // 지점명은 id로 받음, 필요 시 변환
    $('#detail-modal-customer-start-date').val(createdCustomer.currentBusinessDate); // 지점명은 id로 받음, 필요 시 변환
}
function handleResidentNumber() {
    $('#customer-resident-number').on('input', function(event) {
        // 현재 입력된 전체 값
        let currentValue = $(this).val();

        // 백스페이스 처리
        if (event.originalEvent.inputType === 'deleteContentBackward') {
            originalValue = originalValue.slice(0, -1); // 마지막 문자 제거
            $(this).val(originalValue); // 업데이트된 값을 입력 필드에 반영
            maskResidentNumber(); // 마스킹 처리 호출
        } else {
            // 현재 입력된 마지막 문자
            let inputChar = currentValue.slice(-1);

            // 숫자일 경우에만 추가
            if (/^[0-9]$/.test(inputChar)) { // 마지막 문자가 숫자인지 확인
                originalValue += inputChar; // 숫자만 남기고 추가
            }
            maskResidentNumber(); // 마스킹 처리 호출
        }
    });
}

// 주민번호 뒷자리 마스킹 함수
function maskResidentNumber() {
    let displayValue = originalValue; // 화면에 표시할 값 초기화
    if (originalValue.length > 6) {
        displayValue = originalValue.slice(0, 6) + '-' + originalValue.slice(6); // 하이픈 추가
    }
    if (isMasked && displayValue.length > 8) {
        let visiblePart = displayValue.slice(0, 8); // 앞 8자리 표시
        let maskedPart = "*".repeat(displayValue.length - 8); // 뒷자리 숫자 수만큼 * 적용
        displayValue = visiblePart + maskedPart; // 화면에 표시할 값 업데이트
    }
    $('#customer-resident-number').val(displayValue); // 화면에 마스킹된 값만 보여주기
}

function clickViewResidentNumber() {
    $('#toggle-visibility').on('click', function () {
        isMasked = !isMasked; // 마스킹 상태 토글
        maskResidentNumber(); // 현재 상태에 따라 업데이트
        // 아이콘 클래스 토글
        $(this).find('i').toggleClass('bi-eye bi-eye-slash'); // bi-eye와 bi-eye-slash 클래스 토글
    });
}

function handlePhoneNumber() {
    $('#customer-phone-number').on('input', function(event) {
        // 현재 입력된 전체 값
        let currentValue = $(this).val();

        // 백스페이스 처리
        if (event.originalEvent.inputType === 'deleteContentBackward') {
            originalPhoneNumber = originalPhoneNumber.slice(0, -1); // 마지막 문자 제거
            $(this).val(originalPhoneNumber); // 업데이트된 값을 입력 필드에 반영
            hyphenPhoneNumber(); // 마스킹 처리 호출
        } else {
            // 현재 입력된 마지막 문자
            let inputChar = currentValue.slice(-1);

            // 숫자일 경우에만 추가
            if (/^[0-9]$/.test(inputChar)) { // 마지막 문자가 숫자인지 확인
                originalPhoneNumber += inputChar; // 숫자만 남기고 추가
            }
            hyphenPhoneNumber(); // 마스킹 처리 호출
        }
    });
}

// 주민번호 뒷자리 마스킹 함수
function hyphenPhoneNumber() {
    let displayPhoneNumber = originalPhoneNumber; // 화면에 표시할 값 초기화
    if (originalPhoneNumber.length > 3 && originalPhoneNumber.length <= 7) {
        displayPhoneNumber = originalPhoneNumber.slice(0, 3) + '-' + originalPhoneNumber.slice(3); // 하이픈 추가
    }
    if (originalPhoneNumber.length > 7) {
        displayPhoneNumber = originalPhoneNumber.slice(0, 3) + '-' + originalPhoneNumber.slice(3, 7) + '-' + originalPhoneNumber.slice(7); // 하이픈 추가
    }
    $('#customer-phone-number').val(displayPhoneNumber); // 화면에 마스킹된 값만 보여주기
}
