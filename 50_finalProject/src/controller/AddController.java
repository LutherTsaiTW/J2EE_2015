package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.AdModel;
import model.MemberModel;

import org.springframework.beans.factory.annotation.Autowired;
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

import viewmodel.UploadAdPageModel;

@Controller("controller.finalProject.AddController")
@RequestMapping("/finalProject")
public class AddController {
	@Autowired
    public HttpSession session;
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/addAdvertise", method = RequestMethod.GET)
	public ModelAndView addAdvertise() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		MemberModel memberModel = new MemberModel();
		
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
		} catch (RestClientException e) {
			String statusCode = e.getMessage();
			if(statusCode.equals("404 Not Found")){
				feeErrors.add(new FieldError("AddController", "error.http", "登入已過期，請重新登入"+e.getMessage()));
			}
			else {
				feeErrors.add(new FieldError("AddController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("AddController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String ADD_AD = (String) context.getBean("ADD_AD");
		ModelAndView view = new ModelAndView(ADD_AD);
		view.addObject("userName", memberModel.getName());
		view.addObject("userToken", memberModel.getApplicationToken());
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		view.addObject("minDate", currentDate);
		return view;
	}
	
	@RequestMapping(value = "/doAddAdvertise", method = RequestMethod.POST)
	public ModelAndView doAddAdvertise(@Valid UploadAdPageModel uploadAdPageModel, BindingResult bindingResult) {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if (bindingResult.hasErrors())  {
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		String member_system = (String) context.getBean("member_system");
		MemberModel memberModel = new MemberModel();
		
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
		} catch (RestClientException e) {
			String statusCode = e.getMessage();
			if(statusCode.equals("404 Not Found")){
				feeErrors.add(new FieldError("AddController", "error.http", "登入已過期，請重新登入"+e.getMessage()));
			}
			else {
				feeErrors.add(new FieldError("AddController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("AddController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String ad_system = (String) context.getBean("ad_system");
		
		AdModel adModel = new AdModel();
		adModel.setAdOwnerToken(uploadAdPageModel.getAdOwnerToken());
		adModel.setAdTitle(uploadAdPageModel.getAdTitle());
		adModel.setAdDes(uploadAdPageModel.getAdDes());
		adModel.setAdImageLink(uploadAdPageModel.getAdImageLink());
		adModel.setAdRef(uploadAdPageModel.getAdRef());
		adModel.setAdImageHeight(Integer.valueOf(uploadAdPageModel.getAdImageHeight()));
		adModel.setAdImageWidth(Integer.valueOf(uploadAdPageModel.getAdImageWidth()));
		String[] date = uploadAdPageModel.getAdStartDate().split("-");
		StringBuilder dateBuilder = new StringBuilder(date[0]).append(date[1]).append(date[2]);
		adModel.setAdStartDate(Integer.valueOf(dateBuilder.toString()));
		date = uploadAdPageModel.getAdEndDate().split("-");
		dateBuilder = new StringBuilder(date[0]).append(date[1]).append(date[2]);
		adModel.setAdEndDate(Integer.valueOf(dateBuilder.toString()));
		adModel.setAdClick(0);
		
		int currentPayment = memberModel.getAdPaymentFee();
		currentPayment += Integer.valueOf(uploadAdPageModel.getFeeCount());
		memberModel.setAdPaymentFee(currentPayment);
		
		try {
			restTemplate.postForObject(ad_system, adModel, AdModel.class);
			restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
		}catch (RestClientException e) {
			feeErrors.add(new FieldError("AddController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch(Exception e){
			feeErrors.add(new FieldError("AddController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		return new ModelAndView("redirect:" + "manageAdvertise");
	}
}
