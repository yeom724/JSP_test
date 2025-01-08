package Member_Controllers;

import java.io.IOException;


import DAO.MemberRepository;
import DTO.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/logoutMember")
public class LogoutMember_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("logout 메서드 입장");
		//login하는 폼을 전달하는 메서드 
		
		//전처리 : 전달되는 파라미터가 없음
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		//뷰이동 : 리다이렉트 홈으로 이
		resp.sendRedirect("/BookMarket_CRUD");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	}

}
