<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
    
<script>
	alert("아이디 또는 비밀번호가 일치하지 않아요!😥");
	location.href = "${root}/user/login";
</script>
 
 