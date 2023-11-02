<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="userInfo" value="${session.getAttribute.userInfo }" scope="request"></c:set>
    
<script>
	alert("${userInfo.userId} 님 반가워요!☺️");
	location.href = "${root}/index.jsp";
</script>
 
 