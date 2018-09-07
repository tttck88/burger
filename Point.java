package burger_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import common_util.JDBC_Close;

public class Point {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@203.236.209.174:1521:xe"; 
	private	static final String USER = "Burger";
	private	static final String PASSWORD = "burger";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	static {
		try {
			Class.forName(DRIVER);
			System.out.println(">> ����Ŭ JDBC ����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			System.out.println("[���ܹ߻�]");
			e.printStackTrace();
		}
	}
	
	
	
	
	Sum sum = new Sum();
	
	
	public void date() {
		Calendar cal = Calendar.getInstance();
		
		String dateTime = cal.get(Calendar.YEAR) 
				+ "-" + (cal.get(Calendar.MONTH) + 1)
				+ "-" + cal.get(Calendar.DATE)
				+ " " + cal.get(Calendar.HOUR_OF_DAY)
				+ ":" + cal.get(Calendar.MINUTE)
				+ ":" + cal.get(Calendar.SECOND);
		
		System.out.println(dateTime);
		}				
	
	public void point () {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			int i = sum.burgerSum();
			int j = sum.drinkSum();
			int k = sum.dessertSum();
			
			int sum = i+j+k;				
			double point = sum*0.05;	
			
			System.out.println(point);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} JDBC_Close.closeConnStmtRs(conn, pstmt, rs);

	}
	
	

}















