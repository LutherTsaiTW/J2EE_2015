package spring.controller.database;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import model.FeeModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exceptions.FeeBeenPaidException;
import exceptions.NullAccountException;
import service.fee.Fee;
import viewmodel.HelloModel;

@Controller("Spring.Controller.PayController")
@RequestMapping("/database")
public class PayController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView pay(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		
		String PAY = (String) context.getBean("PAY");
		return new ModelAndView(PAY);
	}
	
	
	@RequestMapping(value = "/doPay", method = RequestMethod.POST)
	public ModelAndView doPay(@Valid HelloModel helloModel, BindingResult bindingResult){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			for (FieldError err : bindingResult.getFieldErrors()) {
				feeErrors.add(err);
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Fee fee = (Fee) context.getBean("fee");
		FeeModel feeModel = new FeeModel();
		feeModel.setName(helloModel.getName());
		int count;
		
		try {
			feeModel = fee.find(feeModel);
			count = feeModel.getCount();
			
			if (count > 0) {
				feeModel.setCount(0);
				fee.update(feeModel);
				feeModel.setCount(count);
			} else if (count == 0) {
				throw new FeeBeenPaidException();
			}
		} catch (NullAccountException | FeeBeenPaidException e) {
			feeErrors.add(new FieldError("PayController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("PayController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String SUCCESS = (String)context.getBean("paySUCCESS");
		return new ModelAndView(SUCCESS, "FeeModel", feeModel);
	}
}
