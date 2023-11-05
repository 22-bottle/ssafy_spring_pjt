-<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<%@ include file="./include/head.jsp" %>
  </head>

  <body>
    <!--상단 header start-->
    <%@ include file="./include/nav.jsp" %>
    <!--상단 header end-->

    <!--중앙 content start-->
    <div class="container">
      <!-- Carousel -->
      <div id="demo" class="carousel slide" data-bs-ride="carousel">
      
        <!-- Indicators/dots -->
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
          <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
          <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
        </div>

        <!-- The slideshow/carousel -->
        <div class="carousel-inner mb-0">
          <div class="carousel-item active">
            <div class="carousel-caption d-none d-md-block img-reponsive">
              <h4><strong>꿀잼도시 대전으로 오세요!${user}</strong></h4>
              <p>📍대전 유성구 도룡동</p>
            </div>
            <img
              src="${root}/img/main-1.jpg"
              width="640"
              height="480"
              style="object-fit: cover; object-position: center"
              alt="main-1"
              class="d-block w-100"
            />
          </div>
          <div class="carousel-item">
            <div class="carousel-caption d-none d-md-block">
              <h3><strong>도심 속 휴식을 취할 수 있는, 한밭수목원</strong></h3>
              <p>📍대전 서구 둔산대로 169</p>
            </div>
            <img
              src="${root}/img/main-2.jpg"
              width="640"
              height="480"
              style="object-fit: cover; object-position: 80% 20%"
              alt="main-2"
              class="d-block w-100"
            />
          </div>
          <div class="carousel-item">
            <div class="carousel-caption d-none d-md-block">
              <h4><strong>자연경관이 아름다운 대전!</strong></h4>
              <p>대전에서 추억을 남겨보세요</p>
            </div>
            <img
              src="${root}/img/main-3.jpg"
              width="640"
              height="480"
              style="object-fit: cover; object-position: 40% 60%"
              alt="main-3"
              class="d-block w-100"
            />
          </div>
        </div>

        <!-- Left and right controls/icons -->
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#demo"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#demo"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>
    </div>

    <!--중앙 카드 사진 content start-->
    <div class="container">
      <div class="row card-group m-5">
        <div class="card">
          <img src="${root}/img/card-1.jpg" class="card-img-top" alt="대전0시축제" />
          <div class="card-body">
            <h5 class="card-title">대전0시축제 "내가 찍은 여름"</h5>
            <p class="card-text">
              -접수기간 : 2023.8.11(금) ~ 8.24(목) <br />
              -수상작 발표 : 2023.9.1(금) / 대전광역시 SNS로 공개 <br />
              -공모주제 : 8.11(금)~8.17(목)까지 <br />
              &nbsp;'대전0시축제'에서 찍은 멋지고 생생한 현장을 담아낸 사진을 소개해주세요! <br />
              -참가대상 : '대전0시축제'를 방문한 국민 누구나 <br />
              -출품규격 : 3,000 x 2,000pixel 이상, 용량 100MB 이하
            </p>
            <p class="card-text">
              <a class="text-body-secondary">자세히 보기</a>
            </p>
          </div>
        </div>
        <div class="card">
          <img src="${root}/img/card-2.png" class="card-img-top" alt="충청권 스탬프 투어" />
          <div class="card-body">
            <h5 class="card-title">충청권 스탬프 투어</h5>
            <p class="card-text">
              -기간: 2023.06.08 ~ 2023.12.31 (경품 소진 시 조기 종료) <br />
              -장소: 대전광역시, 세종특별자치시, 충청북도, 충청남도 <br />
              -주제: 충청권(대전·세종·충북·충남) 스탬프 투어 떠나자! <br />
              -주최: 대전관광협회 <br />
              -주관: 대전광역시
            </p>
            <p class="card-text">
              <a class="text-body-secondary">자세히 보기</a>
            </p>
          </div>
        </div>
        <div class="card">
          <img src="${root}/img/card-3.png" class="card-img-top" alt="2023 누들대전 페스티벌" />
          <div class="card-body">
            <h5 class="card-title">2023 누들대전 페스티벌</h5>
            <p class="card-text">
              	세부행사 <br />
              -나만의 누들 레시피 숏폼 콘테스트 <br />
              -누들감성 라이브공연 <br />
              -대전 누들 맛집 푸드존 <br />
              -누들 감성 무비 나잇 <br />
              -누들(면) 요리 경연대회 <br />
              -누들 빨리먹기 대회 / 누들체험 6종
            </p>
            <p class="card-text">
              <a class="text-body-secondary">자세히 보기</a>
            </p>
          </div>
        </div>
      </div>
      <!--중앙 카드 사진 content end-->
    </div>
    <!--중앙 content end-->

    <!--하단 footer start-->
    <!-- Include Footer -->
	<div
		style="left: 0; bottom: 0; width: 100%; background-color: #f0f0f0; text-align: center;">
		<jsp:include page="./include/footer.jsp"></jsp:include>
	</div>
    <!--하단 footer end-->

  </body>

  <script
    src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"
  ></script>
  <script
    src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"
  ></script>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"
  ></script>
</html>
