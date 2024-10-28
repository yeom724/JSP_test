package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;

import dto.member_dto;

public class member_repository {
	//1개만 존재(싱글턴)해야 하는 저장소
	private static member_repository mr = new member_repository();
	//미리 생성하여 저장
	
	public static member_repository getInstance(){
		return mr;
		//함수로 호출하면 생성되어 있는 주소가 나감
		//싱글턴 방식
	}
	
	//예외를 try catch로 감싸거나 throws로 예외 던지기
	private Connection DBconn() throws Exception{
		//Step1 JDBC 드라이버 로딩
		//특정 클래스를 찾아주는 역할
		Class.forName("com.mysql.jdbc.Driver");
		
		//Step2 connection 객체 생성 (확인할 것, !데이터베이스 생성여부! !WEB-INF/lib 폴더에 jar파일 있는지 확인!)
		
		String database = "jdbc:mysql://localhost:3306/login_crud";
		String id = "root";
		String password="1234";
		
		Connection conn = DriverManager.getConnection(database,id,password);
		System.out.println("데이터 베이스 연결 완료");
		
		return conn;
		//create와 read 동일하게 연결을 진행하기 때문에 코드로 따로 작성
		//!똑같은 코드가 있기 때문에 함수처리를 진행하면 좋음!
	}
	
	
	
	//Create / CR
	public void member_create(member_dto dto) {
		
		try {
			Connection conn = DBconn();
			//Connection타입으로 받아와서 사용함
			
			//SQL쿼리를 전송해야 됨 : 데이터 베이스에 데이터를 삽입할 차례
			//Step3 SQL 전송객체 생성 및 전송
			Statement stmt = conn.createStatement(); //java.sql.Statement import할 것
			
			String user_id = dto.getId();
			String pw = dto.getPw();
			int age = dto.getAge();
			
			// 예시) "insert into member values('admin', '1234', 7)" : 워크벤치에서 테스트 실행
			// 변수사용) "insert into member values('user_id', 'pw', age)" : 변수 부분과 문자열 부분 분리 진행
			
			String sql = "insert into member values('" + user_id + "','"+ pw +"'," + age + ")";
			stmt.executeUpdate(sql);
			//업데이트를 진행
			
			System.out.println("데이터 베이스 업로드 완료");
			
		} catch (Exception e) { System.out.println("데이터 베이스 연결 실패(오류)"); }
		
		//Step4 리턴이 있다면 ResultSet 객체에 담기 (Create.Update.Delete는 해당없음)
		String sql2 = "insert into member values('"
				+ "admin"
				+ "', '"
				+ "1234"
				+ "', "
				+ "7"
				+ ")"; //엔터치면 자동으로 결합자 생성해줌
	}
	
	
	//Read
	public ArrayList<member_dto> getAllmember(){
		
		ArrayList<member_dto> arr = new ArrayList<member_dto>();
		
		ResultSet rs = null;
		//데이터베이스가 보내주는 데이터를 받을 장소
		
		try {
			Connection conn = DBconn();
			Statement stmt = conn.createStatement();
			String sql = "select * from member";
			
			rs = stmt.executeQuery(sql);
			//데이터베이스에서 ResultSet이라는 객체에 담아 Java로 전송해줌
		
		
			//다음행이 있으면 True
			while(rs.next()) {
				
				String id = rs.getString("id"); //컬럼명
				String pw = rs.getString("pw");
				int age = rs.getInt("age");
				
				member_dto dto = new member_dto();
				dto.setAge(age);
				//dto.setAge(rs.getString("id")); 바로 담아도 됨
				dto.setId(id);
				dto.setPw(pw);
				
				arr.add(dto);
				
			}
		} catch(Exception e) { System.out.println("불러오기 실패"); }
		
		return arr;
	}
	
	
	//Update
	public void update_member(member_dto dto) {
		
		try {
			
			Connection conn = DBconn();
			Statement stmt = conn.createStatement();
			
			String sql = "update member set pw='"+ dto.getPw() + "',age="+ dto.getAge() + " where id='"+ dto.getId() + "'";
			System.out.println(sql);
			
			stmt.executeUpdate(sql);
			//업데이트
			
			
		} catch(Exception e) { System.out.println("수정실패"); }
		
	}
	
	//Read
 	public member_dto getOnemember(String id) {
		
		ResultSet rs = null;
		member_dto dto = new member_dto();
		
		try {
			//Step1 DB연결
			Connection conn = DBconn();
			
			//Step 2 Query 전송 및 실행
			Statement stmt = conn.createStatement();
			String sql = "select * from member where id='"+id+"'";
			System.out.println(sql);
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println("if진입");
				
				String userid = rs.getString("id");
				String pw = rs.getString("pw");
				int age = rs.getInt("age");
				
				dto.setAge(age);
				dto.setId(userid);
				dto.setPw(pw);
			}
			
		}
		catch(Exception e) { System.out.println("수정실패"); }
		
		return dto;
	}
	
	//Delete / DR
 	public void deleteUser(String id) {
		
		try {
			//Step 1 DB연결
			Connection conn = DBconn();
			
			//Step 2 Query 전송 및 실행
			Statement stmt = conn.createStatement();
			String sql = "delete from member where id='"+id+"'";
			//작은 따옴표 주의할 것
			System.out.println(sql);
			
			stmt.executeUpdate(sql);
			System.out.println("삭제 완료");

		} catch (Exception e) { System.out.println("삭제실패"); }
	}
}
