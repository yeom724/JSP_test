package controller;

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
import jakarta.websocket.Session;

@WebServlet("/products")
public class product_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Verify Step 1 : 콘솔창으로 매핑 확인
		System.out.println("/product 매핑");
		
		//전처리
		
		//모델 가져오기
		ArrayList<Book> arr = BookRepository.getAllBooks();
		
		//이동
		req.setAttribute("array", arr);
		//HTML로 데이터를 보내기 위해 키 / 값을 작성
		//여기서 ArrayList타입으로 담은 참조변수 arr값을 보냈지만 받는 곳에서는 Object타입(조상타입)이 되기 때문에 캐스팅이 필요하다.
		RequestDispatcher rd = req.getRequestDispatcher("books.jsp");
		//requset 주소를 들고가기 위해 생성
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
