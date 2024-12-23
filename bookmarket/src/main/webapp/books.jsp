<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "dto.Book" %>
<jsp:useBean id="bookDAO" class="dao.BookRepository" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>BookList</title>
</head>
<body>
	<div class="container py-4">
		<%@ include file = "menu.jsp" %>
		
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서목록</h1>
				<p class="col-md-8 fs-4">BookList</p>
			</div>
		</div>
		
		<%
			ArrayList<Book> listOfBooks = bookDAO.getAllBooks();
		%>
		
		<div class="row align-items-md-stretch text-center">
			<%
				for(int i=0; i<listOfBooks.size(); i++){
					Book book = listOfBooks.get(i);
					//아직 for문 닫지 않음	
			%>
			
			<div class="col-md-4">
				<div class="h-100 p-2">
					<h5><b><%= book.getName() %></b></h5>
					<p><%= book.getAuthor() %></p>
					<p><%= book.getPublisher() %> | <%= book.getUnitPrice() %>원</p>
					<p><%= book.getDescription().substring(0,60) %>...</p>
					<p><%= book.getUnitPrice() %>원</p>
					<p><a href="./book.jsp?id=<%=book.getBookId()%>" class="btn btn-secondary" role="button">상세 정보 &raquo;</a></p>
				</div>
			</div>
			
			<%
				} //여기서 for문 종료, HTML태그도 모두 반복적용됨
			%>
		</div>
		<%@ include file = "footer.jsp" %>
	</div>
</body>
</html>