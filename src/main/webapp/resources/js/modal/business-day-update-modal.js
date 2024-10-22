$(document).ready(function() {
    // 이벤트 등록
    registerClickEventOfEmpAllCheckBox();
    registerClickEventOfBusinessDayUpdateBtn(); // 추가된 버튼 이벤트 등록
    registerClickEventOfEmpCheckBox();

    // 모달이 열릴 때 실행
    $('#business-day-update-modal').on('show.bs.modal', function(e) {
        handleWorkers();
        resetSwitchStates(); // 모달이 열릴 때 스위치 상태 초기화
    });
});

// 개별 스위치 클릭 이벤트
function registerClickEventOfEmpCheckBox() {
    // 이벤트 위임 사용
    $('#business-day-modal-emp-list').on('click', '.form-check-input', function () {
        const isChecked = $(this).prop('checked');
        const row = $(this).closest('tr');
        row.toggleClass('selected', isChecked);

        // 선택된 행원만 prev-cash-balance-init 필드를 활성화
        row.find('#prev-cash-balance-init').prop('disabled', !isChecked);

        // 만약 하나의 스위치라도 꺼지면 전체 선택 스위치도 해제
        const allChecked = $('#business-day-modal-emp-list .form-check-input').length === $('#business-day-modal-emp-list .form-check-input:checked').length;
        $('#selectAllSwitch').prop('checked', allChecked); // 모든 스위치가 체크되면 전체 선택 스위치도 체크
    });
}

// 전체 선택 스위치 클릭 이벤트
function registerClickEventOfEmpAllCheckBox() {
    $('#selectAllSwitch').on('change', function() {
        const isChecked = $(this).prop('checked');
        // 모든 개별 스위치를 전체 선택 스위치 상태와 맞추기
        $('#business-day-modal-emp-list .form-check-input').each(function() {
            $(this).prop('checked', isChecked); // 전체 스위치 상태에 맞추어 개별 스위치 상태 변경
            const row = $(this).closest('tr');
            row.toggleClass('selected', isChecked);
            row.find('#prev-cash-balance-init').prop('disabled', !isChecked); // 선택된 경우만 활성화
        });
    });
}

// 영업 시작 버튼 클릭 이벤트 등록
function registerClickEventOfBusinessDayUpdateBtn() {
    $('#business-day-update-modal-update-btn').on('click', function () {
        changeBusinessDay(); // 버튼 클릭 시 영업일 변경 처리 함수 호출
    });
}

// 모달이 열릴 때 스위치 상태 초기화
function resetSwitchStates() {
    // 전체 선택 스위치 초기화 (처음에는 선택되지 않은 상태)
    $('#selectAllSwitch').prop('checked', false);

    // 모든 개별 스위치 초기화 (처음에는 선택되지 않은 상태)
    $('#business-day-modal-emp-list .form-check-input').each(function() {
        $(this).prop('checked', false).closest('tr').removeClass('selected');
        $(this).closest('tr').find('#prev-cash-balance-init').prop('disabled', true); // 처음엔 모두 비활성화
    });
}

// 서버에서 근무자 데이터를 가져와서 모달에 표시하는 함수
function handleWorkers() {
    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'GET',
        success: function(response) {
            $('#business-day-modal-emp-list').empty();

            // 전일자 현금 총액 가져오기
            const branchBalance = response.prevCashBalanceOfBranch;

            // 서버에서 받은 데이터를 사용해 각 행을 생성
            response.closingDataList.forEach(function (employee) {
                let formattedCashBalance = employee.prevCashBalance;

                // 역할(role)에 따라 표기 변경
                let roleDisplay = '';
                if (employee.roles === 'ROLE_MANAGER') {
                    roleDisplay = '매니저';
                    formattedCashBalance = branchBalance; // 매니저는 전일자 현금 총액
                } else if (employee.roles === 'ROLE_EMPLOYEE') {
                    roleDisplay = '행원';
                } else {
                    roleDisplay = employee.roles; // 다른 값이 있으면 그대로 표시
                }

                let row = `
                    <tr class="business-day-element">
                        <td>
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch">
                            </div>
                        </td>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${roleDisplay}</td>
                        <td>
                            <input type="text" value="${formattedCashBalance}" disabled>
                        </td>
                        <td>
                            <input id="prev-cash-balance-init" type="text" value="10000" disabled>
                        </td>
                    </tr>
                `;
                // 테이블의 tbody에 추가
                $('#business-day-modal-emp-list').append(row);
            });

            // 전일자 지점 잔액 설정
            $('#business-day-modal-branch-balance').val(branchBalance);

            // 스위치 초기화 상태 맞추기
            resetSwitchStates();
        },
        error: function(xhr, status, error) {
            console.error('에러 발생:', error);
        }
    });
}

// 영업일 변경 처리 함수
function changeBusinessDay() {
    const data = [];

    // 각 business-day-element 행을 순회
    $('.business-day-element').each(function() {
        const row = $(this); // 현재 행
        const isChecked = row.find('.form-check-input').prop('checked'); // 스위치 상태

        // 스위치 상태에 따라 status 결정
        const status = isChecked ? 'OPEN' : 'CLOSED';

        // ID, 이름, 이전 캐시 밸런스 값 추출
        const id = row.find('td:nth-child(2)').text().trim(); // 두 번째 열에서 id
        const name = row.find('td:nth-child(3)').text().trim(); // 세 번째 열에서 name
        const prevCashBalance = row.find('td:nth-child(5) input').val().trim().replace(/,/g, ''); // 네 번째 열의 input에서 value

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
        url: '/api/manager/business-day-change',
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
