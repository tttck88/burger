package burger_project1;

public class loginTest {

	public static void main(String[] args) {
		Login login = new Login();
		
//		login.createAccount();
		
		String id = login.login();
		
		System.out.println(id);
		
	}
}