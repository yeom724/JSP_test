<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
</head>
<body>
	<div class="container py-4">
		<%@include file="menu.jsp" %>
		
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">주문 완료</h1>
				<p class="col-md-8 fs-4">Order Completed</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch">
			<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
			<p>주문은 <%= (String)request.getAttribute("sh_Date") %>에 배송될 예정입니다!</p>
			<p>주문번호 : <%= (String)request.getAttribute("sh_cartId")%></p>
		</div>
		
		<div class="container">
			<p><a href="products" class="btn btn-secondary">&laquo;도서 목록</a></p>
		</div>
		
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>