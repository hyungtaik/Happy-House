<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="area" value="${a}"></c:set>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>HappyHouse-관심지역</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<style>
.select-wrapper {
	width: 20%;
	float: left;
	margin-right: 15px;
}

.area {
	margin-bottom: 20px;
}

a[name^=nav]:hover{
	/* 보라보라 */
	background-color:#8A4680;	
}

</style>
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript">

$(document).on("change","select[name='sido']", function(){
	var $target = $(this).parent().parent().find("select[id='gugun']");	//gu selectbox
	var $target2 = $(this).parent().parent().find("select[id='dong']");	//dong selectbox
	
	$target.empty();
	$target2.empty();
	$target2.append('<option value="0">-읍/면/동-</option>');	
	
	var sCity = $(this).val();

	$.ajax({
		type: 'get',
		url: '<%=root%>/map/gu/' + sCity,
		async: false,
		dataType : 'json',   
		success: function(data){
			$target.empty();
			$target.append('<option value="0">-구/군-</option>');
			$.each(data, function(index, vo) {
				$target.append("<option value='"+vo.gugun_name+"'>"+vo.gugun_name+"</option>");
			});//each
		}, error:function(request, status, error){
			alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
			return;
		}
	});	//change
	
});

$(document).on("change","select[name='gugun']", function(){
	var $target = $(this).parent().parent().find("select[id='dong']");	//dong selectbox
	$target.empty();
	
	var sGu = $(this).val();
	
	$.ajax({
		type: 'get',
		url: '<%=root%>/map/dong/' + sGu,
		async: false,
		dataType : 'json',   
		success: function(data){
			$target.empty();
			$target.append('<option value="0">-읍/면/동-</option>');
			$.each(data, function(index, vo) {
				$target.append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
			});//each
		}, error:function(request, status, error){
			alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
			return;
		}
	});
	
});

//삭제 버튼 클릭 -> 관심지역 하나를 지운다
$(document).on("click","a[id='removeBtn']", function(){
	var $target = $(this).parent().parent();
	$target.remove();		//area 하나 날림
});


$(document).ready(function() {
	
	//관심지역 추가버튼 클릭
	$("#addBtn").click(function(){
		var $div = $('<div id="area" class="area">'
				+'<div>'
				+'<div class="select-wrapper">'
				+'<select id="sido" name="sido">'
				+'<option value="0">-시/도-</option>'
				+'</select>'
				+'</div>'
				+'<div class="select-wrapper">'
				+'<select id="gugun" name="gugun">'
				+'<option value="0">-구/군-</option>'
				+'</select>'
				+'</div>'
				+'<div class="select-wrapper">'
				+'<select id="dong" name="dong">'
				+'<option value="0">-읍/면/동-</option>'
				+'</select>'
				+'</div>'
				+'<a class="button small" id="removeBtn" style="height:35px;"><img src="/img/trash.png" style="width:25px; padding-bottom:10px;"></a>'
				+'</div>'
				+'</div>');

		
		//updateform의 마지막 자식 요소로 추가
		$('#areas').append($div);
		 

		var $target = $("#areas > #area:last-child").find("select[name='sido']");	//city selectbox
		$target.empty();
		
		var sCity = $(this).val();
		
		$.ajax({
			type: 'get',
			url: '<%=root%>/map/city',
			async: false,
			dataType : 'json',   
			success: function(data){
				$target.append('<option value="0">-시/도-</option>');
				$.each(data, function(index, vo) {
					$target.append("<option value='"+vo.sido_name+"'>"+vo.sido_name+"</option>");
				});//each
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
				return;
			}
		});
	});
	
	
	//부적합한 값들 있는지 검사
	$("#updateBtn").click(function(){
		var flag = true;
		$("select[name^=sido]").each(function() {
			  $selsido = $(this);
			  if(flag == true){
				  if($selsido.val() == "0"){
					  alert("시/도 확인해주세요!");
					  flag = false;
				  }
			  }
		});
		if(flag == false)
			return false;
		
		$("select[name^=gugun]").each(function() {
			  $selgu = $(this);
			  if(flag == true){
				  if($selgu.val() == "0"){
					  alert("구/군 확인해주세요!");
					  flag = false;
					  return;
				  }
			  }
		});
		if(flag == false)
			return false;
		
		$("select[name^=dong]").each(function() {
			  $seldong = $(this);
			  if(flag == true){
				  if($seldong.val() == "0"){
					  alert("읍/면/동 확인해주세요!");
					  flag = false;
					  return;
				  }
			  }
		});
		
		if(flag == false){
			return false;
		}else{
			//검사끝났으면 저장
			$("#updateform").attr("action", "<%=root%>/fav/save").submit();
		}
	});
});
</script>
</head>
<body id="page-top">
	<jsp:include page="../common/header.jsp" />
	<section class="wrapper style4" style="height: 800px;">
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
		</div>
		<!-- </div> -->
		<div class="inner" style="width: 85%; float: right;">
			<div class="box">
				<!-- <div class="content"> -->
				<header class="align-center">
					<h2>관심지역 설정하기</h2>
				</header>


				<form id="updateform" method="post">
					<input type="hidden" name="action" id="action" value="">
					<div id="areas" style="margin-left:200px;">
						<c:forEach items="${area}" var="item" varStatus="status">
							<div id="area" class="area">
								<div>
									<div class="select-wrapper">
										<select class="form-control" id="sido" name="sido">
											<option value="${item.city}" selected>${item.city}</option>
										</select>
									</div>
									<div class="select-wrapper">
										<select class="form-control" id="gugun" name="gugun">
											<option value="${item.gu}" selected>${item.gu}</option>
										</select>
									</div>
									<div class="select-wrapper">
										<select class="form-control" id="dong" name="dong">
											<option value="${item.dong}" selected>${item.dong}</option>
										</select>
									</div>
									<a class="button small" id="removeBtn" style="height:35px;"><img src="/img/trash.png" style="width:25px; padding-bottom:10px;"></a>
								</div>
							</div>
						</c:forEach>
					</div>

				</form>
				<div class="form-group" align="center">
					<a id="addBtn" class="button small">관심지역 추가</a>
				</div>
				<div class="form-group" align="center">
					<p>&nbsp;</p>
				</div>
				<div class="form-group" align="center">
					<a id="updateBtn" class="button special small">저장</a>
				</div>

			</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>