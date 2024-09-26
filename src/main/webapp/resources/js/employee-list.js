$(document).ready(function() {
    // Enter 키 입력 이벤트 리스너
    $('#empSearchValue').keypress(function(event) {
        if (event.which == 13) {  // Enter 키를 감지
            // 선택된 검색 옵션과 입력된 검색어 가져오기
            let searchOption = $('#empSearchOption').val();
            let searchValue = $('#empSearchValue').val();


            /*
            console.log("option : " + searchOption);
            console.log("value : "+searchValue);
            */

            // Ajax 요청
            $.ajax({
                url: '/api/manager/employee',
                type: 'GET',
                data: {
                    searchOption: searchOption,
                    searchValue: searchValue
                },
                success: function(response) {
                    // 성공 시 처리할 로직 작성
                    $('#employeeTableBody').empty();
                    response.forEach(function(employee) {
                            // 새로운 행을 생성하고 테이블에 추가
                            let newRow = `
                            <tr class="employee-element">
                                <td style="width: 10%;">${employee.id}</td>
                                <td style="width: 10%;">${employee.name}</td>
                                <td style="width: 20%;">${new Date(employee.birthDate).toISOString().split('T')[0]}</td>
                                <td style="width: 20%;">${employee.phoneNumber}</td>
                                <td style="width: 20%;">${employee.email}</td>
                                <td style="width: 10%;">${employee.roles}</td>
                            </tr>
                        `;
                            $('#employeeTableBody').append(newRow); // 새 데이터 추가
                    });
                },
                error: function(xhr, status, error) {
                    // 에러 발생 시 처리할 로직 작성
                    console.error('에러 발생:', error);
                }
            });
        }
    });
});