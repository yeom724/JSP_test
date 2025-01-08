package Member_Controllers;

import java.io.IOException;
import java.util.ArrayList;

import DAO.MemberRepository;
import DTO.Member;
import DTO.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/loginMember")
public class LoginMember_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/loginmember 멤버 폼 제공 메서드 입장");
		//login하는 폼을 전달하는 메서드 
		String error_code = req.getParameter("error");
		//전처리 : 전달되는 파라미터가 없음
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		
		//뷰이동 : 폼을 전달하는 뷰로이동 
		req.setAttribute("error", error_code);
		RequestDispatcher rd = req.getRequestDispatcher("member/loginMember.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인 처리 프로세스 처리 메서드 입장");
		//전처리 : 전달하는 파라미터를 받
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		MemberRepository mr = MemberRepository.getInstance();
		Member mb = mr.getOneObject(id,password);
		
		//mb 참조변수의 내용이 존재한다면 회원임이 증명됨 
		if(mb !=null) {
			System.out.println("로그인 확인 세션을 생성합니다. ");
			HttpSession session = request.getSession(true); //  세션을 생성함
			session.setAttribute("member", mb);
			//장바구니를 추가해준다.
			ArrayList<Book> cartlist = new ArrayList<Book>();
			session.setAttribute("cart", cartlist);
			RequestDispatcher rd = request.getRequestDispatcher("member/resultMember.jsp?msg=2");
			rd.forward(request, response);
		}else {
			System.out.println("로그인 확인이 안됨 ");
			response.sendRedirect("loginMember?error=1");
		}
		
		
		//뷰이동 : 폼을 전달하는 뷰로이동 
		
		
		
		
		
		
	}

}
