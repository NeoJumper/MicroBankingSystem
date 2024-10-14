<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>직원관리</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/employee-management.css" />
    <link href="https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>
<div id="mainArea">
    <div class="d-flex justify-content-between m-5">
        <div class="col-3">
            <h3 class="fw-bold">직원관리</h3>
        </div>
        <div id="organization-btn-group" class="col-9 d-flex justify-content-end">
            <div class="mx-2">
                <button id="uploadEmployeeBtn" type="button" class="btn border border-black btn-black bg-white"><p>업로드</p></button>
            </div>
            <div class="mx-2">
                <button id="addEmployeeBtn" type="button" class="btn btn-primary"><p>+ 직원 추가하기</p></button>
            </div>

        </div>

    </div>
    <div style="margin: 0 70px; position:relative;">
        <div class="my-4 d-flex justify-content-between align-items-center">
            <div class="col-6 d-flex">
                <h4 id="totalNumber" style="margin: 0;">총 직원 수 1</h4>
                <div class="mx-2">
                    <button id="sendJoinCodeBtn" type="button" class="btn border border-black btn-black bg-white"><p id="clickedNumber1">합류코드 전송(0)</p></button>
                </div>
                <div id="empUpdateBtn" class="mx-2" style="display: none">
                    <button  type="button" class="btn border border-black btn-black bg-white"><p>정보 업데이트</p></button>
                </div>
            </div>

        </div>

        <div class="s-table-container">
            <table class="table table-hover">
                <thead>
                <tr id="employee-element-header" style="border-bottom: 2px solid rgb(160, 160, 160);">
                    <th colspan="col">
                        <div class ="checkbox-img">
                            <img src="/resources/assets/checkbox-unactive.png"/>
                        </div>
                    </th>
                    <th scope="col">입금계좌번호</th>
                    <th scope="col">이체 금액(원)</th>
                    <th scope="col">한글금액표시(원)</th>
                    <th scope="col">받는분</th>
                    <th scope="col">받는분 통장 표시</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="employee" items="${employees}">
                    <tr class = "employee-element" data-emp-id="${employee.id}">
                        <td colspan="col">
                            <div class ="checkbox-img">
                                <img src="/resources/assets/checkbox-unactive.png"/>
                            </div>
                        </td>
                        <td >
                            ${employee.id}
                        </td>
                        <td >
                                ${employee.name}
                        </td>
                        <td>
                                ${employee.authority}
                        </td>
                        <td>
                                ${employee.deptName}
                        </td>
                        <td>
                                ${employee.position}
                        </td>
                        <td>
                            <fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                            ${formattedDate}
                        </td>
                        <td>
                                ${employee.email}
                        </td>
                        <td class = "joinState text-center">
                                ${employee.joinState}
                        </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>

</div>

<!-- 직원추가 모달 -->
<div class="modal fade" id="addEmployeeModal" tabindex="-1">
    <div class="modal-dialog  modal-lg" style="margin-left: 34%">
        <div class="modal-content" style="width: 70%">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold" id="exampleModalLabel">직원 정보 추가</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">
                <div class="d-flex  mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">조직</p></div>
                    <div class="col-9">
                        <select class="form-select dropdown" id="addDepartmentSelect" >
                        </select>
                    </div>
                </div>
                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">이름</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="addEmployeeNameInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>
                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">이메일</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="addEmployeeEmailInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>

                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">직책</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="addEmployeePositionInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>
                <div class="d-flex mb-3 ">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">권한</p></div>
                    <div class="col-9">
                        <select class="form-select" id="addEmployeeIdAuthoritySelect">
                            <option selected>최고 관리자</option>
                            <option value="1">1차 승인권자</option>
                            <option value="2">2차 승인권자</option>
                            <option value="3">권한 없음</option>
                        </select>
                    </div>
                </div>
                <div class="d-flex mb-3 ">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px; margin-bottom: 16px;">입사일</p></div>
                    <div class="col-9">
                        <input style="margin-bottom: 0;" id="addEmployeeDatepicker"/>
                    </div>
                </div>
                <div class="my-3 d-flex justify-content-start">
                    <p class ="text-color-gray2" style="font-size: 12px;">
                        * 직원을 추가하고 초대메일을 보내 조직을 구성하세요.

                </div>



            </div>
            <div class="modal-footer">
                <button id="modalCloseBtn" type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white" style="border-radius: 2px;"><p>닫기</p></button>
                <button id="addEmployeeBtnOfModal" type="button" data-bs-dismiss="modal"  class="btn btn-primary">추가하기</button>
            </div>
        </div>
    </div>
</div>

<!-- 직원수정 모달 -->
<div class="modal fade" id="updateEmployeeModal" tabindex="-1">
    <div class="modal-dialog  modal-lg" style="margin-left: 34%">
        <div class="modal-content" style="width: 70%">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">직원 정보 수정</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">
                <div class="d-flex  mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">조직</p></div>
                    <div class="col-9">
                        <select class="form-select dropdown" id="updateDepartmentSelect" >
                        </select>
                    </div>
                </div>
                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">이름</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="updateEmployeeNameInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>
                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">이메일</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="updateEmployeeEmailInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>

                <div class="d-flex mb-3">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">직책</p></div>
                    <div class="col-9 d-flex align-items-center" style="position: relative">
                        <input id="updateEmployeePositionInput" type="text" class="form-control" style="border-radius: 2px;" >
                    </div>
                </div>
                <div class="d-flex mb-3 ">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">권한</p></div>
                    <div class="col-9">
                        <select class="form-select" id="updateEmployeeIdAuthoritySelect">
                            <option selected>최고관리자</option>
                            <option value="1">1차 승인권자</option>
                            <option value="2">2차 승인권자</option>
                            <option value="3">권한 없음</option>
                        </select>
                    </div>
                </div>
                <div class="d-flex mb-3 ">
                    <div class="col-3 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px; margin-bottom: 16px;">입사일</p></div>
                    <div class="col-9">
                        <input style="margin-bottom: 0;" id="updateEmployeeDatepicker"/>
                    </div>
                </div>


            </div>
            <div class="modal-footer d-flex justify-content-between">
                <div>
                    <button id="deleteEmployeeBtnOfModal" type="button" class="btn btn-primary">삭제</button>
                </div>
                <div>
                    <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white" style="border-radius: 2px;"><p>닫기</p></button>
                    <button id="updateEmployeeBtnOfModal" type="button" data-bs-dismiss="modal"  class="btn btn-primary">정보수정</button>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 합류 코드 전송 모달-->
<div class="modal fade" id="sendJoinCodeModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top:200px;">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">합류 코드 전송</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">

                <div class="d-flex mb-3">
                    <div class="d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">
                        <p id="unJoinedNumber">미합류 직원 : 0명</p>
                    </div>
                </div>
                <div class="d-flex">
                    <div class="d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">
                        <p id="clickedNumber2" style="color: #3391FF">합류코드가 전송될 직원들 : 0명</p>
                    </div>
                </div>
                <div class="d-flex">
                    <label class="form-check py-4 d-flex align-items-center" for="joinCodeRadio1">
                        <input class="form-check-input" name="joinCodeRadioForm" type="radio" id="joinCodeRadio1">
                        <span class="ms-2">즉시전송</span>
                    </label>
<%--                    <label class="ms-4 form-check py-4 d-flex align-items-center" for="joinCodeRadio2">
                        <input class="form-check-input" name="joinCodeRadioForm" type="radio" id="joinCodeRadio2">
                        <span class="ms-2">예약전송</span>
                    </label>--%>
                </div>
<%--

                <div class="d-flex">
                    <div class="col-6">
                        <input id="joinCodeDatepicker" width="200" />
                    </div>
                    <div class="col-6">
                        <input id="joinCodeTimePicker" type="time" class="form-control" value="10:05 AM" />
                    </div>
                </div>

--%>


                <div class="mb-3 d-flex justify-content-start">
                    <p class ="text-color-gray2" style="font-size: 12px;">
                        * 참고사항<br>
                        합류코드는 각 직원에 등록된 이메일로 전송됩니다.

                </div>



            </div>
            <div class="modal-footer">
                <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white" style="border-radius: 2px;"><p>닫기</p></button>
                <button id="sendJoinCodeBtnOfModal" type="button"  class="btn btn-primary">전송하기</button>
            </div>
        </div>
    </div>
</div>

<!-- 직원업로드 모달 -->
<div class="modal fade" id="uploadEmployeeModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top:200px;">
        <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title fs-5 fw-bold">엑셀을 이용해 직원을 추가해보세요</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex flex-column">

                    <div class="d-flex mb-3">
                        <div class="d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">
                            <p>
                                직원 일괄추가 템플릿을 다운로드한 후,
                                <br>
                                템플릿 형식에 맞춰 직원정보를 입력한 뒤 업로드해주세요
                            </p>

                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="d-flex col-12 justify-content-center align-items-center p-4" style="background-color: #E6EFFA">

                            <div>
                                <p class ="text-color-gray1" style="font-size: 15px; margin: 0px;">employees-upload.xlsx</p>
                            </div>
                            <button type="button" class="d-flex ms-2 btn btn-light border border-2 align-items-center" style="height: 27px;">
                                <a class ="text-color-gray1" style="font-size: 15px;" href="/api/employee/bulk-transfer/file-download">다운로드</a><br>
                            </button>
                        </div>
                    </div>
                    <div class="d-flex mt-4">
                        <div class="d-flex col-12 justify-content-center align-items-center p-4" style="background-color: #E6EFFA">

                            <div>
                                <input id="excelInput" type="file" style="width: 250px;" >
                            </div>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white" style="border-radius: 2px;"><p>닫기</p></button>
                    <button id="uploadEmployeePreviewBtnOfModal" type="button" data-bs-dismiss="modal"  class="btn btn-primary">업로드 미리보기</button>
                </div>
        </div>
    </div>
</div>

<!-- 직원업로드 미리보기 모달 -->
<div class="modal fade" id="uploadEmployeePreviewModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top:100px; margin-left: 20vw; margin-right: 20vw;">
        <div class="modal-content" style="width: 60vw">
            <div class="modal-header">
                <h2 class="modal-title fs-5 fw-bold">업로드 미리보기</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column">

                <div class="d-flex mb-3">
                    <div class="d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">
                        <p>
                            * 올바른 포맷으로 입력되지 않은 경우 붉은색으로 표시됩니다.
                        </p>
                    </div>
                </div>

                <div class="s-excel-table-container">
                    <table class="table table-hover">
                        <thead>
                        <tr style="border-bottom: 2px solid rgb(160, 160, 160);">
                            <th scope="col">입금계좌번호</th>
                            <th scope="col">이체 금액(원)</th>
                            <th scope="col">한글금액표시(원)</th>
                            <th scope="col">받는분</th>
                            <th scope="col">받는분 통장 표시</th>
                        </tr>
                        </thead>
                        <tbody id="employeeTablePreviewBody">

                        </tbody>
                    </table>

                </div>


            </div>
            <div class="modal-footer">
                <button type="button" data-bs-dismiss="modal" class="btn border border-black btn-black bg-white" style="border-radius: 2px;"><p>닫기</p></button>
                <button id="uploadEmployeeCompleteBtnOfModal" type="button" data-bs-dismiss="modal"  class="btn btn-primary">업로드</button>
            </div>
        </div>
    </div>
</div>


<!-- 삭제 확인 모달 -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="margin-top:200px;">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmModalLabel">삭제 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                직원 정보를 정말 삭제하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="confirmCancelBtn" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-danger" id="confirmDeleteBtn">삭제</button>
            </div>
        </div>
    </div>
</div>

<script>
    const urlParams = new URL(location.href).searchParams;
    const addedEmployeeId = urlParams.get('addedEmployeeId');
    const resend = urlParams.get('resend');
</script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<script src="/resources/js/employee-management.js"></script>


<script>
    $('#addEmployeeDatepicker').datepicker({
        uiLibrary: 'bootstrap5'
    });
</script>
<script>
    $('#updateEmployeeDatepicker').datepicker({
        uiLibrary: 'bootstrap5'
    });
</script>

<script>
    $('#joinCodeDatepicker').datepicker({
        uiLibrary: 'bootstrap5'
    });
</script>

</body>
</html>
