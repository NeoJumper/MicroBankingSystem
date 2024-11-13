document.addEventListener("DOMContentLoaded", function () {
    // 현재 상태 확인
    isClosed();
    
    let handoverTotal = 0;
    let receiptTotal = 0;

    // 'amount' 클래스 요소를 모두 가져와 순회하면서 합계 계산
    document.querySelectorAll('.amount').forEach(function (element) {
        const amount = parseFloat(element.getAttribute('data-amount')) || 0;

        if (element.classList.contains('HANDOVER')) {
            handoverTotal += amount;
        } else if (element.classList.contains('RECEIVE')) {
            receiptTotal += amount;
        }
    });

    // 계산 결과 표시
    document.getElementById("handoverTotal").value = handoverTotal.toLocaleString();
    document.getElementById("receiptTotal").value = receiptTotal.toLocaleString();


    const closeButton = document.getElementById("cash-exchange-close");

    closeButton.addEventListener("click", function () {
        let managerCashBalanceVal = $('#lastManagerCash').val();
        managerCashBalanceVal = managerCashBalanceVal.replace(/,/g, '');

        // 숫자 변환
        let managerCashBalance = Number(managerCashBalanceVal);

        $.ajax({
            url: '/api/manager/cash-exchange-close',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({managerCashBalance: managerCashBalance}),
            success: function () {
                swal({
                        title: "시재금 마감 완료",
                        text: "금일 시재금 거래 마감이 성공적으로 완료되었습니다.",
                        icon: "success",
                        button: "닫기",
                    }
                );

                isClosed();

            },
            error: function (xhr, status, error) {
                swal({
                    title: "마감 실패",
                    text: xhr.responseText,
                    icon: "error",
                    button: "닫기"
                })
            }
        })
    });
});
