<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 오류</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
</head>
<body>
	<div class="container py-4">
		<%@ include file="menu.jsp" %>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="alert alert-danger">요청하신 페이지를 찾을 수 없습니다.</h1>
			</div>	
		</div>
		
		<div class="row align-items-md-stretch">
			<div>
				<div>
					<p><%= request.getRequestURL() %></p>
					<p><a href="products" class="btn btn-secondary">도서 목록 &raquo;</a></p>
				</div>
			
			</div>
		
		</div>
		
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>