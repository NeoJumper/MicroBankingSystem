$(document).ready(function () {
    // 상품 정보 api 함수
    accoutOpenProductInfo();

    // 총이율 입력 함수
    InputChangeOfTotalInterest();

    // 계좌 개설 함수
    accountOpen();

});

// 상품이율, 상품번호 api
function accoutOpenProductInfo() {
    // 상품이율 구하기
    $.ajax({
        url: '/api/employee/account/product-interest',
        method: 'GET',
        success: function (data) {
            $('#product-interest-input').val(data.interestRate);
            $('#product-id-hidden-input').val(data.id)
        },
        error: function (error) {
            console.error('Error fetching product interest:', error);
            $('#preferred-interest-input').val('error');
        }
    });

}

// 기준이율 + 우대이율 = 총 이자율 계산
function calculateTotalInterest() {
    const productInterest = parseFloat($('#product-interest-input').val()) || 0; // 기준이율
    const preferredInterest = parseFloat($('#preferred-interest-input').val()) || 0; // 우대이율

    const totalInterest = productInterest + preferredInterest;
    $('#total-interest-input').val(totalInterest.toFixed(2)); // 소수점 2자리까지 출력
}

function InputChangeOfTotalInterest() {
    $('#preferred-interest-input').on('input', function () {
        calculateTotalInterest();
    });
}

// 계좌 생성 함수
function accountOpen() {

    $('#account-create-btn').on("click", function () {


        const customerId = $('#customer-id-input').val();
        const productId = $('#product-id-hidden-input').val();

        //const startDate = $('startDate').val();
        const preferentialInterestRate = $('#preferred-interest-input').val();
        const password = $('#password-input').val();
        const balance = $('#balance-input').val();

        const empId = $('#emp-id-hidden-input').val();
        const branchId = $('#branch-id-hidden-input').val();

        $.ajax({
            type: 'POST',
            url: '/api/employee/account/open',
            contentType: 'application/json',
            data: JSON.stringify({
                branchId: branchId,
                customerId: customerId,
                registerId: empId,
                productId: productId,
                preferentialInterestRate: preferentialInterestRate,
                password: password,
                balance: balance,
                tradeType: "OPEN"
            }),
            success: function (accountId) {
                swal({
                    title: " 계좌 생성 성공",
                    text: "계좌가 성공적으로 개설되었습니다.",
                    icon: "success",
                    button: "닫기",
                }).then(() => {
                    // swal의 닫기 버튼이 클릭된 후 실행
                    accountOpenResult(accountId); // 개설된 계좌 정보 성공 모달 호출
                });

            },
            error: function (error) {
                swal({
                    title: "검증 실패",
                    text: error.responseText,
                    icon: "error",
                    buttons: {
                        cancel: true,
                        confirm: false,
                    },
                });
            }

        });// end
    });
}

// modal 내용 지우기
function clearCustomerSearchModal() {
    $('#customerIdText').val('');
    $('#customerName').val('');
    $('#customerBirth').val('');
    $('#customerPhone').val('');
    $('#searchQuery').val('');
    $('#customerSearch').prop('selectedIndex', 0);
    $('#customer-info').empty();
}


// 계좌 개설 완료 모달 호출 함수
function accountOpenResult(accountId) {

    $.ajax({
        url: '/api/employee/account/open/' + accountId,
        method: 'GET',
        success: function (data) {


            $('#result-modal-account-id-input').val(data.accId);
            $('#result-modal-customer-name-input').val(data.customerName)
            $('#result-modal-customer-number-input').val(data.customerId);
            $('#result-modal-phone-number-input').val(data.phoneNumber);
            $('#result-modal-product-name-input').val(data.productName);
            $('#result-modal-start-date-input').val(data.startDate);

            $('#result-modal-balance-input').val(data.balance);
            $('#result-modal-branch-name-input').val(data.branchName);
            $('#result-modal-registrant-name-input').val(data.registrantName);

            $('#result-modal-total-interest-input').val(data.totalInterestRate);

            $("#result-modal-open-account").modal("show");


        },
        error: function (error) {
            $('#product-interest-input').val('error');
        }
    });
}




