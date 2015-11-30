package examine.controller;

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

import examine.exceptions.NullListException;
import examine.model.CarModel;
import examine.service.car.Car;
import examine.viewmodel.GarageModel;

@Controller("examine.controller.CarListController")

@RequestMapping("/examine")
public class CarListController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("examine/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("examine.resources.MessageDictionary");
	
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
			feeErrors.add(new FieldError("CarListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("CarListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		int totalPrice = 0;
		for (CarModel carModel : carListModel) {
			totalPrice += carModel.getPrice();
		}
		
		GarageModel garageModel = new GarageModel();
		garageModel.setOwnerId(carListModel.size());
		garageModel.setCount(totalPrice);
		garageModel.setCarList(carListModel);
		String LIST = (String)context.getBean("carList");
		return new ModelAndView(LIST, "GarageModel", garageModel);
	}
}
