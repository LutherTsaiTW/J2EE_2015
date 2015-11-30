package test3.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class BuildCarModel {

	@NotEmpty(message="{error.idEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String id;
	
	@NotEmpty(message="{error.nameEmpty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String name;
	
	@NotEmpty(message="{error.priceEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String price;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
