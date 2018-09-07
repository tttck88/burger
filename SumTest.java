package burger_project1;

public class SumTest {

	public static void main(String[] args) {
		Sum sum = new Sum();
		
		int bgSum = sum.burgerSum();
		int drSum = sum.drinkSum();
		int dsSum = sum.dessertSum();
		
		
		System.out.println("버거가격" + bgSum);
		System.out.println("음료가격" + drSum);
		System.out.println("디저트가격" + dsSum);
		
		
		
		

		

		
//		int sum =0;
//		
//		sum +=sum.burgerSum();
//		

	}

}
