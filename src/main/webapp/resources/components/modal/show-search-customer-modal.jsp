<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>

        #searchHeader {
            display: flex;
            align-items: center; /* 세로 가운데 정렬 */
            justify-content: flex-start; /* 요소들이 왼쪽에 정렬되도록 설정 */
            gap: 10px; /* 요소 사이 간격 추가 */
        }

        #searchInfo {
            display: flex;
            align-items: center;
            flex: 0 1 auto; /* 선택 박스는 필요한 크기만 차지 */
        }

        #customerSearch {
            padding: 5px;
        }

        #searchInput {
            flex: 0 1 300px; /* 입력 칸은 고정 너비를 가지게 설정 */
        }

        #searchInput input {
            width: 100%; /* 입력 필드가 부모 요소의 전체 너비 차지 */
            padding: 5px;
        }

        #searchButton {
            flex: 0 1 auto; /* 버튼이 고정된 크기를 가지게 설정 */
        }

        #searchButton button {
            padding: 5px 10px;
            height : 43px;
            width : 50px;
        }

        #tableContainer {
            margin-top: 20px; /* 검색 버튼 아래 공간 추가 */
        }

    </style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="showSearchCustomerModal" tabindex="-1" role="dialog"
     aria-labelledby="showSearchCustomerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel" style="margin-right: 10px">고객 정보 검색 모달</h5>
                <p style="margin-bottom: 0px" id="modalStatus"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="searchHeader">

                    <div id="searchInfo">
                        <label for="customerSearch"></label>
                        <select style="height: 41px;" id="customerSearch" name="customerSearch">
                            <option value="customerNumber">고객번호</option>
                            <option value="customerName">고객이름</option>
                            <option value="customerPhone">전화번호</option>
                        </select>
                    </div>
                    <div id="searchInput">
                        <input  type="text" id="searchQuery" placeholder="입력하시오">
                    </div>
                    <div id="searchButton">
                        <button type="button" class="btn btn-primary" id="searchBtn">검색</button>
                    </div>

                </div>
                <br><br>
                <div id="searchResult">

                    <h5>고객정보</h5>
                    <hr>
                    <table class="commonTable">
                        <thead>
                        <th><label>선택</label></th>
                        <th><label id="modal-customer-id">고객번호</label></th>
                        <th><label id="modal-customer-name">이름</label></th>
                        <th><label id="modal-customer-birth">생년월일</label></th>
                        <th><label id="modal-customer-phone">전화번호</label></th>
                        <th><label id="modal-branch-name">관리지점</label></th>
                        </thead>
                        <tbody id="customer-info">

                        </tbody>
                    </table>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" id="modal-customer-select" class="btn btn-primary" data-bs-dismiss="modal">선택</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>