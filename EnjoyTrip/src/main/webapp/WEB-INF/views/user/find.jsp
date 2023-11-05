<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>

<body>
	<%@ include file="/include/nav.jsp" %>

	<div class="d-flex mt-5"></div>

    <div class="row justify-content-center">
    
    <!-- 비번 찾기 헤더 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
        	비밀번호 찾기
      </h2>
    </div>
    
	<!-- 비번 찾기 폼 -->
	<div class="container d-flex justify-content-center">
      <div class="login-body pt-4 ">
      	<form id="form-find" action="${root }/user?action=find" method="post">
			<input type="hidden" name="action" value="login">
			
			<!-- 아이디 -->
			<div class="form-group find-id row justify-content-center my-2">
				<div class="col-4">
					<label for="id">아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="id" name="id" placeholder="">
				</div>
			</div>
			
			<!-- 이름 -->
			<div class="form-group find-name row justify-content-center my-2">
				<div class="col-4">
					<label for="pw">이름 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="name" name="name" placeholder="">
				</div>
			</div>
			
			<!-- 비번 찾기 버튼 -->
			<div class="row d-flex mt-3">
				<button type="submit" id="btn-find" class="btn btn-primary">비밀번호 찾기</button>			
			</div>
			
		</form>
      </div>
	</div>
    
    <script>
	   	document.querySelector("#btn-find").addEventListener("click", function(){
	   		if (!document.querySelector("#id").value) {
  	          alert("앗! 아이디를 입력하지 않았어요!😥");
  	          return;
  	        } else if (!document.querySelector("#name").value) {
  	          alert("앗! 이름을 입력하지 않았어요!😥");
  	          return;
  	        } 
	   	});
    </script>
    
</body>
</html>