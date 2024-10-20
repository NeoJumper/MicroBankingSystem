$(document).ready(function () {


    // 총이율 입력 함수
    InputChangeOfTotalInterest();

    // 계좌 개설 함수
    accountOpen();


    // 입력 금액 포맷 변경
    handleKRWFormat();

    // 이체금액 text 변경
    handleTransferLimitText();


    $('input[name="major-category"]').change(function() {
        // span의 내용을 변경합니다.
        handleTransferLimitText();
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

// modal 내용 지우기
function clearCustomerSearchModal() {
    $('#customerIdText').val('');
    $('#customerName').val('');
    $('#customerBirth').val('');
    $('#customerPhone').val('');
    $('#searchQuery').val('');
    $('#customerSearch').prop('selectedIndex', 0);
    $('#customer-info').empty();
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
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5억 원');
            $('#daily-limit-input').val('500,000,000');
        }
        else if(selectedCustomerSecurityLevel === "2등급")
        {
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5백만 원');
            $('#per-trade-limit-input').val('5,000,000');
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 1천만 원');
            $('#daily-limit-input').val('10,000,000');
        }

    }
    else if(selectedAccountType === "CORPORATION"){ // 법인 계좌
        if(selectedCustomerSecurityLevel === "1등급"){
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 10억 원');
            $('#per-trade-limit-input').val('1,000,000,000');
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 50억 원');
            $('#daily-limit-input').val('5,000,000,000');

        }
        else if(selectedCustomerSecurityLevel === "2등급")
        {
            $('#per-trade-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 1억 원');
            $('#per-trade-limit-input').val('100,000,000');
            $('#daily-limit-input').siblings('span').html('&nbsp;&nbsp; 최대 금액 : 5억 원');
            $('#daily-limit-input').val('500,000,000');
        }
    }
}
function removeCommas(numberString) {
    return numberString.replace(/,/g, '');
}