<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>집 거래 정보 리스트</title>
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

<style>
img {
	width: 95%;
	height: 400px;
	margin-top:35px;
}

.row {
	margin-bottom: 15px;
}

.housedealItem {
	cursor: pointer;
}

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

table {
	font-size: 80%;
	/* card size */ . gm-style-iw , .gm-style-iw-c, .gm-style-iw-d {
	/* padding : 5px !important;
	max-height: 400px !important;
	max-width: 500px !important;
	overflow: hidden !important; */
}

.select-wrapper {
	width: 30%;
	float: left;
}

/* td a {
	color:#8A4680;
}

td a:hover {
	color:#8A4680;
	font-weight: bold;
}

.housedealItem:hover{
background-color:#8A4680;
	font-weight: bold;
} */
a.page-link{
	color:gray;
}

a.page-link:hover{
	color:#8A4680;
	font-weight: bold;
}

.page-item active .page-link {
    z-index: 3;
    color: #fff;
    background-color: #8A4680 !important;
    border: 1px solid #dee2e6 !important;
}
</style>
</head>
<body>
	<jsp:include page="./common/header.jsp" />
	<section class="wrapper style4" style="height: 1000px;">
						<div class="inner"
					style="width: 45%; float: left; margin-left: 50px;">
					<div class="box" align="center">
						<div class="btn-group" role="group" aria-label="Basic example">
							<button type="button" id="searchRegBtn" class="button small" onclick="location.href='/map'">지역으로 검색</button>
							<button type="button" id="searchNameBtn" class="button special small">아파트/동명 검색</button>
						</div>
					</div>
					<!-- 지역으로 검색 -->
					<!-- 동명/아파트명으로 검색  -->
					<div class="box" id="searchNameBox" style="margin:0;">
						<form id="formHouseDealSearch" action="/house/list2" method="get">
							<input type="hidden" id="pageIndex" name="page" value=1>
							<div>
								<div class="select-wrapper" style="width: 20%; margin-right:20px; display: inline-block; ">
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
									style="width: 60%; display: inline-block; margin-right: 20px; margin-bottom:0;"
									name="aptName" id="aptName"
									value="<c:if test="${aptName != null}">${aptName}</c:if>">
								<button class="button alt small" type="submit" id="button-addon2">검색</button>
							</div>
						</form>
					</div>

					<!-- 검색결과 -->
					<div class='left-box' style="width:100%;  margin-top:50px;">
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
								<c:forEach var="housedeal" items="${housedeals}">
							<tr class="housedealItem" data-housename="${housedeal.aptName}"
								data-dong="${housedeal.dong }">
								<!--         <td><img src="<c:url value='/img/${housedeal.img}'/>"></td> -->
								<td>${housedeal.aptName}</td>
								<td>${housedeal.dong}</td>
								<td>${housedeal.dealAmount }</td>
								<td>${housedeal.area }</td>
								<td>${housedeal.dealYear}.${housedeal.dealMonth}.${housedeal.dealDay}</td>
							</tr>
						</c:forEach>
							</tbody>
					</table>
					</div>
						
						<div style="width: 95%;">
					<ul class="pagination justify-content-center">

						<c:if test="${housePagination.curRange ne 1 }">
							<li class="page-item "><a class="page-link" href="#"
								tabindex="-1" aria-disabled="true" data-pageno='1' style="color:#8A4680"> < </a></li>
						</c:if>

						<c:if test="${housePagination.curPage ne 1 }">
							<li class="page-item "><a class="page-link" href="#"
								tabindex="-1" aria-disabled="true"
								data-pageno='${housePagination.startPage-10 }' style="color:#8A4680"> << </a></li>
						</c:if>

						<c:forEach var="pageNum" begin="${housePagination.startPage }"
							end="${housePagination.endPage }">
							<c:choose>
								<c:when test="${pageNum eq  housePagination.curPage}">
									<li class="page-item active" aria-current="page" ><span
										class="page-link" data-pageno='${pageNum }' style="background-color: #8A4680;border: 1px solid #dee2e6 !important;"> ${pageNum }
											<span class="sr-only" >(current)</span>
									</span></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="#"
										data-pageno='${pageNum }' style="color:#8A4680">${pageNum }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if
							test="${housePagination.curRange ne housePagination.rangeCnt && housePagination.rangeCnt> 0}">
							<li class="page-item"><a class="page-link" href="#"
								data-pageno='${housePagination.endPage+1 }' style="color:#8A4680"> > </a></li>
							<li class="page-item "><a class="page-link" href="#"
								tabindex="-1" aria-disabled="true"
								data-pageno='${housePagination.pageCnt }' style="color:#8A4680"> >> </a></li>
						</c:if>
					</ul>
				</div>
					</div>
				</div>

			<div class="inner" style="width: 50%; float: right;">
				<header class="align-center">
					<h2>아파트 정보</h2>
				</header>
				
				<div class="box" align="center" style="margin-right:50px;">
			<div class='right-box' style="width: 95%; height: 650px; margin-top:35px;">
				<!-- 이미지 -->
				<div id="imageDIV"></div>
			</div>
		</div>
			</div>
			<!-- 
			<div>
			${housePagination.curRange}
			${housePagination.rangeCnt}
			총 게시글 수 : ${housePagination.searchCnt } / 총 페이지 수 :
				${housePagination.pageCnt } / 현재 페이지 : ${housePagination.curPage } /
				현재 블럭 : ${housePagination.curRange } / 총 블럭 수 :
				${housePagination.rangeCnt }</div>
		-->
		<!-- <div class="box" align="center" style="margin-right:50px;">
			<div class='right-box' style="width: 50%; height: 500px;">
				이미지
				<div id="imageDIV"></div>
			</div>
		</div> -->
			<script>
		$(document).ready(function() {
			$(".housedealItem").click(function() {
				var houseno = $(this).attr("data-houseno");
				var housename = $(this).attr("data-housename");
				
				$.ajax({
					type: 'get',
					url: '<%=root%>/house/img/' +$(this).attr("data-dong") + '/'+$(this).attr("data-housename"),
					async: false,
					dataType : 'json',   
					success: function(data){
						$("#imageDIV").empty();
						
						let temp = '<div class="table-wrapper">'
						+'<table class="alt">'
						+'<tr>'
						+'<td width="130px">아파트 이름 </td>'
						+'<td>'+ data.aptName +'</td>'
						+'</tr>'
						+'<tr>'
						+'<td>지번</td>'
						+'<td>'+ data.jibun +'</td>'
						+'</tr>'
						+'<tr>'
						+'<td>건축년도</td>'
						+'<td>'+ data.buildYear +'</td>'
						+'</tr>'
						+'<tr>'
						+'<td colspan="2">'
						+'<img src="<%=root%>/img/'
						+ data.img
						+ '" align="center" height="400px">'
						+'</td>'
						+'</tr>';
						
					$("#imageDIV").append(temp);
					//geocode(data);
					},
					error : function(request,status,error) {
						alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다...");
						return;
					}
				});

			});

				$(".page-link").click(function() {
					var pageno = $(this).attr("data-pageno");
					$("#pageIndex").val(pageno);
					console.log($("#pageIndex").val());
					$("#formHouseDealSearch").submit();
				});
			});
		</script>
	</section>
	<jsp:include page="./common/footer.jsp" />
</body>
</html>
