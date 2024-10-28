var searchOption = '';
var searchValue = '';

$(document).ready(function() {

    registerEnterEventOfCustomerSearchInput(); // Enter 키 입력 이벤트 리스너
    searchCustomer('','',1);
});

/**
 * 이벤트 등록 함수 목록
 */
function registerEnterEventOfCustomerSearchInput() {


    $('#customer-search-value').keypress(function(event) {
        if (event.which === 13) {  // Enter 키를 감지
            searchOption = $('#customer-search-option').val();
            searchValue = $('#customer-search-value').val();
            searchCustomer(searchOption, searchValue,1);  // 선택된 검색 옵션과 입력된 검색어로 DB조회
        }
    });
}

/**
 * 기능 함수 목록
 */

function searchCustomer(searchOption, searchValue,pageNum = 1){

    const amount = 10; // 페이지당 항목 수

    // Ajax 요청
    $.ajax({
        url: '/api/employee/customer',
        type: 'GET',
        data: {
            searchOption: searchOption,
            searchValue: searchValue,
            'criteria.pageNum': pageNum, // Criteria의 pageNum
            'criteria.amount': amount
        },
        success: function(response) {
            // 성공 시 처리할 로직 작성
            $('#customer-table-body').empty();
            response.customerSearchInfoList.forEach(function(customer) {
                var newRow = $('<tr class="customer-element">')
                    .append($('<td>').text(customer.customerId)).addClass('text-center')
                    .append($('<td>').text(customer.customerName)).addClass('text-center')
                    .append($('<td>').text(customer.phoneNumber)).addClass('text-center')
                    .append($('<td>').text(customer.securityLevel)).addClass('text-center')
                    .append($('<td>').text(customer.branchId)).addClass('text-center');

                $('#customer-table-body').append(newRow); // 새 데이터 추가
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
        searchCustomer(searchOption, searchValue,selectedPage);
    });
}
