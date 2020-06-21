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
#hone{
	font-size: 30px;
	font-style: inherit;
}
.select-wrapper {
	width: 30%;
	float: left;
	margin-right: 10px;
}
.card {

	height: 350px;

	width: 300px;

	border-radius: 15px;

	display: inline-block;

	margin-top: 30px;

	margin-left: 30px;

	margin-bottom: 30px;

	position: relative;

	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

	overflow: hidden;
	

}



.card-header {

	-webkit-transition: 0.5s; /*사파리 & 크롬*/

    -moz-transition: 0.5s;  /*파이어폭스*/

    -ms-transition: 0.5s;	/*인터넷 익스플로러*/

    -o-transition: 0.5s;  /*오페라*/

    transition: 0.5s;

	width: 100%;

	height: 670px;

	border-radius: 15px 15px 0 0;

	/* background-image: url("images/korea.jpeg"); */

	background-size: 100% 250px;

	background-repeat: no-repeat;	

}



.card:hover .card-header  {

	opacity: 0.8;

	height: 100px;

}



.card-header-is_closed{

    background-color: #EF5A31 ;

    color: #FFF ;

    font-weight: bold ;

    text-align: center ;

    float: right;

    margin: 15px 15px 0 0;

    border-radius: 50%;

    font-weight: bold;

    padding: 10px 10px;

    line-height: 20px;

}







.card-body {
}



.card-body-header{

	line-height: 25px;

	margin: 5px 20px 0px 20px;

}

#title{
	font-size: 20px;
}

.card-body-description  {

    opacity: 0;

    color: #757B82;

    line-height: 25px;

    -webkit-transition: .2s ease-in-out; /*사파리&크롬*/

    -moz-transition: .2s ease-in-out; /*파이어폭스*/

    -ms-transition: .2s ease-in-out; /*익스플로러*/

    -o-transition: .2s ease-in-out; /*오페라*/

    transition : .2s ease-in-out;

    overflow: hidden;

	height: 180px;

	margin: 5px 20px;

}



.card:hover .card-body-description {

    opacity: 1;

    -webkit-transition: .5s ease-in-out;

    -moz-transition: .5s ease-in-out;

    -ms-transition: .5s ease-in-out;

    -o-transition: .5s ease-in-out;

    transition : .5s ease-in-out;


}



.card-body-hashtag {

	color: #2478FF;

	font-style: italic;

	margin:0;
}



.card-body-footer {

  	position: absolute; 

  	margin-top: 15px;

  	margin-bottom: 0px;

    bottom: 0; 

    width: 264px;

    font-size: 14px;

    color: #9FA5A8;

    padding: 0 15px;

}



.icon {

    display: inline-block;

    vertical-align: middle;

    margin-right: 2px;

}



.icon-view_count {

    width: 25px;

    height: 17px;

	background: url("images/eye.jpg") no-repeat;

}



.icon-comments_count {

	margin-left: 5px;

	width: 25px;

    height: 17px;

	background: url("images/comment.jpg") no-repeat;	

}



.reg_date {

	float: right;

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
			url: '<%=root%>/rank/' + $("#dong").val(),
			async: false,
			dataType : 'json',   
			success: function(data){
				$(".here").empty();
				$.each(data, function(index, vo) {
					let str = 
						'<div class="card" style="float:left;">'
						+'<div class="card-header" style="background-image: url(img/'+vo.img+');">'
						<%-- +'<img class="card-img-bottom" src="<%=root%>/img/'+vo.img+'" alt="Card image" width="200px"; height="100px;">' --%>
						+'<div class="card-header-is_closed">'
						+'<div class="card-header-text">Rank</div>'
						+'<div class="card-header-number">'+vo.rank+'</div>'
						+'</div>'
						+'</div>'
						+'<div class="card-body">'
						+'<div class="card-body-header">'
						+'<div id="title">'+vo.aptName+'</div>'
						+'<p class="card-body-hashtag">'+vo.dong+'</p>'
						+'<p class="card-body-nickname">거래금액: '+vo.pay+'</p>'
						+'</div>'
						+'<p class="card-body-description">거래 년/월: '+vo.dealDay +'<br>층: '+vo.floor +'<br>면적: '+vo.area+'</p>'
						+'<div class="card-body-footer">'
						+'<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">'
						+'<i class="reg_date">'+vo.dealDay+'</i>'
						+'</div>'
						+'</div></div>';
					$(".here").append(str);
					
					/* $("#searchResult").append(vo.aptName+" "+vo.dealAmount+" "+vo.area+" "+vo.type+" "+vo.buildYear+"<br>"); */
				});//each
				//geocode(data);
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
				return;
			}
		});
		
	});//change
	
});	

</script>
	<section class="wrapper style4" style="height: 1500px;">

		<div class="inner" style="width: 45%;">
			<h1 align="center" id="hone">매물 순위</h1>
			<!-- 지역으로 검색 -->
			<div class="box" id="searchRegBox" style="margin-bottom: 20px;">
				<div class="select-wrapper">
					<select id="sido" name="sido">
						<option value="0">-시/도-</option>
					</select>
				</div>
				<div class="select-wrapper">
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
		</div>
			<!-- 크롤링 데이터 -->
			<br>
			<div class="here" style="float: left; margin-left: 60px; overflow:y-scroll">
			<!-- <!-- 카드 헤더 -->
			</div>
		<!-- here end -->
	</section>
	<!-- section end -->
	<jsp:include page="./common/footer.jsp" />
</body>
</html>