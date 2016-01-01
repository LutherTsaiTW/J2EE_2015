package controller;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import viewmodel.RegisterPageModel;


@Controller("controller.webservice.HelloController")
@RequestMapping("/webservice")
public class RegisterController {
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
		String REGISTER = (String) context.getBean("REGISTER");
		return new ModelAndView(REGISTER);
	}
	
	@RequestMapping(value = "/doHello", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid RegisterPageModel registerPageModel, BindingResult bindingResult){		
		ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
		String ERROR = (String) context.getBean("ERROR");
		
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if (bindingResult.hasErrors())  {
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		String message_service = (String) context.getBean("message_service");
		String mail_service = (String) context.getBean("mail_service");
		String fee_service =(String) context.getBean("fee_service");
		
		String message_provider = message_service.split("/")[3].split("_")[0];
		String mail_provider = mail_service.split("/")[3].split("_")[0];
		String fee_provider = fee_service.split("/")[3].split("_")[0];
		
		String name = helloModel.getName();
		
		return new ModelAndView(SUCCESS, "MessageProviderModel", messageProviderModel);
	}
}
