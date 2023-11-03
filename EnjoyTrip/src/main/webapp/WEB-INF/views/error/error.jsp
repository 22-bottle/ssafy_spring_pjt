<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/nav.jsp" %>
	
	<div class="d-flex mt-5"></div>
	
	<div class="min-vh-100">
	
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
	          <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
	            	잘못된 페이지입니다
	          </h2>
	    </div>
	    
	    <div class="d-flex justify-content-center">
	       <a type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm" href="${root}/main/index">메인으로  돌아가기 </a>
	     </div>
	</div>
	
	
	
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>