
$(document).ready(function () {
    accoutOpenProductInfo();




    // 상품의 총이율 text입력
    $('#preferred-interest').on('input', function() {
        calculateTotalInterest();
    });

    accountOpen();


});



// 상품이율, 상품번호 api
function accoutOpenProductInfo(){
    // 상품이율 구하기
    $.ajax({
        url: '/api/employee/account/productInterest',
        method: 'GET',
        success: function(data) {
            $('#preferred-interest').val(data.interestRate);
            $('#product-id').val(data.id)
        },
        error: function(error) {
            console.error('Error fetching product interest:', error);
            $('#preferred-interest').val('error');
        }
    });

}

// 기준이율 + 우대이율 = 총 이자율 계산
function calculateTotalInterest() {
    const productInterest = parseFloat($('#product-interest').val()) || 0; // 기준이율
    const preferredInterest = parseFloat($('#preferred-interest').val()) || 0; // 우대이율

    const totalInterest = productInterest + preferredInterest;
    $('#total-interest').val(totalInterest.toFixed(2)); // 소수점 2자리까지 출력
}

// 계좌 생성 함수
function accountOpen(){

    $('#account-create-btn').on("click",function (){

        alert("click #accountCreateBtn");
        const customerId = $('#customer-id-input').val();
        const productId = $('#product-id').val();

        //const startDate = $('startDate').val();
        const preferentialInterestRate =$('#preferred-interest').val();
        const password =$('#password').val();
        const balance =$('#balance').val();

        const empId =$('#emp-id').val();
        const branchId = $('#branch-id').val();

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
                balance: balance

            }),
            success: function(response) {
                alert('계좌가 성공적으로 생성되었습니다.');
                accountOpenResult(); // 개설계좌 정보 성공 모달 호출

            },
            error: function() {
                alert('계좌 생성에 실패했습니다.');
            }
        });

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
function accountOpenResult(){

    $("#account-open-result-modal").modal("show");

    $.ajax({
        url: '/api/employee/account/productInterest',
        method: 'GET',
        success: function(data) {
            $('#product-interest').val(data.interestRate);
            $('#product-id').val(data.id)
        },
        error: function(error) {
            $('#product-interest').val('error');
        }
    });
}




