
$(document).ready(function() {
    // 임시 오늘 날짜 지정
    setNowDate();

    $('.amount-btn').click(function() {
        setAmount(this);
    });
});

function setNowDate() {
    // 오늘 날짜를 구하는 함수
    var today = new Date();
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);  // 월은 0부터 시작하므로 1을 더함
    var day = ('0' + today.getDate()).slice(-2);
    var formattedDate = year + '-' + month + '-' + day;

    // input[type="date"]에 오늘 날짜 설정
    $('#execution-date').val(formattedDate);
}

function setAmount(button) {
    $('.amount-btn').removeClass('active');
    $(button).addClass('active');

    var amountText = $(button).text().replace(/[^0-9]/g, ''); // 숫자만 추출
    var amount = parseInt(amountText) * 10000;  // 만 단위로 변환

    // 만약 '전액' 버튼을 누르면 전체 잔액을 설정할 수 있음
    if ($(button).text() === '전액') {
        amount = parseInt($('#account-balance').text().replace(/[^0-9]/g, ''));
    }

    // 실제 값은 hidden input에 저장
    $('#transfer-amount').val(amount);

    // 천 단위 콤마는 display input에 표시
    $('#transfer-amount-display').val(amount.toLocaleString('ko-KR') + ' 원');
}
