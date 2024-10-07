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
            <img  src="/resources/assets/kcc-logo.png" alt="Logo" id="header-logo-img"  />
        </div>
        <div>
            <h2>Micro Banking System</h2>
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
            <hr>


            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br/>
            <button class="col-12 d-flex justify-content-center btn border text-color-white" style="background-color:#3391FF;" type="submit">
                <div class ="d-flex  login-img">
                    <p style="margin:0px;"> 로그인 </p>
                </div>
            </button>
        </form>




    </div>
</div>



</body>
</html>
