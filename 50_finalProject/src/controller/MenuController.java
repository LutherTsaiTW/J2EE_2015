package controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		String LOGIN_SUCCESS = (String) context.getBean("LOGIN_SUCCESS");
		System.out.print(session.getAttribute("SESSIONID").toString() + "\n");
		return new ModelAndView(LOGIN_SUCCESS);
	}
	
}
