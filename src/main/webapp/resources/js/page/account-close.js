$(document).ready(function () {
    isClosed();

    $('#search-modal-account').on('hidden.bs.modal', function () {
        getAccountDetail();
    });

    $('#search-modal-select-account-btn').click(function () {
        selectAccount();
    })

    $('#common-account-submit-btn').click(function () {
        closeAccount();
    })

    $('#input-confirm').click(function () {
        checkAccountId();
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
                if (data.accountStatus === "CLS") {
                    swal({
                        title: "해지 신청된 계좌",
                        text: "이미 해지가 신청된 계좌입니다.",
                        icon: "warning",
                    });
                    return;
                }else{
                    accountData = data;

                    const textAfterInter = Number(data.amountSum) * (1-Number(data.productTaxRate));
                    const totalPayment = data.accountBal + textAfterInter;
                    accountData.textAfterInter = textAfterInter;
                    accountData.totalPayment = totalPayment;

                    console.log(data.accountId);
                    $('#table-content tbody').append(
                        '<tr>' +
                        '<td style="width: 5%;">' + data.accountBal + '원' + '</td>' +
                        '<td style="width: 5%;">' + data.productInterRate + '%' + '</td>' +
                        '<td style="width: 5%;">' + data.accountPreInterRate + '%' + '</td>' +
                        '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                        '<td style="width: 10%;">' + data.amountSum + '</td>' +
                        '<td style="width: 10%;">' + textAfterInter + '</td>' +
                        '<td style="width: 10%;">' + totalPayment + '</td>' +
                        '</tr>'
                    );
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
            $('#common-account-submit-btn').removeAttr('style');
            $('#common-account-submit-btn').prop('disabled', false);

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

function closeAccount() {
    var accountNumber = $('#account-number').val();
    // accountId가 비어있지 않은지 확인
    if (accountNumber) {
        console.log("accountClose Start")
        $.ajax({
            url: '/api/employee/close-trade',
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify({accId: accountNumber, amount: accountData.totalPayment, status: "CLS", description:"계좌해지", tradeType:"CLOSE"}), // JSON으로 변환하여 전송
            success: function (response) {
                swal({
                    title: "해지 성공",
                    // text: "비밀번호 인증 성공",
                    icon: "success",
                });
                //상세 모달창 열어주기
                $('#transfer-result-modal').modal('show');

                $('#modal-account-close-customerName').text(accountData.customerName);
                $('#modal-account-close-accountId').text(accountData.accountId);
                $('#modal-account-close-productName').text(accountData.productName);
                $('#modal-account-close-accountBal').text(accountData.accountBal);
                $('#modal-account-close-productInterRate').text(accountData.productInterRate);
                $('#modal-account-close-accountPreInterRate').text(accountData.accountPreInterRate);
                $('#modal-account-close-productTaxRate').text(accountData.productTaxRate);
                $('#modal-account-close-amountSum').text(accountData.amountSum);
                $('#modal-account-close-textAfterInter').text(accountData.textAfterInter);
                $('#modal-account-close-totalPayment').text(accountData.totalPayment);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('오류 발생:', textStatus, errorThrown);
                swal({
                    title: "해지 실패",
                    // text: "비밀번호 인증 성공",
                    icon: "warning",
                });
            }
        }).always(function () {
            accountData = {};
            $('#account-number').val("");
            $('#product-name').val("");
            $('#customer-name').val("");
            $('#account-pw-input').val("");
            // 버튼 비활성화
            $('#common-account-submit-btn').attr('style', 'background-color: gray; opacity: 0.5;');
            $('#common-account-submit-btn').prop('disabled', true);
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

function selectAccount() {

    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        swal({
            title: "계좌를 선택해 주세요.",
            // text: "비밀번호 인증 성공",
            icon: "warning",
        });
        return;
    }
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: selectedAccountId, productName: null},
        type: "GET",
        success: function (data) {
            console.log("======!!!",data);
           // const openAccount = data.filter((account)=> account.)
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
