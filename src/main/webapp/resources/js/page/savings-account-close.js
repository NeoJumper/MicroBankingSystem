$(document).ready(function () {
    $('#search-modal-select-account-btn').click(function () {
        selectSavingsAccount();
    });

});

function selectSavingsAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');
    var accountId = selectedRow.find('td:eq(1)').text();

    if (!accountId) {
        swal({
            title: "선택된 계좌 없음",
            text: "계좌를 선택해 주세요.",
            icon: "warning",
        });
        return;
    }

    // 1. 선택 계좌 정보
    // 1-1. 계좌 번호, 고객 이름, 제품명
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/accounts",
        data: {accId: accountId},
        type: "GET",
        success: function (data) {
            console.log("saving-account-close DATA TEST", data)
            $('#savings-account-close-number').val(data[0].accId);
            $('#savings-account-product-name').val(data[0].productName);
            $('#customer-name').val(data[0].customerName);

            $('#savings-account-product-type').text(data[0].productType);
            $('#savings-account-interest-calculation-method').text(data[0].interestCalculationMethod);
            $('#search-modal-account').modal('hide');


            // 2. 정기 적금 / 자유 적금 분기처리
            // 2-1. 정기적금일 경우
            if (data[0].productType == "FIXED") {
                // 3. 선택 계좌 세부 정보
                getSavingsAccount(data);
                // 적금 세부정보 표시
                $('.common-account-detail').show();
                $('.fixed-account-area').show();
                $('.flexible-account-area').hide();

            }
            // 2-2. 자유적금일 경우
            else if (data[0].productType == "FLEXIBLE") {
                getSavingsFlexibleAccount(data, accountId);
                // 적금 세부정보 표시
                $('.common-account-detail').show();
                $('.fixed-account-area').hide();
                $('.flexible-account-area').show();

            }

        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });
}

// 자유적금 해지 프로세스
function getSavingsFlexibleAccount(data, accountId) {
    // 3. 월별 이자내역 출력
    $.ajax({
        url: "/api/employee/interest-details/" + accountId,
        type: "GET",
        success: function (data) {
            console.log("account close flexible", data);
        }
    })
}


// 정기적금 해지 프로세스
function getSavingsAccount(data) {

    var accountId = data[0].accId;

    if (!accountId) {
        swal({
            title: "계좌를 선택해 주세요.",
            icon: "warning",
        });
        return;
    }
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/savings-account-close-details/" + accountId,
        type: "GET",
        success: function (data) {
            console.log("======!!!", data);
            console.log(data);
            $('#savings-account-close-number').val(data.accountId);
            $('#savings-account-product-name').val(data.productName);
            $('#customer-name').val(data.customerName);

            $('#product-type').val(data.productType);

            let period;
            let productRate;
            let savingsDays;
            let productTotalDays;

            calculateRate(period, productRate, savingsDays, productTotalDays);

            $('#savings-account-close-info').append(


            );

            $('#savings-account-total-cash').append(
                '<tr>' +
                '<td style="width: 5%;">' + data.productType + '</td>' +
                '<td style="width: 5%;">' + data.productInterestRate + '%' + '</td>' +
                '<td style="width: 5%;">' + data.accountInterestRate + '%' + '</td>' +
                '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                '<td style="width: 5%;">' + '세전 이자' + '</td>' +
                '<td style="width: 5%;">' + '세후 이자' + '원' + '</td>' +
                '<td style="width: 10%;">' + '계산 완료된 이자금액' + '</td>' +
                /* 이자가 포함된 총 지급 금액*/
                '<td style="width: 10%;">' + data.amountSum + '</td>' +
                '</tr>'
            );
            // 모달 닫기
            $('#search-modal-account').modal('hide');
        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });

}

function calculateRate(period, productRate, savingsDays, productTotalDays) {
    var result;

    switch (period) {
        case 'under-1m':
            result = productRate;
            break;
        case 'under-3m':
            result = productRate * 0.5;
            break;
        case 'under-6m':
            result = productRate * 0.6;
            break;
        case 'over-6m':
            result = productRate * 0.6 * (savingsDays / productTotalDays);
            break;
        case 'over-9m':
            result = productRate * 0.7 * (savingsDays / productTotalDays);
            break;
        case 'over-11m':
            result = productRate * 0.9 * (savingsDays / productTotalDays);
            break;
        case 'maturity':
            // 만기 시 이율 계산
            result = productRate;
            break;
        case 'after-maturity':
            // 만기 후 이율 계산
            if (savingsDays <= 30) {
                result = productRate * 0.5;
            } else {
                result = productRate * 0.25;
            }
            break;
    }

    return result;
}