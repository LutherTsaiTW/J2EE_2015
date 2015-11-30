package test3.controller;

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

import test3.exceptions.NotEnoughMoneyException;
import test3.exceptions.NullAccountException;
import test3.model.AccountModel;
import test3.model.CarModel;
import test3.service.account.Account;
import test3.service.car.Car;
import test3.viewmodel.BuildCarModel;
import test3.viewmodel.GarageModel;

@Controller("test3.controller.BuildCarController")

@RequestMapping("/test3")
public class BuildCarController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test3/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test3.resources.MessageDictionary");
	
	@RequestMapping(value = "/buildCar", method = RequestMethod.GET)
	public ModelAndView account() {
		String BUILD = (String) context.getBean("BUILD");
		return new ModelAndView(BUILD);
	}
	
	@RequestMapping(value = "/doBuildCar", method = RequestMethod.POST)
	public ModelAndView doQuery(@Valid BuildCarModel queryModel, BindingResult bindingResult){
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		Account account = (Account) context.getBean("account");
		AccountModel accountModel = new AccountModel();
		accountModel.setId(Integer.valueOf(queryModel.getId()));
		
		try {
			accountModel = account.find(accountModel);
			if (accountModel.getCash() >= Integer.valueOf(queryModel.getPrice())) {
				CarModel carModel = new CarModel();
				carModel.setName(queryModel.getName());
				carModel.setPrice(Integer.valueOf(queryModel.getPrice()));
				carModel.setOwnerId(accountModel.getId());
				
				Car car = (Car) context.getBean("car");
				
				try {
					car.create(carModel);
					int rest = accountModel.getCash() - Integer.valueOf(queryModel.getPrice());
					int newAsset = accountModel.getAsset() + Integer.valueOf(queryModel.getPrice());
					int newCount = accountModel.getCount() + 1;
					accountModel.setCash(rest);
					accountModel.setAsset(newAsset);
					accountModel.setCount(newCount);
					account.update(accountModel);
					
					try {
						carListModel = car.personalList(carModel);
					} catch (NullAccountException nullAccountException) {
						feeErrors.add(new FieldError("AccountController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
						return new ModelAndView(ERROR, "ErrorModel", feeErrors);
					} catch (Exception e) {
						feeErrors.add(new FieldError("AccountController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
						return new ModelAndView(ERROR, "ErrorModel", feeErrors);
					}
					
				} catch (Exception e) {
					feeErrors.add(new FieldError("AccountController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
					return new ModelAndView(ERROR, "ErrorModel", feeErrors);
				}
			}
			else {
				throw new NotEnoughMoneyException();
			}
		} catch (NotEnoughMoneyException e) {
			feeErrors.add(new FieldError("BuildCarController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("AccountController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		GarageModel garageModel = new GarageModel();
		garageModel.setOwnerId(Integer.valueOf(queryModel.getId()));
		garageModel.setCount(carListModel.size());
		garageModel.setCarList(carListModel);
		
		String SUCCESS = (String)context.getBean("buildSUCCESS");
		return new ModelAndView(SUCCESS, "GarageModel", garageModel);
	}
}
