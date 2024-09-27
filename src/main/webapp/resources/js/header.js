$(document).ready(function() {

    handleAuthDataOfHeader();

});

function handleAuthDataOfHeader(){
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