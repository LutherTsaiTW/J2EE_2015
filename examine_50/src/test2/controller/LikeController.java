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

import test1.viewmodel.QueryModel;
import test2.exceptions.NullAccountException;
import test2.model.AccountModel;
import test2.service.account.Account;
import test2.viewmodel.MessageModel;

@Controller("test2.controller.LikeController")

@RequestMapping("/test2")
public class LikeController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test2/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test2.resources.MessageDictionary");
	
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ModelAndView like(){
		String LikePage = (String) context.getBean("LikePage");
		return new ModelAndView(LikePage);
	}
	
	@RequestMapping(value = "/doLike", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid QueryModel helloModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			feeErrors.add(bindingResult.getFieldError());
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Account fee = (Account) context.getBean("account");
		AccountModel feeModel = new AccountModel();
		feeModel.setName(helloModel.getName());
		int count;
		
		try {
			feeModel = fee.find(feeModel);
			count = feeModel.getCount();
			
			if (count >= 0) {
				feeModel.setCount(++count);
				fee.update(feeModel);
			}
		} catch (NullAccountException nullAccountException) {
			count = 1;
			feeModel.setCount(count);
			
			try {
				fee.create(feeModel);
			} catch (Exception e) {
				feeErrors.add(new FieldError("LikeController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}
		} catch (Exception e) {
			feeErrors.add(new FieldError("LikeController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(helloModel.getName());
		msgModel.setCount(count);
		
		String SUCCESS = (String)context.getBean("likeSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
