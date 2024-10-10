let accountData = {};

$(document).ready(function () {
    $('#search-modal-account').on('hidden.bs.modal', function () {
        getAccountDetail();
    });

    $('#search-modal-select-account-btn').click(function () {
        selectAccount();
    })

    $('#input-confirm').click(function () {
        checkAccountId();
    })

    $('#cancel-submit-btn').click(function () {
        cancelCloseAccount();
    })
});

function getAccountDetail() {
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
                    swal({
                        title: "해지 신청하지 않은 계좌입니다.",
                        // text: "비밀번호 인증 성공",
                        icon: "warning",
                    });
                    return;
                }else{
                    accountData = data;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error fetching data:', textStatus, errorThrown);
            }
        });
    } else {
        // 입력이 비어있으면 테이블 초기화
        $('#data-table tbody').empty();
    }
}

function cancelCloseAccount(){
    var accountNumber = $('#account-number').val();
    var totalAmount = Number(accountData.accountBal) - Number(accountData.amountSum);

    if (accountNumber) {
        $.ajax({
            url: '/api/employee/close-cancel-trade',
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify({accountId: accountNumber}), // JSON으로 변환하여 전송
            success: function (response) {
                console.log(response);
                swal({
                    title: "해지 취소 완료",
                    // text: "비밀번호 인증 성공",
                    icon: "success",
                });
                // const registrationDate = new Date(accountData.amountDate);
                // const now = new Date();
                // const totalDays = Math.floor((now - registrationDate) / 1000 / 60 / 60 / 24);
                // const totalPayment = accountData.accountBal + accountData.amountSum;
                //상세 모달창 열어주기

                $('#transfer-result-modal').modal('show');

                $('#modal-account-close-customerName').text(response.customerName);
                $('#modal-account-close-accountId').text(response.accountId);
                $('#modal-account-close-productName').text(response.productName);
                $('#modal-account-close-interRate').text(response.interRate); // 기본 이율
                $('#modal-account-close-preInterRate').text(response.preInterRate);
                $('#modal-account-close-taxRate').text(response.taxRate);
                $('#modal-account-close-preTaxInterest').text(response.preTaxInterest);
                $('#modal-account-close-afterTaxInterest').text(response.afterTaxInterest);
                $('#modal-account-close-balanceToRollback').text(response.balanceToRollback);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal({
                    title: "해지 취소 실패",
                    // text: "비밀번호 인증 성공",
                    icon: "error",
                });
                console.error('오류 발생:', textStatus, errorThrown);
                // 오류 처리 로직
            }
        }).always(function () {
            accountData = {};
            $('#account-number').val("");
            $('#product-name').val("");
            $('#customer-name').val("");
            $('#account-pw-input').val("");
        });
    } else {
      // accountId가 없을 경우 경고
        swal({
            title: "계좌 ID를 입력해주세요.",
            // text: "비밀번호 인증 성공",
            icon: "warning",
        });
    }

}

function checkAccountId() {
    const inputId = $('#account-pw-input').val();
    var accountNumber = $('#account-number').val();

    $.ajax( {
        url: '/api/employee/account-validate',
        contentType: "application/x-www-form-urlencoded",
        type: "POST",
        data: {
            accountNumber: accountNumber,
            password: inputId
        },
        success: function (response) {
            swal({
                title: "검증 완료",
                text: "비밀번호 인증 성공",
                icon: "success",
            })

            //비밀번호 성공시 opacity 스타일 제거
            $('#cancel-submit-btn').removeAttr('style');
            $('#cancel-submit-btn').prop('disabled', false);

        }, error: function (error){
            swal({
                title: "검증 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });

            console.log("Transfer failed", error);
        }
    })
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
        url: "/api/employee/accounts",
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
