$(document).ready(function () {
    updateTransactionTitle();
    registerClickEventOfEmpSearchBtn();
    handleEmpDataOfUpdateForm();
})

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


function handleEmpDataOfUpdateForm(){

    // 특정 파라미터 값 가져오기 (예: "id"라는 파라미터가 있을 때)
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');

    // 파라미터 값이 있을 경우에만 수정 폼을 채워준다.
    if(id)
    {
        $.ajax({
            type: 'GET',
            url: '/api/manager/employee/' + id,  // 서버의 URL로 변경
            success: function(employeeDetail) {

                console.log("selectedEmpId",id);
                console.log(employeeDetail);

            },
            error: function(error) {
                alert('데이터 전송 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    }
}