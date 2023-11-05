<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp"%>
</head>
<body>
	<%@ include file="../include/nav.jsp"%>

	<%@ include file="../include/confirm.jsp"%>

	<div class="d-flex mt-5"></div>

	<div class="min-vh-100">
		<div class="row justify-content-center">
			<!-- 글쓰기 헤더 -->
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="mt-5 my-3 py-3 shadow-sm text-center">글쓰기</h2>
			</div>

			<!-- 글쓰기 내용 -->
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-register" method="POST" action="">
					<input type="hidden" name="action" value="write">
					<div class="mb-3">
						<label for="subject" class="form-label">제목 : </label> <input
							type="text" class="form-control" id="subject" name="subject"
							placeholder="제목을 입력해주세요..." maxlength="100" />
					</div>
					<div class="mb-3">
						<label for="content" class="form-label">내용 : </label>
						<textarea class="form-control" id="content" name="content"
							rows="7" maxlength="2000" placeholder="내용을 입력해주세요..."></textarea>
					</div>
					<div class="col-auto text-center">
						<button type="button" id="btn-register"
							class="btn btn-outline-primary mb-3">글작성</button>
						<button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="../include/footer.jsp"%>

	<script>
		document.querySelector("#btn-register").addEventListener(
				"click",
				function() {
					if (!document.querySelector("#subject").value) {
						alert("앗! 제목을 입력하지 않았어요!😥");
						return;
					} else if (!document.querySelector("#content").value) {
						alert("앗! 내용을 입력하지 않았어요!😥");
						return;
					} else {
						let form = document.querySelector("#form-register");
						form.setAttribute("action",
								"${root}/board/write");
						form.submit();
					}
				});
	</script>
</body>
</html>