package burger_project1;

import java.util.ArrayList;

public class CustomerVO {
	
	ArrayList<String> bgList = new ArrayList();
	ArrayList<String> dKList = new ArrayList();
	ArrayList<String> dSList = new ArrayList();
	
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
		bgList.add(whatBg);
	}
	public ArrayList getWhatDk() {
		return whatDk;
	}
	public void setWhatDk(String whatDk) {
		dKList.add(whatDk);
	}
	public ArrayList getWhatDs() {
		return whatDs;
	}
	public void setWhatDs(String whatDs) {
		dSList.add(whatDs);
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
		return "CustomerVO [bgList=" + bgList + ", dKList=" + dKList + ", dSList=" + dSList + "whatSet=" + whatSet +", whereEat=" + whereEat
				+ ", whereCheck=" + whereCheck + "]";
	}

	
	
	

	
	
	

}
