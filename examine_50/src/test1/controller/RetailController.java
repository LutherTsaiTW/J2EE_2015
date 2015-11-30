package test1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test1.service.mail.Mail;
import test1.exception.NullAccountException;
import test1.model.CarModel;
import test1.viewmodel.MessageModel;
import test1.viewmodel.SellCarModel;
import test1.service.car.Car;

@Controller("test1.controller.RetailController")

@RequestMapping("/test1")
public class RetailController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("test1/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test1.resources.MessageDictionary");
	
	@RequestMapping(value = "/sellCar", method = RequestMethod.GET)
	public ModelAndView carArrive(){
		
		String SELL = (String) context.getBean("SELL");
		return new ModelAndView(SELL);
	}
	
	@RequestMapping(value = "/doSellCar", method = RequestMethod.POST)
	public ModelAndView doCarArrive(@Valid SellCarModel sellCarModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			for (FieldError err : bindingResult.getFieldErrors()) {
				feeErrors.add(err);
			}
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Car car = (Car) context.getBean("car");
		CarModel carModel = new CarModel();
		carModel.setName(sellCarModel.getCarName());
		int count, totalAmount = 0;
		
		try {
			carModel = car.find(carModel);
			count = carModel.getCount();
			
			if (count > 0) {
				totalAmount = count - 1;
				if(totalAmount == 0) {
					car.delete(carModel);
				}
				else {
					carModel.setCount(totalAmount);
					car.update(carModel);
				}
			}
		} catch (NullAccountException nullAccountException) {
			feeErrors.add(new FieldError("RetailController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("RetailController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		Mail mail = (Mail) context.getBean("mail");
		String mailMsg = sellCarModel.getCustomerName() + "感謝您購買" + sellCarModel.getCarName();
		String stamp = mail.sendMail(mailMsg);
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(mailMsg);
		msgModel.setStamp(stamp);
		
		String SUCCESS = (String)context.getBean("sellSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
