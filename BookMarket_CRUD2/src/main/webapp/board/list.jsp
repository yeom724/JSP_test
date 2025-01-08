<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="DTO.Board"%>
<%@ page session="false" %>

<%
    HttpSession session = request.getSession(false);
    if(session == null){
    	System.out.println("세션이 없습니다 ");
    }
    else{
    	System.out.println("세션이 있습니다 ");
    }
	List boardList = (List) request.getAttribute("boardlist");	
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	
%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>Board</title>
<script type="text/javascript">
	function checkForm() {	
		if (<%= session == null ?"true":"false" %>) {
			alert("로그인 해주세요.");
			return false;
		}	
		else{	
		location.href = "BoardWriteAction";
		}
		
	}
</script>

</head>
<body>
<div class="container py-4">
   <jsp:include page="/menu.jsp" />
	
	 <div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">게시판</h1>
        <p class="col-md-8 fs-4">Board</p>      
      </div>
    </div>
	
	<div class="row align-items-md-stretch   text-center">	 	
		<form action="<c:url value="./BoardListAction"/>" method="post">
	
			
				<div class="text-end"> 
					<span class="badge text-bg-success">전체 <%=total_record%>건	</span>
				</div>
		
			<div style="padding-top: 20px">
				<table class="table table-hover text-center">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회</th>
						<th>글쓴이</th>
					</tr>
					<%
					
						for (int j = 0; j < boardList.size() ; j++){
							
							Board notice = (Board) boardList.get(j);
					%>
					<tr>
						<td><%=notice.getNum()%></td>
						  <!-- href="./BoardViewAction?num=글번호&pageNum=페이지번호"  -->
						<td><a href="./BoardViewAction?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>"> <%=notice.getSubject()%></a></td>
						<td><%=notice.getRegist_day()%></td>
						<td><%=notice.getHit()%></td>
						<td><%=notice.getName()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<div align="center">
				<c:set var="pageNum" value="<%=pageNum%>" />
				<c:forEach var="i" begin="1" end="<%=total_page%>">
					<a href="<c:url value="./BoardListAction.do?pageNum=${i}" /> ">
						<c:choose>
							<c:when test="${pageNum==i}">
								<font color='4C5317'><b> [${i}]</b></font>
							</c:when>
							<c:otherwise>
								<font color='4C5317'> [${i}]</font>

							</c:otherwise>
						</c:choose>
					</a>
				</c:forEach>
			</div>
			
			<div class="py-3" align="right">							
				<a href="#" onclick="return checkForm();" class="btn btn-primary">&laquo;글쓰기</a>				
			</div>			
			<div align="left">				
				<select name="items" class="txt">
					<option value="subject">제목에서</option>
					<option value="content">본문에서</option>
					<option value="name">글쓴이에서</option>
				</select> <input name="text" type="text" /> <input type="submit" id="btnAdd" class="btn btn-primary " value="검색 " />				
			</div>
			
		</form>			
	</div>
	<jsp:include page="/footer.jsp" /> 
</div>

</body>
</html>





