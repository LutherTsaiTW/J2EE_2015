package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import model.MemberModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.finalProject.MenuController")
@RequestMapping("/finalProject")
public class MenuController {
	@Autowired
    public HttpSession session;
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView login() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		MemberModel memberModel = new MemberModel();
		
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
		} catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String LOGIN_SUCCESS = (String) context.getBean("LOGIN_SUCCESS");
		return new ModelAndView(LOGIN_SUCCESS, "userName", memberModel.getName());
	}
	
}
