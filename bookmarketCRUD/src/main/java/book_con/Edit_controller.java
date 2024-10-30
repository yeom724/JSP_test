package book_con;

import java.io.IOException;
import java.util.ArrayList;

import dao.BookRepository;
import dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editpage")
public class Edit_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("edit doget() 들어옴");
		
		//전처리
		String edit = req.getParameter("edit");
		
		BookRepository dao = BookRepository.getRepository();
		ArrayList<Book> arr = dao.getAllBooks();
		
		req.setAttribute("edit", edit);
		req.setAttribute("list", arr);
		RequestDispatcher rd = req.getRequestDispatcher("editpage.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
