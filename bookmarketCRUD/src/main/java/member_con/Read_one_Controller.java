package member_con;

import java.io.IOException;

import dao.BookRepository;
import dao.MemberRepository;
import dto.Book;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet("/member_login")
public class Read_one_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("로그인 화면 진입");
		RequestDispatcher rd = req.getRequestDispatcher("loginMember.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전처리
		System.out.println("로그인을 시작합니다.");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//모델이동
		MemberRepository dao = MemberRepository.getRepository();
		Member dto = dao.getUser(id, password);
		
		//유효성 검사 (DTO가 존재하면 세션생성)
		if(dto != null) {
			
			//세션 생성
			HttpSession session = req.getSession(true);
			session.setAttribute("member", dto);
			
			System.out.println("결과 화면으로 넘어갑니다.");
			RequestDispatcher rd = req.getRequestDispatcher("resultMember.jsp?msg=2");
			rd.forward(req, resp);
		
		} else {
			resp.sendRedirect("member_login?error=1");
		}
		
		
	}
	
}
