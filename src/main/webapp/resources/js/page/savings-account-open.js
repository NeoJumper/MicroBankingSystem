$(document).ready(function () {
    // 담당자/ 담당 지점(id)/영업일 설정 및 지정
    setCurrentData();

    // 자동이체 여부 선택
    checkAutoTransfer();

    // 계좌정보
    searchAutoTransferAccountData();


    $('#search-modal-product-select-btn').click(function() {
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
        checkBalance();
    });
    
    // 출금금액 변경될 때 정기적금 금액 변경
    $('#auto-transfer-amount').on('input', function(){
        updateSavingsAmount();
        checkBalance();
    });
    
    // 정기적금 금액 초기값 설정
    updateTransferAmount();


    // 적금 자동이체 출금계좌 선택시 필드 입력 함수
    $('#search-modal-select-transfer-account-btn').click(function(){
        selectTransferAccount();
    });


    // 출금계좌 인증하기
    $('#check-transfer-account-btn').click(function(){
        checkTransferPassword();
    });

    // 계좌 잔액이 변경될 때마다도 확인
    $('#account-balance').on('input', function () {
        checkBalance();  // 잔액 부족 확인 함수 호출
    });

    // 계좌 생성
    $('#account-')
});

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
function clickAmountBtn(){
    // 버튼 클릭 시 입력 필드에 금액을 입력하는 함수
    document.querySelectorAll('.amount-btn').forEach(button => {
        button.addEventListener('click', function() {
            const amount = this.getAttribute('data-amount'); // 버튼의 data-amount 속성에서 금액 가져오기
            document.getElementById('auto-transfer-amount-input').value = amount; // 입력 필드에 금액 설정
            document.getElementById('auto-transfer-amount').value = amount; // 입력 필드에 금액 설정

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



function setCurrentData(){

    $.ajax({
        url: "/api/common/current-data",
        type: "GET",
        success: function(data) {
            $('#emp-name-input').val(data.employeeName);
            $('#emp-id-hidden').val(data.employeeId);

            $('#branch-name-input').val(data.branchName);
            $('#branch-id-hidden').val(data.branchId);

            var formattedDate = data.currentBusinessDate.substring(0, 10);
            $('#savings-account-start-date-input').val(formattedDate);


        }, error: function(data) {
            console.log(data);
        }
    });
}


function checkAutoTransfer(){

    const yesRadio = document.getElementById("yes-automatic-transfer");
    const noRadio = document.getElementById("no-automatic-transfer");
    const additionalInfo = document.getElementById("automatic-transfer-info-div");

    yesRadio.addEventListener("change", checkAutoTransfer);
    noRadio.addEventListener("change", checkAutoTransfer);

    // 추가 정보 표시/숨기기 함수
    function checkAutoTransfer() {
        additionalInfo.style.display = yesRadio.checked ? "block" : "none";
    }

}

function selectProduct(){

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

function getSavingsAccountEndDate(){
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

function selectTransferAccount(){

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

    $.ajax( {
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

            AddInfoOfAutoTransferAccount(false);

            console.log("Transfer failed", error);
        }
    })

}



function AddInfoOfAutoTransferAccount(result){
    const addTransferAccountInfo = document.getElementById("checked-transfer-account-div");

    addTransferAccountInfo.style.display = result ? "block" : "none";
}


