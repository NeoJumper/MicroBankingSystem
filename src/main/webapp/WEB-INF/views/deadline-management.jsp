<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common-table.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/deadline-management.css"/>
</head>
<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="mainArea">
    <div>
        <h5>마감일 관리</h5>
    </div>
    <div>
        <div class="d-flex align-items-center">
            <h3 class="mb-0">마감일 관리(행원)</h3>
            <h4 style="background-color :blue; color: white; font-size: 1.2rem; padding: 7px 10px; margin: 0 10px;">은평 제 1지점</h4>
            <h5 class="mb-0">2024-09-24</h5>
        </div>
        <hr>
    </div>
    <div class="d-flex">
        <div class="col-9">
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">현금 입금액</label>
                    <input class="amount-input" type="text" value="400,000" disabled>
                </div>
                <div>
                    <label class="amount-label2">거래내역 현금 입금액</label>
                    <input class="amount-input" type="text" value="400,000" disabled>
                </div>
            </div>
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">현금 출금액</label>
                    <input class="amount-input" type="text" value="1,800,000" disabled>
                </div>
                <div>
                    <label class="amount-label2">거래내역 현금 출금액</label>
                    <input class="amount-input" type="text" value="1,800,000" disabled>
                </div>
            </div>
            <div class="d-flex w-100">
                <div class="me-5 mb-2">
                    <label class="amount-label1">초기 금액</label>
                    <input class="amount-input" type="text" value="1,800,000" disabled>
                </div>
                <div>
                    <label class="amount-label2">금일 마감 금액</label>
                    <input class="amount-input" type="text" value="1,800,000" disabled>
                </div>
            </div>

        </div>
        <div class="col-3 d-flex justify-content-end">
            <div >
                <button class ="update-btn">
                    내역 상세보기
                </button>
            </div>
            <div >
                <button class ="update-btn">
                    수정하기
                </button>
            </div>
        </div>
    </div>

    <div class="mt-4">
        <div class="d-flex align-items-center">
            <h3 class="mb-0">거래 금액</h3>
        </div>
        <hr>
    </div>

    <div>
        <table class="table">
            <thead>
            <tr>
                <th style="width: 10%;">사번</th>
                <th style="width: 10%;">이름</th>
                <th style="width: 20%;">생년월일</th>
                <th style="width: 20%;">전화번호</th>
                <th style="width: 20%;">이메일</th>
                <th style="width: 10%;">지점</th>
                <th style="width: 10%;">직책</th>
            </tr>
            </thead>
        </table>

        <div id="employee-add-list" style="overflow-y: auto; height: 200px;">
            <table class="table table-hover">
                <tbody>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>
                <tr class="employee-element">
                    <td style="width: 10%;">1001</td>
                    <td style="width: 10%;">지승용</td>
                    <td style="width: 20%;">1999-02-14</td>
                    <td style="width: 20%;">010-5355-4406</td>
                    <td style="width: 20%;">gkfktkrh153@naver.com</td>
                    <td style="width: 10%;">은평 1지점</td>
                    <td style="width: 10%;">행원</td>
                </tr>

                <!-- 추가 행들 -->
                </tbody>
            </table>
        </div>

    </div>
</div>


<script src="/resources/js/footer.js"></script>
</body>
</html>