package burger_project1;

import java.util.Scanner;

public class Order {
	
		Scanner choice = new Scanner(System.in);
		int pick;
		BurgerVO vo = new BurgerVO();
		
		/////////////�ֹ�?
		public void start() {
			System.out.println("Ŭ�� 1");
			
			pick = choice.nextInt();
			choice.nextLine();
		
			if(pick == 1) {		
				whereEat();
			}
		}
		
		////////////���� or ����ũ�ƿ�?
		public void whereEat() {
			System.out.println("��� ��ðڽ��ϱ�?");
			System.out.println("1.���� 2.����ũ�ƿ�");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) { //�������� ����
				whatMenu();
			} else if (pick == 2) { //�������� ����
				whatMenu();
			}
		}
		
		////////////���� �޴�?
		public void whatMenu() {
			System.out.println("1: ���Ŵ�ǰ 2: ���� 3: ����Ʈ 4: ��Ʈ 5: ó������ 6:����");
			
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
				start();
			} else if (pick == 6) {
				whereCheck();
			}
		}
		
		//���� ���ý�
		public void whatBg() {
			System.out.println("��������?");
			System.out.println("1.ġ����� 2.����ġ�����");

			pick = choice.nextInt();
			choice.nextLine();
			
			//���ż���if
			if(pick == 1) {	
				System.out.println("ġ�����");
				//��ٱ��Ͽ� ����
			} else if (pick == 2) {
				System.out.println("����ġ�����");
				//��ٱ��Ͽ� ����
			}
			
			pick = choice.nextInt();
			choice.nextLine();
			
			System.out.println("���Ͻô� ������ �������ּ���");
			//���ż�����if
			System.out.println("1��ǰ,2��Ʈ,������Ʈ");
			pick = choice.nextInt();
			choice.nextLine();
			if(pick == 1) {
				//���� ������ ���� ��Ʈ��
				whatMenu();
				System.out.println("��ǰ");				
				//��ٱ��Ͽ� ����
			} else if (pick == 2) {
				whatDk();
				//��ٱ��Ͽ� ����
			} else if (pick == 3) {
				whatDk();
			}
		}
		
		//���ἱ�ý�
		public void whatDk() {
			System.out.println("��������?");
			System.out.println("1�ݶ�,2���̴�");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("�ݶ�");			
				whatMenu();
			} else if (pick == 2) {
				System.out.println("���̴�");
				whatMenu();
			}
		}
		//����Ʈ���ý�
		public void whatDs() {
			System.out.println("��������Ʈ?");
			System.out.println("1���̽�ũ��,2��");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("���̽�ũ��");	
				whatMenu();
			} else if (pick == 2) {
				System.out.println("��");
				whatMenu();
			}
			//if
		}
		//��Ʈ���ý�
		public void whatSet() {
			System.out.println("������Ʈ?");
			System.out.println("1ġ�Ʈ,2����ġ�Ʈ");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("ġ�Ʈ");
				whatMenu();
			} else if (pick == 2) {
				System.out.println("����ġ�Ʈ");
				whatMenu();
			}
			
			//if
		}
		
		//��𿡼� ����?
		public void whereCheck() {
			System.out.println("��� �����Ͻðڽ��ϱ�?");
			System.out.println("1ī����,2����");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
				System.out.println("ī����");
			} else if (pick == 2) {
				System.out.println("���");
			}		
		}
		
		public void pirntBill() {
			System.out.println("������");
			
			pick = choice.nextInt();
			choice.nextLine();	
		}
		
		
		
		
}
