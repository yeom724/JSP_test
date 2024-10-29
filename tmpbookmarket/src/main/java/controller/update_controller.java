package controller;

import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BookRepository;
import dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class update_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("업데이트 도착");
		
		String id = req.getParameter("id");
		
		BookRepository dao = BookRepository.getRepository(); //모델 가져오기
		Book book = new Book();
		book = dao.getBookOne(id); //전달된 정보 저장
		
		req.setAttribute("book", book);
		
		RequestDispatcher rd = req.getRequestDispatcher("updateBook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("수정 파일 도착");
		
		//form 데이터에 파일이 있기 때문에 MultipartRequeset 객체를 사용해야함
		String save = req.getServletContext().getRealPath("resources/img");
		MultipartRequest multi = new MultipartRequest(req, save, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		Book book = new Book(); //모델로 보내기 위해서 묶음처리 진행
		BookRepository dao = BookRepository.getRepository(); //모델생성
		
		
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
		
		dao.bookUpdate(book);
		
		resp.sendRedirect("products");
		
	}

}
