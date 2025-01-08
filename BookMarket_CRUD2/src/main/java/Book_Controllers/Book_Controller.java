package Book_Controllers;

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

@WebServlet("/book")
public class Book_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Book을 통해 선택된 책을 출력하는 메서드 
		
		//전처리 : book id를 가져온다 
		String id = req.getParameter("id");
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		BookRepository br = BookRepository.getInstance();
		Book bk = br.getBookById(id);		
		
		//뷰이동 : Book DTO들을 출력하는 뷰로이동 
		req.setAttribute("book", bk);
		RequestDispatcher rd = req.getRequestDispatcher("book/book.jsp");
		rd.forward(req, resp);
	}
}
