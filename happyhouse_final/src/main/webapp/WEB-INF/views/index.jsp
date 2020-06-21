<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>
<!DOCTYPE HTML>
<html lang="ko">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
		$.ajax({
			type: 'get',
			url: '<%=root%>/news',
			async: false,
			dataType : 'json',   
			success: function(data){
				let str = "<div class='image fit'>"
				+"<img src='" +data[0].imgUrl+"' alt='' /></div><div class='content'>"
				+"<header class='align-center'>"
				+"<h2>"+data[0].title+"</h2></header>"
				+"<p>"+data[0].contents+"</p>"
				+"<footer class='align-center'>"
				+"<a href="+data[0].url+" class='button alt'>기사보러 가기</a></footer></div></div>"
				$("#news1").append(str);
				
				str2 = "<div class='image fit'>"
					+"<img src='" +data[1].imgUrl+"' alt='' /></div><div class='content'>"
					+"<header class='align-center'>"
					+"<h2>"+data[1].title+"</h2></header>"
					+"<p>"+data[1].contents+"</p>"
					+"<footer class='align-center'>"
					+"<a href="+data[1].url+" class='button alt'>기사보러 가기</a></footer></div></div>"
					$("#news2").append(str2);
			}, error:function(request, status, error){
				alert("지금은 시스템 사정으로 요청하신 작업을 처리할 수 없습니다.");
				return;
			}
	});//change
});

</script>
<body>
	<!-- Header & Nav -->
	<jsp:include page="common/header.jsp" />
	<!-- Banner -->
	<section class="banner full">
		<article>
			<img src="/template/images/house11.jpg" alt="" />
			<div class="inner">
				<header>
					<p>
						형택 & 수연 <a href="https://lab.ssafy.com/qkdqnwpwp/happyhouse_final">Project</a>
					</p>
					<h2>Happy House</h2>
				</header>
			</div>
		</article>
	</section>

	<!-- One -->
	<section id="one" class="wrapper style2">
		<div class="inner">
			<div class="grid-style">

				<div>
					<div class="box" id="news1">
						
					</div>
				</div>

				<div>
					<div class="box" id="news2">
						
					</div>
				</div>

			</div>
		</div>
	</section>

	<!-- Two -->
	<section id="two" class="wrapper style3">
		<div class="inner">
			<header class="align-center">
				<p>Nam vel ante sit amet libero scelerisque facilisis eleifend
					vitae urna</p>
				<h2>Morbi maximus justo</h2>
			</header>
		</div>
	</section>

	<!-- Three -->
	<section id="three" class="wrapper style2">
		<div class="inner">
			<header class="align-center">
				<p class="special">Nam vel ante sit amet libero scelerisque
					facilisis eleifend vitae urna</p>
				<h2>Morbi maximus justo</h2>
			</header>
			<div class="gallery">
				<div>
					<div class="image fit">
						<a href="#"><img src="template/images/pic01.jpg" alt="" /></a>
					</div>
				</div>
				<div>
					<div class="image fit">
						<a href="#"><img src="template/images/pic02.jpg" alt="" /></a>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
  Launch static backdrop modal
</button> -->

<!-- Modal -->
<!-- <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div> -->
	<!-- Footer -->
	<jsp:include page="common/footer.jsp" />

</body>
</html>