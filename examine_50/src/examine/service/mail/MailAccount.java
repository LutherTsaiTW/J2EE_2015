package examine.service.mail;

public interface MailAccount {
	// mail server
	public static final String host = "smtp.gmail.com";
	public static final String port = "465";

	// mail account
	public static final String account = "javakingntut";
	public static final String password = "ntut2015";

	// mail
	public static final String sender = "javakingntut@gmail.com";
	//public static final String reciver = "Luther0429@gmail.com";
	public static String reciver = "ntut.java.ta2@gmail.com";
	public static final String subject = "Java Test Mail";
}
