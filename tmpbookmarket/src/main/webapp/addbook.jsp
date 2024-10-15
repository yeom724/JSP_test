<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class = "container py-5">
		<%@ include file = "menu.jsp" %>
		
		<div class = "p-5 mb-4 bg-body-teriary rounded-3">
			<div class = "container-fluid py-5">
				<h1 class="display-5 fw-bold py-5">도서 등록</h1>
				<p class="col-md-8 fs-4">Book Addition</p>
			</div>
		</div>
	
		<div class = "row align-items-md-stretch">
			<form class="form-horizontal" name="newBook" method="post" action="AddBook" enctype="multipart/form-data">
				<!-- 도서코드 -->
				<div class = "mb-3 row"> 
					<label class="col-sm-2">도서코드</label>
					<div class="col-sm-3">
						<input type="text" name="bookId" class="form-control">
					</div>
				</div>
				
				<!-- 도서명 -->
				<div class = "mb-3 row"> 
					<label class="col-sm-2">도서명</label>
					<div class="col-sm-3">
						<input type="text" name="name" class="form-control">
					</div>
				</div>
				
				<!-- 가격 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">가격</label>
					<div class="col-sm-3">
						<input type="text" name="unitPrice" class="form-control">
					</div>
				</div>
				
				<!-- 저자 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">저자</label>
					<div class="col-sm-3">
						<input type="text" name="author" class="form-control">
					</div>
				</div>
				
				<!-- 출판사 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">출판사</label>
					<div class="col-sm-3">
						<input type="text" name="publisher" class="form-control">
					</div>
				</div>
				
				<!-- 출판일 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">출판일</label>
					<div class="col-sm-3">
						<input type="text" name="releaseDate" class="form-control">
					</div>
				</div>
				
				<!-- 상세정보 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">상세정보</label>
					<div class="col-sm-5">
						<textarea rows="2" cols="50" name="description" class="form-control" placeholder="100자 이상 적어주세요"></textarea>
					</div>
				</div>
				
				<!-- 분류 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">카테고리</label>
					<div class="col-sm-3">
						<input type="text" name="category" class="form-control">
					</div>
				</div>
				
				<!-- 재고수량 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">재고</label>
					<div class="col-sm-3">
						<input type="text" name="unitsInStock" class="form-control">
					</div>
				</div>
				
				<!-- 상태 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">제품 상태</label>
					<div class="col-sm-5">
						<input type="radio" name="condition" value="New"> 신규도서
						<input type="radio" name="condition" value="Old"> 중고도서
						<input type="radio" name="condition" value="EBook"> E-Book
					</div>
				</div>
				
				<!-- 이미지 등록 -->
				<div class = "mb-3 row">
					<label class="col-sm-2">이미지</label>
					<div class="col-sm-5">
						<input type="file" name="bookImage" class="form-control">
					</div>
				</div>
				
				
				<div class = "mb-3 row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value="등록">
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>