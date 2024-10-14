let nextBusinessDay = '';
let currentBusinessDay = '';

$(document).ready(function() {
    handleCurrentBusinessDay();
    handleNextBusinessDay();
    registerClickEventOfBusinessDayResetBtnOfModal();
});

function registerClickEventOfBusinessDayResetBtnOfModal() {


    $('#business-day-reset-modal-reset-btn').on('click', function () {
        $('#business-day-reset-modal').modal('hide');
        resetBusinessDay();
    });

}

/**
 * @Description
 * 현재 영업일 란 채우기
 * 현재 영업일이 마감됐을 때만 영업일 변경, 영업일 시작 버튼 활성화
 */
function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/common/current-business-day',
        type: 'GET',
        success: function(response) {

            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);

            if(response.status === "OPEN")
            {

                $("#business-day-update-modal-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#business-day-update-modal-btn").prop("disabled", true);

                $("#business-day-update-modal-update-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#business-day-update-modal-update-btn").prop("disabled", true);

            }


        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}
// 다음 영업일 란 채우기
function handleNextBusinessDay(){
    $.ajax({
        url: '/api/common/next-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            nextBusinessDay = response.businessDate.substring(0, 10);
            $('#next-business-day').val(nextBusinessDay);
            $('#business-day-update-modal-next-business-day').html("다음 영업일&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;" + nextBusinessDay);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}


function resetBusinessDay(){


    $.ajax({
        url: '/api/manager/business-day-reset',
        type: 'PATCH',
        success: function(response) {
            swal({
                title: "영업 시작",
                text: "영업일 되돌리기 작업이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            }).then(() => {
                // swal 경고창이 닫힌 후에 리다이렉트
                window.location.href = '/page/manager/business-day-management';
            });
        },
        error: function(xhr, status, error) {
            swal({
                title: "변경 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            })
            console.error('Error updating business day:', error);
        }
    });

    // 결과 JSON 데이터 출력 (또는 전송)
    console.log(businessDayUpdate);
}

