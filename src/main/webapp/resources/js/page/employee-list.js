$(document).ready(function() {

    registerEnterEventOfEmpSearchInput(); // Enter 키 입력 이벤트 리스너
    searchEmployee('','',1);
});

/**
 * 이벤트 등록 함수 목록
 */
function registerEnterEventOfEmpSearchInput() {


    $('#emp-search-value').keypress(function(event) {
        if (event.which === 13) {  // Enter 키를 감지
            let searchOption = $('#emp-search-option').val();
            let searchValue = $('#emp-search-value').val();
            searchEmployee(searchOption, searchValue);  // 선택된 검색 옵션과 입력된 검색어로 DB조회
        }
    });
}

/**
 * 기능 함수 목록
 */

function searchEmployee(searchOption, searchValue,pageNum = 1){

    const amount = 10; // 페이지당 항목 수

    // Ajax 요청
    $.ajax({
        url: '/api/manager/employee',
        type: 'GET',
        data: {
            searchOption: searchOption,
            searchValue: searchValue,
            'criteria.pageNum': pageNum, // Criteria의 pageNum
            'criteria.amount': amount
        },
        success: function(response) {
            // 성공 시 처리할 로직 작성
            $('#emp-table-body').empty();

            response.employeeSearchInfoList.forEach(function(employee) {
                // 새로운 행을 생성하고 테이블에 추가
                var role = employee.address === 'ROLE_MANAGER' ? '매니저' : '행원';

                let newRow = `
                            <tr class="emp-element">
                                <td style="text-align: center;">${employee.id}</td>
                                <td style="text-align: center;">${employee.name}</td>
                                <td style="text-align: center;">${employee.email}</td>
                                <td style="text-align: center;">${employee.phoneNumber}</td>
                                <td style="text-align: center;">${role}</td>
                                <td style="text-align: center;">${employee.roles}</td>
                            </tr>
                        `;
                $('#emp-table-body').append(newRow); // 새 데이터 추가
            });
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}