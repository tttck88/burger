package burger_project1;

public class BurgerVO {
	
	
	private int bgId;
	private String bgName;
	private int bgPrice;
	private int num;
	
	BurgerVO() {
	}
	
	BurgerVO(int bgId, String bgName, int bgPrice) {
		this.bgName = bgName;
		this.bgPrice = bgPrice;		
	}
	
	public int getBgId() {
		return bgId;
	}

	public void setBgId(int bgId) {
		this.bgId = bgId;
	}

	public String getBgName() {
		return bgName;
	}

	public void setBgName(String bgName) {
		this.bgName = bgName;
		int i =0;
		this.num=++i;
	}

	public int getBgPrice() {
		return bgPrice;
	}

	public void setBgPrice(int bgPrice) {
		this.bgPrice = bgPrice;
	}
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "BurgerVO [bgName=" + bgName + ", bgPrice=" + bgPrice + "]";
	}
	
	
	
	
	
	
}
