$(document).ready(function () {
    isClosed();
    // 담당자/ 담당 지점(id)/영업일 설정 및 지정
    setCurrentData();

    // 자동이체 여부 선택
    checkAutoTransfer();

    // 계좌정보
    searchAutoTransferAccountData();

    // flatpickr("#auto-transfer-start-date-input", {
    //     minDate: "2024-11-10",  // 최소 날짜 설정
    //     dateFormat: "Y-m-d",     // 날짜 형식 설정
    //     onChange: function(selectedDates, dateStr, instance) {
    //         console.log("선택된 날짜:", dateStr);  // 선택된 날짜 출력
    //     }
    // });

    $('#search-modal-product-select-btn').click(function () {
        selectProduct();  // 선택된 상품 정보 입력 함수 호출
        getSavingsAccountEndDate(); // 만기일자 구하는 함수 (기간 + 현재영업일)
    });
    // 상품 조회 버튼 클릭시

    // 총 이율 구하기
    $('#preferred-interest-input').on('input', function () {
        calculateTotalInterest();
    });

    // 정기적금 금액 버튼 클릭시 필드 입력함수
    clickAmountBtn();

    // 정기적금 금액 값이 변경될 때마다 자동으로 업데이트
    $('#auto-transfer-amount-input').on('input', function () {
        updateTransferAmount(); // 값 업데이트 함수 호출

        document.getElementById("yes-automatic-transfer");
        if (yesRadio.checked) {
            checkBalance();
        } else {
        }

    });

    // 출금금액 변경될 때 정기적금 금액 변경
    $('#auto-transfer-amount').on('input', function () {
        updateSavingsAmount();
        checkBalance();
    });

    // 정기적금 금액 초기값 설정
    updateTransferAmount();

    // 예상 만기이자 함수
    $('#total-interest-input').on('input', function () {
        // 총 이자 입력 필드에 값이 있는지 확인
        if ($(this).val().trim() !== '') {
            calculateExpectedInterest();
        } else {
            // 만약 값이 비어있다면 결과 필드도 비워줌
            $('#expected-maturity-interest-input').val('');

        }
    });

    // 예상 만기이자 함수
    $('#auto-transfer-amount-input, #product-period-input').on('input', function () {
        // 각 필드에 값이 있을 때마다 이자 계산
        if ($('#total-interest-input').val().trim() !== '') {
            calculateExpectedInterest();
        }
    });


    // 적금 자동이체 출금계좌 선택시 필드 입력 함수
    $('#search-modal-select-transfer-account-btn').click(function () {
        selectTransferAccount();
    });


    // 출금계좌 인증하기
    $('#check-transfer-account-btn').click(function () {
        checkTransferPassword();
    });


    // 계좌 잔액이 변경될 때마다도 확인
    $('#account-balance').on('input', function () {
        checkBalance();  // 잔액 부족 확인 함수 호출
    });

    // 계좌 생성
    $('#savings-account-create-btn').click(function () {
        createSavingAccount();
    });


    // 라디오 버튼과 버튼 요소 가져오기
    const yesRadio = document.getElementById("yes-automatic-transfer");
    const noRadio = document.getElementById("no-automatic-transfer");
    // 라디오 버튼에 이벤트 리스너 추가
    yesRadio.addEventListener("change", updateButtonState);
    noRadio.addEventListener("change", updateButtonState);

    // 초기 상태 설정 (초기에는 "아니오"가 선택되어 있으므로 활성화)
    updateButtonState();

    // 입력 금액 포맷 변경
  //  handleAmountInputFormat();
});
/*function handleAmountInputFormat(){
    $('#expected-maturity-interest-input').on('input', function() {
        handleAmountFormat($(this));
    });

}
function handleAmountFormat(element){

    // 현재 입력된 값을 가져옴
    let value = element.val();

    if(String(value) === ''){
        element.siblings('.krw-amount-input').val('').hide();
        value = 0;
        element.val(value);
        return;
    }
    console.log(value);


    value = parseFloat(convertNumber(value));  // 입력된 값에서 쉼표 제거 후 숫자로 변환



    element.siblings('.krw-amount-input').val(convertToKoreanNumber(parseInt(value)) + '원').show();

    // 숫자를 한국 원화 형식으로 변환
    value = String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    console.log(value);
    // 변환된 값을 다시 input에 설정
    element.val(value);

}*/


// 잔액 부족 알림 띄우기
function checkBalance() {
    const transferAmount = parseInt($('#auto-transfer-amount-input').val()) || 0; // 자동이체 금액
    const accountBalance = parseInt($('#account-balance').text().replace(/,/g, '')) || 0; // 계좌 잔액 (콤마 제거 후 정수 변환)
    const specifiedTransferAmount = parseInt($('#auto-transfer-amount').val().replace(/원/g, '').replace(/,/g, '')) || 0; // 지정된 이체 금액 (콤마 및 '원' 제거 후 정수 변환)

    // 잔액 부족 여부 확인
    if (transferAmount > accountBalance || specifiedTransferAmount > accountBalance) {
        $('#balance-warning').show();  // 잔액 부족 경고 표시
        $('#savings-account-create-btn').addClass('disabled'); // 비활성화 클래스 추가
    } else {
        $('#balance-warning').hide();  // 잔액 부족 경고 숨기기
        $('#savings-account-create-btn').removeClass('disabled'); // 비활성화 클래스 제거
    }
}

// 적금금액 입력시 이체 금액에 자동입력 기능 함수
function updateTransferAmount() {
    const amountInput = $('#auto-transfer-amount-input'); // 입력 필드 선택
    const transferAmountDisplay = $('#auto-transfer-amount'); // 이체 금액 표시 필드 선택

    // 적금 금액 입력 필드의 값 가져오기
    const amount = amountInput.val();

    // 이체 금액 필드에 값 설정
    transferAmountDisplay.val(amount ? `${amount}원` : '0원');
}

function updateSavingsAmount() {
    const transferAmountDisplay = $('#auto-transfer-amount'); // 이체 금액 표시 필드 선택
    const amountInput = $('#auto-transfer-amount-input'); // 입력 필드 선택

    // 이체 금액 필드의 값에서 '원' 제거하고 숫자만 추출
    const amount = transferAmountDisplay.val().replace(/원/g, '').trim();

    // 입력 필드에 값 설정
    amountInput.val(amount ? amount : '');
}

// 적금 금액 버튼 클릭시 이벤트 함수
function clickAmountBtn() {
    // 버튼 클릭 시 입력 필드에 금액을 입력하는 함수
    document.querySelectorAll('.amount-btn').forEach(button => {
        button.addEventListener('click', function () {
            const amount = this.getAttribute('data-amount'); // 버튼의 data-amount 속성에서 금액 가져오기
            document.getElementById('auto-transfer-amount-input').value = amount; // 입력 필드에 금액 설정
            document.getElementById('auto-transfer-amount').value = amount; // 입력 필드에 금액 설정
            handleAmountFormat($('#auto-transfer-amount-input'));
        });
    });
}

// 적금이율 + 우대이율 = 총 이자율 계산
function calculateTotalInterest() {
    const productInterest = parseFloat($('#savings-interest-input').val()) || 0; // 적금이율
    const preferredInterest = parseFloat($('#preferred-interest-input').val()) || 0; // 우대이율

    const totalInterest = productInterest + preferredInterest;
    $('#total-interest-input').val(totalInterest.toFixed(2)); // 소수점 2자리까지 출력
}

// 콤마를 추가하는 함수
function addCommas(number) {
    return number.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



// 만기 예상 이자 계산 함수
function calculateExpectedInterest() {
    const principal = parseFloat($('#auto-transfer-amount-input').val());
    const annualRate = parseFloat($('#total-interest-input').val());
    const months = parseInt($('#product-period-input').val().replace('월', '').trim(), 10); // '월' 제거

    console.log(principal+"principal"+annualRate+"annualRate"+months+"months");

    // 만기 계산
    const interest = calculateMaturity(principal, annualRate, months);

    const totalAmount  = interest + months*principal;

    // 이자와 총 금액 설정
    $('#expected-maturity-interest-input').val(addCommas(interest.toFixed(0))).css('color', 'green'); // 이자 입력

    $('#expected-maturity-totalAmount-input').val(addCommas(totalAmount.toFixed(0))).css('color', 'blue'); // 이자 입력


}


function removeCommas(numberString) {
    return numberString.replace(/,/g, '');
}
// 만기 시 이자를 계산하고 총 금액을 반환하는 함수
function calculateMaturity(principal, finalInterestRate, months) {
    let totalInterest = 0;

    // 첫 번째 달: 연이율 * 12/12
    let firstMonthInterestRate = finalInterestRate * (12 / 12);
    let firstMonthInterest = principal * (firstMonthInterestRate / 100);
    totalInterest += firstMonthInterest;

    // 두 번째 달부터는 연이율 * 남은개월/12
    for (let i = 2; i <= months; i++) {
        let remainingMonths = months - i + 1; // 남은 개월 수 (12 -> 11 -> 10 ...)
        let monthlyInterestRate = finalInterestRate * (remainingMonths / 12);

        // 해당 달 이자 계산
        let interestForMonth = principal * (monthlyInterestRate / 100);
        totalInterest += interestForMonth;
    }

    // 소수점 4자리로 반올림 (round down equivalent)
    return parseFloat(totalInterest.toFixed(4));
}


function setCurrentData() {

    $.ajax({
        url: "/api/common/current-data",
        type: "GET",
        success: function (data) {
            $('#emp-name-input').val(data.employeeName);
            $('#emp-id-hidden').val(data.employeeId);

            $('#branch-name-input').val(data.branchName);
            $('#branch-id-hidden').val(data.branchId);


            var formattedDate= new Date(data.currentBusinessDate).toLocaleDateString('ko-KR');
            $('#savings-account-start-date-input').val(formattedDate);


        }, error: function (data) {
            console.log(data);
        }
    });
}


function checkAutoTransfer() {

    const yesRadio = document.getElementById("yes-automatic-transfer");
    const noRadio = document.getElementById("no-automatic-transfer");
    const additionalInfo = document.getElementById("automatic-transfer-info-div");
    const createAccBtn = document.getElementById("savings-account-create-btn");

    // 추가 정보 표시/숨기기 함수
    function checkAutoTransfer() {
        additionalInfo.style.display = yesRadio.checked ? "block" : "none";
    }

    // 계좌 생성 활성화 버튼
    function activeOfCreatAccBtn() {
        if (yesRadio.checked) {
            createAccBtn.classList.add('disabled'); // 'disabled' 클래스 제거 (활성화)
        } else {
            createAccBtn.classList.remove('disabled'); // 'disabled' 클래스 추가 (비활성화)
        }
    }


    yesRadio.addEventListener("change", checkAutoTransfer);
    noRadio.addEventListener("change", checkAutoTransfer);


    // 라디오 버튼에 이벤트 리스너 추가
    yesRadio.addEventListener("change", function () {
        checkAutoTransfer();
        activeOfCreatAccBtn(); // 버튼 활성화/비활성화
    });

    noRadio.addEventListener("change", function () {
        checkAutoTransfer();
        activeOfCreatAccBtn(); // 버튼 활성화/비활성화
    });

    checkAutoTransfer();
    activeOfCreatAccBtn();


}

function selectProduct() {

    const selectedProduct = $('input[name="selected-product-id"]:checked');

    if (selectedProduct.length > 0) {
        // 선택된 행 (tr) 찾기
        const selectedRow = selectedProduct.closest('tr');

        // 각 td에서 데이터를 가져와서 입력 필드에 설정
        const productId = selectedRow.find('td:nth-child(2)').text().trim();  // productId
        const productName = selectedRow.find('td:nth-child(3)').text().trim();  // productName
        const productPeriod = selectedRow.find('td:nth-child(4)').text().trim();  // productPeriod
        const productInterestRate = selectedRow.find('td:nth-child(5)').text().trim();  // productInterestRate
        const productTaxRate = selectedRow.find('td:nth-child(6)').text().trim();  // productTaxRate

        // 값을 입력 필드에 넣음
        $('#product-id-input').val(productId);
        $('#product-name-input').val(productName);
        $('#product-period-input').val(productPeriod);
        $('#savings-interest-input').val(productInterestRate);
        $('#savings-tax-input').val(productTaxRate);


    } else {
        alert('상품을 선택해 주세요.');
    }
    $('#search-modal-product').modal('hide');
}

function getSavingsAccountEndDate() {
    var selectedMonths = parseInt($('#product-period-input').val());  // 선택한 개월 수를 숫자로 변환

    // 기존 시작 날짜 가져오기 (YYYY-MM-DD 형식)
    var formattedDate = $('#savings-account-start-date-input').val();
    var startDate = new Date(formattedDate);

    // 개월 수만큼 더하기 (추가하는 개월 수)
    var newDate = addMonthsToDate(startDate, selectedMonths);

    // 'YYYY-MM-DD' 형식으로 변환된 날짜 설정
    $('#savings-account-end-date-input').val(formatDateToString(newDate));
}

// 개월 추가 함수
function addMonthsToDate(date, months) {
    var newDate = new Date(date);
    newDate.setMonth(newDate.getMonth() + months);
    return newDate;
}

// 날짜를 'YYYY-MM-DD' 형식으로 변환하는 함수
function formatDateToString(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function selectTransferAccount() {

    const selectedTransferAccount = $('input[name="selected-transfer-account-id"]:checked');

    if (selectedTransferAccount.length > 0) {
        // 선택된 행 (tr) 찾기
        const selectedRow = selectedTransferAccount.closest('tr');

        // 각 td에서 데이터를 가져와서 입력 필드에 설정
        const accId = selectedRow.find('td:nth-child(2)').text().trim();
        const customerName = selectedRow.find('td:nth-child(4)').text().trim();
        //const productName = selectedRow.find('td:nth-child(5)').text().trim();
        const balance = selectedRow.find('td:nth-child(6)').text().trim();


        // 값을 입력 필드에 넣음
        $('#auto-transfer-account-number').val(accId);
        $('#auto-transfer-account-customer-name').val(customerName);

        $('#account-balance').text(balance);
        $('#account-balance-label').show();

        checkBalance();

    } else {
        alert('상품을 선택해 주세요.');
    }
    $('#search-modal-transfer-account').modal('hide');
}

function checkTransferPassword() {
    var accountNumber = $('#auto-transfer-account-number').val();
    var accountPassword = $('#auto-transfer-account-password').val();

    $.ajax({
        url: '/api/employee/account-validate',
        contentType: "application/x-www-form-urlencoded",
        type: "POST",
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

            AddInfoOfAutoTransferAccount(true);


        }, error: function (error) {
            swal({
                title: "검증 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });

            AddInfoOfAutoTransferAccount(false);

            console.log("Transfer failed", error);
        }
    })

}


function AddInfoOfAutoTransferAccount(result) {
    const addTransferAccountInfo = document.getElementById("checked-transfer-account-div");
    const savingsAccountCreateBtn = document.getElementById("savings-account-create-btn"); // 버튼 요소

    if (result) {
        // result가 true일 경우 (보이게 하고 버튼 활성화)
        addTransferAccountInfo.style.display = "block"; // div 보이기
        savingsAccountCreateBtn.disabled = false; // 버튼 활성화
        savingsAccountCreateBtn.classList.remove("disabled"); // 비활성화 스타일 제거
    } else {
        // result가 false일 경우 (숨기고 버튼 비활성화)
        addTransferAccountInfo.style.display = "none"; // div 숨기기
        savingsAccountCreateBtn.disabled = true; // 버튼 비활성화
        savingsAccountCreateBtn.classList.add("disabled"); // 비활성화 스타일 추가
    }
}

// 적금 계좌 개설 함수
// function createSavingAccount() {
//
//     swal({
//         title: " 계좌 생성 성공",
//         text: "계좌가 성공적으로 개설되었습니다.",
//         icon: "success",
//         button: "닫기",
//     });
// }

function createSavingAccount() {

    const customerId = $('#customer-id-input').val();
    const productId = $('#product-id-input').val();
    const preferentialInterestRate = $('#savings-interest-input').val();
    const password = $('#savings-account-password-input').val();
    const balance = $('#auto-transfer-amount-input').val();
    const empId = $('#emp-id-hidden').val();
    const branchId = $('#branch-id-hidden').val();
    const accountType = "PRIVATE";

    $.ajax({
        type: 'POST',
        url: '/api/employee/accounts',
        contentType: 'application/json',
        data: JSON.stringify({
            branchId: branchId,
            customerId: customerId,
            registerId: empId,
            productId: productId,
            preferentialInterestRate: preferentialInterestRate.replace('%', ''),
            password: password,
            balance: balance,
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

    });//
}
function accountOpenResult(accountId) {

    $.ajax({
        url: '/api/employee/accounts/' + accountId,
        method: 'GET',
        success: function (data) {
            console.log(data);

            $('#savings-result-account-id-input').val(data.accId);
            $('#savings-result-customer-name-input').val(data.customerName);
            $('#savings-result-customer-number-input').val(data.customerId);
            $('#savings-result-phone-number-input').val(data.phoneNumber); // 필요시 추가

            $('#savings-result-product-name-input').val(data.productName);

            $('#savings-result-start-date-input').val(data.startDate.split(' ')[0]);

            $('#savings-result-branch-name-input').val(data.branchName);
            $('#savings-result-registrant-name-input').val(data.registrantName);

            $('#savings-result-product-interest-input').val(data.interestRate);
            $('#savings-result-preferred-interest-input').val(data.preferentialInterestRate);

            $('#savings-result-balance-input').val(data.balance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
            $('#savings-result-total-interest-input').val(data.totalInterestRate);

            $("#savings-result-modal").modal("show");


        },
        error: function (error) {
            $('#product-interest-input').val('error');
        }
    });
}
// 초기 상태에 맞게 버튼을 설정하는 함수
function updateButtonState() {
    const savingsAccountCreateBtn = document.getElementById("savings-account-create-btn");
    const yesRadio = document.getElementById("yes-automatic-transfer");

    if (yesRadio.checked) {
        savingsAccountCreateBtn.disabled = true;  // 버튼 비활성화
        savingsAccountCreateBtn.classList.add('disabled');  // 비활성화 스타일 추가
    } else {
        savingsAccountCreateBtn.disabled = false;  // 버튼 활성화
        savingsAccountCreateBtn.classList.remove('disabled');  // 비활성화 스타일 제거
    }
}



/*
    var savingsAccount = {
        branchId: branchId,
        customerId: customerId,
        registerId: empId,
        productId: productId,
        preferentialInterestRate: preferentialInterestRate,
        password: password,
        balance: balance,
        accountType: accountType,
        tradeType: "OPEN"
    };

    const create_date = $('#savings-account-start-date-input').val(formattedDate);

    var savingsAutoTransfer ={
        target_acc_id: branchId,
        amount: removeCommas(balance),
        auto_transfer_start_date: empId,
        auto_transfer_period: password,
        create_date: create_date
    }

    var savingsAccountTotalInfo = [savingsAccount, savingsAutoTransfer];

    $.ajax({
        type: 'POST',
        url: '/api/employee/savings-accounts',
        contentType: 'application/json',
        data: JSON.stringify(savingsAccountTotalInfo),
        success: function (accountId) {
            swal({
                title: " 계좌 생성 성공",
                text: "계좌가 성공적으로 개설되었습니다.",
                icon: "success",
                button: "닫기",
            }).then(() => {
                // swal의 닫기 버튼이 클릭된 후 실행
                alert("finish");
               // accountOpenResult(accountId); // 개설된 계좌 정보 성공 모달 호출
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

    });

*/


// 특정 날짜 설정 (예: 2024년 11월 10일)
  /*  const specificDate = new Date('2024-11-10');

    // '예' 버튼이 선택된 경우 한 달 뒤 날짜 계산
    function getNextMonthDate(date) {
        const nextMonthDate = new Date(date);
        nextMonthDate.setMonth(date.getMonth() + 1); // 한 달 뒤로 설정
        return nextMonthDate;
    }

    // 날짜 선택기 설정
    function setFlatpickr() {
        const isYesSelected = document.getElementById('yes-cash-transfer').checked; // '예' 버튼이 선택되었는지 여부
        const dateInput = document.getElementById('auto-transfer-start-date-input');

        // '예' 버튼 선택 시: specificDate의 다음 달 이후 날짜만 선택 가능
        if (isYesSelected) {
            const nextMonthDate = getNextMonthDate(specificDate);
            flatpickr(dateInput, {
                minDate: nextMonthDate.toISOString().split('T')[0], // 다음 달부터 선택 가능
                dateFormat: "Y-m-d", // 날짜 형식 (YYYY-MM-DD)
                onChange: function(selectedDates, dateStr, instance) {
                    // '예' 버튼을 선택한 경우 선택된 날짜가 한 달 뒤의 날짜 이상이어야 한다는 조건 확인
                    if (new Date(dateStr) <= nextMonthDate) {
                        alert('입력된 날짜는 ' + nextMonthDate.toISOString().split('T')[0] + ' 이후여야 합니다.');
                        dateInput.value = ''; // 잘못된 날짜 입력을 지운다
                    }
                }
            });
        }
        // '아니오' 버튼 선택 시: specificDate 이후 날짜만 선택 가능
        else {
            flatpickr(dateInput, {
                minDate: specificDate.toISOString().split('T')[0], // specificDate 이후 날짜만 선택 가능
                dateFormat: "Y-m-d", // 날짜 형식 (YYYY-MM-DD)
                onChange: function(selectedDates, dateStr, instance) {
                    // '아니오' 버튼을 선택한 경우 선택된 날짜가 specificDate 이후여야 한다는 조건 확인
                    if (new Date(dateStr) <= specificDate) {
                        alert('입력된 날짜는 ' + specificDate.toISOString().split('T')[0] + ' 이후여야 합니다.');
                        dateInput.value = ''; // 잘못된 날짜 입력을 지운다
                    }
                }
            });
        }
    }*/


