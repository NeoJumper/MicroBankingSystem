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
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">영업일 변경</h2>
            </div>
            <div class="modal-body">
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


                <h3 class="mt-5">근무 인원 지정</h3>
                <table class="common-table no-margin">
                    <thead>
                    <tr id="business-day-modal-all-checkbox">
                        <th style="width: 5%"><i class="bi bi-square"></i></th>
                        <th style="width: 25%"><label id="business-day-modal-emp-id">사원번호</label></th>
                        <th style="width: 30%"><label id="business-day-modal-emp-name">사원명</label></th>
                        <th style="width: 40%"><label id="business-day-modal-emp-roles">전일자 현금 잔액</label></th>
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
