$('document').ready(function () {
    handleTransferData();
})

function handleTransferData(){

    const params = new URLSearchParams(window.location.search);
    const tradeNumber = params.get('tradeNumber');

    alert("tradeNumber"+tradeNumber);

    // tradeNumber 값이 존재할 때
    if(tradeNumber){
        $.ajax({
            type: 'GET',
            url: '/api/employee/account-transfer/cancel/' + tradeNumber,
        })
    }

}