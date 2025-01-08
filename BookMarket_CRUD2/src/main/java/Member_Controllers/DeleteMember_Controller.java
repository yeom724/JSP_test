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
@WebServlet("/deleteMember")
public class DeleteMember_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("deleteMember 입");
		//회원탈퇴 메서드 
		
		//전처리 : 전달되는 파라미터 수신 
		String id = req.getParameter("id");
		
		//모델이동 : 모델에 가지고 이동 
		MemberRepository mr = MemberRepository.getInstance();
		mr.deleteMember(id);
		//회원을 탈퇴했으므로 세션을 종료하고 로그아웃 시킴 
		HttpSession session = req.getSession(false);
		session.invalidate();
		//뷰이동 : 리다이렉트 홈으로 이
		resp.sendRedirect("/BookMarket_CRUD");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	}

}
