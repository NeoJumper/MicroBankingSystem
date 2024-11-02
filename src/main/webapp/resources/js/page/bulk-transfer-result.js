let employeeDataForUpload = [];
let errorItems = [];
let checkedData = [];
let url;

document.addEventListener("DOMContentLoaded", function () {
    url = window.location.href;
    const bulkTransferId = getParameterByName('bulkTransferId', url);
    fillBulkTransferInfoListBody(bulkTransferId);

    registerClickEventOfSelectAllCheckbox();
    registerClickEventOfBack();
    registerClickEventOfResendErrorItems();

    registerClickEventOfSearch();
    registerClickEventOfFileUpload();
    registerClickEventOfPrint();
});

function registerClickEventOfSelectAllCheckbox() {
    $('#bulk-transfer-info thead input[type="checkbox"]').click(function () {
        toggleAllCheckboxes($(this).is(':checked'));
    });
}

function registerClickEventOfSearch() {
    $('#searchInput').on('input', function () {
        handleSearch($(this).val(), $('#searchCondition').val());
    });
}

function registerClickEventOfFileUpload() {
    $('input[value="파일등록"]').click(function () {
        handleFileUpload();
    });
}

function registerClickEventOfPrint() {
    $('input[value="인쇄"]').click(function () {
        handlePrint();
    });
}

function registerClickEventOfBack() {
    $('#back-btn').click(function () {
        handleBack();
    });
}

function registerClickEventOfResendErrorItems() {
    $('#resend-error-item').click(function () {
        resendErrorItem();
    });
}

function toggleAllCheckboxes(isChecked) {
    $('#bulk-transfer-info tbody input[type="checkbox"]').prop('checked', isChecked);
}

function handleSearch(searchValue, searchCondition) {
    const tbody = $('#bulk-transfer-info-list-body');
    tbody.empty();

    if (searchValue === "" || searchCondition === "") {
        displayAllData();
    } else {
        displayFilteredData(searchValue, searchCondition);
    }
}

function displayAllData() {
    const tbody = $('#bulk-transfer-info-list-body');
    $.each(employeeDataForUpload, function (index, bulkTransferInfo) {
        tbody.append(createRow(bulkTransferInfo, ++index));
    });
}

function displayFilteredData(searchValue, searchCondition) {
    const tbody = $('#bulk-transfer-info-list-body');
    let filteredData = employeeDataForUpload.filter(item => {
        if (searchCondition === "targetAccId") {
            return item.targetAccId.includes(searchValue);
        } else if (searchCondition === "depositor") {
            return item.targetName.includes(searchValue);
        }
        return false;
    });

    $.each(filteredData, function (index, bulkTransferInfo) {
        tbody.append(createRow(bulkTransferInfo, ++index));
    });
}

function handleFileUpload() {
    const formattedData = employeeDataForUpload.map(item => ({
        '입금계좌번호': item.targetAccId,
        '처리결과': item.status,
        '이체금액(원)': item.amount,
        '받는분': item.targetName,
        '받는분 통장표시': item.description,
        '비고': item.failureReason,
    }));

    const workbook = new ExcelJS.Workbook();
    const worksheet = workbook.addWorksheet("입금계좌정보");

    worksheet.columns = [
        { header: '입금계좌번호', key: '입금계좌번호', width: 20 },
        { header: '처리결과', key: '처리결과', width: 15 },
        { header: '이체금액(원)', key: '이체금액(원)', width: 15 },
        { header: '받는분', key: '받는분', width: 20 },
        { header: '받는분 통장표시', key: '받는분 통장표시', width: 25 },
        { header: '비고', key: '비고', width: 35 }
    ];

    formattedData.forEach(item => worksheet.addRow(item));

    workbook.xlsx.writeBuffer().then(function(buffer) {
        const blob = new Blob([buffer], { type: 'application/octet-stream' });
        const url = URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = '입금계좌정보.xlsx';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    });
}

function handlePrint() {
    const printWindow = window.open('', '', 'height=600,width=800');
    printWindow.document.write('<html><head><title>인쇄</title>');
    printWindow.document.write('<link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>');
    printWindow.document.write('<link rel="stylesheet" type="text/css" href="/resources/css/page/bulk-transfer.css"/>');
    printWindow.document.write('<link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>');
    printWindow.document.write('<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">');
    printWindow.document.write('</head><body style="padding: 15px;">');

    const sectionA = $('#sectionA').clone();
    const sectionB = $('#sectionB').clone();
    printWindow.document.write(sectionA.prop('outerHTML'));
    printWindow.document.write(sectionB.prop('outerHTML'));
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();
}

function handleBack() {
    history.replaceState(null, '', document.referrer);
    window.history.back();
}

function resendErrorItem() {
    const bulkTransferId = getParameterByName('bulkTransferId', url);
    window.location.href = '/page/employee/bulk-transfer?bulkTransferId=' + bulkTransferId;
}

function fillBulkTransferInfoListBody(bulkTransferId) {
    $.ajax({
        url: '/api/employee/bulk-transfer-trade?bulkTransferId=' + bulkTransferId,
        type: 'GET',
        success: function (bulkTransferInfoList) {
            employeeDataForUpload = bulkTransferInfoList;
            errorItems = bulkTransferInfoList.filter(item => item.status === "FAIL");

            const tbody = $('#bulk-transfer-info-list-body');
            tbody.empty();

            $.each(bulkTransferInfoList, function (index, bulkTransferInfo) {
                tbody.append(createRow(bulkTransferInfo, ++index));
            });
        },
        error: function (xhr, status, error) {
            console.error('Upload failed!');
            console.error(error);
        }
    });
}

function createRow(bulkTransferInfo, index) {
    const row = $('<tr>').addClass('bulk-transfer-info-element').attr('data-trade-id', bulkTransferInfo.id);

    row.append($('<td><label><input type="checkbox"/></label></td>'));
    row.append($('<td>').text(index));
    row.append($('<td>').text(bulkTransferInfo.status).css('color', bulkTransferInfo.status === 'FAIL' ? '#D40000' : ''));
    row.append($('<td>').text(bulkTransferInfo.targetAccId));
    row.append($('<td>').text(bulkTransferInfo.amount.toLocaleString()));
    row.append($('<td>').text(bulkTransferInfo.targetName));
    row.append($('<td>').text(bulkTransferInfo.description));
    row.append($('<td>').text(bulkTransferInfo.failureReason));

    return row;
}

function getParameterByName(name, url) {
    const urlParams = new URLSearchParams(new URL(url).search);
    return urlParams.get(name);
}
