package viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterPageModel {

	@NotEmpty(message="{error.accountEmptyInput}")
	@Pattern(regexp="[a-zA-Z0-9]+", message="{error.charOnly}") 
	private String account;
	
	@NotEmpty(message="{error.passwordEmptyInput}") 
	@Pattern(regexp="[a-zA-Z0-9]+", message="{error.charOnly}")
	private String password;
	
	@NotEmpty(message="{error.nameEmptyInput}") 
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	private String name;
	
	@NotEmpty(message="{error.emailEmptyInput}") 
	@Email(message="{error.eamilError}") 
	private String email;
	
	@NotEmpty(message="{error.bankAccountEmptyInput}") 
	@Pattern(regexp="[0-9]+", message="{error.numericOnly}")
	private String bankAccount;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	
	
}
