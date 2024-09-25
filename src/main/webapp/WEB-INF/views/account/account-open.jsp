<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <!-- jquery 소스-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        function customerSearchModalPopup() {
            $("#showSearchCustomerModal").modal("show");

        }


        function customerSearchModalEvent() {
            // 셀렉트 요소 가져오기
            $('#searchBtn').on('click', function () {
                // 드롭다운에서 선택된 값과 입력 필드의 값 가져오기
                const searchType = $('#customerSearch').val();
                const searchQuery = $('#searchQuery').val();

                alert(searchType + "searchType" + searchQuery + "searchQuery");

                // 유효성 검사: 검색어가 비어 있는지 확인
                if (!searchQuery) {
                    alert("검색어를 입력해주세요.");
                    return;
                }

                // 선택된 검색 타입에 따라 쿼리스트링을 동적으로 구성
                let queryParam = '';
                if (searchType === 'customerName') {
                    queryParam = 'customerName=' + searchQuery;
                    alert(searchType);
                } else if (searchType === 'customerNumber') {
                    queryParam = 'customerNumber=' + searchQuery;
                    alert(searchType);
                } else if (searchType === 'customerPhone') {
                    queryParam = 'customerPhone=' + searchQuery;
                    alert(searchType);
                }

                // AJAX 요청 (GET 방식)
                $.ajax({
                    url: '/api/employee/customer?' + queryParam,
                    method: 'GET',
                    success: function (response) {
                        console.log("Response from server:", response);
                        var newBody = $('<tbody id="customer-info">');

                        response.forEach(function (item) {
                            console.log("Item:", item); // 개별 항목 확인

                            var row = $('<tr>')
                                .append($('<td>').append($('<input class="form-check-input row-radio" type="radio" name="selectedCustomer">')))
                                .append($('<td>').text(item.customerId))
                                .append($('<td>').text(item.customerName))
                                .append($('<td>').text(item.formattedBirthDate)) //getformattedBirthDate()함수호출
                                .append($('<td>').text(item.phoneNumber))
                                .append($('<td>').text(item.branchId));

                            newBody.append(row);
                        });

                        $('#customer-info').empty();
                        $('#customer-info').replaceWith(newBody);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                        console.error('검색 실패:', errorThrown);
                        alert('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
                    }
                });
            });
        }

        function insertCustomerId() {

            $('#modal-customer-select').on('click', function () {

                const selectedCustomer = $('input[name="selectedCustomer"]:checked');

                if (selectedCustomer.length > 0) {

                    const selectedRow = selectedCustomer.closest('tr');
                    const customerId = selectedRow.find('td:nth-child(2)').text();
                    $('#customerNumber').val(customerId);


                    $('#showSearchCustomerModal').modal('hide');

                } else {
                    alert('고객을 선택해 주세요.');
                }
            });

        }


        function accoutOpenProductInfo(){
            // 상품이율 구하기
            $.ajax({
                url: '/api/employee/account/productInterest',
                method: 'GET',
                success: function(data) {
                    $('#productInterest').val(data.interestRate);
                },
                error: function(error) {
                    console.error('Error fetching product interest:', error);
                    $('#productInterest').val('Failed to load data.');
                }
            });

        }

        // 기준이율 + 우대이율 = 총 이자율 계산
        function calculateTotalInterest() {
            const productInterest = parseFloat($('#productInterest').val()) || 0; // 기준이율
            const preferredInterest = parseFloat($('#preferredInterest').val()) || 0; // 우대이율

            const totalInterest = productInterest + preferredInterest;
            $('#totalInterest').val(totalInterest.toFixed(2)); // 소수점 2자리까지 출력
        }



        function clearCustomerSearchModal() {
            $('#customerNumber').val('');
            $('#customerName').val('');
            $('#customerBirth').val('');
            $('#customerPhone').val('');
            $('#searchQuery').val('');
            $('#customerSearch').prop('selectedIndex', 0);
            $('#customer-info').empty();
        }

        $(document).ready(function () {
            accoutOpenProductInfo();

            $("#customerNumber").on("click", function () {

                customerSearchModalPopup();
                customerSearchModalEvent();
                insertCustomerId();
            });


            $('#preferredInterest').on('input', function() {
                calculateTotalInterest();
            });
        });

    </script>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<%@ include file="/resources/components/modal/show-search-customer-modal.jsp" %>
<div id="mainArea">
    <div>
        <h5>예금 관리 > </h5>
        <h5>&nbsp 계좌 개설 </h5>
    </div>

    <div>
        <h3>계좌 개설 정보 입력</h3>
        <hr>
    </div>
    <table class="commonTable">
        <tr>
            <th>고객번호</th>
            <td><input type="text" id="customerNumber" readonly >
                <span class="bi bi-search" style="position: absolute; left: 5px; top: 50%; transform: translateY(-50%);"></span></td>
            <th>비밀번호</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>시작일자</th>
            <td><input type="date"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>잔액(KRW)</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>기준이율</th>
            <td><input type="text" id="productInterest" disabled> %</td>

            <th>우대이율</th>
            <td><input type="text" id="preferredInterest"> %</td>
        </tr>
        <tr>
            <th>총 이자율</th>
            <td><input type="text" id="totalInterest" disabled> %</td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text" value="유은서" disabled></td>
            <td colspan="2"></td>
        </tr>
    </table>

    <div id="accountOpenInsertBtn"  style="text-align:center;">
        <button  class="btn btn-primary">추가</button>

    </div>
    <div id="modalArea">

    </div>

</div>
<script src="/resources/js/footer.js"></script>
</body>
</html>
