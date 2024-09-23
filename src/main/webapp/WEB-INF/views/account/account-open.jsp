<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>

    <style>


        table {
            width: 100%;
            height: 100%;
            max-width: 1000px;
            margin: 0 auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-size: 14px;
        }

        th {
            background-color: #F5F5F5;
            color: #5F5F5F;


        }


        input[type="text"], input[type="number"], input[type="date"] {
            width: 90%;
            padding: 8px;
            border: 1px solid #ccc;

            box-sizing: border-box;
        }

        input[type="text"]::placeholder {

        }

        /* 테이블 가장자리 둥글게 */
        table {

            overflow: hidden;
        }

        /* 마지막 행 아래에 테두리 제거 */
        table tr:last-child td {
            border-bottom: none;
        }
    </style>
</head>

<body>
<%@ include file="/resources/components/header.jsp" %>
<%@ include file="/resources/components/sidebar.jsp" %>
<div id="mainArea">
    <h3 style="font-weight:bold; color:#5F5F5F;">계좌 개설</h3>

    <br><br>


    <table>
        <tr>
            <th>고객번호</th>
            <td><input type="text"></td>
            <th>비밀번호</th>
            <td><input type="text"></td>
        </tr>
        <tr>
            <th>계약일자</th>
            <td><input type="date"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>잔액(KRW)</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>기준이율</th>
            <td><input type="text" placeholder="%"></td>

            <th>우대이율</th>
            <td><input type="text" placeholder="%"></td>
        </tr>
        <tr>
            <th>축 이자율</th>
            <td><input type="text" placeholder="%"></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>담당자</th>
            <td><input type="text"></td>
            <td colspan="2"></td>
        </tr>
    </table>
</div>
<script src="/resources/js/footer.js"></script>
</body>
</html>
