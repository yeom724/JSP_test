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

@WebServlet("/removeCart")
public class RemoveCart_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Cart에서 아이템을 삭제 
		String id = req.getParameter("id");
		HttpSession session = req.getSession(false);
		
		ArrayList<Book> cartlist = (ArrayList<Book>) session.getAttribute("cart");
		System.out.println("삭제프로스세 DTO : "+cartlist.size());
		for(int i=0; i<cartlist.size();i++) {
			Book bk = cartlist.get(i);
			String bookId = bk.getBookId();
			if(bookId.equals(id)) {
				cartlist.remove(i);
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("addCart");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
