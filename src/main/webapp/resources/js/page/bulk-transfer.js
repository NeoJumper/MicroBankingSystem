let selectedEmpId = null;
var securityLevel = '';
let employeeDataForUpload = [];
let validPassword = "";


document.addEventListener("DOMContentLoaded", function () {
    isClosed();
    handleBusinessDayDateInput();
    userNameInput();

    // 이벤트 핸들러 초기화
    initializeEventHandlers();
});


function initializeEventHandlers() {

    // 모달 내 계좌선택 버튼
    $('#search-modal-select-account-btn').click(selectAccount);

    // 비고란 문구 선택
    $('input[name="salaryType"]').change(handleSalaryTypeChange);

    // 계좌 비밀번호 확인
    $('#input-confirm').click(checkAccountId);

    // 파일등록 버튼
    $('#uploadEmployeeBtn').click(() => showModal('uploadEmployeeModal'));

    // 개별추가 버튼
    $('#uploadIndividualEmployeeBtn').click(() => showModal('uploadIndividualEmployeeModal'));

    // 개별추가 - 추가
    $('#uploadIndivisualEmployeePreviewBtn').click(uploadIndividualEmployee);

    // 개별추가 모달 금액입력(숫자) -> 한글로 채움
    $('#transferAmountModal').on('input', handleTransferAmountInput);

    // 엑셀 업로드
    $('#uploadEmployeePreviewBtnOfTable').click(uploadEmployeePreview);

    // 이체하기
    $('input[value="이체실행"]').click(transferExecution);

    // 계좌 유효성 검증
    $('input[value="예금주 확인"]').click(validationExecution);

    // 검색어로 조회하기
    $('#searchInput').on('input', filterEmployeeData);

    // 초기화
    $('input[value="초기화"]').click(initExecution);

    // 이체한도 툴팁 마우스 호버 이벤트 관리
    $('#select-transfer-limit').hover(
        function() {
            // 마우스가 올라갔을 때
            $('#select-transfer-limit-tooltip').css('opacity', '1');
        },
        function() {
            // 마우스가 벗어났을 때
            $('#select-transfer-limit-tooltip').css('opacity', '0');
        }
    );

    // 이체하기 버튼 클릭 시
    $('#reserve-time-select-div').css({ // 기본설정
        height: 0,
        transition: 'height 0.5s ease'
    });

    // 라디오 버튼이 변경될 때 이벤트 처리
    $('input[name="scheduled-status"]').on('change', function() {
        if ($('#scheduled-transfer-btn').is(':checked')) {
            // 예약 이체가 체크됐을 때 height를 자동으로 변경하여 자연스럽게 보여줌
            $('#reserve-time-select-div').css({
                height: $('#reserve-time-select-div')[0].scrollHeight + 'px'
            });
        } else {
            // 즉시 이체가 체크됐을 때 height를 0으로 변경하여 숨김
            $('#reserve-time-select-div').css({
                height: 0
            });
        }
    });

    // 파일 선택 시 파일 이름을 표시
    $('#excelInput').on('change', function() {
        const fileName = this.files[0] ? this.files[0].name : '선택된 파일이 없음';
        $('#fileName').text(fileName).show();
    });

}

function selectAccount() {
    const selectedRow = $('input[name="select-account"]:checked').closest('tr');
    const selectedAccountId = selectedRow.find('td:eq(1)').text();

    if (!selectedAccountId) {
        swal({ title: "계좌를 선택해 주세요.", icon: "warning" });
        return;
    }

    $.ajax({
        url: "/api/employee/accounts/" + selectedAccountId,
        type: "GET",
        success: function (data) {

            let transferAmountOfToday = data.transferAmountOfToday || 0;
            securityLevel = data.securityLevel;
            customerId = data.customerId;

            $('#withdrawal-customer-name').text(data.customerName +'(' + securityLevel + ')  |   ');
            $('#withdrawal-product-name').text(data.productName);
            $('#withdrawal-account-number').text(data.accId);
            $('#per-trade-limit').replaceWith(`<span id="per-trade-limit" class="amount-span">${comma(data.perTradeLimit)}&nbsp; 원</span>`);
            $('#daily-limit').replaceWith(`<span id="per-trade-limit" class="amount-span">${comma(data.dailyLimit)}&nbsp; 원</span>`);
            $('#transfer-amount-of-today').replaceWith(`<span id="transfer-amount-of-today" class="amount-span">${comma(transferAmountOfToday)}&nbsp; 원</span>`);


            // 금일 이체 한도
            var transferableAmountLimitOfToday = data.dailyLimit - transferAmountOfToday;


            // 현재 거래에서의 이체 가능 한도 = 1회 이체 한도 > 금일 이체 한도 ? 1회 이체 한도 : 금일 이체 한도;
            var currentTransferableLimit = data.perTradeLimit < transferableAmountLimitOfToday
                ? data.perTradeLimit : transferableAmountLimitOfToday;

            // 현재 거래에서의 이체 가능 금액 = 한도와 잔액중 작은 금액을 사용
            var transferableAmount = currentTransferableLimit < data.balance ? currentTransferableLimit : data.balance;

            $('#transferable-amount-limit-of-today').replaceWith(`<span id="transferable-amount-limit-of-today" class="amount-span">${comma(transferableAmountLimitOfToday)}&nbsp; 원</span>`);
            $('#transferable-amount').replaceWith(`<span id="transferable-amount" style="margin-left: 20px;">${comma(transferableAmount)}&nbsp;</span>`);


            console.log(data);

            // 계좌 잔액 라벨을 표시하고 금액 업데이트
            $('#account-balance').text(data.balance.toLocaleString('ko-KR'));



            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}



function handleSalaryTypeChange() {
    const selectedValue = $(this).val();

    if (selectedValue === 'directInput') {
        $('#description').removeAttr('disabled').val('');
    } else {
        $('#description').val(selectedValue).attr('disabled', 'true');
    }
}

function checkAccountId() {
    const pw = $('#account-pw-input').val();
    const accountNumber = $('#withdrawal-account-number').text();

    $.ajax({
        url: '/api/employee/account-validate',
        contentType: "application/x-www-form-urlencoded",
        type: "POST",
        data: { accountNumber, password: pw },
        success: function () {
            swal({ title: "검증 완료", text: "비밀번호 인증 성공", icon: "success" });
            validPassword = pw;


            $('#uploadEmployeeBtn').prop('disabled', false);
            $('#uploadIndividualEmployeeBtn').prop('disabled', false);
            $('input[value="예금주 확인"]').prop('disabled', false);
        },
        error: function (error) {
            swal({ title: "검증 실패", text: error.responseText, icon: "error", buttons: { cancel: true, confirm: false } });
            console.log("Transfer failed", error);
        }
    });
}

function showModal(modalId) {
    const myModal = new bootstrap.Modal(document.getElementById(modalId));
    myModal.show();
}

/**
 * - 개별 추가 시 데이터를 채우고 기존 Body를 지운 뒤
 * - 데이터를 기반으로 요소들을 다시 그린다.
 */
function uploadIndividualEmployee() {
    if (
        $('#targetAccIdModal').val() === "" ||
        $('#transferAmountModal').val() === "" ||
        $('#krwModal').val() === "" ||
        $('#depositorModal').val() === "" ||
        $('#descriptionModal').val() === ""
    ) {
        resetIndividualEmployeeModal();
        swal({ title: "개별 추가 실패", text: "모든 입력창을 채워주세요.", icon: "error", buttons: { cancel: true, confirm: false } });
        return;
    }

    employeeDataForUpload.push({
        accId: $('#withdrawal-account-number').text(),
        accountPassword: validPassword,
        targetAccId: $('#targetAccIdModal').val(),
        transferAmount: $('#transferAmountModal').val(),
        krw: $('#krwModal').val(),
        depositor: $('#depositorModal').val(),
        description: $('#descriptionModal').val(),
    });

    updateEmployeeTable();

    resetIndividualEmployeeModal();
}

function resetIndividualEmployeeModal() {
    $('#targetAccIdModal').val("");
    $('#transferAmountModal').val("");
    $('#krwModal').val("");
    $('#depositorModal').val("");
    $('#descriptionModal').val("");
}

function updateEmployeeTable() {
    const tbody = $('#employeeTablePreviewBody');
    tbody.empty();

    let totalTransferAmount = 0;
    let totalCount = 0;

    $.each(employeeDataForUpload, function (index, employee) {
        totalTransferAmount += parseInt(employee.transferAmount);
        totalCount += 1;

        const transferInfoRow = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id);
        transferInfoRow.append($('<td>').text(index + 1));
        transferInfoRow.append($('<td>').text(employee.targetAccId));
        transferInfoRow.append($('<td>').text(employee.transferAmount));
        transferInfoRow.append($('<td>').text(convertToKoreanNumber(employee.transferAmount)));
        transferInfoRow.append($('<td>').text(employee.depositor));
        transferInfoRow.append($('<td>').text(''));
        transferInfoRow.append($('<td>').text(employee.description));
        tbody.append(transferInfoRow);
    });



    const totalRow = $('<tr>');
    totalRow.append($('<td>').attr('colspan', 2).text('총 ' + totalCount + '건'));
    totalRow.append($('<td>').text(comma(totalTransferAmount)));
    totalRow.append($('<td>').text(convertToKoreanNumber(totalTransferAmount)));
    totalRow.append($('<td>').attr('colspan', 3).text(''));
    tbody.append(totalRow);
}

function handleTransferAmountInput() {

    $(this).val(comma(convertNumber($(this).val())));

    var inputAmount = parseFloat(convertNumber($(this).val()));  // 입력된 값에서 쉼표 제거 후 숫자로 변환


    if (isNaN(inputAmount) || $(this).val() === "") {
        $('#krwModal').val('');
        return;
    }
    const convertToKoreanNumberString = convertToKoreanNumber(inputAmount) + "원";
    $('#krwModal').val(convertToKoreanNumberString);
}

function uploadEmployeePreview() {
    const fileInput = document.getElementById('excelInput');
    const file = fileInput.files[0];

    if (!file) {
        swal({ title: "파일 선택", text: "업로드할 파일을 선택해주세요.", icon: "warning" });
        return;
    }

    const formData = new FormData();
    formData.append('file', file);

    $.ajax({
        url: '/api/employee/bulk-transfer/excel-upload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (employees) {
            employeeDataForUpload = [];

            $.each(employees, function (index, employee) {
                employeeDataForUpload.push({
                    accId: $('#withdrawal-account-number').text(),
                    accountPassword: validPassword,
                    targetAccId: employee.targetAccId,
                    transferAmount: employee.transferAmount,
                    depositor: employee.depositor,
                    description: employee.description,
                });
            });

            updateEmployeeTable();
        },
        error: function (xhr, status, error) {
            console.error('Upload failed!', error);
        }
    });

    $('#excelInput').val('');
}

function validationExecution() {
    $.ajax({
        type: "POST",
        url: "/api/employee/bulk-transfer/validation",
        contentType: 'application/json',
        data: JSON.stringify(employeeDataForUpload),
        success: function (data) {
            const tbody = $('#employeeTablePreviewBody');
            tbody.empty();

            employeeDataForUpload = [];
            let totalTransferAmount = 0;
            let totalCount = 0;

            $.each(data.bulkTransferValidationList, function (index, employee) {
                // 예금주 불일치 확인
                const isValidDepositor = employee.depositor === employee.validDepositor;
                const depositorStyle = isValidDepositor ? {} : { color: '#D40000' };
                totalTransferAmount += parseInt(employee.transferAmount);
                totalCount += 1;

                const row = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id);

                row.append($('<td>').text(index + 1));
                row.append($('<td>').text(employee.targetAccId));
                row.append($('<td>').text(employee.transferAmount));
                row.append($('<td>').text(convertToKoreanNumber(employee.transferAmount)));
                row.append($('<td>').text(employee.depositor).css(depositorStyle));
                row.append($('<td>').text(employee.validDepositor).css(depositorStyle));
                row.append($('<td>').text(employee.description));

                tbody.append(row);

                employeeDataForUpload.push({
                    accId: $('#withdrawal-account-number').text(),
                    accountPassword: $('#account-pw-input').val(),
                    targetAccId: employee.targetAccId,
                    transferAmount: employee.transferAmount,
                    krw: employee.krw,
                    depositor: employee.depositor,
                    validDepositor: employee.validDepositor,
                    description: employee.description,
                });
            });


            const totalRow = $('<tr>');
            totalRow.append($('<td>').attr('colspan', 2).text('총 ' + totalCount + '건'));
            totalRow.append($('<td>').text(comma(totalTransferAmount)));
            totalRow.append($('<td>').text(convertToKoreanNumber(totalTransferAmount)));
            totalRow.append($('<td>').attr('colspan', 3).text(''));
            tbody.append(totalRow);


            $('#total-registrations').text(data.totalCnt);
            $('#valid-recipients').text(data.normalCnt);
            $('#mismatch-recipients').text(data.inconsistencyCnt);
            $('#error-recipients').text(data.errorCnt);

            $('#result-content-div').show();
            $('input[value="초기화"]').show();
            $('input[value="이체실행"]').show();

            // 프로그래스바 진행상황 업데이트
            updateProgressIndicator();

            if (data.bulkTransferValidationList.length) {
                swal({ title: "예금주 확인", text: "예금주 확인 완료", icon: "success" });
                $('input[value="이체실행"]').removeAttr('disabled');
            }
        },
        error: function () {
            console.log("예금주 확인 실패");
        }
    });
}


function transferExecution() {
    $.ajax({
        type: "POST",
        url: "/api/employee/bulk-transfer",
        contentType: 'application/json',
        data: JSON.stringify(employeeDataForUpload),
        success: function (bulkTransferId) {
            const url = `/page/employee/bulk-transfer-result?bulkTransferId=${encodeURIComponent(bulkTransferId)}`;
            window.location.href = url;
        },
        error: function () {
            console.log("이체 실패");
        }
    });
}



function filterEmployeeData() {
    const searchValue = $(this).val();
    const searchCondition = $('#searchCondition').val();
    const tbody = $('#employeeTablePreviewBody');

    tbody.empty();

    let filteredEmployees = employeeDataForUpload;

    if (searchValue && searchCondition) {
        filteredEmployees = employeeDataForUpload.filter((item) => {
            if (searchCondition === "targetAccId") {
                return item.targetAccId.includes(searchValue);
            } else if (searchCondition === "depositor") {
                return item.depositor.includes(searchValue);
            }
            return false;
        });
    }

    $.each(filteredEmployees, function (index, employee) {
        const isValidDepositor = employee.depositor === employee.validDepositor;
        const rowClass = isValidDepositor ? '' : 'failure';
        const row = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id).addClass(rowClass);

        row.append($('<td>').text(index + 1));
        row.append($('<td>').text(employee.targetAccId));
        row.append($('<td>').text(employee.transferAmount));
        row.append($('<td>').text(employee.krw));
        row.append($('<td>').text(employee.depositor));
        row.append($('<td>').text(employee.validDepositor));
        row.append($('<td>').text(employee.description));

        tbody.append(row);
    });
}

function initExecution() {
    $('#employeeTablePreviewBody').empty();
    employeeDataForUpload = [];

    $('#result-content-div').hide();
    $('input[value="초기화"]').hide();
    $('input[value="이체실행"]').hide();

    resetProgressIndicator();
}

function handleBusinessDayDateInput() {
    $.ajax({
        url: '/api/common/current-business-day',
        type: 'GET',
        success: function (response) {
            const formattedDate = response.businessDate.substring(0, 10);
            $('#business-day-date-input').val(formattedDate);
        },
        error: function (xhr, status, error) {
            console.error('에러 발생:', error);
        }
    });
}

function userNameInput() {
    $.ajax({
        url: '/api/common/auth-data',
        type: 'GET',
        success: function (authData) {
            $('#user-name-input').val(authData.name);
        },
        error: function (xhr, status, error) {
            console.error('에러 발생:', error);
        }
    });
}

function updateProgressIndicator() {
    $('.progress-container .step:nth-of-type(1)').removeClass('active');
    if ($('.progress-container .step:nth-of-type(1) .inner-circle').is(':empty')) {
        $('.progress-container .step:nth-of-type(1) .inner-circle').append('<i class="bi bi-check"></i>');
    }
    $('.progress-container .step:nth-of-type(1) .inner-circle').removeClass('active');
    $('.progress-container .step:nth-of-type(3)').addClass('active');
    $('.progress-container .step:nth-of-type(3) .circle').addClass('active');
    $('.progress-container .step:nth-of-type(3) .inner-circle').addClass('active');
}

function resetProgressIndicator() {
    $('.progress-container .step:nth-of-type(3)').removeClass('active');
    $('.progress-container .step:nth-of-type(3) .circle').removeClass('active');
    $('.progress-container .step:nth-of-type(3) .inner-circle').removeClass('active');
}


function numberFormat(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 숫자를 한글로 변환
function convertToKoreanNumber(number) {
    var inputNumber  = number < 0 ? false : number;
    var unitWords    = ['', '만', '억', '조', '경'];
    var splitUnit    = 10000;
    var splitCount   = unitWords.length;
    var resultArray  = [];
    var resultString = '';

    for (var i = 0; i < splitCount; i++){
        var unitResult = (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0){
            resultArray[i] = unitResult;
        }
    }

    for (var i = 0; i < resultArray.length; i++){
        if(!resultArray[i]) continue;
        resultString = String(numberFormat(resultArray[i])) + unitWords[i] + resultString;
    }

    return resultString;
}

