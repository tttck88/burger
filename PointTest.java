package burger_project1;

public class PointTest {

	public static void main(String[] args) {
		Point point = new Point();
		Sum sum = new Sum();
//		Bill bill =new 
//		
//		int bgSum = sum.burgerSum();  //���� ��ü ����
//		int drSum = sum.drinkSum();   //���� ��ü����
//		int dsSum = sum.dessertSum();  //����Ʈ ��ü ����
//
		 //����Ʈ
		
		point.date();
		
		sum.burgerSum();  //���� ��ü ����
		sum.drinkSum();   //���� ��ü����
		sum.dessertSum();  //����Ʈ ��ü ����
		
		System.out.println("����" + sum.burgerSum());
		System.out.println("����" + sum.drinkSum());
		System.out.println("����Ʈ" + sum.dessertSum());
		
		int i = sum.burgerSum();
		int j = sum.drinkSum();
		int k = sum.dessertSum();
		int sum1 = i+j+k;
		
		System.out.println("���� :" + sum1);
		point.point();
	
		

	}

}
