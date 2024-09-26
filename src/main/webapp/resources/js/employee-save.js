$(document).ready(function() {
    $('#empSaveBtn').click(function() {
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
            type: 'POST',
            url: '/api/manager/employee',  // 서버의 URL로 변경
            contentType: 'application/json',
            data: JSON.stringify({
                name: name,
                birthDate: birthDate,
                email: email,
                password: password,
                branchId: branchId,
                phoneNumber: phoneNumber,
                roles: roles
            }),
            success: function(response) {
                swal({
                    title: "직원 추가 성공",
                    text: "직원 정보가 성공적으로 전송되었습니다.",
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