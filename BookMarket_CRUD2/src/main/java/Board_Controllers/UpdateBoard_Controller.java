package Board_Controllers;

import java.io.IOException;


import DAO.BoardRepository;

import DTO.Board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/BoardUpdateAction")
public class UpdateBoard_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("board/writeForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
								
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
			
		
		Board board = new Board();		
		board.setNum(num);
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));		
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date()); 
		 
		board.setHit(0);
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());			
		
		BoardRepository dao = BoardRepository.getInstance();
		dao.updateBoard(board);
        //READ all       
		response.sendRedirect("BoardListAction");
	}

}
