package test1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import test1.model.CarModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test1.exception.NullAccountException;
import test1.service.car.Car;
import test1.viewmodel.HelloModel;
import test1.viewmodel.MessageModel;

@Controller("test1.controller.InsertController")

@RequestMapping("/test1")
public class InsertController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test1/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test1.resources.MessageDictionary");
	
	@RequestMapping(value = "/carArrive", method = RequestMethod.GET)
	public ModelAndView carArrive(){
		String CarArrive = (String) context.getBean("CarArrive");
		return new ModelAndView(CarArrive);
	}
	
	@RequestMapping(value = "/doCarArrive", method = RequestMethod.POST)
	public ModelAndView doCarArrive(@Valid HelloModel helloModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			feeErrors.add(bindingResult.getFieldError());
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		
		Car fee = (Car) context.getBean("car");
		CarModel carModel = new CarModel();
		carModel.setName(helloModel.getName());
		int count, totalAmount = 0;
		
		try {
			carModel = fee.find(carModel);
			count = carModel.getCount();
			
			if (count >= 0) {
				totalAmount = count + Integer.valueOf(helloModel.getAmount());
				carModel.setCount(totalAmount);
				fee.update(carModel);
			}
		} catch (NullAccountException nullAccountException) {
			totalAmount =  Integer.valueOf(helloModel.getAmount());
			carModel.setCount(totalAmount);
			
			try {
				fee.create(carModel);
			} catch (Exception e) {
				feeErrors.add(new FieldError("InsertController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
				return new ModelAndView(ERROR, "ErrorModel", feeErrors);
			}
		} catch (Exception e) {
			feeErrors.add(new FieldError("InsertController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		MessageModel msgModel = (MessageModel) context.getBean("messageModel");
		msgModel.setResult(helloModel.getName());
		msgModel.setCount(totalAmount);
		
		String SUCCESS = (String)context.getBean("arriveSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", msgModel);
	}
}
