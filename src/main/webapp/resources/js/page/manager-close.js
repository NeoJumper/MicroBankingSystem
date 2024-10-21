$(document).ready(function() {
    registerClickEventOfEmpCloseBtn();
    handleEmpCloseBtnStatus();
    handleCurrentBusinessDay();
});

function registerClickEventOfEmpCloseBtn(){
    $("#manager-business-day-close-btn").click(function() {
        closeBusinessDayOfManager();
    });
}


function closeBusinessDayOfManager(){
    let vaultCash = calculateVaultCash();

    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({ vaultCash: vaultCash }),
        success: function(response) {
            console.log('성공');
            swal({
                title: "마감 완료",
                text: "금일 영업 마감이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            });
            $("#manager-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
            $("#manager-business-day-close-btn").prop("disabled", true);
            $("#manager-business-day-close-btn").text("마감 완료");
            $('#business-day-status > span').text("CLOSED");

            let todayClosingAmount = calculateVaultCash();
            fillEmpVaultCash(todayClosingAmount);
        },
        error: function(xhr, status, error) {
            swal({
                title: "마감 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            })
        }
    });
}

/**
 *
 */
function handleEmpCloseBtnStatus(){

    const statuses = [];
    $('.branch-close-employee-status').each(function() {
        const statusText = $(this).text().trim();
        if (statusText === 'CLOSED') {
            statuses.push(statusText);
        }
    });

    // 모든 status 값이 CLOSED인 경우
    const totalStatuses = $('.branch-close-employee-data').length;
    const totalClosedStatuses = statuses.length;


    if (totalStatuses === totalClosedStatuses) {
        $('#manager-business-day-close-btn')
            .addClass('basic-btn')
            .text("지점 마감");
    } else {
        $('#manager-business-day-close-btn')
            .addClass('closed-btn')
            .prop("disabled", true)
            .text("마감 대기");
    }
}

function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/common/current-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            currentBusinessDay = response.businessDate.substring(0, 10);
            $('#current-business-day').val(currentBusinessDay);
            if(response.status === "CLOSED")
            {
                $("#manager-business-day-close-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#manager-business-day-close-btn").prop("disabled", true);
                $("#manager-business-day-close-btn").text("마감 완료");
            }

        },
        error: function(xhr, status, error) {

        }
    });
}

function calculateVaultCash(){
    // 전일자 현금 잔액
    const prevCashBalance = parseFloat($('#manager-close-prev-cash-balance').val()) || 0;
    // 현금 입금액
    const cashDeposit = parseFloat($('#manager-close-total-deposit').val()) || 0;
    // 현금 출금액
    const cashWithdrawal = parseFloat($('#manager-close-total-withdrawal').val()) || 0;

    // 금일 마감 금액 계산
    console.log(prevCashBalance);
    console.log(cashDeposit);
    console.log(cashWithdrawal);
    console.log(prevCashBalance - (cashDeposit + cashWithdrawal));
    return prevCashBalance - (cashDeposit + cashWithdrawal);
}

function fillEmpVaultCash(todayClosingAmount) {

    // 금일 마감 금액 입력란에 값 설정
    $('#manager-close-vault-cash').val(todayClosingAmount);
}