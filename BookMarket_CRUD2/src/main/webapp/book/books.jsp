<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "DTO.Book" %>
<html>
<head>
 <link href = "./resources/css/bootstrap.min.css" rel="stylesheet">

<title>도서 목록</title>
</head>
<body>
	<%
	  ArrayList<Book> arr = (ArrayList<Book>) request.getAttribute("list");
	%>
	<div class="container py-4">
		<jsp:include page="/menu.jsp" />		
	   
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">도서목록</h1>
				<p class="col-md-8 fs-4">BookList</p>      
			</div>
		</div>
		<div class="row align-items-md-stretch   text-center">	 	
			<% for(int i=0; i<arr.size();i++){ 
				Book bk = arr.get(i);  
			%>
			<div class="col-md-4">
				<div class="h-100 p-2">	
					<img src="./resources/images/<%=bk.getFilename()%>" style="width: 250; height:350" />		
					<h5><b><%=bk.getName()%></b></h5>
					<p><%=bk.getAuthor()%>
					<br> <%=bk.getPublisher()%> | <%=bk.getReleaseDate()%>
					<p> <%=bk.getDescription().substring(0,30)%>....
					<p><%=bk.getUnitPrice()%>원
					<p><a href="book?id=<%=bk.getBookId()%>" class="btn btn-secondary" role="button"> 상세 정보 &raquo;</a>	
				</div>	
			</div>
			<%} %>			
		</div>	
		<jsp:include page="/footer.jsp" />   
	</div>
</body>
</html>
