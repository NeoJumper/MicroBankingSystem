$(document).ready(function() {
    $('#modal-check-account').click(function() {
        checkAccount();  // 계좌 조회 함수 호출
    });

    $('#modal-check-account-reset').click(function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });

    $('#modal-select-account').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#accountSearchModal').on('hide.bs.modal', function() {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });
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

// 선택한 계좌 처리 함수
function selectAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        alert("계좌를 선택해 주세요.");
        return;
    }

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/account",
        data: { accId: selectedAccountId, productName: null },
        type: "GET",
        success: function(data) {
            // 계좌번호와 고객명 값을 메인 페이지에 넣기
            $('#withdrawal-account-number').val(data[0].accId);
            $('#withdrawal-product-name').val(data[0].productName);
            $('#withdrawal-customer-name').val(data[0].customerName);

            // 모달 닫기
            $('#accountSearchModal').modal('hide');
        },
        error: function(error) {
            console.log("Error while fetching account details", error);
        }
    });
}
