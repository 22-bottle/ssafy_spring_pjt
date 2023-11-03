<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
    
<script>
	alert("회원가입을 성공했어요!☺️");
	location.href = "${root}/user/login";
</script>
 
 