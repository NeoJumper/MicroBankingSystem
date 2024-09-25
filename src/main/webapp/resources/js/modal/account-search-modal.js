$(document).ready(function() {
    $('#modal-check-account').click(function() {
        var accountNumber = $('#modal-input-account').val();
        $.ajax({
            url: "/api/employee/account",
            data: { accountNumber: accountNumber, productName: null},
            type: "GET",
            success: function(data) {
                var accountTableBody = $("#modal-common-table tbody");
                accountTableBody.empty();

                $.each(data, function(index, account) {
                    var row = "<tr>" +
                        "<td><button class='button-main '>선택</button></td>" +
                        "<td>" + account.accountId + "</td>" +
                        "<td>" + account.openDate + "</td>" +
                        "<td>" + account.customerName + "</td>" +
                        "<td>" + account.productName + "</td>" +
                        "<td>" + account.balance + "</td>" +
                        "</tr>";
                    accountTableBody.append(row);
                });
            },
            error: function(error) {
                console.log("Error while fetching accounts", error);
            }
        });
    });

    $('#modal-check-account-reset').click(function() {
        $('#modal-input-account').val('');
        var accountTableBody = $("#modal-common-table tbody");
        accountTableBody.empty();
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#accountSearchModal').on('hide.bs.modal', function() {
        var accountTableBody = $("#modal-common-table tbody");
        accountTableBody.empty();
        $('#modal-input-account').val('');
    });
});