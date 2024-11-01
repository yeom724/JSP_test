package board_con;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardRepository;
import dao.BookRepository;
import dto.Board;
import dto.Book;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BoardWriteForm")
public class Create_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardWrite 진입");
		
		HttpSession session = req.getSession(false);
		System.out.println("세션여부" + session);
		
		if(session == null) {
			System.out.println("세션이 존재하지 않습니다.");
			resp.sendRedirect("member_login");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("writeForm.jsp");
			rd.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("post 게시물 새로운 내용 도착");
		
		//전처리
		Board dto = new Board();
		
		HttpSession session = req.getSession(false);
		Member mb = (Member) session.getAttribute("member");
		
		String id = mb.getId();
		String name = mb.getName();
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		
		Date currentDatetime = new Date(System.currentTimeMillis());						//현재시간
		java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());				//현재시간
		java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());	//현재시간 이어져잇오...
		
		int hit = 0;
		String ip = req.getRemoteAddr();
		
		dto.setId(id);
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setRegist_day(timestamp);
		dto.setHit(hit);
		dto.setIp(ip);
		
		System.out.println("게시물 업로드 함수로 이동합니다.");
		//모델이동
		BoardRepository br = BoardRepository.getRepository();
		br.boardCreate(dto);
		
		//뷰이동
		resp.sendRedirect("BoardListAction");
		
	}

}
