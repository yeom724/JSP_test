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

@WebServlet("/order")
public class order_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("order창 도착");
		
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
				if(n.equals("sh_name")) { sh_name = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
				if(n.equals("sh_Date")) { sh_Date = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
				if(n.equals("sh_country")) { sh_country = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
				if(n.equals("sh_zip")) { sh_zip = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
				if(n.equals("sh_add")) { sh_add = URLDecoder.decode((thisCookie.getValue()),"utf-8"); }
			}
			
		}
		
		for(int i=0; i<cookies.length; i++) {
			System.out.println(cookies[i].getName());
		}
		
		req.setAttribute("sh_cartId", sh_cartId);
		req.setAttribute("sh_name", sh_name);
		req.setAttribute("sh_Date", sh_Date);
		req.setAttribute("sh_country", sh_country);
		req.setAttribute("sh_zip", sh_zip);
		req.setAttribute("sh_add", sh_add);
		
		RequestDispatcher rd = req.getRequestDispatcher("orderConfirmation.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
