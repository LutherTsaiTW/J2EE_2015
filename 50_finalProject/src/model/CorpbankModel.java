package model;

public class CorpbankModel {
	private int value;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getCorpToken() {
		return corpToken;
	}
	public void setCorpToken(String corpToken) {
		this.corpToken = corpToken;
	}
	private String corpToken;
}
