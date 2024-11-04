$(document).ready(function () {
    $('#search-modal-select-account-btn').click(function () {
        selectSavingsAccount();
    });

    $('#savings-account-close-validate').click(function () {
        checkAccountId();
    });

    $('#saving-account-close-submit-btn').click(function () {
        submitSavingAccountClose();
    })

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
            $('#savings-account-product-type').val(data[0].productType);
            $('#savings-account-interest-calculation-method').text(data[0].interestCalculationMethod);
            $('#savings-account-interest-calculation-method').val(data[0].interestCalculationMethod);
            $('#search-modal-account').modal('hide');


            // 2. 정기 적금 / 자유 적금 분기처리
            // 2-1. 정기적금일 경우
            if (data[0].productType == "FIXED") {
                // 3. 선택 계좌 세부 정보
                getSavingsAccount(data, accountId);
                // 적금 세부정보 표시
                $('.common-account-detail').show();
                //예금 예상 이자 및 총 금액
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

// 최종 이율 계산 함수
function calculateTerminationRate(openDate, businessDate, period, interestRateSum) {
    const open = new Date(openDate.split(' ')[0]); // openDate에서 날짜 부분만 추출
    const business = new Date(businessDate);

    // 계약일수 계산
    const periodMonths = parseInt(period.replace(/[^0-9]/g, ''));
    let contractDays = 0;
    let current = new Date(open);

    // 만기일까지의 일수 계산
    for (let i = 0; i < periodMonths; i++) {
        const nextMonth = new Date(current.getFullYear(), current.getMonth() + 1, current.getDate());
        const daysInMonth = (nextMonth - current) / (1000 * 60 * 60 * 24);
        contractDays += daysInMonth;
        current = nextMonth;
    }

    // 경과일수: openDate로부터 businessDate까지의 일 수
    const diffDays = Math.floor((business - open) / (1000 * 60 * 60 * 24));

    // 최종 이율
    let finalInterestRate;
    // 소수 변환
    const baseInterestRate = interestRateSum / 100;
    // 해지 유형 분기처리
    let isEarlyTermination = false;
    let isMaturityTermination = false;

    // 만기일 이전 해지 (중도 해지)
    if (business < current) {
        // 중도 해지
        isEarlyTermination = true;

        if (diffDays < 30) {
            finalInterestRate = 0.001; // 연 0.1%
        } else if (diffDays < 90) {
            finalInterestRate = 0.0015; // 연 0.15%
        } else if (diffDays < 180) {
            finalInterestRate = 0.002; // 연 0.2%
        } else if (diffDays < 270) {
            finalInterestRate = Math.max(0.002, 0.6 * (diffDays / contractDays) * baseInterestRate); // Ensure minimum 0.2%
        } else if (diffDays < 330) {
            finalInterestRate = Math.max(0.002, 0.7 * (diffDays / contractDays) * baseInterestRate); // Ensure minimum 0.2%
        } else {
            finalInterestRate = Math.max(0.002, 0.9 * (diffDays / contractDays) * baseInterestRate); // Ensure minimum 0.2%
        }
    }
    // 만기일 해지
    else if (business.getTime() === current.getTime()) {
        finalInterestRate = baseInterestRate;
        isMaturityTermination = true;
    }
    // 만기 후 해지
    else {
        const diffDaysAfterMaturity = Math.floor((business - current) / (1000 * 60 * 60 * 24));
        if (diffDaysAfterMaturity <= 30) {
            finalInterestRate = baseInterestRate * 0.5; // 기본금리의 1/2
        } else {
            finalInterestRate = baseInterestRate * 0.25; // 기본금리의 1/4
        }
    }

    return {finalInterestRate, isEarlyTermination, isMaturityTermination, diffDays};
}

// 경과일수 구하는 함수
function calculateElapsedDays(openDate, months) {
    const open = new Date(openDate.split(' ')[0]); // openDate에서 날짜 부분만 추출
    const targetDate = new Date(open.getFullYear(), open.getMonth() + months, open.getDate());

    // targetDate가 openDate보다 미래 날짜인 경우에만 경과일수 계산
    if (targetDate > open) {
        return Math.floor((targetDate - open) / (1000 * 60 * 60 * 24));
    } else {
        return 0; // 이 경우는 계산이 무의미하거나 잘못된 입력일 수 있음
    }
}

function calculateRateData(openDate, businessDate, period, interestRateSum) {
    const open = new Date(openDate.split(' ')[0]); // openDate에서 날짜 부분만 추출
    const business = new Date(businessDate);

    // 계약일수 계산
    const periodMonths = parseInt(period.replace(/[^0-9]/g, ''));
    let contractDays = 0;
    let current = new Date(open);

    // 만기일까지의 일수 계산
    for (let i = 0; i < periodMonths; i++) {
        const nextMonth = new Date(current.getFullYear(), current.getMonth() + 1, current.getDate());
        const daysInMonth = (nextMonth - current) / (1000 * 60 * 60 * 24);
        contractDays += daysInMonth;
        current = nextMonth;
    }

    const elapsedDays6Months = calculateElapsedDays(openDate, 6);  // 6개월 동안의 경과일수
    const elapsedDays9Months = calculateElapsedDays(openDate, 9);  // 9개월 동안의 경과일수
    const elapsedDays11Months = calculateElapsedDays(openDate, 11);  // 11개월 동안의 경과일수

    // 소수 변환
    const baseInterestRate = interestRateSum / 100;

    // 각 케이스별 이율 계산
    const rateData = {
        "under-1m": "0.1 %", // 1개월 미만 연 0.1%
        "under-3m": "0.15 %", // 1개월 이상 ~ 3개월 미만 연 0.15%
        "under-6m": "0.2 %", // 3개월 이상 ~ 6개월 미만 연 0.2%
        "over-6m": `${(Math.max(0.002, 0.7 * (elapsedDays6Months / contractDays) * baseInterestRate) * 100).toFixed(4)} % ~`, // 6개월 이상 ~ 9개월 미만 60% 차등율, 최소 0.2%
        "between-9-11m": `${(Math.max(0.002, 0.7 * (elapsedDays9Months / contractDays) * baseInterestRate) * 100).toFixed(4)} % ~`, // 9개월 이상 ~ 11개월 미만 70% 차등율, 최소 0.2%
        "over-11m": `${(Math.max(0.002, 0.9 * (elapsedDays11Months / contractDays) * baseInterestRate) * 100).toFixed(4)} % ~`, // 11개월 이상 90% 차등율, 최소 0.2%
        "maturity": `${interestRateSum}%`,
        "post-maturity-1m": `${(0.5 * baseInterestRate * 100).toFixed(4)} %`, // 만기 후 1개월 이내 기본금리의 1/2
        "post-maturity-1m-plus": `${(0.25 * baseInterestRate * 100).toFixed(4)} %` // 만기 후 1개월 초과 기본금리의 1/4
    };

    return rateData;
}


// 자유적금 해지 프로세스
function getSavingsFlexibleAccount(data, accountId) {
    // 오늘 영업일
    const businessDate = $('#business-day-date').val();
    // 자유적금 해지를 위한 계좌 세부 정보
    // return CloseSavingsFlexibleAccountTotal
    $.ajax({
        url: "/api/employee/savings-flexible-account-close-total-info/" + accountId,
        type: "GET",
        success: function (data) {
            console.log("SELECT FLEXIBLE ACCOUNT", data);
            // 기존의 총 이율 합산
            const interestRateSum = data.interestRate + data.preferentialInterestRate;
            // 해지 이율 테이블 계산
            const rateData = calculateRateData(
                data.openDate,
                businessDate,
                data.period,
                interestRateSum
            );

            let highlightedKey = ""; // 해당 케이스 키

            // 이율 데이터에 대해 반복하며 적용 이율과 일치하는 키 찾기
            for (const [key, rate] of Object.entries(rateData)) {
                if (parseFloat(rate) === data.finalInterestRate * 100) { // 금리를 퍼센트로 변환하여 비교
                    highlightedKey = key;
                    break; // 일치하는 키를 찾으면 반복 중단
                }
            }

            for (const [key, rate] of Object.entries(rateData)) {
                const rateElement = $(`#${key} .dynamic-rate`);
                rateElement.text(rate);

                // 해당하는 키의 <td>에만 배경색 적용
                if (key === highlightedKey) {
                    rateElement.closest('tr').css('background-color', '#f6f9fc'); // 하이라이트 색상 설정
                    rateElement.closest('tr').css('color', '#3f5ba9');
                }
            }

            // 계좌 해지 정보 추가 - 만기일 추가 예정
            addCloseInfo(data, businessDate);
            // 이자 내역 테이블 추가
            addInterestList(data.interestDetailsList);
        }
    })
}

function addCloseInfo(data, businessDate) {
    let tbodyInfo = $('#savings-account-close-info');
    tbodyInfo.empty();

    let roundDownToTen = (Math.floor(data.totalAmount / 10) * 10).toLocaleString();

    let row = `
        <tr>
            <td>${data.closeTypeDescription}</td>
            <td>${data.openDate.split(" ")[0]}</td>
            <td>${data.expectedExpireDate.split(" ")[0]}</td>
            <td>${businessDate}</td>
            <td>${data.finalInterestRate * 100} %</td>
            <td>${data.totalAmount.toLocaleString()} 원</td>
            <td id="flexible-saving-account-total-amount">${roundDownToTen} 원</td>
        </tr>
    `;

    tbodyInfo.append(row);
}

function addInterestList(interestDetailsList) {
    // tbody 준비
    let tbody = $('#savings-account-flexible-monthly-interest-list').find('tbody');
    tbody.empty();

    if (interestDetailsList.length === 0) {
        // 데이터가 없을 경우 기본 메시지를 추가
        tbody.append(`
                <tr class="saving-account-close-empty-message">
                    <td colspan="5" style="text-align: center; color: gray; border-bottom: none; height: 100px">
                        이자 내역이 존재하지 않습니다.
                    </td>
                </tr>
            `);
    } else {
        // data 리스트의 각 항목을 tbody에 추가
        $.each(interestDetailsList, function (index, item) {
            let row = `
                    <tr>
                        <td>${item.creationDate.split(" ")[0]}</td>
                        <td><input type="text" value="${item.balance.toLocaleString()}" disabled/> 원</td>
                        <td>${item.interestRate} %</td>
                        <td>${item.preferentialInterestRate} %</td>
                        <td><input type="text" value="${item.amount.toLocaleString()}" disabled /> 원</td>
                    </tr>
                `;
            interestSum += item.amount;
            tbody.append(row);
        });
    }
}


// 정기적금 해지 프로세스
function getSavingsAccount(data, accountId) {

    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/savings-account-close-total-info/" + accountId,
        type: "GET",
        success: function (data) {
            console.log("======!!!", data);

            // 적용 이율(이율 합) = 우대 이율 + 기본 이율 ex) 0.1+3.5 = 3.6
            const interestSum = data.accountInterestRate + data.productInterestRate;

            const businessDate = $('#business-day-date').val();
            // - ! 지금 startDate로 받아와지는거 openDate로 받아와야 함

            // 해지 이율 계산

            const {finalInterestRate, isEarlyTermination} = calculateTerminationRate(
                data.openDate,
                businessDate,
                data.productPeriod,
                interestSum
            );


            const rateData = calculateRateData(
                data.openDate,
                businessDate,
                data.productPeriod,
                interestSum
            );

            let highlightedKey = ""; // 해당 케이스 키

            console.log("openDate" + data.openDate + "finalInterestRate Key:", finalInterestRate);

            // 이율 데이터에 대해 반복하며 적용 이율과 일치하는 키 찾기
            for (const [key, rate] of Object.entries(rateData)) {
                if (parseFloat(rate) === finalInterestRate * 100) { // 금리를 퍼센트로 변환하여 비교
                    highlightedKey = key;
                    break; // 일치하는 키를 찾으면 반복 중단
                }
            }

            for (const [key, rate] of Object.entries(rateData)) {
                const rateElement = $(`#${key} .dynamic-rate`);
                rateElement.text(rate);

                // 해당하는 키의 <td>에만 배경색 적용
                if (key === highlightedKey) {
                    console.log("Highlighted Key:", highlightedKey);
                    rateElement.closest('tr').css('background-color', '#f6f9fc'); // 하이라이트 색상 설정
                    rateElement.closest('tr').css('color', '#3f5ba9');
                }
            }

            console.log("FINAL INTEREST::", finalInterestRate);
            savingAccountFixedCloseRequest(data);

        },
        error: function (error) {
            console.log("Error while fetching account details", error);
        }
    });


}


// 일수 계산 : 적금 해지시 해지일까지의 총 개월 수 & 일 수 계산
/*
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
*/
function calculateSelectedRate(openDate, businessDate, period, interestRateSum) {
    // 기존 함수 호출
    const rateData = calculateRateData(openDate, businessDate, period, interestRateSum);

    // 각 조건에 대해 체크하고 첫 번째 맞는 값 반환
    if (rateData["under-1m"]) {
        return rateData["under-1m"];
    }
    if (rateData["under-3m"]) {
        return rateData["under-3m"];
    }
    if (rateData["under-6m"]) {
        return rateData["under-6m"];
    }
    if (rateData["over-6m"]) {
        return rateData["over-6m"];
    }
    if (rateData["between-9-11m"]) {
        return rateData["between-9-11m"];
    }
    if (rateData["over-11m"]) {
        return rateData["over-11m"];
    }
    if (rateData["maturity"]) {
        return rateData["maturity"];
    }
    if (rateData["post-maturity-1m"]) {
        return rateData["post-maturity-1m"];
    }
    if (rateData["post-maturity-1m-plus"]) {
        return rateData["post-maturity-1m-plus"];
    }

    return "조건에 맞는 이율이 없습니다"; // 모든 조건이 만족하지 않을 경우
}

function checkAccountId() {
    const inputId = $('#savings-account-close-password').val();
    var accountNumber = $('#savings-account-close-number').val();

    $.ajax({
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
            $('#saving-account-close-submit-btn').removeAttr('style');
            $('#saving-account-close-submit-btn').prop('disabled', false);

        }, error: function (error) {
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

function submitSavingAccountClose() {
    // 정기적금 해지 프로세스
    if ($('#savings-account-product-type').val("FIXED")) {
        savingAccountFixedCloseRequest();
    }
    // 자유적금 해지 프로세스
    else if ($('#savings-account-product-type').val("FLEXIBLE")) {
        savingAccountFlexibleCloseRequest();
    }
}

// 정기적금 해지 프로세스
function savingAccountFixedCloseRequest() {
    var accountNumber = $('#savings-account-close-number').val();
    var totalAmount = parseFloat($('#flexible-saving-account-total-amount').val().replace(/,/g, ''));
    $.ajax({
        url: '/api/employee/close-trade',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            accid: accountNumber,
            amount: totalAmount,
            status: "CLS",
            description: "자유 적금 계좌 해지",
            tradeType: "CLOSE"
        }),
        success: function (response) {
            swal({
                title: "해지 성공",
                text: "계좌 해지 완료되었습니다.",
                icon: "success",
            });

        }
    })
}

// 자유적금 해지 프로세스
function savingAccountFlexibleCloseRequest() {

}