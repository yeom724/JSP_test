package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// controller를 만드는 방법을 순서대로 확인해보자
// Step1 : HttpServlet클래스 상속받기
// Step2 : 오버라이딩 (doGet(), doPost())
// Step3 : 서블릿매핑 (@WebServlet("/"))


@WebServlet("/first")
public class cont1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("첫번째 doGet 실행됨");
		//기존 내용 삭제했음
		
		RequestDispatcher ds = req.getRequestDispatcher("first.jsp");
		ds.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("첫번째 doPost 실행됨");
	}
	
}
