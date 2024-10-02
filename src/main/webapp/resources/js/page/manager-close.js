$(document).ready(function() {

    registerClickEventOfEmpCloseBtn();
    handleEmpCloseBtnStatus();
    handleCurrentBusinessDay();
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

function handleEmpCloseBtnStatus(){

    const statuses = [];
    $('.branch-close-employee-status').each(function() {
        const statusText = $(this).text().trim();
        if (statusText === 'CLOSED') {
            statuses.push(statusText);
        }
    });

    // 모든 status 값이 CLOSED인 경우
    const totalStatuses = $('.branch-close-employee-data').length;
    const totalClosedStatuses = statuses.length;


    if (totalStatuses === totalClosedStatuses) {
        $('#manager-business-day-close-btn')
            .addClass('update-btn');
    } else {
        $('#manager-business-day-close-btn')
            .addClass('closed-btn')
            .prop("disabled", true)
            .text("마감 대기");
    }
}

function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/current-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);
            if(response.status === "CLOSED")
            {

                $("#manager-business-day-close-btn").removeClass("update-btn").addClass("closed-btn");
                $("#manager-business-day-close-btn").prop("disabled", true);
                $("#manager-business-day-close-btn").text("마감 완료");


            }

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}