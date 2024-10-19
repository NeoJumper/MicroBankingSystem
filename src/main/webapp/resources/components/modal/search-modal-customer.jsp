<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <script src="/resources/js/modal/search-customer-modal.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/modal/search-modal-customer.css"/>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="search-customer-modal" tabindex="-1" role="dialog"
     aria-labelledby="search-customer-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="search-modal-title" style="margin-right: 10px">고객 정보 검색 모달</h5>
                <p style="margin-bottom: 0px" id="modal-search-status"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="common-table">
                    <tbody>
                    <tr>
                        <th>
                            <select id="search-modal-select" name="search-modal-select" style="width: 150px">
                                <option value="customer-id">고객번호</option>
                                <option value="customer-name">고객이름</option>
                                <option value="customer-phone">전화번호</option>
                            </select>
                        </th>
                        <td>
                            <input type="text" id="search-modal-input" placeholder="검색어를 입력하시오" style="width: 400px">
                        </td>
                    </tr>

                    </tbody>
                </table>

                <div id="search-modal-center-box">
                    <button id="search-modal-search-btn" type="button" class="basic-btn">검색</button>
                    <button id="search-modal-reset-btn" class="reset-btn" type="button">초기화</button>
                </div>

                <div id="search-modal-search-result">
                    <h5>고객정보</h5>
                    <div id="scrollable-table-container">
                        <table class="common-table no-margin" id="search-modal-table">
                            <thead>
                            <tr>
                                <th style="width: 10%"><label>선택</label></th>
                                <th style="width: 10%"><label id="search-modal-customer-id">고객번호</label></th>
                                <th style="width: 10%"><label id="search-modal-customer-name">이름</label></th>
                                <th style="width: 20%"><label id="search-modal-customer-birth">생년월일</label></th>
                                <th style="width: 20%"><label id="search-modal-customer-phone">전화번호</label></th>
                                <th style="width: 15%"><label id="search-modal-customer-security-level">보안등급</label></th>
                                <th style="width: 15%"><label id="search-modal-branch-name">관리지점</label></th>
                            </tr>
                            </thead>
                        </table>

                        <div id="scrollable-tbody-wrapper">
                        <table class="common-table">
                            <tbody id="search-modal-customer-information" class="text-center">

                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" id="search-modal-close-btn" class="closed-btn" data-bs-dismiss="modal">닫기</button>
                <button type="button" id="search-modal-select-btn" class="basic-btn">선택</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
