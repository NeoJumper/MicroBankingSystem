var searchOption = '';
var searchValue = '';


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
                var role = employee.role === 'ROLE_MANAGER' ? '매니저' : '행원';
                var address = employee.address === null ? '' : employee.address;

                let newRow = `
                            <tr class="emp-element">
                                <td style="text-align: center;">${employee.id}</td>
                                <td style="text-align: center;">${employee.name}</td>
                                <td style="text-align: center;">${employee.email}</td>
                                <td style="text-align: center;">${employee.phoneNumber}</td>
                                <td style="text-align: center;">${address}</td>
                                <td style="text-align: center;">${role}</td>
                            </tr>
                        `;
                $('#emp-table-body').append(newRow); // 새 데이터 추가
            });
            updatePagination(response.pageDTO);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}


// paging 버튼 동적 생성 및 버튼 클릭시 동작
function updatePagination(pageDTO) {
    const paginationContainer = $('#pagination'); // 페이지네이션을 넣을 컨테이너
    paginationContainer.empty(); // 기존 내용 제거

    console.log("pageDTO.startPage" + pageDTO.startPage);
    if (pageDTO.prev) {
        const prevButton = $('<a href="#" data-page="' + (pageDTO.startPage - 1) + '" class="pagination-btn">이전</a>');
        paginationContainer.append(prevButton);
    }

    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        const pageButton = $('<a href="#" data-page="' + i + '" class="pagination-btn ' + (pageDTO.criteria.pageNum === i ? 'active' : '') + '">' + i + '</a>');
        paginationContainer.append(pageButton);
    }

    if (pageDTO.next) {
        const nextButton = $('<a href="#" data-page="' + (pageDTO.endPage + 1) + '" class="pagination-btn">다음</a>');
        paginationContainer.append(nextButton);
    }

    // 페이지 버튼 클릭 이벤트 처리
    paginationContainer.find('a').on('click', function (e) {
        e.preventDefault(); // 기본 링크 동작 방지
        const selectedPage = $(this).data('page'); // 클릭한 페이지 번호 가져오기
        searchEmployee(searchOption, searchValue,selectedPage);
    });
}
