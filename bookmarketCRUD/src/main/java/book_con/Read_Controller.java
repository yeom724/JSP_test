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

@WebServlet("/products")
public class Read_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("전체상품 컨트롤 페이지 (Read)");
		//전처리
		
		String Edit = req.getParameter("edit");
		
		if(Edit != null) {
			if(Edit.equals("update")) {
				req.setAttribute("edit", Edit);
				System.out.println("업데이트 페이지 진입");
			} else if(Edit.equals("delete")) {
				req.setAttribute("edit", Edit);
				System.out.println("삭제 페이지 진입");
			}
		}
		
		//모델이동 (전체 제품 불러오기)
		BookRepository dao = BookRepository.getRepository();
		ArrayList<Book> arr = dao.getAllBooks();
		
		if(dao==null) { System.out.println("BookRepository 객체 생성 실패"); }
		if(arr==null) { System.out.println("ArrayList null 상태"); }
		
		//뷰 이동
		req.setAttribute("list", arr);
		RequestDispatcher rd = req.getRequestDispatcher("books.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
