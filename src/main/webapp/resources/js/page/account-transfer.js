
$(document).ready(function() {
    accountType = "";

    // 출금계좌 조회 버튼 클릭 시
    $('#check-withdrawal-account-btn').click(function() {
        accountType = $(this).data('account-type'); // "withdrawal" 저장
    });

    // 입금계좌 조회 버튼 클릭 시
    $('#check-deposit-account-btn').click(function() {
        accountType = $(this).data('account-type'); // "deposit" 저장



    });

    // 임시 오늘 날짜 지정
    setNowDate();

    $('.amount-btn').click(function() {
        setAmount(this);
    });

    $('#search-modal-select-account-btn').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });

    // 모달 내 계좌 검색 버튼 클릭 시 검색 처리 후 중복 계좌 비활성화
    $(document).on('ajaxSuccess', function(event, xhr, settings) {
        if (settings.url.includes("/api/employee/account")) {
            disableDuplicateAccounts();  // AJAX 성공 후 중복 계좌 처리 함수 호출
        }
    });
});


// 중복 계좌를 비활성화하는 함수
function disableDuplicateAccounts() {
    var withdrawalAccountId = $('#withdrawal-account-number').val();  // 출금 계좌 번호 가져오기

    if (withdrawalAccountId) {
        // 모달에 있는 모든 라디오 버튼을 순회하면서 출금 계좌와 동일한 계좌를 비활성화
        $('#search-modal-common-table tbody tr').each(function() {
            var accountId = $(this).find('td:eq(1)').text();  // 각 계좌의 계좌 번호 추출

            if (accountId === withdrawalAccountId) {
                // 동일한 계좌일 경우 라디오 버튼 비활성화
                $(this).find('input[name="select-account"]').prop('disabled', true);
            }
        });
    }
}


function setNowDate() {
    // 오늘 날짜를 구하는 함수
    var today = new Date();
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);  // 월은 0부터 시작하므로 1을 더함
    var day = ('0' + today.getDate()).slice(-2);
    var formattedDate = year + '-' + month + '-' + day;

    // input[type="date"]에 오늘 날짜 설정
    $('#execution-date').val(formattedDate);
}

function setAmount(button) {
    $('.amount-btn').removeClass('active');
    $(button).addClass('active');

    var amountText = $(button).text().replace(/[^0-9]/g, ''); // 숫자만 추출
    var amount = parseInt(amountText) * 10000;  // 만 단위로 변환

    // 만약 '전액' 버튼을 누르면 전체 잔액을 설정할 수 있음
    if ($(button).text() === '전액') {
        amount = parseInt($('#account-balance').text().replace(/[^0-9]/g, ''));
    }

    // 실제 값은 hidden input에 저장
    $('#transfer-amount').val(amount);

    // 천 단위 콤마는 display input에 표시
    $('#transfer-amount-display').val(amount.toLocaleString('ko-KR') + ' 원');
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
            if (accountType === "withdrawal") {
                // 출금계좌 처리
                $('#withdrawal-account-number').val(data[0].accId);
                $('#withdrawal-product-name').val(data[0].productName);
                $('#withdrawal-customer-name').val(data[0].customerName);
                // 계좌 잔액 라벨을 표시하고 금액 업데이트
                $('#account-balance-label').css('display', 'inline-block');
                $('#account-balance').text(data[0].balance.toLocaleString('ko-KR'));
                enableAmountButtons(data[0].balance); // 계좌 잔액을 넘겨줌
            } else if (accountType === "deposit") {
                // 입금계좌 처리
                $('#deposit-account-number').val(data[0].accId);
                $('#deposit-customer-name').val(data[0].customerName);
            }

            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function(error) {
            console.log("Error while fetching account details", error);
        }
    });
}

// 출금 계좌가 불러와졌을 때 실행하는 함수
function enableAmountButtons(balance) {
    $('#transfer-amount').prop('disabled', false);
    $('.amount-btn').each(function() {
        var buttonText = $(this).text().replace(/[^0-9]/g, ''); // 숫자만 추출
        var buttonAmount = parseInt(buttonText) * 10000;  // 버튼 금액 만 단위로 변환

        if (buttonAmount > balance) {
            $(this).prop('disabled', true);  // 잔액보다 큰 금액 버튼 비활성화
        } else {
            $(this).prop('disabled', false);  // 잔액 이하인 금액 버튼은 활성화
        }
    });

    // 전액 버튼은 항상 활성화
    $('.amount-btn:contains("전액")').prop('disabled', false);
}
