<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/page/customer-save.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="main-area">
    <div id="title">
        <h5>고객 관리 > </h5>
        <h5>&nbsp 고객 등록 </h5>
    </div>
    <div>
        <h3>고객 추가</h3>
    </div>
    <table class="common-table">
        <tr>
            <th>이름</th>
            <td><input type="text" id="customer-name"></td>
            <th>생년월일</th>
            <td><input type="date" id="customer-birth-date"></td>
        </tr>
        <tr>
            <th>주민번호</th>
            <td style="position: relative;">
                <input type="text" id="customer-resident-number" placeholder="000000-0000000" maxlength="14">
                <div class="toggle-visibility"><i class="bi bi-eye"></i></div>
            </td>
            <th>주소</th>
            <td>
                <div class="d-flex my-2">
                    <input  type="text" id="customer-address" placeholder="주소"><br>
                    <input class="ms-2" style="height: 42px;" type="button" onclick="sample6_execDaumPostcode()" value="주소 검색"><br>
                </div>
                <input type="text" id="customer-detail-address" placeholder="상세주소">

            </td>

        </tr>
        <tr>
            <th>이메일</th>
            <td><input type="text" id="customer-email"></td>
            <th>전화번호</th>
            <td><input type="text" id="customer-phone-number"  maxlength="13"></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <select id="customer-gender">
                    <option disabled selected>성별 선택</option>
                    <option value="MAIL">남성</option>
                    <option value="FEMAIL">여성</option>
                </select>
            </td>
            <th>지점명</th>
            <td>
                <input type="text" id="customer-branch-id" value="${branchName}" disabled>
            </td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text" id="emp-name-input" value="${employeeName}" disabled></td>
            <th>등록일자</th>
            <fmt:parseDate value="${businessDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedBusinessDate" />
            <td>
                <input type="text" id="start-date-input" value="<fmt:formatDate value='${parsedBusinessDate}' pattern='yyyy-MM-dd' />" disabled>
            </td>
        </tr>

    </table>
    <div class="d-flex justify-content-end mt-4 mb-4">
        <button class="basic-btn" id="customer-save-btn">추가하기</button>
    </div>

</div>

<%@ include file="/resources/components/modal/customer-detail-modal.jsp" %>
<script src="/resources/js/footer.js"></script>
<script src="/resources/js/page/customer-save.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }


                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("customer-address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("customer-detail-address").focus();
            }
        }).open();
    }
</script>
</body>
</html>