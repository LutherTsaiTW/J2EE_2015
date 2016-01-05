package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import model.BanktokenModel;
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
		String bank_system = (String) context.getBean("bank_system");
		
		MemberModel memberModel = new MemberModel();
		
		
		try {
			try{
				StringBuilder getURL = new StringBuilder(member_system).append("/findByAccount?account=").append(registerPageModel.getAccount());
				memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
				throw new AccountAlreadyExists();
			}catch (AccountAlreadyExists e) {
				feeErrors.add(new FieldError("RegisterController", e.getMessage(), resource.getString(e.getMessage())));
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}catch (RestClientException restClientException){	
				String statusCode = restClientException.getMessage();
				if(statusCode.equals("404 Not Found")){
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
					memberModel.setBankAccount(Integer.valueOf(registerPageModel.getBankAccount()));
					
//					StringBuilder getBankTokenURL = new StringBuilder(bank_system).append("/getToken");
//					BanktokenModel bankTokenPostModel = new BanktokenModel();
//					bankTokenPostModel.setId(registerPageModel.getAccount());
//					bankTokenPostModel.setToken("0BBFD968AD302F0D3ACC9655963F0622");
//					String bankToken = restTemplate.postForObject(getBankTokenURL.toString(), bankTokenPostModel , String.class);
//					memberModel.setBankToken(bankToken);
					
					restTemplate.postForObject(member_system, memberModel, MemberModel.class);
				}else{
					throw restClientException;
				}
			}
		}catch (RestClientException e) {
			feeErrors.add(new FieldError("RegisterController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}catch(Exception e){
			feeErrors.add(new FieldError("RegisterController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}

		String REGISTER = (String) context.getBean("REGISTER_SUCCESS");
		return new ModelAndView("redirect:../../" + REGISTER + ".jsp");
	}
}
