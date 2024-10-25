document.addEventListener("DOMContentLoaded", function () {
    let handoverTotal = 0;
    let receiptTotal = 0;

    // 'amount' 클래스 요소를 모두 가져와 순회하면서 합계 계산
    document.querySelectorAll('.amount').forEach(function (element) {
        const amount = parseFloat(element.getAttribute('data-amount')) || 0;

        if (element.classList.contains('HANDOVER')) {
            handoverTotal += amount;
        } else if (element.classList.contains('RECEIPT')) {
            receiptTotal += amount;
        }
    });

    // 계산 결과 표시
    document.getElementById("handoverTotal").value = handoverTotal.toLocaleString();
    document.getElementById("receiptTotal").value = receiptTotal.toLocaleString();


    const closeButton = document.getElementById("cash-exchange-close");

    closeButton.addEventListener("click", function () {
        // $.ajax({
        //     url: '/api/manager/cash-exchange-close',
        //     type: 'PATCH',
        //     contentType: 'application/json',
        //     data: JSON.stringify(),
        // })
    });
});
