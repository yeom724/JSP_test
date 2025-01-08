<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="DTO.Book"%>
<%@ page import="DAO.BookRepository"%>
<%
	request.setCharacterEncoding("UTF-8");

	String cartId = session.getId();

	String shipping_cartId = (String)request.getAttribute("shipping_cartId");
	String shipping_name = (String)request.getAttribute("shipping_name");
	String shipping_shippingDate = (String)request.getAttribute("shipping_shippingDate");
	String shipping_country = (String)request.getAttribute("shipping_country");
	String shipping_zipCode = (String)request.getAttribute("shipping_zipCode");
	String shipping_addressName = (String)request.getAttribute("shipping_addressName");


%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>주문 정보</title>
</head>
<body>

<div class="container py-4">
	<jsp:include page="/menu.jsp" />	

   <div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">주문 정보</h1>
        <p class="col-md-8 fs-4">Order Info</p>      
      </div>
    </div>
  	
  	
  		 <div class="row align-items-md-stretch alert alert-info">	 	
	
	
		<div class="text-center ">
			<h1>영수증</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				<strong>배송 주소</strong> <br> 성명 : <% out.println(shipping_name); %><br> 
				우편번호 : <% 	out.println(shipping_zipCode);%><br> 
				주소 : <%	out.println(shipping_addressName);%>(<%	out.println(shipping_country);%>)<br>
			</div>
			<div class="col-4" align="right">
				<p>	<em>배송일: <% out.println(shipping_shippingDate);	%></em>
			</div>
		</div>
		<div class=" py-5">
			<table class="table table-hover">			
			<tr>
				<th class="text-center">도서</th>
				<th class="text-center">#</th>
				<th class="text-center">가격</th>
				<th class="text-center">소계</th>
			</tr>
			<%
				int sum = 0;
				ArrayList<Book> cartList = (ArrayList<Book>) session.getAttribute("cart");
				if (cartList == null)
					cartList = new ArrayList<Book>();
				for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
					Book book = cartList.get(i);
					int total = book.getUnitPrice() * book.getQuantity();
					sum = sum + total;
			%>
			<tr>
				<td class="text-center"><em><%=book.getName()%> </em></td>
				<td class="text-center"><%=book.getQuantity()%></td>
				<td class="text-center"><%=book.getUnitPrice()%>원</td>
				<td class="text-center"><%=total%>원</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td> </td>
				<td> </td>
				<td class="text-right">	<strong>총액: </strong></td>
				<td class="text-center text-danger"><strong><%=sum%> </strong></td>
			</tr>
			</table>			
				<a href="./ShippingInfo.jsp?cartId=<%=shipping_cartId%>"class="btn btn-secondary" role="button"> 이전 </a>
				<a href="cart/thankCustomer.jsp"  class="btn btn-success" role="button"> 주문 완료 </a>
				<a href="cart/checkOutCancelled.jsp" class="btn btn-secondary"	role="button"> 취소 </a>			
		</div>
	</div>
	</div>	
		<jsp:include page="/footer.jsp" /> 	
</body>
</html>
