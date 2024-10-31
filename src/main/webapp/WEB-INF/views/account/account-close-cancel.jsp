<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌 해지 취소</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/account-close.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>예금 관리 >&nbsp</h5>
        <h5>계좌 해지 취소 완료</h5>
    </div>
    <div>

        <div>
            <h3>계좌 해지 취소 신청</h3>

            <table id="search-content" class="common-table">
                <tbody>
                <tr>
                    <th>계좌번호 조회</th>
                    <td>
                        <input placeholder="계좌번호 조회" id="search-input" type="text" disabled>
                        <button id="check-withdrawal-account-btn" class="basic-btn" type="button"
                                data-account-type="withdrawal" data-bs-toggle="modal"
                                data-bs-target="#search-modal-account">
                            계좌조회
                        </button>
                    </td>
                </tbody>

                </tr>
            </table>
            <h4>계좌 정보</h4>


            <table class="common-table">
                <tbody>
                <tr>
                    <th>
                        고객 이름
                    </th>
                    <td>
                        <input id="customer-name" type="text" disabled="">
                    </td>
                </tr>
                <tr>
                    <th>계좌번호</th>
                    <td> <input id="account-number" type="text" disabled></td>
                </tr>
                <tr>
                    <th>상품 이름</th>
                    <td><input id="product-name" type="text" disabled></td>
                </tr>
                </tbody>
            </table>

            <h4>계좌 비밀번호</h4>

            <table class="common-table">
                <tbody>
                    <tr>
                        <th>계좌 비밀번호</th>
                        <td>
                            <input type="password" id="account-pw-input">
                            <input id="input-confirm" class="basic-btn" type="submit" value="확인">
                        </td>
                    </tr>
                </tbody>
            </table>

            <div id="submit" class="row justify-content-center mb-5">
                <input id="cancel-submit-btn" class="basic-btn col-1" type="submit" value="취소 신청" disabled>
            </div>
        </div>
    </div>
</div>

<%@ include file="/resources/components/close-overlay.jsp" %>
<%@ include file="/resources/components/modal/account-close-cancel-result-modal.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<script src="/resources/js/page/account-close-cancel.js"></script>
<script src="/resources/js/footer.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>
