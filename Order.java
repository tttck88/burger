package burger_project1;

import java.util.Scanner;

public class Order {

		Scanner choice = new Scanner(System.in);
		int pick;
		
		/////////////주문?
		public void start(int pick) {
			System.out.println("햄버거 주문하시겠습니까?");
		
			pick = choice.nextInt();
			choice.nextLine();
		
			if(pick == 1) {		
				whereEat(pick);
			}
		}
		
		////////////매장 or 테이크아웃?
		public void whereEat(int pick) {
			System.out.println("어디서 드시겠습니까?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
			} else if (pick == 2) {
			}
		}
		
		////////////무슨 메뉴?
		public void whatMenu(int pick) {
			System.out.println("1: 버거단품 2: 음료 3: 디저트 4: 세트 5: 종료");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
				whatBg(pick);
			} else if (pick == 2) {
				whatDk(pick);
			} else if (pick == 3) {
				whatDs(pick);
			} else if (pick == 4) {
				whatSet(pick);
			} else if (pick == 5) {
				start(pick);
			}
		}
		
		//버거 선택시
		public void whatBg(int pick) {
			System.out.println("무슨버거?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//버거선택if
			if(pick == 1) {				
			}
			System.out.println("원하시는 구성을 선택해주세요");
			//버거선택후if
			if(pick == 1) {
				
			}
		}
		
		//음료선택시
		public void whatDk(int pick) {
			System.out.println("무슨음료?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		//디저트선택시
		public void whatDs(int pick) {
			System.out.println("무슨디저트?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		//세트선택시
		public void whatSet(int pick) {
			System.out.println("무슨세트?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		
		//어디에서 결제?
		public void whereCheck(int pick) {
			System.out.println("어디서 결제하시겠습니까?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
			} else if (pick == 2) {
			}		
		}
		
		public void pirntBill(int pick) {
			System.out.println("영수증");
			
			pick = choice.nextInt();
			choice.nextLine();	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	

}
