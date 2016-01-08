package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.AdModel;
import model.MemberModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.finalProject.ClickController")
@RequestMapping("/finalProject")
public class ClickController {
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/doClick", method = RequestMethod.GET)
	public ModelAndView doClick(@RequestParam String token, @RequestParam String adId) {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		String ad_system = (String) context.getBean("ad_system");
		
		MemberModel memberModel = new MemberModel();
		AdModel adModel = new AdModel();
		
		String redirectLink;
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findByToken?token=").append(token);
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
			int currentIncome = memberModel.getAdIncomeFee();
			currentIncome += 1;
			memberModel.setAdIncomeFee(currentIncome);
			restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
			
			getURL = new StringBuilder(ad_system).append("/getAdByID?adId=").append(adId);
			adModel = restTemplate.getForObject(getURL.toString(), AdModel.class);
			redirectLink = adModel.getAdRef();
			int currentClick = adModel.getAdClick();
			currentClick += 1;
			adModel.setAdClick(currentClick);
			restTemplate.put(new StringBuilder(ad_system).append("/").append(adModel.getAdID()).toString(), adModel);
			
			getURL = new StringBuilder(member_system).append("/findByToken?token=").append(adModel.getAdOwnerToken());
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
			int currentPayment = memberModel.getAdPaymentFee();
			currentPayment += 2;
			memberModel.setAdPaymentFee(currentPayment);
			restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
		}catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		ModelAndView view = new ModelAndView("redirect:" + redirectLink);
		return view;
	}
}
