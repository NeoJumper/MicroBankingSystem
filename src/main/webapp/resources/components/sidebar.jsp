<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Sidebar Example</title>
  <link rel="stylesheet" href="/resources/css/sidebar.css" />

  <!-- jQuery 라이브러리 추가 -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <script>
    $(document).ready(function () {
      $(".sidebar-dropdown").on("click", function () {
        var $sidebarSubmenu = $(this).next(".sidebar-submenu");
        var isExpanded = $(this).attr("aria-expanded") === "true";


        if (isExpanded) {
          $sidebarSubmenu.removeClass("show").css("max-height", "0");
        } else {
          $sidebarSubmenu.addClass("show");

          $sidebarSubmenu.css("max-height", "0");

          requestAnimationFrame(function () {
            $sidebarSubmenu.css("max-height", $sidebarSubmenu[0].scrollHeight + "px");
          });
        }
        $(this).attr("aria-expanded", !isExpanded);
      });

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
        mainArea.style.paddingLeft = "100px";
      } else {
        sidebar.classList.remove("closed");
        sidebar.classList.add("open");
        menubar.classList.add("show-content");
        mainArea.style.paddingLeft = "350px";
      }
    }

  </script>
</head>
<body>
<div id="leftSidebar-wrapper" class="closed">
  <button
          id="leftSidebarBtn"
          onclick="toggleSidebar()"
          class="btn btn-primary"
  >
    <i class="bi bi-chevron-right"></i>
  </button>

  <div id="menubar" class="hidden-content">
    <!-- Sidebar -->
    <nav id="sidebar">
      <ul class="list-unstyled components">
        <li class="menu">
          <a
                  href="#"
                  data-toggle="collapse"
                  aria-expanded="false"
                  class="dropdown-toggle sidebar-dropdown"
          ><i class="bi bi-calendar-range"></i> 휴가 관리</a
          >
          <ul class="submenu sidebar-submenu collapse list-unstyled">
            <li><a href="#">캘린더</a></li>
            <li><a href="#">휴가 신청 / 취소</a></li>
          </ul>
        </li>
        <hr class="sidebarHr"/>

        <li class="menu">
          <a
                  href="#"
                  data-toggle="collapse"
                  aria-expanded="false"
                  class="dropdown-toggle sidebar-dropdown"
          ><i class="bi bi-folder2"></i> 문서 관리</a
          >
          <ul class="submenu sidebar-submenu collapse list-unstyled" id="documentSubmenu">
            <li><a href="#">휴가 증명서 발급</a></li>
            <li><a href="#">연차 촉진 요청서 확인</a></li>
          </ul>
        </li>
        <hr class="sidebarHr"/>

        <li class="menu">
          <a
                  href="#"
                  data-toggle="collapse"
                  aria-expanded="false"
                  class="dropdown-toggle sidebar-dropdown"
          ><i class="bi bi-pencil-square"></i> 결재 관리</a
          >
          <ul class="submenu sidebar-submenu collapse list-unstyled" id="approvalSubmenu">
            <li><a href="#">휴가 신청 확인</a></li>
            <li><a href="#">휴가 취소 신청 확인</a></li>
          </ul>
        </li>
        <hr class="sidebarHr"/>

        <li class="menu">
          <a
                  href="#"
                  data-toggle="collapse"
                  aria-expanded="false"
                  class="dropdown-toggle sidebar-dropdown"
          ><i class="bi bi-person-fill"></i> 프로필</a
          >
          <ul class="submenu sidebar-submenu collapse list-unstyled" id="profileSubmenu">
            <li><a href="#">내 정보 수정</a></li>
          </ul>
        </li>
        <hr class="sidebarHr"/>
        <li class="menu">
          <a href="#" style="color: #757575"
          ><i class="bi bi-box-arrow-left"></i> 로그아웃</a
          >
        </li>
      </ul>
    </nav>
  </div>
</div>
</body>
</html>
