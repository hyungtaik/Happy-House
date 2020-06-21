<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집 거래 상세 페이지</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <style>
  .row{
  	/*margin-right: 1px;*/
  	margin-left: 10px;
  }
  #img-apt{
   	max-width : 100%;
   	height : 300px;
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
<body id="page-top">
<section class="wrapper style4" style="height: 800px;">
<jsp:include page="./common/header.jsp" />
<div class="container" align="center">
    <h2 class="text-center mt-0"><${housedeals[0].aptName}> 거래 목록</h2>
    <hr class="divider my-4" />
	<div class="row" style="width:800px; height:500px; align:center;">
		<%-- <div class="form-group" style="max-width: 49%; height:415px;">
		<img id="img-apt" src="<%=root%>/img/${housedeals[0].img }">
		</div> --%>
		<!-- <div class="row ml-auto" style="max-width:49%; height:415px; overflow:scroll; overflow-x: hidden"> -->
		<!-- <div class="row ml-auto" style="max-width:100%; height:415px; overflow:scroll; overflow-x: hidden"> -->
		
		<div class="here" style="float: left; margin-left: 60px;height:415px; overflow:scroll;overflow-x: hidden"">
		<c:forEach var="housedeal" items="${housedeals}">
			<!-- <!-- 카드 헤더 -->
			<div class="card" style="float:left;">
			<div class="card-header" style="background-image: url(<%=root%>/img/${housedeals[0].img });">
			</div>
			<div class="card-body">
			<div class="card-body-header">
			<div id="title">${housedeals[0].aptName}</div>
			<p class="card-body-hashtag">${housedeal.dong}</p>
			<p class="card-body-nickname">거래금액: ${housedeal.dealAmount}</p>
			</div>
			<p class="card-body-description">거래 일: ${housedeal.dealYear}.${housedeal.dealMonth}.${housedeal.dealDay}
			<br>면적: ${housedeal.area}</p>
			<div class="card-body-footer">
			<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">
			</div>
			</div></div>
		</c:forEach>
		</div>
		<%-- <c:forEach var="housedeal" items="${housedeals}">
			<div class="form-group col-5" style="border: 2px solid black; margin-left:30px">
			    <span class="col-">거래 금액</span>
			    <div class="col-12 div-overflow" >
					${housedeal.dealAmount}
			    </div>
			    <span class="col-">전용 면적</span>
			    <div class="col-12 div-overflow">
					${housedeal.area}
			    </div>
			    <span class="col-">거래 구분</span>
			    <div class="col-12 div-overflow">
			    <!-- type에 따라서 거래 구분 출력 -->
					${housedeal.area}
			    </div>
			    <span class="col-">거래일</span>
			    <div class="col-12 div-overflow">
					${housedeal.dealYear}.${housedeal.dealMonth}.${housedeal.dealDay}
			    </div>
			</div>
		</c:forEach> --%>
		</div>
	</div>
	<div style="clear:both;">
		<div class="form-group row">
		    <div class="col-sm-12" align="center">
		    <!-- submit으로 하던지 a태그로 교환할것. -->
		       <!-- 목록으로 돌아갈때 기존 페이지 기억할것. -->
		       <!-- <button type="button" id="housedealListPage" class="btn btn-primary">목록</button> -->
		       <a href="/map" class="button special small">목록</a>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#housedealListPage').click(function(){
				location.href = '/map';
			});
		})
	</script>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
	
	<!-- Core theme JS-->
	<script src="/js/scripts.js"></script>
	<jsp:include page="./common/footer.jsp" />
	</section>
</body>
</html>