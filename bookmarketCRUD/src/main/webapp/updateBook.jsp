<%@page import="dto.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 수정</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
</head>
<body>

	<%
		Book book = (Book)request.getAttribute("book");
	%>
	<div class="container py-4">
	
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서수정</h1>
				<p class="col-md-8 fs-4">Book Updating</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch">
			<div class="col-md-5">
				<img src="resources/img/<%= book.getFilename()%>" style="width:100%;">
			</div>
			
			<div class="col-md-7">
				<form id="newBook" class="form-horizontal" name="newBook" method="post" action="update" enctype="multipart/form-data">
					<!-- 도서코드 -->
					<div class = "mb-3 row"> 
						<label class="col-sm-2"> 도서코드 </label>
						<div class="col-sm-5">
							<input readonly id="bookId" type="text" name="bookId" class="form-control" value="<%= book.getBookId() %>">
						</div>
					</div>
					
					<!-- 도서명 -->
					<div class = "mb-3 row"> 
						<label class="col-sm-2"> 도서명 </label>
						<div class="col-sm-5">
							<input id="name" type="text" name="name" class="form-control" value="<%= book.getName() %>">
						</div>
					</div>
					
					<!-- 가격 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 가격 </label>
						<div class="col-sm-5">
							<input id="unitPrice" type="text" name="unitPrice" class="form-control" value="<%= book.getUnitPrice() %>">
						</div>
					</div>
					
					<!-- 저자 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 저자 </label>
						<div class="col-sm-5">
							<input id="author" type="text" name="author" class="form-control" value="<%= book.getAuthor() %>">
						</div>
					</div>
					
					<!-- 출판사 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 출판사 </label>
						<div class="col-sm-5">
							<input id="publisher" type="text" name="publisher" class="form-control" value="<%= book.getPublisher() %>">
						</div>
					</div>
					
					<!-- 출판일 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 출판일 </label>
						<div class="col-sm-5">
							<input id="releaseDate" type="text" name="releaseDate" class="form-control" value="<%= book.getReleaseDate() %>">
						</div>
					</div>
					
					<!-- 상세정보 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 상세정보 </label>
						<div class="col-sm-8">
							<textarea id="description" rows="3" cols="100" name="description" class="form-control" placeholder="100자 이상 적어주세요"><%= book.getDescription() %></textarea>
						</div>
					</div>
					
					<!-- 분류 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 분류 </label>
						<div class="col-sm-5">
							<input id="category" type="text" name="category" class="form-control" value="<%= book.getCategory() %>">
						</div>
					</div>
					
					<!-- 재고수량 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 재고수 </label>
						<div class="col-sm-5">
							<input id="unitsInStock" type="text" name="unitsInStock" class="form-control" value="<%= book.getUnitsInStock() %>">
						</div>
					</div>
					
					<!-- 상태 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 상태 </label>
						<div class="col-sm-8">

							<input type="radio" name="condition" value="New"
							<%
								if(book.getCondition().equalsIgnoreCase("new")){%>
								checked
							<%	
								}
							%> > 신규도서 
							
							<input type="radio" name="condition" value="Old"
							<%
								if(book.getCondition().equalsIgnoreCase("old")){
							%>
								checked
							<%	
								}
							%> > 중고도서 
							<input type="radio" name="condition" value="EBook"
							<%
								if(book.getCondition().equalsIgnoreCase("ebook")){
							%>
								checked
							<%	
								}
							%> > E-Book
						</div>
					</div>
					
					<!-- 이미지 등록 -->
					<div class = "mb-3 row">
						<label class="col-sm-2"> 이미지 </label>
						<div class="col-sm-8">
							<input id="bookImage" type="file" name="bookImage" class="form-control">
						</div>
					</div>
					
					
					<div class = "mb-3 row">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value="등록">
							<a href="products" class="btn btn-primary">도서목록</a>
						</div>
					</div>
			</form>
				
				
			</div>
		</div>
	
	
	
	</div>
</body>
</html>