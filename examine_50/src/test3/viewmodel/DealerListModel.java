package test3.viewmodel;

import java.util.List;

import test3.model.AccountModel;

public class DealerListModel {
	private int dealer;
	private int cash;
	private int asset;
	private int count;
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
	
	public int getAsset() {
		return asset;
	}
	
	public void setAsset(int asset) {
		this.asset = asset;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public List<AccountModel> getAccountList() {
		return accountList;
	}
	
	public void setAccountList(List<AccountModel> list) {
		this.accountList = list;
	}
}
