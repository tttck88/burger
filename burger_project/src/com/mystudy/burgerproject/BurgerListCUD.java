package com.mystudy.burgerproject;

import java.util.ArrayList;

public interface BurgerListCUD {
	//���̺� �ִ� ����Ÿ�� ȭ�� ���
	ArrayList<BurgerVO> printDataAll();
	
	//ȸ������� �޾Ƽ� �ϰ� �Է� ó��
	int insertListBurger(ArrayList<BurgerVO> list);
	
	int insertListCust(ArrayList<CustomerVO> list);
	
	int insertListDrink(ArrayList<DrinkVO> list);
	
	int insertListDessert(ArrayList<DessertVO> list);
	
}
