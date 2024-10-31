<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<% 
	String sessionId = null;
	
	HttpSession session = request.getSession(false);
	if(session != null){
		if(session.getAttribute("member")!=null){
			Member mb = (Member) session.getAttribute("member");
			sessionId = mb.getId();
		}
	}
	
	//해당 페이지는 모든 페이지에 삽입되므로, 다른 곳에서 중복 선언하지 않도록 유의한다.
	
	

%> <!-- 로그인 유지용 -->

<!DOCTYPE html> 
 <header class="pb-3 mb-4 border-bottom">
 <div class="container">
 	<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
		<a href="./welcome.jsp" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
			<svg  width="32" height="32" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
	  			<path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z"/>
	  			<path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
			</svg>   
			<span class="fs-4">Home</span>
		</a>
		<ul class="nav nav-pills">

		      	<% if(sessionId == null) { %>
						<li class="nav-item"><a class="nav-link" href="member_login">로그인 </a></li>
						<li class="nav-item"><a class="nav-link" href="member_add">회원 가입</a></li>
				<% } else { %>
						<li style="padding-top: 7px;">[<%=sessionId%>님]</li>
						<li class="nav-item"><a class="nav-link" href="member_logout">로그아웃 </a></li>
						<li class="nav-item"><a class="nav-link" href="member_update">회원 수정</a></li>
				<% } %>
			
      		<!-- R : 전체상품 불러오기 -->
	  		<li class="nav-item"><a class="nav-link"  href="products">All Book</a></li>
	  		<!-- C : 상품입력 -->
	      	<li class="nav-item"><a class="nav-link" href="AddBook">Add Book</a></li>
	      	<!-- U : 상품수정 -->
	      	<li class="nav-item"><a class="nav-link" href="editpage?edit=update">Book update</a></li>
	      	<!-- D : 상품삭제 -->
	      	<li class="nav-item"><a class="nav-link" href="editpage?edit=delete">Book delete</a></li>
	      	<li class="nav-item"><a href = "BoardListAction?pageNum=1" class = "nav-link"  >게시판</a></li>
		</ul>
	</div>
</div>
      
      
      <!-- 
      		경로 지정할 때 앞에 /를 붙이게 되면 절대경로 표시로 바뀌게 된다.
      		지금은 상대경로 표시 중이다.
       -->
    </header>    