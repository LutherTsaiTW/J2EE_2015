package test2.service.account;

import java.util.List;

import test2.model.AccountModel;

public interface Account {
	void create(AccountModel feeModel) throws Exception;
	void update(AccountModel feeModel) throws Exception;
	void delete(AccountModel feeModel) throws Exception;
	AccountModel find(AccountModel feeModel) throws Exception;
	List<AccountModel> list() throws Exception;

}
