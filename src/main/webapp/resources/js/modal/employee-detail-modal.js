$(document).ready(function() {

    registerClickEventOfUpdateBtn(); // 수정 버튼 클릭 이벤트 리스너
    //registerClickEventOfDeleteBtn(); // 삭제 버튼 클릭 이벤트 리스너

});

/**
 * 이벤트 등록 함수 목록
 */
function registerClickEventOfUpdateBtn() {


    $('#detail-modal-emp-update-btn').click(function(event) {
        console.log("redirect update Page");
        let employeeId = $('#detail-modal-emp-id').val();

        redirectEmployeeUpdatePage(employeeId);
    });
}

/**
 * 기능 함수 목록
 */

function redirectEmployeeUpdatePage(employeeId){

    window.location.href = `/page/manager/employee-update?id=${employeeId}`;
}