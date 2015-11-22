package test2.controller;

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

import test2.exceptions.NullAccountException;
import test2.model.AccountModel;
import test2.service.account.Account;
import test2.viewmodel.MessageModel;
import test2.viewmodel.QueryModel;

@Controller("test2.controller.QueryController")

@RequestMapping("/test2")
public class QueryController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test2/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test2.resources.MessageDictionary");
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelAndView query(){
		String QUERY = (String) context.getBean("QUERY");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value = "/doQuery", method = RequestMethod.POST)
	public ModelAndView doQuery(@Valid QueryModel queryModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			feeErrors.add(bindingResult.getFieldError());
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		
		Account fee = (Account) context.getBean("account");
		AccountModel feeModel = new AccountModel();
		feeModel.setName(queryModel.getName());
		int totalAmount = 0;
		
		try {
			feeModel = fee.find(feeModel);
			totalAmount = feeModel.getCount();
		} catch (NullAccountException nullAccountException) {
			feeErrors.add(new FieldError("QueryController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("InsertController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(queryModel.getName());
		msgModel.setCount(totalAmount);
		
		String SUCCESS = (String)context.getBean("querySUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
