<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>주택정보</title>
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
	src="https://maps.googleapis.com/maps/api/js?key= /*API키*/ &callback=getMapData"></script>
<style>
.left-box {
	float: left;
}

.right-box {
	float: right;
}

.center-box {
	margin: 0 auto;
	text-align: center;
}
/* card size */
.gm-style-iw, .gm-style-iw-c, .gm-style-iw-d {
	padding: 8px !important;
	max-height: 400px !important;
	max-width: 500px !important;
	overflow: hidden !important;
}

table {
	font-size: 80%;
}

.select-wrapper {
	width: 30%;
	float: left;
}

td a {
	color:#8A4680;
}

td a:hover {
	color:#8A4680;
	font-weight: bold;
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
	<jsp:include page="./common/header.jsp" />
	<!-- here start -->
	<script>
let colorArr = ['table-secondary','table-light','table-active'];
$(document).ready(function(){
	$.ajax({
		type: 'get',
		url: '<%=root%>/map/city',
		async: false,
		dataType : 'json',   
		success: function(data){
			$.each(data, function(index, vo) {
				$("#sido").append("<option value='"+vo.sido_name+"'>"+vo.sido_name+"</option>");
			});//each
		}, error:function(request, status, error){
			alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
			return;
		}
	});
	
});//ready

$(document).ready(function() {
	$("#searchRegBtn").removeClass('small');
	$("#searchRegBtn").addClass('special small');
	
	$("#searchRegBox").show();
	$("#searchNameBox").hide();
	
	$("#searchRegBtn").click(function() {
		//searchReg 버튼
		$(this).removeClass('small');
		$(this).addClass('special small');
		//searchName 버튼
		$("#searchNameBtn").removeClass('special small');
		$("#searchNameBtn").addClass('small');
		//searchRegBox 보이게 설정
		$("#searchRegBox").show();
		$("#searchNameBox").hide();
	});
	
	$("#searchNameBtn").click(function() {
		//searchName 버튼
		$(this).removeClass('small');
		$(this).addClass('special small');
		//searchReg 버튼
		$("#searchRegBtn").removeClass('special small');
		$("#searchRegBtn").addClass('small');
		//searchNameBox 보이게 설정
		$("#searchNameBox").show();
		$("#searchRegBox").hide();
	});
}); 


$(document).ready(function(){
	$("#sido").change(function() {
		$.ajax({
			type: 'get',
			url: '<%=root%>/map/gu/' + $("#sido").val(),
			async: false,
			dataType : 'json',   
			success: function(data){
				$("#gugun").empty();
				$("#gugun").append('<option value="0">선택</option>');
				$.each(data, function(index, vo) {
					$("#gugun").append("<option value='"+vo.gugun_name+"'>"+vo.gugun_name+"</option>");
				});//each
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
				return;
			}
		});
	});//change
	$("#gugun").change(function() {
		$.ajax({
			type: 'get',
			url: '<%=root%>/map/dong/' + $("#gugun").val(),
			async: false,
			dataType : 'json',   
			success: function(data){
				$("#dong").empty();
				$("#dong").append('<option value="0">선택</option>');
				$.each(data, function(index, vo) {
					$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
				});//each
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
				return;
			}
		});
		
	});//change
	$("#dong").change(function() {
		$.ajax({
			type: 'get',
			url: '<%=root%>/map/aptPage/' + $("#dong").val()+'/'+$(this).attr("data-pageno"),
			async: false,
			dataType : 'json',   
			success: function(data){
				$("tbody").empty();
				$.each(data, function(index, vo) {
					let str = "<tr>"
					+ "<td><a href='<%= root%>/house/Cdetail/"+vo.aptName+"'>"+ vo.aptName +"</a></td>"
 					/* + "<td>"+ vo.aptName +"</td>" */
					+ "<td>" + vo.dong + "</td>"
					+ "<td>" + vo.dealAmount + "</td>"
					+ "<td>" + vo.area + "</td>"  
					+ "<td>" + vo.dealYear
					+ "</td></tr>";
					$("tbody").append(str);
					/* $("#searchResult").append(vo.aptName+" "+vo.dealAmount+" "+vo.area+" "+vo.type+" "+vo.buildYear+"<br>"); */
				});//each
				
				getMapData();
				//geocode(data);
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
				return;
			}
		});
		
		/* paging */
		$.ajax({
			type: 'get',
			url: '<%=root%>/house/list/' + $("#dong").val()+'/'+$(this).attr("data-pageno"),
			async: false,
			dataType : 'json',   
			success: function(data){
				$("#here").empty();
				let str =  "<ul class='pagination justify-content-center'>"
						+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='1'> << </a></li>"
						+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='"
						+(data.startPage-10)+"'> < </a></li>"
						+"<li class='page-item'>"
						+"</li>";
						for(var i=data.startPage;i<=data.endPage;i++) {
							if(i==data.curPage){
								str+="<li class='page-item active' aria-current='page'><span class='page-link' href='#' data-pageno='"+i+"'>"+i+"<span class='sr-only'>(current)</span></span></li>";
							}else{
								str+="<li class='page-item'><a class='page-link' href='#' data-pageno='"+i+"'>"+i+"</a></li>";
							}
						}
						str+="<li class='page-item'><a class='page-link' href='#' data-pageno='"+data.endPage+1 +"'> > </a></li>"
						+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='"+data.pageCnt+"'> >> </a></li>";
						$("#here").append(str);
			//each
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
				return;
			}
		});
	});//change
	
	/* $(".page-link").click(function() {
		alert("??");
		var pageno = $(this).attr("data-pageno");
		console.log(pageno);
		$("#pageIndex").val(pageno);
		console.log($("#pageIndex").val());
		$("#formHouseDealSearch").submit(); 
	});*/
});//ready

$(document).on("click",".page-link",function(){
	$.ajax({
		type: 'get',
		url: '<%=root%>/map/aptPage/' + $("#dong").val()+'/'+$(this).attr("data-pageno"),
		async: false,
		dataType : 'json',   
		success: function(data){
			$("tbody").empty();
			$.each(data, function(index, vo) {
				let str = "<tr>"
				+ "<td><a href='<%= root%>/house/Cdetail/"+vo.aptName+"'>"+ vo.aptName +"</a></td>"
					/* + "<td>"+ vo.aptName +"</td>" */
				+ "<td>" + vo.dong + "</td>"
					+ "<td>" + vo.dealAmount + "</td>"
					+ "<td>" + vo.area + "</td>"
					+ "<td>" + vo.dealYear
				+ "</td></tr>";
				$("tbody").append(str);
			});//each
			getMapData();
			//geocode(data);
		}, error:function(request, status, error){
			alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
			return;
		}
	});
	$.ajax({
		type: 'get',
		url: '<%=root%>/house/list/' + $("#dong").val()+'/'+$(this).attr("data-pageno"),
		async: false,
		dataType : 'json',   
		success: function(data){
			$("#here").empty();
			let str =  "<ul class='pagination justify-content-center'>"
					+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='1'> << </a></li>"
					+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='"
					+(data.startPage-10)+"'> < </a></li>"
					+"<li class='page-item'>"
					+"</li>";
					for(var i=data.startPage;i<=data.endPage;i++) {
						if(i==data.curPage){
							str+="<li class='page-item active' aria-current='page'><span class='page-link' href='#' data-pageno='"+i+"'>"+i+"<span class='sr-only'>(current)</span></span></li>";
						}else{
							str+="<li class='page-item'><a class='page-link' href='#' data-pageno='"+i+"'>"+i+"</a></li>";
						}
					}
					str+="<li class='page-item'><a class='page-link' href='#' data-pageno='"+data.endPage+1 +"'> > </a></li>"
					+"<li class='page-item '><a class='page-link' href='#' tabindex='-1' aria-disabled='true' data-pageno='"+data.pageCnt+"'> >> </a></li>";
					$("#here").append(str);
		//each
		}, error:function(request, status, error){
			alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
			return;
		}
	});
});	


function geocode(jsonData) {
	let idx = 0;
	$.each(jsonData, function(index, vo) {
		let tmpLat;
		let tmpLng;
		$.get("https://maps.googleapis.com/maps/api/geocode/json"
				,{	key:'/*API키*/'
					, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
				}
				, function(data, status) {
					//alert(data.results[0].geometry.location.lat);
					tmpLat = data.results[0].geometry.location.lat;
					tmpLng = data.results[0].geometry.location.lng;
					$("#lat_"+index).text(tmpLat);
					$("#lng_"+index).text(tmpLng);
					//addMarker(tmpLat, tmpLng, vo.aptName);
					
				}
				, "json"
		);//get
	});//each
}
</script>
	<section class="wrapper style4" style="height: 1000px;">

		<div class="inner" style="width: 45%; float: left; margin-left: 50px; ">
			<div class="box" align="center">
				<div class="btn-group" role="group" aria-label="Basic example">
					<button type="button" id="searchRegBtn"
						class="button special small">지역으로 검색</button>
					<button type="button" id="searchNameBtn" class="button small">아파트/동명
						검색</button>
				</div>
			</div>
			<!-- 지역으로 검색 -->
			<div class="box" id="searchRegBox" style="margin-bottom: 20px;">
				<div class="select-wrapper" style="margin-left:15px; margin-right: 20px;">
					<select id="sido" name="sido">
						<option value="0">-시/도-</option>
					</select>
				</div>
				<div class="select-wrapper" style="margin-right: 20px;">
					<select id="gugun" name="gugun">
						<option value="0">-구/군-</option>
					</select>
				</div>
				<div class="select-wrapper">
					<select id="dong" name="dong">
						<option value="0">-읍/면/동-</option>
					</select>
				</div>
			</div>
			<!-- 동명/아파트명으로 검색  -->
			<div class="box" id="searchNameBox" style="margin: 0; display: none;">
				<form id="formHouseDealSearch" action="/house/list2" method="get">
					<input type="hidden" id="pageIndex" name="page" value=1>
					<div>
						<div class="select-wrapper" style="width: 20%; margin-right:20px;">
							<select name="searchField" id="searchField">
								<option value="LIST"
									<c:if test="${searchField != null || searchField == 'LIST'}"> selected </c:if>>전체</option>
								<option value="DONG"
									<c:if test="${searchField == 'DONG'}"> selected </c:if>>동명</option>
								<option value="NAME"
									<c:if test="${searchField == 'NAME'}"> selected </c:if>>아파트명</option>
							</select>
						</div>
						<input type="text"
							style="width: 60%; display: inline-block; margin-right: 20px; margin-bottom: 0;"
							name="aptName" id="aptName"
							value="<c:if test="${aptName != null}">${aptName}</c:if>">
						<button class="button alt small" type="submit" id="button-addon2">검색</button>
					</div>
				</form>
			</div>

			<!-- 검색결과 -->
			<div class='left-box' style="width: 100%; margin-top: 50px;">
				<div class="table-wrapper">
				<table style="table-layout:fixed;">
					<thead>
						<tr>
							<!-- 상세 내역 확인 시 매물 사진 보이도록 할것.         <th>매물 사진</th> -->
							<th width="45%">아파트 이름</th>
							<th width="15%">법정동</th>
							<th width="15%">거래 금액</th>
							<th width="12%">면적</th>
							<th width="13%">거래 일시</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
				</div>
				
				<!-- pagination -->
				<div id="here"></div>
				
			</div>
		</div>


		<div class="inner" style="width: 50%; float: right;">
			<header class="align-center">
				<h2>주택 위치 정보</h2>
			</header>
		<!-- map start -->
		<div class="box" align="center" style="margin-right:50px;">
		<div class='right-box' id="map"
			style="width: 95%; height: 650px; margin-top:35px;"></div>
		</div>
		</div>
		<!-- here end -->


			<script
				src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>

			<script>
var INFO_WINDOW_HTML = 
	   '<div id="container" style="height:500px;width:500px">' +
	   '<div class="card" style="width:500px">' +
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
	
	var initMap = function initMap() {

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
		var dong = $("#dong").val();
		if(dong == 0){
			dong = "사직동";
		}
		
		$.ajax(
		{
	        type : 'get',
	        url : '<%=root%>/map/info/' + $("#dong").val(),
						dataType : 'json',
						success : function(data, status, xhr) {
							locations = data;
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
	</section>
	<!-- section end -->
	<jsp:include page="./common/footer.jsp" />
</body>
</html>