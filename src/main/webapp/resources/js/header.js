let currentBusinessDayStatus = "";
let roles = "";

$(document).ready(function() {
    handleAuthDataOfHeader();
    handleBusinessDay();

});

function handleAuthDataOfHeader(){
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function(authData) {
            // 성공 시 처리할 로직 작성
            let role = authData.roles === ("ROLE_MANAGER") ? "매니저" : "행원";
            console.log(role);
            $('#user-roles').text(role);
            $('#user-branch-name').text(authData.branchName);
            $('#user-name').text(authData.name + '님');
            createHeader(role);
            handleHeaderAndSidebar(role);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function handleBusinessDay(){
    $.ajax({
        url: '/api/common/current-business-day',
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
function createHeader(roles){
    console.log(roles);
    if(roles === "행원")
    {
        $('#navbar').append(`
        <ul id="navbar-globalMenu">
          <li id="header-account-management"><a href="/page/employee/account-open">계좌 관리</a></li>
          <li id="header-customer-management"><a href="/page/common/account-open">고객 관리</a></li>
          <li id="header-business-day-close-management"><a href="/page/employee/business-day-close">마감 관리</a></li>
          <li id="header-dashboard"><a href="/page/employee/dashboard">실적 관리 </a></li>
        </ul>
      `);
    }
    else if(roles === "매니저"){

        $('#navbar').append(`
            <ul id="navbar-globalMenu">
                <li id="header-employee-management"><a href="/page/manager/employee-save">행원 관리</a></li>
                <li id="header-customer-management"><a href="/page/common/account-open">고객 관리</a></li>
                <li id="header-business-day-management"><a href="/page/manager/business-day-management">영업일/마감 관리</a></li>
                <li id="header-dashboard"><a href="/page/manager/dashboard">지점 관리</a></li>
            </ul>
          `);
    }
}