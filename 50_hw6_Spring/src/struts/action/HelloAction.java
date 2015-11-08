package struts.action;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import service.mail.*;
import service.message.*;
import resources.*;

public class HelloAction extends ActionSupport{
	private HelloForm _helloForm;
	
	public HelloForm getHelloForm() {
		return _helloForm;
	}
	
	public void setHelloForm(HelloForm helloForm) {
		this._helloForm = helloForm;
	}
	
	public String doHello() {
		ApplicationContext context = new ClassPathXmlApplicationContext("struts/action/spring.xml");
		
		Message msg = (Message) context.getBean("message");
		_helloForm.setResult(msg.doHello(_helloForm.getName()));
		
		Mail mail = new GoogleMail();
		_helloForm.setStamp(mail.sendMail(_helloForm.getResult()));
		
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		ResourceBundle res = ResourceBundle.getBundle("resources.MessageDictionary");
		
		if(_helloForm.getName().length() != 0) {
			if(!(_helloForm.getName().matches("[A-za-z]+")))
			{
				this.addFieldError("errorName", res.getString("error.charOnly"));
			}
			else if(!(_helloForm.getName().matches("[\u4E00-\u9FA5]+"))) {
				this.addFieldError("errorName", res.getString("error.charOnly"));
			}
		} else {
			this.addFieldError("nullName", res.getString("error.empty"));
		}
		super.validate();
	}
}
