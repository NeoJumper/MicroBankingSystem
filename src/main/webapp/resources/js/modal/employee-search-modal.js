$(document).ready(function () {


    /*각자 선택 버튼 클릭 시 함수 구현
    $('#search-modal-select-btn').on('click', function () {

    });*/

    $("#search-modal-search-btn").on("click", function () {
        searchEmployee();
    });
});


function searchEmployee(){
    // 선택된 검색 옵션과 입력된 검색어 가져오기
    let searchOption = $('#search-modal-select').val();
    let searchValue = $('#search-modal-input').val();
    console.log("option : " + searchOption);
    console.log("value : "+searchValue);
    // Ajax 요청
    $.ajax({
        url: '/api/manager/employee',
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
                let newRow = `
                            <tr class="employee-element">
                                <td style="width: 6%;"><input class="form-check-input row-radio" type="radio" name="selected-employee"></td>
                                <td style="width: 10%;">${employee.id}</td>
                                <td style="width: 10%;">${employee.name}</td>
                                <td style="width: 20%;">${new Date(employee.birthDate)}</td>
                                <td style="width: 20%;">${employee.phoneNumber}</td>
                                <td style="width: 20%;">${employee.email}</td>
                                <td style="width: 10%;">${employee.roles}</td>
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

