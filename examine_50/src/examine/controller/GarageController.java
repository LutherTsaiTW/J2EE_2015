package examine.controller;

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

import examine.exceptions.NullAccountException;
import examine.model.CarModel;
import examine.service.car.Car;
import examine.viewmodel.AccountQueryModel;
import examine.viewmodel.GarageModel;

@Controller("examine.controller.GarageController")

@RequestMapping("/examine")
public class GarageController {

	ApplicationContext context = new ClassPathXmlApplicationContext("examine/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("examine.resources.MessageDictionary");
	
	@RequestMapping(value = "/myGarage", method = RequestMethod.GET)
	public ModelAndView account() {
		String GARAGE = (String) context.getBean("GARAGE");
		return new ModelAndView(GARAGE);
	}
	
	@RequestMapping(value = "/doCheckMyGarage", method = RequestMethod.POST)
	public ModelAndView doQuery(@Valid AccountQueryModel queryModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		
		Car car = (Car) context.getBean("car");
		CarModel carModel = new CarModel();
		carModel.setOwnerId(Integer.valueOf(queryModel.getId()));
		
		try {
			carListModel = car.personalList(carModel);
		} catch (NullAccountException nullAccountException) {
			feeErrors.add(new FieldError("AccountController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("AccountController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		GarageModel garageModel = new GarageModel();
		garageModel.setOwnerId(Integer.valueOf(queryModel.getId()));
		garageModel.setCount(carListModel.size());
		garageModel.setCarList(carListModel);
		
		String SUCCESS = (String)context.getBean("garageSUCCESS");
		return new ModelAndView(SUCCESS, "GarageModel", garageModel);
	}
}
