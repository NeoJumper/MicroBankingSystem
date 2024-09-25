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

        <%--function getCurrentDate() {--%>
        <%--    const today = new Date();--%>
        <%--    const year = today.getFullYear();--%>
        <%--    const month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1--%>
        <%--    const day = String(today.getDate()).padStart(2, '0');--%>

        <%--    return `${year}-${month}-${day}`; // YYYY-MM-DD 형식의 문자열 반환--%>
        <%--}--%>

        <%--function getNextMonthFirstDay(openDate) {--%>
        <%--    if (!openDate) {--%>
        <%--        return null; // openDate가 비어있으면 null 반환--%>
        <%--    }--%>

        <%--    const dateParts = openDate.split('-'); // YYYY-MM-DD 형식의 날짜를 분리--%>
        <%--    const year = parseInt(dateParts[0], 10);--%>
        <%--    const month = parseInt(dateParts[1], 10);--%>

        <%--    // 다음 달을 계산 (12월인 경우에는 1월로 넘어가고 연도를 증가)--%>
        <%--    const nextMonth = month === 12 ? 1 : month + 1;--%>
        <%--    const nextYear = month === 12 ? year + 1 : year;--%>

        <%--    return `${nextYear}-${String(nextMonth).padStart(2, '0')}-01`; // YYYY-MM-01 형식의 문자열 반환--%>
        <%--}--%>

        <%--function insertStartDate() {--%>
        <%--    const formattedDate = getCurrentDate();--%>
        <%--    $('#openDate').val(formattedDate);--%>

        <%--    const openDate = $('#openDate').val();--%>
        <%--    if (openDate) { // openDate가 비어있지 않은 경우에만 처리--%>
        <%--        const formattedNextMonthDate = getNextMonthFirstDay(openDate);--%>
        <%--        $('#startDate').val(formattedNextMonthDate);--%>
        <%--    } else {--%>
        <%--        console.error("openDate is empty or invalid.");--%>
        <%--    }--%>
        <%--}--%>



        function customerSearchModalPopup() {
            $("#showSearchCustomerModal").modal("show");

        }


        function customerSearchModalEvent() {
            // 셀렉트 요소 가져오기
            $('#modal-search-btn').on('click', function () {
                // 드롭다운에서 선택된 값과 입력 필드의 값 가져오기
                const searchType = $('#modal-search-customerSearch').val();
                const searchQuery = $('#modal-search-query').val();

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


                $.ajax({
                    url: '/api/employee/customer?' + queryParam,
                    method: 'GET',
                    success: function (response) {
                        console.log("Response from server:", response);
                        var newBody = $('<tbody id="modal-search-customer-info">');

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

                        $('#modal-search-customer-info').empty();
                        $('#modal-search-customer-info').replaceWith(newBody);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                        console.error('검색 실패:', errorThrown);
                        alert('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
                    }
                });
            });
        }

        function insertCustomerId() {

            $('#modal-search-customer-select').on('click', function () {

                const selectedCustomer = $('input[name="selectedCustomer"]:checked');

                if (selectedCustomer.length > 0) {

                    const selectedRow = selectedCustomer.closest('tr');
                    const customerId = selectedRow.find('td:nth-child(2)').text();
                    $('#customerIdText').val(customerId);

                    const customerName = selectedRow.find('td:nth-child(3)').text();
                    $('#customerName').val(customerName);


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

        // 계좌 생성 함수
        function accountOpen(){

            $('#accountCreateBtn').on("click",function (){


                const customerId = $('#customerIdText').val();
                const productId = $('#productId').val();

                const startDate = $('startDate').val();
                const preferredInterestRate =$('#preferredInterest').val();
                const password =$('#password').val();
                const balance =$('#balance').val();

                const empId =$('#empId').val();
                const branchName =$('#branchName').val();
                const branchId = $('#branchId').val();

                // 저장 안하지만 완료 정보 전달모달에서 필요함.
                const customerName =$('#customerName').val();
                const totalInterest = $('#totalInterest').val();
                const empName = $('#empName').val();



            });
        }

        function clearCustomerSearchModal() {
            $('#customerIdText').val('');
            $('#customerName').val('');
            $('#customerBirth').val('');
            $('#customerPhone').val('');
            $('#searchQuery').val('');
            $('#customerSearch').prop('selectedIndex', 0);
            $('#customer-info').empty();
        }

        $(document).ready(function () {
            accoutOpenProductInfo();


            $("#customerIdSearchBtn").on("click", function () {

                customerSearchModalPopup();
                customerSearchModalEvent();
                insertCustomerId();
            });

            // 상품의 기존이율 뿌리기
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
            <td style="display: flex; align-items: center;">
                <input type="text" id="customerIdText" readonly >
                <button type="button" id="customerIdSearchBtn" class="btn btn-primary" style="margin-left: 10px; padding: 5px; width:80px;height:40px">
                    <span class="bi bi-search" style="margin-right: 5px;"></span> 찾기
                </button>
            </td>
            <th>비밀번호</th>
            <td><input type="text" id="password"></td>
        </tr>
        <tr>
            <th>고객명</th>
            <td><input type="text" id="customerName" disabled></td>
            <th>이자시작일자</th>
            <td><input type="text" id="startDate" value="20241025" disabled></td>

        </tr>
        <tr>
            <th>초기 예치금(KRW)</th>
            <td><input type="text" id="balance"></td>
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
            <td><input type="text" id="empName" value="유은서" disabled></td>
            <td colspan="2"></td>
        </tr>
        <input type="hidden" id="empId" name="empId" value="1">  <!-- employeeId는 실제 값으로 설정 -->
        <input type="hidden" id="branchName" name="branchName" value="서울지점">  <!-- branchName도 실제 값으로 설정 -->
        <input type="hidden" id="branchId" name="branchId" value="001">  <!-- branchId도 실제 값으로 설정 -->
        <input type="hidden" id="openDate" name="openDate" >  <!-- branchId도 실제 값으로 설정 -->

    </table>

    <div  style="text-align:center;">
        <button class="btn btn-primary" id="accountCreateBtn">계좌 개설</button>

    </div>

    <div id="modalArea">

    </div>

</div>
<script src="/resources/js/footer.js"></script>
</body>
</html>
