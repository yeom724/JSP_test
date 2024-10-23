package controller;

import java.io.IOException;
import java.net.URLDecoder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/thankYou")
public class thankYou_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("주문완료 페이지로 넘어갑니다.");
		HttpSession session = req.getSession();
		
		String sh_cartId = "";
		String sh_name = "";
		String sh_Date = "";
		String sh_country = "";
		String sh_zip = "";
		String sh_add = "";
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			
			for(int i=0; i<cookies.length; i++) {
				
				Cookie thisCookie = cookies[i];
				String n = thisCookie.getName();
				
				if(n.equals("sh_cartId")) { sh_cartId = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
				if(n.equals("sh_Date")) { sh_Date = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
			}
			
		}
		
		req.setAttribute("sh_cartId", sh_cartId);
		req.setAttribute("sh_Date", sh_Date);
		
		session.removeAttribute("cartlist");
		
		for(int i=0; i<cookies.length; i++) {
			Cookie thisCookie = cookies[i];
			String n = thisCookie.getName();
			
			if(n.equals("sh_cartId")) { thisCookie.setMaxAge(0); }
			if(n.equals("sh_name")) { thisCookie.setMaxAge(0); }
			if(n.equals("sh_Date")) { thisCookie.setMaxAge(0); }
			if(n.equals("sh_country")) { thisCookie.setMaxAge(0); }
			if(n.equals("sh_zip")) { thisCookie.setMaxAge(0); }
			if(n.equals("sh_add")) { thisCookie.setMaxAge(0); }
			
			resp.addCookie(thisCookie);
		}
		
		
		RequestDispatcher rd = req.getRequestDispatcher("thankCustomer.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
