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
    let vaultCashValue = $('#manager-close-vault-cash').val();

    // 쉼표 제거
    vaultCashValue = vaultCashValue.replace(/,/g, '');
    // 숫자 변환
    const vaultCash = Number(vaultCashValue);

    console.log("vaultCash",vaultCash);

    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({ vaultCash: vaultCash }),
        success: function(response) {
            console.log('성공');
            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#manager-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
            $("#manager-business-day-close-btn").prop("disabled", true);
            $("#manager-business-day-close-btn").text("마감 완료");
            $('#business-day-status > span').text("CLOSED");

            // branch_closing 테이블에서 마감정보 가져와서 다시 채우기
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

/**
 *
 */
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
            .addClass('basic-btn')
            .text("지점 마감");
    } else {
        $('#manager-business-day-close-btn')
            .addClass('closed-btn')
            .prop("disabled", true)
            .text("마감 대기");
    }
}

function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/common/current-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);
            if(response.status === "CLOSED")
            {
                $("#manager-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#manager-business-day-close-btn").prop("disabled", true);
                $("#manager-business-day-close-btn").text("마감 완료");
            }

        },
        error: function(xhr, status, error) {
        }
    });
}
