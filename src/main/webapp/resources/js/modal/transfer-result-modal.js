$(document).ready(function () {
    $('#result-modal-close-btn').on('click', function () {
        resultModalClose();
    })

})

function resultModalClose() {
    window.location.href = `/page/employee/account-transfer`;
}