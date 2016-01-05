package exceptions;

public class AccountAlreadyExists extends Exception {
	public AccountAlreadyExists() {
		super("error.accountExist");
	}
}
