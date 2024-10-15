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
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="productSearchModalLabel">적금 상품 조회</h1> <!-- Modal Title -->
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>상품정보 검색</h5>
                <hr>

                <div id="search-modal-input-box">
                    <label for="search-modal-select-product"></label>
                    <select style="height: 41px;" id="search-modal-select-product" name="search-modal-select">
                        <option value="period" selected>가입기간 / 약정기간</option>
                        <option value="productName">상품이름</option>
                    </select>

                    <input id="modal-product-search-input" type="text" placeholder="검색어를 입력하시오.">
                </div>
                <hr>
                <div id="search-modal-center-box">
                    <button id="modal-check-product-btn" class="btn basic-btn" type="button">상품 검색</button> <!-- Added "btn" class -->
                    <button id="modal-check-product-reset-btn" class="btn reset-btn" type="button">초기화</button> <!-- Added "btn" class -->
                </div>

                <h5>상품정보</h5>
                <hr>
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
            </div>
        </div>
    </div>
</div>

</body>
</html>
