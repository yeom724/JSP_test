<%@page import="dao.BookRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dto.Book" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>도서 정보</title>
</head>
<body>
	<div class="container py-4">
	
		<div class="p-5 mb-4 bg-body-teriary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서정보</h1>
				<p class="col-md-8 fs-4">BookInfo</p>
			</div>
		</div>
		
		<%
			String id = request.getParameter("id");
			BookRepository br = (BookRepository)request.getAttribute("bookDAO");
			Book book = br.getBookById(id);
		%>
		
		<div class="row align-items-md-stretch">
			
			<div class="col-md-12">
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
				<p><a href="#" class="btn btn-info">도서 주문 &raquo;</a></p>
				<p><a href="products" class="btn btn-secondary">도서 목록</a></p>

			</div>
		
		</div>
	</div>
</body>
</html>