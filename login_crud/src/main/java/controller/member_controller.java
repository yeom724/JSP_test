package controller;

import java.io.IOException;

import dao.member_repository;
import dto.member_dto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class member_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("join.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		//Step01 전처리
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		int age = Integer.parseInt(req.getParameter("age"));
		//문자열을 정수형으로 반환
		
		//데이터 묶음처리
		member_dto dto = new member_dto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setAge(age);
		
		
		//Step02 모델이동
		member_repository mr = member_repository.getInstance();
		mr.member_create(dto);
		
		//Step03 뷰이동
	}

}
