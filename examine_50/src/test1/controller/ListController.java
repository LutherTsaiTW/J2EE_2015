package test1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import test1.model.CarModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test1.service.car.Car;
import test1.exception.NullListException;

@Controller("test1.controller.ListController")

@RequestMapping("/test1")
public class ListController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("test1/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test1.resources.MessageDictionary");
	
	@RequestMapping(value = "/doList", method = RequestMethod.GET)
	public ModelAndView doList() {
		
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		Car car = (Car) context.getBean("car");
		
		try {
			carListModel = car.list();
			if (carListModel.size() == 0) {
				throw new NullListException();
			}
		} catch (NullListException e) {
			feeErrors.add(new FieldError("ListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("ListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String LIST = (String)context.getBean("listSUCCESS");
		return new ModelAndView(LIST, "CarListModel", carListModel);
	}

}
