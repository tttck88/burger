package com.mystudy.burgerproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import common_util.JDBC_Close;

public class login {
	
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
		
		Scanner choice = new Scanner(System.in);
		int pick;
		
		//����������(���̵�� �˻��ؼ� ����ü������ ���)
		public CustomerVO mypage(String id) {
			CustomerVO cvo = new CustomerVO();
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String sql = "";
				sql += "SELECT * FROM CUSTOMER ";
				sql += "WHERE CUSTID = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				System.out.println("���Ͻô� ��ȣ�� �����ּ���!");
				System.out.println("1.ȸ������ ����  2.����Ʈ Ȯ��");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				if (pick==1) {
					
					int updatepick=0;
					
					while (updatepick!=1 || updatepick!=2) {
						
						System.out.println("1.��й�ȣ ����  2.��ȭ��ȣ ����");
						updatepick = choice.nextInt();
						choice.nextLine();
						
						if (updatepick==1) {
							updatecustpw(id);
							break;
						} else if (updatepick==2) {
							updatephoneNo(id);
							break;
						} else {
							System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
							System.out.println("-----------------------------------------------");
						}
					}
						
				} else if (pick==2) {
					//����Ʈ Ȯ�� �޼ҵ�
				} else {
					System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
					System.out.println("-----------------------------------------------");
				}
				
				System.out.println("�޴�ȭ������ ���ư��ϴ�.");
				System.out.println("-----------------------------------------------");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}

			return cvo;
			
		}
			
		
		//ȸ�� ��й�ȣ ����
		public void updatecustpw(String id) {
				
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String updatepw = "";
				updatepw += "UPDATE CUSTOMER ";
				updatepw += "SET PASSWORD = ? ";
				updatepw += "WHERE CUSTID = ? ";
				pstmt = conn.prepareStatement(updatepw);
					
				System.out.println("�����Ͻ� ��й�ȣ�� �Է����ּ���.");
				String pw = choice.nextLine();
				System.out.println("��й�ȣ�� �ѹ� �� �Է����ּ���.");
				String pwcheck = choice.nextLine();
			
			if (pw.equals(pwcheck)) {
				System.out.println("��й�ȣ ������ �Ϸ�Ǿ����ϴ�.");
			} else {
				System.out.println("��й�ȣ�� �ٽ� �ѹ� Ȯ�����ּ���.(T_T)");
				updatecustpw(id);
			}
					
				pstmt.setString(1, pw);
				pstmt.setString(2, id);
				pstmt.executeUpdate();	
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}
				
		}
		
		//ȸ�� �ڵ�����ȣ ����
		public void updatephoneNo(String id) {
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String updatepw = "";
				updatepw += "UPDATE CUSTOMER ";
				updatepw += "SET PHONE = ? ";
				updatepw += "WHERE CUSTID = ? ";
				pstmt = conn.prepareStatement(updatepw);
					
				System.out.println("�����Ͻ� ��ȭ��ȣ�� �Է����ּ���.");
				String phoneNo = choice.nextLine();
				System.out.println("��ȭ��ȣ�� �ѹ� �� �Է����ּ���.");
				String phonecheck = choice.nextLine();
			
			if (phoneNo.equals(phonecheck)) {
				System.out.println("��ȭ��ȣ ������ �Ϸ�Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ��ȣ�� �ٽ� �ѹ� Ȯ�����ּ���.(T_T)");
				updatephoneNo(id);
			}
					
				pstmt.setString(1, phoneNo);
				pstmt.setString(2, id);
				pstmt.executeUpdate();	
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}
			
		}
		
}
