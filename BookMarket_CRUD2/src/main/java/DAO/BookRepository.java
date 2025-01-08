package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Book;


public class BookRepository{
	//싱글
	private static BookRepository instance = new BookRepository();
	private BookRepository() {}
	public static BookRepository getInstance(){
		return instance;
	} 
	
	//전역변수
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	//Read (All DTO)
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "select * from book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book bk = new Book();
				bk.setBookId(rs.getString(1));
				bk.setName(rs.getString(2));
				bk.setUnitPrice(rs.getInt(3));
				bk.setAuthor(rs.getString(4));
				bk.setDescription(rs.getString(5));
				bk.setPublisher(rs.getString(6));
				bk.setCategory(rs.getString(7));
				bk.setUnitsInStock(rs.getLong(8));
				bk.setReleaseDate(rs.getString(9));
				bk.setCondition(rs.getString(10));
				bk.setFilename(rs.getString(11));
				
				listOfBooks.add(bk);
			}
			
			
			
		}catch(Exception e) {e.printStackTrace();}
		
		System.out.println("Book DTO의 갯수는 :"+ listOfBooks.size());
		return listOfBooks;
	}
	
	//Read (DTO)
	public Book getBookById(String bookId) {
		Book bk = new Book();

		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "select * from book where b_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bookId);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				// rs.getString()에서 파라미터를 아래와 같이 순서 혹은 컬럼이름 "b_id"를 선택적으로 사용가
				bk.setBookId(rs.getString(1));
				bk.setName(rs.getString(2));
				bk.setUnitPrice(rs.getInt(3));
				bk.setAuthor(rs.getString(4));
				bk.setDescription(rs.getString(5));
				bk.setPublisher(rs.getString(6));
				bk.setCategory(rs.getString(7));
				bk.setUnitsInStock(rs.getLong(8));
				bk.setReleaseDate(rs.getString(9));
				bk.setCondition(rs.getString(10));
				bk.setFilename(rs.getString(11));	
			}	
		}catch(Exception e) {e.printStackTrace();}
		
		return bk;
	}
	
	//Book 추가 
	public void addBook(Book book) {
		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,book.getBookId());
			pstmt.setString(2,book.getName());
			pstmt.setInt(3,book.getUnitPrice());
			pstmt.setString(4,book.getAuthor());
			pstmt.setString(5,book.getDescription());
			pstmt.setString(6,book.getPublisher());
			pstmt.setString(7,book.getCategory());
			pstmt.setLong(8,book.getUnitsInStock());
			pstmt.setString(9,book.getReleaseDate());
			pstmt.setString(10,book.getCondition());
			pstmt.setString(11,book.getFilename());
			pstmt.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
	}
	//Book 수정
	public void updateBook(Book book) {
		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql=null;
			if (book.getFilename() != null) {
				sql = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=?, b_fileName=? WHERE b_id=?";	
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,book.getName());
				pstmt.setInt(2,book.getUnitPrice());
				pstmt.setString(3,book.getAuthor());
				pstmt.setString(4,book.getDescription());
				pstmt.setString(5,book.getPublisher());
				pstmt.setString(6,book.getCategory());
				pstmt.setLong(7,book.getUnitsInStock());
				pstmt.setString(8,book.getReleaseDate());
				pstmt.setString(9,book.getCondition());
				pstmt.setString(10,book.getFilename());
				pstmt.setString(11,book.getBookId());
			}
			else {
				sql = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=? WHERE b_id=?";	
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,book.getName());
				pstmt.setInt(2,book.getUnitPrice());
				pstmt.setString(3,book.getAuthor());
				pstmt.setString(4,book.getDescription());
				pstmt.setString(5,book.getPublisher());
				pstmt.setString(6,book.getCategory());
				pstmt.setLong(7,book.getUnitsInStock());
				pstmt.setString(8,book.getReleaseDate());
				pstmt.setString(9,book.getCondition());
				pstmt.setString(10,book.getBookId());
			}
			pstmt.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		
		
	}
	//Book 삭제
	public void delBook(String bookId) {
		try {
			//데이터 베이스 연결객체 확보 
			conn = DBConnection.getConnection();
			//SQL쿼리 전송
			String sql = "delete from book where b_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bookId);
			pstmt.executeUpdate();	
		}catch(Exception e) {e.printStackTrace();}
	}

}
