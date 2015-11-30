package test3.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountQueryModel {

	@NotEmpty(message="{error.idEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
