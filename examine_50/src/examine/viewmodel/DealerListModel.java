package examine.viewmodel;

import java.util.List;

import examine.model.AccountModel;

public class DealerListModel {
	private int dealer;
	private int cash;
	private List<AccountModel> accountList;
	
	public int getDealer() {
		return dealer;
	}
	
	public void setDealer(int dealer) {
		this.dealer = dealer;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public List<AccountModel> getAccountList() {
		return accountList;
	}
	
	public void setAccountList(List<AccountModel> list) {
		this.accountList = list;
	}
}
