var isMasked = true; // 기본 마스킹 상태
var originalValue = ""; // 주민번호 원본 값 저장
var originalPhoneNumber = ""; // 주민번호 원본 값 저장

$(document).ready(function() {

    registerClickEventOfEmpSaveBtn();
    handleAuthDataOfEmpSaveForm();

    handleResidentNumber();
    clickViewResidentNumber();
    chkPW();
});

/**
 * 이벤트 등록 함수 목록
 */

function registerClickEventOfEmpSaveBtn(){
    $('#emp-save-btn').click(function() {
        // 폼 데이터 수집
        var employeeCreateData = {
            name: $('#emp-name').val(),
            birthDate: $('#emp-birth-date').val(),
            email: $('#emp-email').val(),
            password: $('#emp-password').val(),
            branchId: $('#emp-branch-id').data('branchId'),
            phoneNumber: $('#emp-phone-number').val(),
            roles: $('#emp-roles').val()
        };

        createEmployee(employeeCreateData);
    });
}


/**
 * 기능 함수 목록
 */


function createEmployee(employeeCreateData){
    // Ajax 요청
    $.ajax({
        type: 'POST',
        url: '/api/manager/employee',  // 서버의 URL로 변경
        contentType: 'application/json',
        data: JSON.stringify({
            ...employeeCreateData
        }),
        success: function(createdEmployee) {
            swal({
                title: "직원 추가 성공",
                text: "직원 정보가 성공적으로 추가되었습니다.",
                icon: "success",
                button: "닫기",
            });

            resetEmpDataOfCreateForm();
            fillEmpDataOfDetailModal(createdEmployee);

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
    $('#emp-phone-number').val('');
    $('#emp-branch-id').prop('selectedIndex', 0);  // '지점 선택'으로 설정
    $('#emp-roles').prop('selectedIndex', 0);     // '직급 선택'으로 설정


}

function fillEmpDataOfDetailModal(createdEmployee){
    $('#detail-modal-emp-id').val(createdEmployee.id);
    $('#detail-modal-emp-password').val(createdEmployee.password);
    $('#detail-modal-emp-name').val(createdEmployee.name);
    $('#detail-modal-emp-birth-date').val(new Date(createdEmployee.birthDate).toISOString().split('T')[0]);
    $('#detail-modal-emp-email').val(createdEmployee.email);
    $('#detail-modal-emp-phone-number').val(createdEmployee.phoneNumber);
    $('#detail-modal-emp-branch-name').val(createdEmployee.branchName); // 지점명은 id로 받음, 필요 시 변환
    $('#detail-modal-emp-roles').val(createdEmployee.roles);

}

function handleAuthDataOfEmpSaveForm(){
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

