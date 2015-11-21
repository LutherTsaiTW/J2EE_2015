package test1.viewmodel;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;


public class HelloModel {
	
	@NotEmpty(message="{error.carEmpty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String name;
	@NotEmpty(message="{error.amountEmpty}")
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String amount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
