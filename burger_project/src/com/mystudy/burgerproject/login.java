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
	
	//static 초기화 구문
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
			
			System.out.println("아이디를 입력해주세요1");
			String custId = choice.nextLine();
			System.out.println("비밀번호를 입력해주세요1");
			String password = choice.nextLine();
													
				pstmt.setString(1, custId);
				pstmt.setString(2, password);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()==true) {
					result=custId;
					System.out.println("접속에 성공하셨습니다.");
				} else {
					System.out.println("접속에 실패하셨습니다.");
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
				System.out.println("회원가입히겠습니까");
				System.out.println("아이디를 입력해주세요.");
				String custid = choice.nextLine();
				System.out.println("비밀번호를 설정해주세요.");
				String password = choice.nextLine();
				
				System.out.println("핸드폰 번호를 입력해주세요.");
				String phone = choice.nextLine();
				
				pstmt.setString(1, custid);
				pstmt.setString(2, password);
				pstmt.setString(3, phone);
				pstmt.executeUpdate();
				System.out.println("설정이 되었습니다.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}
		}
		
		Scanner choice = new Scanner(System.in);
		int pick;
		
		//마이페이지(아이디로 검색해서 고객전체데이터 출력)
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
				
				System.out.println("원하시는 번호를 눌러주세요!");
				System.out.println("1.회원정보 변경  2.포인트 확인");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				if (pick==1) {
					
					int updatepick=0;
					
					while (updatepick!=1 || updatepick!=2) {
						
						System.out.println("1.비밀번호 변경  2.전화번호 변경");
						updatepick = choice.nextInt();
						choice.nextLine();
						
						if (updatepick==1) {
							updatecustpw(id);
							break;
						} else if (updatepick==2) {
							updatephoneNo(id);
							break;
						} else {
							System.out.println("< 1~2 > 다시 선택해주세요.(T_T)");
							System.out.println("-----------------------------------------------");
						}
					}
						
				} else if (pick==2) {
					//포인트 확인 메소드
				} else {
					System.out.println("< 1~2 > 다시 선택해주세요.(T_T)");
					System.out.println("-----------------------------------------------");
				}
				
				System.out.println("메뉴화면으로 돌아갑니다.");
				System.out.println("-----------------------------------------------");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}

			return cvo;
			
		}
			
		
		//회원 비밀번호 수정
		public void updatecustpw(String id) {
				
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String updatepw = "";
				updatepw += "UPDATE CUSTOMER ";
				updatepw += "SET PASSWORD = ? ";
				updatepw += "WHERE CUSTID = ? ";
				pstmt = conn.prepareStatement(updatepw);
					
				System.out.println("변경하실 비밀번호를 입력해주세요.");
				String pw = choice.nextLine();
				System.out.println("비밀번호를 한번 더 입력해주세요.");
				String pwcheck = choice.nextLine();
			
			if (pw.equals(pwcheck)) {
				System.out.println("비밀번호 변경이 완료되었습니다.");
			} else {
				System.out.println("비밀번호를 다시 한번 확인해주세요.(T_T)");
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
		
		//회원 핸드폰번호 수정
		public void updatephoneNo(String id) {
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String updatepw = "";
				updatepw += "UPDATE CUSTOMER ";
				updatepw += "SET PHONE = ? ";
				updatepw += "WHERE CUSTID = ? ";
				pstmt = conn.prepareStatement(updatepw);
					
				System.out.println("변경하실 전화번호를 입력해주세요.");
				String phoneNo = choice.nextLine();
				System.out.println("전화번호를 한번 더 입력해주세요.");
				String phonecheck = choice.nextLine();
			
			if (phoneNo.equals(phonecheck)) {
				System.out.println("전화번호 변경이 완료되었습니다.");
			} else {
				System.out.println("전화번호를 다시 한번 확인해주세요.(T_T)");
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
