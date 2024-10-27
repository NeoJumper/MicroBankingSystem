$(document).ready(function() {

    handleEmpDataOfUpdateForm();
    handleAuthDataOfEmpUpdateForm();

    registerClickEventOfEmpSearchBtn();
    registerClickEventOfEmpUpdateCompleteBtn();

        $('#search-modal-select-btn').on('click', function () {

        const selectedEmployee = $('input[name="selected-employee"]:checked');

        if (selectedEmployee.length > 0) {

            const selectedRow = selectedEmployee.closest('tr');
            const customerId = selectedRow.find('td:nth-child(2)').text();

            window.location.href = `/page/manager/employee-update?id=${customerId}`;


        } else {
            alert('고객을 선택해 주세요.');
        }
    });
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
            id: $('#customer-id-text').val(),
            name : $('#emp-name').val(),
            birthDate : $('#emp-birth-date').val(),
            email : $('#emp-email').val(),
            password : $('#emp-password').val(),
            branchId: $('#emp-branch-id').data('branchId'),
            phoneNumber : $('#emp-phone-number').val(),
            roles : $('#emp-roles').val()
        };

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
    const id = params.get('id');

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

    $('#customer-id-text').val(id);
    $('#emp-name').val(employeeDetail.name);
    $('#emp-birth-date').val(employeeDetail.birthDate.split(' ')[0]);
    $('#emp-email').val(employeeDetail.email);
    $('#emp-password').val(employeeDetail.password);
    $('#emp-phone-number').val(employeeDetail.phoneNumber);
    // select 요소들을 기본 선택값으로 설정
    $('#emp-branch-id').prop('selectedIndex', 0);  // '지점 선택'으로 설정
    $('#emp-roles').prop('selectedIndex', 0);     //

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
        success: function(updatedEmployee) {

            swal({
                title: "직원 수정 성공",
                text: "직원 정보가 성공적으로 수정되었습니다.",
                icon: "success",
                button: "닫기",
            });

            resetEmpDataOfCreateForm();
            fillEmpDataOfDetailModal(updatedEmployee);


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


function fillEmpDataOfDetailModal(updatedEmployee){
    $('#detail-modal-emp-id').val(updatedEmployee.id);
    $('#detail-modal-emp-password').val(updatedEmployee.password);
    $('#detail-modal-emp-name').val(updatedEmployee.name);
    $('#detail-modal-emp-birth-date').val(new Date(updatedEmployee.birthDate).toISOString().split('T')[0]);
    $('#detail-modal-emp-email').val(updatedEmployee.email);
    $('#detail-modal-emp-phone-number').val(updatedEmployee.phoneNumber);
    $('#detail-modal-emp-branch-name').val(updatedEmployee.branchName); // 지점명은 id로 받음, 필요 시 변환
    $('#detail-modal-emp-roles').val(updatedEmployee.roles);

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