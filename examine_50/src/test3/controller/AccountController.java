package test3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test3.exceptions.NullAccountException;
import test3.model.AccountModel;
import test3.service.account.Account;
import test3.viewmodel.AccountQueryModel;

@Controller("test3.controller.AccountController")

@RequestMapping("/test3")
public class AccountController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("test3/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test3.resources.MessageDictionary");
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView account() {
		String ACCOUNT = (String) context.getBean("ACCOUNT");
		return new ModelAndView(ACCOUNT);
	}
	
	@RequestMapping(value = "/doAccountQuery", method = RequestMethod.POST)
	public ModelAndView doQuery(@Valid AccountQueryModel queryModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		
		Account account = (Account) context.getBean("account");
		AccountModel accountModel = new AccountModel();
		accountModel.setId(Integer.valueOf(queryModel.getId()));
		
		try {
			accountModel = account.find(accountModel);
		} catch (NullAccountException nullAccountException) {
			feeErrors.add(new FieldError("AccountController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("AccountController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String SUCCESS = (String)context.getBean("accountSUCCESS");
		return new ModelAndView(SUCCESS, "AccountModel", accountModel);
	}
}
