<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value='<%= request.getParameter("leng") %>'/>
	<%
		//value값을 작성할 때 큰따옴표("") 로 작성하게 되면 안된다
		//표현식을 다르게 써서 param을 통해 EL문 ${} 을 작성하는것이 안전하다고 한다...
		//일단 따옴표('')로 작성하여 스크립트 태그를 사용하니 정상작동이 된다.
	%>
	<fmt:bundle basename="bundle.message">
	<div class = "container py-5">
		<%@ include file = "menu.jsp" %>
		
		<div class = "p-5 mb-4 bg-body-teriary rounded-3">
			<div class = "container-fluid py-5">
				<h1 class="display-5 fw-bold py-5"><fmt:message key="title"></fmt:message></h1>
				<p class="col-md-8 fs-4">Book Addition</p>
			</div>
		</div>
	
		<div class = "row align-items-md-stretch">
			<div class="text-end">
				<a href="?leng=ko"> 한국어 </a> | <a href="?leng=en">English</a>
				<a href="logout" class="btn btn-sm btn-success pull right">logout</a>
			</div>
			<form id="newBook" class="form-horizontal" name="newBook" method="post" action="AddBook" enctype="multipart/form-data">
				<!-- 도서코드 -->
				<div class = "mb-3 row"> 
					<label class="col-sm-2"> <fmt:message key="bookId"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="bookId" type="text" name="bookId" class="form-control">
					</div>
				</div>
				
				<!-- 도서명 -->
				<div class = "mb-3 row"> 
					<label class="col-sm-2"> <fmt:message key="name"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="name" type="text" name="name" class="form-control">
					</div>
				</div>
				
				<!-- 가격 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="unitPrice"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="unitPrice" type="text" name="unitPrice" class="form-control">
					</div>
				</div>
				
				<!-- 저자 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="author"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="author" type="text" name="author" class="form-control">
					</div>
				</div>
				
				<!-- 출판사 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="publisher"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="publisher" type="text" name="publisher" class="form-control">
					</div>
				</div>
				
				<!-- 출판일 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="releaseDate"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="releaseDate" type="text" name="releaseDate" class="form-control">
					</div>
				</div>
				
				<!-- 상세정보 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="description"></fmt:message> </label>
					<div class="col-sm-5">
						<textarea id="description" rows="2" cols="50" name="description" class="form-control" placeholder="100자 이상 적어주세요"></textarea>
					</div>
				</div>
				
				<!-- 분류 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="category"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="category" type="text" name="category" class="form-control">
					</div>
				</div>
				
				<!-- 재고수량 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="unitsInStock"></fmt:message> </label>
					<div class="col-sm-3">
						<input id="unitsInStock" type="text" name="unitsInStock" class="form-control">
					</div>
				</div>
				
				<!-- 상태 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="condition"></fmt:message> </label>
					<div class="col-sm-5">
						<input type="radio" name="condition" value="New"><fmt:message key="condition_New"></fmt:message> 
						<input type="radio" name="condition" value="Old"><fmt:message key="condition_Old"></fmt:message> 
						<input type="radio" name="condition" value="EBook"><fmt:message key="condition_Ebook"></fmt:message> 
					</div>
				</div>
				
				<!-- 이미지 등록 -->
				<div class = "mb-3 row">
					<label class="col-sm-2"> <fmt:message key="bookImage"></fmt:message> </label>
					<div class="col-sm-5">
						<input id="bookImage" type="file" name="bookImage" class="form-control">
					</div>
				</div>
				
				
				<div class = "mb-3 row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" value="<fmt:message key="button"></fmt:message>" id="but">
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	</fmt:bundle>
</body>

<script type="text/javascript" src="resources/js/validation.js"></script>
</html>