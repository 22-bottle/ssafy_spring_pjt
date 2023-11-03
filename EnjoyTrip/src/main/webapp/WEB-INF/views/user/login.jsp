<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="id" value="${cookie.id.value }"/>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/head.jsp" %>
</head>

<body>
	<%@ include file="../include/nav.jsp" %>

	<div class="d-flex mt-5"></div>

    <div class="row justify-content-center">
    
    <!-- 로그인 헤더 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
        	로그인
      </h2>
    </div>
    
	<!-- 로그인 폼 -->
	<div class="container d-flex justify-content-center">
      <div class="login-body pt-4 ">
      	<form id="form-login" action="" method="post">
			<input type="hidden" name="action" value="login">
			
			<!-- 아이디 -->
			<div class="form-group login-id row justify-content-center my-2">
				<div class="col-4">
					<label for="id">아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="id" name="userId" placeholder="" value="${id }">
				</div>
			</div>
			
			<!-- 패스워드 -->
			<div class="form-group login-pw row justify-content-center my-2">
				<div class="col-4">
					<label for="pw">패스워드 </label> 
				</div>
				<div class="col">
					<input type="password" class="form-control" id="pw" name="userPassword" placeholder="">
				</div>
			</div>
			
			<!-- 아이디 기억하기 -->
			<div class="form-group form-check">
				<label class="form-check-label"> 
					<input class="form-check-input" type="checkbox" name="remember"
					<c:if test="${!empty id }"> checked="checked"</c:if> >
					아이디 기억하기
				</label>
			</div>
			
			<!-- 로그인 버튼 -->
			<div class="row d-flex mt-3">
				<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>			
			</div>
			
			<!-- 비밀번호 찾기 버튼 -->
			<div class="row d-flex mt-3">
				<button type="button" id="btn-find" class="btn btn-secondary">비밀번호 찾기</button>			
			</div>
		</form>
      </div>
	</div>
	</div>
	
	<script>
		document.querySelector("#btn-login").addEventListener("click", function(){
	   		if (!document.querySelector("#id").value) {
	          	alert("앗! 아이디를 입력하지 않았어요!😥");
	          	return;
	        } else if (!document.querySelector("#pw").value) {
		       	alert("앗! 비밀번호을 입력하지 않았어요!😥");
		       	return;
	        } else{
        		let form = document.querySelector("#form-login");
	          	form.setAttribute("action", "${root}/user/login");
	          	form.submit();
	        }
	   	});
		
		document.querySelector("#btn-find").addEventListener("click", function(){
			location.href = "${root}/main/find";
		});
	</script>
    
</body>
</html>