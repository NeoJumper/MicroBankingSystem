$(document).ready(function () {


    customerSearchInputEnterEvent();
    clickSearchModalSearchBtn();
    handleChangeSearchOption();

});
function handleChangeSearchOption() {
    $('#search-modal-select').change(function() {
        var selectedOption = $(this).val();
        var placeholderText = '';


        // 선택된 옵션에 따라 placeholder 텍스트 설정
        if (selectedOption === 'id') {
            placeholderText = '고객번호를 입력하세요';
        } else if (selectedOption === 'name') {
            placeholderText = '이름을 입력하세요';
        } else if (selectedOption === 'phone_number') {
            placeholderText = '전화번호를 입력하세요';
        }

        // search-modal-input의 placeholder 업데이트
        $('#search-modal-input').val('');
        $('#search-modal-input').attr('placeholder', placeholderText);
    });
}

function customerSearchInputEnterEvent() {

    $('#search-modal-input').keydown(function(event) {

        if (event.which === 13) {
            searchEmployee();
        }
    });
}

function clickSearchModalSearchBtn() {
    $("#search-modal-search-btn").on("click", function () {
        searchEmployee();
    });
}

function searchEmployee(){
    // 선택된 검색 옵션과 입력된 검색어 가져오기
    let searchOption = $('#search-modal-select').val();
    let searchValue = $('#search-modal-input').val();
    console.log("option : " + searchOption);
    console.log("value : "+searchValue);
    // Ajax 요청
    $.ajax({
        url: '/api/manager/employee2',
        type: 'GET',
        data: {
            searchOption: searchOption,
            searchValue: searchValue
        },
        success: function(response) {
            // 성공 시 처리할 로직 작성
            $('#search-modal-employee-information').empty();
            response.employeeSearchInfoList.forEach(function(employee) {
                // 새로운 행을 생성하고 테이블에 추가
                let roleDisplay = employee.roles === 'ROLE_EMPLOYEE' ? '행원' : '매니저';
                let newRow = `
                            <tr class="employee-element">
                                <td style="width: 6%;"><input class="form-check-input row-radio" type="radio" name="selected-employee"></td>
                                <td style="width: 10%;">${employee.id}</td>
                                <td style="width: 10%;">${employee.name}</td>
                                <td style="width: 20%;">${employee.phoneNumber}</td>
                                <td style="width: 20%;">${employee.email}</td>
                                <td style="width: 10%;">${roleDisplay}</td>
                            </tr>
                        `;

                console.log("new",newRow)
                $('#search-modal-employee-information').append(newRow); // 새 데이터 추가
            });

            $('.employee-element').on('click', function() {
                // 해당 tr 안의 라디오 버튼을 체크
                $(this).find('.row-radio').prop('checked', true);
            });

            // 라디오 버튼이 클릭되었을 때도 체크되도록 설정
            $('.row-radio').on('click', function(e) {
                e.stopPropagation();  // 이벤트 전파 중단 (tr 클릭이 중복 처리되지 않도록)
            });

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

