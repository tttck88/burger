package com.mystudy.burgerproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import common_util.JDBC_Close;

public class custorder {
	
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
	
	Scanner choice = new Scanner(System.in);
	int pick;
	Burger burger = new Burger();
	
	CustomerVO cvo = new CustomerVO();
	
	BurgerVO bvo = new BurgerVO();
	DrinkVO dvo = new DrinkVO();
	DessertVO desvo = new DessertVO();
	SetVO setvo = new SetVO();
	
	OrderVO ord = new OrderVO();
	login log = new login();
	checkbill check = new checkbill();
	
	
	
	///////////////////////////////////////////////////
	///////////////////////////////////////////////////
	Point point = new Point();
	Grade grade = new Grade();
	Payment payment = new Payment();
	public String id;
	double addPoint;
	double pay;
	///////////////////////////////////////////////////
	//////////////////////////////////////////////////
	
	
	public void start() {
		System.out.println("�������. �ܹ����Դϴ�!");
		System.out.println("ȸ���̽ø� < 1 >, ��ȸ���̽ø� < 2 >�� �����ּ���!");
		
		
		pick = choice.nextInt();
		choice.nextLine();
	
		if(pick == 1) {
			login();
		} else if (pick == 2) {
			addCust();
		} else {
			System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
			System.out.println("-----------------------------------------------");
			start();
		}
	}

	
	
	
	//ȸ���ֹ�
	public String login() {
		
		String result =  null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("select * from customer where custid =? and password =?");
			pstmt = conn.prepareStatement(sb.toString());				
			
			System.out.println("���̵� �Է����ּ���");
			String custId = choice.nextLine();
			System.out.println("��й�ȣ�� �Է����ּ���");
			String password = choice.nextLine();
				
			pstmt.setString(1, custId);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()==true) {
				result=custId;
				id = custId;
				System.out.println("���ӿ� �����ϼ̽��ϴ�.");
				System.out.println("�� �� �� ~");
				
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
				whereEat();
				
			} else {
				
				while (pick!=1 || pick !=2 || pick !=3) {
					System.out.println("���ӿ� �����ϼ̽��ϴ�.");
					System.out.println("��õ��� ���Ͻø� <1>, ȸ�������� ���Ͻø� <2>,"
							+ "���Ḧ ���Ͻø� <3> �� �����ּ���.");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					if(pick==1) {
						login();
					} else if (pick==2) {
						addCust();
					} else if(pick==3){
						System.out.println("�����մϴ�.");
						System.out.println("***********************************************");
						start();
					} else {
						System.out.println("< 1~3 > �ٽ� �������ּ���.(T_T)");
						System.out.println("-----------------------------------------------");
					}	
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	//��ȸ���ֹ�
	public void addCust() {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO CUSTOMER ");
			sb.append("		(CUSTID, PASSWORD, PHONE) ");
			sb.append("VALUES(?, ?, ?)");
			pstmt = conn.prepareStatement(sb.toString());
			
			int i=0;
			System.out.println("[ȸ������]" + ++i + " �� ������ �ֹ�");
			System.out.println("���̵� �������ּ���.");
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
			System.out.println("�����Ͻ� ���̵�� �α��� �õ����ּ���!");
			System.out.println("-----------------------------------------------");
			
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			login();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			
		}
	}
	
	////////////���� or ����ũ�ƿ�?
	public void whereEat() {		
		System.out.println("���忡�� ��ø� < 1 >, ����ũ�ƿ� �Ͻø� < 2 > �� �����ּ���!");
		
		pick = choice.nextInt();
		choice.nextLine();
		
		if(pick == 1) { //�������� ����
			pick();
		} else if (pick == 2) { //�������� ����
			pick();
		} else {
			System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
			System.out.println("-----------------------------------------------");
			whereEat();
		}
		
	}
	
	//�޴�
	public void pick() {
		System.out.println("< �� �� >");
		System.out.println("���Ͻô� ��ȣ�� �����ּ���!");
		System.out.println("1.����  2.����  3.����Ʈ  4.��Ʈ  5.����");
		
		pick = choice.nextInt();
		choice.nextLine();
		
		if(pick == 1) {
			selectBurger(id);
		} else if (pick==2) {
			selectDrink(id);
		} else if (pick==3) {
			selectDs(id);
		} else if (pick==4) {
			selectSet(id);
		} else if (pick==5){
			System.out.println("�����մϴ�.");
			System.out.println("***********************************************");
			start();
		} else {
			System.out.println("< 1~5 > �ٽ� �������ּ���.(T_T)");
			System.out.println("-----------------------------------------------");
			pick();
		}
		
	}
	
	//ȸ���� ��Ʈ�ֹ�
	public void selectSet(String id) {
		
		try {
	           conn = DriverManager.getConnection(URL, USER, PASSWORD);
	           
	           String sql = "";
				sql += "INSERT INTO ORDERS ";
				sql += "(ORDERID, CUSTID, SETID, ORDERDATE, LIST) ";
				sql += "VALUES ((SELECT NVL(MAX(ORDERID),0)+1 ";
				sql += "FROM ORDERS), ";
				sql += "?, ?, sysdate, ";
				sql += "(SELECT NVL(MAX(LIST),0)+1\r\n" + 
						"        FROM ORDERS WHERE CUSTID = ?)) ";
				pstmt = conn.prepareStatement(sql);
	           
	           System.out.println("**��Ʈ�޴��� �������ּ���.");
	           burger.printDataSet();
	           int selectset = choice.nextInt();
	           
	           System.out.println("������ �������ּ���.(0~9)");
	           
	           int num = choice.nextInt();
	           choice.nextLine();
	           
	           for(int i=1; i<=num; i++) {
		           pstmt.setString(1, id);
		           pstmt.setInt(2, selectset);
		           pstmt.setString(3, id);
		           pstmt.executeUpdate();
		           pstmt.executeUpdate();
	           }
	           System.out.println("��Ʈ������ �Ϸ�Ǿ����ϴ�~~~~!^0^");
	           add(id);
	          	           
	        } catch (SQLException e) {
	           e.printStackTrace();
	        } finally {
	           JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
	        }
		
	}
	
	//�߰��ֹ�
	public void add(String id) {
		
		System.out.println("------------------------------------------------"); 
		System.out.println("�ֹ��� �������� < 1 >,");
        System.out.println("�߰��ֹ��� ���Ͻø� < 2 > �� �����ּ���.");
        System.out.println("------------------------------------------------"); 
        
        pick = choice.nextInt();
        choice.nextLine();
         
		
         if(pick == 1) {
      	   bag();
         } else if (pick == 2) {
      	   addmenu(id);
         } else {
      	   System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
      	   System.out.println("-----------------------------------------------");
      	   add(id);
      	   
         }
		
	}
	
	//�߰��ֹ� �޴����
	public void addmenu(String id) {
		
		System.out.println("�߰��ֹ��� ���Ͻô� �޴��� �������ּ���.");
    	System.out.println("1.����  2.����  3.����Ʈ  4.��Ʈ");
    	  
    	  pick = choice.nextInt();
	      choice.nextLine();
	          
	          if(pick==1) {
	        	  selectBurger(id);
	          } else if(pick==2) {
	        	  selectDrink(id);
	          } else if(pick==3) {
	        	  selectDs(id);
	          } else if(pick==4) {
	        	  selectSet(id);
	          } else {
	        	  System.out.println("< 1~4 > �ٽ� �������ּ���.(T_T)");
	        	  System.out.println("-----------------------------------------------");
	        	  addmenu(id);
	          }
		
		
	}
	
	//ȸ���� �����ֹ�
	public void selectBurger(String id) {
        
        try {
           conn = DriverManager.getConnection(URL, USER, PASSWORD);
           
           String sql = "";
			sql += "INSERT INTO ORDERS ";
			sql += "(ORDERID, CUSTID, BURGERID, ORDERDATE, LIST) ";
			sql += "VALUES ((SELECT NVL(MAX(ORDERID),0)+1 ";
			sql += "FROM ORDERS), ";
			sql += "?, ?, sysdate, ";
			sql += "(SELECT NVL(MAX(LIST),0)+1\r\n" + 
					"        FROM ORDERS WHERE CUSTID = ?)) ";
			pstmt = conn.prepareStatement(sql);
			
           System.out.println("**���Ÿ޴��� �������ּ���.");
           burger.printDataBurger();
           int selectbur = choice.nextInt();
           
           System.out.println("������ �������ּ���.(0~9)");
           
           int num = choice.nextInt();
           choice.nextLine();
           
           for(int i=1; i<=num; i++) {
	           pstmt.setString(1, id);
	           pstmt.setInt(2, selectbur);
	           pstmt.setString(3, id);
	           pstmt.executeUpdate();
           }
           
           System.out.println("���ż����� �Ϸ�Ǿ����ϴ�~~~~!^0^");
           add(id);
           
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
        }
     }
	
	//ȸ���� �����ֹ�
	public void selectDrink(String id) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "";
			sql += "INSERT INTO ORDERS ";
			sql += "(ORDERID, CUSTID, DRINKID, ORDERDATE, LIST) ";
			sql += "VALUES ((SELECT NVL(MAX(ORDERID),0)+1 ";
			sql += "FROM ORDERS), ";
			sql += "?, ?, sysdate, ";
			sql += "(SELECT NVL(MAX(LIST),0)+1\r\n" + 
					"        FROM ORDERS WHERE CUSTID = ?)) ";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("**����޴��� �������ּ���.");
			burger.printDataDrink();
			int selectdrk = choice.nextInt();
			
			System.out.println("������ �������ּ���.(0~9)");
	           
	        int num = choice.nextInt();
	        choice.nextLine();
	           
	        for(int i=1; i<=num; i++) {
				pstmt.setString(1, id);
				pstmt.setInt(2, selectdrk);
				pstmt.setString(3, id);
				pstmt.executeUpdate();
	        }
	        
	        System.out.println("���ἱ���� �Ϸ�Ǿ����ϴ�~~~~!^0^");
	        add(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
	}
	
	//ȸ���� ����Ʈ�ֹ�
	public void selectDs(String id) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "";
			sql += "INSERT INTO ORDERS ";
			sql += "(ORDERID, CUSTID, DESSERTID, ORDERDATE, LIST) ";
			sql += "VALUES ((SELECT NVL(MAX(ORDERID),0)+1 ";
			sql += "FROM ORDERS), ";
			sql += "?, ?, sysdate, ";
			sql += "(SELECT NVL(MAX(LIST),0)+1\r\n" + 
					"        FROM ORDERS WHERE CUSTID = ?)) ";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("**����Ʈ �޴��� �������ּ���.");
			burger.printDataDessert();
			int selectds = choice.nextInt();
			
			System.out.println("������ �������ּ���.(0~9)");
	           
	        int num = choice.nextInt();
	        choice.nextLine();
	           
	        for(int i=1; i<=num; i++) {
				pstmt.setString(1, id);
				pstmt.setInt(2, selectds);
				pstmt.setString(3, id);
				pstmt.executeUpdate();
	        }
			System.out.println("����Ʈ������ �Ϸ�Ǿ����ϴ�~~~~!^0^");
			add(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
	}
	
	//��ٱ���ȣ���� ���
	public void bag() {
		System.out.println("*~~~~* �ֹ� ����Ʈ *~~~~*");
		System.out.println("�ֹ���ȣ\t ����ȣ\t ����\t ����\t ����Ʈ\t ��Ʈ");
		burger.printDataBag();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("�ֹ��Ͻ� ������ �´��� Ȯ�����ּ���.");
			
			while (pick!=1 || pick !=2 || pick !=3) {
				System.out.println("����Ͻ÷��� < 1 >, ���θ޴��� ���ư��÷��� < 2 >"
						+ ", ������ ���Ͻø� < 3 > �� �����ּ���!");
				
				pick = choice.nextInt();
		        choice.nextLine();
				
				if (pick == 1) {
					cancel(id);
				} else if (pick == 2) {
					pick();
				} else if (pick == 3) {
					whereCheck();
				} else {
					System.out.println("< 1~3 > �ٽ� �������ּ���.(T_T)");
					System.out.println("-----------------------------------------------");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
	}
	
	//���
	//�ֹ����� ���
	public void cancel(String id) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
	        sb.append("update orders set burgerid = null "
	        		+ "where burgerid = ? and custid = ? and list = ?");
			pstmt = conn.prepareStatement(sb.toString());
			
			System.out.println("����Ͻ� �޴���ȣ�� �������ּ���.");
			System.out.println("1.����  2.����  3.����Ʈ  4.��Ʈ");
			pick = choice.nextInt();
	        choice.nextLine();
			
			while (pick!=1 || pick !=2 || pick !=3 || pick !=4) {
				
				pick = choice.nextInt();
		        choice.nextLine();
		        
				if (pick == 1) {
					burger.myorderlistbug(id);
				} else if (pick == 2) {
					burger.myorderlistdk(id);
				} else if (pick == 3) {
					burger.myorderlistdes(id);
				} else if (pick == 4) {
					burger.myorderlistset(id);
				} else {
					System.out.println("< 1~4 > �ٽ� �������ּ���.(T_T)");
					System.out.println("-----------------------------------------------");
					
				}
			}
			
			
			Scanner choice = new Scanner(System.in);
			int cancelBuger = choice.nextInt();
			choice.nextLine();
			
			System.out.println("����Ͻô� ������ �������ֽʽÿ�.");
			int cancelNum = choice.nextInt();
	        choice.nextLine();
	        
	        for (int i=0; i<=cancelNum; i++) {                   
	            pstmt.setInt(1, cancelBuger);                     
	            pstmt.setString(2, id); //custid =3                  
	            pstmt.setInt(3, i);
	            pstmt.executeUpdate();
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			bag();
		}
	}

	//�����հ�
	//�����հ�
	public int burgersum(String id) {
		int str=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuffer sql = new StringBuffer();
			sql.append("select sum((select price from burger where burgerid = o.burgerid)) from orders o");
			sql.append(" where custid =?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				str += rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return str;
	}
	
	//�����հ�
	//�����հ�
	public int drinksum(String id) {
		int str=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuffer sql = new StringBuffer();
			sql.append("select sum((select price from drink where drinkid = o.drinkid)) from orders o");
			sql.append(" where custid =?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				str += rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return str;
	}
	
	//����Ʈ�հ�
	//����Ʈ�հ�
	public int dessertsum(String id) {
		int str=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuffer sql = new StringBuffer();
			sql.append("select sum((select price from dessert where dessertid = o.dessertid)) from orders o");
			sql.append(" where custid =?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				str += rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return str;
	}
	
	//��Ʈ�հ�
	//��Ʈ�հ�
	public int setsum(String id) {
		int str=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuffer sql = new StringBuffer();
			sql.append("select sum((select price from SETMENU where SETID = o.SETID)) from orders o");
			sql.append(" where custid =?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				str += rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return str;
	}
	
	//�������
	//�������
	public void whereCheck() {
		
		System.out.println("��������� �����ϼ���.");
		System.out.println("1.ī�� 2.����");
		
		pick = choice.nextInt();
		choice.nextLine();
		
		if(pick == 1) {	
			System.out.println("����Ʈ�� ����Ͻ÷��� 1���� �ƴϸ� 2����");
			System.out.println("(�˸�) ����Ʈ ���� ����� �϶� �� �� �ֽ��ϴ�.");
			
			System.out.println("��������Ʈ" + point.restPoint(id));
			
			pick = choice.nextInt();
			choice.nextLine();		
			
			if(pick ==1) {
				System.out.println("����Ͻ� ����Ʈ �ݾ��� �������ֽʽÿ�");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				if(pick>restPoint(id)) {
					System.out.println("����Ʈ ��� �ݾ��� �ʰ��߽��ϴ�. ���� ȭ������ ���ư��ϴ�.");
					whereCheck();
				} else if (pick<=restPoint(id)) {
					fixPoint(pick, id);
					Updategrade(id);
					System.out.println(payment(pick, id)+" ���� ī��� ������ �Ǿ����ϴ�.\n"
							+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
					totPoint(id);
					Updategrade(id);
					bill();
					start();
					
				}	
				
			} else if(pick==2) {
				fixPoint(0, id);
				Updategrade(id);
				System.out.println(payment(0, id)+" ���� ī��� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
				totPoint(id);
				Updategrade(id);
				start();
				
			}

		} else if (pick == 2) {
			System.out.println("����Ʈ�� ����Ͻ÷��� 1���� �ƴϸ� 2����");
			System.out.println("(�˸�) ����Ʈ ���� ����� �϶� �� �� �ֽ��ϴ�.");
			
			System.out.println("��������Ʈ" + restPoint(id));
			
			pick = choice.nextInt();
			choice.nextLine();		
			
			if(pick ==1) {
				System.out.println("����Ͻ� ����Ʈ �ݾ��� �������ֽʽÿ�");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				if(pick>restPoint(id)) {
					System.out.println("����Ʈ ��� �ݾ��� �ʰ��߽��ϴ�. ���� ȭ������ ���ư��ϴ�.");
					whereCheck();
				} else if (pick<=restPoint(id)) {
					fixPoint(pick, id);
					Updategrade(id);
					System.out.println(payment(pick, id)+" ���� �������� ������ �Ǿ����ϴ�.\n"
							+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
					totPoint(id);
					Updategrade(id);
					start();
				}	
				
			} else if(pick==2) {
				fixPoint(0, id);
				Updategrade(id);
				System.out.println(payment(0, id)+" ���� �������� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");				totPoint(id);
				Updategrade(id);
				start();
				
			}
		} else {
			System.out.println("< 1~2 > �ٽ� �������ּ���.(T_T)");
			System.out.println("-----------------------------------------------");
			whereCheck();
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
			////////////////////////////////////////////////////
			////////����Ʈ
			
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
			
			/////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////
			//////// �׷��̵�
			
			//����Ʈ ����Ʈ ������ �;���
			//��� ����
			public void Updategrade(String id) { 
				try {
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
					StringBuffer sql = new StringBuffer();
					sql.append("update customer set grade = ? where custid = ?");
					
					String grade = "";
					if (restPoint(id)<=2000) {
						grade = "4���";
					} else if (restPoint(id)<=5000) {
						grade = "3���";
					} else if (restPoint(id)<=10000) {
						grade = "2���";
					} else {
						grade = "1���";
					}
					
					pstmt = conn.prepareStatement(sql.toString());
				
					pstmt.setString(1, grade);
					pstmt.setString(2, id);
					
					pstmt.executeUpdate();
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			
			///�������� 
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
			
			/////////////////////////////////////////////////////////
			////////////////////////////////////////////
			///////////////////// ���� ��� 
			
			//ī��� �׷��̵� �ڿ� �;���
			public double payment(int pick,String id) {
				
				if(whatGrade(id).equals("3���")) {
					pay = ((burgersum(id)+drinksum(id)+dessertsum(id))*0.95)-pick;
				} else if (whatGrade(id).equals("2���")) {
					pay = ((burgersum(id)+drinksum(id)+dessertsum(id))*0.90)-pick;
				} else if (whatGrade(id).equals("1���")) {
					pay = ((burgersum(id)+drinksum(id)+dessertsum(id))*0.85)-pick;

				} else {
					pay = burgersum(id)+drinksum(id)+dessertsum(id)-pick;

				}
				return pay ;
			}
			
			

	

	
	
	


//������
//������
	public void bill() {
		check.date();		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
			System.out.println("*~~~~* �ֹ� ����Ʈ *~~~~*");
			System.out.println("�ֹ���ȣ\t ����ȣ\t ����\t ����\t ����Ʈ\t ��Ʈ");
			burger.printDataBag();
			
			System.out.println("������ "+pay);
			
			System.out.println("����Ʈ " + restPoint(id));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
}
	
	public void write() {
		//File -> FileWriter -> BufferedWriter -> PrintWriter
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		ObjectOutputStream oos = null;
		
		try {
			fw = new FileWriter(new File("file/pw_out.txt"));
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.write(custorder);
			pw.write("�ݰ����ϴ�~~\n");
			pw.print("���� �ڹٰ������Դϴ�.");
			pw.println("������ �ǰ��ϴ�~~~");
			pw.println("���� ���� ����ʹ�.");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	
}























