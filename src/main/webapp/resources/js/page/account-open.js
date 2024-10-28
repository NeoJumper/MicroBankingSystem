var perTradeLimit = 0;
var dailyLimit = 0;

$(document).ready(function () {
    isClosed();

    // 총이율 입력 함수
    InputChangeOfTotalInterest();

    // 계좌 개설 함수
    accountOpen();


    // 입력 금액 포맷 변경
    handleKRWFormat();


    handlePerTradeLimit();
    handleDailyLimit()

    $('input[name="major-category"]').change(function() {
        resetFormData();
    });
});


// 기준이율 + 우대이율 = 총 이자율 계산
function calculateTotalInterest() {
    const productInterest = parseFloat($('#product-interest-input').val()) || 0; // 기준이율
    const preferredInterest = parseFloat($('#preferred-interest-input').val()) || 0; // 우대이율

    const totalInterest = productInterest + preferredInterest;
    $('#total-interest-input').val(totalInterest.toFixed(2)); // 소수점 2자리까지 출력
}

function InputChangeOfTotalInterest() {
    $('#preferred-interest-input').on('input', function () {
        calculateTotalInterest();
    });
}

// 계좌 생성 함수
function accountOpen() {

    $('#account-create-btn').on("click", function () {


        const customerId = $('#customer-id-input').val();
        const productId = $('#product-id-hidden-input').val();
        const dailyLimit = $('#daily-limit-input').val();
        const perTradeLimit = $('#per-trade-limit-input').val();
        //const startDate = $('startDate').val();
        const preferentialInterestRate = $('#preferred-interest-input').val();
        const password = $('#password-input').val();
        const balance = $('#init-balance-input').val();
        const accountType = $('input[name="major-category"]:checked').val();
        const empId = $('#emp-id-hidden-input').val();
        const branchId = $('#branch-id-hidden-input').val();

        $.ajax({
            type: 'POST',
            url: '/api/employee/accounts',
            contentType: 'application/json',
            data: JSON.stringify({
                branchId: branchId,
                customerId: customerId,
                registerId: empId,
                productId: productId,
                preferentialInterestRate: preferentialInterestRate,
                password: password,
                dailyLimit: removeCommas(dailyLimit),
                perTradeLimit: removeCommas(perTradeLimit),
                balance: removeCommas(balance),
                accountType: accountType,
                tradeType: "OPEN"
            }),
            success: function (accountId) {
                swal({
                    title: " 계좌 생성 성공",
                    text: "계좌가 성공적으로 개설되었습니다.",
                    icon: "success",
                    button: "닫기",
                }).then(() => {
                    // swal의 닫기 버튼이 클릭된 후 실행
                    accountOpenResult(accountId); // 개설된 계좌 정보 성공 모달 호출
                });

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
            }

        });// end
    });
}

// 계좌 개설 완료 모달 호출 함수
function accountOpenResult(accountId) {

    $.ajax({
        url: '/api/employee/accounts/' + accountId,
        method: 'GET',
        success: function (data) {
            console.log(data);

            $('#result-modal-account-id-input').val(data.accId);
            $('#result-modal-customer-name-input').val(data.customerName)
            $('#result-modal-customer-number-input').val(data.customerId);
            $('#result-modal-phone-number-input').val(data.phoneNumber);
            $('#result-modal-product-name-input').val(data.productName);

            $('#result-modal-start-date-input').val(data.startDate.split(' ')[0]);


            $('#result-modal-branch-name-input').val(data.branchName);
            $('#result-modal-registrant-name-input').val(data.registrantName);

            $('#result-modal-product-interest-input').val(data.interestRate);
            $('#result-modal-preferred-interest-input').val(data.preferentialInterestRate);

            $('#result-modal-balance-input').val(data.balance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
            $('#result-modal-per-trade-limit-input').val(data.perTradeLimit.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
            $('#result-modal-daily-limit-input').val(data.dailyLimit.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));

            $('#result-modal-total-interest-input').val(data.totalInterestRate);

            $("#result-modal-open-account").modal("show");


        },
        error: function (error) {
            $('#product-interest-input').val('error');
        }
    });
}

function handleKRWFormat(){
    $('.balance-input').on('input', function() {
        // 현재 입력된 값을 가져옴
        let value = $(this).val();

        // 숫자 이외의 문자를 제거
        value = value.replace(/[^0-9]/g, '');

        // 숫자를 한국 원화 형식으로 변환
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        // 변환된 값을 다시 input에 설정
        $(this).val(value);
    });
}

function handleTransferLimitText() {
    var selectedAccountType =  $('input[name="major-category"]:checked').val();
    var selectedCustomerSecurityLevel = $('#customer-security-level-input').val();

    //console.log(selectedAccountType);
    //console.log(selectedCustomerSecurityLevel);
    if(selectedAccountType === "PRIVATE")
    {
        if(selectedCustomerSecurityLevel === "1등급"){
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 1억 원');
            $('#per-trade-limit-input').val('100,000,000');
            perTradeLimit = 100000000;
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5억 원');
            $('#daily-limit-input').val('500,000,000');
        }
        else if(selectedCustomerSecurityLevel === "2등급")
        {
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5백만 원');
            $('#per-trade-limit-input').val('5,000,000');
            perTradeLimit = 5000000;
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 1천만 원');
            $('#daily-limit-input').val('10,000,000');
            dailyLimit = 10000000;
        }

    }
    else if(selectedAccountType === "CORPORATION"){ // 법인 계좌
        if(selectedCustomerSecurityLevel === "1등급"){
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 10억 원');
            $('#per-trade-limit-input').val('1,000,000,000');
            perTradeLimit = 1000000000;
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 50억 원');
            $('#daily-limit-input').val('5,000,000,000');
            dailyLimit = 5000000000;

        }
        else if(selectedCustomerSecurityLevel === "2등급")
        {
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 1억 원');
            $('#per-trade-limit-input').val('100,000,000');
            perTradeLimit = 100000000;
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5억 원');
            $('#daily-limit-input').val('500,000,000');
            dailyLimit = 500000000;
        }
    }
}
function removeCommas(numberString) {
    return numberString.replace(/,/g, '');
}

function resetFormData() {

    $('#customer-id-input').val('');              // 고객번호 비우기
    $('#emp-id-hidden-input').val('');            // 숨겨진 직원 ID 비우기
    $('#branch-id-hidden-input').val('');         // 숨겨진 지점 ID 비우기
    $('#product-id-hidden-input').val('');        // 숨겨진 상품 ID 비우기
    $('#customer-security-level-input').val('');        // 숨겨진 상품 ID 비우기
    $('#customer-name-input').val('');        // 숨겨진 상품 ID 비우기


    $('#deposit-product-name-input').val('');   // 상품명 비우기
    $('#product-interest-input').val('');       // 기준이율 비우기
    $('#preferred-interest-input').val('');     // 우대이율 비우기
    $('#total-interest-input').val('');         // 총 이자율 비우기
    $('#init-balance-input').val('');           // 초기 예치금 비우기
    $('#password-input').val('');               // 계좌 비밀번호 비우기
    $('#per-trade-limit-input').val('');        // 1회 이체한도 비우기
    $('#daily-limit-input').val('');            // 1일 이체한도 비우기

    $('.max-amount-span').text('');             // 내부 텍스트 비우기

    $('#account-open-info').hide();


}

function handlePerTradeLimit() {
    $(document).on('input', '#per-trade-limit-input', function () {
        $(this).val(comma(convertNumber(($(this).val()))));

        var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환



        if (inputAmount > perTradeLimit) {
            $('#over-per-trade-limit').text("최대 이체 한도를 초과했습니다.");
            $(this).val(comma(perTradeLimit));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-per-trade-limit').text("");  // 경고 메시지 제거
        }
    });
}

function handleDailyLimit() {
    $(document).on('input', '#daily-limit-input', function () {
        $(this).val(comma(convertNumber(($(this).val()))));

        var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환



        if (inputAmount > dailyLimit) {
            $('#over-daily-limit').text("최대 이체 한도를 초과했습니다.");
            $(this).val(comma(dailyLimit));  // 입력된 값을 계좌 잔액으로 제한
        } else {
            $('#over-daily-limit').text("");  // 경고 메시지 제거
        }
    });
}


function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

function convertNumber(str) {
    str = String(str.replace(/[^\d]+/g, ''));  // 숫자를 제외한 모든 문자 제거


    if (/^0{2,}/.test(str)) {
        // 두 번 이상 연속된 0을 잘라내고 나머지 부분 반환
        return str.replace(/^0{2,}/, '0');
    }
    return str;
}