package board_con;

import java.io.IOException;

import dao.BoardRepository;
import dao.BookRepository;
import dto.Board;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BoardViewAction")
public class Read_one_con extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardRepository dao = BoardRepository.getRepository();
		Board bo = dao.getOneBoard(num);
		req.setAttribute("board", bo);
		req.setAttribute("pageNum", pageNum);
		System.out.println("게시물 불러오기 성공");
		
		RequestDispatcher rd = req.getRequestDispatcher("view.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
