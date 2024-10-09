let accountData = {};

$(document).ready(function () {
    $('#search-modal-account').on('hidden.bs.modal', function () {
        console.log("모달 닫힘");
        getAccountDetail();
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
                        title: "해지 신청이 완료된 계좌입니다.",
                        // text: "비밀번호 인증 성공",
                        icon: "warning",
                    });
                    return;
                }else{
                    accountData = data;
                    console.log(data)

                    const textAfterInter = Number(data.amountSum) * (1-Number(data.productTaxRate));
                    const totalPayment = data.accountBal + textAfterInter;
                    accountData.textAfterInter = textAfterInter;
                    accountData.totalPayment = totalPayment;
                    console.log("=========================accountDataTotalPaymemt");
                    console.log(accountData.totalPayment, "==============총 지급 금액")
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
    if (!inputId) {
        swal({
            title: "비밀번호를 입력하세요.",
            // text: "비밀번호 인증 성공",
            icon: "warning",
        });
        return;
    }
    if (accountData.customerId == inputId) {
        swal({
            title: "비밀번호 인증 성공.",
            // text: "비밀번호 인증 성공",
            icon: "success",
        });
        //비밀번호 성공시 opacity 스타일 제거
        $('#submit-btn').removeAttr('style');
        $('#submit-btn').prop('disabled', false);
    }else {
        $('#account-pw-input').val('');
        swal({
            title: "비밀번호 인증 실패",
            // text: "비밀번호 인증 성공",
            icon: "warning",
        });
    }
}

function closeAccount() {
    var accountNumber = $('#account-number').val();
    var totalAmount = Number(accountData.amountSum) + Number(accountData.accountBal);
    // accountId가 비어있지 않은지 확인
    if (accountNumber) {
        console.log("accountClose Start")
        $.ajax({
            url: '/api/employee/close-trade',
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 전송
            data: JSON.stringify({accId: accountNumber, amount: accountData.totalPayment, status: "CLS", description:"계좌해지", balance:0, tradeType:"CLOSE"}), // JSON으로 변환하여 전송
            success: function (response) {
                swal({
                    title: "해지 성공",
                    // text: "비밀번호 인증 성공",
                    icon: "success",
                });
                const registrationDate = new Date(accountData.amountDate);
                const now = new Date();
                const totalDays = Math.floor((now - registrationDate) / 1000 / 60 / 60 / 24);
                const totalPayment = accountData.accountBal + accountData.amountSum;
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
