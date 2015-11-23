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

import test2.exceptions.NotEnoughLikeException;
import test2.exceptions.NullAccountException;
import test2.model.AccountModel;
import test2.service.account.Account;
import test2.service.mail.Mail;
import test2.viewmodel.AppreciateModel;
import test2.viewmodel.MessageModel;

@Controller("test2.controller.AppreciateController")

@RequestMapping("/test2")
public class AppreciateController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test2/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test2.resources.MessageDictionary");
	
	@RequestMapping(value = "/appreciate", method = RequestMethod.GET)
	public ModelAndView appreciate(){
		String APPRECIATE = (String) context.getBean("APPRECIATE");
		return new ModelAndView(APPRECIATE);
	}
	
	@RequestMapping(value = "/doAppreciate", method = RequestMethod.POST)
	public ModelAndView doAppreciate(@Valid AppreciateModel appreciateModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			for (FieldError err : bindingResult.getFieldErrors()) {
				feeErrors.add(err);
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Account account = (Account) context.getBean("account");
		AccountModel accountModel = new AccountModel();
		accountModel.setName(appreciateModel.getName());
		int count, appreciateValue;
		
		try {
			accountModel = account.find(accountModel);
			count = accountModel.getCount();
			appreciateValue = Integer.valueOf(appreciateModel.getLikeAmount());
			if (count >= appreciateValue) {
				if((count - appreciateValue) == 0) {
					account.delete(accountModel);
				}
				else if ((count - appreciateValue) > 0){
					accountModel.setCount((count - appreciateValue));
					account.update(accountModel);
				}
			}else {
				throw new NotEnoughLikeException();
			}
		} catch (NullAccountException | NotEnoughLikeException e) {
			feeErrors.add(new FieldError("AppreciateController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("AppreciateController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Mail mail = (Mail) context.getBean("mail");
		String mailMsg = "非常感謝，" + appreciateModel.getName();
		String stamp = mail.sendMail(mailMsg);
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(appreciateModel.getName());
		msgModel.setStamp(appreciateModel.getLikeAmount());
		
		String SUCCESS = (String)context.getBean("appreciateSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
