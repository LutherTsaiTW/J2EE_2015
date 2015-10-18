package mailservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.mail.GoogleMail;
import service.mail.Mail;

public class MailServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException
	{
		String result = (String)req.getAttribute("result");
		String stamp;
		
		Mail mail = new GoogleMail();
		stamp = mail.sendMail(result);
		
		req.setAttribute("stamp", stamp);
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}
}
