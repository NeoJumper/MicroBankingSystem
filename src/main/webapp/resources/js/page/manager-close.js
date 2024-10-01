$(document).ready(function() {

    registerClickEventOfEmpCloseBtn();
});

function registerClickEventOfEmpCloseBtn(){
    $("#manager-business-day-close-btn").click(function() {
        closeBusinessDayOfManager();
    });
}


function closeBusinessDayOfManager(){
    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'PATCH',
        success: function(response) {
            console.log('성공');
            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#manager-business-day-close-btn").removeClass("update-btn").addClass("closed-btn");
            $("#manager-business-day-close-btn").prop("disabled", true);
            $("#manager-business-day-close-btn").text("마감 완료");
            $('#business-day-status > span').text("CLOSED");
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}