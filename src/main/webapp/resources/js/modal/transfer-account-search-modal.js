$(document).ready(function () {

    $('#search-transfer-account-modal-btn').click(function () {

        searchAutoTransferAccountData();  // 계좌 조회 함수 호출
    });

    $('#modal-check-account-reset-btn').click(function () {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });

    // 모달이 닫힐 때 테이블 초기화
    $('#search-modal-account').on('hide.bs.modal', function () {
        resetAccountInput();  // 입력 및 테이블 초기화 함수 호출
    });



});


function searchAutoTransferAccountData() {
    //const customerId = '1';

    const customerId = $('#customer-id-input').val();

       $.ajax({
           url: '/api/employee/transfer-accounts/' + customerId,
           type: 'GET',
           success: function (data) {
               var accountTableBody = $("#search-modal-common-table tbody");
               accountTableBody.empty();

               $.each(data, function (index, account) {
                   var openDateOnly = account.openDate.split(' ')[0];

                   var row = "<tr>" +
                       "<td><input type='radio' name='selected-transfer-account-id' value='" + account.accId + "' class='selected-transfer-account-radio'></td>" +
                       "<td>" + account.accId + "</td>" +
                       "<td>" + openDateOnly + "</td>" +
                       "<td>" + account.customerName + " 님</td>" +
                       "<td>" + account.productName + "</td>" +
                       "<td>" + account.balance.toLocaleString('ko-KR') + " 원</td>" +
                       "</tr>";
                   accountTableBody.append(row);
               });

           },
           error: function (error) {
               console.error('error:', error);
           }
       });


}


// 입력 및 테이블 초기화 함수
function resetAccountInput() {
    $('#modal-input-account').val('');
    var accountTableBody = $("#search-modal-common-table tbody");
    accountTableBody.empty();
}




