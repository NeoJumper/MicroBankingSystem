let selectedEmpId = null;
let employeeDataForUpload = [];
let validPassword = "";

document.addEventListener("DOMContentLoaded", function () {
    // 모달 계좌선택 버튼
    $('#search-modal-select-account-btn').click(function () {
        selectAccount();
    })
    function selectAccount() {

        var selectedRow = $('input[name="select-account"]:checked').closest('tr');  // 선택된 라디오 버튼이 속한 행을 가져옴
        var selectedAccountId = selectedRow.find('td:eq(1)').text();  // 해당 행의 2번째 열(계좌번호 열)에서 값 추출

        if (!selectedAccountId) {
            swal({
                title: "계좌를 선택해 주세요.",
                // text: "비밀번호 인증 성공",
                icon: "warning",
            });
            return;
        }
        // 선택된 계좌번호로 서버에 다시 요청해서 계좌 정보 가져오기
        $.ajax({
            url: "/api/employee/accounts",
            data: {accId: selectedAccountId, productName: null},
            type: "GET",
            success: function (data) {
                $('#account-number').text(data[0].accId);
                $('#account-balance').text(data[0].balance.toLocaleString());
                $('#transferable-amount').text(data[0].balance.toLocaleString());

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
                    } else {
                        accountData = data;

                        const textAfterInter = Number(data.amountSum) * (1 - Number(data.productTaxRate));
                        const totalPayment = data.accountBal + textAfterInter;
                        accountData.textAfterInter = textAfterInter;
                        accountData.totalPayment = totalPayment;

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
    $('input[name="salaryType"]').change(function () {
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
        const pw = $('#account-pw-input').val();
        var accountNumber = $('#account-number').text();

        $.ajax({
            url: '/api/employee/account-validate',
            contentType: "application/x-www-form-urlencoded",
            type: "POST",
            data: {
                accountNumber: accountNumber,
                password: pw
            },
            success: function (response) {
                swal({
                    title: "검증 완료",
                    text: "비밀번호 인증 성공",
                    icon: "success",
                })
                validPassword = pw;
                //비밀번호 성공시 opacity 스타일 제거

                $('input[value="예금주 확인"]').prop('disabled', false);

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

    // 파일등록 버튼
    $('#uploadEmployeeBtn').click(uploadEmployeeBtnClickHandler);
    function uploadEmployeeBtnClickHandler() {
        var myModal = new bootstrap.Modal(document.getElementById('uploadEmployeeModal'));
        myModal.show();
    }

    // 개별추가 버튼
    $('#uploadIndividualEmployeeBtn').click(uploadIndividualEmployeeBtnClickHandler);
    function uploadIndividualEmployeeBtnClickHandler() {
        var myModal = new bootstrap.Modal(document.getElementById('uploadIndividualEmployeeModal'));
        myModal.show();
    }

    // 개별추가 - 추가
    $('#uploadIndivisualEmployeePreviewBtn').click(uploadIndivisualEmployeePreviewBtnClickHandler);
    function uploadIndivisualEmployeePreviewBtnClickHandler() {
        var tbody = $('#employeeTablePreviewBody');
        tbody.empty();
        if (
            $('#targetAccIdModal').val() === "" ||
            $('#transferAmountModal').val() === "" ||
            $('#krwModal').val() === "" ||
            $('#depositorModal').val() === "" ||
            $('#descriptionModal').val() === ""
        ) {
            
            // 모달 초기화
            $('#targetAccIdModal').val("");
            $('#transferAmountModal').val("");
            $('#krwModal').val("");
            $('#depositorModal').val("");
            $('#descriptionModal').val("");
            swal({
                title: "개별 추가 실패",
                text: "모든 입력창을 채워주세요.",
                icon: "error",
                buttons: {
                    cancel: true,
                    confirm: false,
                },
            });
            return;
        }

        employeeDataForUpload.push(
            {
                accId: $('#account-number').text(),
                accountPassword: validPassword,
                targetAccId: $('#targetAccIdModal').val(),
                transferAmount: $('#transferAmountModal').val(),
                krw: $('#krwModal').val(),
                depositor: $('#depositorModal').val(),
                description: $('#descriptionModal').val(),
            }
        );

        $.each(employeeDataForUpload, function (index, employee) {
            var row = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id);

            row.append($('<td>').text(++index));
            row.append($('<td>').text(employee.targetAccId));
            row.append($('<td>').text(employee.transferAmount));
            row.append($('<td>').text(employee.krw));
            row.append($('<td>').text(employee.depositor));
            row.append($('<td>').text(''));
            row.append($('<td>').text(employee.description));

            tbody.append(row);
        });

        // 모달 초기화
        $('#targetAccIdModal').val("");
        $('#transferAmountModal').val("");
        $('#krwModal').val("");
        $('#depositorModal').val("");
        $('#descriptionModal').val("");
    }

    // 엑셀 업로드
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
                    row.append($('<td>').text(''));
                    row.append($('<td>').text(employee.description));

                    tbody.append(row);

                    employeeDataForUpload.push(
                        {
                            accId: $('#account-number').text(),
                            accountPassword: validPassword,
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

        $('#excelInput').val('');
    }

    // 이체하기 
    $('input[value="이체실행"]').click(transferExecution);
    function transferExecution() {
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
            error: function (data) {
                console.log("실패");

            }
        });

    }

    // 계좌 유효성 검증
    $('input[value="예금주 확인"]').click(validationExecution);
    function validationExecution() {
        $.ajax({
            type: "POST",
            url: "/api/employee/bulk-transfer/validation",
            contentType: 'application/json',
            data: JSON.stringify(employeeDataForUpload),
            success: function (data) {

                var tbody = $('#employeeTablePreviewBody');
                tbody.empty(); // 기존 내용을 비웁니다.

                // 비워줌
                employeeDataForUpload = [];

                // 서버에서 받은 데이터를 기반으로 테이블 생성
                $.each(data.bulkTransferValidationList, function (index, employee) {
                    let backgroundColor = "red";
                    if (employee.depositor == employee.validDepositor) {
                        backgroundColor = "green";
                    }
                    var row = $('<tr>').addClass('employee-element').attr('data-emp-id', employee.id);

                    row.append($('<td>').text(++index));
                    row.append($('<td>').text(employee.targetAccId));
                    row.append($('<td>').text(employee.transferAmount));
                    row.append($('<td>').text(employee.krw));
                    if (backgroundColor == "red") {
                        row.append($('<td>').text(employee.depositor).css('color', '#D40000'));
                        row.append($('<td>').text(employee.validDepositor).css('color', '#D40000'));
                    } else {
                        row.append($('<td>').text(employee.depositor));
                        row.append($('<td>').text(employee.validDepositor));
                    }
                    row.append($('<td>').text(employee.description));

                    tbody.append(row);

                    employeeDataForUpload.push(
                        {
                            accId: $('#account-number').text(),
                            accountPassword: "1234",
                            targetAccId: employee.targetAccId,
                            transferAmount: employee.transferAmount,
                            krw: employee.krw,
                            depositor: employee.depositor,
                            validDepositor: employee.validDepositor,
                            description: employee.description,
                        }
                    );
                });

                $('#total-registrations').text(data.totalCnt);
                $('#valid-recipients').text(data.normalCnt);
                $('#mismatch-recipients').text(data.inconsistencyCnt);
                $('#error-recipients').text(data.errorCnt);

                $('#result-content-div').show();
                $('input[value="초기화"]').show();
                $('input[value="이체실행"]').show();

                $('.progress-container .step:nth-of-type(1)').removeClass('active');

                if ($('.progress-container .step:nth-of-type(1) .inner-circle').is(':empty')) {
                    $('.progress-container .step:nth-of-type(1) .inner-circle').append('<i class="bi bi-check"></i>');
                }

                $('.progress-container .step:nth-of-type(1) .inner-circle').removeClass('active');


                $('.progress-container .step:nth-of-type(3)').addClass('active');
                $('.progress-container .step:nth-of-type(3) .circle').addClass('active');
                $('.progress-container .step:nth-of-type(3) .inner-circle').addClass('active');

                // 조회완료 alert
                if (data.bulkTransferValidationList.length) {
                    swal({
                        title: "예금주 확인",
                        text: "예금주 확인 완료",
                        icon: "success",
                    })

                    $('input[value="이체실행"]').removeAttr('disabled');
                }

            },
            error: function (data) {
                console.log("실패");

            }
        });
    }

    // 검색어로 조회하기
    $('#searchInput').on('input', function () {
        var searchValue = $(this).val(); // 입력된 검색어
        var searchCondition = $('#searchCondition').val(); // 선택된 검색 조건
        var tbody = $('#employeeTablePreviewBody');

        // 기존 내용 비우기
        tbody.empty();

        // 검색 로직 (예시)
        if (searchValue === "" || searchCondition === "") {
            // 검색어가 없거나 조건이 없을 경우 모든 데이터 표시
            $.each(employeeDataForUpload, function (index, employee) {
                let backgroundColor = "red";
                if (employee.depositor === employee.validDepositor) {
                    backgroundColor = "green";
                }
                var row = $('<tr>').addClass('employee-element')
                    .attr('data-emp-id', employee.id)
                    .addClass(backgroundColor === "red" ? 'failure' : '');

                row.append($('<td>').text(++index));
                row.append($('<td>').text(employee.targetAccId));
                row.append($('<td>').text(employee.transferAmount));
                row.append($('<td>').text(employee.krw));
                row.append($('<td>').text(employee.depositor));
                row.append($('<td>').text(employee.validDepositor));
                row.append($('<td>').text(employee.description));

                tbody.append(row);
            });
        } else {
            // 검색어와 조건이 있을 경우 필터링
            let filteredEmployees;
            if (searchCondition === "targetAccId") {
                filteredEmployees = employeeDataForUpload.filter((item) => {
                    return item.targetAccId.includes(searchValue); // 검색어로 필터링
                });
            } else if (searchCondition === "depositor") {
                filteredEmployees = employeeDataForUpload.filter((item) => {
                    return item.depositor.includes(searchValue); // 검색어로 필터링
                });
            }

            // 필터링된 데이터 표시
            $.each(filteredEmployees, function (index, employee) {
                let backgroundColor = "red";
                if (employee.depositor === employee.validDepositor) {
                    backgroundColor = "green";
                }
                var row = $('<tr>').addClass('employee-element')
                    .attr('data-emp-id', employee.id)
                    .addClass(backgroundColor === "red" ? 'failure' : '');

                row.append($('<td>').text(++index));
                row.append($('<td>').text(employee.targetAccId));
                row.append($('<td>').text(employee.transferAmount));
                row.append($('<td>').text(employee.krw));
                row.append($('<td>').text(employee.depositor));
                row.append($('<td>').text(employee.validDepositor));
                row.append($('<td>').text(employee.description));

                tbody.append(row);
            });
        }
    });

    // 초기화
    $('input[value="초기화"]').click(initExecution);
    function initExecution() {

        var tbody = $('#employeeTablePreviewBody');
        tbody.empty(); // 기존 내용을 비웁니다.
        employeeDataForUpload = [];

        $('#result-content-div').hide();
        $('input[value="초기화"]').hide();
        $('input[value="이체실행"]').hide();

        $('.progress-container .step:nth-of-type(3)').removeClass('active');
        $('.progress-container .step:nth-of-type(3) .circle').removeClass('active');
        $('.progress-container .step:nth-of-type(3) .inner-circle').removeClass('active');
    }
})