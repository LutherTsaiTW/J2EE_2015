package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Spring;
import javax.validation.Valid;

import model.MemberModel;

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

import exceptions.AccountAlreadyExists;
import viewmodel.RegisterPageModel;


@Controller("controller.finalProject.RegisterController")
@RequestMapping("/finalProject")
public class RegisterController {
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		String REGISTER = (String) context.getBean("REGISTER");
		return new ModelAndView(REGISTER);
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public ModelAndView doHello(@Valid RegisterPageModel registerPageModel, BindingResult bindingResult){	
		String ERROR = (String) context.getBean("ERROR");
		
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if (bindingResult.hasErrors())  {
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		String member_system = (String) context.getBean("member_system");
		
		MemberModel memberModel = new MemberModel();
		memberModel.setAccount(registerPageModel.getAccount());
		memberModel.setName(registerPageModel.getName());
		memberModel.setEmail(registerPageModel.getEmail());
		
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(registerPageModel.getPassword().getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			memberModel.setPassword(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			md = MessageDigest.getInstance("MD5");
			String token = registerPageModel.getPassword() + registerPageModel.getAccount();
			md.update(token.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			memberModel.setApplicationToken(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		memberModel.setAdIncomeFee(0);
		memberModel.setAdPaymentFee(0);
		memberModel.setPrevilege(111);
		
		
		try {
			try{
				StringBuilder getURL = new StringBuilder(member_system).append("/findByName?name=").append(registerPageModel.getAccount());
				memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
				throw new AccountAlreadyExists();
			}catch (AccountAlreadyExists e) {
				feeErrors.add(new FieldError("CarListController", e.getMessage(), resource.getString(e.getMessage())));
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}catch (RestClientException restClientException){	
				String statusCode = restClientException.getMessage();
				if(statusCode.equals("404 Not Found")){
					
				}else{
					throw restClientException;
				}
			}
		}catch (RestClientException e) {
			feeErrors.add(new FieldError("FeeController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch(Exception e){
			feeErrors.add(new FieldError("FeeController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}

		return new ModelAndView("redirect:../../index.jsp");
	}
}
