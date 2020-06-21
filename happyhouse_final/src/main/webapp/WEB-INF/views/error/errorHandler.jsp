<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String message = (String) request.getAttribute("msg");
%>
<html>
<head>
	<title>Exception Test</title>
</head>
<body>
<h1>
	error.jsp Page
</h1>
<%= message %>

</body>
</html>
