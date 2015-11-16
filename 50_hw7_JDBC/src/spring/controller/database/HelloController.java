package spring.controller.database;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import model.FeeModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exceptions.NullAccountException;
import service.fee.Fee;
import service.mail.GoogleMail;
import service.mail.Mail;
import service.message.Message;
import viewmodel.HelloModel;
import viewmodel.MessageModel;

@Controller("Spring.database.HelloController")

@RequestMapping("/database")
public class HelloController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		
		String HELLO = (String) context.getBean("HELLO");
		return new ModelAndView(HELLO);
	}
	
	@RequestMapping(value = "/doHello", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid HelloModel helloModel, BindingResult bindingResult){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldError());
		}
		
		Message msg = (Message) context.getBean("message");
		String result = msg.doMessage(helloModel.getName());
		
		Fee fee = (Fee) context.getBean("fee");
		FeeModel feeModel = new FeeModel();
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
				feeErrors.add(new FieldError("HelloController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}
		} catch (Exception e) {
			feeErrors.add(new FieldError("HelloController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Mail mail = (Mail) context.getBean("mail");
		String mailMsg = result + "\n" + "NT$ => " + Integer.toString(count);;
		String stamp = mail.sendMail(mailMsg);
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(result);
		msgModel.setStamp(stamp);
		msgModel.setCount(count);
		
		String SUCCESS = (String)context.getBean("helloSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
