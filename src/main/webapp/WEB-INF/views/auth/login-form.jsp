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
            <img src="/resources/assets/logo.png" alt="Logo" />
        </div>
        <div>
            <h2>NeoV</h2>
        </div>
    </div>
</div>

<div id="mainContent" class = "container d-flex justify-content-center align-items-center" style="height: 80vh;">
    <div class = "col-6 d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2 class="fw-bold">로그인</h2>
        </div>

        <form action="/auth/login" method="post">

            <div class="d-flex justify-content-start"><p class="fw-bold">이메일</p></div>
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
                    <p style="margin:0px;">이메일로 로그인</p>
                </div>
            </button>
        </form>




        <div style="position:relative;">
            <hr style ="margin: 30px 0px;">
            <div style = "position:absolute; left:48%; top:17px; height:50px; margin: 0px 5px; background-color:white;"><p> OR </p></div>
        </div>

        <a href="/oauth2/authorization/google" class="d-flex btn btn-white mb-4 border text-color-gray1" style="text-decoration: none;">

            <div class="col-4 d-flex justify-content-end ms-4 me-2 google-login-img">
                <img src="/resources/assets/google.PNG" alt="Logo" />
            </div>
            <p style="margin: 0px; color: inherit;">Google 계정으로 로그인</p>

        </a>

        <a href="/oauth2/authorization/kakao" class="d-flex btn btn-dark mb-4 border text-color-gray1" style="background-color:#FFE812; text-decoration: none;">
            <div class="col-4 d-flex justify-content-end ms-4 me-2 login-img">
                <img src="/resources/assets/kakaotalk.png" alt="Logo" />
            </div>
            <p style="margin:0px; color: black;">카카오 계정으로 로그인</p>
        </a>
        <button class="d-flex btn  mb-4 border text-color-white" style="background-color:#00BF18;" type="button">
            <div class ="col-4 d-flex justify-content-end ms-4 me-2 login-img">
                <img src="/resources/assets/naver.png" alt="Logo" />
            </div>
            <p style="margin:0px;">네이버 계정으로 로그인</p>
        </button>



    </div>
</div>



</body>
</html>
