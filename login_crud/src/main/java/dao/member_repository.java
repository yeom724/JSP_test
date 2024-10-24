package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import dto.member_dto;

public class member_repository {
	//1개만 존재(싱글턴)해야 하는 저장소
	
	private static member_repository mr = new member_repository();
	//미리 생성하여 저장
	
	public static member_repository getInstance() {
		return mr;
		//함수로 호출하면 생성되어 있는 주소가 나감
		//싱글턴 방식
	}
	
	//Create
	public void member_create(member_dto dto) {
		
		try {
			//Step1 JDBC 드라이버 로딩
			//특정 클래스를 찾아주는 역할
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step2 connection 객체 생성
			Connection conn = null;
			
			String database = "jdbc:mysql://localhost:3306/login_crud";
			String id = "root";
			String password="1234";
			
			conn = DriverManager.getConnection(database,id,password);
			System.out.println("데이터 베이스 연결 완료");
			
			
		} catch (Exception e) { System.out.println("데이터 베이스 연결 실패(오류)"); }
		
		
		
		//Step3 SQL 전송객체 생성 및 전송
		
		//Step4 리턴이 있다면 ResultSet 객체에 담기 (Create.Update.Delete는 해당없음)
	}
	
	
	//Read
	
	//Update
	
	//Delete
}
