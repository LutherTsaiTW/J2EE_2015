package test2.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class AppreciateModel {
	@NotEmpty(message="{error.customerEmpty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String accountName;
	
	@NotEmpty(message="{error.carEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String appreaciateAmount;
	
	public String getName() {
		return accountName;
	}

	public void setName(String name) {
		this.accountName = name;
	}
	
	public String getLikeAmount() {
		return appreaciateAmount;
	}

	public void setLikeAmount(String amount) {
		this.appreaciateAmount = amount;
	}
}
