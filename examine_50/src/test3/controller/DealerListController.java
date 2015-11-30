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
import test3.model.AccountModel;
import test3.model.CarModel;
import test3.service.account.Account;
import test3.service.car.Car;
import test3.viewmodel.DealerListModel;

@Controller("test3.controller.DealerListController")

@RequestMapping("/test3")
public class DealerListController {
	ApplicationContext context = new ClassPathXmlApplicationContext("test3/controller/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("test3.resources.MessageDictionary");
	
	@RequestMapping(value = "/dealerList", method = RequestMethod.GET)
	public ModelAndView doList() {
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		List<AccountModel> accountListModel;
		
		Car car = (Car) context.getBean("car");
		Account account = (Account) context.getBean("account");
		
		try {
			carListModel = car.list();
			accountListModel = account.list();
		} catch (NullListException e) {
			feeErrors.add(new FieldError("ListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("ListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		int totalCash = 0;
		int totalAsset = 0;
		
		for (AccountModel accountModel : accountListModel) {
			totalCash += accountModel.getCash();
			totalAsset += accountModel.getAsset();
		}
		
		DealerListModel dealerListModel = new DealerListModel();
		dealerListModel.setDealer(accountListModel.size());
		dealerListModel.setCash(totalCash);
		dealerListModel.setAsset(totalAsset);
		dealerListModel.setCount(carListModel.size());
		dealerListModel.setAccountList(accountListModel);
		
		String LIST = (String)context.getBean("dealerList");
		return new ModelAndView(LIST, "DealerListModel", dealerListModel);
	}
}
