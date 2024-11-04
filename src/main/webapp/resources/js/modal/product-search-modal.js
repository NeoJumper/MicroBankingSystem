
$(document).ready(function() {


    $('#search-modal-product-btn').click(function(){
        checkProduct();
    });

    $('#modal-check-product-btn').click(function () {
        let period = '';
        const hiddenPage = 'savings-account';
        switch (hiddenPage) {
            case 'account':
                period = '00';
                break;

            case 'savings-account':
                period = '';
                break;

            case '':

                break;

        }
        console.log('검색 버튼');
        checkProduct();
         // 계좌 조회 함수 호출
    });

    $('#modal-check-product-reset-btn').click(function() {
        resetProductInput();  // 입력 및 테이블 초기화 함수 호출
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#search-modal-product').on('hide.bs.modal', function() {
        resetProductInput();  // 입력 및 테이블 초기화 함수 호출
    });


});


// 계좌 조회 함수
function checkProduct() {

    var searchType = $('#search-modal-select-product').val();
    // 입력된 검색어
    var searchValue = $('#modal-product-search-input').val();
    // 정렬 순서
    var searchSortOrder = $('#search-modal-sort-order').val();
    // 지점 ID
    var branchId = $('#branch-id-hidden').val();

    // 검색어가 없으면 null로 설정
    var productName = searchType === 'productName' ? searchValue : null; // 상품이름을 조건으로 사용
    var period = searchType === 'period' ? searchValue : null; // 가입기간/약정기간을 조건으로 사용

    var requestData = {
        period: period,
        productName: productName,
        sortOrder: searchSortOrder,
        branchId: branchId

    };


    $.ajax({
        url: '/api/employee/products',
        data: requestData,
        type: "GET",
        success: function(data) {
            var productTableBody = $("#search-product-modal-body");
            productTableBody.empty();

            $.each(data, function(index, product) {

                var row = "<tr>" +
                    "<td><input type='radio' name='selected-product-id' value='" + product.productId + "' class='select-product-radio'></td>" +
                    "<td>" + product.productId +"</td>" +
                    "<td>" + product.productName + " </td>" +
                    "<td>" + product.productPeriod + "월 </td>" +
                    "<td>" + product.productInterestRate + "% </td>" +
                    "<td>" + product.productTaxRate + "% </td>" +
                    "<td>" + product.branchName + "</td>" +
                    "</tr>";
                productTableBody.append(row);
            });
        },
        error: function(error) {
            console.log("checkProduct() '/api/employee/products'api 에러남", error);
        }
    });


}

// 입력 및 테이블 초기화 함수
function resetProductInput() {
    $('#modal-product-search-input').val('');
    var productTableBody = $("#search-product-modal-common-table tbody");
    productTableBody.empty();
}




