<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" href="/resources/css/modal/employee-search-modal.css">


</head>
<body>

<!-- Modal -->
<div class="modal fade" id="employee-search-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                    <h5 class="modal-title" id="employee-search-modal-title" style="margin-right: 10px">행원 정보 검색 모달</h5>
                    <p style="margin-bottom: 0px" id="modal-search-status"></p>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                    <table class="common-table">
                        <tbody>
                            <tr>
                                <th>
                                    <label for="search-modal-select"></label>
                                    <select style="height: 41px;" id="search-modal-select" name="search-modal-select">
                                        <option value="id">사원번호</option>
                                        <option value="name">이름</option>
                                        <option value="phone_number">전화번호</option>
                                    </select>
                                </th>
                                <td>
                                    <input type="text" id="search-modal-input" placeholder="사원 번호를 입력하세요">
                                </td>
                            </tr>
                        </tbody>
                    </table>

                <div id="search-modal-center-box">
                    <button type="button" class="basic-btn" id="search-modal-search-btn">검색</button>
                    <button id="search-modal-reset-btn" class="reset-btn" type="button">초기화</button>
                </div>
                <div id="search-modal-search-result">
                    <h5>고객정보</h5>
                    <table class="common-table" style="margin-bottom: 0px">
                        <thead>
                            <th style="width: 6%;"><label>선택</label></th>
                            <th style="width: 10%;"><label id="search-modal-employee-id">사원번호</label></th>
                            <th style="width: 10%;"><label id="search-modal-employee-name">이름</label></th>
                            <th style="width: 20%;"><label id="search-modal-employee-birth">생년월일</label></th>
                            <th style="width: 20%;"><label id="search-modal-employee-phone">전화번호</label></th>
                            <th style="width: 20%;"><label id="search-modal-employee-email">이메일</label></th>
                            <th style="width: 10%;"><label id="search-modal-branch-roles">직책</label></th>
                        </thead>


                    </table>

                    <div id="employee-add-list" style="overflow-y: auto; height: 230px;">
                        <table class="common-table">
                            <tbody id="search-modal-employee-information">

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" id="search-modal-select-btn" class="basic-btn" data-bs-dismiss="modal">선택</button>
                <button type="button" id="search-modal-close-btn" class="closed-btn" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/modal/employee-search-modal.js"></script>

</body>
</html>
