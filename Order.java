package burger_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import common_util.JDBC_Close;

public class Order {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@203.236.209.174:1521:xe"; 
	private static final String USER = "burger"; 
	private static final String PASSWORD = "burger"; 
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	BurgerVO bvo = null;	
	DrinkVO dvo = null;	
	DessertVO dvs = null;			



	
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
	
		Scanner choice = new Scanner(System.in);
		int pick;
		ArrayList list = new ArrayList();
		CustomerVO cvo = new CustomerVO();
		
		/////////////주문?
		public void start() {
			System.out.println("주문? 1");
			
			pick = choice.nextInt();
			choice.nextLine();
		
			if(pick == 1) {		
				whereEat();
			} else if (pick != 1) {  //다른 수를 입력 할 경우에는 계속해서 초기화면을 보여주기 위해 자기자신을 호출
				start();
			}
		}
		
		////////////매장 or 테이크아웃?
		public void whereEat() {
			System.out.println("어디서 드시겠습니까?");
			System.out.println("1.매장 2.테이크아웃 3.처음으로");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) { //직원에게 전달
				cvo.setWhereEat("매장");
			} else if (pick == 2) { //직원에게 전달
				cvo.setWhereEat("테이크아웃");
			} else if (pick == 3) {
				start();
			} else {
			System.out.println("다시 선택해주세요.");
			whereEat();
			}
			whatMenu();
		}
		
		////////////무슨 메뉴?
		public void whatMenu() {
			System.out.println("1:버거단품 2:음료 3:디저트 4:세트 5:이전으로 6:처음으로 7:장바구니");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
				whatBg();
			} else if (pick == 2) {
				whatDk();
			} else if (pick == 3) {
				whatDs();
			} else if (pick == 4) {
				whatSet();
			} else if (pick == 5) {
				whereEat();
			} else if (pick == 6) {
				start();
			} else if (pick == 7) {
				bag();
			} else {
				System.out.println("다시 선택해주세요.");
				whatMenu();
				
			}
		}
		
		//버거 메뉴
		public void whatBg() {
			ArrayList<BurgerVO> list = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT BURGERID, BURGERNAME, PRICE ");
				sb.append("  FROM BURGER ");
				sb.append(" ORDER BY BURGERID ");
				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<BurgerVO>();
				while (rs.next()) {
					list.add(new BurgerVO(rs.getInt("burgerid"),
										  rs.getString("burgername"),
										  rs.getInt("price")));
					
					if (list.size() < 1) {
						list = null;
					}
				}
				for(BurgerVO bvo : list) {
					System.out.println(bvo);
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
				pickBurger();
			}
		}
			
		//버거 아이디로 검색
		public void pickBurger () {
				System.out.println("무슨버거?");
			try {			
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String sql = "";
				sql += "SELECT BURGERID, BURGERNAME, PRICE ";
				sql += "  FROM BURGER ";
				sql += " WHERE BURGERID = ?";
				pstmt = conn.prepareStatement(sql);
				
				pick = choice.nextInt();
    			choice.nextLine();
				
				pstmt.setInt(1, pick);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					bvo = new BurgerVO(
					rs.getInt(1)
				   ,rs.getString(2)
				   ,rs.getInt(3)
					);
					cvo.setWhatBg(bvo.getBgName());
				} else {
					System.out.println("데이터 없음!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}  whatMenu();			
		}
			
			public void whatBg2() {
			System.out.println("원하시는 구성을 선택해주세요");
			System.out.println("1단품,2세트");

			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				//버거 종류에 따른 세트로
				whatMenu();
				//장바구니에 저장
			} else if (pick == 2) { //일반세트
				whatDk();
				//장바구니에 저장
			} else {
				System.out.println("다시 입력해주세요");
				whatBg2();
			} 
		}
		
		//세트선택시
		public void whatSet() {
			System.out.println("무슨세트?");
			System.out.println("1치즈세트,2더블치즈세트");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("치즈세트");
				cvo.setWhatSet("치즈세트");
			} else if (pick == 2) {
				System.out.println("더블치즈세트");
				cvo.setWhatSet("더블치즈세트");
			} else {
				System.out.println("다시 입력해주세요");
				whatSet();
			}
			whatMenu();
		}
		//음료선택시
		public void whatDk() {
			ArrayList<DrinkVO> list = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT drinkid, drinkname, PRICE ");
				sb.append("  FROM drink ");
				sb.append(" ORDER BY drinkID ");
				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<DrinkVO>();
				while (rs.next()) {
					list.add(new DrinkVO(rs.getInt("drinkid"),
										  rs.getString("drinkname"),
										  rs.getInt("price")));
					
					if (list.size() < 1) {
						list = null;
					}
				}
				for(DrinkVO bvo : list) {
					System.out.println(bvo);
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
				pickDrink();
			}
		}
		//버거 아이디로 검색
				public void pickDrink () {
						System.out.println("무슨음료?");
					try {			
						conn = DriverManager.getConnection(URL, USER, PASSWORD);
						
						String sql = "";
						sql += "SELECT drinkid, drinkname, PRICE ";
						sql += "  FROM drink ";
						sql += " WHERE drinkid = ?";
						pstmt = conn.prepareStatement(sql);
						
						pick = choice.nextInt();
		    			choice.nextLine();
						
						pstmt.setInt(1, pick);
						
						rs = pstmt.executeQuery();
						
						if (rs.next()) {
							dvo = new DrinkVO(
							rs.getInt(1)
						   ,rs.getString(2)
						   ,rs.getInt(3)
							);
							cvo.setWhatDk(dvo.getDkName());
						} else {
							System.out.println("데이터 없음!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
					}  whatMenu();			
				}
				
		//디저트선택시
		public void whatDs() {
			ArrayList<DessertVO> list = null; 
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT dessertid, dessertname, PRICE ");
				sb.append("  FROM dessert ");
				sb.append(" ORDER BY dessertid ");
				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<DessertVO>();
				while (rs.next()) {
					list.add(new DessertVO(rs.getInt("dessertid"),
										  rs.getString("dessertname"),
										  rs.getInt("PRICE")));
					
					if (list.size() < 1) {
						list = null;
					}
				}
				for(DessertVO dvo : list) {
					System.out.println(dvo);
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
				pickDessert ();
			}
		}
		//디저트 아이디로 검색
				public void pickDessert () {
						System.out.println("무슨디저트?");
					try {			
						conn = DriverManager.getConnection(URL, USER, PASSWORD);
						
						String sql = "";
						sql += "SELECT dessertid, dessertname, PRICE ";
						sql += "  FROM dessert ";
						sql += " WHERE dessertid = ?";
						pstmt = conn.prepareStatement(sql);
						
						pick = choice.nextInt();
		    			choice.nextLine();
						
						pstmt.setInt(1, pick);
						
						rs = pstmt.executeQuery();
						
						if (rs.next()) {
							dvs = new DessertVO(
							rs.getInt(1)
						   ,rs.getString(2)
						   ,rs.getInt(3)
							);
							cvo.setWhatDs(dvs.getDsName());
						} else {
							System.out.println("데이터 없음!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
					}  whatMenu();			
				}
		
		//장바구니?
		public void bag() {
			System.out.println("1.취소 2.메인메뉴 3.결제");
			
			System.out.println(cvo);
	
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick==1) {
				System.out.println("취소를 원하시는 메뉴를 선택해주세요");
				System.out.println("1.버거, 2.음료, 3.디저트 4.이전화면");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				
				if(pick==1) {
					for (int i=0; i<cvo.bgList.size(); i++) {
						System.out.println(i+1 + "." + cvo.bgList.get(i));
					}
					
					System.out.println("취소를 원하시는 버거번호를 선택해주세요");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					cvo.bgList.remove(pick-1);
							
					System.out.println(cvo.bgList);
					bag();
					
					
				} else if (pick==2) {
					for (int i=0; i<cvo.dkList.size(); i++) {
						System.out.println(i+1 + "." + cvo.dkList.get(i));
					}
					
					System.out.println("취소를 원하시는 음료번호를 선택해주세요");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					cvo.dkList.remove(pick-1);
							
					System.out.println(cvo.dkList);
					bag();
					
				} else if (pick==3) {
					for (int i=0; i<cvo.dsList.size(); i++) {
						System.out.println(i+1 + "." + cvo.dsList.get(i));
					}
					
					System.out.println("취소를 원하시는 디저트번호를 선택해주세요");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					cvo.dsList.remove(pick-1);
							
					System.out.println(cvo.dsList);
					bag();
				} else if (pick==4) {
					bag();
				} else {
					System.out.println("다시 선택해주세요.");
					System.out.println("1.버거, 2.음료, 3.디저트 4.이전화면");
					
					pick = choice.nextInt();
					choice.nextLine();
					}
			}
			
			if (pick==2) {
				whatMenu();
			} 
			
			if(pick==3) {
				whereCheck();
			} else {
				System.out.println("다시 입력해주세요");
				bag();
			}
		} 
			
				
	
		public void whereCheck() {
			System.out.println("어디서 결제하시겠습니까?");
			System.out.println("1카운터,2가게");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
				System.out.println("카운터");
				cvo.setWhereCheck("카운터");
			} else if (pick == 2) {
				System.out.println("기계");
				cvo.setWhereCheck("가게");
			} else {
				System.out.println("다시 입력해주세요");
				whereCheck();
			}
			pirntBill();
		}

		
		public void pirntBill() {
			int sum = 0;
			
			System.out.println("영수증");
			System.out.println(cvo);
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				StringBuffer sql = new StringBuffer();
				
				
				sql.append("select price from burger where burgername = ?");
				System.out.println(sum);
				for (int i=1; i<=cvo.bgList.size(); i++) {
				
					pstmt=conn.prepareStatement(sql.toString());
					
					pstmt.setString(1, cvo.bgList.get(i-1));
					rs = pstmt.executeQuery();
					while(rs.next()) {
						sum += rs.getInt(1);						
					}				
				System.out.println(sum);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				StringBuffer sql = new StringBuffer();
				
				
				sql.append("select price from drink where drinkname = ?");
				System.out.println(sum);
				for (int i=1; i<=cvo.dkList.size(); i++) {
				
					pstmt=conn.prepareStatement(sql.toString());
					
					pstmt.setString(1, cvo.dkList.get(i-1));
					rs = pstmt.executeQuery();
					while(rs.next()) {
						sum += rs.getInt(1);						
					}				
				System.out.println(sum);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				StringBuffer sql = new StringBuffer();
				
				
				sql.append("select price from dessert where dessertname = ?");
				System.out.println(sum);
				for (int i=1; i<=cvo.dsList.size(); i++) {
				
					pstmt=conn.prepareStatement(sql.toString());
					
					pstmt.setString(1, cvo.dsList.get(i-1));
					rs = pstmt.executeQuery();
					while(rs.next()) {
						sum += rs.getInt(1);						
					}				
				System.out.println(sum);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
//					cvo.bgList.get(0);
//			System.out.println(bvo.getBgPrice());
			System.out.println("감사합니다.");
			start();
		}
		
		
		
		
		
		
}
