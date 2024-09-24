$(document).ready(function() {
    $('#modal-check-account').click(function() {
        var accountNumber = $('#modal-input-account').val();
        alert(accountNumber);
        $.ajax({
            url: "/api/employee/accounts",
            type: "GET",
            dataType: "json",
            success: function(data) {
                // 응답 데이터(JSON 배열)를 처리하는 부분
                var accountTableBody = $("#modal-common-table tbody");
                accountTableBody.empty(); // 기존 내용을 비움

                // 데이터 배열을 반복하면서 테이블에 행을 추가
                $.each(data, function(index, account) {
                    var row = "<tr>" +
                        "<td>" + account.id + "</td>" +
                        "<td>" + account.branchId + "</td>" +
                        "<td>" + account.customerId + "</td>" +
                        "<td>" + account.productId + "</td>" +
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
    });
});
