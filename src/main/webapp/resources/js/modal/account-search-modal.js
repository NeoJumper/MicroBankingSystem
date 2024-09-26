
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

    // *자신의 페이지에 맞게 구현
    // $('#modal-select-account').click(function() {
    //     selectAccount();  // 선택된 계좌 처리 함수 호출
    // });

});


// 계좌 조회 함수
function checkAccount() {
    var accountId = $('#modal-input-account').val();
    $.ajax({
        url: "/api/employee/account",
        data: { accId: accountId, productName: null },
        type: "GET",
        success: function(data) {
            var accountTableBody = $("#modal-common-table tbody");
            accountTableBody.empty();

            $.each(data, function(index, account) {
                var openDateOnly = account.openDate.split(' ')[0];

                var row = "<tr>" +
                    "<td><input type='radio' name='select-account' value='" + account.accId + "' class='select-account-radio'></td>" +
                    "<td>" + account.accId + "</td>" +
                    "<td>" + openDateOnly + "</td>" +
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




