$(document).ready(function() {

    const params = new URLSearchParams(window.location.search);
// 특정 파라미터 값 가져오기 (예: "id"라는 파라미터가 있을 때)
    const id = params.get('id');


    $('#employee-search-btn').click(function()
    {
        var employeeSearchModal = new bootstrap.Modal(document.getElementById('employee-search-modal'));
        employeeSearchModal.show();
    });

    if(id)
    {
        $.ajax({
            type: 'GET',
            url: '/api/manager/employee/' + id,  // 서버의 URL로 변경
            success: function(response) {
                $('#empName').val(response.name);
                $('#empBirthDate').val(response.birthDate.split(' ')[0]);
                $('#empEmail').val(response.email);
                $('#empPassword').val(response.password);
                $('#empPhoneNumber').val(response.phoneNumber);
                // select 요소들을 기본 선택값으로 설정
                $('#empBranchId').prop('selectedIndex', 0);  // '지점 선택'으로 설정
                $('#empRoles').prop('selectedIndex', 0);     //



            },
            error: function(error) {
                alert('데이터 전송 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    }

    $('#empUpdateBtn').click(function() {
        // 폼 데이터 수집
        var name = $('#empName').val();
        var birthDate = $('#empBirthDate').val();
        var email = $('#empEmail').val();
        var password = $('#empPassword').val();
        var branchId = $('#empBranchId').val();
        var phoneNumber = $('#empPhoneNumber').val();
        var roles = $('#empRoles').val();

        /*
             if (!name || !birthDate || !email || !password || !branchName || !phoneNumber || !position) {
             alert('모든 항목을 입력해 주세요.');
             return;
             }
         */


        // Ajax 요청
        $.ajax({
            type: 'PUT',
            url: '/api/manager/employee',  // 서버의 URL로 변경
            contentType: 'application/json',
            data: JSON.stringify({
                id: 1,
                name: name,
                birthDate: birthDate,
                email: email,
                password: password,
                branchId: branchId,
                phoneNumber: phoneNumber,
                roles: roles
            }),
            success: function(response) {

                $('#empName').val('');
                $('#empBirthDate').val('');
                $('#empEmail').val('');
                $('#empPassword').val('');
                $('#empPhoneNumber').val('');
                // select 요소들을 기본 선택값으로 설정
                $('#empBranchId').prop('selectedIndex', 0);  // '지점 선택'으로 설정
                $('#empRoles').prop('selectedIndex', 0);     //


                swal({
                    title: "직원 수정 성공",
                    text: "직원 정보가 성공적으로 수정되었습니다.",
                    icon: "success",
                    button: "닫기",
                });

                $('#registrantIdOfDetailModal').val(response.id);
                $('#empPasswordOfDetailModal').val(response.password);
                $('#empNameOfDetailModal').val(response.name);
                $('#empBirthDateOfDetailModal').val(response.birthDate);
                $('#empEmailOfDetailModal').val(response.email);
                $('#empPhoneNumberOfDetailModal').val(response.phoneNumber);
                $('#empBranchNameOfDetailModal').val(response.branchName); // 지점명은 id로 받음, 필요 시 변환
                $('#empRolesOfDetailModal').val(response.roles);

                var employeeDetailModal = new bootstrap.Modal(document.getElementById('employeeDetailModal'));
                employeeDetailModal.show();

                // 성공 시 추가 작업 (예: 페이지 리로드, 메시지 표시 등)
            },
            error: function(error) {
                alert('데이터 전송 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    });
});


