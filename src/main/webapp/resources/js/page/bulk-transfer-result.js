document.addEventListener("DOMContentLoaded", function () {
    const url = window.location.href;

    // bulkTransferId 값 추출
    const bulkTransferId = getParameterByName('bulkTransferId', url);
    fillBulkTransferInfoListBody(bulkTransferId);



});


function fillBulkTransferInfoListBody(bulkTransferId){
    $.ajax({
        url: '/api/employee/bulk-transfer-trade?bulkTransferId=' + bulkTransferId, // API endpoint
        type: 'GET',
        success: function (bulkTransferInfoList) {

            var tbody = $('#bulk-transfer-info-list-body');
            tbody.empty(); // 기존 내용을 비웁니다.

            // 서버에서 받은 데이터를 기반으로 테이블 생성
            $.each(bulkTransferInfoList, function (index, bulkTransferInfo) {

                console.log(bulkTransferInfo);
                var row = $('<tr>').addClass('bulk-transfer-info-element').attr('data-trade-id', bulkTransferInfo.id);

                row.append($('<td><label><input type="checkbox"/></label></td>'));
                row.append($('<td>').text(++index));
                if (bulkTransferInfo.status === 'FAIL') {
                    row.append($('<td>').text(bulkTransferInfo.status).css('color', '#D40000'));
                } else {
                    row.append($('<td>').text(bulkTransferInfo.status));
                }
                row.append($('<td>').text(bulkTransferInfo.targetAccId));
                row.append($('<td>').text(bulkTransferInfo.amount));
                row.append($('<td>').text(bulkTransferInfo.targetName));
                row.append($('<td>').text(bulkTransferInfo.description));
                row.append($('<td>').text(bulkTransferInfo.failureReason));

                tbody.append(row);

            });
        },
        error: function (xhr, status, error) {
            console.error('Upload failed!');
            console.error(error); // Handle errors
        }
    });
}

function getParameterByName(name, url) {
    // URL에서 쿼리 파라미터를 찾기 위한 정규식 생성
    const urlParams = new URLSearchParams(new URL(url).search);
    return urlParams.get(name); // 해당 파라미터의 값을 반환
}