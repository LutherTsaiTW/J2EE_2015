package model;

public class MemberModel {
	private int memberID;
	private String name;
	private String email;
	private String account;
	private String password;
	private String applicationToken;
	private String bankToken;
	private String session;
	private int bankAccount;
	private int previlege;
	private int adIncomeFee;
	private int adPaymentFee;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
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

	public String getApplicationToken() {
		return applicationToken;
	}

	public void setApplicationToken(String applicationToken) {
		this.applicationToken = applicationToken;
	}

	public int getPrevilege() {
		return previlege;
	}

	public void setPrevilege(int previlege) {
		this.previlege = previlege;
	}

	public int getAdIncomeFee() {
		return adIncomeFee;
	}

	public void setAdIncomeFee(int adIncomeFee) {
		this.adIncomeFee = adIncomeFee;
	}

	public int getAdPaymentFee() {
		return adPaymentFee;
	}

	public void setAdPaymentFee(int adPaymentFee) {
		this.adPaymentFee = adPaymentFee;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankToken() {
		return bankToken;
	}

	public void setBankToken(String bankToken) {
		this.bankToken = bankToken;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

}
