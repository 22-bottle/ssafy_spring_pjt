<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<head>
	<style>
	  body {
	    font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui,
	      Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR",
	      "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
	    font-size: 18px;
	    height: 800px;
	  }
	</style>
</head>

<nav class="navbar navbar-expand-sm bg-light fixed-top shadow">
	<div class="container" style="width: 100%">
		<div id="navb" class="navbar-collapse d-flex justify-content-between">
		
			<!-- 왼쪽 메뉴 -->
			<div class="justify-content-start">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="${root }/index.jsp">홈</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${root }/trip?action=trip">지역별여행지</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${root }/board?action=list">여행후기</a>
				</li>
			</ul>
			</div>
			
			<!-- 로고 -->
			<div id="navb-logo" class="justify-content-center">
				<a class="navbar-brand " href="${root }/index.jsp">
			        <img
			          src="${root }/assets/img/logo.png"
			          href="${root }/index.jsp"
			          style="width: 100px"
			          alt="logo image"
			        />
			    </a>
		    </div>
		    
			<!-- 로그인 정보 -->
			<div id="navb-login" >
				<c:set var="userInfo" value="${session.getAttribute.userInfo }" scope="request"></c:set>
				
				<c:choose>
					<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
					<c:when test="${empty userInfo }">
						<ul class="navbar-nav">
							<li class="nav-item">
								<a class="nav-link" href="${root }/user/login.jsp">로그인</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="${root }/user/join.jsp">회원가입</a>
							</li>
						</ul>
					</c:when>
					
					<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
					<c:otherwise>
						<ul class="navbar-nav">
							<li class="nav-item">
								<a class="nav-link" href="${root }/user/mypage.jsp">${userInfo.userId }님 반가워요!</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="${root }/user?action=logout">로그아웃</a>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
	</div>
</nav>
