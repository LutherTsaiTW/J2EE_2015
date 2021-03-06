package spring.controller.database;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import model.FeeModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exceptions.NullAccountException;
import exceptions.NullListException;
import service.fee.Fee;
import viewmodel.HelloModel;

@Controller("Spring.database.ListController")

@RequestMapping("/database")
public class ListController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/doList", method = RequestMethod.GET)
	public ModelAndView doList() {
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		List<FeeModel> feeListModel;
		Fee fee = (Fee) context.getBean("fee");
		
		try {
			feeListModel = fee.list();
			if (feeListModel.size() == 0) {
				throw new NullListException();
			}
		} catch (NullAccountException e) {
			feeErrors.add(new FieldError("ListController", e.getMessage(), res.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("ListController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String LIST = (String)context.getBean("listSUCCESS");
		return new ModelAndView(LIST, "FeeListModel", feeListModel);
	}
}
