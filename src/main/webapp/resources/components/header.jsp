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
        <div id="user-roles"></div>
        <div id="user-branch-name"></div>
        <div id="user-name"></div>
        <div id="logoutButton"><a href="/auth/logout">로그아웃</a></div>
      </div>
    </div>
    <div id="business-day-header">
      <div id="business-day-date"></div>
      <div id="business-day-status">
        <span></span>
      </div>
    </div>
  </div>
  <nav id="navbar">
    <ul id="navbar-globalMenu">
      <li id="header-account-management" ><a href="/page/employee/account-open">계좌 관리</a></li>
      <li id="header-customer-management"><a href="/page/employee/account-open">고객 관리</a></li>
      <li id="header-employee-management"><a href="/page/manager/employee-save">행원 관리</a></li>
      <li id="header-businessday-management"><a href="/page/manager/business-day-management">영업일 관리</a></li>
      <li id="header-branch-management"><a href="/page/employee/account-open">지점 관리</a></li>
    </ul>
  </nav>

</div>

<!-- jQuery 라이브러리 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/header.js"></script>

</body>
</html>
