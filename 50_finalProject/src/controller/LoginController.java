package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import exceptions.FailToLoginException;
import viewmodel.LoginPageModel;

@Controller("controller.finalProject.LoginController")
@RequestMapping("/finalProject")
public class LoginController {
	@Autowired
    public HttpSession session;
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		session.invalidate();
		String LOGIN = (String) context.getBean("LOGIN");
		String ad_system = (String) context.getBean("ad_system");
		StringBuilder getURL = new StringBuilder(ad_system).append("/getAD?token=6ac8788402456e6bb9867e511c80b290");
		String html = restTemplate.getForObject(getURL.toString(), String.class);
		return new ModelAndView(LOGIN, "adImport", html);
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid LoginPageModel loginPageModel, BindingResult bindingResult){	
		String ERROR = (String) context.getBean("ERROR");
		
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if (bindingResult.hasErrors())  {
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		String member_system = (String) context.getBean("member_system");
		
		MemberModel memberModel = new MemberModel();
		memberModel.setAccount(loginPageModel.getAccount());
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(loginPageModel.getPassword().getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			loginPageModel.setPassword(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			StringBuilder postURL = new StringBuilder(member_system).append("/authencate");
			boolean loginStatus = restTemplate.postForObject(postURL.toString(), loginPageModel, Boolean.class);
			if (!loginStatus) {
				throw new FailToLoginException();
			}
			else {
				StringBuilder getURL = new StringBuilder(member_system).append("/findByAccount?account=").append(memberModel.getAccount());
				memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
				String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime());
				String newSessionString = currentDate + memberModel.getAccount();
				try {
					md = MessageDigest.getInstance("MD5");
					md.update(newSessionString.getBytes());
					byte[] digest = md.digest();
					StringBuffer sb = new StringBuffer();
					for (byte b : digest) {
						sb.append(String.format("%02x", b & 0xff));
					}
					session.setAttribute("SESSIONID", sb.toString());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				memberModel.setSession(session.getAttribute("SESSIONID").toString());
				restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
			}
		}catch (FailToLoginException e) {
			feeErrors.add(new FieldError("LoginController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		return new ModelAndView("redirect:menu");
	}

}
