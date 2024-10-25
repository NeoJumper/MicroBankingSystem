$(document).ready(function () {
    $('#result-modal-open-account').on('hidden.bs.modal', function () {
        resultModalClose();
    })
})

function resultModalClose() {
    window.location.href = `/page/employee/account-open`;
}