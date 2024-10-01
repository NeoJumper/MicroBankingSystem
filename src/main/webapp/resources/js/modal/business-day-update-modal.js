$(document).ready(function() {

    registerClickEventOfEmpCheckBox();
    registerClickEventOfEmpAllCheckBox();


    handleWorkers();


});

function registerClickEventOfEmpCheckBox(){
    // businessday-element 클래스가 적용된 모든 행을 대상으로 클릭 이벤트
    $('#business-day-modal-emp-add-list').on('click', '.business-day-element', function () {

        const icon = $(this).find('i');

        if (icon.hasClass('bi-square')) {
            icon.removeClass('bi-square').addClass('bi-check-square');
        } else {
            icon.removeClass('bi-check-square').addClass('bi-square');
        }
    });
}

function registerClickEventOfEmpAllCheckBox(){
    $('#business-day-modal-all-checkbox').on('click', function () {
        // modal-body 내부의 모든 i 태그 선택
        $('.modal-body i').each(function() {
            const icon = $(this);

            // 클래스 전환
            if (icon.hasClass('bi-square')) {
                icon.removeClass('bi-square').addClass('bi-check-square');
            } else {
                icon.removeClass('bi-check-square').addClass('bi-square');
            }
        });
    });
}


function handleWorkers() {
    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'GET',
        success: function (response) {
            console.log(response);
            $('#business-day-modal-emp-list').empty();

            // 새로운 데이터를 tbody에 추가
            response.closingDataList.forEach(function (employee) {
                let formattedCashBalance = employee.vaultCash.toLocaleString(); // 숫자를 3자리마다 쉼표로 포맷

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

            let formattedCashBalance = response.vaultCashOfBranch.toLocaleString(); // 숫자를 3자리마다 쉼표로 포맷

            $('#business-day-modal-branch-balance').val(formattedCashBalance);


        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}



