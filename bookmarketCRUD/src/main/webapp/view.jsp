<%@page import="dto.Member"%>
<%@page import="dto.Board"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>

<%
	Board notice = (Board) request.getAttribute("board");
	int pageNum = (int)request.getAttribute("pageNum");
	//int num = ((Integer) request.getAttribute("num")).intValue();
	//int nowpage = ((Integer) request.getAttribute("page")).intValue();
%>
<html>
<head>
<link rel="stylesheet" href="/bookmarketCRUD/resources/css/bootstrap.min.css" />
<title>Board</title>
</head>
<body>

<div class="container py-4">
	<jsp:include page="menu.jsp" />
	
	 <div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">게시판</h1>
        <p class="col-md-8 fs-4">Board</p>      
      </div>
    </div>



		<div class="row align-items-md-stretch   text-center">	 
		<form name="newUpdate" action="BoardUpdateAction?num=<%= notice.getNum() %>&pageNum=<%= pageNum %>"  method="post">
				<div class="mb-3 row">
				<label class="col-sm-2 control-label" >성명</label>
				<div class="col-sm-3">
					<input name="name" class="form-control"	value=" <%=notice.getName()%>">
				</div>
			</div>
				<div class="mb-3 row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input name="subject" class="form-control"	value=" <%=notice.getSubject()%>" >
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					<textarea name="content" class="form-control" cols="50" rows="5"> <%=notice.getContent()%></textarea>
				</div>
			</div>
				<div class="mb-3 row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<%
						HttpSession session = request.getSession(false);
						if(session != null){
							Member mb = (Member)session.getAttribute("member");
							if(mb != null){
								System.out.println("세션 멤버입니다.");
	
								if(mb.getId().equals(notice.getId())){
					%>
						<p>
							<a	href="BoardDeleteAction?num=<%= notice.getNum() %>&pageNum=<%= pageNum %>"	class="btn btn-danger"> 삭제</a> 
							<input type="submit" class="btn btn-success" value="수정 ">
					<%
									
								}
							}
						}
						
					%>

					<a href="BoardListAction?pageNum=<%= pageNum %>" class="btn btn-primary"> 목록</a>
				</div>
			</div>
		</form>
	
	</div>
	<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>


