let nextBusinessDay = '';
let currentBusinessDay = '';

$(document).ready(function() {

    handleCurrentBusinessDay();
    handleNextBusinessDay();
});



function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/current-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);
            $('#business-day-update-modal-next-business-day').html("다음 영업일&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;" + currentBusinessDay);

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}
function handleNextBusinessDay(){
    $.ajax({
        url: '/api/next-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            nextBusinessDay = response.businessDate.substring(0, 10);
            $('#next-business-day').val(nextBusinessDay);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}