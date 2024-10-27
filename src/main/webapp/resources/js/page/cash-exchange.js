$(document).ready(function () {
    updateTransactionTitle();
    registerClickEventOfEmpSearchBtn();
    searchModalSelectBtn();
})



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
                data: { employeeId: employeeId },
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

// 선택된 사원 정보를 테이블에 추가하는 함수
function updateEmployeeTable(employeeDetail) {
    const tableBody = $('#selected-employee-table tbody');

    // 새로운 행을 생성하여 ClosingData 정보 추가
    const newRow = `
        <tr>
            <td>${employeeDetail.id}</td>
            <td>${employeeDetail.name}</td>
            <td>${(employeeDetail.prevCashBalance+employeeDetail.totalDeposit-employeeDetail.totalWithdrawal).toLocaleString()} 원</td>
             <td><input type="text" /></td>
        </tr>
    `;

    // 테이블에 새로운 행 추가
    tableBody.append(newRow);
}


// 라디오 버튼 선택에 따라 <h3> 제목 변경
function updateTransactionTitle() {
    const selectedValue = document.querySelector('input[name="transactionType"]:checked').value;
    const titleElement = document.getElementById("transactionTitle");

    if (selectedValue === "handover") {
        titleElement.textContent = "인도 거래";
    } else if (selectedValue === "receive") {
        titleElement.textContent = "인수 거래";
    }
}


function registerClickEventOfEmpSearchBtn(){
    $('#employee-search-btn').click(function()
    {
        var employeeSearchModal = new bootstrap.Modal(document.getElementById('employee-search-modal'));
        employeeSearchModal.show();
    });
}
