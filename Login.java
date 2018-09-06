package burger_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import common_util.JDBC_Close;

public class Login {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@203.236.209.174:1521:xe"; 
	private static final String USER = "burger"; 
	private static final String PASSWORD = "burger"; 
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//static �ʱ�ȭ ����
		static {
			try {
				Class.forName(DRIVER);
				System.out.println(">> ����Ŭ JDBC ����̹� �ε� ����!!");
			} catch (ClassNotFoundException e) {
				System.out.println("[���ܹ߻�] ����̹� �ε� ����!!!");
			}
		}
		
		private Connection getConnection() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				System.out.println("[���ܹ߻�] DB���� ����!!!");
			}
			return conn;
		}
		
		
		
		public String login() {
			String result =  null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			///////////////////////////////////////////////////////////////////
			try {
			StringBuilder sb = new StringBuilder();
			
			sb.append("select * from customer where custid =? and password =?");
			
			pstmt = conn.prepareStatement(sb.toString());				
			
			Scanner choice = new Scanner(System.in);
			
			System.out.println("���̵� �Է����ּ���1");
			String custId = choice.nextLine();
			System.out.println("��й�ȣ�� �Է����ּ���1");
			String password = choice.nextLine();
													
				pstmt.setString(1, custId);
				pstmt.setString(2, password);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()==true) {
					result=custId;
					System.out.println("���ӿ� �����ϼ̽��ϴ�.");
				} else {
					System.out.println("���ӿ� �����ϼ̽��ϴ�.");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			} return result;	

		}
		
		public void createAccount() {
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO CUSTOMER ");
				sb.append("		(CUSTID, PASSWORD, PHONE) ");
				sb.append("VALUES(?, ?, ?)");
				pstmt = conn.prepareStatement(sb.toString());
				
				Scanner choice = new Scanner(System.in);
				
				int i=0;
				System.out.println("ȸ���������ڽ��ϱ�");
				System.out.println("���̵� �Է����ּ���.");
				String custid = choice.nextLine();
				System.out.println("��й�ȣ�� �������ּ���.");
				String password = choice.nextLine();
				
				System.out.println("�ڵ��� ��ȣ�� �Է����ּ���.");
				String phone = choice.nextLine();
				
				pstmt.setString(1, custid);
				pstmt.setString(2, password);
				pstmt.setString(3, phone);
				pstmt.executeUpdate();
				System.out.println("������ �Ǿ����ϴ�.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
