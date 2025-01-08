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



@WebServlet("/BoardWriteAction")
public class AddBoard_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("board/writeForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
								
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));	
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date()); 
		
		board.setHit(0);
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());			
		
		BoardRepository br = BoardRepository.getInstance();
		br.insertBoard(board);
		
        //READ all       
		response.sendRedirect("BoardListAction");
	}

}
