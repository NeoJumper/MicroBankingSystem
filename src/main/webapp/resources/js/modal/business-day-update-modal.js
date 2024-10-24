$(document).ready(function () {
    // 전체 선택 버튼
    registerClickEventOfEmpAllCheckBox();
    // 개별 선택 버튼
    registerClickEventOfEmpCheckBox();

    // 영업일 버튼
    registerClickEventOfBusinessDayUpdateBtn();

    // 모달이 열릴 때 실행
    $('#business-day-update-modal').on('show.bs.modal', function (e) {
        handleWorkers();
        resetSwitchStates(); // 모달이 열릴 때 스위치 상태 초기화
        registerCashBalanceChangeEvent();
    });

    // 툴팁 초기화
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    });
});

// 시재금 변경 이벤트 등록
function registerCashBalanceChangeEvent() {
    $('#business-day-modal-emp-list').on('input', '.prev-cash-balance-init', function () {
        let currentValue = $(this).val();

        // 숫자가 아닌 문자를 제거한 후, 쉼표를 추가하기 전에 앞에 붙은 0을 제거
        currentValue = convertNumber(currentValue); // 숫자가 아닌 모든 문자를 제거
        if (currentValue.startsWith('0') && currentValue.length > 1) {
            currentValue = currentValue.replace(/^0+/, ''); // 앞에 붙은 0을 제거
        }

        // 값이 비어있으면 0으로 처리
        if (currentValue === "") {
            currentValue = "0";
        } else {
            currentValue = comma(currentValue); // 천 단위 쉼표 추가
        }

        $(this).val(currentValue); // 수정된 값을 다시 input에 설정

        const row = $(this).closest('tr');
        const branchPrevCash = $('#business-day-modal-branch-balance'); // 지점 현금 총액 엘리먼트
        let branchCashBalance = parseFloat(branchPrevCash.val().replace(/,/g, '')) || 0; // 지점 현금 총액

        const prevCashBalance = parseFloat($(this).data('prevCashBalance')) || 0; // 이전 시재금 값
        let newCashBalance = parseFloat(currentValue.replace(/,/g, '')) || 0; // 쉼표 제거 후 현재 값

        const difference = newCashBalance - prevCashBalance; // 현재 값과 이전 값의 차이

        // 지점 현금 총액이 0 이하로 내려가지 않도록 방지
        if (branchCashBalance - difference < 0) {
            alert('지점의 전일자 현금 총액이 0 이하로 떨어질 수 없습니다.');
            $(this).val(comma(prevCashBalance)); // 이전 값으로 되돌리고 쉼표 추가
        } else {
            branchCashBalance -= difference;
            branchPrevCash.val(branchCashBalance.toLocaleString()); // 포맷팅된 지점 현금 총액 업데이트
            $(this).data('prevCashBalance', newCashBalance); // 현재 시재금 값을 저장 (쉼표 제거 후 저장)
        }
    });
}


// 개별 스위치 클릭 이벤트
function registerClickEventOfEmpCheckBox() {
    $('#business-day-modal-emp-list').on('click', '.form-check-input', function () {
        const isChecked = $(this).prop('checked');
        const row = $(this).closest('tr');
        const prevCashBalance = parseFloat(row.find('.prev-cash-balance-init').val().replace(/,/g, '')) || 0; // 현재 시재금 값
        const branchCashBalanceElem = $('#business-day-modal-branch-balance'); // 지점 현금 총액 엘리먼트
        let branchCashBalance = parseFloat(branchCashBalanceElem.val().replace(/,/g, '')) || 0; // 지점 현금 총액

        if (!isChecked) {
            // 체크 해제 시, 시재금을 지점 현금 총액에 더해줌
            if (prevCashBalance > 0) {
                branchCashBalance += prevCashBalance; // 시재금이 0보다 큰 경우에만 더함
                branchCashBalanceElem.val(branchCashBalance.toLocaleString()); // 포맷팅된 매니저 현금 총액 업데이트
            }

            // 체크 해제 시, 해당 행원의 시재금을 0으로 설정
            row.find('.prev-cash-balance-init').val('0');
            row.find('.prev-cash-balance-init').data('prevCashBalance', 0); // 이전 값도 0으로 설정하여 중복 방지
        }

        row.toggleClass('selected', isChecked);

        // 선택된 행원만 prev-cash-balance-init 필드를 활성화
        row.find('.prev-cash-balance-init').prop('disabled', !isChecked);

        // 만약 하나의 스위치라도 꺼지면 전체 선택 스위치도 해제
        const allChecked = $('#business-day-modal-emp-list .form-check-input').length === $('#business-day-modal-emp-list .form-check-input:checked').length;
        $('#selectAllSwitch').prop('checked', allChecked); // 모든 스위치가 체크되면 전체 선택 스위치도 체크
    });
}

// 전체 선택 스위치 클릭 이벤트
function registerClickEventOfEmpAllCheckBox() {
    $('#selectAllSwitch').on('change', function () {
        const isChecked = $(this).prop('checked');
        // 모든 개별 스위치를 전체 선택 스위치 상태와 맞추기
        $('#business-day-modal-emp-list .form-check-input').each(function () {
            $(this).prop('checked', isChecked); // 전체 스위치 상태에 맞추어 개별 스위치 상태 변경
            const row = $(this).closest('tr');
            row.toggleClass('selected', isChecked);
            row.find('.prev-cash-balance-init').prop('disabled', !isChecked); // 선택된 경우만 활성화

            // 체크 해제 시, 모든 행원의 시재금을 0으로 설정
            if (!isChecked) {
                const prevCashBalance = parseFloat(row.find('.prev-cash-balance-init').val().replace(/,/g, '')) || 0; // 현재 시재금 값
                const branchCashBalanceElem = $('#business-day-modal-branch-balance'); // 지점 현금 총액 엘리먼트
                let branchCashBalance = parseFloat(branchCashBalanceElem.val().replace(/,/g, '')) || 0; // 매니저 현금 총액

                if (prevCashBalance > 0) {
                    branchCashBalance += prevCashBalance; // 현재 시재금을 매니저 현금에 다시 더해줌
                    branchCashBalanceElem.val(branchCashBalance.toLocaleString()); // 포맷팅된 매니저 현금 총액 업데이트
                }

                // 행원의 시재금을 0으로 설정
                row.find('.prev-cash-balance-init').val('0');
                row.find('.prev-cash-balance-init').data('prevCashBalance', 0); // 이전 시재금 값을 0으로 설정
            }
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
    $('#business-day-modal-emp-list .form-check-input').each(function () {
        $(this).prop('checked', false).closest('tr').removeClass('selected');
        $(this).closest('tr').find('.prev-cash-balance-init').prop('disabled', true); // 처음엔 모두 비활성화
    });
}

// 서버에서 근무자 데이터를 가져와서 모달에 표시하는 함수
function handleWorkers() {
    $.ajax({
        url: '/api/manager/business-day-close',
        type: 'GET',
        success: function (response) {
            // 직원 리스트 초기화
            $('#business-day-modal-emp-list').empty();

            const branchBalance = response.vaultCashOfBranch;
            const formattedBranchBalance = new Intl.NumberFormat().format(branchBalance);
            $('#business-day-modal-branch-balance').val(formattedBranchBalance); // 포맷팅된 값 적용
            $('#business-day-modal-prev-cash-balance').val(formattedBranchBalance); // 포맷팅된 값 적용

            response.closingDataList.forEach(function (employee) {


                    // 직원 정보 처리
                    let vaultCash = employee.prevCashBalance;
                    let prevCashBalance = 0;
                    let roleDisplay = employee.roles === 'ROLE_EMPLOYEE' ? '행원' : employee.roles;

                    // vaultCash와 prevCashBalance를 포맷팅
                    let formattedVaultCash = new Intl.NumberFormat().format(vaultCash);
                    let formattedPrevCashBalance = new Intl.NumberFormat().format(prevCashBalance)

                    let employeeRow = `
                        <tr class="business-day-element">
                            <td style="width: 6%">
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" role="switch">
                                </div>
                            </td>
                            <td style="width: 8%; text-align: center">${employee.id}</td>
                            <td style="width: 8%">${employee.name}</td>
                            <td>${roleDisplay}</td>
                            <td>
                                <input type="text" value="${formattedVaultCash}" disabled>
                            </td>
                            <td>
                                <input class="prev-cash-balance-init" type="text" value="${formattedPrevCashBalance}" disabled>
                            </td>
                        </tr>
                    `;
                    $('#business-day-modal-emp-list').append(employeeRow);

            });

            // 스위치 초기화 상태 맞추기
            resetSwitchStates();
        },
        error: function (xhr, status, error) {
            console.error('에러 발생:', error);
        }
    });
}

// 영업일 변경 처리 함수
function changeBusinessDay() {
    const data = [];

    // 각 business-day-element 행을 순회
    $('.business-day-element').each(function () {
        const row = $(this); // 현재 행
        const isChecked = row.find('.form-check-input').prop('checked'); // 스위치 상태

        // 스위치 상태에 따라 status 결정
        const status = isChecked ? 'OPEN' : 'CLOSED';

        // ID, 이름, 이전 캐시 밸런스 값 추출
        const id = row.find('td:nth-child(2)').text().trim(); // 두 번째 열에서 id
        const name = row.find('td:nth-child(3)').text().trim(); // 세 번째 열에서 name
        // 지정한 시재금 값 저장
        const prevCashBalance = row.find('.prev-cash-balance-init').val().trim().replace(/,/g, '');

        // 추출한 데이터를 객체로 만들고 배열에 추가
        data.push({
            id: id,
            name: name,
            prevCashBalance: prevCashBalance,
            status: status
        });
    });

    const businessDayUpdate = {
        workerDataList: data,
        prevCashBalanceOfBranch: $('#business-day-modal-prev-cash-balance').val().trim().replace(/,/g, ''),
        cashBalanceOfBranch: $('#business-day-modal-branch-balance').val().trim().replace(/,/g, ''),
        businessDateToChange: $('#next-business-day').val()
    }


    console.log("CLOSING TESETSET", data);
    alert("CLOSING TESETSET", data);
    console.log("DATE TESET", businessDayUpdate);

    $.ajax({
        url: '/api/manager/business-day-change',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify(businessDayUpdate), // JSON.stringify를 사용하여 객체를 JSON 문자열로 변환
        success: function (response) {
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
        error: function (xhr, status, error) {
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
