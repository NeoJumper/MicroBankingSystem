$(document).ready(function() {

    registerClickEventOfEmpCheckBox();
    registerClickEventOfEmpAllCheckBox();
    registerClickEventOfBusinessDayUpdateBtn();


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

        const allCheckBox = $(this).find('i');
        if (allCheckBox.hasClass('bi-square')) {

            $('.modal-body i').each(function() {
                const icon = $(this);
                icon.removeClass('bi-square').addClass('bi-check-square');
            });

            allCheckBox.removeClass('bi-square').addClass('bi-check-square');
        }
        else{
            $('.modal-body i').each(function() {
                const icon = $(this);
                icon.removeClass('bi-check-square').addClass('bi-square');
            });

            allCheckBox.removeClass('bi-check-square').addClass('bi-square');
        }
    });
}

function registerClickEventOfBusinessDayUpdateBtn(){
    $('#business-day-update-modal-update-btn').on('click', function () {
        changeBusinessDay();
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

function changeBusinessDay(){
    const data = [];

    // 각 business-day-element 행을 순회
    $('.business-day-element').each(function() {
        const row = $(this); // 현재 행
        const icon = row.find('i'); // 아이콘 요소

        // 아이콘 클래스에 따라 status 결정
        const status = icon.hasClass('bi-check-square') ? 'OPEN' : 'CLOSED';

        // ID, 이름, 이전 캐시 밸런스 값 추출
        const id = row.find('td:nth-child(2)').text().trim(); // 두 번째 열에서 id
        const name = row.find('td:nth-child(3)').text().trim(); // 세 번째 열에서 name
        const prevCashBalance = row.find('td:nth-child(4) input').val().trim().replace(/,/g, ''); // 네 번째 열의 input에서 value

        // 추출한 데이터를 객체로 만들고 배열에 추가
        data.push({
            id: id,
            name: name,
            prevCashBalance: prevCashBalance,
            status: status
        });
    });

    const businessDayUpdate = {
        workerDataList : data,
        prevCashBalanceOfBranch : $('#business-day-modal-branch-balance').val().trim().replace(/,/g, ''),
        businessDateToChange : $('#next-business-day').val()
    }

    $.ajax({
        url: '/api/business-day',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify(businessDayUpdate), // JSON.stringify를 사용하여 객체를 JSON 문자열로 변환
        success: function(response) {
            swal({
                title: "영업 시작",
                text: "영업일 변경 작업이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            }).then(() => {
                // swal 경고창이 닫힌 후에 리다이렉트
                window.location.href = '/page/manager/business-day-management';
            });
        },
        error: function(xhr, status, error) {
            swal({
                title: "변경 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            })
            console.error('Error updating business day:', error);
        }
    });

    // 결과 JSON 데이터 출력 (또는 전송)
    console.log(businessDayUpdate);
}

