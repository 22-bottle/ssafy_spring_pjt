<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
    
<script>
	alert("회원정보를 찾을 수 없어요!😥");
	location.href = "${root}/user/find.jsp";
</script>
 
 