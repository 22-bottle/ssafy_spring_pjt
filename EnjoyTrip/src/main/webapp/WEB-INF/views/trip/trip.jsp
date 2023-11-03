<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/head.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/assets/js/trip.js"></script>
</head>
<body>
	<!-- Include Navigation Bar -->
	<jsp:include page="/include/nav.jsp"></jsp:include>

	<div class="container">
		<!--지역별 여행지 start-->
		<div style="margin-top: 100px">
			<h2 class="d-flex justify-content-center mt-5 custom-font">지역 선택</h2>
			<div>
				<div class="dropdown d-flex justify-content-center mt-3 mb-3">
					<select name="sido" id="sido" class="dropdown-toggle ms-3 me-3 form-select"></select>
					<select name="gugun" id="gugun" class="dropdown-toggle ms-3 me-3 form-select">
						<option value="" class="dropdown-menu">구군선택</option>
					</select> <select name="type" id="type" class="dropdown-toggle ms-3 me-3 form-select">
						<option value="0" selected>관광지 유형</option>
						<option value="12">관광지</option>
						<option value="14">문화시설</option>
						<option value="15">축제공연행사</option>
						<option value="25">여행코스</option>
						<option value="28">레포츠</option>
						<option value="32">숙박</option>
						<option value="38">쇼핑</option>
						<option value="39">음식점</option>
					</select> <input type="text" id="info" placeholder="키워드 입력"
						style="display: none;" />
					<button onclick="loadMap()"class="col-1 btn btn-outline-success">검색</button>
				</div>
			</div>
		</div>
		<!--지역별 여행지 end-->

		<!-- kakao map start-->
		<div class="container">
			<!-- kakao map start -->
			<div id="map" class="mt-3" style="width: 100%; height: 700px"></div>
			<!-- kakao map end -->
		</div>
		<!-- kakao map end-->

		<!-- 여행지 상세정보 start-->
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="text-align: left; padding-right: 30px;">대표이미지</th>
						<th style="text-align: left; padding-right: 30px;">관광지명</th>
						<th style="text-align: left; padding-right: 30px;">주소</th>
					</tr>
				</thead>
				<tbody id="trip-list"></tbody>
			</table>
		</div>
	</div>
	<!-- 여행지 상세정보 end-->

	<!-- menu script -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
        let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
        var regcode = "";
        var regText = "";
        var area = "";

        // 시도가 바뀌면 구군정보 얻기.
        sendRequest("sido", "", area);

        document.querySelector("#sido").addEventListener("change", function () {
          if (this[this.selectedIndex].value) {
            regcode = " " +this[this.selectedIndex].value.substr(0, 2);
            area = this.options[this.selectedIndex].text
            sendRequest("gugun", regcode, area);
          } else {
            initOption("gugun");
            initOption("type");
          }
        });

        document.querySelector("#type").addEventListener("change", function () {
            if (this[this.selectedIndex].value) {
              a = area + " " + this.options[this.selectedIndex].text;
              r = regcode + " " + this[this.selectedIndex].value.substr(0, 2);
             regcode = r;
            } else {
              initOption("gugun");
              initOption("type");
            }
        });

        // DB연동          
     	function sendRequest(selid, regcode, area) {
    	  //console.log("selid: " + selid + " area: "+area);
    	  //console.log(regcode);
		  const url = "/EnjoyTrip/trip";
		  let params = "regcode_pattern=" + selid + "&regcode=" + regcode + "&area="+area;
		
		  // AJAX 요청
		  var request = new XMLHttpRequest();
		  request.open("GET", url + "?" + params, true);
		  request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
		    	var data = JSON.parse(request.responseText);
		      if ( "detail" != selid) {
		      	addOption(selid, data);
		      }
		      else {
		    	  let infoList = ``;
		    	  data.regcodes.forEach(function(item) {
		    		  title = item.title;
		    		  addr = item.addr;
		    		  img = item.img;

		    		  //console.log("Title:", title);
		    		  //console.log("Address:", addr);
		    		  //console.log("Image URL:", img);
		    		  infoList += `
		    			  <tr>
		    			    <td><img src="\${img}" width="100px"></td>
		    			    <td>\${title}</td>
		    			    <td>\${addr}</td>
		    			  </tr>
		    			`;
		    		});
		    	  document.getElementById("trip-list").innerHTML = infoList;
		      }
		    }
		  };
		  request.send();
		}

        function addOption(selid, data) {
          let opt = ``;
          initOption(selid);

          switch (selid) {
            case "sido":
              opt += `<option value="">시도선택</option>`;
              data.regcodes.forEach(function (regcode) {
                opt += `
                  <option value="\${regcode.code}">\${regcode.name}</option>
                  `;
              });
              break;
            case "gugun":
              opt += `<option value="">구군선택</option>`;
              for (let i = 0; i < data.regcodes.length; i++) {
                if (i != data.regcodes.length - 1) {
                  if (
                    data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                    data.regcodes[i].name.split(" ").length != data.regcodes[i + 1].name.split(" ").length
                  ) {
                    data.regcodes.splice(i, 1);
                    i--;
                  }
                }
              }
              let name = "";
              data.regcodes.forEach(function (regcode) {
                if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
                else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
                opt += `
                  <option value="\${regcode.code}">\${name}</option>
                  `;
              });
              break;
          }
          document.querySelector("#" + selid).innerHTML = opt;
        }

        function initOption(selid) {
           let options = document.querySelector("#"+selid);
          options.length = 0;
        }

      </script>
	<!-- /menu script -->

	<!--kakao map script-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=066c4bf5fb8745fcc2b066ec145bb938&libraries=services,clusterer,drawing"></script>
	<script>
        // 카카오지도
        var mapContainer = document.getElementById("map"), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
            level: 5, // 지도의 확대 레벨
          };
        // 지도를 표시할 div와 지도 옵션으로  지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);
      </script>
	<!--/kakao map script-->

	<!-- Include Footer -->
	<div
		style="left: 0; bottom: 0; width: 100%; background-color: #f0f0f0; text-align: center;">
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>
