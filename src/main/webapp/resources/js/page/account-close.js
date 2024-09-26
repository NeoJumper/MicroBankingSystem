$(document).ready(function () {
    $('#accountSearchModal').on('hidden.bs.modal', function () {
        var accountNumber = $('#account-number').val();
        let accountData = {};
        if (accountNumber) {
            $.ajax({
                url: '/api/employee/account-close-details/'+accountNumber,
                type: 'GET',
                success: function (data) {
                    // 테이블 초기화
                    $('#table-content tbody').empty();
                    // 취소신청이 완료된 계좌는 alert로 알림
                    if(data.accountStatus === "CLS"){window.alert("취소 신청이 완료된 계좌입니다."); return;}
                    accountData = data;
                    console.log(data)
                    const registrationDate = new Date(data.amountDate);
                    const now = new Date();
                    const totalDays = Math.floor((now - registrationDate)/1000/60/60/24);
                    const totalIntRate = data.interestRateSum + data.accountPreInterRate;
                    const totalPayment = data.accountBal + data.amountSum;
                    console.log("=========================");
                    console.log(data.accountId);
                    $('#table-content tbody').append(
                        '<tr>' +
                            '<td style="width: 5%;">' + totalDays +'일'+ '</td>' +
                            '<td style="width: 5%;">' + totalIntRate +'%'+ '</td>' +
                            '<td style="width: 10%;">' + data.amountSum + '</td>' +
                            '<td style="width: 10%;">' + data.accountBal + '</td>' +
                            '<td style="width: 10%;">' + data.productTaxRate +'%'+ '</td>' +
                            '<td style="width: 10%;">' + totalPayment + '</td>' +
                        '</tr>'
                        );
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error('Error fetching data:', textStatus, errorThrown);
                }
            });
        } else {
            // 입력이 비어있으면 테이블 초기화
            $('#data-table tbody').empty();
        }
    });

    $('#submitBtn').click(function () {
        
    })
});
