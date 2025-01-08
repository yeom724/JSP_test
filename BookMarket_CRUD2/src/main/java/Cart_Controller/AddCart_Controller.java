package Cart_Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.BookRepository;
import DTO.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addCart")
public class AddCart_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//addCart  이동 메서드 
		
		RequestDispatcher rd = req.getRequestDispatcher("cart/cart.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("add카드 입장 -----------------------------------------------------");
		//전처리 : 전달되는 파라미터 수신 
		String id = req.getParameter("id");
		if (id == null || id.trim().equals("")) {
			resp.sendRedirect("books");
		}
		
		BookRepository dao = BookRepository.getInstance();
		Book goods = dao.getBookById(id);
		
		//세션 생성(로그인)시 생성한 cart를 가져온다. 
		HttpSession session = req.getSession(false);
		if(session == null) {
			resp.sendRedirect("loginMember");
		}
		ArrayList<Book> list = (ArrayList<Book>) session.getAttribute("cart");
		
		int cnt = 0;
		Book goodsQnt = null;
		System.out.println("카트사이즈 : "+list.size());
		for (int i = 0; i < list.size(); i++) {
			goodsQnt = list.get(i);
			if (goodsQnt.getBookId().equals(id)) {
				cnt++;
				int orderQuantity = goodsQnt.getQuantity() + 1;
				goodsQnt.setQuantity(orderQuantity);
			}
		}

		if (cnt == 0) { 
			goods.setQuantity(1);
			list.add(goods);
		}
		//확인
		for (int i = 0; i < list.size(); i++) {
			Book bk = list.get(i);
	
			System.out.println("상품갯수 : "+ bk.getQuantity());
				
			
		}
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		
		//뷰이동 : 폼을 전달하는 뷰로이
		resp.sendRedirect("book?id=" + id);
		
		
	}

}
