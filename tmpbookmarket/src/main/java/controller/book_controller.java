package controller;

import java.io.IOException;
import java.util.ArrayList;

import dto.Book;
import dao.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class book_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/book 매핑");
		
		String id = req.getParameter("id");
		
		//저장소 생성 후, 아이디 조회하여 하나의 Book 객체를 빼오는 함수를 실행
		BookRepository bookDA0 = BookRepository.getRepository();
		Book book = bookDA0.getBookById(id);
		
		//해당 객체만 페이지로 전송
		req.setAttribute("book", book);
		
		RequestDispatcher rd = req.getRequestDispatcher("book.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
