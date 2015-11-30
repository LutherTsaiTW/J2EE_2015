package test3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test3.exceptions.NullListException;
import test3.model.CarModel;
import test3.service.car.Car;

@Controller("test3.controller.CarListController")

@RequestMapping("/test3")
public class CarListController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("test3/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test3.resources.MessageDictionary");
	
	@RequestMapping(value = "/carList", method = RequestMethod.GET)
	public ModelAndView doCarList() {
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
		
		String LIST = (String)context.getBean("carList");
		return new ModelAndView(LIST, "CarListModel", carListModel);
	}
}
