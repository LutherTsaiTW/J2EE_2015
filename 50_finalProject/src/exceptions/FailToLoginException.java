package exceptions;

public class FailToLoginException extends Exception {
	public FailToLoginException() {
		super("error.loginFail");
	}
}
