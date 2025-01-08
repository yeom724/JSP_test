package Member_Controllers;

import java.io.IOException;
import java.sql.Timestamp;

import DAO.MemberRepository;
import DTO.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/updateMember")
public class UpdateMember_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("updateMember 폼제공 메서");
		//login하는 폼을 전달하는 메서드 
		
		//전처리 : 전달되는 파라미터가 없음
		
		//모델이동 : 모델에 전달하거나 받아올 데이터가 없음
		
		//뷰이동 : 
		RequestDispatcher rd = req.getRequestDispatcher("member/updateMember.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("/updateMember 멤버 수정 메서드 입장 ");
		//전처리 
		request.setCharacterEncoding("UTF-8"); //한글이 깨지지 않게 설정
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		
		String year = request.getParameter("birthyy");
		String month = request.getParameter("birthmm");
		String day = request.getParameter("birthdd");
		String birth = year + "-" + month + "-" + day; // 위의 변수 3개를 하나의 문자열로 합친다 
		
		String mail1 = request.getParameter("mail1");
		String mail2 = request.getParameterValues("mail2")[0];
		String mail = mail1 + "@" + mail2; // 위의 변수 2개를 하나의 문자열로 합친다. 
		
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Member mb = new Member();
		mb.setId(id);
		mb.setPassword(password);
		mb.setName(name);
		mb.setGender(gender);
		mb.setBirth(birth);
		mb.setMail(mail);
		mb.setPhone(phone);
		mb.setAddress(address);
		//mb.setRegist_day(timestamp);
		
		//모델 이동
		MemberRepository mr = MemberRepository.getInstance();
		mr.updateMember(mb);
		
		//뷰이동 
		RequestDispatcher rd = request.getRequestDispatcher("member/resultMember.jsp?msg=0");
		rd.forward(request, response);
	}

}
