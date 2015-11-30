package examine.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class BidCarModel {
	@NotEmpty(message="{error.idEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String id;
	
	@NotEmpty(message="{error.bidCarEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String name;
	
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
}
