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
		
		BookRepository bookDA0 = BookRepository.getRepository();
		Book book = bookDA0.getBookById(id);
		
		req.setAttribute("book", book);
		
		RequestDispatcher rd = req.getRequestDispatcher("book.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
