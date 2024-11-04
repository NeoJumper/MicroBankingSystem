<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품조회 모달</title>

    <link rel="stylesheet" href="/resources/css/modal/product-search-modal.css">

    <script src="/resources/js/modal/product-search-modal.js"></script>

</head>
<body>
<!-- Modal -->
<div class="modal fade" id="search-modal-product" tabindex="-1" aria-labelledby="productSearchModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="productSearchModalLabel">적금 상품 조회</h1> <!-- Modal Title -->
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="common-table">
                    <tbody>
                    <tr>
                        <h5>상품정보 검색</h5>
                        <th style="width: 30%">
                            <select style="width: 170px" id="search-modal-select-product" name="search-modal-select">
                                <option value="period" selected>가입기간 / 약정기간</option>
                                <option value="productName">상품이름</option>
                            </select>
                        </th>
                        <td>
                            <input id="modal-product-search-input" type="text" placeholder="검색어를 입력하시오.">
                        </td>
                    </tr>

                    </tbody>
                </table>



                <div id="search-modal-center-box">
                    <button id="modal-check-product-btn" class="btn basic-btn" type="button">상품 검색</button> <!-- Added "btn" class -->
                    <button id="modal-check-product-reset-btn" class="btn reset-btn" type="button">초기화</button> <!-- Added "btn" class -->
                </div>

                <div id="search-modal-search-result">
                    <h5>상품정보</h5>
                    <div id="scrollable-table-container">
                        <table id="search-product-modal-common-table" class="common-table">
                            <thead>
                            <th><label>선택</label></th>
                            <th><label id="search-modal-product-id">상품번호</label></th>
                            <th><label id="search-modal-product-name">상품이름</label></th>
                            <th><label id="search-modal-product-period">상품기간</label></th>
                            <th><label id="search-modal-product-interest-rate">상품이율</label></th>
                            <th><label id="search-modal-product-tax-rate">상품세율</label></th>
                            <th><label id="search-modal-branch-name">지점이름</label></th>
                            </thead>
                        </table>

                        <div id="scrollable-tbody-wrapper">
                            <table class="common-table">
                                <tbody id="search-product-modal-body">

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <hr>
                    <div id="search-modal-product-select-button" class="mt-2 d-flex justify-content-end">
                        <button id="search-modal-product-select-btn" class="btn basic-btn">상품 선택</button>
                    </div>
                </div>

                <%--
                <h5>상품정보</h5>
                <table id="search-product-modal-common-table" class="common-table">
                    <thead>
                    <th><label>선택</label></th>
                    <th><label id="search-modal-product-id">상품번호</label></th>
                    <th><label id="search-modal-product-name">상품이름</label></th>
                    <th><label id="search-modal-product-period">상품기간</label></th>
                    <th><label id="search-modal-product-interest-rate">상품이율</label></th>
                    <th><label id="search-modal-product-tax-rate">상품세율</label></th>
                    <th><label id="search-modal-branch-name">지점이름</label></th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <div id="search-modal-product-select-button">
                    <button id="search-modal-product-select-btn" class="btn basic-btn">상품 선택</button>
                </div>

                --%>
            </div>
        </div>
    </div>
</div>

</body>
</html>
