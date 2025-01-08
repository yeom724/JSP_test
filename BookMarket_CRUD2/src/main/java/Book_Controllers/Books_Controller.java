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

@WebServlet("/books")
public class Books_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Book를 통해 모든 책을 출력하는 메서드 
		
		//전처리 : 전달되는 파라미터가 없음
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		BookRepository br = BookRepository.getInstance();
		ArrayList<Book> arr = br.getAllBooks();		
		//뷰이동 : Book DTO들을 출력하는 뷰로이동 
		req.setAttribute("list", arr);
		RequestDispatcher rd = req.getRequestDispatcher("book/books.jsp");
		rd.forward(req, resp);
	}
}
