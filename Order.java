package burger_project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	
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
		
		//버거 선택시
		public void whatBg() {
			System.out.println("무슨버거?");
			System.out.println("1.치즈버거 2.더블치즈버거");

			pick = choice.nextInt();
			choice.nextLine();
			
			//버거선택if
			if(pick == 1) {	
				cvo.setWhatBg("치즈버거");
				//장바구니에 저장
			} else if (pick == 2) {
				cvo.setWhatBg("더블치즈버거");
				//장바구니에 저장
			} else {
				System.out.println("다시 입력해주세요");
				whatBg();
			}
			whatBg2();
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
			System.out.println("무슨음료?");
			System.out.println("1콜라,2사이다");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("콜라");
				cvo.setWhatDk("콜라");
				whatMenu();
			} else if (pick == 2) {
				System.out.println("사이다");
				cvo.setWhatDk("사이다");
				whatMenu();
			} else {
				System.out.println("다시 입력해주세요");
				whatDk();
			}
		}
		//디저트선택시
		public void whatDs() {
			System.out.println("무슨디저트?");
			System.out.println("1아이스크림,2윙");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("아이스크림");	
				cvo.setWhatDs("아이스크림");
				whatMenu();
			} else if (pick == 2) {
				System.out.println("윙");
				cvo.setWhatDs("윙");
				whatMenu();
			} else {
				System.out.println("다시 입력해주세요");
				whatDs();
			}
			//if
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
				}
			}
			
			if (pick==2) {
				whatMenu();
			}
			
			if(pick==3) {
				whereCheck();
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
			}
			pirntBill();
		}

		
		public void pirntBill() {
			System.out.println("영수증");
			System.out.println(cvo);
			
			System.out.println("감사합니다.");
			start();
		}
		
		
		
		
}
