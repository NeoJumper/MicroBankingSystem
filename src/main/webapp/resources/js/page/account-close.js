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

    // *자신의 페이지에 맞게 구현
    $('#modal-select-account').click(function() {
        selectAccount();  // 선택된 계좌 처리 함수 호출
    });


});

    function selectAccount() {
    var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
    var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

    if (!selectedAccountId) {
        alert("계좌를 선택해 주세요.");
        return;
    }
    // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
    $.ajax({
        url: "/api/employee/account",
        data: { accId: selectedAccountId, productName: null },
        type: "GET",
        success: function(data) {
            $('#account-number').val(data[0].accId);
            $('#product-name').val(data[0].productName);
            $('#customer-name').val(data[0].customerName);
            // 모달 닫기
            $('#accountSearchModal').modal('hide');
        },
        error: function(error) {
            console.log("Error while fetching account details", error);
        }
    });
}
