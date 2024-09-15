<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />

    <title>회원가입</title>
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

    <div id="mainContent" class = "container d-flex justify-content-center">
        <div class = "col-6 d-flex flex-column"> 
          <div class="d-flex justify-content-center">
            <h2 class="fw-bold">환영합니다!</h2>
          </div>
          <div class="d-flex justify-content-center">
            <h6 class= "text-color-gray1">휴가관리 솔루션 NeoV, 지금 바로 시작해보세요</h6>
          </div>

          <form action="/auth/join" method="post">
            <div class="d-flex justify-content-start"><p class="fw-bold">이메일</p></div>
            <div class="d-flex justify-content-start">
              <input name="email" type="text" class="form-control" >
            </div>

            <div class="d-flex justify-content-start" style="margin-top:16px;"><p class="fw-bold">비밀번호</p></div>
            <div class="d-flex justify-content-start">
              <input name = "password" id="password" type="password" class="form-control">
            </div>
            <div class="d-flex justify-content-start"><p class ="text-color-gray2">* 10자 이상이면서 영문, 숫자, 특수문자를 모두 포함하세요</p></div>


            <div class="d-flex justify-content-start"><p class="fw-bold">비밀번호 재확인</p></div>
            <div class="d-flex justify-content-start">
              <input name="passwordCheck" id="passwordCheck" type="password" class="form-control">
            </div>
            <div class="d-flex justify-content-start"><p class ="text-color-gray2">* 비밀번호를 다시 입력해주세요</p></div>

            <div class = "d-flex">
              <div class = "col-6 pe-3">
                <div class="d-flex justify-content-start"><p class="fw-bold">이름<p></div>
                <div class="d-flex justify-content-start">
                  <input name="name" id="name" type="text" class="form-control">
                </div>
              </div>

              <div class = "col-6 ps-3">
                <div class="d-flex justify-content-start"><p class="fw-bold">전화번호(선택)<p></div>
                <div class="d-flex justify-content-start">
                  <input name="phoneNumber" id="phoneNumber" type="text" class="form-control">
                </div>
              </div>
            </div>

            <hr>
            <button class="col-12 d-flex justify-content-center btn border text-color-white" style="background-color:#3391FF;" type="submit">
              <div class ="d-flex  login-img">
                <p style="margin:0px;">이메일로 가입하기</p>
              </div>
            </button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br/>

          </form>



          <div style="position:relative;">
            <hr style ="margin: 30px 0px;">
            <div style = "position:absolute; left:48%; top:17px; height:50px; margin: 0px 5px; background-color:white;"><p> OR </p></div>
          </div>

          <a href="/oauth2/authorization/google" class="d-flex btn btn-white mb-4 border text-color-gray1" style="text-decoration: none;">

            <div class="col-4 d-flex justify-content-end ms-4 me-2 google-login-img">
              <img src="/resources/assets/google.PNG" alt="Logo" />
            </div>
            <p style="margin: 0px; color: inherit;">Google 계정으로 가입하기</p>

          </a>

          <a href="/oauth2/authorization/kakao" class="d-flex btn btn-dark mb-4 border text-color-gray1" style="background-color:#FFE812; text-decoration: none;">
            <div class="col-4 d-flex justify-content-end ms-4 me-2 login-img">
              <img src="/resources/assets/kakaotalk.png" alt="Logo" />
            </div>
            <p style="margin:0px; color: black;">카카오 계정으로 가입하기</p>
          </a>
          <button class="d-flex btn  mb-4 border text-color-white" style="background-color:#00BF18;" type="button">
            <div class ="col-4 d-flex justify-content-end ms-4 me-2 login-img">       
              <img src="/resources/assets/naver.png" alt="Logo" />
            </div>
            <p style="margin:0px;">네이버 계정으로 가입하기</p>
          </button>



        </div>
      </div>


    <!-- 모달 -->

    <div class="modal fade" id="certificateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
      <div class="modal-dialog" style="margin-top:200px;">
        <div class="modal-content">
          <div class="modal-header">
            <h2 class="modal-title fs-5 fw-bold" id="exampleModalLabel">직원 합류</h2>
          </div>
          <div class="modal-body d-flex flex-column">
            <div class="d-flex mb-2">
              <div class="col-2 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">이메일</p></div>
              <div class="col-6 d-flex border border-2 align-items-center" style="position: relative">
                <input style="font-size: 14px" id="email-input1" class= "col-6 code-input" type="text">
                <input style="font-size: 14px" id="email-input2" class= "col-6 code-input" type="text">
                <div style="position: absolute; top:3px; left:47%;"><h3 style="font-size:17px;">@</h3></div>
              </div>
            </div>
            <div id="loginFailMessageByEmail" class="loginFailMessage" style="display: none; margin-left: 17%; margin-bottom: 5px; margin-top: 5px">
              등록되지 않은 이메일입니다.
            </div>

            <div class="d-flex ">
              <div class="col-2 d-flex align-items-center"><p style="margin-bottom: 0; padding-bottom: 2px;">합류코드</p></div>
              <div class="col-6 d-flex border border-2 align-items-center" style="position: relative">
                <input id="authentication-code-input1" class= "col-6 code-input" placeholder="AAAA" maxlength="4" type="text">
                <input id="authentication-code-input2" class= "col-6 code-input" placeholder="AAAA" maxlength="4" type="text">
                <div style="position: absolute; left:48%;"><h3>-</h3></div>
              </div>
            </div>
            <div id="loginFailMessageByCode" class="loginFailMessage" style="display: none; margin-left: 17%; margin-bottom: 5px; margin-top: 5px">
              유효하지 않은 인증코드입니다.
            </div>
            <div class="my-3 d-flex justify-content-start">
              <p class ="text-color-gray2" style="font-size: 12px;">
                     * 합류코드는 관리자에게서 받은 초대이메일/문자메시지에서 찾을 수 있습니다.
                <br> * 합류코드를 받지 못했다면, 관리자에게 문의해주세요
              </p>
            </div>


          </div>
          <div class="modal-footer">
            <button id="certificateBtn" type="button" class="btn btn-primary">인증하기</button>
          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="/resources/js/join-form.js"></script>
  </body>


</html>
