$(document).ready(function() {
    $('#modal-check-account').click(function() {
        checkAccount();  // 계좌 조회 함수 호출
    });

    $('#modal-check-account-reset').click(function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#accountSearchModal').on('hide.bs.modal', function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });
});

// 계좌 조회 함수
function checkAccount() {
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
                    "<td><button class='button-main select-account'>선택</button></td>" +
                    "<td>" + account.accountId + "</td>" +
                    "<td>" + account.openDate + "</td>" +
                    "<td>" + account.customerName + " 님</td>" +
                    "<td>" + account.productName + "</td>" +
                    "<td>" + account.balance.toLocaleString('ko-KR') + " 원</td>" +
                    "</tr>";
                accountTableBody.append(row);
            });
        },
        error: function(error) {
            console.log("Error while fetching accounts", error);
        }
    });
}

// 입력 및 테이블 초기화 함수
function resetAccountInput() {
    $('#modal-input-account').val('');
    var accountTableBody = $("#modal-common-table tbody");
    accountTableBody.empty();
}
