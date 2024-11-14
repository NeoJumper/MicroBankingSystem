$(document).ready(function () {
    $('#savings-result-modal').on('hidden.bs.modal', function () {
        resultModalClose();
    })
})

function resultModalClose() {
    window.location.href = `/page/employee/savings-account-open`;
}


