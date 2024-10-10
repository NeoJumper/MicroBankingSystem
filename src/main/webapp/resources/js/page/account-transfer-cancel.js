$(document).ready(function () {
    handleTransferData();

    // 비밀번호 인증 버튼 클릭 시
    $('#cancel-transfer-validate').click(function(){
        validateAccountPassword();
    });

    // 거래 취소 버튼 클릭 시
    $('#cancel-transfer-submit').click(function() {
        cancelTransfer();  // 거래 취소 처리
    });
});

function handleTransferData() {
    const params = new URLSearchParams(window.location.search);
    const tradeNumber = params.get('tradeNumber');

    // tradeNumber 값이 존재할 때
    if (tradeNumber) {
        $.ajax({
            type: 'GET',
            url: '/api/employee/account-transfer-cancel/' + tradeNumber,
            success: function (response) {
                console.log(response);
                // 응답 데이터를 처리해서 테이블에 값 설정
                response.forEach(function (trade) {
                    if (trade.tradeType === "WITHDRAWAL") {
                        $('#cancel-transfer-withdrawal-acc-id').text(trade.accId);
                        $('#cancel-transfer-withdrawal-customer-name').text(trade.customerName);
                        $('#cancel-transfer-withdrawal-amount').text(trade.amount.toLocaleString('ko-KR'));
                        $('#cancel-transfer-withdrawal-balance').text(trade.balance.toLocaleString('ko-KR'));
                        $('#cancel-transfer-withdrawal-trade-date').text(trade.tradeDate.substring(0, 10));
                        $('#cancel-transfer-withdrawal-description').text(trade.description);
                    } else if (trade.tradeType === "DEPOSIT") {
                        $('#cancel-transfer-deposit-acc-id').text(trade.accId);
                        $('#cancel-transfer-deposit-customer-name').text(trade.customerName);
                        $('#cancel-transfer-deposit-amount').text(trade.amount.toLocaleString('ko-KR'));
                        $('#cancel-transfer-deposit-balance').text(trade.balance.toLocaleString('ko-KR'));
                    }
                });
            },
            error: function (error) {
                console.log("Error fetching transfer details:", error);
            }
        });
    }
}

// 비밀번호 인증
function validateAccountPassword() {
    var accountNumber = $('#cancel-transfer-deposit-acc-id').text();
    var accountPassword = $('#cancel-transfer-account-password').val();

    $.ajax({
        url: '/api/employee/account-validate',
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded',
        data: {
            accountNumber: accountNumber,
            password: accountPassword
        },
        success: function (response) {
            swal({
                title: "검증 완료",
                text: "비밀번호 인증 성공",
                icon: "success",
            })

            // 비밀번호 인증 성공 후 취소 버튼 활성화
            $('#cancel-transfer-submit').prop('disabled', false);

        },
        error: function (error) {
            swal({
                title: "검증 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });
            console.log("Password validation failed:", error);
        }
    });
}

// 거래 취소 처리
function cancelTransfer(){
    const params = new URLSearchParams(window.location.search);
    const tradeNumber = params.get('tradeNumber');

    var accountPassword = $('#cancel-transfer-account-password').val();

    if(tradeNumber){
        $.ajax({
            url: '/api/employee/account-transfer-cancel',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                tradeNumber: tradeNumber,
                password: accountPassword
            }),
            success: function (response) {
                swal({
                    title: "취소 신청 완료",
                    text: "취소 요청 성공",
                    icon: "success",
                }).then(function () {
                    // 알림 창을 닫은 후 페이지 이동
                    window.location.href = `/page/employee/trade-list`;
                });
            }, error: function (error){
                swal({
                    title: "취소 요청 실패",
                    text: "취소 요청 실패",
                    icon: "error",
                    buttons: {
                        cancel: true,
                        confirm: false,
                    },
                });
                console.log(error);
            }
        });
    }

}