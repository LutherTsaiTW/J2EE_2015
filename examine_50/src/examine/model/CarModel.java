package examine.model;

public class CarModel {
	private int id;
	private String name;
	private int price;
	private int ownerId;
	
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice (int Input) {
		this.price = Input;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId (int Input) {
		this.ownerId = Input;
	}
}
