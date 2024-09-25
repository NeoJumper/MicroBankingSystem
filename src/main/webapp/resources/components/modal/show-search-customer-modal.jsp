<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>

        #modal-search-header {
            display: flex;
            align-items: center;
            justify-content: flex-start;
            gap: 10px;
        }

        #modal-search-info {
            display: flex;
            align-items: center;
            flex: 0 1 auto;
        }

        #modal-search-customerSearch {
            padding: 5px;
        }

        #modal-search-input {
            flex: 0 1 300px;
        }

        #modal-search-input input {
            width: 100%;
            padding: 5px;
        }

        #modal-search-button {
            flex: 0 1 auto;
        }

        #modal-search-button button {
            padding: 5px 10px;
            height : 43px;
            width : 50px;
        }

        #modal-search-tableContainer {
            margin-top: 20px;
        }

    </style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="showSearchCustomerModal" tabindex="-1" role="dialog"
     aria-labelledby="modal-search-showSearchCustomerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-search-label" style="margin-right: 10px">고객 정보 검색 모달</h5>
                <p style="margin-bottom: 0px" id="modal-search-status"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="modal-search-header">

                    <div id="modal-search-info">
                        <label for="modal-search-customerSearch"></label>
                        <select style="height: 41px;" id="modal-search-customerSearch" name="customerSearch">
                            <option value="customerNumber">고객번호</option>
                            <option value="customerName">고객이름</option>
                            <option value="customerPhone">전화번호</option>
                        </select>
                    </div>
                    <div id="modal-search-input">
                        <input type="text" id="modal-search-query" placeholder="입력하시오">
                    </div>
                    <div id="modal-search-button">
                        <button type="button" class="btn btn-primary" id="modal-search-btn">검색</button>
                    </div>

                </div>
                <br><br>
                <div id="modal-search-result">

                    <h5>고객정보</h5>
                    <hr>
                    <table class="commonTable">
                        <thead>
                        <th><label>선택</label></th>
                        <th><label id="modal-search-customer-id">고객번호</label></th>
                        <th><label id="modal-search-customer-name">이름</label></th>
                        <th><label id="modal-search-customer-birth">생년월일</label></th>
                        <th><label id="modal-search-customer-phone">전화번호</label></th>
                        <th><label id="modal-search-branch-name">관리지점</label></th>
                        </thead>
                        <tbody id="modal-search-customer-info">

                        </tbody>
                    </table>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" id="modal-search-customer-select" class="btn btn-primary" data-bs-dismiss="modal">선택</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
