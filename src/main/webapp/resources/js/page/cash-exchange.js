document.addEventListener("DOMContentLoaded", initializePage);

function initializePage() {
    isClosed();
    updateTransactionTitle();
    registerClickEventOfEmpSearchBtn();
    registerSearchModalSelectBtn();
    checkEmptyTable();
    registerTransactionAmountInput();

    $('#cash-exchange-result-close-btn').on('click', () => window.location.reload());
    $('#cash-exchange-accept').on('click', handleCashExchangeAccept);
}

// 거래 승인 버튼 클릭 시 처리 함수
function handleCashExchangeAccept() {
    if (isEmployeeTableEmpty()) {
        showAlert("거래 승인 불가", "error", "선택된 행원이 존재하지 않습니다.");
    } else {
        const amount = getParsedIntValue('.cash-exchange-amount');
        if (amount === 0) {
            showAlert("거래 승인 불가", "warning", "거래 금액은 0원이 될 수 없습니다.");
        } else {
            performCashExchange();
        }
    }
}

// 거래 금액 입력 이벤트 등록
function registerTransactionAmountInput() {
    $('#selected-employee-table').on('input', '.cash-exchange-amount', function () {
        calculateTransactionAmount($(this));
    });
}

// 거래 금액 계산 및 유효성 검사
function calculateTransactionAmount(inputElement) {
    let transactionAmount = formatNumberInput(inputElement.val());

    // 입력값이 비어 있을 경우 콤마 없이 비워두기
    if (transactionAmount === "") {
        inputElement.val(0);
    } else {
        // 콤마를 추가한 포맷팅된 값으로 설정
        inputElement.val(parseInt(transactionAmount).toLocaleString());
    }

    const transactionAmountInt = parseInt(transactionAmount.replace(/,/g, '')) || 0;
    const lastManagerCash = getParsedIntValue('#lastManagerCash');
    const preEmployeeBalance = getParsedIntValue('.cash-exchange-emp-balance');

    const exchangeType = getSelectedExchangeType();
    const { isValid, message, newManagerBalance, newEmployeeBalance } = validateTransactionAmount(
        transactionAmountInt, lastManagerCash, preEmployeeBalance, exchangeType
    );

    if (!isValid) {
        $('#is-over-balance').text(message); // 초과 경고 메시지 설정
        const previousAmount = inputElement.data('previous-amount') || "";
        inputElement.val(previousAmount ? parseInt(previousAmount).toLocaleString() : ""); // 이전 유효 값에 쉼표 추가
    } else {
        $('#is-over-balance').text(""); // 경고 메시지 지우기
        updateTransactionBalances(true, "", newManagerBalance, newEmployeeBalance, transactionAmount, inputElement);
        inputElement.data('previous-amount', transactionAmount);  // 유효한 값 업데이트
    }
}

// 거래금액 유효성 검증 및 오류 메시지 반환
function validateTransactionAmount(transactionAmount, lastManagerCash, preEmployeeBalance, exchangeType) {
    if (exchangeType === "HANDOVER" && transactionAmount > lastManagerCash) {
        return { isValid: false, message: "거래금은 시재금을 넘을 수 없습니다." };
    }
    if (exchangeType === "RECEIVE" && transactionAmount > preEmployeeBalance) {
        return { isValid: false, message: "거래금은 행원 시재금을 넘을 수 없습니다." };
    }
    return {
        isValid: true,
        message: "",
        newManagerBalance: exchangeType === "HANDOVER" ? lastManagerCash - transactionAmount : lastManagerCash + transactionAmount,
        newEmployeeBalance: exchangeType === "HANDOVER" ? preEmployeeBalance + transactionAmount : preEmployeeBalance - transactionAmount
    };
}

// 거래 후 잔액 업데이트
function updateTransactionBalances(isValid, message, newManagerBalance, newEmployeeBalance, transactionAmount, inputElement) {
    if (isValid) {
        $('#is-over-balance').text("");
        inputElement.data('previous-amount', transactionAmount);  // 유효한 값 업데이트
        $('#afterManagerCash').val(newManagerBalance.toLocaleString());
        $('.cash-exchange-after-balance').val(newEmployeeBalance.toLocaleString());
    } else {
        $('#is-over-balance').text(message);
        inputElement.val(inputElement.data('previous-amount') || "");
    }
}

// 거래 승인 요청 수행
function performCashExchange() {
    const requestData = {
        afterManagerCash: getParsedIntValue('#afterManagerCash'),
        afterEmployeeCash: getParsedIntValue('.cash-exchange-after-balance'),
        amount: getParsedIntValue('.cash-exchange-amount'),
        exchangeType: getSelectedExchangeType(),
        empId: $('.cash-exchange-emp-id').text()
    };

    sendCashExchangeRequest(requestData, updateCashExchangeModal, () => showAlert("거래 처리 실패", "error", "거래 처리 중 오류가 발생했습니다."));
}

function sendCashExchangeRequest(data, successCallback, errorCallback) {
    $.ajax({
        url: "/api/manager/cash-exchange",
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: successCallback,
        error: errorCallback
    });
}

// 모달 데이터 업데이트 및 표시
function updateCashExchangeModal(response) {
    $('#resultEmpId').text(response.empId);
    $('#resultEmpName').text($('.cash-exchnage-emp-name').text());
    $('#resultAmount').val(response.amount.toLocaleString());
    $('#resultEmpCashBalance').text(response.empCashBalance.toLocaleString());
    $('#resultManagerCashBalance').text(response.managerCashBalance.toLocaleString());

    new bootstrap.Modal(document.getElementById('cash-exchange-result-modal')).show();
}

// 사원 테이블 업데이트
function updateEmployeeTable(employeeDetail) {
    const formattedBalance = calculateEmployeeBalance(employeeDetail).toLocaleString();
    const newRow = `
        <tr class="new-row">
            <td class="cash-exchange-emp-id">${employeeDetail.id}</td>
            <td class="cash-exchnage-emp-name">${employeeDetail.name}</td>
            <td><input class="cash-exchange-emp-balance" type="text" value="${formattedBalance}" disabled></td>
            <td><input type="text" value="0" class="cash-exchange-amount" /></td>
            <td><input type="text" class="cash-exchange-after-balance" value="${formattedBalance}" disabled></td>
        </tr>
    `;
    $('#selected-employee-table tbody').html(newRow);
    checkEmptyTable();
}

// 유틸리티 함수들
function isEmployeeTableEmpty() {
    return $('#selected-employee-table tbody').find('tr').length === 1 && $('#empty-message').is(':visible');
}

function calculateEmployeeBalance({ prevCashBalance, totalDeposit, totalWithdrawal }) {
    return prevCashBalance + totalDeposit - totalWithdrawal;
}

function formatNumberInput(value) {
    return value.replace(/[^0-9]/g, '') || "";  // 빈 문자열이면 빈 값 반환
}

function getParsedIntValue(selector) {
    return parseInt($(selector).val().replace(/,/g, '')) || 0;
}

function getSelectedExchangeType() {
    return document.querySelector('input[name="exchangeType"]:checked').value;
}

function showAlert(title, icon, text) {
    swal({ title, text, icon: icon, button: "닫기" });
}

function checkEmptyTable() {
    const isEmpty = isEmployeeTableEmpty();
    $('#empty-message').toggle(isEmpty);
}

function registerSearchModalSelectBtn() {
    $('#search-modal-select-btn').on('click', fetchEmployeeDetails);
}

function fetchEmployeeDetails() {
    const selectedEmployee = $('input[name="selected-employee"]:checked');
    if (selectedEmployee.length > 0) {
        const employeeId = selectedEmployee.closest('tr').find('td:nth-child(2)').text();
        $.ajax({
            type: 'GET',
            url: '/api/manager/cash-exchange',
            data: { employeeId },
            success: function(response) {
                updateEmployeeTable(response);
                $('#employee-search-modal').modal('hide');
            },
            error: error => showAlert("행원 선택 실패", "error", "데이터 전송 중 오류가 발생했습니다.")
        });
    } else {
        showAlert("행원 선택", "warning", "거래할 행원을 선택해 주세요.");
    }
}

function registerClickEventOfEmpSearchBtn() {
    $('#employee-search-btn').click(() => new bootstrap.Modal(document.getElementById('employee-search-modal')).show());
}

function updateTransactionTitle() {
    const selectedValue = getSelectedExchangeType();
    const titleElement = document.getElementById("transactionTitle");
    titleElement.textContent = selectedValue === "HANDOVER" ? "인도 거래" : "인수 거래";
    resetTransactionDetails();
}

function resetTransactionDetails() {
    $('#is-over-balance').text("");
    $('#selected-employee-table tbody').html(`
        <tr id="empty-message">
            <td colspan="5" style="text-align: center; color: gray; border-bottom: none; height: 250px">
                행원 조회 버튼을 눌러 행원을 선택하여 주십시오
            </td>
        </tr>
    `);
    $('#afterManagerCash').val($('#lastManagerCash').val());
}
