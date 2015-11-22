package test2.controller;

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

import test2.exceptions.NullListException;
import test2.model.AccountModel;
import test2.service.account.Account;

@Controller("test2.controller.ListController")

@RequestMapping("/test2")
public class ListController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test2/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test2.resources.MessageDictionary");
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView doList() {
		
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<AccountModel> accountListModel;
		Account account = (Account) context.getBean("account");
		
		try {
			accountListModel = account.list();
			if (accountListModel.size() == 0) {
				throw new NullListException();
			}
		} catch (NullListException e) {
			feeErrors.add(new FieldError("ListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("ListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String LIST = (String)context.getBean("listSUCCESS");
		return new ModelAndView(LIST, "AccountListModel", accountListModel);
	}
}
