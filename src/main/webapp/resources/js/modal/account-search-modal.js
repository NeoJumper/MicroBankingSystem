
$(document).ready(function() {

    $('#modal-check-account-btn').click(function () {
        let status = '';
        const currentUrl = extractUrl(window.location.href);
        switch (currentUrl) {
            case 'account-close':
                status = 'OPN';
                break;

            case 'account-close-cancel':
                status = 'CLS';
                break;

            case '':

                break;

        }

        checkAccount(status);  // 계좌 조회 함수 호출
    });

    $('#modal-check-account-reset-btn').click(function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#search-modal-account').on('hide.bs.modal', function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });

    // *자신의 페이지에 맞게 구현
    // $('#search-modal-select-account-btn').click(function() {
    //     selectAccount();  // 선택된 계좌 처리 함수 호출
    // });

});


// 계좌 조회 함수
function checkAccount(status) {
    var accountId = $('#modal-input-account').val();
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: accountId, productName: null, status:status},
        type: "GET",
        success: function(data) {
            var accountTableBody = $("#search-modal-common-table tbody");
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
    var accountTableBody = $("#search-modal-common-table tbody");
    accountTableBody.empty();
}




