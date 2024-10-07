$(document).ready(function() {

    $("#trade-list-detail-btn").click(function() {
        // tradeDetails와 tradeDetailsContent의 표시 여부를 토글
        $("#tradeDetails, #tradeDetailsContent").toggle();
    });
    handleAuthDataOfEmployeeClosePage();
    registerClickEventOfEmpCloseBtn();
});

function registerClickEventOfEmpCloseBtn(){
    $("#employee-business-day-close-btn").click(function() {
        closeBusinessDayOfEmployee();
    });
}


function closeBusinessDayOfEmployee(){
    $.ajax({
        url: '/api/employee/business-day-close',
        type: 'PATCH',
        success: function(response) {

            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#employee-business-day-close-btn").removeClass("update-btn").addClass("closed-btn");
            $("#employee-business-day-close-btn").prop("disabled", true);
            $("#employee-business-day-close-btn").text("마감 완료");

        },
        error: function(xhr, status, error) {
            swal({
                title: "마감 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            })
        }
    });
}

function handleAuthDataOfEmployeeClosePage(){
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function(authData) {
            // 성공 시 처리할 로직 작성
            console.log(authData);
            $('#employee-close-page-user-branch-name').text(authData.branchName);
            $('#employee-close-page-user-name').text(authData.name + '님');
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}