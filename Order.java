package burger_project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	
		Scanner choice = new Scanner(System.in);
		int pick;
		ArrayList list = new ArrayList();
		CustomerVO cvo = new CustomerVO();
		
		/////////////�ֹ�?
		public void start() {
			System.out.println("�ֹ�? 1");
			
			pick = choice.nextInt();
			choice.nextLine();
		
			if(pick == 1) {		
				whereEat();
			} else if (pick != 1) {  //�ٸ� ���� �Է� �� ��쿡�� ����ؼ� �ʱ�ȭ���� �����ֱ� ���� �ڱ��ڽ��� ȣ��
				start();
			}
		}
		
		////////////���� or ����ũ�ƿ�?
		public void whereEat() {
			System.out.println("��� ��ðڽ��ϱ�?");
			System.out.println("1.���� 2.����ũ�ƿ� 3.ó������");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) { //�������� ����
				cvo.setWhereEat("����");
			} else if (pick == 2) { //�������� ����
				cvo.setWhereEat("����ũ�ƿ�");
			} else if (pick == 3) {
				start();
			} else {
			System.out.println("�ٽ� �������ּ���.");
			whereEat();
			}
			whatMenu();
		}
		
		////////////���� �޴�?
		public void whatMenu() {
			System.out.println("1:���Ŵ�ǰ 2:���� 3:����Ʈ 4:��Ʈ 5:�������� 6:ó������ 7:��ٱ���");
			
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
				System.out.println("�ٽ� �������ּ���.");
				whatMenu();
				
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
				cvo.setWhatBg("ġ�����");
				//��ٱ��Ͽ� ����
			} else if (pick == 2) {
				cvo.setWhatBg("����ġ�����");
				//��ٱ��Ͽ� ����
			} else {
				System.out.println("�ٽ� �Է����ּ���");
				whatBg();
			}
			whatBg2();
		}
			
			public void whatBg2() {
			System.out.println("���Ͻô� ������ �������ּ���");
			System.out.println("1��ǰ,2��Ʈ");

			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				//���� ������ ���� ��Ʈ��
				whatMenu();
				//��ٱ��Ͽ� ����
			} else if (pick == 2) { //�Ϲݼ�Ʈ
				whatDk();
				//��ٱ��Ͽ� ����
			} else {
				System.out.println("�ٽ� �Է����ּ���");
				whatBg2();
			} 
		}
		
		//��Ʈ���ý�
		public void whatSet() {
			System.out.println("������Ʈ?");
			System.out.println("1ġ�Ʈ,2����ġ�Ʈ");
			
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("ġ�Ʈ");
				cvo.setWhatSet("ġ�Ʈ");
			} else if (pick == 2) {
				System.out.println("����ġ�Ʈ");
				cvo.setWhatSet("����ġ�Ʈ");
			} else {
				System.out.println("�ٽ� �Է����ּ���");
				whatSet();
			}
			whatMenu();
		}
		//���ἱ�ý�
		public void whatDk() {
			System.out.println("��������?");
			System.out.println("1�ݶ�,2���̴�");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {
				System.out.println("�ݶ�");
				cvo.setWhatDk("�ݶ�");
				whatMenu();
			} else if (pick == 2) {
				System.out.println("���̴�");
				cvo.setWhatDk("���̴�");
				whatMenu();
			} else {
				System.out.println("�ٽ� �Է����ּ���");
				whatDk();
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
				cvo.setWhatDs("���̽�ũ��");
				whatMenu();
			} else if (pick == 2) {
				System.out.println("��");
				cvo.setWhatDs("��");
				whatMenu();
			} else {
				System.out.println("�ٽ� �Է����ּ���");
				whatDs();
			}
			//if
		}
		
		//��ٱ���?
		public void bag() {
			System.out.println("1.��� 2.���θ޴� 3.����");
			
			System.out.println(cvo);
	
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick==1) {
				System.out.println("��Ҹ� ���Ͻô� �޴��� �������ּ���");
				System.out.println("1.����, 2.����, 3.����Ʈ 4.����ȭ��");
				
				pick = choice.nextInt();
				choice.nextLine();
				
				
				if(pick==1) {
					for (int i=0; i<cvo.bgList.size(); i++) {
						System.out.println(i+1 + "." + cvo.bgList.get(i));
					}
					
					System.out.println("��Ҹ� ���Ͻô� ���Ź�ȣ�� �������ּ���");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					cvo.bgList.remove(pick-1);
							
					System.out.println(cvo.bgList);
					bag();
					
					
				} else if (pick==2) {
					for (int i=0; i<cvo.dkList.size(); i++) {
						System.out.println(i+1 + "." + cvo.dkList.get(i));
					}
					
					System.out.println("��Ҹ� ���Ͻô� �����ȣ�� �������ּ���");
					
					pick = choice.nextInt();
					choice.nextLine();
					
					cvo.dkList.remove(pick-1);
							
					System.out.println(cvo.dkList);
					bag();
					
				} else if (pick==3) {
					for (int i=0; i<cvo.dsList.size(); i++) {
						System.out.println(i+1 + "." + cvo.dsList.get(i));
					}
					
					System.out.println("��Ҹ� ���Ͻô� ����Ʈ��ȣ�� �������ּ���");
					
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
			System.out.println("��� �����Ͻðڽ��ϱ�?");
			System.out.println("1ī����,2����");
			pick = choice.nextInt();
			choice.nextLine();
			
			if(pick == 1) {			
				System.out.println("ī����");
				cvo.setWhereCheck("ī����");
			} else if (pick == 2) {
				System.out.println("���");
				cvo.setWhereCheck("����");
			}
			pirntBill();
		}

		
		public void pirntBill() {
			System.out.println("������");
			System.out.println(cvo);
			
			System.out.println("�����մϴ�.");
			start();
		}
		
		
		
		
}
