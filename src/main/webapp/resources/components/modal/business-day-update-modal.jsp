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
    <div class="modal-dialog modal-xl modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">영업일 변경</h2>
            </div>
            <div class="modal-body">
                <h4>지점 정보</h4>
                <table class="common-table">
                    <tbody>
                    <tr>
                        <th>
                            <label>전일자 현금 총액</label>
                        </th>
                        <td>
                            <input id="business-day-modal-branch-balance" type="text" disabled>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <h4>매니저 정보</h4>
                <hr>
                <table class="common-table">
                    <thead>
                    <tr>
                        <th style="width: 8%">사원번호</th>
                        <th>사원명</th>
                        <th>권한</th>
                        <th>전일자 현금 총액</th>
                        <th>
                            시재금
                            <i class="bi bi-question-circle"
                               data-bs-toggle="tooltip"
                               data-bs-placement="top"
                               title="매니저의 시재금은 지점 보유 현금에서 행원의 시재금을 제한 금액으로 자동 책정됩니다."></i>
                        </th>

                    </tr>
                    </thead>
                    <tbody id="business-day-manager-list">
                        <!-- 매니저 정보 여기에 동적으로 삽입 -->
                    </tbody>
                </table>

                <h4 class="mt-5">근무 인원 지정</h4>
                <table class="common-table no-margin">
                    <thead>
                    <tr id="business-day-modal-all-checkbox">
                        <th style="width: 6%">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="selectAllSwitch">
                            </div>
                        </th>
                        <th style="width: 8%"><label id="business-day-modal-emp-id">사원번호</label></th>
                        <th style="width: 8%;"><label id="business-day-modal-emp-name">사원명</label></th>
                        <th style="width: 8%;"><label id="business-day-modal-emp-role">권한</label></th>
                        <th style="width: 35%"><label id="business-day-modal-emp-vault-cash">전일자 마감 금액</label></th>
                        <th><label id="business-day-modal-emp-prev-cash-balance">시재금</label></th>
                    </tr>
                    </thead>
                </table>
                <div id="scrollable-tbody-wrapper">
                    <table class="common-table">
                        <tbody id="business-day-modal-emp-list">

                        </tbody>
                    </table>
                </div>
            </div>


            <div class="modal-footer">
                <button id="business-day-update-modal-update-btn" class="basic-btn">영업 시작</button>
                <button type="button" id="search-modal-close-btn" class="closed-btn" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>


<script src="/resources/js/modal/business-day-update-modal.js"></script>

</body>
</html>
