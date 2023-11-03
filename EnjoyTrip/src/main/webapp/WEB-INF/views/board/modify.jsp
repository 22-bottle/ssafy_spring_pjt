<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/nav.jsp" %>
	
	<c:if test="${queryStrig eq null }">
		<script>
		console.log("queryStrig");
		</script>
	</c:if>
	<c:if test="${article eq null}">
		<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/article?action=list";
		</script>
	</c:if>
      <%@ include file="/include/confirm.jsp" %>
      
      <div class="d-flex mt-5"></div>
      
      <div class="min-vh-100">
      <div class="row justify-content-center">
        <!-- 글수정 헤더 -->
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
            	글수정
          </h2>
        </div>
        
        <!-- 글수정 내용 -->
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-modify" method="POST" action="">
          	<input type="hidden" name="action" value="modify">
          	<input type="hidden" name="articleno" value="${article.getArticleNo()}">
            <div class="mb-3">
              <label for="subject" class="form-label">제목 : </label>
              <input type="text" class="form-control" id="subject" name="subject" value="${article.subject}" />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7">${article.content}</textarea>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">글수정</button>
              <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">글목록</button>
            </div>
          </form>
      </div>
      </div>
    </div>
    
    <%@ include file="../include/footer.jsp" %>
    
    <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("앗! 제목을 입력하지 않았어요!😥");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("앗! 내용을 입력하지 않았어요!😥");
          return;
        } else {
          let form = document.querySelector("#form-modify");
      	  // origin
          //form.setAttribute("action", "${root}/board");
          // 추가
          form.setAttribute("action", "${root}/board?action=list&pgno=${pgno}&key=&word=#");
          form.submit();
        }
      });
      document.querySelector("#btn-list").addEventListener("click", function () {
    	// origin
        //location.href = "${root}/board?action=list";
        // 추가
        location.href = "${root}/board?action=list&pgno=${pgno}&key=&word=#";
      });
    </script>
</body>
</html>
