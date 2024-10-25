<%@page import="dto.member_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		member_dto dto = (member_dto)request.getAttribute("dto");
	%>
	
	<form action="update" method="post">
		<p>ID : <input readonly="readonly" type="text" name="id" value="<%= dto.getId() %>"> </p>
		<p>PW : <input type="text" name="pw" value="<%= dto.getPw() %>"> </p>
		<p>age : <input type="text" name="age" value="<%= dto.getAge() %>"> </p>
		<input type="submit" value="수정하기">
	</form>
</body>
</html>