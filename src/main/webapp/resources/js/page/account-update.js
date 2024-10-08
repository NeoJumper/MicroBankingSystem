$(document).ready(function () {



    $('#search-modal-select-account-btn').click(function () {

        const checkedAccId = $('input[name="select-account"]:checked').val();
        getSearchAccountInfo(checkedAccId);  // 선택된 계좌 처리 함수 호출

    });
});


// function AccountIdChange(accIdInput) {
//     const accId = accIdInput.value;
//     if (accId) {
//         getSearchAccountInfo(accId);
//     }
// }

function getSearchAccountInfo(accId) {

    $.ajax({
        url: '/api/employee/account/open/' + accId,
        method: 'GET',
        success: function (data) {

            $('#customer-id-input').val(data.customerId);
            $('#customer-name-input').val(data.customerName);
            $('#password-input').val(data.password);
            $('#balance-input').val(data.balance);
            $('#product-interest-input').val(data.interestRate);
            $('#preferred-interest-input').val(data.preferentialInterestRate);
            $('#total-interest-input').val(data.totalInterestRate);
            $('#emp-name-input').val(data.registrantName);
            $('#acc-id-input').val(data.accId);
            $('#branch-name').val(data.branchName);


            $('#search-modal-account').modal('hide');

        },
        error: function (error) {
            alert("error");
        }
    });
}