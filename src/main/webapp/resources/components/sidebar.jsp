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

    </nav>
  </div>
</div>

<script src="/resources/js/sidebar.js"></script>
</body>
</html>
