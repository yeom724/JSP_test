package board_con;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dao.BoardRepository;
import dao.MemberRepository;
import dto.Board;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BoardListAction")
public class Read_All_con extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한페이지가 출력할 수 있는 최대 글의 갯수
		int Limit = 5;
		int pageNum = 0;
		System.out.println("board 전처리 진행중...");
		
		String page = req.getParameter("pageNum");
		System.out.println(page);
		
		if(page != null) {
			pageNum = Integer.parseInt(page);
		} else { pageNum = 1; }
		
		//모델이동
		BoardRepository dao = BoardRepository.getRepository();
		
		ArrayList<Board> arr = dao.getAllBoard();
		int total_record = dao.getTotalCount();
		int total_page = 0;
		
		if(total_record % Limit == 0) {
			total_page = total_record/Limit;
		} else {
			total_page = (total_record/Limit)+1; //나머지를 출력할 페이지 추가
		}
		
		req.setAttribute("list", arr); //전체 글
		req.setAttribute("pageNum", pageNum); //현재 페이지 번호
		req.setAttribute("total_record", total_record); //전체 글의 갯수
		req.setAttribute("total_page", total_page); //페이지 갯수
		
		RequestDispatcher rd = req.getRequestDispatcher("list.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("회원정보 도착");
		
		
		System.out.println("홈으로 돌아갑니다.");
		resp.sendRedirect("/bookmarketCRUD"); //홈으로
		
	}
	
}
