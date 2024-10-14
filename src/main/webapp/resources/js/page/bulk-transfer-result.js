document.addEventListener("DOMContentLoaded", function () {

    fillBulkTransferInfoListBody();

});


function fillBulkTransferInfoListBody(){
    $.ajax({
        url: '/api/employee/bulk-transfer-trade?bulkTransferId=' + 1, // API endpoint
        type: 'GET',
        success: function (bulkTransferInfoList) {

            var tbody = $('#bulk-transfer-info-list-body');
            tbody.empty(); // 기존 내용을 비웁니다.

            // 서버에서 받은 데이터를 기반으로 테이블 생성
            $.each(bulkTransferInfoList, function (index, bulkTransferInfo) {


                var row = $('<tr>').addClass('bulk-transfer-info-element').attr('data-trade-id', bulkTransferInfo.id);

                row.append($('<td><label><input type="checkbox"/></label></td>'));
                row.append($('<td>').text(++index));
                row.append($('<td>').text(bulkTransferInfo.status));
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