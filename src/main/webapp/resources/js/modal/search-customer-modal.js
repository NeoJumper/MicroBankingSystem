// 모달 내 고객 검색 버튼 클릭시 함수
function customerSearchModalEvent() {
    // 셀렉트 요소 가져오기
    
    $('#search-modal-search-btn').on('click', function () {
        // 드롭다운에서 선택된 값
        const searchType = $('#search-modal-select').val();
        // 입력 필드의 값 가져오기
        const searchInput = $('#search-modal-input').val();

        alert(searchType + "searchType" + searchInput + "searchInput");

        // 유효성 검사: 검색어가 비어 있는지 확인
        if (!searchInput) {
            alert("검색어를 입력해주세요.");
            return;
        }

        let queryParam = '';
        if (searchType === 'customer-id') {
            queryParam = 'customerId=' + searchInput;
            alert(searchType);
        } else if (searchType === 'customer-name') {
            queryParam = 'customerName=' + searchInput;
            alert(searchType);
        } else if (searchType === 'customer-phone') {
            queryParam = 'customerPhone=' + searchInput;
            alert(searchType);
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

                /*
                                var newBody = $('<tbody id="search-modal-customer-information">');


                                response.forEach(function (item) {
                                    console.log("Item:", item); // 개별 항목 확인

                                    var row = $('<tr>')
                                        .append($('<td>').append($('<input class="form-check-input row-radio" type="radio" name="selected-customer">')))
                                        .append($('<td>').text(item.customerId))
                                        .append($('<td>').text(item.customerName))
                                        .append($('<td>').text(item.formattedBirthDate)) //getformattedBirthDate()함수호출
                                        .append($('<td>').text(item.phoneNumber))
                                        .append($('<td>').text(item.branchId));

                                    newBody.append(row);
                                });

                                $('#search-modal-customer-information').empty();
                                $('#search-modal-customer-information').replaceWith(newBody);*/
            },
            error: function (jqXHR, textStatus, errorThrown) {

                console.error('검색 실패:', errorThrown);
                alert('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
}

// 모달내 선택 버튼 클릭시 함수
function insertCustomerId() {

    $('#search-modal-select-btn').on('click', function () {

        const selectedCustomer = $('input[name="selected-customer"]:checked');

        if (selectedCustomer.length > 0) {

            const selectedRow = selectedCustomer.closest('tr');
            const customerId = selectedRow.find('td:nth-child(2)').text();
            $('#customer-id-input').val(customerId);

            const customerName = selectedRow.find('td:nth-child(3)').text();
            $('#customer-name').val(customerName);

            $('#search-customer-modal').modal('hide');

        } else {
            alert('고객을 선택해 주세요.');
        }
    });

}

$(document).ready(function () {
    $("#customer-id-search-btn").on("click", function () {

        customerSearchModalEvent();
        insertCustomerId();

    });
});

