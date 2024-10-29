package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.BookRepository;
import dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet("/addCart")
public class addCart_controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("addBook-get 도착");
		
		RequestDispatcher rd;
		
		HttpSession session = req.getSession(false);
		//엥
		
		String id = req.getParameter("id");
		//장바구니에 추가할 때 책의 고유 ID를 값으로 받아온다.
		
		//고유 ID값이 없거나 비어있으면 책 목록으로 돌려보낸다. (유효성 검사1)
		if(id == null || id.trim().equals("")) {
			System.out.println("파라미터 없음");
			rd = req.getRequestDispatcher("products");
			rd.forward(req, resp);
			return;
			
		}
		
		BookRepository dao = BookRepository.getRepository();
		//모델 객체 생성
		System.out.println(dao);
		
		Book book = dao.getBookOne(id);
		//모델에서 해당 아이디를 가지고 있는 책을 가져와 담음
		System.out.println(id);
		System.out.println(book);
		
		//해당 아이디를 가지고 있지 않아 null이 발생한 경우 에러페이지를 보냄. (유효성 검사2)
		if(book == null) {
			System.out.println("책 아이디 없음");
			rd = req.getRequestDispatcher("error01");
			rd.forward(req, resp);
			return;
		}
		
		ArrayList<Book> goodsList = dao.getAllBooks();
		//모델에서 전체 항목을 List로 가져옴 (!원본파일을 그대로 들고옴!)
		
		Book goods = new Book();
		//원본파일에서 제품을 복사하여 받아오기 위해 객체를 새로 생성함
		//여기서 객체 생성 없이 그냥 담게 되면 원본파일을 가져오기 때문에 원본이 변할 가능성이 있음
		
		for(int i=0; i<goodsList.size(); i++) {
			
			goods = goodsList.get(i); //옮겨담음
			
			//객체가 가진 ID값과, 파라미터로 전송받은 ID값이 일치하는지 확인하며 반복문 실행
			if(goods.getBookId().equals(id)) {
				System.out.println("일치하는 아이디 발견, 반복종료");
				break;
				//ID값이 일치하면 반복문 종료 후 빠져나옴.
				//반복문이 종료된 시점에 goods에 담긴 객체는 유지됨.
			}
		}
		
		ArrayList<Book> list = (ArrayList<Book>) session.getAttribute("cartlist");
		//세션이 가지고 있는 장바구니 항목을 가져옴, 존재하지 않으면 if문으로 새로운 장바구니를 개설함
		
		if(list==null) {
			list = new ArrayList<Book>();
			session.setAttribute("cartlist", list);
			System.out.println("첫 장바구니 생성");
		}
		
		int count = 0;
		Book goodsQnt = new Book(); //장바구니에 추가하기 위한 객체 생성
		
		for(int i=0; i<list.size(); i++) {
			
			goodsQnt = list.get(i);
			System.out.println("for문 : " + goodsQnt.getBookId().equals(id));
			System.out.println(goodsQnt.getBookId());
			
			//장바구니에 들어있는 아이디와 파라미터로 받은 값이 일치하면 수량을 1 증가시킴
			if(goodsQnt.getBookId().equals(id)) {
				count++;
				
				int order = goodsQnt.getQuantity() + 1;
				goodsQnt.setQuantity(order);
				System.out.println("이미 존재하는 상품입니다, 갯수를 추가합니다.");
			}
			
		}
		
		//반복문을 돌리지 못하고 빠져나오고, 초기 count값이 0이면 장바구니에 제품을 추가함.
		if(count==0) {
			goods.setQuantity(1);
			list.add(goods);
			System.out.println("처음 담는 상품입니다.");
			System.out.println(goods.getBookId());
		}
		
		
		rd = req.getRequestDispatcher("book?id="+id);
		rd.forward(req, resp);
		//resp.sendRedirect("book?id="+id);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("addBook-get 도착");
		RequestDispatcher rd;
		
		HttpSession session = req.getSession(false);
		//엥
		
		String id = req.getParameter("id");
		//장바구니에 추가할 때 책의 고유 ID를 값으로 받아온다.
		
		//고유 ID값이 없거나 비어있으면 책 목록으로 돌려보낸다. (유효성 검사1)
		if(id == null || id.trim().equals("")) {
			System.out.println("파라미터 없음");
			//rd = req.getRequestDispatcher("products");
			//rd.forward(req, resp);
			resp.sendRedirect("products");
			return;
			
		}
		
		BookRepository dao = BookRepository.getRepository();
		//모델 객체 생성
		System.out.println(dao);
		
		Book book = dao.getBookOne(id);
		//모델에서 해당 아이디를 가지고 있는 책을 가져와 담음
		System.out.println(id);
		System.out.println(book);
		
		//해당 아이디를 가지고 있지 않아 null이 발생한 경우 에러페이지를 보냄. (유효성 검사2)
		if(book == null) {
			System.out.println("책 아이디 없음");
			//rd = req.getRequestDispatcher("error01");
			//rd.forward(req, resp);
			resp.sendRedirect("error01");
			return;
		}
		
		ArrayList<Book> goodsList = dao.getAllBooks();
		//모델에서 전체 항목을 List로 가져옴 (!원본파일을 그대로 들고옴!)
		
		Book goods = new Book();
		//원본파일에서 제품을 복사하여 받아오기 위해 객체를 새로 생성함
		//여기서 객체 생성 없이 그냥 담게 되면 원본파일을 가져오기 때문에 원본이 변할 가능성이 있음
		
		for(int i=0; i<goodsList.size(); i++) {
			
			goods = goodsList.get(i); //옮겨담음
			
			//객체가 가진 ID값과, 파라미터로 전송받은 ID값이 일치하는지 확인하며 반복문 실행
			if(goods.getBookId().equals(id)) {
				System.out.println("일치하는 아이디 발견, 반복종료");
				break;
				//ID값이 일치하면 반복문 종료 후 빠져나옴.
				//반복문이 종료된 시점에 goods에 담긴 객체는 유지됨.
			}
		}
		
		ArrayList<Book> list = (ArrayList<Book>) session.getAttribute("cartlist");
		//세션이 가지고 있는 장바구니 항목을 가져옴, 존재하지 않으면 if문으로 새로운 장바구니를 개설함
		
		if(list==null) {
			list = new ArrayList<Book>();
			session.setAttribute("cartlist", list);
			System.out.println("첫 장바구니 생성");
		}
		
		int count = 0;
		Book goodsQnt = new Book(); //장바구니에 추가하기 위한 객체 생성
		
		for(int i=0; i<list.size(); i++) {
			
			goodsQnt = list.get(i);
			System.out.println("for문 : " + goodsQnt.getBookId().equals(id));
			System.out.println(goodsQnt.getBookId());
			
			//장바구니에 들어있는 아이디와 파라미터로 받은 값이 일치하면 수량을 1 증가시킴
			if(goodsQnt.getBookId().equals(id)) {
				count++;
				
				int order = goodsQnt.getQuantity() + 1;
				goodsQnt.setQuantity(order);
				System.out.println("이미 존재하는 상품입니다, 갯수를 추가합니다.");
			}
			
		}
		
		//반복문을 돌리지 못하고 빠져나오고, 초기 count값이 0이면 장바구니에 제품을 추가함.
		if(count==0) {
			goods.setQuantity(1);
			list.add(goods);
			System.out.println("처음 담는 상품입니다.");
			System.out.println(goods.getBookId());
		}
		
		
		resp.sendRedirect("book?id="+id);
		
	}

}
