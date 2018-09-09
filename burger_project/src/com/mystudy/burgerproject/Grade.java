package com.mystudy.burgerproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grade {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private	static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private	static final String USER = "Burger";
	private	static final String PASSWORD = "burger";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	

	
	static {
		try {
			Class.forName(DRIVER);
			System.out.println(">> 오라클 JDBC 드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생]");
			e.printStackTrace();
		}
	}
	
	public void Updategrade(String id) { //레스트 포인트 다음에 와야함
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuffer sql = new StringBuffer();
			sql.append("update customer set grade = ? where custid = ?");
			
			String grade = "";
			if (restPoint(id)<=2000) {
				grade = "4등급";
			} else if (restPoint(id)<=5000) {
				grade = "3등급";
			} else if (restPoint(id)<=10000) {
				grade = "2등급";
			} else {
				grade = "1등급";
			}
			
			pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setString(1, grade);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public String whatGrade(String id) {
		String result = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select grade from customer where custid = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String str = "";
				str = rs.getString(1);
				result = str;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}






















