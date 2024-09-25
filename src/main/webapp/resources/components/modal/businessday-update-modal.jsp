<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/resources/css/modal/businessday-update-modal.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="businessdayUpdateModal" tabindex="-1" aria-labelledby="businessdayUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header d-flex justify-content-between">
                <h2 class="modal-title" id="businessdayUpdateModalLabel">영업일 변경</h2>
                <h1 class="modal-title fs-5" id="businessdayUpdateModalLabel">다음 영업일 - 2024/09/24</h1>
            </div>
            <div class="modal-body">
                <div id="inputBox" class=" d-flex justify-content-end">
                    <label class="me-3 mb-0">전일자 현금 잔액</label>
                    <input type="text" disabled>
                    <button class="reset-btn" type="button">균등 분배</button>
                </div>


                <h3 class="mt-3">근무 인원 지정</h5>
                <hr>
                <table class="commonTable">
                    <thead>
                    <th><i class="bi bi-square"></i></th>
                    <th style="width: 20%"><label id="modal-businessday-id">사원번호</label></th>
                    <th style="width: 20%"><label id="modal-open-date">사원명</label></th>
                    <th style="width: 20%"><label id="modal-total-amount">직급</label></th>
                    <th style="width: 20%"><label id="modal-customer-name">지점명</label></th>


                    </thead>
                </table>

                <div id="businessday-add-list" style="overflow-y: auto; height: 300px;">
                    <table class="table table-hover">
                        <tbody>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <tr id="modal-select-row" class="businessday-element">
                            <td><i class="bi bi-square"></i></td>
                            <td style="width: 20%;">1001</td>
                            <td style="width: 20%;">지승용</td>
                            <td style="width: 20%;">행원</td>
                            <td style="width: 20%;">은평 1지점</td>
                        </tr>
                        <!-- 추가 행들 -->
                        </tbody>
                    </table>
                </div>
                <div class="d-flex justify-content-center mt-3">
                    <div >
                        <button class ="update-btn">
                            수정사항 저장
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
   $(document).ready(function() {
         // businessday-element 클래스가 적용된 모든 행을 대상으로 클릭 이벤트
         $('#businessday-add-list').on('click', '.businessday-element', function() {
             console.log("click");
             alert("test");
             // 클릭한 행 내의 i 태그만 변경
             $(this).find('i').removeClass('bi-square').addClass('bi-check-square');
         });
   });
</script>

</body>
</html>
