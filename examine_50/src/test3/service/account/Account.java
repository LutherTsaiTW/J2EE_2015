package test3.service.account;

import java.util.List;

import test3.model.AccountModel;

public interface Account {
	void update(AccountModel feeModel) throws Exception;
	AccountModel find(AccountModel feeModel) throws Exception;
	List<AccountModel> list() throws Exception;
}
