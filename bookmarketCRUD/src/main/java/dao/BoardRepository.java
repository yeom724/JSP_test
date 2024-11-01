package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Board;

public class BoardRepository {
	//싱글톤
	
	private static BoardRepository dao = new BoardRepository();
	
	private BoardRepository() {}
	
	public static BoardRepository getRepository() {
		return dao;
	}
	
	//데이터베이스 연결
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
	
	//Connection conn = DBconn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	//귀찮아서 모두 전역변수 해버림 ㅎ
	
	
	
	
	//행의 갯수를 리턴함
	public int getTotalCount() {
		int count = 0;
		Connection conn = DBconn();

		try {
			
			String sql = "Select count(*) from board";
			//데이터베이스 개수를 가져오는 명령문 count()는 함수
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); //컬럼명 대신 순서를 작성해도 된다.
			}
			
		}catch(Exception e) { }
		
		
		
		return count;
	}

	
	//C
	public void boardCreate(Board bo) {
		System.out.println("게시물 함수에 도착");
		//데이터 베이스 연결
		Connection conn = DBconn();
		
		try {
			
			String sql = "insert into board(id,name,subject,content,regist_day,hit,ip) value(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bo.getId());
			ps.setString(2, bo.getName());
			ps.setString(3, bo.getSubject());
			ps.setString(4, bo.getContent());
			ps.setTimestamp(5, bo.getRegist_day());
			ps.setInt(6, bo.getHit());
			ps.setString(7, bo.getIp());
			
			System.out.println("명령어 전송중...");
			ps.executeUpdate();
			
			
		} catch(Exception e) { System.out.println("게시물 업로드 실패"); }
		
		
		//쿼리전송
		
		//ResultSet 필요없음
		
		
	}
	
	//Read All
	public ArrayList<Board> getAllBoard() {
		
		ArrayList<Board> arr = new ArrayList<Board>();
		
		//데이터베이스 연결
		Connection conn = DBconn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL전송
		try {
			
			String sql = "select * from board";
			ps = conn.prepareStatement(sql); //명령어 전달
			rs = ps.executeQuery(); //목록 가져오기
			
			while(rs.next()) {
				
				Board bo = new Board();
				
				bo.setNum(rs.getInt("num"));
				bo.setId(rs.getString("id"));
				bo.setName(rs.getString("name"));
				bo.setSubject(rs.getString("subject"));
				bo.setContent(rs.getString("content"));
				bo.setRegist_day(rs.getTimestamp("regist_day"));
				bo.setHit(rs.getInt("hit"));
				bo.setIp(rs.getString("ip"));
				
				arr.add(bo);
				
			}
			
			
		} catch(Exception e) { System.out.println("안되는디..."); }
		
		//ResultSet 반환
	
		return arr;
		
	}
	
	//Read One
	public Board getOneBoard(int num) {
		Board bo = null;
		
		Connection conn = DBconn();
		
		try {
			
			String sql = "select * from board where num=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				sql = "update board set hit=hit+1 where num=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, num);
				ps.executeUpdate();
				
				bo = new Board();
				
				bo.setNum(rs.getInt(1));
				bo.setId(rs.getString(2));
				bo.setName(rs.getString(3));
				bo.setSubject(rs.getString(4));
				bo.setContent(rs.getString(5));
				bo.setRegist_day(rs.getTimestamp(6));
				bo.setHit(rs.getInt(7));
				bo.setIp(rs.getString(8));
				System.out.println("게시물 로드 완료");
			}
			
			
		} catch(Exception e) { System.out.println("한 게시물 가져오기 실패"); }
		
		
		return bo;
	}
	
	
	//U
	
	//D
}
