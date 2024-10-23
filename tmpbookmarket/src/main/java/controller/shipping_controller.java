package controller;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/shipping")
public class shipping_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("shippinginfo.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("Shipping post 도착");
		
		Cookie cartId = new Cookie("sh_cartId", URLEncoder.encode(req.getParameter("cartId"),"UTF-8"));
		Cookie name = new Cookie("sh_name", URLEncoder.encode(req.getParameter("name"),"UTF-8"));
		Cookie shippingDate = new Cookie("sh_Date", URLEncoder.encode(req.getParameter("shippingDate"),"UTF-8"));
		Cookie country = new Cookie("sh_country", URLEncoder.encode(req.getParameter("country"),"UTF-8"));
		Cookie zipCode = new Cookie("sh_zip", URLEncoder.encode(req.getParameter("zipCode"),"UTF-8"));
		Cookie addressName = new Cookie("sh_add", URLEncoder.encode(req.getParameter("addressName"),"UTF-8"));
		
		cartId.setMaxAge(24 * 60 * 60);
		name.setMaxAge(24 * 60 * 60);
		zipCode.setMaxAge(24 * 60 * 60);
		country.setMaxAge(24 * 60 * 60);
		addressName.setMaxAge(24 * 60 * 60);
		
		resp.addCookie(cartId);
		resp.addCookie(name);
		resp.addCookie(shippingDate);
		resp.addCookie(country);
		resp.addCookie(zipCode);
		resp.addCookie(addressName);
		
		resp.sendRedirect("order");
	}

}
