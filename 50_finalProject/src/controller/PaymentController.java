package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import model.BankauthModel;
import model.CorpbankModel;
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

@Controller("controller.finalProject.PaymentController")
@RequestMapping("/finalProject")
public class PaymentController {
	@Autowired
    public HttpSession session;
	
	RestTemplate restTemplate = new RestTemplate();
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/spring.xml");
	
	@RequestMapping(value = "/managePayment", method = RequestMethod.GET)
	public ModelAndView payment() {
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
		
		String PAYMENT = (String) context.getBean("PAYMENT");
		ModelAndView view = new ModelAndView(PAYMENT, "userName", memberModel.getName());
		view.addObject("adIncomeFee", memberModel.getAdIncomeFee());
		view.addObject("adPaymentFee", memberModel.getAdPaymentFee());
		return view;
	}
	
	@RequestMapping(value = "/doPayIncomeFee", method = RequestMethod.POST)
	public ModelAndView doPayIncomeFee() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		String bank_system = (String) context.getBean("bank_system");
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
		
		try {
			StringBuilder getURL =  new StringBuilder(bank_system).append("/corp");
			CorpbankModel bankTokenPostModel = new CorpbankModel();
			bankTokenPostModel.setValue(memberModel.getAdIncomeFee());
			bankTokenPostModel.setCorpToken(memberModel.getBankToken());
			restTemplate.postForObject(getURL.toString(), bankTokenPostModel , int.class);
			
			memberModel.setAdIncomeFee(0);
			restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
			
			getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
		} catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String PAYMENT = (String) context.getBean("PAYMENT");
		ModelAndView view = new ModelAndView(PAYMENT, "userName", memberModel.getName());
		view.addObject("adIncomeFee", memberModel.getAdIncomeFee());
		view.addObject("adPaymentFee", memberModel.getAdPaymentFee());
		view.addObject("callFunction", "IncomeSuccess();");
		return view;
	}
	
	@RequestMapping(value = "/doPayFee", method = RequestMethod.POST)
	public ModelAndView doPayFee() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		String bank_system = (String) context.getBean("bank_system");
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
		
		String url = new String();
		
		try {
			String PAYMENT_SUCCESS = (String) context.getBean("PAYMENT_SUCCESS");
			String callBackURL = "http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/" + PAYMENT_SUCCESS;
			StringBuilder getURL =  new StringBuilder(bank_system).append("/auth");
			BankauthModel bankauthModel = new BankauthModel();
			bankauthModel.setValue(memberModel.getAdPaymentFee());
			bankauthModel.setToken("0BBFD968AD302F0D3ACC9655963F0622");
			bankauthModel.setCallbackurl(callBackURL);
			url = restTemplate.postForObject(getURL.toString(), bankauthModel , String.class);
		} catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		ModelAndView view = new ModelAndView("redirect:" + url);
		return view;
	}
	
	@RequestMapping(value = "/doPaySuccess", method = RequestMethod.GET)
	public ModelAndView doPaySuccess() {
		String ERROR = (String) context.getBean("ERROR");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		String member_system = (String) context.getBean("member_system");
		MemberModel memberModel = new MemberModel();
		
		try {
			StringBuilder getURL = new StringBuilder(member_system).append("/findBySession?session=").append(session.getAttribute("SESSIONID"));
			memberModel = restTemplate.getForObject(getURL.toString(), MemberModel.class);
			
			memberModel.setAdPaymentFee(0);
			restTemplate.put(new StringBuilder(member_system).append("/").append(memberModel.getMemberID()).toString(), memberModel);
		} catch (RestClientException e) {
			feeErrors.add(new FieldError("LoginController", "error.http", resource.getString("error.http")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch(Exception e){
			feeErrors.add(new FieldError("LoginController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String PAYMENT = (String) context.getBean("PAYMENT");
		ModelAndView view = new ModelAndView(PAYMENT, "userName", memberModel.getName());
		view.addObject("adIncomeFee", memberModel.getAdIncomeFee());
		view.addObject("adPaymentFee", memberModel.getAdPaymentFee());
		view.addObject("callFunction", "PaySuccess();");
		return view;
	}

}
