package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.member_repository;
import dto.member_dto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/readAll")
public class readAll_controller extends HttpServlet{
// 목표 : 여러개의 dto를 읽어와서 뷰에 출력하는 것
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전처리 : 파라미터 없어서 생략
		
		//모델이동
		member_repository mr = member_repository.getInstance();
		ArrayList<member_dto> arr = mr.getAllmember();
		
		//List형식으로 받아와 리퀘스트에 담은 후 forward 진행
		req.setAttribute("list", arr);
		
		//뷰이동
		RequestDispatcher rd = req.getRequestDispatcher("all.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
