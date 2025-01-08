package Book_Controllers;

import java.io.IOException;

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

@WebServlet("/addBook")
public class AddBook_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Book을 추가하는 폼을 전달하는 메서
		
		//전처리 : 전달되는 파라미터가 없음
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		
		//뷰이동 : 폼을 전달하는 뷰로이
		RequestDispatcher rd = req.getRequestDispatcher("book/addBook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String realFolder = req.getServletContext().getRealPath("/resources/images");
		int maxSize = 5 * 1024 * 1024; //최대 업로드될 파일의 크기5Mb
		String encType = "utf-8"; //인코딩 타입
		

		MultipartRequest multi = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());


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

		String fileName = multi.getFilesystemName("bookImage");
		
		
		int price;
		//unitPrice값이 없으면 price 변수에 0을 담고 있다면 정수로 바꿔서 담는다.
		if (unitPrice.isEmpty()) { 
			price = 0;
		}
		else {
			price = Integer.valueOf(unitPrice);			
		}

		long stock;
		// unitsInStock(재고)의 값이 전달되지 않으면 stock 변수에 0을 담고 값이 있을 경우 정수로 바꿔서 stock 변수에 담는다. 
		if (unitsInStock.isEmpty()) {
			stock = 0;
		}
		else {
			stock = Long.valueOf(unitsInStock);	
		}
		
		Book bk = new Book();
		bk.setBookId(bookId);
		bk.setName(name);
		bk.setUnitPrice(price);
		bk.setAuthor(author);
		bk.setDescription(description);
		bk.setPublisher(publisher);
		bk.setCategory(category);
		bk.setUnitsInStock(stock);
		bk.setReleaseDate(releaseDate);
		bk.setCondition(condition);
		bk.setFilename(fileName);
		
		//모델 이동
		BookRepository br = BookRepository.getInstance();
		br.addBook(bk);
		
		//뷰이동 
		resp.sendRedirect("books");
		
		
	}

}
