package com.mystudy.burgerproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Point {
	
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
	
	//포인트가 있느냐?
		public int restPoint (String id) {
			int result = 0;		
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuffer sql = new StringBuffer();
				
				sql.append("select point from customer where custid = ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					result =rs.getInt(1);
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			return result;
		}
		
		
		
		////////////////////////////////////////////////////////////////////////
		//포인트합산 restpoint 다음에 와야 함
		public double totPoint (String id) {
			addPoint =(burgersum(id)+drinksum(id)+dessertsum(id))*0.05;
			double result = restPoint(id); //포인트가 없다면 이것을 반환
			try {
				
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				StringBuffer sql = new StringBuffer();
				
				sql.append("update customer ");
				sql.append("set point = ? ");
				sql.append("where custid = ? ");
				
				
				if(restPoint(id) !=0) {
					result += addPoint; ///포인트가 존재하여 합산한 포인트 반환
				} else {
					result = addPoint;
				};
				
				
				pstmt = conn.prepareStatement(sql.toString());
						
				pstmt.setDouble(1, result);
				pstmt.setString(2, id);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		//////////////////////////////////// 포인트 수정
		//////////restpoint 다음에 와야 함
		public void fixPoint(int pick, String id) {
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuffer sql = new StringBuffer();
				sql.append("update customer set point = ? where custid = ?");
				
				int fixPoint = restPoint(id)-pick;
							
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, fixPoint);
				pstmt.setString(2, id);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}























