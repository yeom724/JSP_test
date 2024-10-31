package member_con;

import java.io.IOException;
import java.util.Date;

import dao.MemberRepository;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member_add")
public class Create_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("addMember.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("회원정보 도착");
		
		//전처리
		req.setCharacterEncoding("UTF-8");

		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String year = req.getParameter("birthyy");
		String month = req.getParameterValues("birthmm")[0];
		String day = req.getParameter("birthdd");
		String birth = year + "/" + month + "/" + day;
		String mail1 = req.getParameter("mail1");
		String mail2 = req.getParameterValues("mail2")[0];
		String mail = mail1 + "@" + mail2;
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");

		Date currentDatetime = new Date(System.currentTimeMillis());						//현재시간
		java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());				//현재시간
		java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());	//현재시간
		
		Member mb = new Member();
		
		mb.setId(id);
		mb.setPassword(password);
		mb.setName(name);
		mb.setGender(gender);
		mb.setBirth(birth);
		mb.setMail(mail);
		mb.setPhone(phone);
		mb.setAddress(address);
		mb.setRegist_day(timestamp);
		
		//모델이동
		MemberRepository dao = MemberRepository.getRepository();
		System.out.println("데이터 베이스로 이동합니다.");
		dao.create(mb);
		
		System.out.println("홈으로 돌아갑니다.");
		resp.sendRedirect("/bookmarketCRUD"); //홈으로
		
	}
	
}
