package spring.controller.database;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;

import model.FeeModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.fee.Fee;
import viewmodel.HelloModel;
import exceptions.NullAccountException;

@Controller("Spring.database.QueryController")

@RequestMapping("/database")
public class QueryController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR = (String)context.getBean("ERROR");
	ResourceBundle res = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelAndView query(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		
		String QUERY = (String) context.getBean("QUERY");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value = "/doQuery", method = RequestMethod.POST)
	public ModelAndView doQuery(@Valid HelloModel helloModel, BindingResult bindingResult){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		List<FieldError> feeErrors = new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors()){
			return new ModelAndView(ERROR, "ErrorModel", bindingResult.getFieldError());
		}
		
		Fee fee = (Fee) context.getBean("fee");
		FeeModel feeModel = new FeeModel();
		feeModel.setName(helloModel.getName());
		
		try {
			feeModel = fee.find(feeModel);
		} catch (NullAccountException nullAccountException) {
			feeErrors.add(new FieldError("QueryController", nullAccountException.getMessage(), res.getString(nullAccountException.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		} catch (Exception e) {
			feeErrors.add(new FieldError("QueryController", "error.database", res.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", feeErrors);
		}
		
		String SUCCESS = (String)context.getBean("querySUCCESS");
		return new ModelAndView(SUCCESS, "FeeModel", feeModel);
	}
}
