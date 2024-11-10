$(document).ready(function() {

    registerClickEventOfUpdateBtn(); // 수정 버튼 클릭 이벤트 리스너
    //registerClickEventOfDeleteBtn(); // 삭제 버튼 클릭 이벤트 리스너


    $('#customer-detail-modal').on('hidden.bs.modal', function () {
        resultModalClose();
    })
});

/**
 * 이벤트 등록 함수 목록
 */
function registerClickEventOfUpdateBtn() {


    $('#detail-modal-emp-update-btn').click(function(event) {
        console.log("redirect update Page");
        let customerId = $('#detail-modal-emp-id').val();

        redirectCustomerUpdatePage(customerId);
    });
}

/**
 * 기능 함수 목록
 */

function redirectCustomerUpdatePage(customerId){

    window.location.href = `/page/common/customer-update?id=${customerId}`;
}

function resultModalClose() {
    let extractUrlName = extractUrl(window.location.href);
    if(extractUrlName === 'customer-save')
        window.location.href = `/page/common/customer-save`;
    else if (extractUrlName === 'customer-update')
        window.location.href = `/page/common/customer-update`;
}
function extractUrl(url){
    //console.log(url);
    const splitUrl1 = url.split("/").pop();
    return  splitUrl1.split("?").shift();
}