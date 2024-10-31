<%@page import="dao.BookRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dto.Book" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page errorPage="error01" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookmarketCRUD/resources/css/bootstrap.min.css" >
<meta charset="UTF-8">
<title>도서 정보</title>
</head>
<body>
	<div class="container py-4">
	
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서정보</h1>
				<p class="col-md-8 fs-4">BookInfo</p>
			</div>
		</div>
		
		<% Book book = (Book)request.getAttribute("book"); %>
		
		<div class="row align-items-md-stretch">
		
			<div class="col-md-5">
				<img src="/bookmarketCRUD/resources/img/<%= book.getFilename()%>" style="width:70%;">
			</div>
			
			<div class="col-md-6">
				<h3><b><%= book.getName() %></b></h3>
				<p><%= book.getDescription() %></p>
				<table>
					<tr>
						<td><b>도서코드</b></td>
						<td>　:　</td>
						<td><span class="badge text-bg-danger"><%= book.getBookId() %></span></td>
					</tr>
					<tr>
						<td><b>저자</b></td>
						<td>　:　</td>
						<td><%= book.getAuthor() %></td>
					</tr>
					<tr>
						<td><b>출판사</b></td>
						<td>　:　</td>
						<td><%= book.getPublisher() %></td>
					</tr>
										<tr>
						<td><b>출판일</b></td>
						<td>　:　</td>
						<td><%= book.getReleaseDate() %></td>
					</tr>
										<tr>
						<td><b>분류</b></td>
						<td>　:　</td>
						<td><%= book.getCategory() %></td>
					</tr>
										<tr>
						<td><b>재고수</b></td>
						<td>　:　</td>
						<td><%= book.getUnitsInStock() %></td>
					</tr>
				</table>
				<hr>
				<h4><%= book.getUnitPrice() %>원</h4>
				<form name="addForm" action="addCart?id=<%= book.getBookId()%>" method="post">
					<a href="cart" class="btn btn-warning">장바구니 &raquo;</a>
					<a href="#" class="btn btn-info" onclick="addToCart()">도서 주문 &raquo;</a>
					<a href="products" class="btn btn-secondary">도서 목록 &raquo;</a>
				</form>
				

			</div>
		
		</div>
	</div>
</body>

<script type="text/javascript">

	
	function addToCart() {
		//확인창이 뜸, yes는 true
		if(confirm("도서를 장바구니에 추가하시겠습니까?")){
			document.addForm.submit();
		} else {
			document.addForm.reset();
		}
	}

</script>
</html>