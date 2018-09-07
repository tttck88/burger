package burger_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sum {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@203.236.209.174:1521:xe"; 
	private static final String USER = "burger"; 
	private static final String PASSWORD = "burger"; 
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	static {
		try {
			Class.forName(DRIVER);
			System.out.println(">> 오라클 JDBC 드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] 드라이버 로딩 실패!!!");
		}
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("[예외발생] DB연결 실패!!!");
		}
		return conn;
	}
	
	
	
	public int burgerSum () {
		int str = 0;
	try {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select sum((select price from burger where burgerid = o.burgerid)) from orders o");
		pstmt = conn.prepareStatement(sql.toString());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			str += rs.getInt(1);

		}			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str;
	}
	
	public int drinkSum () {
		int str = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select sum((select price from drink where drinkid = o.drinkid)) from orders o");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				str += rs.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		}
	
	public int dessertSum () {
		int str = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select sum((select price from dessert where dessertid = o.dessertid)) from orders o");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
	
				str += rs.getInt(1);

			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		}
}



















