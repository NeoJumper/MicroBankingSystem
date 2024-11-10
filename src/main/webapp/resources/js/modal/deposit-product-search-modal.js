$(document).ready(function () {

    registerClickEventOfDepositProductSearchBtn();
    selectDepositProduct();
    $("#deposit-product-search-modal-search-btn").on("click", function () {
        searchEmployee();
    });
});

function registerClickEventOfDepositProductSearchBtn() {
    $('#deposit-product-search-btn').on('click', function () {
        $('#deposit-product-search-modal-product-information').empty();
    });
}

function searchEmployee(){
    // 선택된 검색 옵션과 입력된 검색어 가져오기
    let searchOption = $('#deposit-product-search-modal-select').val();
    let searchValue = $('#deposit-product-search-modal-input').val();
    let selectedAccountType =  $('input[name="major-category"]:checked').val();
    //console.log("option : " + searchOption);
    //console.log("value : "+searchValue);
    // Ajax 요청
    $.ajax({
        url: '/api/employee/deposit-products',
        type: 'GET',
        data: {
            searchOption: searchOption,
            searchValue: searchValue,
            accountType : selectedAccountType
        },
        success: function(response) {
            // 성공 시 처리할 로직 작성
            $('#deposit-product-search-modal-product-information').empty();
            response.forEach(function(product) {
                // 새로운 행을 생성하고 테이블에 추가
                let newRow = `
                            <tr class="product-element">
                                <td style="width: 12%;"><input class="form-check-input row-radio" type="radio" name="selected-product"></td>
                                <td style="width: 20%;">${product.id}</td>
                                <td style="width: 30%;">${product.name}</td>
                                <td style="width: 20%;">${product.interestRate}</td>
                                <td style="width: 20%;">${product.taxRate}</td>
                            </tr>
                        `;

                $('#deposit-product-search-modal-product-information').append(newRow); // 새 데이터 추가
            });

            $('.product-element').on('click', function() {
                // 해당 tr 안의 라디오 버튼을 체크
                $(this).find('.row-radio').prop('checked', true);
            });

            // 라디오 버튼이 클릭되었을 때도 체크되도록 설정
            $('.row-radio').on('click', function(e) {
                e.stopPropagation();  // 이벤트 전파 중단 (tr 클릭이 중복 처리되지 않도록)
            });

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

// 모달내 선택 버튼 클릭시 input 입력 함수
function selectDepositProduct() {

    $('#deposit-product-search-modal-select-btn').on('click', function () {

        const selectedProduct = $('input[name="selected-product"]:checked');

        if (selectedProduct.length > 0) {

            const insertValueMappings = [
                { selector: '#product-id-hidden-input', columnIndex: 2 },
                { selector: '#deposit-product-name-input', columnIndex: 3 },
                { selector: '#product-interest-input', columnIndex: 4 },
            ];

            const selectedRow = selectedProduct.closest('tr');

            insertValueMappings.forEach(item => {
                const value = selectedRow.find(`td:nth-child(${item.columnIndex})`).text();
                const inputElement = $(item.selector);

                //console.log(value);
                //console.log(inputElement);
                // 존재하는 요소에 필요한 값 넣어주기
                if (inputElement.length > 0 && value && value.trim() !== "") {
                    inputElement.val(value);
                }

            });

            $('#search-deposit-product-modal').modal('hide');
            handleTransferLimitText();

        } else {
            swal({
                title: "고객을 선택해 주세요.",
                // text: "비밀번호 인증 성공",
                icon: "warning",
            });
        }
    });

}