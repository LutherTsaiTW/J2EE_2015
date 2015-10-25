package action.struts;

import com.opensymphony.xwork2.ActionSupport;

import service.mail.*;
import messageservice.*;

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
}
