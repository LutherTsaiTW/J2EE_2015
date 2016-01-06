package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import model.AdModel;
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

@Controller("controller.finalProject.AdListController")
@RequestMapping("/finalProject")
public class AdListController {
	@Autowired
    public HttpSession session;
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/manageAdvertise", method = RequestMethod.GET)
	public ModelAndView addAdvertise() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		String ad_system = (String) context.getBean("ad_system");
		
		MemberModel memberModel = new MemberModel();
		List<AdModel> adList;
		
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
			try {
				getURL = new StringBuilder(ad_system).append("/getSelfAD?token=").append(memberModel.getApplicationToken());
				adList = restTemplate.getForObject(getURL.toString(), List.class);
			} catch (RestClientException restClientException) {
				String statusCode = restClientException.getMessage();
				if(statusCode.equals("404 Not Found")){
					feeErrors.add(new FieldError("AddController", "error.http", "沒有資料"));
				}
				else {
					feeErrors.add(new FieldError("AddController", "error.http", resource.getString("error.http")+"<br>"+restClientException.getMessage()));
				}
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}
		} catch (RestClientException e) {
			String statusCode = e.getMessage();
			if(statusCode.equals("404 Not Found")){
				feeErrors.add(new FieldError("AddController", "error.http", "登入已過期，請重新登入"));
			}
			else {
				feeErrors.add(new FieldError("AddController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("AddController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String ADD_LIST = (String) context.getBean("ADD_LIST");
		ModelAndView view = new ModelAndView(ADD_LIST);
		view.addObject("AdListModel", adList);
		view.addObject("userName", memberModel.getName());
		return view;
	}
}
