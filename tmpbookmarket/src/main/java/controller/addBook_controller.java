package controller;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.connector.Response;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import dao.BookRepository;
import dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddBook")
public class addBook_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("로그인에 성공하여 addbook화면 진입");
		RequestDispatcher rd = req.getRequestDispatcher("addbook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("addBook 도착");
		
		//Step1. 전처리
		String save = req.getServletContext().getRealPath("resources/img");
		System.out.println(save);
		
		MultipartRequest multi = new MultipartRequest(req, save, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		req.setCharacterEncoding("UTF-8");
		
		Book book = new Book();
		
		book.setBookId(multi.getParameter("bookId"));
		book.setName(multi.getParameter("name"));
		book.setAuthor(multi.getParameter("author"));
		book.setPublisher(multi.getParameter("publisher"));
		book.setReleaseDate(multi.getParameter("releaseDate"));
		book.setDescription(multi.getParameter("description"));
		book.setCategory(multi.getParameter("category"));
		book.setCondition(multi.getParameter("condition"));
		book.setFilename(multi.getFilesystemName("bookImage"));
		
		if(multi.getParameter("unitPrice").isEmpty()) {
			book.setUnitPrice(0);
		} else {
			book.setUnitPrice(Integer.valueOf(multi.getParameter("unitPrice")));
		}
		
		if(multi.getParameter("unitsInStock").isEmpty()) {
			book.setUnitsInStock(0);
		} else {
			book.setUnitsInStock(Long.parseLong(multi.getParameter("unitsInStock")));
		}

		
		
		BookRepository dao = BookRepository.getRepository();
		//모델 불러오기
		
		dao.addBook(book);
		
		resp.sendRedirect("products");
	}

}
