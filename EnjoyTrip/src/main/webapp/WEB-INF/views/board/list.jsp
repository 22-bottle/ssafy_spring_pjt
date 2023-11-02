<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	
	<div class="d-flex mt-5"></div>
	
	<div class="min-vh-100">
	<div class="row justify-content-center">
		<!-- 공지사항 헤더 -->
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
            	여행후기
          </h2>
        </div>
        
        <!-- 공지사항 내용 -->
        <div class="col-lg-8 col-md-10 col-sm-12 d-flex flex-column min-vh-50">
          <div class="row mb-2">
          	
            <!-- 검색하기 -->
            <div class="col-md-7 d-flex justify-content-start">
              <form class="d-flex" id="form-search" action="">
                <input type="hidden" name="action" value="list"/>
                <input type="hidden" name="pgno" value="1"/>
                <select
                  name="key"
                  id="key"
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  aria-label="검색조건"
                >
                  <option value="" selected>검색조건</option>
                  <option value="article_no">글번호</option>
                  <option value="subject">제목</option>
                  <option value="user_id">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" name="word" id="word" class="form-control" placeholder="검색어를 입력하세요..." />
                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
            
            <!-- 글쓰기 버튼 -->
            <div class="col-md-4 d-flex justify-content-end">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">글쓰기 </button>
            </div>
          </div>
          
          <!-- 공지사항 테이블 -->
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">댓글수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>    
				<c:forEach var="article" items="${articles}">    
	              <tr class="text-center">
	                <th scope="row">${article.getArticleNo()}</th>
	                <td class="text-start">
	                
	                  <a
	                    href="#"
	                    class="article-title link-dark"
	                    data-no="${article.getArticleNo()}"
	                    style="text-decoration: none"
	                  >
	                  
	                  <c:choose>
	                  	<c:when test="${article.subject.length() > 22 }" >
	                  		${article.subject.substring(0,22) }...
	                  	</c:when>
	                  	<c:otherwise>
	                  		${article.subject }
	                  	</c:otherwise>
	                  </c:choose>
	                    
	                  </a>
	                </td>
	                <td>${article.userId}</td>
	                <td>${article.hit}</td>
	                <td>${article.commentCnt}</td>
	                <td>${article.registerTime}</td>
	              </tr>            
				</c:forEach>   
            </tbody>
          </table>
        </div>
        <div class="row">
          ${navigation.navigator}
        </div>
      </div>
    </div>
    <!-- 페이지 번호 -->
    <form id="form-param" method="get" action="">
      <input type="hidden" id="p-action" name="action" value="">
      <input type="hidden" id="p-pgno" name="pgno" value="">
      <input type="hidden" id="p-key" name="key" value="">
      <input type="hidden" id="p-word" name="word" value="">
    </form>
    
    <!-- Include Footer -->
	<div
		style="position: fixed; left: 0; bottom: 0; width: 100%; background-color: #f0f0f0; text-align: center;">
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</div>
    
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          location.href = "${root}/board?action=view&articleno=" + this.getAttribute("data-no");
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board?action=mvwrite";
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  if(!document.querySelector("#key").value){
	       	  alert("앗! 검색 조건을 선택하지 않았어요!😥");
	          return;
          } else{
	    	  let form = document.querySelector("#form-search");
	          form.setAttribute("action", "${root}/board");
	          form.submit();
          }
      });
      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
          console.log(this.parentNode.getAttribute("data-pg"));
          document.querySelector("#p-action").value = "list";
       	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
       	  document.querySelector("#p-key").value = "${param.key}";
       	  document.querySelector("#p-word").value = "${param.word}";
          document.querySelector("#form-param").submit();
        });
      });
    </script>
	
</body>
</html>