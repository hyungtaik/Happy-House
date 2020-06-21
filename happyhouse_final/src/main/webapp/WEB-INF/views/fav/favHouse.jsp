<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.happyhouse.dto.UserDto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="area" value="${a}"></c:set>
<%
	String root = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title>관심지역 주택정보</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script
	src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=/*API키*/&callback=getMapData"></script>
<style>
.fav {
	cursor: pointer;
}

.area:hover {
	background-color: lightgray;
}

.area.gray {
	background-color: lightgray;
}

/* card size */
.gm-style-iw, .gm-style-iw-c, .gm-style-iw-d {
	padding: 8px !important;
	max-height: 400px !important;
	max-width: 500px !important;
	overflow: hidden !important;
}


a[name^=nav]:hover{
	/* 보라보라 */
	background-color:#8A4680;	
}

a.page-link{
	color:gray;
}

a.page-link:hover{
	color:#8A4680;
	font-weight: bold;
}

.page-item.active .page-link {
    z-index: 3;
    color: #fff;
    background-color: #8A4680 !important;
    border: 1px solid #dee2e6 !important;
}
</style>

</head>
<body>
<!-- paging 처리 js -->
<script src="<%=root%>/template/assets/js/util_paging.js"></script>	
	
<script>

/* paging 관련 변수 */
var LIST_ROW_COUNT = 10;	//limit
var OFFSET = 0;
var SEARCH_WORD = "";

let dong = "";
let colorArr = ['table-secondary','table-light','table-active'];
	$(document).on("click", "div[class='area']", function() {
		var $target = $(this).find("ul[class='alt']").children(); //gu selectbox
		//var sido = $target.eq(0).text();
		//var gugun = $target.eq(1).text();
		dong = $target.eq(2).text();
		
		//alert(dong);

		//관심지역 클릭 이벤트
		$("div[class='area gray']").removeClass("gray");
		$(this).addClass('gray');
		
		houseList();
	});
	
	
	function houseList(){
			$.ajax({
				type: 'get',
				url: '<%=root%>/fav/apt/' + dong,
				async: false,
				dataType : 'json', 
				data : {
					limit: LIST_ROW_COUNT,
					offset: OFFSET
					//dong: dong
				},
				success: function(data){
					makeListHtml(data);
					getMapData();
					//geocode(data);
				}, error:function(request, status, error){
					alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
					return;
				}
			});
	}
	
	function makeListHtml(list){

		<%-- $("tbody").empty();
		$.each(data, function(index, vo) {
			let str = "<tr class="+colorArr[index%3]+">"
			+ "<td><a href='<%= root%>/house/Cdetail/"+vo.aptName+"'>"+ vo.aptName +"</a></td>"
			+ "<td>" + vo.dealAmount + "</td>"
			+ "<td>" + vo.area + "</td>"
			+ "<td>" + vo.type + "</td>"
			+ "<td>" + vo.buildYear
			+ "</td></tr>";
			$("tbody").append(str);
		});
		 --%>
		
		
		
		$("tbody").html("");

		for( var i=0; i<list.length; i++){
		console.log(list[i])
			var aptName = list[i].aptName;
			var dealAmount = list[i].dealAmount;
			var area = list[i].area;
			var type = list[i].type;
			var buildYear = list[i].buildYear;	// javascript of parsed from LocalDateTime
			
			var listHtml =
				<%-- '<tr style="cursor:pointer"><a href='<%= root%>/house/Cdetail/"+vo.aptName+"'><td>' + aptName + '</td><td>' + dealAmount + '</td><td>' + area + '</td><td>' + type + '</td><td>' + buildYear + '</td></a></tr>'; --%>
				<%-- '<a href="<%= root%>/house/Cdetail/'+aptName+'"><tr style="cursor:pointer"><td>' + aptName + '</td><td>' + dealAmount + '</td><td>' + area + '</td><td>' + type + '</td><td>' + buildYear + '</td></tr></a>'; --%>
				'<tr style="cursor:pointer" data-aptName=' + aptName +'><td>' + aptName + '</td><td>' + dealAmount + '</td><td>' + area + '</td><td>' + type + '</td><td>' + buildYear + '</td></tr>';
				
			$("tbody").append(listHtml);
		}
		
		makeListHtmlEventHandler();
		
		houseListTotalCnt();
	}
	
	function makeListHtmlEventHandler(){
		$("tbody tr").click(function(){
			var aptName = $(this).attr("data-aptName");
			location.href="${root}/house/Cdetail/"+aptName;
		});
	} 
	
	/* paging 관련 변수 */
	var PAGE_LINK_COUNT = 10;	// pagination link 갯수
	var TOTAL_LIST_ITEM_COUNT = 0;
	var CURRENT_PAGE_INDEX = 1;
	
	function houseListTotalCnt(){
		
		$.ajax(
		{
	        type : 'get',
	        url : '<%=root%>/fav/houseListTotalCnt',
	        dataType : 'json',
	        data : 
			{
				dong: dong
			},
	        success : function(data, status, xhr) {
	        	TOTAL_LIST_ITEM_COUNT = data;
	        	addPagination();
	        }, 
	        error: function(jqXHR, textStatus, errorThrown) 
	        { 
	        	alertify.notify(
	       			'Opps!! 글 전체 갯수 조회 과정에 문제가 생겼습니다.', 
	       			'error', //'error','warning','message'
	       			3, //-1
	       			function(){
	       				console.log(jqXHR.responseText); 
	       			}
	       		);
	        }
	    });
	}
	
	function addPagination(){

		makePaginationHtml(LIST_ROW_COUNT, PAGE_LINK_COUNT, CURRENT_PAGE_INDEX, TOTAL_LIST_ITEM_COUNT, "paginationWrapper" );
	}
	
	function movePage(pageIndex){
		OFFSET = (pageIndex - 1) * LIST_ROW_COUNT;
		CURRENT_PAGE_INDEX = pageIndex;
		houseList();
	}
</script>
	<jsp:include page="../common/header.jsp" />
	<section class="wrapper style4" style="height: 1000px;">
		<div class="inner" style="width: 15%; float: left; padding-top:30px;">
			<!-- 사이드 바 메뉴-->
			<div class="box">
			  <div class="col-10">
				<h4>Menu</h4>
			    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
			      <a class="button small" id="nav" name="nav" href="/fav/list">관심지역 설정</a>
			      <a class="button small" id="nav" name="nav" href="/fav/house">부동산 정보</a>
			      <a class="button small" id="nav" name="nav" href="/fav/store">상권 정보</a>
			    </div>
			  </div>
			</div>
			<div class="col-10">
				<header align="center">
					<hr><p style="background-color:#8A4680;">지역을 선택하세요</p>
				</header>
				<div>
					<dl id="areas">
						<c:forEach items="${area}" var="item" varStatus="status">
							<div id="area" class="area">
								<dt>지역 ${status.count}</dt>
								<dd>
									<ul class="alt">
										<li>${item.city}</li>
										<li>${item.gu}</li>
										<li>${item.dong}</li>
									</ul>
								</dd>
							</div>
						</c:forEach>
					</dl>
				</div>
			</div>
		</div>

		<div class="inner" style="width: 85%; float: right;">
			<div class="box">
				<!-- <div class="content"> -->
				<header class="align-center">
					<h2>관심지역 부동산정보</h2>
				</header>
				<!-- 거래정보 -->
				<div class='left-box' style="width: 55%; float:left;">
					<table class="table table-hover">
						<thead>
							<tr>
								<!-- 상세 내역 확인 시 매물 사진 보이도록 할것.         <th>매물 사진</th> -->
								<th>아파트 이름</th>
								<th>법정동</th>
								<th>거래 금액</th>
								<th>면적</th>
								<th>거래 일시</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
					<div id="paginationWrapper"></div>
				</div>
				<!-- map start -->
				<div class='right-box' id="map"
					style="width: 40%; height: 500px; margin: auto;"></div>


				<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>

				<script>
var INFO_WINDOW_HTML = 
	   '<div id="container" style="height:350px;width:350px">' +
	   '<div class="card" style="width:350px">' +
	   '<div class="card-body">' +
	   '<h4 class="card-title">^APT_NAME</h4>' +
	   '<p class="card-text">^DONG</p>' +
	   '<a href="<%= root%>/house/Cdetail/^APT_NAME" class="button special small">거래 정보 보기</a>' +
	   '</div>' +
	   '<img class="card-img-bottom" src="<%=root%>/img/^IMAGE" alt="Card image" width="200px"; height="200px;">' +
	   '</div>' +
	   '</div>';


	var multi = {lat: 37.5665734, lng: 126.978179};
	var map;
	
	//var initMap = function initMap() {
	function initMap() {

		var map = new google.maps.Map(
			document.getElementById('map'), 
			{
        		zoom: 15,
        		center: {
        			lat: parseFloat(locations[0].lat), lng: parseFloat(locations[0].lng)
        			//lat: parseFloat(multi.lat), lng: parseFloat(multi.lng)
        		}
      		}
		);

		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		var markers = locations.map(
			function(location, i) {
        		return new google.maps.Marker(
     				{
       					position: { lat: parseFloat(location.lat), lng: parseFloat(location.lng) },	// should be float, not string
       					label: labels[i % labels.length],
       					aptName: location.aptName,
       					dong: location.dong,
       					img:location.img
     				}
        		);
     		 }
		);
		
		var markerCluster = new MarkerClusterer(
			map,
			markers,
			{
				imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
			}
		);
		
		
		markers.map(
			function(marker, i){
				marker.addListener('click', function() {
					var infowindow = new google.maps.InfoWindow({
					    content: INFO_WINDOW_HTML.replace('^APT_NAME', marker.aptName).replace('^DONG', marker.dong).replace('^IMAGE', marker.img).replace('^APT_NAME', marker.aptName)
					});
					infowindow.open(map, marker);
				});
			}
		);
    }
    
	var locations ={lat: 37.5665734, lng: 126.978179};
	
	function getMapData(){
		/* if(dong == ""){
			dong = "사직동";
		} */
		
		$.ajax(
		{
	        type : 'get',
	        url : '<%=root%>/map/info/' + dong,
			dataType : 'json',
			success : function(data, status, xhr) {
				locations = data;
				//alert(locations[0].lat);
				initMap();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alertify.notify(
						'Opps!! 글 Map data를 받는 과정에 문제가 생겼습니다.',
						'error', //'error','warning','message'
						3, //-1
						function() {
							console.log(jqXHR.responseText);
						});
					}
				});
			}
				</script>
				<!-- map end -->
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>
