var isMasked = true; // 기본 마스킹 상태
var originalValue = ""; // 주민번호 원본 값 저장
var originalPhoneNumber = ""; // 주민번호 원본 값 저장
var id;
$(document).ready(function() {

    handleEmpDataOfUpdateForm();
    handleAuthDataOfEmpUpdateForm();

    registerClickEventOfEmpSearchBtn();
    registerClickEventOfEmpUpdateCompleteBtn();

    handleResidentNumber();
    clickViewResidentNumber();
    handlePhoneNumber();
    chkPW();
    $('#search-modal-select-btn').on('click', function () {

        const selectedEmployee = $('input[name="selected-employee"]:checked');

        if (selectedEmployee.length > 0) {
            $('#employee-search-modal').modal('hide');
            const selectedRow = selectedEmployee.closest('tr');
            const customerId = selectedRow.find('td:nth-child(2)').text();

            window.location.href = `/page/manager/employee-update?id=${customerId}`;


        } else {
            swal({
                title: "행원을 선택해 주세요.",
                // text: "비밀번호 인증 성공",
                icon: "warning",
            });
        }
    });

    $('#employee-detail-modal').on('hidden.bs.modal', function () {
        window.location.href = '/page/manager/employee-update';
    })
});




/**
 * @Description
 * 이벤트 등록 함수
 */

function registerClickEventOfEmpSearchBtn(){
    $('#employee-search-btn').click(function()
    {

        var employeeSearchModal = new bootstrap.Modal(document.getElementById('employee-search-modal'));
        employeeSearchModal.show();
    });
}


function registerClickEventOfEmpUpdateCompleteBtn(){
    $('#emp-update-btn').click(function() {

        console.log("수정 완료 버튼클릭");
        // 폼 데이터 수집
        var employeeUpdateData = {
            id: id,
            name: $('#emp-name').val(),
            birthDate: $('#emp-birth-date').val(),
            password: $('#emp-password').val(),
            identificationCode: originalValue,
            address: $('#emp-address').val(),
            detailAddress: $('#emp-detail-address').val(),
            email: $('#emp-email').val(),
            phoneNumber: originalPhoneNumber,
            roles: $('#emp-roles').val()
        }


        updateEmployee(employeeUpdateData);

    });
}



/**
 * @Description
 * 기능 함수
 */

function handleEmpDataOfUpdateForm(){

    // 특정 파라미터 값 가져오기 (예: "id"라는 파라미터가 있을 때)
    const params = new URLSearchParams(window.location.search);
    id = params.get('id');

    // 파라미터 값이 있을 경우에만 수정 폼을 채워준다.
    if(id)
    {
        $.ajax({
            type: 'GET',
            url: '/api/manager/employee/' + id,  // 서버의 URL로 변경
            success: function(employeeDetail) {

                fillEmpDataOfUpdateForm(id, employeeDetail);

            },
            error: function(error) {
                alert('데이터 전송 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    }
}
function fillEmpDataOfUpdateForm(id, employeeDetail){
    console.log(employeeDetail);
    $('#emp-id').val(employeeDetail.id);
    $('#emp-name').val(employeeDetail.name);
    $('#emp-birth-date').val(new Date(employeeDetail.birthDate).toISOString().split('T')[0]);
    $('#emp-email').val(employeeDetail.email);
    $('#emp-phone-number').val(employeeDetail.phoneNumber);
    $('#emp-branch-name').val(employeeDetail.branchName);
    $('#emp-resident-number').val(employeeDetail.identificationCode);
    $('#emp-address').val(employeeDetail.address);
    $('#emp-detail-address').val(employeeDetail.detailAddress);
    $('#emp-roles').val(employeeDetail.roles);
    $('#emp-name-input').val(employeeDetail.registrantName);
    $('#start-date-input').val(employeeDetail.registrationDate);

    originalValue = convertNumber(employeeDetail.identificationCode);
    originalPhoneNumber = convertNumber(employeeDetail.phoneNumber);
    maskResidentNumber();
    hyphenPhoneNumber();
}

function updateEmployee(employeeUpdateData){
    // Ajax 요청
    $.ajax({
        type: 'PUT',
        url: '/api/manager/employee',  // 서버의 URL로 변경
        contentType: 'application/json',
        data: JSON.stringify({
            ...employeeUpdateData
        }),
        success: function(updatedEmployeeId) {

            swal({
                title: "직원 수정 성공",
                text: "직원 정보가 성공적으로 수정되었습니다.",
                icon: "success",
                button: "닫기",
            });

            resetEmpDataOfCreateForm();
            fillEmpDataOfDetailModal(updatedEmployeeId);


            var employeeDetailModal = new bootstrap.Modal(document.getElementById('employee-detail-modal'));
            employeeDetailModal.show();

            // 성공 시 추가 작업 (예: 페이지 리로드, 메시지 표시 등)
        },
        error: function(error) {
            alert('데이터 전송 중 오류가 발생했습니다.');
            console.error(error);
        }
    });
}

function resetEmpDataOfCreateForm(){

        $('#emp-name').val('');
        $('#emp-birth-date').val('');
        $('#emp-email').val('');
        $('#emp-password').val('');

        $('#emp-address').val('');
        $('#emp-detail-address').val('');
        $('#emp-resident-number').val('');
        $('#emp-phone-number').val('');

        $('#emp-branch-id').prop('selectedIndex', 0);  // '지점 선택'으로 설정
        $('#emp-roles').prop('selectedIndex', 0);     // '직급 선택'으로 설정

        originalValue = ""; // 주민번호 원본 값 저장
        originalPhoneNumber = ""; // 주민번호 원본 값 저장
}


function fillEmpDataOfDetailModal(updatedEmployeeId){
    $.ajax({
        type: 'GET',
        url: '/api/manager/employee/' + updatedEmployeeId ,  // 서버의 URL로 변경
        success: function(updatedEmployee) {
            swal({
                title: "직원 수정 성공",
                text: "직원 정보가 성공적으로 수정되었습니다.",
                icon: "success",
                button: "닫기",
            });

            $('#detail-modal-emp-id').val(updatedEmployee.id);
            $('#detail-modal-emp-password').val(updatedEmployee.password);
            $('#detail-modal-emp-name').val(updatedEmployee.name);
            $('#detail-modal-emp-birth-date').val(new Date(updatedEmployee.birthDate).toISOString().split('T')[0]);
            $('#detail-modal-emp-email').val(updatedEmployee.email);
            $('#detail-modal-emp-phone-number').val(updatedEmployee.phoneNumber);
            $('#detail-modal-emp-branch-name').val(updatedEmployee.branchName);
            $('#detail-modal-customer-identification-code').val(updatedEmployee.identificationCode);
            $('#detail-modal-emp-address').val(updatedEmployee.address);
            $('#detail-modal-emp-detail-address').val(updatedEmployee.detailAddress);

            var role = updatedEmployee.roles === 'ROLE_MANAGER' ? '매니저' : '행원';
            $('#detail-modal-emp-roles').val(role);
            $('#detail-modal-emp-registrant-name').val(updatedEmployee.registrantName);
            $('#detail-modal-emp-registration-date').val(updatedEmployee.currentBusinessDate);

            originalValue = convertNumber(updatedEmployee.identificationCode);
            originalPhoneNumber = convertNumber(updatedEmployee.phoneNumber);
            maskResidentNumber();
            hyphenPhoneNumber();

            var employeeDetailModal = new bootstrap.Modal(document.getElementById('employee-detail-modal'));
            employeeDetailModal.show();

            // 성공 시 추가 작업 (예: 페이지 리로드, 메시지 표시 등)
        },
        error: function(error) {
            alert('데이터 전송 중 오류가 발생했습니다.');
            console.error(error);
        }
    });
}
function handleAuthDataOfEmpUpdateForm(){
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
    $('#emp-resident-number').on('input', function(event) {
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
    $('#emp-resident-number').val(displayValue); // 화면에 마스킹된 값만 보여주기
    $('#detail-modal-emp-identification-code').val(displayValue); // 화면에 마스킹된 값만 보여주기

}
function clickViewResidentNumber() {
    $('.toggle-visibility').on('click', function () {
        isMasked = !isMasked; // 마스킹 상태 토글
        maskResidentNumber(); // 현재 상태에 따라 업데이트
        // 아이콘 클래스 토글
        $(this).find('i').toggleClass('bi-eye bi-eye-slash'); // bi-eye와 bi-eye-slash 클래스 토글
    });
}

function chkPW(){

    $("#emp-password").on('input', function(event) {
        var pw = $("#emp-password").val();
        var num = pw.search(/[0-9]/g);
        var eng = pw.search(/[a-z]/ig);
        var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if(pw.length < 8 || pw.length > 20){
            $('#password-error-message').text("8자리 ~ 20자리 이내로 입력해주세요.");
            return false;
        }else if(pw.search(/\s/) != -1){
            $('#password-error-message').text("비밀번호는 공백 없이 입력해주세요.");
            return false;
        }else if(num < 0 || eng < 0 || spe < 0 ){
            $('#password-error-message').text("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
            return false;
        }else {
            $('#password-error-message').text("");  // 경고 메시지 제거
            return true;
        }
    });

}

function handlePhoneNumber() {
    $('#emp-phone-number').on('input', function(event) {
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

// 휴대전화 하이푼 처리
function hyphenPhoneNumber() {
    let displayPhoneNumber = originalPhoneNumber; // 화면에 표시할 값 초기화
    if (originalPhoneNumber.length > 3 && originalPhoneNumber.length <= 7) {
        displayPhoneNumber = originalPhoneNumber.slice(0, 3) + '-' + originalPhoneNumber.slice(3); // 하이픈 추가
    }
    if (originalPhoneNumber.length > 7) {
        displayPhoneNumber = originalPhoneNumber.slice(0, 3) + '-' + originalPhoneNumber.slice(3, 7) + '-' + originalPhoneNumber.slice(7); // 하이픈 추가
    }
    $('#emp-phone-number').val(displayPhoneNumber); // 화면에 마스킹된 값만 보여주기
    $('#detail-modal-emp-phone-number').val(displayPhoneNumber); // 화면에 마스킹된 값만 보여주기
}
function convertNumber(str) {
    str = String(str.replace(/[^\d]+/g, ''));  // 숫자를 제외한 모든 문자 제거


    if (/^0{2,}/.test(str)) {
        // 두 번 이상 연속된 0을 잘라내고 나머지 부분 반환
        return str.replace(/^0{2,}/, '0');
    }
    return str;
}

