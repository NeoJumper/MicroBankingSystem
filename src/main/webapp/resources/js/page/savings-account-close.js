$(document).ready(function () {
    $('#search-modal-select-account-btn').click(function () {

        // 검색한 적금계좌의 해지 정보 api
        getSavingsAccount();

    });

});

/*
function selectSavingsAccount() {

    var selectedRow = $('input[name="select-account"]:checked').closest('tr');
    var accountId = selectedRow.find('td:eq(1)').text();

    if (!accountId) {
        swal({
            title: "계좌를 선택해 주세요.",
            icon: "warning",
        });
        return;
    }
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: accountId, },
        type: "GET",
        success: function (data) {
            console.log("======!!!", data);
            console.log(data);
            $('#savings-account-close-number').val(data[0].accId);
            $('#savings-account-product-name').val(data[0].productName);
            $('#customer-name').val(data[0].customerName);
            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });

}
*/

function getSavingsAccount() {

    var selectedRow = $('input[name="select-account"]:checked').closest('tr');
    var accountId = selectedRow.find('td:eq(1)').text();

    if (!accountId) {
        swal({
            title: "계좌를 선택해 주세요.",
            icon: "warning",
        });
        return;
    }
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/savings-account-close-details/"+accountId,
        type: "GET",
        success: function (data) {
            console.log("======!!!", data);
            console.log(data);
            $('#savings-account-close-number').val(data.accountId);
            $('#savings-account-product-name').val(data.productName);
            $('#customer-name').val(data.customerName);


            $('#product-type').val(data.productType);

            $('#table-content tbody').append(
                '<tr>' +
                '<td style="width: 5%;">' + data.productType  + '</td>' +
                '<td style="width: 5%;">' + data.productInterestRate + '%' + '</td>' +
                '<td style="width: 5%;">' + data.accountInterestRate + '%' + '</td>' +
                '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                '<td style="width: 5%;">' + '세전 이자'  + '</td>' +
                '<td style="width: 5%;">' + '세후 이자' + '원' + '</td>' +
                '<td style="width: 10%;">' + '계산 완료된 이자금액'  + '</td>' +
                /* 이자가 포함된 총 지급 금액*/
                '<td style="width: 10%;">' + data.amountSum + '</td>' +
                '</tr>'
            );
            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });

}
