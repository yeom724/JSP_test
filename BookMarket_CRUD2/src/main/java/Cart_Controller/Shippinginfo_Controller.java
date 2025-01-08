package Cart_Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.BookRepository;
import DTO.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/shippingInfo")
public class Shippinginfo_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//addCart  이동 메서드 
		
		RequestDispatcher rd = req.getRequestDispatcher("cart/shippingInfo.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		Cookie cartId = new Cookie("Shipping_cartId", URLEncoder.encode(req.getParameter("cartId"), "utf-8"));
		Cookie name = new Cookie("Shipping_name", URLEncoder.encode(req.getParameter("name"), "utf-8"));
		Cookie shippingDate = new Cookie("Shipping_shippingDate", URLEncoder.encode(req.getParameter("shippingDate"), "utf-8"));
		Cookie country = new Cookie("Shipping_country",	URLEncoder.encode(req.getParameter("country"), "utf-8"));
		Cookie zipCode = new Cookie("Shipping_zipCode", URLEncoder.encode(req.getParameter("zipCode"), "utf-8"));
		Cookie addressName = new Cookie("Shipping_addressName", URLEncoder.encode(req.getParameter("addressName"), "utf-8"));

		cartId.setMaxAge( 24 * 60 * 60);
		name.setMaxAge( 24 * 60 * 60);
		zipCode.setMaxAge( 24 * 60 * 60);
		country.setMaxAge( 24 * 60 * 60);
		addressName.setMaxAge( 24 * 60 * 60);

		resp.addCookie(cartId);
		resp.addCookie(name);
		resp.addCookie(shippingDate);
		resp.addCookie(country);
		resp.addCookie(zipCode);
		resp.addCookie(addressName);
		
		
		resp.sendRedirect("orderConfirmation");
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		
		//뷰이동 : 폼을 전달하는 뷰로이
		
		
		
	}

}
