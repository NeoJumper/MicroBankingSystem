<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/modal/search-modal-deposit-product.css"/>

</head>
<body>

<!-- Modal -->
<div class="modal fade" id="search-deposit-product-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="search-modal-title" style="margin-right: 10px">예금 상품 검색</h5>
                <p style="margin-bottom: 0px" id="modal-search-status"></p>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="common-table">
                    <tbody>
                    <tr>
                        <th>
                            <select id="deposit-product-search-modal-select" name="search-modal-select" style="width: 150px">
                                <option value="id">상품번호</option>
                                <option value="name">상품명</option>
                            </select>
                        </th>
                        <td>
                            <input type="text" id="deposit-product-search-modal-input" placeholder="검색어를 입력하시오" style="width: 400px">
                        </td>
                    </tr>

                    </tbody>
                </table>

                <div id="deposit-product-search-modal-center-box">
                    <button id="deposit-product-search-modal-search-btn" type="button" class="basic-btn">검색</button>
                    <button id="deposit-product-search-modal-reset-btn" class="reset-btn" type="button">초기화</button>
                </div>

                <div id="deposit-product-search-modal-search-result">
                    <h5>상품 정보</h5>
                    <div id="scrollable-table-container">
                        <table class="common-table no-margin" id="deposit-product-search-modal-table">
                            <thead>
                            <tr>
                                <th style="width: 10%"><label>선택</label></th>
                                <th style="width: 20%"><label id="deposit-product-search-modal-product-id">상품번호</label></th>
                                <th style="width: 25%"><label id="deposit-product-search-modal-product-name">상품명</label></th>
                                <th style="width: 20%"><label id="deposit-product-search-modal-product-interest-rate">상품이율</label></th>
                                <th style="width: 20%"><label id="deposit-product-search-modal-product-tax-rate">상품세율</label></th>
                            </tr>
                            </thead>
                        </table>

                        <div id="scrollable-tbody-wrapper">
                        <table class="common-table">
                            <tbody id="deposit-product-search-modal-product-information" class="text-center">

                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" id="deposit-product-search-modal-close-btn" class="closed-btn" data-bs-dismiss="modal">닫기</button>
                <button type="button" id="deposit-product-search-modal-select-btn" class="basic-btn">선택</button>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/modal/deposit-product-search-modal.js"></script>

</body>
</html>
