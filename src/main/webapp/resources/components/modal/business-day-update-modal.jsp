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
<div class="modal fade" id="business-day-update-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header d-flex justify-content-between">
                <h2 class="modal-title">영업일 변경</h2>
                <h1 id="business-day-update-modal-next-business-day" class="modal-title fs-5"></h1>
            </div>
            <div class="modal-body">
                <div id="inputBox" class="d-flex justify-content-end">
                    <div class="d-flex justify-content-end align-items-center"><label class="me-3 mb-0">전일자 현금 총액</label></div>
                    <input id="business-day-modal-branch-balance" style="width:70%; direction: rtl;" type="text" disabled>
                </div>


                <h3 class="mt-5">근무 인원 지정</h3>
                <hr>
                <table class="common-table">
                    <thead>
                    <th><i class="bi bi-square"></i></th>
                    <th style="width: 20%"><label id="business-day-modal-emp-id">사원번호</label></th>
                    <th style="width: 20%"><label id="business-day-modal-emp-name">사원명</label></th>
                    <th style="width: 40%"><label id="business-day-modal-emp-roles">전일자 현금 잔액</label></th>


                    </thead>
                </table>

                <div id="business-day-modal-emp-add-list" style="overflow-y: auto; height: 300px;">
                    <table class="table table-hover">
                        <tbody id = "business-day-modal-emp-list">

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
         $('#business-day-modal-emp-add-list').on('click', '.business-day-element', function() {
             console.log("click");
             alert("test");
             // 클릭한 행 내의 i 태그만 변경
             $(this).find('i').removeClass('bi-square').addClass('bi-check-square');
         });
   });
</script>

<script src="/resources/js/modal/business-day-update-modal.js"></script>

</body>
</html>