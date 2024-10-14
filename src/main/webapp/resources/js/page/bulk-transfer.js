let selectedEmpId = null;
let employeeDataForUpload = [];

document.addEventListener("DOMContentLoaded", function () {
    // 모달 계좌선택 버튼
    $('#search-modal-select-account-btn').click(function () {
        selectAccount();
    })
    function selectAccount() {

        var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
        var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

        if (!selectedAccountId) {
            alert("계좌를 선택해 주세요.");
            return;
        }
        // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
        $.ajax({
            url: "/api/employee/accounts",
            data: {accId: selectedAccountId, productName: null},
            type: "GET",
            success: function (data) {
                console.log(data,"data====!");
                $('#account-number').text(data[0].accId);
                $('#account-balance').text(data[0].balance);
                $('#transferable-amount').text(data[0].balance);
                // $('#customer-name').val(data[0].customerName);
                // 모달 닫기
                $('#search-modal-account').modal('hide');
            },
            error: function (error) {
                console.log("Error while fetching account details", error);
            }
        });
    }
    //계좌 조회
    $('#search-modal-account').on('hidden.bs.modal', function () {
        console.log("모달 닫힘");
        getAccountDetail();
    });
    function getAccountDetail() {
        var accountNumber = $('#account-number').val();

        if (accountNumber) {
            $.ajax({
                url: '/api/employee/account-close-details/' + accountNumber,
                type: 'GET',
                success: function (data) {
                    // 테이블 초기화
                    $('#table-content tbody').empty();
                    // 취소신청이 완료된 계좌는 alert로 알림
                    if (data.accountStatus === "CLS") {
                        swal({
                            title: "해지 신청이 완료된 계좌입니다.",
                            // text: "비밀번호 인증 성공",
                            icon: "warning",
                        });
                        return;
                    }else{
                        accountData = data;
                        console.log(data)

                        const textAfterInter = Number(data.amountSum) * (1-Number(data.productTaxRate));
                        const totalPayment = data.accountBal + textAfterInter;
                        accountData.textAfterInter = textAfterInter;
                        accountData.totalPayment = totalPayment;
                        console.log("=========================accountDataTotalPaymemt");
                        console.log(accountData.totalPayment, "==============총 지급 금액")
                        console.log(data.accountId);
                        $('#table-content tbody').append(
                            '<tr>' +
                            '<td style="width: 5%;">' + data.accountBal + '원' + '</td>' +
                            '<td style="width: 5%;">' + data.productInterRate + '%' + '</td>' +
                            '<td style="width: 5%;">' + data.accountPreInterRate + '%' + '</td>' +
                            '<td style="width: 10%;">' + data.productTaxRate + '%' + '</td>' +
                            '<td style="width: 10%;">' + data.amountSum + '</td>' +
                            '<td style="width: 10%;">' + textAfterInter + '</td>' +
                            '<td style="width: 10%;">' + totalPayment + '</td>' +
                            '</tr>'
                        );
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error('Error fetching data:', textStatus, errorThrown);
                }
            });
        } else {
            // 입력이 비어있으면 테이블 초기화
            $('#data-table tbody').empty();
        }
    }

    // 비고란 문구 선택
    $('input[name="salaryType"]').change(function() {
        var selectedValue = $(this).val(); // 선택된 라디오 버튼의 값 가져오기

        if (selectedValue === 'directInput') {
            $('#description').removeAttr('disabled');
            $('#description').val('');
        } else {
            $('#description').val(selectedValue);
            $('#description').attr('disabled', 'true');
        }
    });

    // 계좌 비밀번호 확인
    $('#input-confirm').click(function () {
        checkAccountId();
    })
    function checkAccountId() {
        const inputId = $('#account-pw-input').val();
        var accountNumber = $('#account-number').text();
        console.log(accountNumber,"this is accountNumber");
        console.log(inputId,"this is inputId");
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

    // 파일 등록버튼
    $('#uploadEmployeeBtn').click(uploadEmployeeBtnClickHandler);
    function uploadEmployeeBtnClickHandler() {
        var myModal = new bootstrap.Modal(document.getElementById('uploadEmployeeModal'));
        myModal.show();
    }

    // 파일등록 유효성 검증
    $('#uploadEmployeePreviewBtnOfTable').click(uploadEmployeePreviewBtnOfTableClickHandler);

    function uploadEmployeePreviewBtnOfTableClickHandler() {
        var fileInput = document.getElementById('excelInput');
        var file = fileInput.files[0];

        var formData = new FormData();
        formData.append('file', file);

        // Send AJAX request
        $.ajax({
            url: '/api/employee/bulk-transfer/excel-upload', // API endpoint
            type: 'POST',
            data: formData,
            processData: false, // Prevent jQuery from automatically transforming the data into a query string
            contentType: false, // Set content type to false to let the browser set the correct value
            success: function (employees) {

                console.log(employees);
                var tbody = $('#employeeTablePreviewBody');
                tbody.empty(); // 기존 내용을 비웁니다.

                // 서버에서 받은 데이터를 기반으로 테이블 생성
                $.each(employees, function (index, employee) {

                    var row = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id);

                    row.append($('<td>').text(++index));
                    row.append($('<td>').text(employee.targetAccId));
                    row.append($('<td>').text(employee.transferAmount));
                    row.append($('<td>').text(employee.krw));
                    row.append($('<td>').text(employee.depositor));
                    row.append($('<td>').text(employee.description));

                    tbody.append(row);


                    employeeDataForUpload.push(
                        {
                            accId: "001-0000019-1834",
                            accountPassword: "1234",
                            targetAccId: employee.targetAccId,
                            transferAmount: employee.transferAmount,
                            krw: employee.krw,
                            depositor: employee.depositor,
                            description: employee.description,
                        }
                    );

                });
                // 마지막 행의 첫 번째 td 값 가져오기
                const lastRow = tbody.find('tr:last'); // jQuery를 사용하여 마지막 행 선택
                if (lastRow.length) { // 마지막 행이 존재하는 경우
                    console.log("마지막 행이 존재함");
                    const firstCellValue = lastRow.find('td:first').text(); // 첫 번째 td의 값 가져오기
                    console.log(firstCellValue); // 첫 번째 td의 값 출력
                    $('#total-registrations').text(firstCellValue); // 값을 설정
                } else {
                    console.log('테이블에 행이 없습니다.'); // 마지막 행이 없을 경우
                    $('#total-registrations').text('0'); // 기본값 설정
                }

            },
            error: function (xhr, status, error) {
                console.error('Upload failed!');
                console.error(error); // Handle errors
            }
        });
    }

    // 이체하기 
    $('input[value="이체실행"]').click(transferExecution);
    function transferExecution(){
        console.log(employeeDataForUpload,"employeeDataForUpload======!!");
        $.ajax({
            type: "POST",
            url: "/api/employee/bulk-transfer",
            contentType: 'application/json',
            data: JSON.stringify(employeeDataForUpload),
            success: function (bulkTransferId) {
                console.log("성공");
                // 리턴값으로 bulkTransferId 주면 파라미터로 담아서 보냄
                var url = '/page/employee/bulk-transfer-result?bulkTransferId=' + encodeURIComponent(bulkTransferId);
                window.location.href = url;
            },
            error: function(data){
                console.log("실패");

            }
        });


    }

})