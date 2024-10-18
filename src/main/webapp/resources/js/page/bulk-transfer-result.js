let employeeDataForUpload = [];
let checkedData = [];

document.addEventListener("DOMContentLoaded", function () {
    const url = window.location.href;

    // bulkTransferId 값 추출
    const bulkTransferId = getParameterByName('bulkTransferId', url);
    fillBulkTransferInfoListBody(bulkTransferId);

    // 모든 체크박스 선택
    $('#bulk-transfer-info thead input[type="checkbox"]').click(function() {
        var isChecked = $(this).is(':checked');
        // tbody의 모든 체크박스를 선택 또는 해제
        $('#bulk-transfer-info tbody input[type="checkbox"]').prop('checked', isChecked);
    });

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

    // 파일등록 클릭
    $('input[value="파일등록"]').click(function () {


        // 엑셀 컬럼명 변경
        var formattedData = checkedData.map(item => ({
            '입금계좌번호': item.targetAccId,
            '처리결과': item.status,
            '이체금액(원)': item.amount,
            '받는분': item.targetName,
            '받는분 통장표시': item.description,
            '비고': item.failureReason,
        }));

        // JSON 데이터를 워크북으로 변환
        var worksheet = XLSX.utils.json_to_sheet(formattedData);
        var workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, "입금계좌정보");

        // 엑셀 파일 다운로드
        XLSX.writeFile(workbook, "입금계좌정보.xlsx");
    });
    
    }); // DOMContentLoaded 이벤트 끝

    // table 채우는 메서드
    function fillBulkTransferInfoListBody(bulkTransferId){
        $.ajax({
            url: '/api/employee/bulk-transfer-trade?bulkTransferId=' + bulkTransferId, // API endpoint
            type: 'GET',
            success: function (bulkTransferInfoList) {
                employeeDataForUpload = bulkTransferInfoList;
                console.log("this is bulkTransferInfoList: ", employeeDataForUpload);
                var tbody = $('#bulk-transfer-info-list-body');
                tbody.empty(); // 기존 내용을 비웁니다.

                // 서버에서 받은 데이터를 기반으로 테이블 생성
                $.each(bulkTransferInfoList, function (index, bulkTransferInfo) {

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