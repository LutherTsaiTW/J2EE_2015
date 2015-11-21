package test1.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class SellCarModel {
	@NotEmpty(message="{error.customerEmpty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String customerName;
	
	@NotEmpty(message="{error.carEmpty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String carName;
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
	}
	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String amount) {
		this.carName = amount;
	}
}
