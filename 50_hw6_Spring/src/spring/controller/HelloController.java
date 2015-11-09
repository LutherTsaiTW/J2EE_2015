package spring.controller;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.mail.GoogleMail;
import service.mail.Mail;
import service.message.Message;
import spring.viewmodel.HelloModel;
import spring.viewmodel.MessageModel;

@Controller("Spring.HelloController")
public class HelloController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/spring.xml");
		
		String HELLO = (String) context.getBean("HELLO");
		return new ModelAndView(HELLO);
	}
	
	@RequestMapping(value = "/doHello", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid HelloModel helloModel, BindingResult bindingResult){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/spring.xml");
		
		if(bindingResult.hasErrors()){
			String ERROR = (String) context.getBean("ERROR");
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldError());
		}
		
		Message msg = (Message) context.getBean("message");
		String result = msg.doHello(helloModel.getName());
		
		Mail mail = new GoogleMail();
		String stamp = mail.sendMail(result);
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(result);
		msgModel.setStamp(stamp);
		
		String SUCCESS = (String)context.getBean("SUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}

}
