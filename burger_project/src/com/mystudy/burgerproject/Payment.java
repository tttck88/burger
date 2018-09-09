package com.mystudy.burgerproject;

public class Payment {
	
public void Cardpayment(int pick,String id) {
		
		if(whatGrade(id).equals("3등급")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.95)-pick+ " 원이 카드로 결제가 되었습니다.\n"
					+ " 감사합니다! 또 방문해주세요! ^0^ !!");
		} else if (whatGrade(id).equals("2등급")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.90)-pick+ " 원이 카드로 결제가 되었습니다.\n"
					+ " 감사합니다! 또 방문해주세요! ^0^ !!");
		} else if (whatGrade(id).equals("1등급")) {
			System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.85)-pick+ " 원이 카드로 결제가 되었습니다.\n"
					+ " 감사합니다! 또 방문해주세요! ^0^ !!");
		} else {
			System.out.println(burgersum(id)+drinksum(id)+dessertsum(id)-pick+ " 원이 카드로 결제가 되었습니다.\n"
					+ " 감사합니다! 또 방문해주세요! ^0^ !!");
		}
	}
	
	public void Cashpayment(int pick, String id) {
			
			if(whatGrade(id).equals("3등급")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.95)-pick+ " 원이 카드로 결제가 되었습니다.\n"
						+ " 감사합니다! 또 방문해주세요! ^0^ !!");
			} else if (whatGrade(id).equals("2등급")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.90)-pick+ " 원이 카드로 결제가 되었습니다.\n"
						+ " 감사합니다! 또 방문해주세요! ^0^ !!");
			} else if (whatGrade(id).equals("1등급")) {
				System.out.println(((burgersum(id)+drinksum(id)+dessertsum(id))*0.85)-pick+ " 원이 카드로 결제가 되었습니다.\n"
						+ " 감사합니다! 또 방문해주세요! ^0^ !!");
			} else {
				System.out.println(burgersum(id)+drinksum(id)+dessertsum(id)-pick+ " 원이 카드로 결제가 되었습니다.\n"
						+ " 감사합니다! 또 방문해주세요! ^0^ !!");
			}
	}

}
