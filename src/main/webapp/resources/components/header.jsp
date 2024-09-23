<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Header Example</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/header.css"/>
</head>

<body>
<div id="customHeader">
  <div id="informationHeader">
    <div id="imgContainer">
      <img  src="/resources/assets/kcc-logo.png" alt="Logo" id="header-logo-img"  />

    </div>
    <div id="userInfoHeader">
      <div id="systemName">Micro Banking System</div>
      <div id="systemInfo">
        <div id="userStatus">행원</div>
        <div id="userinfo">은평 1지점 유은서님</div>
        <div id="logoutButton">로그아웃</div>
      </div>
    </div>
    <div id="dateStatusHeader">
      <div id="nowDateTime">2024년 09월 10일 오후 17시 41분</div>
      <div id="branchStatus">영업중</div>
    </div>
  </div>
  <nav id="navbar">
    <ul id="navbar__globalMenu">
      <li><a href="" style="background-color:white;color :#0079D4;">계좌 관리</a></li>
      <li><a href="">고객 관리</a></li>
      <li><a href="">행원 관리</a></li>
      <li><a href="">영업일 관리</a></li>
      <li><a href="">지점 관리</a></li>
    </ul>
  </nav>

</div>


</body>
</html>
