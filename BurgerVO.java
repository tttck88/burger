package burger_project1;

public class BurgerVO {
	
	private String bgName;
	private String bgPrice;
	
	BurgerVO(String bgName, String bgPrice) {
		this.bgName = bgName;
		this.bgPrice = bgPrice;		
	}

	public String getBgName() {
		return bgName;
	}

	public void setBgName(String bgName) {
		this.bgName = bgName;
	}

	public String getBgPrice() {
		return bgPrice;
	}

	public void setBgPrice(String bgPrice) {
		this.bgPrice = bgPrice;
	}

	@Override
	public String toString() {
		return "BurgerVO [bgName=" + bgName + ", bgPrice=" + bgPrice + "]";
	}
	
	
	
	
	
	
}
