package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException  {		

		
		Connection conn = null;		
	
		String url = "jdbc:mysql://localhost:3306/BookMarketDB";
		String user = "root";
		String password = "12345678";

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);		
		System.out.println("데이터베이스가 연결되었습니다.");
		return conn;
		 }
}
