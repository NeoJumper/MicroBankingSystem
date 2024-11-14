$(document).ready(function () {


    // 검색 버튼 클릭 이벤트
    $('#search-button').on('click', function() {
        getListOfReserve();
    });

});

function getListOfReserve(){
    const selectedValue = $('input[name="major-category"]:checked').val();

    if (selectedValue === 'reserve') {
        loadReserveTransferData(); // 예약 이체 데이터를 불러옵니다.
    } else if (selectedValue === 'auto') {
        loadAutoTransferData(); // 자동 이체 데이터를 불러옵니다.
    }
}

function renderTableData(data) {
    const tbody = $('#reserve-trade-result-tbody');
    tbody.empty(); // 기존 데이터를 지움

    if (data.length === 0) {
        tbody.append('<tr class="reserve-empty-message"><td colspan="8" style="text-align: center; color: gray; border-bottom: none; height: 100px">조회 조건에 맞는 데이터가 없습니다.</td></tr>');
        return;
    }

    data.forEach(item => {
        const row = `<tr>
                <td>${item.id || '-'}</td>
                <td>${item.tradeDate || '-'}</td>
                <td>${item.accId || '-'}</td>
                <td>${item.targetAccId || '-'}</td>
                <td>${item.amount || '-'}</td>
                <td>${item.status ? '성공' : '실패'}</td>
                <td>${item.retryCount || '-'}</td>
            </tr>`;
        tbody.append(row);
    });
}



function loadReserveTransferData() {
    alert(">>>>>>>>>>예약이체");
    $.ajax({
        url: '/api/employee/auto-transfer/result-list', // 예약 이체 데이터 API 경로
        method: 'GET',
        success: function(response) {

            renderTableData(response);
        },
        error: function(error) {
            console.error("예약 이체 데이터를 가져오는 중 오류 발생:", error);
        }
    });
}

function loadAutoTransferData() {
    alert(">>>>>>>>>>자동이체");
    $.ajax({
        url: '/api/employee/auto-transfer/auto-list', // 자동 이체 데이터 API 경로
        method: 'GET',
        success: function(response) {
            renderTableData(response);
        },
        error: function(error) {
            console.error("자동 이체 데이터를 가져오는 중 오류 발생:", error);
        }
    });
}




