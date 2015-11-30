package test3.model;

public class AccountModel {
	private int id;
	private String name;
	private int cash;
	private int asset;
	private int count;
	
	public int getId() {
		return id;
	}
	
	public void setId (int InputId) {
		this.id = InputId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName (String InputName) {
		this.name = InputName;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void setCash (int Input) {
		this.cash = Input;
	}
	
	public int getAsset() {
		return asset;
	}
	
	public void setAsset (int Input) {
		this.asset = Input;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount (int Input) {
		this.count = Input;
	}
}
