$(document).ready(function () {
    updateTransactionTitle();
    registerClickEventOfEmpSearchBtn();
    searchModalSelectBtn();
    checkEmptyTable();

    // 모달 닫기 버튼에 추가
    $('#cash-exchange-result-close-btn').on('click', function () {
        window.location.reload();
    })

    // 거래 승인 버튼 함수 추가
    $('#cash-exchange-accept').on('click', function () {
        // 거래 후 매니저 잔액
        let afterManagerCash = $('#afterManagerCash').val().replace(/[^0-9]/g, '') || 0;
        // 거래 후 행원 잔액
        let afterEmployeeCash = $('.cash-exchange-after-balance').val().replace(/[^0-9]/g, '') || 0;
        // 금액
        let amount = $('.cash-exchange-amount').val().replace(/[^0-9]/g, '') || 0;
        // 거래 유형
        const exchangeType = document.querySelector('input[name="exchangeType"]:checked').value;
        // 거래하는 행원 ID
        const empId = $('.cash-exchange-emp-id').text();

        $.ajax({
            url: "/api/manager/cash-exchange",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                afterManagerCash: afterManagerCash,
                afterEmployeeCash: afterEmployeeCash,
                amount: amount,
                exchangeType: exchangeType,
                empId: empId
            }),
            success : function (response) {
                // 모달 데이터 업데이트
                $('#resultEmpId').text(response.empId);
                $('#resultEmpName').text($('.cash-exchnage-emp-name').text());
                $('#resultAmount').val(response.amount.toLocaleString());
                $('#resultEmpCashBalance').text(response.empCashBalance.toLocaleString());
                $('#resultManagerCashBalance').text(response.managerCashBalance.toLocaleString());

                // 모달 표시
                var cashExchangeResultModal = new bootstrap.Modal(document.getElementById('cash-exchange-result-modal'));
                cashExchangeResultModal.show();
            },
            error: function () {
                alert('거래 처리 중 오류가 발생했습니다.');
            }
        });
    });


    // 이전 입력 값을 저장할 변수 선언
    let previousTransactionAmount = "0";

    // 거래금 입력 시 동적 계산
    $('#selected-employee-table').on('input', '.cash-exchange-amount', function () {
        let transactionAmount = $(this).val();

        // 숫자가 아닌 문자를 제거하고, 앞에 붙은 0 제거
        transactionAmount = transactionAmount.replace(/[^0-9]/g, '');
        if (transactionAmount.startsWith('0') && transactionAmount.length > 1) {
            transactionAmount = transactionAmount.replace(/^0+/, '');
        }

        // 쉼표를 추가한 포맷팅된 값으로 설정
        transactionAmount = transactionAmount === "" ? "0" : parseInt(transactionAmount).toLocaleString();
        $(this).val(transactionAmount);

        // 실제 계산용 값 추출
        const transactionAmountInt = parseInt(transactionAmount.replace(/,/g, '')) || 0;
        const lastManagerCash = parseInt($('#lastManagerCash').val().replace(/,/g, '')) || 0;
        const preEmployeeBalance = parseInt($('.cash-exchange-emp-balance').val().replace(/,/g, '')) || 0;

        // 현재 선택된 exchangeType 확인
        const exchangeType = document.querySelector('input[name="exchangeType"]:checked').value;

        if (exchangeType === "HANDOVER") {
            // 인도 거래: 기존 로직
            if (transactionAmountInt > lastManagerCash) {
                alert('거래금은 잔여 시재금을 초과할 수 없습니다.');
                // 초과 시 이전 값으로 되돌림
                $(this).val(previousTransactionAmount);
            } else {
                // 초과하지 않을 경우, 새로운 값을 previousTransactionAmount에 저장
                previousTransactionAmount = transactionAmount;
                // 거래 후 시재금 계산
                const newManagerBalance = lastManagerCash - transactionAmountInt;
                const newEmployeeBalance = preEmployeeBalance + transactionAmountInt;
                $('#afterManagerCash').val(newManagerBalance.toLocaleString());  // 쉼표 형식으로 업데이트
                $('.cash-exchange-after-balance').val(newEmployeeBalance.toLocaleString());
            }
        } else if (exchangeType === "RECEIVE") {
            // 인수 거래: 새로운 로직
            if (transactionAmountInt > preEmployeeBalance) {
                alert("인수 금액이 행원 시재금을 초과할 수 없습니다.");
                // 초과 시 이전 값으로 되돌림
                $(this).val(previousTransactionAmount);
            } else {
                // 초과하지 않을 경우, 새로운 값을 previousTransactionAmount에 저장
                previousTransactionAmount = transactionAmount;
                // 거래 후 시재금 계산
                const newManagerBalance = lastManagerCash + transactionAmountInt;
                const newEmployeeBalance = preEmployeeBalance - transactionAmountInt;
                $('#afterManagerCash').val(newManagerBalance.toLocaleString());  // 쉼표 형식으로 업데이트
                $('.cash-exchange-after-balance').val(newEmployeeBalance.toLocaleString());
            }
        }
    });
});

function resetTransactionDetails() {
    // Clear the tbody of #selected-employee-table and show the empty message
    const tbody = document.querySelector("#selected-employee-table tbody");
    tbody.innerHTML = `
        <tr id="empty-message">
            <td colspan="5" style="text-align: center; color: gray; border-bottom: none; height: 250px">
                행원 조회 버튼을 눌러 행원을 선택하여 주십시오
            </td>
        </tr>
    `;

    // Reset afterManagerCash to initial manager cash balance
    const afterManagerCash = document.getElementById("afterManagerCash");
    const initialCashBalance = document.getElementById("lastManagerCash").value;
    afterManagerCash.value = initialCashBalance;
}

// 빈 테이블 상태 확인 함수
function checkEmptyTable() {
    const tableBody = $('#selected-employee-table tbody');
    const isEmpty = tableBody.find('tr').length === 1 && tableBody.find('#empty-message').is(':visible');

    if (isEmpty) {
        $('#empty-message').show();  // 안내 메시지 표시
    } else {
        $('#empty-message').hide();  // 안내 메시지 숨김
    }
}


// 선택된 사원 정보를 테이블에 추가하는 함수
function updateEmployeeTable(employeeDetail) {
    const tableBody = $('#selected-employee-table tbody');
    tableBody.empty();

    // JavaScript로 숫자 포맷 수행
    const formattedBalance = (employeeDetail.prevCashBalance + employeeDetail.totalDeposit - employeeDetail.totalWithdrawal).toLocaleString();

    // 새로운 행을 생성하여 ClosingData 정보 추가
    const newRow = `
        <tr>
            <td class="cash-exchange-emp-id">${employeeDetail.id}</td>
            <td class="cash-exchnage-emp-name">${employeeDetail.name}</td>
            <td>
                <input
                       class="cash-exchange-emp-balance"
                       type="text"
                       value="${formattedBalance}"
                       disabled>
            </td>
            <td><input type="text" class="cash-exchange-amount" /></td>
            <td><input type="text" class="cash-exchange-after-balance" value="${formattedBalance}" disabled></td>
        </tr>
    `;

    // 테이블에 새로운 행 추가
    tableBody.append(newRow);

    // 직원 추가 후 빈 상태 확인
    checkEmptyTable();
}

function searchModalSelectBtn() {
    $('#search-modal-select-btn').on('click', function () {
        const selectedEmployee = $('input[name="selected-employee"]:checked');

        if (selectedEmployee.length > 0) {
            const selectedRow = selectedEmployee.closest('tr');
            const employeeId = selectedRow.find('td:nth-child(2)').text();

            // AJAX 요청을 보내서 선택된 사원의 정보를 가져옴
            $.ajax({
                type: 'GET',
                url: '/api/manager/cash-exchange',
                data: {employeeId: employeeId},
                success: function (employeeDetail) {
                    updateEmployeeTable(employeeDetail);  // 테이블에 데이터를 추가하는 함수 호출
                },
                error: function (error) {
                    alert('데이터 전송 중 오류가 발생했습니다.');
                    console.error(error);
                }
            });
        } else {
            alert('고객을 선택해 주세요.');
        }
    });
}

// 라디오 버튼 선택에 따라 <h3> 제목 변경
function updateTransactionTitle() {
    const selectedValue = document.querySelector('input[name="exchangeType"]:checked').value;
    const titleElement = document.getElementById("transactionTitle");

    if (selectedValue === "HANDOVER") {
        titleElement.textContent = "인도 거래";
    } else if (selectedValue === "RECEIVE") {
        titleElement.textContent = "인수 거래";
    }
    resetTransactionDetails();
}

function registerClickEventOfEmpSearchBtn() {
    $('#employee-search-btn').click(function () {
        var employeeSearchModal = new bootstrap.Modal(document.getElementById('employee-search-modal'));
        employeeSearchModal.show();
    });
}
