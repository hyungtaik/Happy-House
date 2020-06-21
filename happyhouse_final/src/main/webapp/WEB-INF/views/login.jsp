<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function Login(){
	if(document.loginform.id.value == "") {
		alert("아이디 입력!!!")
		document.loginform.user.focus();
	}
	else if (document.loginform.pwd.value == "" ) {
		alert("비밀번호 입력!!!")
		document.loginform.pass.focus();
	}
	else {
		document.getElementById("loginform").action = "<%= root %>/login";
		document.getElementById("loginform").submit();
	}
}

</script>

</head>
<body class="subpage">
	<!-- Header & Nav -->
	<jsp:include page="common/header.jsp" />
	
	<!-- Banner -->
	<section id="One" class="wrapper style3">
		<div class="inner">
			<header class="align-center">
				<p>Sed amet nulla</p>
				<h2>Elements</h2>
			</header>
		</div>
	</section>
	
	
	<!-- Vertically centered modal -->
<div class="modal-dialog modal-dialog-centered">
  ...
</div>

<!-- Vertically centered scrollable modal -->
<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
  ...
</div>
	
	<!-- Footer -->
	<jsp:include page="common/footer.jsp" />
</body>

</html>