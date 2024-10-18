<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />

</head>

<body>

<div class="modal fade" id="business-day-reset-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="search-modal-title">영업일 되돌리기</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mt-4 mb-4 d-flex flex-column justify-content-center align-items-center">
                    <h3>영업일을 되돌리시겠습까?</h3>
                    <h5 style="color: #cc0000">* 현재의 데이터는 유지되지않습니다. </h5>
                    <h5 style="color: #6C757D"> 이전 영업일 - 2024-08-02 </h5>
                </div>
                <hr>

                <div class="d-flex justify-content-center mt-3">
                    <div>
                        <button id="business-day-reset-modal-reset-btn" class="basic-btn">
                            영업일 되돌리기
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
