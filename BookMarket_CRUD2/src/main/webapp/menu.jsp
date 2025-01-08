<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="DTO.Member" %>

<%
    HttpSession session = request.getSession(false);
    String sessionid=null;
    if(session != null){
    	Member mb = (Member) session.getAttribute("member");
        if(mb != null){
        	sessionid = mb.getId();
        }
    }
	
%>

 <header class="pb-3 mb-4 border-bottom">
  <div class="container ">  
   <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">  
      <a href="./welcome.jsp" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
      <svg  width="32" height="32" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
  			<path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z"/>
  			<path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
		</svg>   
        <span class="fs-4">Home</span>
      </a> 
      
       <ul class="nav nav-pills">
  	      <% if(sessionid == null){ 
  	         //로그인 하지 않았을 경우 아래의 li를 코드에 포함시킨다    
  	      %> 
			<li class="nav-item"><a class="nav-link" href="loginMember">로그인 </a></li>
			<li class="nav-item"><a class="nav-link" href="addMember">회원 가입</a></li>
	  	  <%}else{ 
	  	     //로그인 되었을 경우 아래의 아래의 li를 코드에 포함 시킨다 
	  	  %>		
			<li style="padding-top: 7px">[<%=sessionid%>님]</li>
			<li class="nav-item"><a class="nav-link" href="logoutMember">로그아웃 </a></li>
			<li class="nav-item"><a class="nav-link" href="updateMember">회원 수정</a></li>
			<li class="nav-item"><a class="nav-link" href="deleteMember?id=<%=sessionid%>">회원 탈퇴</a></li>
	  	  <%} %>
        <li class="nav-item"><a href="books" class="nav-link" >도서 목록</a></li>
        <li class ="nav-item"><a href = "addBook" class = "nav-link"  >도서 등록</a></li>
        <li class ="nav-item"><a href = "editBook?edit=update" class = "nav-link"  >도서 수정</a></li>
        <li class ="nav-item"><a href = "editBook?edit=delete" class = "nav-link"  >도서 삭제</a></li>
        <li class ="nav-item"><a href = "BoardListAction?pageNum=1" class = "nav-link"  >게시판</a></li>
        </ul> 
    </div>
  </div>   
</header>    