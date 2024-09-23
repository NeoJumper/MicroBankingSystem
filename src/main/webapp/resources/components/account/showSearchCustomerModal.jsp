<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Modal -->
<div class="modal fade" id="showSearchCustomerModal" tabindex="-1" role="dialog" aria-labelledby="showSearchCustomerModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalLabel" style="margin-right: 10px">고객 정보 검색 모달</h5>
        <p style="margin-bottom: 0px" id="modalStatus"></p>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" id="approveBtn" class="btn btn-success" style="display: none;">승인</button>
        <button type="button" id="rejectBtn" class="btn btn-danger" style="display: none;">반려</button>
      </div>
    </div>
  </div>
</div>


