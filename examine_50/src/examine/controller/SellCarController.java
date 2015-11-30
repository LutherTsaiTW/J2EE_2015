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

import examine.exceptions.NotEnoughMoneyException;
import examine.model.AccountModel;
import examine.model.CarModel;
import examine.service.account.Account;
import examine.service.car.Car;
import examine.viewmodel.BuildCarModel;
import examine.viewmodel.SellCarModel;

@Controller("examine.controller.SellCarController")

@RequestMapping("/examine")
public class SellCarController {
	ApplicationContext context = new ClassPathXmlApplicationContext("examine/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("examine.resources.MessageDictionary");
	
	@RequestMapping(value = "/sellCar", method = RequestMethod.GET)
	public ModelAndView account() {
		String BUILD = (String) context.getBean("SELL");
		return new ModelAndView(BUILD);
	}
	
	@RequestMapping(value = "/doSellCar", method = RequestMethod.POST)
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
			if (accountModel.getCash() >= (Integer.valueOf(queryModel.getPrice()) * 0.05)) {
				CarModel carModel = new CarModel();
				carModel.setName(queryModel.getName());
				carModel.setPrice(Integer.valueOf(queryModel.getPrice()));
				carModel.setOwnerId(accountModel.getId());
				
				Car car = (Car) context.getBean("car");
				car.create(carModel);
				
				int rest = accountModel.getCash() - (int)(Integer.valueOf(queryModel.getPrice()) * 0.05);
				accountModel.setCash(rest);
				account.update(accountModel);
			}
			else {
				throw new NotEnoughMoneyException();
			}
		} catch (NotEnoughMoneyException e) {
			feeErrors.add(new FieldError("SellCarController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("SellCarController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		SellCarModel sellCarModel = new SellCarModel();
		sellCarModel.setOwnerId(Integer.valueOf(queryModel.getId()));
		sellCarModel.setName(queryModel.getName());
		sellCarModel.setPrice(Integer.valueOf(queryModel.getPrice()));
		
		String SUCCESS = (String)context.getBean("sellSUCCESS");
		return new ModelAndView(SUCCESS, "SellCarModel", sellCarModel);
	}
}
