<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:if test="${article eq null}">
	<script>
	alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
	location.href = "${root}/board?action=list";
	</script>
</c:if>
	
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
		<!-- 글보기 헤더 -->
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="mt-5 my-3 py-3 shadow-sm text-center">
            	글보기
          </h2>
        </div>
        
        <!-- 글보기 내용 -->
        <div class="col-lg-8 col-md-10 col-sm-12">
        
          <div class="row my-2">
            <h2 class="text-primary">${article.subject}</h2>
          </div>
          
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img class="avatar me-2 float-md-start bg-light p-2"
                     src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
                <p>
                  <span class="fw-bold">${article.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${article.registerTime}  |  글번호 : ${article.getArticleNo()}  |  조회 : ${article.hit} </span>
                </p>
              </div>
            </div>
    
            <!-- 본문내 문자열 검색 -->
            <div class="col-md-4 align-self-center text-end">
            	<form action="javascript:kwsearch();">
     				<textarea class="comment-text form-control" id="kw" name="kw" 
						style="width: 70%; height: 10px; resize:none; display:inline" placeholder="본문 내 검색"></textarea>
					<button type="submit" class="btn btn-outline-primary text-end align-top" id="kwsearch" name="kwsearch" style="display:inline">찾기</button>
				</form>
            </div>
            
            <div class="divider mb-3"></div>
            <div class="text-secondary" style="font-size: 18px;"> ${article.content}</div>
            
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">글목록</button>
              <c:if test="${userInfo.userId eq article.userId}">
	              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1"> 글수정</button>
	              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1"> 글삭제	</button>
              </c:if>
            </div>
            
          </div>
        </div>
        
        <!-- 댓글  작성 -->
        
        <div class="col-lg-8 col-md-10 col-sm-12" style="background: #F5F5F5; border: 1px; margin: 10px 0;">
        	<br>
        	
       		<div class="row">
         		<h4>✈ ${comments.size() }개의 댓글</h2>
         	</div>
        
		  <form id="comment-regist" method="POST" action="">
		    <input type="hidden" name="action" value="regist">
		    <input type="hidden" name="articleNo" value="${article.getArticleNo()}">
		    
		    <div class="mb-3">
		      <div class="container">
		        <div class="row">
		        
		          <!-- 댓글 작성자 정보 -->
		          <div class="col-md-12"> <!-- 12개의 열을 차지하여 가운데 정렬 -->
					<c:set var="userInfo" value="${session.getAttribute.userInfo }" scope="request"></c:set>
					<c:choose>
						<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
						<c:when test="${empty userInfo }">
							<textarea class="comment-text form-control" id="comment" name="comment" rows="2" 
								style="width:100%; resize:none" placeholder="로그인 후 이용 가능합니다..." disabled="true"></textarea>
						</c:when>
						
						<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
						<c:otherwise>
							<!-- 
							<label for="comment" class="comment-label fw-bold" name="userId" >${userInfo.userId }</label>
							-->
							<textarea class="comment-text form-control" id="comment" name="comment" rows="2" 
								style="padding:5px; width:100%; resize:none" placeholder="댓글을 작성해주세요..." ></textarea>
						</c:otherwise>
					</c:choose>
		          </div>
		          
		          <!-- 댓글 등록 버튼 -->
		          <div class="d-flex justify-content-end ml-auto">
		              <c:if test="${!empty userInfo }">
			              <button type="button" id="comment-register" name="comment-register" class="btn btn-outline-primary mt-3 text-end">댓글 등록</button>
		              </c:if>
	              </div>
	            	
		        </div>
		      </div>
		    </div>
		    
		  </form>
		  
		</div>
		
		<br>
		
		<!-- 댓글 보기 부분 -->
		<div class="col-lg-8 col-md-10 col-sm-12">
        
          <div class="row">
          
          	<!-- 댓글 불러오기 -->
          	<c:forEach var="comment" items="${comments}">  
	          	<div class="row">
	              <div class="clearfix align-content-center">
              		<img class="avatar me-2 float-md-start bg-light p-2"
                     src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
              		<span class="fw-bold">${comment.userId}</span> <br />
                  	<span class="text-secondary fw-light"> ${comment.registerTime}</span>
	                </p>
	              </div>
	            </div>
	            
	            <c:choose>
	            	<c:when test="${userInfo.userId eq comment.userId}">
	            		<div class="col-md-10">
			            	<div class="text-secondary mb-3" style="font-size: 18px;"> ${comment.commentContent}</div>
			            </div>
			            
			            <div class="col-md-2">
						    <button type="button" id="comment-delete-${comment.commentNo}" name="comment-delete" 
						      article-no="${article.articleNo}" comment-no="${comment.commentNo}" 
						      class="btn btn-outline-danger mb-1 ms-1">삭제</button>
						      
						    <script>
						      document.querySelector("#comment-delete-${comment.commentNo}").addEventListener("click", function () {
						        if (confirm("정말로 삭제하시겠어요?😥")) {
						          location.href = "${root}/board?action=commentDelete&articleno=${article.getArticleNo()}&commentno=" + this.getAttribute("comment-no");
						        }
						      });
						    </script>
						</div>
	            	</c:when>
	            	
	            	<c:otherwise>
	            		<div class="col-md-12">
			            	<div class="text-secondary mb-3"> ${comment.commentContent}</div>
			            </div>
	            	</c:otherwise>
	            </c:choose>
	            
	            <hr style="height:1px; style:dotted; background-color:#FFF;">
          	</c:forEach>
            
		</div>
    	
      </div>
    </div>
    </div>
    
    <!-- Include Footer -->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	
    <!-- 로그인==글작성자 일 때 동작 -->
    <c:if test="${userInfo.userId eq article.userId}">
    	<script>
    		document.querySelector("#btn-mv-modify").addEventListener("click", function () {
            	location.href = "${root}/board?action=mvmodify&articleno=${article.getArticleNo()}";
          	});
    		
          	document.querySelector("#btn-delete").addEventListener("click", function () {
          		if(confirm("정말로 삭제하시겠어요?😥")){
	            	location.href = "${root}/board?action=delete&articleno=${article.getArticleNo()}";
          		}
       		});
    	</script>
    </c:if>
    
    <!-- 로그인==댓글작성자 일 때 동작 -->
    <c:if test="${userInfo.userId eq comment.userId}">
    	
    </c:if>
    
    <!-- 로그인과 상관 없이 동작 -->
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        //location.href = "${root}/board?action=list"; // origin
        // 추가
        location.href = "${root}/board?action=list&pgno=${pgno}&key=&word=#";
        console.log(location.href);
      });
      
      document.querySelector("#comment-register").addEventListener("click", function () {
        if (!document.querySelector("#comment").value) {
        	alert("앗! 내용을 입력하지 않았어요!😥");
          return;
        } else {
          let form = document.querySelector("#comment-regist");
          form.setAttribute("action", "${root}/board?action=commentAdd");
          form.submit();
        }
      });
    </script>
    
    <!-- 문자열 검색 -->
    <script>
    	var originText = "";
    	
    	window.onload = function() {
    		originText = document.querySelectorAll(".text-secondary")[1].innerHTML;
    	}
    	function kwsearch() {
    		// 본문 내용
    		T = originText
    		// 패턴 문자열
    		P = document.querySelector("#kw").value
    		// 본문 내용의 길이
    		inputLength = T.length
    		// 패턴 문자열의 길이
    		patternLength = P.length
    		// 부분 일치 테이블
    		pi = new Array(P.length)
    		// 본문에서 패턴 문자열 시작 인덱스 저장 배열
    		startList = new Array()
			// 패턴 문자열 등장 횟수
			num = 0;
	    		
    		// 부분(b) 일치(i) 테이블(t) 생성
    		bit()
    		search()
    		
    		console.log("원본 문자열: " + T)
    		console.log("찾고자 하는 문자열 패턴: " + P)
    		// 생성된 부분 일치 테이블 확인
    		console.log(pi.toString())
    		// 패턴 문자열 등장 횟수 확인
    		console.log(num)
			// 패턴 문자열 시작 인덱스 확인
			console.log(startList.toString())
    		
			// 첫 등장 문자열 패턴만 하이라이팅
			for(i = 0; i < num; i++) {
				//하이라이트 하게 변경
				 // style="background-color:yellow"
				
				T = T.replaceAt(startList[i], startList[i] + patternLength, P)
				// offset
				for(k=i+1; k<num; k++) {
					startList[k] += 45;
				}
				console.log(T)
			}
			document.querySelectorAll(".text-secondary")[1].innerHTML = T
		    		
    	}
		   
    	String.prototype.replaceAt = function(startIdx, endIdx, replacement) {
    		// 수정 위치 오류 처리
    	    if (startIdx >= this.length) {
    	        return this.valueOf();
    	    }
    	 	// 수정된 문자열 반환
    	    return this.substring(0, startIdx) + '<span style="background-color:yellow">' + replacement + '</span>' + this.substring(endIdx);
    	}
		    	
    	function bit() {
    		footer = 0
    		pi[0] = 0
            for (header = 1; header < patternLength; header++) {
                while (footer > 0 && P.charAt(header) != P.charAt(footer)) {
                    footer = pi[footer - 1]
                }
                if (P.charAt(header) == P.charAt(footer)) {
                    pi[header] = ++footer
                } else {
                    pi[header] = 0
                }
            }
    	}
		    	
    	function search() {
            j = 0;
            for (i = 0; i < inputLength; i++) {
                while (j > 0 && T.charAt(i) != P.charAt(j)) {
                    j = pi[j - 1];
                }
                if (T.charAt(i) == P.charAt(j)) {
                    if (j == patternLength - 1) {
                        startList[num++] = (i - j + 1) - 1;
                        j = pi[j];
                    } else {
                        j++;
                    }
                }
            }
    	}
		    	
    </script>

			    
</body>
</html>
