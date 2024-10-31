$(document).ready(function () {
    $('#search-modal-select-account-btn').click(function () {
        selectSavingsAccount();
    });

    $('#savings-account-close-validate').click(function(){
        checkAccountId();
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
                $('#flexible-account-interest').hide();
                $('#fixed-account-interest').show();

            }
            // 2-2. 자유적금일 경우
            else if (data[0].productType == "FLEXIBLE") {
                getSavingsFlexibleAccount(data, accountId);
                // 적금 세부정보 표시
                $('.common-account-detail').show();
                $('.fixed-account-area').hide();
                $('.flexible-account-area').show();
                $('#flexible-account-interest').show();
                $('#fixed-account-interest').hide();
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
        url: "/api/employee/savings-account-close-total-info/"+accountId,
        type: "GET",
        success: function (data) {
            console.log("======!!!", data);
            console.log(data.productType);

            $('#product-type').val(data.productType);

            let period = data.productPeriod;
            let productRate = data.productInterestRate;
            let accountRate = data.accountInterestRate;
            console.log("accountRate"+accountRate+"productRate"+productRate);
            let startDate = data.startDate;

            const result = calculateSavingsMonths("2024-08-01",startDate);
            console.log(`총 일수: ${result.totalDays}, 적금 넣은 개월 수: ${result.monthsSaved}`);

            let savingsDays = result.totalDays;

            // 적금 만기일 구하기
            calculateEndDate(startDate, period);
            console.log("시작일" + startDate);

            console.log("만기일" + calculateEndDate(startDate, period));

            // 적금 계약일수 구하기
            let productTotalDays = calculateContractDays(startDate,period).contractDays;

            // 적금 이율 구하기
            calculateRate(accountRate, productRate, savingsDays, productTotalDays);

            // 경과일 대비 이율 계산
            const rates = calculateRate(productRate, savingsDays, productTotalDays);

            // 조건별 이율 계산 결과 입력
            calculationResultInterest(rates);
            $('#savings-account-close-info').empty();
            $('#savings-account-total-cash').empty();

            $('#savings-account-close-info').append(

                '<tr>' +
                '<td style="width: 5%;">' +  + '</td>' +
                '<td style="width: 5%;">' + data.productInterestRate + '%' + '</td>' +
                '<td style="width: 5%;">' + data.accountInterestRate + '%' + '</td>' +
                '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                '<td style="width: 5%;">' + '세전 이자'+   + '</td>' +
                '<td style="width: 5%;">' + '세후 이자' + 'data.productTaxRate'+ '원' + '</td>' +
                '<td style="width: 10%;">' + '계산 완료된 이자금액'  + '</td>' +
                /* 이자가 포함된 총 지급 금액*/
                '<td style="width: 10%;">' + data.amountSum + '</td>' +
                '</tr>'

            );

            $('#savings-account-total-cash').append(
                '<tr>' +
                '<td style="width: 5%;">' + data.productType  + '</td>' +
                '<td style="width: 5%;">' + data.productInterestRate + '%' + '</td>' +
                '<td style="width: 5%;">' + data.accountInterestRate + '%' + '</td>' +
                '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                '<td style="width: 5%;">' + '세전 이자'+   + '</td>' +
                '<td style="width: 5%;">' + '세후 이자' + 'data.productTaxRate'+ '원' + '</td>' +
                '<td style="width: 10%;">' + '계산 완료된 이자금액'  + '</td>' +
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


// 적금 종료일 구하기
function calculateEndDate(startDateString, months) {
    const startDate = new Date(startDateString); // 시작일을 Date 객체로 변환

    // 유효한 날짜인지 확인
    if (isNaN(startDate)) {
        throw new Error('유효하지 않은 날짜 형식입니다.'); // 유효하지 않은 날짜 경고
    }

    // months가 문자열일 경우 숫자로 변환
    const monthsToAdd = Number(months);

    // 종료일 계산
    startDate.setMonth(startDate.getMonth() + monthsToAdd); // 개월 수만큼 더하기

    // 종료일을 ISO 형식으로 반환
    return startDate.toISOString().split('T')[0];
}

// 적금 상품의 계약일수 & 종료일
function calculateContractDays(contractStartDate, months) {
    const startDate = new Date(contractStartDate);
    const endDateString = calculateEndDate(contractStartDate, months); // 종료일을 문자열로 받아옴
    const endDate = new Date(endDateString); // 종료일을 Date 객체로 변환
    console.log(endDateString+"endDateString");

    // 두 날짜 사이의 일수 계산
    const differenceInTime = endDate - startDate;
    const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24)); // 밀리초를 일수로 변환

    return {
        endDate: endDate.toISOString().split('T')[0], // 종료일
        contractDays: differenceInDays // 계약일수
    };
}

function checkAccountId() {
    const inputId = $('#savings-account-close-password').val();
    var accountNumber = $('#acco unt-number').val();

    $.ajax( {
        url: '/api/employee/account-validate',
        contentType: "application/x-www-form-urlencoded",
        type: "POST",
        data: {
            accountNumber: accountNumber,
            password: inputId
        },
        success: function (response) {
            swal({
                title: "검증 완료",
                text: "비밀번호 인증 성공",
                icon: "success",
            })

            //비밀번호 성공시 opacity 스타일 제거
            $('#submit-btn').removeAttr('style');
            $('#submit-btn').prop('disabled', false);

        }, error: function (error){
            swal({
                title: "검증 실패",
                text: error.responseText,
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });

            console.log("Transfer failed", error);
        }
    })
}


// 기간별 이율 계산
function calculateRate( productRate, savingsDays, productTotalDays) {
    const rates = {
        'under-1m': productRate * 0.001, // 연 0.1%
        'under-3m': productRate * 0.0015, // 연 0.15%
        'under-6m': productRate * 0.002, // 연 0.2%
        'over-6m': 0, // 초기값
        'between-9-11m': 0,
        'over-11m': 0,
        'maturity': productRate, // 만기 시 약정 당시의 정기 적금 금리
        'after-maturity-1m': savingsDays <= 30 ? productRate * 0.005 : 0, // 만기 후 1개월 이내
        'after-maturity-1m-plus': savingsDays > 30 ? productRate * 0.0025 : 0 // 만기 후 1개월 초과
    };

    // 차등율 적용
    if (savingsDays < 9 * 30) {
        rates['over-6m'] = productRate * 0.6 * (savingsDays / productTotalDays); // 6개월 이상 ~ 9개월 미만
    } else if (savingsDays < 11 * 30) {
       rates['between-9-11m'] = productRate * 0.7 * (savingsDays / productTotalDays); // 9개월 이상 ~ 11개월 미만
    } else {
        rates['over-11m'] = productRate * 0.9 * (savingsDays / productTotalDays); // 11개월 이상
    }


    for (const key in rates) {
        if (isNaN(rates[key])) {
            rates[key] = 0; // NaN인 경우 0으로 설정
            console.log("rates NAN!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        }
    }

    return rates;
}

// 이율 결과 입력하기
function calculationResultInterest(rates) {
    $('#under-1m .dynamic-rate').text(rates['under-1m'].toFixed(4) + '%');
    $('#under-3m .dynamic-rate').text(rates['under-3m'].toFixed(4) + '%');
    $('#under-6m .dynamic-rate').text(rates['under-6m'].toFixed(4) + '%');
    $('#over-6m .dynamic-rate').text(rates['over-6m'].toFixed(4) + '%');
    $('#between-9-11m .dynamic-rate').text(rates['between-9-11m'].toFixed(4) + '%');
    $('#over-11m .dynamic-rate').text(rates['over-11m'].toFixed(4) + '%');
    $('#maturity .dynamic-rate').text(rates['maturity'].toFixed(4) + '%');
    $('#post-maturity-1m .dynamic-rate').text(rates['after-maturity-1m'].toFixed(4) + '%');
    $('#post-maturity-1m-plus .dynamic-rate').text(rates['after-maturity-1m-plus'].toFixed(4) + '%');
}

// 일수 계산 : 적금 해지시 해지일까지의 총 개월 수 & 일 수 계산
function calculateSavingsMonths(nowDate, startDate) {
    const currentDate = new Date(nowDate);
    const startDateObj = new Date(startDate);

    const startMonth = startDateObj.getMonth(); // 시작 월 (0부터 시작)
    const endMonth = currentDate.getMonth(); // 현재 월 (0부터 시작)

    // 월별 일수
    const daysInMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // 윤년을 고려하여 2월의 일수
    if ((currentDate.getFullYear() % 4 === 0 && currentDate.getFullYear() % 100 !== 0) ||
        (currentDate.getFullYear() % 400 === 0)) {
        daysInMonths[1] = 29; // 윤년인 경우 2월은 29일
    }

    let totalDays = 0;

    // 시작 월의 남은 일수 계산
    if (startMonth === endMonth) {
        totalDays = currentDate.getDate() - startDateObj.getDate() + 1;
    } else {
        // 시작 월의 남은 일수
        totalDays += daysInMonths[startMonth] - startDateObj.getDate() + 1;

        // 중간 월의 전체 일수 계산
        for (let month = startMonth + 1; month < endMonth; month++) {
            totalDays += daysInMonths[month];
        }

        // 해지시 총 일수
        totalDays += currentDate.getDate();
    }

    // 총 적금 개월 수
    const monthsSaved = endMonth - startMonth + 1;

    return {
        totalDays,
        monthsSaved
    };
}

