package burger_project1;

import java.util.ArrayList;

public class CustomerVO {
	
	ArrayList<String> list = new ArrayList();
	
	private String whereEat;
	private String whatMenu;
	private ArrayList whatBg;
	private ArrayList whatDk;
	private ArrayList whatDs;
	private String whatSet;
	private String whereCheck;
	
	public String getWhereEat() {
		return whereEat;
	}
	public void setWhereEat(String whereEat) {
		this.whereEat = whereEat;
	}
	public String getWhatMenu() {
		return whatMenu;
	}
	public void setWhatMenu(String whatMenu) {
		this.whatMenu = whatMenu;
	}
	public ArrayList getWhatBg() {
		return whatBg;
	}
	public void setWhatBg(String whatBg) {
		list.add(whatBg);
		for (String str : list) {
			System.out.println(str);
		}
	}
	public ArrayList getWhatDk() {
		return whatDk;
	}
	public void setWhatDk(String whatDk) {
		list.add(whatDk);
	}
	public ArrayList getWhatDs() {
		return whatDs;
	}
	public void setWhatDs(String whatDs) {
		list.add(whatDs);
	}
	public String getWhatSet() {
		return whatSet;
	}
	public void setWhatSet(String whatSet) {
		this.whatSet = whatSet;
	}
	public String getWhereCheck() {
		return whereCheck;
	}
	public void setWhereCheck(String whereCheck) {
		this.whereCheck = whereCheck;
	}
	@Override
	public String toString() {
		return "CustomerVO [list=" + list + ", whereEat=" + whereEat + ", whatMenu=" + whatMenu + ", whatBg=" + whatBg
				+ ", whatDk=" + whatDk + ", whatDs=" + whatDs + ", whatSet=" + whatSet + ", whereCheck=" + whereCheck
				+ "]";
	}
	
	
	

	
	
	

}
