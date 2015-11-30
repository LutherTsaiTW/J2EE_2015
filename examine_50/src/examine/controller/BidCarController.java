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

import examine.exceptions.CannotBidOwnCar;
import examine.exceptions.LowerThanPriceException;
import examine.exceptions.NotEnoughMoneyException;
import examine.exceptions.NullAccountException;
import examine.model.AccountModel;
import examine.model.CarModel;
import examine.service.account.Account;
import examine.service.car.Car;
import examine.viewmodel.BidCarModel;
import examine.viewmodel.BuyCarModel;
import examine.viewmodel.GarageModel;

@Controller("examine.controller.BidCarController")

@RequestMapping("/examine")
public class BidCarController {
	ApplicationContext context = new ClassPathXmlApplicationContext("examine/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("examine.resources.MessageDictionary");
	
	@RequestMapping(value = "/buyCar", method = RequestMethod.GET)
	public ModelAndView bid() {
		String BUY = (String) context.getBean("BUY");
		return new ModelAndView(BUY);
	}
	
	@RequestMapping(value = "/doBuyCar", method = RequestMethod.POST)
	public ModelAndView doBid(@Valid BidCarModel queryModel, BindingResult bindingResult) {
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldErrors());
		}
		
		CarModel carModel = new CarModel();
		carModel.setId(Integer.valueOf(queryModel.getName()));
		Car car = (Car) context.getBean("car");
		
		Account account = (Account) context.getBean("account");
		AccountModel accountModel = new AccountModel();
		accountModel.setId(Integer.valueOf(queryModel.getId()));
		
		try {
			carModel = car.find(carModel);
			accountModel = account.find(accountModel);
			if (accountModel.getId() == carModel.getOwnerId()) {
				throw new CannotBidOwnCar();
			} 
			else {
				AccountModel dealerAccountModel = new AccountModel();
				dealerAccountModel.setId(carModel.getOwnerId());
				dealerAccountModel = account.find(dealerAccountModel);
				
				int rest = dealerAccountModel.getCash() + carModel.getPrice();
				
				dealerAccountModel.setCash(rest);
				account.update(dealerAccountModel);
				
				carModel.setOwnerId(accountModel.getId());
				car.update(carModel);
				
				rest = accountModel.getCash() - carModel.getPrice();
				accountModel.setCash(rest);
				account.update(accountModel);
			}
		} catch (CannotBidOwnCar | NotEnoughMoneyException | LowerThanPriceException e) {
			feeErrors.add(new FieldError("BidCarController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("BidCarController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		BuyCarModel buyCarModel = new BuyCarModel();
		buyCarModel.setOwnerName(accountModel.getName());
		buyCarModel.setName(carModel.getName());
		buyCarModel.setPrice(carModel.getPrice());
		buyCarModel.setCash(accountModel.getCash());
		
		String SUCCESS = (String)context.getBean("buySUCCESS");
		return new ModelAndView(SUCCESS, "BuyCarModel", buyCarModel);
	}
}
