
$(document).ready(function() {
    // enter 키
    $('#modal-input-account').keypress(function(event) {
        if (event.key === 'Enter') {
            let status = '';
            let period = '';
            const currentUrl = extractUrl(window.location.href);
            console.log(currentUrl,"currentUrl=======!!!!")
            switch (currentUrl) {
                case 'account-close':
                    status = 'OPN';
                    period = '00';
                    break;

                case 'account-close-cancel':
                    status = 'CLS';
                    period = '00';
                    break;

                case 'savings-account-close':
                    status = 'OPN';
                    period = 'SAVINGS';
                    break;
            }

            checkAccount(status,period);  // 계좌 조회 함수 호출
        }
    });

    $('#modal-check-account-btn').click(function () {
        let status = '';
        let period = '';
        const currentUrl = extractUrl(window.location.href);
        console.log(currentUrl,"currentUrl=======!!!!")
        switch (currentUrl) {
            case 'account-close':
                status = 'OPN';
                period = '00';
                break;

            case 'account-close-cancel':
                status = 'CLS';
                period = '00';
                break;

            case 'savings-account-close':
                status = 'OPN';
                period = 'SAVINGS';
                break;

            // case 'bulk-transfer':
            //
            //     break;

            case '':

                break;

        }

        checkAccount(status,period);  // 계좌 조회 함수 호출
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
function checkAccount(status,period) {
    var accountId = $('#modal-input-account').val();
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: accountId, productName: null, status:status, period: period},
        type: "GET",
        success: function(data) {
            var accountTableBody = $("#search-modal-common-table tbody");
            accountTableBody.empty();

            $.each(data, function(index, account) {
                var openDateOnly = account.openDate.split(' ')[0];

                var row = "<tr>" +
                    "<td style='width: 5%;'><input type='radio' name='select-account' value='" + account.accId + "' class='select-account-radio'></td>" +
                    "<td style='width: 20%;'>" + account.accId + "</td>" +
                    "<td style='width: 15%;'>" + openDateOnly + "</td>" +
                    "<td style='width: 20%;'>" + account.customerName + " 님</td>" +
                    "<td style='width: 15%;'>" + account.productName + "</td>" +
                    "<td style='width: 25%'>" + account.balance.toLocaleString('ko-KR') + " 원</td>" +
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




