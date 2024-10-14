let selectedEmpId = null;
let employeeDataForUpload = [];

document.addEventListener("DOMContentLoaded", function () {
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

})