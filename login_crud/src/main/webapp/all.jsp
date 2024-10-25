<%@page import="dto.member_dto"%>
<%@page import="java.util.ArrayList"%>
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
		ArrayList<member_dto> arr = (ArrayList<member_dto>)request.getAttribute("list");
		
	%>
	
	<h1>회원명단</h1>
	<a href="/login_crud">home</a>
	<table>
		<tr>
			<td>ID</td>
			<td>PW</td>
			<td>AGE</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
			<%
				for(int i=0; i<arr.size(); i++){
					
					member_dto mb = arr.get(i);
			%>
					<tr>
						<td><%= mb.getId() %></td>
						<td><%= mb.getPw() %></td>
						<td><%= mb.getAge() %></td>
						<td> <a href="update?id=<%= mb.getId()%>">수정</a> </td>
						<td> <a href="delete?id=<%= mb.getId()%>">삭제</a> </td>
					</tr>	
					
			<%	
				}
			%>
		
	</table>
</body>
</html>