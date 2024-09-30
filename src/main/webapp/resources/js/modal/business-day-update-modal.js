$(document).ready(function() {

    handleWorkers();
    handleCashBalanceOfBranch();
});

function handleWorkers(){
    $.ajax({
        url: '/api/manager/employees/cash-balance',
        type: 'GET',
        success: function(response) {

            $('#business-day-modal-emp-list').empty();

            // 새로운 데이터를 tbody에 추가
            response.forEach(function(employee) {
                let formattedCashBalance = employee.cashBalance.toLocaleString(); // 숫자를 3자리마다 쉼표로 포맷

                let row = `
                    <tr class="business-day-element">
                        <td><i class="bi bi-square"></i></td>
                        <td style="width: 20%;">${employee.id}</td>
                        <td style="width: 20%;">${employee.name}</td>
                        <td style="width: 40%;">
                            <input style="direction: rtl;" type="text" value="${formattedCashBalance}" disabled>
                        </td>
                    </tr>
                `;
                // 테이블의 tbody에 추가
                $('#business-day-modal-emp-list').append(row);
            });




        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function handleCashBalanceOfBranch(){
    $.ajax({
        url: '/api/manager/branch/cash-balance',
        type: 'GET',
        success: function(response) {
            let formattedCashBalance = response.toLocaleString(); // 숫자를 3자리마다 쉼표로 포맷

            $('#business-day-modal-branch-balance').val(formattedCashBalance);

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}


