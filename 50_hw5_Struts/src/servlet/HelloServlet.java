package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messageservice.*;
import service.mail.GoogleMail;
import service.mail.Mail;

public class HelloServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException
	{
		String name = req.getParameter("name");
		String result;
		String stamp;
		
//		Message msg = new HelloMessage();
//		Message msg = new HiMessage();
		Message msg = new AlohaMessage();
		result = msg.doHello(name);
		
		Mail mail = new GoogleMail();
		stamp = mail.sendMail(result);
		
		req.setAttribute("stamp", stamp);
		req.setAttribute("result", result);
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}
}
