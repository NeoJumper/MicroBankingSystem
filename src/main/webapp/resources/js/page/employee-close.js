$(document).ready(function() {

    $("#trade-list-detail-btn").click(function() {
        // tradeDetails와 tradeDetailsContent의 표시 여부를 토글
        $("#tradeDetails, #tradeDetailsContent").toggle();
    });
    handleAuthDataOfEmployeeClosePage();
    registerClickEventOfEmpCloseBtn();

});

function registerClickEventOfEmpCloseBtn(){
    $("#employee-business-day-close-btn").click(function() {
        closeBusinessDayOfEmployee();
    });
}


function closeBusinessDayOfEmployee(){

    isValidRequest = checkInvalidCashTrade();
    if(!isValidRequest){
        swal({
            title: "마감 실패",
            text: "현금 입출금액과 거래내역의 현금 입출금액이 일치하지 않습니다.",
            icon: "error",
            button: "닫기",
        })
        return;
    }



    let vaultCash = calculateVaultCash();

    $.ajax({
        url: '/api/employee/business-day-close',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({ vaultCash: vaultCash }),
        success: function(response) {
            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#employee-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
            $("#employee-business-day-close-btn").prop("disabled", true);
            $("#employee-business-day-close-btn").text("마감 완료");

            let todayClosingAmount = calculateVaultCash();
            fillEmpVaultCash(todayClosingAmount);
        },
        error: function(xhr, status, error) {
            swal({
                title: "마감 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            });
        }
    });
}

function handleAuthDataOfEmployeeClosePage(){
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function(authData) {
            // 성공 시 처리할 로직 작성
            console.log(authData);
            $('#employee-close-page-user-branch-name').text(authData.branchName);
            $('#employee-close-page-user-name').text(authData.name + '님');
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function calculateVaultCash(){
    // 전일자 현금 잔액
    const prevCashBalance = parseFloat($('.emp-close-prev-cash-balance').val()) || 0;
    // 현금 입금액
    const cashDeposit = parseFloat($('.emp-close-cash-deposit').val()) || 0;
    // 현금 출금액
    const cashWithdrawal = parseFloat($('.emp-close-cash-withdrawal').val()) || 0;

    // 금일 마감 금액 계산
    return prevCashBalance - (cashDeposit + cashWithdrawal);
}

function fillEmpVaultCash(todayClosingAmount) {

    // 금일 마감 금액 입력란에 값 설정
    $('.emp-close-vault-cash').val(todayClosingAmount);
}

function checkInvalidCashTrade() {
    // 현금 입금액과 거래내역 현금 입금액 비교
    const cashDeposit = parseFloat($('.emp-close-cash-deposit').val()) || 0;
    const tradeListDeposit = parseFloat($('.emp-close-trade-list-deposit').val()) || 0;

    // 현금 출금액과 거래내역 현금 출금액 비교
    const cashWithdrawal = parseFloat($('.emp-close-cash-withdrawal').val()) || 0;
    const tradeListWithdrawal = parseFloat($('.emp-close-trade-list-withdrawal').val()) || 0;

    // 입금액과 출금액의 불일치 확인
    const isDepositMismatch = cashDeposit !== tradeListDeposit;
    const isWithdrawalMismatch = cashWithdrawal !== tradeListWithdrawal;

    // 결과 반환 또는 알림
    if (isDepositMismatch || isWithdrawalMismatch) {
        return false;
    } else {
        return true;
    }
}