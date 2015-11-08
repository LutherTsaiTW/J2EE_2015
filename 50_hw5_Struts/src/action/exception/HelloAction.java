package action.exception;

import java.util.ResourceBundle;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.imap.Quota.Resource;

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
		Message msg = new HelloMessage();
//		Message msg = new HiMessage();
//		Message msg = new AlohaMessage();
		
		_helloForm.setResult(msg.doHello(_helloForm.getName()));
		
		Mail mail = new GoogleMail();
		_helloForm.setStamp(mail.sendMail(_helloForm.getResult()));
		
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		ResourceBundle res = ResourceBundle.getBundle("resources.ExceptionMessage");
		
		if(_helloForm.getName().length() != 0) {
			if(!(_helloForm.getName().matches("[A-za-z]+")))
			{
				this.addFieldError("errorName", res.getString("error.charOnly"));
			}
			else if((_helloForm.getName().matches("[\u4E00-\u9FA5]+"))) {
				this.addFieldError("errorName", res.getString("error.charOnly"));
			}
		} else {
			this.addFieldError("nullName", res.getString("error.empty"));
		}
		super.validate();
	}
}
