$(document).ready(function() {

    registerClickEventOfEmpSaveBtn();

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
            branchId: $('#emp-branch-id').val(),
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