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

import test3.exceptions.CannotBidOwnCar;
import test3.exceptions.LowerThanPriceException;
import test3.exceptions.NotEnoughMoneyException;
import test3.exceptions.NullAccountException;
import test3.model.AccountModel;
import test3.model.CarModel;
import test3.service.account.Account;
import test3.service.car.Car;
import test3.viewmodel.BidCarModel;
import test3.viewmodel.GarageModel;

@Controller("test3.controller.BidCarController")

@RequestMapping("/test3")
public class BidCarController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test3/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test3.resources.MessageDictionary");
	
	@RequestMapping(value = "/bid", method = RequestMethod.GET)
	public ModelAndView bid() {
		String BID = (String) context.getBean("BID");
		return new ModelAndView(BID);
	}
	
	@RequestMapping(value = "/doBidCar", method = RequestMethod.POST)
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
				if (accountModel.getCash() < Integer.valueOf(queryModel.getPrice())) {
					throw new NotEnoughMoneyException();
				}
				else {
					if (Integer.valueOf(queryModel.getPrice()) < carModel.getPrice()) {
						throw new LowerThanPriceException();
					}
					else {
						AccountModel dealerAccountModel = new AccountModel();
						dealerAccountModel.setId(carModel.getOwnerId());
						dealerAccountModel = account.find(dealerAccountModel);
						int rest = dealerAccountModel.getCash() + Integer.valueOf(queryModel.getPrice());
						int newAsset = dealerAccountModel.getAsset() - carModel.getPrice();
						int newCount = dealerAccountModel.getCount() - 1;
						dealerAccountModel.setCash(rest);
						dealerAccountModel.setAsset(newAsset);
						dealerAccountModel.setCount(newCount);
						account.update(accountModel);
						
						carModel.setOwnerId(accountModel.getId());
						carModel.setPrice(Integer.valueOf(queryModel.getPrice()));
						car.update(carModel);
						
						rest = accountModel.getCash() - Integer.valueOf(queryModel.getPrice());
						newAsset = accountModel.getAsset() + Integer.valueOf(queryModel.getPrice());
						newCount = accountModel.getCount() + 1;
						accountModel.setCash(rest);
						accountModel.setAsset(newAsset);
						accountModel.setCount(newCount);
						account.update(accountModel);
						
						try {
							carListModel = car.personalList(carModel);
						} catch (NullAccountException nullAccountException) {
							feeErrors.add(new FieldError("BidCarController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
							return new ModelAndView(ERROR, "ErrorModel", feeErrors);
						} catch (Exception e) {
							feeErrors.add(new FieldError("BidCarController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
							return new ModelAndView(ERROR, "ErrorModel", feeErrors);
						}
					}
				}
			}
		} catch (CannotBidOwnCar | NotEnoughMoneyException | LowerThanPriceException e) {
			feeErrors.add(new FieldError("BidCarController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("BidCarController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		GarageModel garageModel = new GarageModel();
		garageModel.setOwnerId(Integer.valueOf(queryModel.getId()));
		garageModel.setCount(carListModel.size());
		garageModel.setCarList(carListModel);
		
		String SUCCESS = (String)context.getBean("bidSUCCESS");
		return new ModelAndView(SUCCESS, "GarageModel", garageModel);
	}
}
