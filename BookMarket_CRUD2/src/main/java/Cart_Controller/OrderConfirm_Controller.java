package Cart_Controller;

import java.io.IOException;
import java.net.URLDecoder;
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

@WebServlet("/orderConfirmation")
public class OrderConfirm_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//addCart  이동 메서드 
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String cartId = session.getId();

		String shipping_cartId = "";
		String shipping_name = "";
		String shipping_shippingDate = "";
		String shipping_country = "";
		String shipping_zipCode = "";
		String shipping_addressName = "";
		
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie thisCookie = cookies[i];
				String n = thisCookie.getName();
				if (n.equals("Shipping_cartId"))
					shipping_cartId = URLDecoder.decode((thisCookie.getValue()), "utf-8");
				if (n.equals("Shipping_name"))
					shipping_name = URLDecoder.decode((thisCookie.getValue()), "utf-8");
				if (n.equals("Shipping_shippingDate"))
					shipping_shippingDate = URLDecoder.decode((thisCookie.getValue()), "utf-8");
				if (n.equals("Shipping_country"))
					shipping_country = URLDecoder.decode((thisCookie.getValue()), "utf-8");
				if (n.equals("Shipping_zipCode"))
					shipping_zipCode = URLDecoder.decode((thisCookie.getValue()), "utf-8");
				if (n.equals("Shipping_addressName"))
					shipping_addressName = URLDecoder.decode((thisCookie.getValue()), "utf-8");
			}
		}
	
		request.setAttribute("shipping_cartId", shipping_cartId);
		request.setAttribute("shipping_name", shipping_name);
		request.setAttribute("shipping_shippingDate",shipping_shippingDate );
		request.setAttribute("shipping_country",shipping_country );
		request.setAttribute("shipping_zipCode",shipping_zipCode );
		request.setAttribute("shipping_addressName", shipping_addressName);
		RequestDispatcher rd = request.getRequestDispatcher("cart/orderConfirmation.jsp");
		rd.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}

}
