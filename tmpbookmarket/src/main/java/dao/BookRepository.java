package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Book;

public class BookRepository {
	
	private static ArrayList<Book> listOfBooks = new ArrayList<Book>();
	//Book 객체를 담는 배열객체 - 저장소 변수
	
	private static BookRepository repository = new BookRepository();
	//생성자 호출을 미리 해둠
	//private - 외부에서 접근을 막기 때문에, 함수도 같이 생성되나 접근할 수 없음
	
	public static BookRepository getRepository() {
		return repository;
		//생성된 객체를 반환
	}
	
	//데이터 베이스 연결
	private static Connection DBconn() {
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
	
	//생성자를 만들면 ArrayList에 값이 담기게 됨
	public BookRepository() {
		
		Book book1 = new Book("ISBN1234", "C# 프로그래밍", 27000);
		book1.setAuthor("우재남");
		book1.setDescription("C#을 처음 접하는 독자를 대상으로 1:1 수업처럼 자세히 설명한 책이다. 꼭 알아야 할 핵심 개념은 기본 예제로 최대한 쉽게 설명했으며, 중요한 내용은 응용 예제, 퀴즈, 셀프 스터디, 예제 모음으로 한번 더 복습할 수 있다.");
		book1.setPublisher("한빛아카데미");
		book1.setCategory("IT모바일");
		book1.setUnitsInStock(1000);
		book1.setReleaseDate("2022/10/06");
		book1.setFilename("ISBN1234.jpg");
		
		Book book2 = new Book("ISBN1235", "자바마스터", 30000);
		book2.setAuthor("송미영");
		book2.setDescription("자바를 처음 배우는 학생을 위해 자바의 기본 개념과 실습 예제를 그림을 이용하여 쉽게 설명합니다. 자바의 이론적 개념→기본 예제→프로젝트 순으로 단계별 학습이 가능하며, 각 챕터의 프로젝트를 실습하면서 온라인 서점을 완성할 수 있도록 구성하였습니다.");
		book2.setPublisher("한빛아카데미");
		book2.setCategory("IT모바일");
		book2.setUnitsInStock(1000);
		book2.setReleaseDate("2023/01/01");
		book2.setFilename("ISBN1235.jpg");
		
		Book book3 = new Book("ISBN1236", "파이썬 프로그래밍", 30000);
		book3.setAuthor("최성철");
		book3.setDescription("파이썬으로 프로그래밍을 시작하는 입문자가 쉽게 이해할 수 있도록 기본 개념을 상세하게 설명하며, 다양한 예제를 제시합니다. 또한 프로그래밍의 기초 원리를 이해하면서 파이썬으로 데이터를 처리하는 기법도 배웁니다.");
		book3.setPublisher("한빛아카데미");
		book3.setCategory("IT모바일");
		book3.setUnitsInStock(1000);
		book3.setReleaseDate("2023/01/01");
		book3.setFilename("ISBN1236.jpg");
		
		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
		
		
	}
	
	//CREATE
	public void addBook(Book book) {
		
		System.out.println("addbook 함수 실행중...");
		PreparedStatement ps = null;
		
		try {
			Connection conn = DBconn();
			
			String sql = "insert into book values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql); //명령어 미리 전달
			
			ps.setString(1, book.getBookId());
			ps.setString(2, book.getName());
			ps.setInt(3, book.getUnitPrice());
			ps.setString(4, book.getAuthor());
			ps.setString(5, book.getDescription());
			ps.setString(6, book.getPublisher());
			ps.setString(7, book.getCategory());
			ps.setLong(8, book.getUnitsInStock());
			ps.setString(9, book.getReleaseDate());
			ps.setString(10, book.getCondition());
			ps.setString(11, book.getFilename());
			ps.executeUpdate(); //업로드
			System.out.println("파일 업로드 완료");
			
			ps.close();
			conn.close();
			System.out.println("addbook 함수 종료중...");
			
		} catch(Exception e) {System.out.println("도서 추가 실패");}
	}
	
	//UPDATE
	public void bookUpdate(Book book) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Connection conn = DBconn();
			
			String sql = "select * from book where b_id=?"; //기존 데이터를 바꾸기 위해 불러와야함
			ps = conn.prepareStatement(sql); //해당 명령어 전달
			ps.setString(1, book.getBookId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("데이터 발견!");
				
				//데이터를 수정할 때, 파일명을 삽입하지 않으면 기존 데이터가 날아가기 때문
				//input file태그는 value값을 지정할 수 없기때문에 수정하지 않으면 null값으로 오게 된다.
				if(book.getFilename()!=null) {
					sql = "update book set b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=?, b_filename=? where b_id=?";
					ps = conn.prepareStatement(sql); //재사용?
					ps.setString(1, book.getName());
					ps.setInt(2, book.getUnitPrice());
					ps.setString(3, book.getAuthor());
					ps.setString(4, book.getDescription());
					ps.setString(5, book.getPublisher());
					ps.setString(6, book.getCategory());
					ps.setLong(7, book.getUnitsInStock());
					ps.setString(8, book.getReleaseDate());
					ps.setString(9, book.getCondition());
					ps.setString(10, book.getFilename());
					ps.setString(11, book.getBookId());
					ps.executeUpdate();
					System.out.println("이미지가 수정됩니다, 업로드 완료.");
					
				} else {
					
					sql = "update book set b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=? where b_id=?";
					ps = conn.prepareStatement(sql); //재사용?
					ps.setString(1, book.getName());
					ps.setInt(2, book.getUnitPrice());
					ps.setString(3, book.getAuthor());
					ps.setString(4, book.getDescription());
					ps.setString(5, book.getPublisher());
					ps.setString(6, book.getCategory());
					ps.setLong(7, book.getUnitsInStock());
					ps.setString(8, book.getReleaseDate());
					ps.setString(9, book.getCondition());
					ps.setString(10, book.getBookId());
					ps.executeUpdate();
					System.out.println("이미지가 유지됩니다, 업로드 완료.");
				}
			}
			
			
		} catch(Exception e) {System.out.println("도서 업데이트 실패");}
		
	}

	//READ all
 	public static ArrayList<Book> getAllBooks() {
		//return listOfBooks;
		//해당 메서드는 도서 목록을 반환
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		
		try {
			Connection conn = DBconn();
			
			String sql = "select * from book";
			ps = conn.prepareStatement(sql); //명령어 전달
			rs = ps.executeQuery();			//파일 받아오기
			System.out.println("전체 도서 목록을 가져옵니다.");
			
			while(rs.next()) {
				Book book = new Book();
				
				book.setBookId(rs.getString("b_id"));
				book.setName(rs.getString("b_name"));
				book.setUnitPrice(rs.getInt("b_unitPrice"));
				book.setAuthor(rs.getString("b_author"));
				book.setDescription(rs.getString("b_description"));
				book.setPublisher(rs.getString("b_publisher"));
				book.setCategory(rs.getString("b_category"));
				book.setUnitsInStock(rs.getLong("b_unitsInStock"));
				book.setReleaseDate(rs.getString("b_releaseDate"));
				book.setCondition(rs.getString("b_condition"));
				book.setFilename(rs.getString("b_filename"));
				
				listOfBooks.add(book);
				
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		}
		catch(Exception e) { System.out.println("전체 도서 불러오기 실패"); }
		
		System.out.println("반환 완료");
		return listOfBooks;
	}

 	//READ One
	public static Book getBookOne(String id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = new Book(); //전달할 dto객체 생성
		
		try {
			
			Connection conn = DBconn();
			
			String sql = "select * from book where b_id=?";
			ps = conn.prepareStatement(sql); //명령어 전달
			ps.setString(1, id); //이후 추가하기
			
			rs = ps.executeQuery(); //resultset에 담기
			
			if(rs.next()) {
				
				book.setBookId(rs.getString("b_id"));
				book.setName(rs.getString("b_name"));
				book.setUnitPrice(rs.getInt("b_unitPrice"));
				book.setAuthor(rs.getString("b_author"));
				book.setDescription(rs.getString("b_description"));
				book.setPublisher(rs.getString("b_publisher"));
				book.setCategory(rs.getString("b_category"));
				book.setUnitsInStock(rs.getLong("b_unitsInStock"));
				book.setReleaseDate(rs.getString("b_releaseDate"));
				book.setCondition(rs.getString("b_condition"));
				book.setFilename(rs.getString("b_filename"));				
			}
			
			System.out.println("업데이트 될 도서 정보를 가져옵니다.");
			
			rs.close();
			ps.close();
			conn.close();
			
		}catch(Exception e) {System.out.println("도서 정보 가져오기 실패");}
		
		return book;
		
	}
	
	
	public void bookDel(String id) {
		
		PreparedStatement ps = null;
		
		try {
			
			Connection conn = DBconn();
			
			String sql = "delete from book where b_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate(); //명령어 실행
			
			System.out.println("도서삭제 완료");
			
			ps.close();
			conn.close();
			
			
		} catch(Exception e) {System.out.println("도서삭제 실패");}
		
	}
	
	public Book getBookById(String bookId) {
		Book bookById=null;
		System.out.println(bookId);
		
		for(int i=0; i<listOfBooks.size(); i++) {
			Book book=listOfBooks.get(i);
			System.out.println("ID비교중 원본 : " + book.getBookId());
			
			if(book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
				System.out.println("일치합니다.");
				bookById = book;
				break;
			}
		}
		
		return bookById;
	}

}
