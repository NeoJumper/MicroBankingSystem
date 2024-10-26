$(document).ready(function () {
    handleAuthDataOfEmployeeClosePage();
    registerClickEventOfEmpCloseBtn();


    document.addEventListener('DOMContentLoaded', function () {
        const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
        const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
    });

});

function registerClickEventOfEmpCloseBtn() {
    $("#employee-business-day-close-btn").click(function () {
        closeBusinessDayOfEmployee();
    });
}


function closeBusinessDayOfEmployee() {

    isValidRequest = checkInvalidCashTrade();

    if (!isValidRequest) {
        swal({
            title: "마감 실패",
            text: "현금 입출금액과 거래내역의 현금 입출금액이 일치하지 않습니다.",
            icon: "error",
            button: "닫기",
        })
        return;
    }


    let vaultCashValue = $('#emp-close-today-vault-cash').val();
    // let vaultCash = calculateVaultCash();

    // 쉼표 제거
    vaultCashValue = vaultCashValue.replace(/,/g, '');
    // 숫자 변환
    const vaultCash = Number(vaultCashValue);

    console.log("vaultCash", vaultCash);

    $.ajax({
        url: '/api/employee/business-day-close',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({vaultCash: vaultCash}),
        success: function () {
            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#employee-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
            $("#employee-business-day-close-btn").prop("disabled", true);
            $("#employee-business-day-close-btn").text("마감 완료");

            // let todayClosingAmount = calculateVaultCash();
            // fillEmpVaultCash(todayClosingAmount);
        },
        error: function (xhr, status, error) {
            swal({
                title: "마감 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            });
        }
    });
}

function handleAuthDataOfEmployeeClosePage() {
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function (authData) {
            // 성공 시 처리할 로직 작성
            console.log(authData);
            $('#employee-close-page-user-branch-name').text(authData.branchName);
            $('#employee-close-page-user-name').text(authData.name + '님');
        },
        error: function (xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function calculateVaultCash() {
    // 전일자 현금 잔액
    const prevCashBalance = parseFloat($('.emp-close-prev-cash-balance').val()) || 0;
    // 현금 입금액
    const cashDeposit = parseFloat($('.emp-close-cash-deposit').val()) || 0;
    // 현금 출금액
    const cashWithdrawal = parseFloat($('.emp-close-cash-withdrawal').val()) || 0;

    // 금일 마감 금액 계산
    return prevCashBalance - (cashDeposit + cashWithdrawal);
}


function checkInvalidCashTrade() {

    // 현금 입금액
    let empCloseCashDeposit = Number($('.emp-close-cash-deposit').val().replace(/,/g, ''));

// 현금 인수액
    let empCloseCashExchangeDeposit = Number($('.emp-close-cash-exchange-deposit').val().replace(/,/g, ''));

// 거래내역 현금 입금액
    let empCloseTradeListDeposit = Number($('.emp-close-trade-list-deposit').val().replace(/,/g, ''));

// 현금 출금액
    let empCloseCashWithdrawal = Number($('.emp-close-cash-withdrawal').val().replace(/,/g, ''));

// 현금 인도액
    let empCloseCashExchangeWithdrawal = Number($('.emp-close-cash-exchange-withdrawal').val().replace(/,/g, ''));

// 거래내역 현금 출금액
    let empCloseTradeListWithdrawal = Number($('.emp-close-trade-list-withdrawal').val().replace(/,/g, ''));


    // 결과 확인 (콘솔에 출력)
    console.log("현금 입금액:", empCloseCashDeposit);
    console.log("현금 인수액:", empCloseCashExchangeDeposit);
    console.log("거래내역 현금 입금액:", empCloseTradeListDeposit);
    console.log("현금 출금액:", empCloseCashWithdrawal);
    console.log("현금 인도액:", empCloseCashExchangeWithdrawal);
    console.log("거래내역 현금 출금액:", empCloseTradeListWithdrawal);

    // 입금액과 출금액의 불일치 확인
    const isDepositMismatch = empCloseCashDeposit !== empCloseCashExchangeDeposit + empCloseTradeListDeposit;
    const isWithdrawalMismatch = empCloseCashWithdrawal !== empCloseCashExchangeWithdrawal + empCloseTradeListWithdrawal;

    // 결과 반환 또는 알림
    if (isDepositMismatch || isWithdrawalMismatch) {
        return false;
    } else {
        return true;
    }
}