var isMasked = true; // 기본 마스킹 상태
var originalValue = ""; // 주민번호 원본 값 저장
var originalPhoneNumber = ""; // 주민번호 원본 값 저장

$(document).ready(function() {

    handleCustomerDataOfUpdateForm();
    handleAuthDataOfCustomerUpdateForm();

    registerClickEventOfCustomerSearchBtn(); // 페이지 내 고객 선택 버튼
    registerClickEventOfCustomerSelectBtn(); // 모달 내 고객 선택 버튼
    registerClickEventOfCustomerUpdateCompleteBtn(); // 수정사항 저장 버튼
    clickViewResidentNumber();


    handleResidentNumber();
    handlePhoneNumber();

});




/**
 * @Description
 * 이벤트 등록 함수
 */
function registerClickEventOfCustomerSearchBtn(){
    $('#customer-search-btn').click(function()
    {
        var customerSearchModal = new bootstrap.Modal(document.getElementById('search-customer-modal'));
        customerSearchModal.show();
    });
}

function registerClickEventOfCustomerSelectBtn() {
    $('#search-modal-select-btn').on('click', function () {

        const selectedCustomer = $('input[name="selected-customer"]:checked');

        if (selectedCustomer.length > 0) {

            const selectedRow = selectedCustomer.closest('tr');
            const customerId = selectedRow.find('td:nth-child(2)').text();

            window.location.href = `/page/common/customer-update?id=${customerId}`;
        }
    });
}

function registerClickEventOfCustomerUpdateCompleteBtn(){
    $('#customer-update-btn').click(function() {

        console.log("수정 완료 버튼클릭");
        // 폼 데이터 수집
        var customerUpdateData = {
            id: $('#customer-id-text').val(),
            name : $('#customer-name').val(),
            birthDate : $('#customer-birth-date').val(),
            identificationCode : $('#customer-resident-number').val(),
            address : $('#customer-address').val(),
            detailAddress : $('#customer-detail-address').val(),
            email : $('#customer-email').val(),
            phoneNumber : $('#customer-phone-number').val(),
            gender: $('#customer-gender').val(),
        };

        updateCustomer(customerUpdateData);

    });
}



/**
 * @Description
 * 기능 함수
 */

function handleCustomerDataOfUpdateForm(){

    // 특정 파라미터 값 가져오기 (예: "id"라는 파라미터가 있을 때)
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');

    // 파라미터 값이 있을 경우에만 수정 폼을 채워준다.
    if(id)
    {
        $.ajax({
            type: 'GET',
            url: '/api/common/customer/' + id,  // 서버의 URL로 변경
            success: function(customerDetail) {

                fillCustomerDataOfUpdateForm(id, customerDetail);

            },
            error: function(error) {
                alert('데이터 전송 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    }
}
function fillCustomerDataOfUpdateForm(id, customerDetail){

    $('#customer-id-text').val(id);
    $('#customer-name').val(customerDetail.name);
    $('#customer-birth-date').val(customerDetail.birthDate.split(' ')[0]);
    $('#customer-resident-number').val(customerDetail.identificationCode);
    originalValue = customerDetail.identificationCode.replace(/-/g, '');

    $('#customer-address').val(customerDetail.address);
    $('#customer-detail-address').val(customerDetail.detailAddress);
    $('#customer-email').val(customerDetail.email);
    $('#customer-phone-number').val(customerDetail.phoneNumber);
    originalPhoneNumber = customerDetail.phoneNumber.replace(/-/g, '');

    $('#customer-gender').find('option[value="' + customerDetail.gender + '"]').prop('selected', true);
}

function updateCustomer(customerUpdateData){
    // Ajax 요청
    $.ajax({
        type: 'PUT',
        url: '/api/common/customer',  // 서버의 URL로 변경
        contentType: 'application/json',
        data: JSON.stringify({
            ...customerUpdateData
        }),
        success: function(updatedCustomer) {

            swal({
                title: "고객 수정 성공",
                text: "고객 정보가 성공적으로 수정되었습니다.",
                icon: "success",
                button: "닫기",
            });

            fillCustomerDataOfDetailModal(updatedCustomer);


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

function fillCustomerDataOfDetailModal(updatedCustomer){

    $('#detail-modal-customer-id').val(updatedCustomer.id);
    $('#detail-modal-customer-name').val(updatedCustomer.name);
    $('#detail-modal-customer-security-level').val(updatedCustomer.securityLevel);
    $('#detail-modal-customer-identification-code').val(updatedCustomer.identificationCode);
    $('#detail-modal-customer-birth-date').val(new Date(updatedCustomer.birthDate).toISOString().split('T')[0]);
    $('#detail-modal-customer-email').val(updatedCustomer.email);
    $('#detail-modal-customer-gender').val($('#customer-gender option:selected').text());
    $('#detail-modal-customer-phone-number').val(updatedCustomer.phoneNumber);

    $('#detail-modal-customer-branch-name').val($('#customer-branch-id').val());
    $('#detail-modal-customer-registrant-name').val($('#emp-name-input').val());
    console.log("1 : " + $('#start-date-input').val());
    console.log("2 : " + $('#start-date-input').val().split('T')[0]);

    $('#detail-modal-customer-start-date').val($('#start-date-input').val().split('T')[0]);

}
function handleAuthDataOfCustomerUpdateForm(){
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function(authData) {
            // 성공 시 처리할 로직 작성
            $('#emp-branch-id')
                .val(authData.branchName)
                .data('branchId', authData.branchId);  // branchId를 저장
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
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
    $('#detail-modal-customer-identification-code').val(displayValue); // 화면에 마스킹된 값만 보여주기

}

function clickViewResidentNumber() {
    $('.toggle-visibility').on('click', function () {
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
    $('#detail-modal-customer-phone-number').val(displayPhoneNumber); // 화면에 마스킹된 값만 보여주기
}