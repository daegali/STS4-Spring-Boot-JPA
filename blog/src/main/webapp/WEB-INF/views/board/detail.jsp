<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">

	<div class="form-group">
		<h4>${board.title}</h4>
	</div>
	<hr>
	<div class="form-group">
		<div>${board.content}</div>
	</div>
	<br /> <br />
	<div>
		글 번호: <span id="id"><i>${board.id} </i></span>&nbsp; 작성자: <span><i>${board.user.username} </i></span>
		&nbsp;작성일: <span id = "id"><i>${board.createDate }</i></span>&nbsp; 조회수: <span id = "id"><i>${board.count}</i></span>
	</div>
	<br />
	<br />
	<p class="pagination justify-content-center">
		<c:if test="${board.user.id == principal.user.id}">
		<a href = "/board/${board.id }/updateForm" class="btn btn-warning">수정</a>
		</c:if>
		<button class="btn btn-secondary" onclick="history.back()">목록</button>
		<c:if test="${board.user.id == principal.user.id}">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
	</p>
</div>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>
