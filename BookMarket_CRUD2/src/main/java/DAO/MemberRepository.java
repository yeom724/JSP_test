package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Member;

public class MemberRepository {
	//싱글톤
	private static MemberRepository mr = new MemberRepository();
	private MemberRepository(){};
	public static MemberRepository getInstance() {
		return mr;
	}
	
	//전역변수
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	//멤버를 추가하는 메서드 : CREATE
	public void addMember(Member mb) {
		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			
			//SQL쿼리 전송
			String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mb.getId());
			pstmt.setString(2,mb.getPassword());
			pstmt.setString(3,mb.getName());
			pstmt.setString(4,mb.getGender());
			pstmt.setString(5,mb.getBirth());
			pstmt.setString(6,mb.getMail());
			pstmt.setString(7,mb.getPhone());
			pstmt.setString(8,mb.getAddress());
			pstmt.setTimestamp(9,mb.getRegist_day());
			pstmt.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
	}

	//멤버를 검색하는 메서드 : READ
	public Member getOneObject(String id, String password) {
		Member mb = new Member();
		try {
			//데이터 베이스 연결
			conn = DBConnection.getConnection();
			
			//Query 전송
			String sql = "SELECT * FROM MEMBER WHERE id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mb.setId(rs.getString(1));
				mb.setPassword(rs.getString(2));
				mb.setName(rs.getString(3));
				mb.setGender(rs.getString(4));
				mb.setBirth(rs.getString(5));
				mb.setMail(rs.getString(6));
				mb.setPhone(rs.getString(7));
				mb.setAddress(rs.getString(8));
				mb.setRegist_day(rs.getTimestamp(9));
				return mb;
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}

	//멤버를 업데이터하는 메서드 : UPDATE
	public void updateMember(Member mb) {
		try {
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "UPDATE member SET PASSWORD=?, NAME=?, GENDER=?, BIRTH=?, MAIL=?, PHONE=?, ADDRESS=? WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mb.getPassword());
			pstmt.setString(2,mb.getName());
			pstmt.setString(3,mb.getGender());
			pstmt.setString(4,mb.getBirth());
			pstmt.setString(5,mb.getMail());
			pstmt.setString(6,mb.getPhone());
			pstmt.setString(7,mb.getAddress());
			pstmt.setString(8,mb.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	//멤버를 탈퇴하는 메서드 : DELETE
	public void deleteMember(String id) {
		try {
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "delete from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);	
			pstmt.executeUpdate();
			
		} catch (Exception e) {e.printStackTrace();}
	}
}
