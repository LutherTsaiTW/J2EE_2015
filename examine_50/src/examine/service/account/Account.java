package examine.service.account;

import java.util.List;

import examine.model.AccountModel;

public interface Account {
	void update(AccountModel feeModel) throws Exception;
	AccountModel find(AccountModel feeModel) throws Exception;
	List<AccountModel> list() throws Exception;
}
