<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<%
	String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>HappyHouse</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/template/assets/css/main.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<style>
#login, #logout {
	background-color: transparent;
	color: white !important;
}

#join {
	border: 1px solid white;
	background-color: transparent;
	color: white !important;
	margin-right: 20px;
}

input{
	margin-bottom:10px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#joinGo").click(function() {
		var user = {
				id:$('#uid').val(),
				pwd:$('#upwd').val(),
				name:$('#uname').val(),
				email:$('#email').val(),
				phone:$('#phone').val()
		}
		$.ajax({
			type: 'post',
			url: '<%=root%>/user/join',
			dataType: 'json',
            contentType: "application/json; charset=utf-8",
			data:JSON.stringify(user),
			success: function(data,status,xhr){
				console.log(data);
				if(data.id==null){
					alert("중복된 Email입니다.");
					return;
				}
				alert("회원가입을 축하드립니다!!!");
				location.href="${root}/";
				return;
			}, error:function(request, status, error){
				alert("중복된 ID입니다.다시한번 확인해주세요");
				return;
			}
		});
	});
	$("#findGo").click(function() {
		var user = {
				email:$('#femail').val(),
				phone:$('#fphone').val()
		}
		$.ajax({
			type: 'post',
			url: '<%=root%>/user/find',
			dataType: 'json',
            contentType: "application/json; charset=utf-8",
			data:JSON.stringify(user),
			success: function(data,status,xhr){
				console.log(data);
				alert("[ID] "+data.id+"  [PW] "+data.pwd);
				location.href="${root}/";
				return;
			}, error:function(request, status, error){
				alert("해당 정보가 없습니다.");
				return;
			}
		});
	});
});
function Login(){
	if(document.loginform.id.value == "") {
		alert("아이디를 입력해주세요!")
		document.loginform.id.focus();
	}
	else if (document.loginform.pwd.value == "" ) {
		alert("비밀번호를 입력해주세요!")
		document.loginform.pwd.focus();
	}
	else {
		document.getElementById("loginform").action = "<%=root%>/user/login";
			document.getElementById("loginform").submit();
		}
	}

</script>
</head>
<body>

	<!-- Header -->
	<header id="header">
		<div class="logo">
			<a href="${root}/">HappyHouse <span>by how us</span></a>
			&emsp;&emsp;&emsp;&emsp;&emsp; <span>${msg}</span>
		</div>


		<!-- 비로그인 상태면 login버튼과 join버튼을 보여준다 -->
		<c:if test="${empty suser}">
			<button id="login" type="button" class="button" data-toggle="modal"
				data-target="#loginModal">Login</button>
			<button id="join" type="button" class="button" data-toggle="modal"
				data-target="#joinModal">Join</button>
		</c:if>
		<!-- 로그인 상태면 메뉴를 보여준다 -->
		<c:if test="${not empty suser}">
			<button id="logout" type="button" class="button" data-toggle="modal"
				data-target="#logoutModal">Logout</button>
			<a href="#menu">Menu</a>
		</c:if>
	</header>

	<!-- Nav -->
	<nav id="menu">
		<ul class="links">
			<li><a href="/map">부동산 정보</a></li>
			<!-- <li><a href="#">상권정보</a></li> -->
			<li><a href="/rank">매물 순위</a></li>
			<li><a href="/fav/list">관심 지역</a></li>
			<li><a href="/QnA.html">Q&A</a></li>
			<li><a href="/Notice.html">공지사항</a></li>
			<li><a href="/mypage">MyPage</a></li>
		</ul>
	</nav>

	<!-- Login Modal -->
	<div class="modal fade" id="loginModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel">Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="post" id="loginform" name="loginform" action="">
									<label for="id">ID</label>
									<input id="id" type="text" name="id"
										style="font-size: 10pt; height: 35px;"
										placeholder="아이디를 입력하세요" tabindex=1
										onKeyPress="if (event.keyCode == 13) Login();" />
									<label for="pwd">PASS</label>
									<input id="pwd" type="password" name="pwd"
										style="font-size: 10pt; height: 35px;"
										placeholder="비밀번호를 입력하세요" tabindex=2
										onKeyPress="if (event.keyCode == 13) Login();" />
					</form>
				</div>
				<div class="modal-footer">
					<a class="button small special" href="javascript:Login();">Login</a>
					<button id="find" type="button" class="button small" data-toggle="modal"
						data-target="#findModal">ID/PW 찾기</button>
					<a class="button small alt" data-dismiss="modal">닫기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Logout Modal -->
	<div class="modal fade" id="logoutModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="logoutModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="logoutModalLabel">Logout</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>로그아웃 하겠습니까?</p>
				</div>
				<div class="modal-footer">
					<a class="button small special" href="/user/logout">확인</a> <a
						class="button small alt" data-dismiss="modal">닫기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Join Modal -->
	<div class="modal fade" id="joinModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="joinModalLabel">Join</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="post" id="joinform" name="joinform" action="">
									<label for="uid">ID</label>
									<input id="uid" type="text" name="uid"
										style="font-size: 10pt; height: 35px;"
										placeholder="아이디를 입력하세요" tabindex=1 />
									<label for="uname">Name</label>
									<input id="uname" type="text" name="uname"
										style="font-size: 10pt; height: 35px;" placeholder="이름을 입력하세요"
										tabindex=2 />
									<label for="upwd">PASS</label>
									<input id="upwd" type="password" name="upwd"
										style="font-size: 10pt; height: 35px;"
										placeholder="비밀번호를 입력하세요" tabindex=3 />
									<label for="email">E-Mail</label>
									<input id="email" type="text" name="email"
										style="font-size: 10pt; height: 35px;"
										placeholder="E-Mail을 입력하세요" tabindex=4 />
									<label for="phone">Phone Number</label>
									<input id="phone" type="text" name="phone"
										style="font-size: 10pt; height: 35px;"
										placeholder="010-XXXX-XXXX" tabindex=5 />
					</form>
				</div>
				<div class="modal-footer">
					<a class="button small special" id="joinGo">Join</a> 
					<button id="find" type="button" class="button small" data-toggle="modal"
						data-target="#findModal">ID/PW 찾기</button> <a
						class="button small alt" data-dismiss="modal">닫기</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Find PW -->
	<!-- Login Modal -->
	<div class="modal fade" id="findModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="findModalLabel">ID/PW 찾기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="post" id="findform" name="findform" action="">
						<div class="table-wrapper">
							<table>
								<tr>
									<label for="femail">E-Mail</label>
									<input id="femail" type="text" name="femail"
										style="font-size: 10pt; height: 35px;"
										placeholder="이메일을 입력하세요" tabindex=1 />
								</tr>
								<tr>
									<label for="fphone">전화번호</label>
									<input id="fphone" type="text" name="fphone"
										style="font-size: 10pt; height: 35px;"
										placeholder="000-0000-0000" tabindex=2 />
								</tr>
							</table>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a class="button small special" id="findGo">찾기</a>
					<a class="button small alt" data-dismiss="modal">닫기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>