// trip.js

function loadMap() {
  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();

  regText =
    $("#sido option:checked").text() +
    " " +
    $("#gugun option:checked").text();
  
  // console.log(regcode);
  // console.log(regText);
  
  sendRequest("detail", regcode, area);
  
  // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places();

  var keyword = document.getElementById("info").value;
  // 키워드로 장소를 검색합니다
  ps.keywordSearch(regText + " " + keyword, placesSearchCB);

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < data.length; i++) {
        displayMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    }
  }

  // 지도에 마커를 표시하는 함수입니다
  function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    let marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(place.y, place.x),
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "click", function () {
    	  // 클릭시 상세정보 페이지로 이동합니다.
    	  var link = place.place_url;
    	  window.open(link, "_blank");
    });
    
    // 마커 호버
    var iwContent = `
    	<div class="container" style="padding-top: 10px; padding-bottom: 20px; width: 300px; height: 200px">
		  <div>관광지명: ${place.place_name}</div>
		`;
		if (place.road_address_name == "") iwContent += `</div>`;
		else {
			iwContent += `도로명 주소: ${place.road_address_name}
			</div>
			`;
		}
    // 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
      content : iwContent
    });
    
    
    // 마커에 마우스오버 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseover', function() {
      // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);
    });

    // 마커에 마우스아웃 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseout', function() {
        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
        infowindow.close();
    });
  }
}
