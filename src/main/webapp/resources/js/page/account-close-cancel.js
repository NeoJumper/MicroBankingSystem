let accountData = {};

$(document).ready(function () {
    $('#search-modal-account').on('hidden.bs.modal', function () {

        var accountNumber = $('#account-number').val();

        if (accountNumber) {
            $.ajax({
                url: '/api/employee/account-close-details/' + accountNumber,
                type: 'GET',
                success: function (data) {
                    // 테이블 초기화
                    $('#table-content tbody').empty();
                    // 취소신청이 완료된 계좌는 alert로 알림
                    if (data.accountStatus !== "CLS") {
                        window.alert("해지 신청하지 않은 계좌입니다.");
                        return;
                    }
                    accountData = data;
                    // console.log(data)
                    // const registrationDate = new Date(data.amountDate);
                    // const now = new Date();
                    // const totalDays = Math.floor((now - registrationDate) / 1000 / 60 / 60 / 24);
                    // const totalIntRate = data.interestRateSum + data.accountPreInterRate;
                    // const totalPayment = data.accountBal + data.amountSum;
                    // console.log("=========================");
                    // console.log(data.accountId);
                    // $('#table-content tbody').append(
                    //     '<tr>' +
                    //     '<td style="width: 5%;">' + totalDays + '일' + '</td>' +
                    //     '<td style="width: 5%;">' + totalIntRate + '%' + '</td>' +
                    //     '<td style="width: 10%;">' + data.amountSum + '</td>' +
                    //     '<td style="width: 10%;">' + data.accountBal + '</td>' +
                    //     '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                    //     '<td style="width: 10%;">' + totalPayment + '</td>' +
                    //     '</tr>'
                    // );
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error('Error fetching data:', textStatus, errorThrown);
                }
            });
        } else {
            // 입력이 비어있으면 테이블 초기화
            $('#data-table tbody').empty();
        }
    });

    $('#search-modal-select-account-btn').click(function () {
        selectAccount();
    })

    $('#submit-btn').click(function () {
        closeAccount();
    })

    $('#input-confirm').click(function () {
        checkAccountId();
    })

    $('#cancel-submit-btn').click(function () {
        cancelCloseAccount();
    })

});

function cancelCloseAccount(){
    var accountNumber = $('#account-number').val();
    var totalAmount = Number(accountData.accountBal) - Number(accountData.amountSum);

    if (accountNumber) {
        $.ajax({
            url: '/api/employee/close-trade',
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify({accId: accountNumber, amount: 0, status: "OPN", description:"계좌해지 취소", balance:totalAmount, tradeType:"OPEN"}), // JSON으로 변환하여 전송
            success: function (response) {
                alert('성공:', response);
                // TODO:: 상세 모달창 열어주기

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('오류 발생:', textStatus, errorThrown);
                // 오류 처리 로직
            }
        }).always(function () {
            accountData = {};
        });
    } else {
        alert('계좌 ID를 입력해주세요.'); // accountId가 없을 경우 경고
    }

}

function checkAccountId() {
    const inputId = $('#account-pw').val();
    if (!inputId) {
        alert("비밀번호를 입력하세요.");
        return;
    }
    if (accountData.customerId == inputId) {
        $('#cancel-submit-btn').prop('disabled', false);
    }else{
        $('#account-pw').val('');
        window.alert("비밀번호 불일치");
    }
}

function closeAccount() {
    var accountNumber = $('#account-number').val();
    var totalAmount = Number(accountData.amountSum) + Number(accountData.accountBal);
    // accountId가 비어있지 않은지 확인
    if (accountNumber) {
        $.ajax({
            url: '/api/employee/close-trade',
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify({accId: accountNumber, amount: totalAmount, status: "CLS"}), // JSON으로 변환하여 전송
            success: function (response) {
                alert('성공:', response);
                // TODO:: 상세 모달창 열어주기
                // 성공하면 계좌의 해지조회 값을 비워 줌.
                accountData = {};
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('오류 발생:', textStatus, errorThrown);
                // 오류 처리 로직
            }
        });
    } else {
        alert('계좌 ID를 입력해주세요.'); // accountId가 없을 경우 경고
    }
}

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
        data: {accId: selectedAccountId, productName: null},
        type: "GET",
        success: function (data) {
            console.log(data);
            $('#account-number').val(data[0].accId);
            $('#product-name').val(data[0].productName);
            $('#customer-name').val(data[0].customerName);
            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}
