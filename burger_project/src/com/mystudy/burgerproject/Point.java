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
			System.out.println(">> ����Ŭ JDBC ����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			System.out.println("[���ܹ߻�]");
			e.printStackTrace();
		}
	}
	
	//����Ʈ�� �ִ���?
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
		//����Ʈ�ջ� restpoint ������ �;� ��
		public double totPoint (String id) {
			addPoint =(burgersum(id)+drinksum(id)+dessertsum(id))*0.05;
			double result = restPoint(id); //����Ʈ�� ���ٸ� �̰��� ��ȯ
			try {
				
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				StringBuffer sql = new StringBuffer();
				
				sql.append("update customer ");
				sql.append("set point = ? ");
				sql.append("where custid = ? ");
				
				
				if(restPoint(id) !=0) {
					result += addPoint; ///����Ʈ�� �����Ͽ� �ջ��� ����Ʈ ��ȯ
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
		
		//////////////////////////////////// ����Ʈ ����
		//////////restpoint ������ �;� ��
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























