<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/nav.jsp" %>

	<div class="d-flex mt-5"></div>
	
	<div class="row justify-content-center">
	<!-- 회원가입 헤더 -->
	<div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
        	회원가입
      </h2>
    </div>
    
	<!-- 회원가입 폼 -->
	<div class="container d-flex justify-content-center">
      <div class="login-body pt-4 ">
      	<form action="${root }/user?action=regist" method="post">
			<input type="hidden" name="action" value="login">
			
			<!-- 아이디 -->
			<div class="form-group join-id row justify-content-center my-2">
				<div class="col-4">
					<label for="userId">아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="userId" name="userId" placeholder="">
				</div>
			</div>
			
			<!-- 이름 -->
			<div class="form-group join-name row justify-content-center my-2">
				<div class="col-4">
					<label for="userName">이름 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="userName" name="userName" placeholder="">
				</div>
			</div>
			
			<!-- 패스워드 -->
			<div class="form-group join-pw row justify-content-center my-2">
				<div class="col-4">
					<label for="userPassword">패스워드 </label> 
				</div>
				<div class="col">
					<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="">
				</div>
			</div>
			
			<!-- 이메일아이디 -->
			<div class="form-group join-emailid row justify-content-center my-2">
				<div class="col-4">
					<label for="emailId">이메일아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="emailId" name="emailId" placeholder="">
				</div>
			</div>
			
			<!-- 이메일도메인 -->
			<div class="form-group join-emaildomain row justify-content-center my-2">
				<div class="col-4">
					<label for="emailDomain">이메일도메인 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="emailDomain" name="emailDomain" placeholder="">
				</div>
			</div>
			
			<!-- 회원가입 버튼 -->
			<div class="row d-flex mt-3">
				<button type="submit" class="btn btn-primary" id="joinUser"> 회원가입</button>			
			</div>
			
		</form>
      </div>
	</div>
	</div>
	
</body>
</html>