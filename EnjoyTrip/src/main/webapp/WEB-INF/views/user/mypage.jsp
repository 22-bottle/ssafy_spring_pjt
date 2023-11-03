<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="userInfo" value="${session.getAttribute.userInfo }" scope="request"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/nav.jsp" %>

	<div class="d-flex mt-5"></div>
	
	<div class="row justify-content-center">
	<!-- 회원정보수정 헤더 -->
	<div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
        	회원정보수정
      </h2>
    </div>
    
	<!-- 회원정보수정 폼 -->
	<div class="container d-flex justify-content-center">
      <div class="mypage-body pt-4 ">
      	<form id="user-modify" action="" method="post">
			<input type="hidden" name="action" value="login">
			
			<!-- 아이디 -->
			<div class="form-group join-id row justify-content-center my-2">
				<div class="col-4">
					<label for="userId">아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="userId" name="userId" placeholder="" value="${userInfo.userId }" readonly>
				</div>
			</div>
			
			<!-- 이름 -->
			<div class="form-group join-name row justify-content-center my-2">
				<div class="col-4">
					<label for="userName">이름 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="userName" name="userName" placeholder="" value="${userInfo.userName }">
				</div>
			</div>
			
			<!-- 패스워드 -->
			<div class="form-group join-pw row justify-content-center my-2">
				<div class="col-4">
					<label for="userPassword">패스워드 </label> 
				</div>
				<div class="col">
					<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="" value="">
				</div>
			</div>
			
			<!-- 이메일아이디 -->
			<div class="form-group join-emailid row justify-content-center my-2">
				<div class="col-4">
					<label for="emailId">이메일아이디 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="emailId" name="emailId" placeholder="" value="${userInfo.emailId }">
				</div>
			</div>
			
			<!-- 이메일도메인 -->
			<div class="form-group join-emaildomain row justify-content-center my-2">
				<div class="col-4">
					<label for="emailDomain">이메일도메인 </label> 
				</div>
				<div class="col">
					<input type="text" class="form-control" id="emailDomain" name="emailDomain" placeholder="" value="${userInfo.emailDomain }">
				</div>
			</div>
			
			<!-- 정보수정 버튼 -->
			<div class="row d-flex mt-3">
				<button type="submit" class="btn btn-outline-success" id="updateUser"> 정보수정</button>			
			</div>
			
			<!-- 회원탈퇴 버튼 -->
			<div class="row d-flex mt-3">
				<button type="submit" class="btn btn-outline-danger" id="deleteUser"> 회원탈퇴</button>			
			</div>
			
		</form>
      </div>
	</div>
	</div>
	
	<script>
	document.querySelector("#updateUser").addEventListener("click", function () {
		if (!document.querySelector("#userName").value) {
          	alert("앗! 아이디를 입력하지 않았어요!😥");
          	return;
        } else if (!document.querySelector("#userPassword").value) {
	       	alert("앗! 비밀번호를 입력하지 않았어요!😥");
	       	return;
        } else if (!document.querySelector("#emailId").value) {
          	alert("앗! 이메일을 입력하지 않았어요!😥");
          	return;
        } else if (!document.querySelector("#emailDomain").value) {
	       	alert("앗! 이메일도메인을 입력하지 않았어요!😥");
	       	return;
        } else{
			let form = document.querySelector("#user-modify");
	        form.setAttribute("action", "${root}/user/update");
	        form.submit();
        }
      });
    document.querySelector("#deleteUser").addEventListener("click", function () {
        alert("정말로 탈퇴하시겠어요?😥");
        let form = document.querySelector("#user-modify");
        form.setAttribute("action", "${root}/user?action=delete");
        form.submit();
      });
    </script>
	
</body>
</html>