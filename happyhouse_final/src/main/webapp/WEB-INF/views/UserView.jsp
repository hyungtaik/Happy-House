<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:set var="msg" value="${msg}"></c:set>
<c:set var="id" value="${u.id}"></c:set>

<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MyPage</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>



<style type="text/css">
tbody, h1, h4 {
	text-align: center;
	/* 수정 */
	font-size: 1.3rem;
	line-height: 1.7;
	/*  */
}

#infoTable {
	margin: auto auto;
}

p {
	text-align: center;
}

#infoTable td {
	border: 1px solid white;
	padding: 10px 0px;
	text-align: center;
}

#infoTable td:first-child {
	text-align: center;
	background-color: lightgray;
}

#infoTable td:nth-child(2) {
	width: 500px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#modifyGo").click(function() {
		var user = {
				pwd:$('#mpwd').val(),
				name:$('#mname').val(),
				email:$('#memail').val(),
				phone:$('#mphone').val()
		}
		$.ajax({
			type: 'put',
			url: '<%=root%>/user/modify',
			dataType: 'json',
            contentType: "application/json; charset=utf-8",
			data:JSON.stringify(user),
			success: function(data,status,xhr){
				console.log(data);
				if(data==null){
					alert("중복된 Email입니다.");
					return;
				}
				alert("회원정보 수정 완료되었습니다.");
				location.href="${root}/mypage";
				return;
			}, error:function(request, status, error){
				alert("수정실패.다시한번 확인해주세요");
				return;
			}
		});
	});
	$("#deleteGo").click(function() {
		$.ajax({
			type: 'delete',
			url: '<%=root%>/user/delete',
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success : function(data, status, xhr) {
					alert("탈퇴 완료되었습니다.");
					location.href = "${root}/";
					return;
				},
				error : function(request, status, error) {
					alert("탈퇴실패.다시한번 확인해주세요");
					return;
				}
			});
		});
	});
</script>
</head>

<body id="page-top">

	<jsp:include page="common/header.jsp" />
	<section id="One" class="wrapper style3">
		<div class="inner">
			<header class="align-center">
				<p>MyPage에 오신걸 환영합니다</p>
				<h2>MyPage</h2>
			</header>
		</div>
	</section>
	<section id="two" class="wrapper style4">
		<div class="inner">
			<div class="box" align="center">
				<!-- <div class="content"> -->

				<div class="6u 12u$(medium)">
						<header class="align-center">
							<h2>나의 정보</h2>
						</header>
						<div class="table-wrapper">
							<table class="alt">
								<tr>
									<td>아이디</td>
									<td>${u.id}</td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td>${u.pwd}</td>
								</tr>
								<tr>
									<td>이름</td>
									<td>${u.name}</td>
								</tr>
								<tr>
									<td>email</td>
									<td>${u.email}</td>
								</tr>
								<tr>
									<td>휴대폰 번호</td>
									<td>${u.phone}</td>
								</tr>
								<tr>
									<td>권한</td>
									<td>${u.auth}</td>
								</tr>
							</table>
						</div>

					<div align="center" style="padding-bottom: 50px;">
						<button id="modify" type="button" class="button small" style="margin-right:15px;"
							data-toggle="modal" data-target="#modifyModal">회원정보 수정</button>
						<button id="delete" type="button" class="button special small"
							data-toggle="modal" data-target="#deleteModal">회원 탈퇴</button>
					</div>

				</div>
			</div>
		</div>
	</section>

	<!-- Modify Modal -->
		<div class="modal fade" id="modifyModal" data-backdrop="static"
			data-keyboard="false" tabindex="-1" role="dialog"
			aria-labelledby="modifyModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modifyModalLabel">회원정보 수정</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form method="post" id="modifyform" name="modifyform" action="">
										<label for="mname">Name</label>
										<input id="mname" type="text" name="mname" style="font-size: 10pt; height: 35px;" placeholder="이름을 입력하세요" tabindex=2 />
										<label for="mpwd">PASS</label>
										<input id="mpwd" type="password" name="mpwd"
											style="font-size: 10pt; height: 35px;"
											placeholder="비밀번호를 입력하세요" tabindex=3 />
										<label for="memail">E-Mail</label>
										<input id="memail" type="text" name="memail"
											style="font-size: 10pt; height: 35px;"
											placeholder="E-Mail을 입력하세요" tabindex=4 />
										<label for="mphone">Phone Number</label>
										<input id="mphone" type="text" name="mphone"
											style="font-size: 10pt; height: 35px;"
											placeholder="010-XXXX-XXXX" tabindex=5 />
						</form>
					</div>
					<div class="modal-footer">
						<a class="button small special" id="modifyGo">수정</a> <a
							class="button small alt" data-dismiss="modal">닫기</a>
					</div>
				</div>
			</div>
		</div>
				<!-- Delete Modal -->
	<div class="modal fade" id="deleteModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">회원 탈퇴</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>탈퇴 하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<a class="button small special" id="deleteGo">탈퇴</a> <a
						class="button small alt" data-dismiss="modal">닫기</a>
				</div>
			</div>
		</div>
	</div>	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>

	<!-- Core theme JS-->
	<jsp:include page="common/footer.jsp" />
	<%-- </c:if> --%>
</body>
</html>

