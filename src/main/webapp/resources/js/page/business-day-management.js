let nextBusinessDay = '';
let currentBusinessDay = '';

$(document).ready(function() {
    handleCurrentBusinessDay();
    handleNextBusinessDay();
});


/**
 * @Description
 * 현재 영업일 란 채우기
 * 현재 영업일이 마감됐을 때만 영업일 변경, 영업일 시작 버튼 활성화
 */
function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/current-business-day',
        type: 'GET',
        success: function(response) {

            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);

            if(response.status === "CLOSED")
            {

                $("#business-day-update-modal-btn").removeClass("update-btn").addClass("closed-btn");
                $("#business-day-update-modal-btn").prop("disabled", true);

                $("#business-day-update-modal-update-btn").removeClass("update-btn").addClass("closed-btn");
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
        url: '/api/next-business-day',
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
