package mailservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException
	{
		String name = req.getParameter("name");
		String result;
		
//		Message msg = new HelloMessage();
//		Message msg = new HiMessage();
		Message msg = new AlohaMessage();
		result = msg.doHello(name);
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("sendMail").forward(req, resp);
	}
}
