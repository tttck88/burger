package burger_project1;

import java.util.Scanner;

public class Order {

		Scanner choice = new Scanner(System.in);
		int pick;
		
		/////////////�ֹ�?
		public void start(int pick) {
			System.out.println("�ܹ��� �ֹ��Ͻðڽ��ϱ�?");
		
			pick = choice.nextInt();
			choice.nextLine();
		
			if(pick == 1) {		
				whereEat(pick);
			}
		}
		
		////////////���� or ����ũ�ƿ�?
		public void whereEat(int pick) {
			System.out.println("��� ��ðڽ��ϱ�?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
			} else if (pick == 2) {
			}
		}
		
		////////////���� �޴�?
		public void whatMenu(int pick) {
			System.out.println("1: ���Ŵ�ǰ 2: ���� 3: ����Ʈ 4: ��Ʈ 5: ����");
			
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
		
		//���� ���ý�
		public void whatBg(int pick) {
			System.out.println("��������?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//���ż���if
			if(pick == 1) {				
			}
			System.out.println("���Ͻô� ������ �������ּ���");
			//���ż�����if
			if(pick == 1) {
				
			}
		}
		
		//���ἱ�ý�
		public void whatDk(int pick) {
			System.out.println("��������?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		//����Ʈ���ý�
		public void whatDs(int pick) {
			System.out.println("��������Ʈ?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		//��Ʈ���ý�
		public void whatSet(int pick) {
			System.out.println("������Ʈ?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			//if
		}
		
		//��𿡼� ����?
		public void whereCheck(int pick) {
			System.out.println("��� �����Ͻðڽ��ϱ�?");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
			} else if (pick == 2) {
			}		
		}
		
		public void pirntBill(int pick) {
			System.out.println("������");
			
			pick = choice.nextInt();
			choice.nextLine();	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	

}
