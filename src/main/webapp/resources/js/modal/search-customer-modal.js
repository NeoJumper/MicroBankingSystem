

$(document).ready(function () {
    $("#customer-id-search-btn").on("click", function () {

        customerSearchModalEvent();
        insertCustomerId();

    });
});

// ------------------------------- START customerSearchModalEvent()------------------------------------------

// 모달 내 고객 검색 버튼 클릭시 함수
function customerSearchModalEvent() {
    // 셀렉트 요소 가져오기
    
    $('#search-modal-search-btn').on('click', function () {
        // 드롭다운에서 선택된 값
        const searchType = $('#search-modal-select').val();
        // 입력 필드의 값 가져오기
        const searchInput = $('#search-modal-input').val();


        // 유효성 검사: 검색어가 비어 있는지 확인
        if (!searchInput) {
            alert("검색어를 입력해주세요.");
            return;
        }

        let queryParam = '';
        if (searchType === 'customer-id') {
            queryParam = 'customerId=' + searchInput;

        } else if (searchType === 'customer-name') {
            queryParam = 'customerName=' + searchInput;

        } else if (searchType === 'customer-phone') {
            queryParam = 'customerPhone=' + searchInput;

        }


        $.ajax({
            url: '/api/employee/customer?' + queryParam,
            method: 'GET',
            success: function (response) {
                var customerTableBody = $("#search-modal-customer-information");
                customerTableBody.empty();

                $.each(response, function(index, customer){
                    var row = $('<tr>')
                        .append($('<td>').append($('<input class="form-check-input row-radio" type="radio" name="selected-customer">')))
                        .append($('<td>').text(customer.customerId))
                        .append($('<td>').text(customer.customerName))
                        .append($('<td>').text(customer.formattedBirthDate)) //getformattedBirthDate()함수호출
                        .append($('<td>').text(customer.phoneNumber))
                        .append($('<td>').text(customer.branchId));

                    customerTableBody.append(row);
                })

            },
            error: function (jqXHR, textStatus, errorThrown) {

                console.error('검색 실패:', errorThrown);
                alert('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
}

// ------------------------------- END customerSearchModalEvent()------------------------------------------

// ------------------------------- START insertCustomerId()------------------------------------------
/*
    insertCustomerId() 함수 설명
    - 모달 값을 넣어줄 id명 맞춰주기
    - (5가지 요소 다 가능 / 고객아이디, 이름, 생일 등등)

    예) 경우 : 고객 검색 후 고객 id를 input값에 할당해야할 경우

        input의 id -> customer-name-input로 설정한다.
        ex ) <input type="text" id="customer-name-input" disabled>
*/

// 모달내 선택 버튼 클릭시 input 입력 함수
function insertCustomerId() {

    $('#search-modal-select-btn').on('click', function () {

        const selectedCustomer = $('input[name="selected-customer"]:checked');

        if (selectedCustomer.length > 0) {

            const insertValueMappings = [
                { selector: '#customer-id-input', columnIndex: 2 },
                { selector: '#customer-name-input', columnIndex: 3 },
                { selector: '#customer-birth-input', columnIndex: 4 },
                { selector: '#customer-phone-input', columnIndex: 5 },
                { selector: '#branch-name-input', columnIndex: 6 }
            ];

            const selectedRow = selectedCustomer.closest('tr');

            insertValueMappings.forEach(item => {
                const value = selectedRow.find(`td:nth-child(${item.columnIndex})`).text();
                const inputElement = $(item.selector);
                
                // 존재하는 요소에 필요한 값 넣어주기
                if (inputElement.length > 0 && value && value.trim() !== "") {
                    inputElement.val(value);
                }

            });


            $('#search-customer-modal').modal('hide');

        } else {
            alert('고객을 선택해 주세요.');
        }
    });

}

// ------------------------------- END insertCustomerId()------------------------------------------

