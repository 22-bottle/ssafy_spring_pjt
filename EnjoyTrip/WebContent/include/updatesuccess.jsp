<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
    
<script>
	alert("회원정보를 업데이트했어요!");
	location.href = "${root}/user/mypage.jsp";
</script>
 
 