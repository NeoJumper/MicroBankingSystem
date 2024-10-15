<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/signup.css" />
</head>

<body>
<div id="customHeader">
    <div id="imgContainer" class= "d-flex">

        <div class ="me-2">
            <img  src="/resources/assets/neobank-logo.png" alt="Logo" id="header-logo-img"  />
        </div>
        <div>
            <h2>네오은행</h2>
        </div>
    </div>
</div>

<div id="mainContent" class = "container d-flex justify-content-center align-items-center" style="height: 80vh;">
    <div class = "col-6 d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2 class="fw-bold">로그인</h2>
        </div>

        <form action="/api/anonymous/login" method="post">

            <div class="d-flex justify-content-start"><p class="fw-bold">아이디</p></div>
            <div class="d-flex justify-content-start">
                <input type="text" class="form-control" name = "username">
            </div>

            <div class="d-flex justify-content-start" style="margin-top:16px;"><p class="fw-bold">비밀번호</p></div>
            <div class="d-flex justify-content-start">
                <input type="password" class="form-control"  name="password">
            </div>

            <c:if test="${param.get('error')}">
                <div class="loginFailMessage">
                        ${exception}
                </div>
            </c:if>


            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br/>
            <div class="row justify-content-center">
            <button class="justify-content-center basic-btn " type="submit">
                    로그인
            </button>
            </div>
        </form>




    </div>
</div>



</body>
</html>
