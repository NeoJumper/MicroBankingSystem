let employeeDataForUpload = [];

document.addEventListener("DOMContentLoaded", function () {
    const url = window.location.href;

    // bulkTransferId 값 추출
    const bulkTransferId = getParameterByName('bulkTransferId', url);
    fillBulkTransferInfoListBody(bulkTransferId);

    // 검색어로 조회하기
    $('#searchInput').on('input', function () {
        var searchValue = $(this).val(); // 입력된 검색어
        var searchCondition = $('#searchCondition').val(); // 선택된 검색 조건
        var tbody = $('#bulk-transfer-info-list-body');

            // 기존 내용 비우기
            tbody.empty();

            // 검색 로직 (예시)
            if (searchValue === "" || searchCondition === "") {
                // 검색어가 없거나 조건이 없을 경우 모든 데이터 표시
                $.each(employeeDataForUpload, function (index, bulkTransferInfo) {

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
            } else {
                // 검색어와 조건이 있을 경우 필터링
                let filteredEmployees;
                if (searchCondition === "targetAccId") {
                    filteredEmployees = employeeDataForUpload.filter((item) => {
                        return item.targetAccId.includes(searchValue); // 검색어로 필터링
                    });
                } else if (searchCondition === "depositor") {
                    filteredEmployees = employeeDataForUpload.filter((item) => {
                        return item.targetName.includes(searchValue); // 검색어로 필터링
                    });
                }

                // 필터링된 데이터 표시
                $.each(filteredEmployees, function (index, bulkTransferInfo) {

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
            }
        });

    });

// table 채우는 메서드
function fillBulkTransferInfoListBody(bulkTransferId){
    $.ajax({
        url: '/api/employee/bulk-transfer-trade?bulkTransferId=' + bulkTransferId, // API endpoint
        type: 'GET',
        success: function (bulkTransferInfoList) {
            employeeDataForUpload = bulkTransferInfoList;

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