<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Sidebar Example</title>
  <link rel="stylesheet" href="/resources/css/sidebar.css"/>

  <!-- jQuery 라이브러리 추가 -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <script>
    $(document).ready(function () {
      // 사이드바 토글
      $("#sidebarCollapse").on("click", function () {
        $("#sidebar").toggleClass("active");
      });
    });

    function toggleSidebar() {
      var sidebar = document.getElementById("leftSidebar-wrapper");
      var menubar = document.getElementById("menubar");
      var mainArea = document.getElementById("mainArea");
      var isOpen = sidebar.classList.contains("open");

      if (isOpen) {
        sidebar.classList.remove("open");
        sidebar.classList.add("closed");
        menubar.classList.remove("show-content");
        menubar.classList.add("hidden-content"); // 숨기기

        mainArea.style.paddingLeft = "100px";
      } else {
        sidebar.classList.remove("closed");
        sidebar.classList.add("open");
        menubar.classList.remove("hidden-content"); // 표시하기
        menubar.classList.add("show-content");

        mainArea.style.paddingLeft = "300px";
      }
    }
  </script>
</head>
<body>
<div id="leftSidebar-wrapper" class="open">
  <button
          id="leftSidebarBtn"
          onclick="toggleSidebar()"
          class="btn btn-primary"
  >
    <i class="bi bi-chevron-right"></i>
  </button>

  <div id="menubar" class="show-content">
    <!-- Sidebar -->
    <nav id="sidebar">
      <ul class="list-unstyled components">
        <li class="menu">
          <a href="#">
            <i class="bi bi-bank"></i> 예금 관리
          </a>
          <ul class="submenu list-unstyled components">
            <li><a href="#">계좌 개설</a></li>
            <li><a href="#">계좌 해지</a></li>
            <li><a href="#">계좌 해지 취소</a></li>
            <li><a href="#">예금 잔액 증명</a></li>
          </ul>
        </li>
        <li class="menu">
          <a href="#">
            <i class="bi bi-search"></i> 계좌 조회
          </a>
          <ul class="submenu list-unstyled components">
            <li><a href="#">계좌 조회</a></li>
          </ul>
        </li>
        <li class="menu">
          <a href="#">
            <i class="bi bi-arrow-right-circle"></i> 계좌 이체
          </a>
          <ul class="submenu list-unstyled components">
            <li><a href="#">즉시 이체</a></li>
            <li><a href="#">계좌 이체 내역</a></li>
            <li><a href="#">계좌 입출금</a></li>
          </ul>
        </li>
      </ul>
    </nav>
  </div>
</div>
</body>
</html>
