package viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginPageModel {
	@NotEmpty(message="{error.accountEmptyInput}")
	@Pattern(regexp="[a-zA-Z0-9]+", message="{error.charOnly}") 
	private String account;
	
	@NotEmpty(message="{error.passwordEmptyInput}") 
	@Pattern(regexp="[a-zA-Z0-9]+", message="{error.charOnly}")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
