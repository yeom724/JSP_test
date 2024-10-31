package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberRepository {
	//싱글톤
	
	private static MemberRepository mr = new MemberRepository();
	
	private MemberRepository() {}

	public static MemberRepository getRepository() {
		return mr;
	}
	
	private Connection DBconn() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/bookmarketdb";
			String user = "root";
			String pw = "1234";
			
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("데이터 베이스 연결 성공");
		} catch(Exception e) {System.out.println("데이터 베이스 연결 실패");}
		
		return conn;
		
	}
	
	//Create
	public void create(Member dto) {
		
		//데이터베이스 연결
		Connection conn = DBconn();
		PreparedStatement ps = null;
		
		try {
			
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql); //명령어 전달먼저!
			
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getBirth());
			ps.setString(6, dto.getMail());
			ps.setString(7, dto.getPhone());
			ps.setString(8, dto.getAddress());
			ps.setTimestamp(9, dto.getRegist_day()); //String 타입으로 저장되지만 타임객체도 알아서 들어감
			ps.executeUpdate(); //실행
			System.out.println("회원 정보가 생성되었습니다.");
			
		} catch(Exception e) { System.out.println("멤버 정보 업로드 실패"); }
		
		finally {
			try {
				
				if(ps != null) { ps.close(); }
				if(conn != null) { conn.close(); }
				
			} catch (Exception e2) { System.out.println("SQL리소스 닫기 실패"); }
		}
		
	}
	
	//Read
	public Member getUser(String id, String pw) {
		
		Member mb = null;
		
		//데이터 베이스 연결, SQL 명령문 전달, SQL데이터 받는용
		Connection conn = DBconn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from member where id=? and password=?";
			//두가지 모두 일치해야하기 때문에 둘 다 조회함
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			System.out.println("전송완료");
			
			if(rs.next()) {
				System.out.println("일치하는 회원 발견!");
				
				mb = new Member();
				mb.setId(rs.getString("id"));
				mb.setPassword(rs.getString("password"));
				mb.setName(rs.getString("name"));
				mb.setGender(rs.getString("gender"));
				mb.setBirth(rs.getString("birth"));
				mb.setMail(rs.getString("mail"));
				mb.setPhone(rs.getString("phone"));
				mb.setAddress(rs.getString("address"));
				mb.setRegist_day(rs.getTimestamp("regist_day"));
				
				
			} else {
				System.out.println("그런 회원 없습니다.");
			}
			
		} catch(SQLException e) { System.out.println("안되는데용?"); }
		
		finally {
			try {
				
				if(rs != null) { rs.close(); }
				if(ps != null) { ps.close(); }
				if(conn != null) { conn.close(); }
				
			} catch (Exception e2) { System.out.println("SQL리소스 닫기 실패"); }
		}
		
		
		return mb;
	}
	
	//Update
	public void update(Member dto) {
		
		Connection conn = DBconn();
		PreparedStatement ps = null;
		
		try {
			
			String sql = "update member set password=?, name=?, gender=?, birth=?, mail=?, phone=?, address=?, regist_day=? where id=?";
			ps = conn.prepareStatement(sql); //명령어 전달먼저!
			
			ps.setString(9, dto.getId());
			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getGender());
			ps.setString(4, dto.getBirth());
			ps.setString(5, dto.getMail());
			ps.setString(6, dto.getPhone());
			ps.setString(7, dto.getAddress());
			ps.setTimestamp(8, dto.getRegist_day()); //String 타입으로 저장되지만 타임객체도 알아서 들어감
			ps.executeUpdate(); //실행
			System.out.println("회원 정보가 갱신되었습니다.");
			
		} catch(Exception e) { }
		
		finally {
			try {
				
				if(ps != null) { ps.close(); }
				if(conn != null) { conn.close(); }
				
			} catch (Exception e2) { System.out.println("SQL리소스 닫기 실패"); }
		}
		
	}
	
	
	//Delete
	
}
