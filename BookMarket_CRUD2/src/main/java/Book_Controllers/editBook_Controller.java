package Book_Controllers;

import java.io.IOException;
import java.util.ArrayList;
import DAO.BookRepository;
import DTO.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editBook")
public class editBook_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Book을 수정하는 메서드 
		
		//전처리 : 전달되는 파라미터가 없음
		String edit = req.getParameter("edit");
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		BookRepository br = BookRepository.getInstance();
		ArrayList<Book> arr = br.getAllBooks();
		//뷰이동
		req.setAttribute("list", arr);
		req.setAttribute("edit", edit);
		RequestDispatcher rd = req.getRequestDispatcher("book/editBook.jsp");
		rd.forward(req, resp);
	}
}
