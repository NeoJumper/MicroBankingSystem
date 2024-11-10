$(document).ready(function () {
    $('#transfer-result-modal').on('hidden.bs.modal', function () {
        resultModalClose();
    })
})

function resultModalClose() {
    window.location.href = `/page/employee/account-transfer`;
}