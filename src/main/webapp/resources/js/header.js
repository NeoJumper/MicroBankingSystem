let currentBusinessDayStatus = "";

$(document).ready(function() {
    console.log("aaa");
    handleAuthDataOfHeader();
    handleBusinessDay();
});

function handleAuthDataOfHeader(){
    console.log("aaa");
    $.ajax({
        url: '/api/auth-data',
        type: 'GET',
        success: function(authData) {
            // 성공 시 처리할 로직 작성
            console.log(authData);
            $('#user-roles').text(authData.roles);
            $('#user-branch-name').text(authData.branchName);
            $('#user-name').text(authData.name + '님');
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function handleBusinessDay(){
    $.ajax({
        url: '/api/current-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성

            var formattedDate = response.businessDate.substring(0, 10);

            $('#business-day-date').text(formattedDate);
            currentBusinessDayStatus = response.status;
            $('#business-day-status > span').text(currentBusinessDayStatus);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}