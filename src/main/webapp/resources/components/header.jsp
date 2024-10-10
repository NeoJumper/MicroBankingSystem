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
      <img  src="/resources/assets/neobank-logo.png" alt="Logo" id="header-logo-img"  />
    </div>
    <div id="systemName">네오은행</div>
    <div id="colContianer">
      <div id="business-day-header">
      <div id="business-day-date"></div>
      <div id="business-day-status">
        <span></span>
      </div>
    </div>
      <div id="userInfoHeader">
        <div id="systemInfo">
          <div id="user-branch-name"></div>
          <div id="user-roles"></div>
          <div id="user-name"></div>
          <div id="logoutButton"><a href="/api/common/logout">로그아웃</a></div>
        </div>
      </div>
    </div>
    </div>
  <nav id="navbar">
  </nav>

</div>

<!-- jQuery 라이브러리 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/header.js"></script>

</body>
</html>
