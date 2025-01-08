package Board_Controllers;

import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.BoardRepository;
import DAO.BookRepository;
import DTO.Board;
import DTO.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BoardViewAction")
public class ReadOneBoard_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardRepository br = BoardRepository.getInstance();
		Board board = br.getBoardByNum(num,pageNum);
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("board", board);
		RequestDispatcher rd = req.getRequestDispatcher("board/view.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
