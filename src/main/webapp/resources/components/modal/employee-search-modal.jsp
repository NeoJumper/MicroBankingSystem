<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/resources/css/modal/employee-search-modal.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="employeeSearchModal" tabindex="-1" aria-labelledby="employeeSearchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="employeeSearchModalLabel">사원 검색</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>사원정보 입력</h5>
                <hr>
                <div id="inputBox">
                    <label>사원번호</label>
                    <input type="text">
                    <button class="search-btn" type="button">사원 검색</button>
                    <button class="reset-btn" type="button">초기화</button>
                </div>
                <hr>


                <h5>사원정보</h5>
                <hr>
                <table class="commonTable">
                    <thead>
                    <th style="width: 25%"><label id="modal-employee-id">사원번호</label></th>
                    <th style="width: 25%"><label id="modal-open-date">사원명</label></th>
                    <th style="width: 25%"><label id="modal-total-amount">직급</label></th>
                    <th style="width: 25%"><label id="modal-customer-name">지점명</label></th>

                    </thead>
                </table>

                <div id="employee-add-list" style="overflow-y: auto; height: 250px;">
                    <table class="table table-hover">
                        <tbody>
                        <tr class="employee-element">
                            <td style="width: 25%;">1001</td>
                            <td style="width: 25%;">지승용</td>
                            <td style="width: 25%;">행원</td>
                            <td style="width: 25%;">은평 1지점</td>
                        </tr>
                        <!-- 추가 행들 -->
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
