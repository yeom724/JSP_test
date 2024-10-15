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
		RequestDispatcher rd = req.getRequestDispatcher("addbook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("addBook 도착");
		
		String save = req.getServletContext().getRealPath("resources/img");
		System.out.println(save);
		
		MultipartRequest multi = new MultipartRequest(req, save, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		req.setCharacterEncoding("UTF-8");
		
		String bookId = multi.getParameter("bookId");
		String name = multi.getParameter("name");
		String unitPrice = multi.getParameter("unitPrice");
		String author = multi.getParameter("author");
		String publisher = multi.getParameter("publisher");
		String releaseDate = multi.getParameter("releaseDate");
		String description = multi.getParameter("description");
		String category = multi.getParameter("category");
		String unitsInStock = multi.getParameter("unitsInStock");
		String condition = multi.getParameter("condition");
		String filename = multi.getFilesystemName("bookImage");
		
		System.out.println(filename);
		
		int price;
		long stock;
		
		if(unitPrice.isEmpty()) {
			price = 0;
		} else {
			price = Integer.valueOf(unitPrice);
		}
		
		if(unitsInStock.isEmpty()) {
			stock = 0;
		} else {
			stock = Long.valueOf(unitsInStock);
		}
		
		BookRepository dao = BookRepository.getRepository();
		Book newBook = new Book();
		
		newBook.setBookId(bookId);
		newBook.setName(name);
		newBook.setUnitPrice(price);
		newBook.setAuthor(author);
		newBook.setPublisher(publisher);
		newBook.setReleaseDate(releaseDate);
		newBook.setDescription(description);
		newBook.setCategory(category);
		newBook.setUnitsInStock(stock);
		newBook.setCondition(condition);
		newBook.setFilename(filename);
		
		dao.addBook(newBook);
		
		resp.sendRedirect("products");
	}

}
