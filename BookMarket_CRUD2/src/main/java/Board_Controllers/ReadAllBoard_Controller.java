package Board_Controllers;

import java.io.IOException;
import java.util.ArrayList;

import DAO.BoardRepository;
import DTO.Board;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/BoardListAction")
public class ReadAllBoard_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  	int pageNum=1;
		int limit=5;
		
		if(request.getParameter("pageNum")!=null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}	
		//검색일 경우 파라미터 수신함 
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		
		BoardRepository dao = BoardRepository.getInstance();
		
		int total_record=dao.getListCount(items, text);
		ArrayList<Board> boardlist = new ArrayList<Board>();
		boardlist = dao.getBoardList(pageNum,limit, items, text); 
		
		
		int total_page;
		if (total_record % limit == 0){     
	     	total_page =total_record/limit;
	     	Math.floor(total_page);  
		}
		else{
		   total_page =total_record/limit;
		   Math.floor(total_page); 
		   total_page =  total_page + 1; 
		}		
   
   		request.setAttribute("pageNum", pageNum);		  
   		request.setAttribute("total_page", total_page);   
		request.setAttribute("total_record",total_record); 
		request.setAttribute("boardlist", boardlist);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
		rd.forward(request, response);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글검색하기 
	}

}
