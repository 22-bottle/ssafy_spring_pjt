<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
    
<script>
	alert("회원탈퇴가 완료되었어요!");
	location.href = "${root}/user/logout";
</script>
 
 