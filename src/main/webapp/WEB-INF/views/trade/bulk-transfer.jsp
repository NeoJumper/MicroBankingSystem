<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>계좌관리 >
        계좌이체 >
        대량이체</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/bulk-transfer.css"/>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>계좌관리 > </h5>
        <h5>&nbsp 계좌이체 > </h5>
        <h5>&nbsp&nbsp;대량이체 > </h5>
        <h5>&nbsp&nbsp;등록</h5>
    </div>
    <div>
        <h3>대량이체</h3>
    </div>

    <div class="progress-container">
        <div class="step active">
            <div class="circle active">
                <div class="inner-circle active"></div>
            </div>
            <p>1. 이체정보 입력</p>
        </div>
        <div class="line"></div>
        <div class="step">
            <div class="circle">
                <div class="inner-circle"></div>
            </div>
            <p>2. 이체정보 확인</p>
        </div>
        <div class="line"></div>
        <div class="step">
            <div class="circle">
                <div class="inner-circle"></div>
            </div>
            <p>3. 이체결과 확인</p>
        </div>
    </div>

    <%--  registration-page  --%>
    <container id="registration-page">
        <%--  계좌선택 섹션  --%>
        <section>
            <h4>출금계좌정보</h4>
            <hr>
            <%--계좌선택--%>
            <div id="select-account-form">
                <div class="account-info">
                    <div>
                        <span id="withdrawal-customer-name" class="text-span"></span>
                        <span id="withdrawal-product-name" class="text-span"></span>
                        <br>
                        <span id="withdrawal-account-number">계좌를 선택해주세요.</span>
                    </div>
                    <div>
                        <button id="check-withdrawal-account-btn" class="basic-btn" type="button"
                                data-account-type="withdrawal" data-bs-toggle="modal"
                                data-bs-target="#search-modal-account">
                            계좌조회
                        </button>
                    </div>
                </div>
                <div class="account-balance d-flex align-items-center">
                    <div class="me-2">
                        계좌잔액<span id="account-balance" class="amount-span" style="margin-left: 20px;">0</span> 원
                    </div>
                    <div class="mx-3 transfer-possible-amount">|</div>
                    <div class="ms-2 transfer-possible-amount">
                        이체가능금액<span id="transferable-amount" class="amount-span" style="margin-left: 20px">0</span> 원
                    </div>
                    <div id="select-transfer-limit">
                        <span class="tooltip-link">이체한도조회
                        </span>
                    </div>
                    <div id="select-transfer-limit-tooltip">
                        <div><span
                                style="width: 110px; display: inline-block">1회 이체 한도  </span><span>:&nbsp &nbsp </span><span
                                id="per-trade-limit" class="tooltip-amount-span">0 &nbsp 원</span></div>
                        <div><span
                                style="width: 110px; display: inline-block">1일 이체 한도  </span><span>:&nbsp &nbsp </span><span
                                id="daily-limit" class="tooltip-amount-span">0 &nbsp 원</span></div>
                        <div><span style="width: 110px; display: inline-block">금일 출금액  </span><span>:&nbsp &nbsp </span><span
                                id="transfer-amount-of-today" class="tooltip-amount-span">0 &nbsp 원</span></div>
                        <div><span style="width: 110px; display: inline-block">금일 이체 한도</span><span>:&nbsp &nbsp </span><span
                                id="transferable-amount-limit-of-today" class="tooltip-amount-span">0 &nbsp 원</span></div>
                    </div>
                </div>

            </div>
            <table class="common-table">
                <tr>
                    <th><label>예약 여부</label></th>
                    <td>
                        <div id="reserve-button-group" class="button-group mb-2">
                            <input type="radio" id="immediate-transfer-btn" name="scheduled-status" checked>
                            <label for="immediate-transfer-btn">즉시 이체</label>
                            <input class="ms-3" type="radio" id="scheduled-transfer-btn" name="scheduled-status">
                            <label for="scheduled-transfer-btn">예약 이체</label>
                        </div>
                        <div id="reserve-time-select-div" style="overflow: hidden; transition: height 0.5s ease;">
                            <input type="date" value="1234" style="margin-right: 20px; height: 50px">
                            <div id="time-search-container">
                                <select id="time-search-btn">
                                    <option value="1">9:30 ~ 10:30</option>
                                    <option value="1">10:30 ~ 11:30</option>
                                    <option value="1">11:30 ~ 12:30</option>
                                    <option value="1">12:30 ~ 13:30</option>
                                    <option value="1">13:30 ~ 14:30</option>
                                    <option value="1">14:30 ~ 15:30</option>
                                    <option value="1">15:30 ~ 16:00</option>
                                </select>
                                <img class="search-icon" type="submit" src="/resources/assets/timer.jpg">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="description">비고</label></th>
                    <td>
                        <div>
                            <label><input checked type="radio" name="salaryType" value="directInput"> 직접입력 &nbsp
                                &nbsp</label>
                            <label><input type="radio" name="salaryType" value="월급여"> 월급여 &nbsp &nbsp</label>
                            <label><input type="radio" name="salaryType" value="상여금"> 상여금</label>
                        </div>
                        <input placeholder="10자 이내  입력" type="text" id="description" maxlength="10">
                    </td>
                </tr>
                <tr>
                    <th><label for="user-name-input">담당자</label></th>
                    <td>
                        <input id="user-name-input" type="text" disabled>
                    </td>
                </tr>
                <tr>
                    <th><label for="business-day-date-input">등록일자</label></th>
                    <td>
                        <input id="business-day-date-input" type="text" disabled>
                    </td>
                </tr>

            </table>

            <h4>계좌 비밀번호</h4>

            <table class="common-table">
                <tbody>
                <tr>
                    <th>계좌 비밀번호</th>
                    <td>
                        <input type="password" id="account-pw-input">
                        <input id="input-confirm" type="submit" class="basic-btn" value="확인">
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <%--    입금계좌정보 테이블    --%>
        <section>
            <h4>입금계좌정보</h4>
            <div class="table-top-btns">
                <div class="d-flex" >

                    <button id="uploadEmployeeBtn" type="button" class="file-btn" value="파일등록"
                            style="height: 33px; font-weight: bold;" disabled>
                        파일 등록
                    </button>

                    <button id="uploadIndividualEmployeeBtn" type="button" class="file-btn" value="개별추가"
                            style="height: 33px; font-weight: bold;" disabled>
                        개별 추가
                    </button>

                </div>
                <div>
                    <select id="searchCondition" class="select-option" style="height: 33px;">
                        <option value="" disabled selected>검색조건</option>
                        <option value="targetAccId">입금계좌번호</option>
                        <option value="depositor">등록된 예금주</option>
                    </select>
                    <input type="text" id="searchInput" class="select-option" style="height: 33px;" placeholder="검색어 입력">
                </div>
            </div>
            <div class="tableWrapper">
                <table id="bulk-transfer-info" class="common-table bulk-insert-table">
                    <thead>
                        <tr>
                            <th><label>NO.</label></th>
                            <th><label>입금계좌번호</label></th>
                            <th><label>이체금액(원)</label></th>
                            <th><label>한글금액표시(원)</label></th>
                            <th><label>등록된 예금주</label></th>
                            <th><label>조회된 예금주</label></th>
                            <th><label>받는분 통장표시</label></th>
                            <th><label>상태</label></th>
                        </tr>
                    </thead>
                    <tbody id="employeeTablePreviewBody">
                    <%--  동적으로 직원계좌정보 생성됨 --%>
                    <tr >
                        <td colspan="2">총 <span>0</span>개</td>
                        <td>0</td>
                        <td>영원</td>
                        <td colspan="3"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div id="result-content-div" style="display: none">
                <div class="result-content">
                    <div>
                        총 등록건수(건) <br>
                        <span id="total-registrations">0</span>(건)
                    </div>
                    <div>
                        받는분 정상(건) <br>
                        <span id="valid-recipients">0</span>(건)
                    </div>
                    <div>
                        받는분 불일치(건) <br>
                        <span id="mismatch-recipients">0</span>(건)
                    </div>
                    <div>
                        받는분 오류(건) <br>
                        <span id="error-recipients">0</span>(건)
                    </div>
                </div>
                <ul class="warning-writer">
                    <li>입력하신 받는분 정보가 조회 결과와 일치하는지 꼭 확인하세요.</li>
                    <li>받는분 조회 결과에 ‘오류’ 건이 있는 경우, 잘못 입력한 내용이 없는지 다시 한번 계좌정보를 확인해 주세요.</li>
                </ul>
            </div>

        </section>
        <section class="submit-btns">
            <input class="basic-btn" type="button" value="예금주 확인" disabled>
            <input class="basic-btn" type="button" value="초기화" style="display: none">
            <input class="basic-btn" type="button" value="이체실행" disabled style="display: none">
            <input class="basic-btn" type="button" value="OTP 인증" id="otp-authentication-modal-btn" disabled style="display: none;">
        </section>
    </container>
</div>

<!-- 파일업로드 모달 -->
<div class="modal fade" id="uploadEmployeeModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top:200px;">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">엑셀을 이용해 대량이체 목록을 추가해보세요</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">

                <div class="d-flex mb-3">
                    <div class="d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">
                        <p>
                            대량이체 템플릿을 다운로드한 후,
                            <br>
                            템플릿 형식에 맞춰 이체정보들을 입력한 뒤 업로드해주세요
                        </p>

                    </div>
                </div>
                <div class="d-flex">
                    <div class="d-flex col-12 justify-content-center align-items-center p-4"
                         style="background-color: #E6EFFA">

                        <div>
                            <p class="text-color-gray1" style="font-size: 15px; margin: 0px;">대량이체양식.xlsx</p>
                        </div>
                        <button type="button" class="file-btn" style="height: 33px;">
                            <a class="text-color-gray1" style="font-size: 18px; text-decoration-line: none;"
                               href="/api/employee/bulk-transfer/file-download">다운로드</a><br>
                        </button>
                    </div>
                </div>
                <div class="d-flex mt-4">
                    <div class="d-flex col-12 justify-content-center align-items-center p-4"
                         style="background-color: #E6EFFA">
                            <div>
                                <p id="fileName" style="font-size: 15px;" >선택된 파일이 없음</p>
                            </div>
                            <button type="button" class="file-btn"
                                    style="height: 33px;" onclick="document.getElementById('excelInput').click()">
                                파일 선택
                            </button>
                            <input id="excelInput" type="file" style="display: none">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white"
                        style="border-radius: 2px;"><p style="margin: 0px;">닫기</p></button>
                <button id="uploadEmployeePreviewBtnOfTable" type="button" data-bs-dismiss="modal"
                        class="btn btn-primary">파일등록
                </button>
            </div>
        </div>
    </div>
</div>
<!-- 개별추가 모달 -->
<div class="modal fade" id="uploadIndividualEmployeeModal" tabindex="-1">
    <div class="modal-dialog modal-md" style="margin-top:200px;">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">직접 입력으로 입금 정보를 추가해보세요.</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">

                <div class="modal-body">
                    <h5 style="font-weight: bold">입금 정보</h5>
                    <table class="common-table" style="margin-bottom: 0px">
                        <tr>
                            <th>입금계좌번호</th>
                            <td><input id="targetAccIdModal" maxlength="16" placeholder=" 예) 000-0000000-0000"></td>
                        </tr>
                        <tr>
                            <th>이체금액(원)</th>
                            <td><input id="transferAmountModal" placeholder=" 예) 3,000,000"></td>
                        </tr>
                        <tr>
                            <th>한글금액표시(원)</th>
                            <td><input id="krwModal" placeholder=" 예) 삼백만원" disabled></td>
                        </tr>
                        <tr>
                            <th>받는분</th>
                            <td><input id="depositorModal" placeholder=" 예) 홍길동 "></td>
                        </tr>
                        <tr>
                            <th>받는분 통장표시</th>
                            <td><input id="descriptionModal" placeholder=" 예) 월급"></td>
                        </tr>
                    </table>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white"
                        style="border-radius: 2px;"><p style="margin: 0px;">닫기</p></button>
                <button id="uploadIndivisualEmployeePreviewBtn" type="button" data-bs-dismiss="modal"
                        class="btn btn-primary">추가
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 수정/삭제 모달 -->
<div class="modal fade" id="transfer-info-detail-modal" tabindex="-1">
    <div class="modal-dialog modal-lg" style="margin-top:200px;">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">직접 입력으로 입금 정보를 추가해보세요.</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">

                <div class="modal-body">
                    <table class="common-table" style="margin-bottom: 0px">
                        <tr style="display: none;">
                            <td><input id="update-target-index"></td>
                        </tr>
                        <tr>
                            <th>입금계좌번호</th>
                            <td><input id="update-target-acc-id" maxlength="16" placeholder=" 예) 000-0000000-0000"></td>
                        </tr>
                        <tr>
                            <th>이체금액(원)</th>
                            <td><input id="update-transfer-amount" placeholder=" 예) 3,000,000"></td>
                        </tr>
                        <tr>
                            <th>한글금액표시(원)</th>
                            <td><input id="update-krw" placeholder=" 예) 삼백만원" disabled></td>
                        </tr>
                        <tr>
                            <th>받는분</th>
                            <td><input id="update-depositor" placeholder=" 예) 홍길동 "></td>
                        </tr>
                        <tr>
                            <th>받는분 통장표시</th>
                            <td><input id="update-description" placeholder=" 예) 월급"></td>
                        </tr>
                    </table>
                </div>

            </div>
            <div class="modal-footer">
                <button id="individual-transfer-info-update-btn" type="button" data-bs-dismiss="modal"
                        class="btn btn-primary">수정
                </button>
                <button id="individual-transfer-info-delete-btn" type="button" data-bs-dismiss="modal"
                        class="btn cancel-btn">삭제
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/resources/components/close-overlay.jsp" %>
<%@ include file="/resources/components/modal/account-search-modal.jsp" %>
<%@ include file="/resources/components/modal/otp-input-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/bulk-transfer.js"></script>
</body>

</html>
