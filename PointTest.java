package burger_project1;

public class PointTest {

	public static void main(String[] args) {
		Point point = new Point();
		Sum sum = new Sum();
//		Bill bill =new 
//		
//		int bgSum = sum.burgerSum();  //버거 전체 가격
//		int drSum = sum.drinkSum();   //음료 전체가격
//		int dsSum = sum.dessertSum();  //디저트 전체 가격
//
		 //포인트
		
		point.date();
		
		sum.burgerSum();  //버거 전체 가격
		sum.drinkSum();   //음료 전체가격
		sum.dessertSum();  //디저트 전체 가격
		
		System.out.println("버거" + sum.burgerSum());
		System.out.println("음료" + sum.drinkSum());
		System.out.println("디저트" + sum.dessertSum());
		
		int i = sum.burgerSum();
		int j = sum.drinkSum();
		int k = sum.dessertSum();
		int sum1 = i+j+k;
		
		System.out.println("총합 :" + sum1);
		point.point();
	
		

	}

}
