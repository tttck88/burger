package com.mystudy.burgerproject;

public class Payment {
	
public void Cardpayment(int pick,String id) {
		
		if(whatGrade(id).equals("3���")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.95)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
					+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
		} else if (whatGrade(id).equals("2���")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.90)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
					+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
		} else if (whatGrade(id).equals("1���")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.85)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
					+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
		} else {
			System.out.println(burgersum(id)+drinksum(id)+dessertsum(id)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
					+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
		}
	}
	
	public void Cashpayment(int pick, String id) {
			
			if(whatGrade(id).equals("3���")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.95)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
			} else if (whatGrade(id).equals("2���")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.90)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
			} else if (whatGrade(id).equals("1���")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.85)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
			} else {
				System.out.println(burgersum(id)+drinksum(id)+dessertsum(id)-pick+ " ���� ī��� ������ �Ǿ����ϴ�.\n"
						+ " �����մϴ�! �� �湮���ּ���! ^0^ !!");
			}
	}

}
