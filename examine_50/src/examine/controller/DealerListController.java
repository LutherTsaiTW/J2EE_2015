package examine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import examine.exceptions.NullListException;
import examine.model.AccountModel;
import examine.service.account.Account;
import examine.viewmodel.DealerListModel;

@Controller("examine.controller.DealerListController")

@RequestMapping("/examine")
public class DealerListController {
	ApplicationContext context = new ClassPathXmlApplicationContext("examine/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("examine.resources.MessageDictionary");
	
	@RequestMapping(value = "/dealerList", method = RequestMethod.GET)
	public ModelAndView doList() {
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<AccountModel> accountListModel;
		
		Account account = (Account) context.getBean("account");
		
		try {
			accountListModel = account.list();
		} catch (NullListException e) {
			feeErrors.add(new FieldError("ListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("ListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		int totalCash = 0;
		
		for (AccountModel accountModel : accountListModel) {
			totalCash += accountModel.getCash();
		}
		
		DealerListModel dealerListModel = new DealerListModel();
		dealerListModel.setDealer(accountListModel.size());
		dealerListModel.setCash(totalCash);
		dealerListModel.setAccountList(accountListModel);
		
		String LIST = (String)context.getBean("dealerList");
		return new ModelAndView(LIST, "DealerListModel", dealerListModel);
	}
}
