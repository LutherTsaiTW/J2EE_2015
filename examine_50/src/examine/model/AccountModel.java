package examine.model;

public class AccountModel {
	private int id;
	private String name;
	private String email;
	private int cash;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail (String InputName) {
		this.email = InputName;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void setCash (int Input) {
		this.cash = Input;
	}
}
