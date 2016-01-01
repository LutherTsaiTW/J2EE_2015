package finalProjectWS.ad;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exceptions.NullAccountException;
import model.AdModel;

@RestController("restfulWS.WSAd")
@RequestMapping("/webservice/ad")
public class WSAd implements Ad {
	private Session hibernateSession;
	private SessionFactory hibernateSessionFactory;
	private Criteria hibernateCriteria;
	private ServiceRegistry serviceRegistry;

	public WSAd() throws Exception {

		String configXML = "finalProjectWS/ad/adhibernate-config.xml";
		try {
			Configuration hibernateConfig = new Configuration()
					.configure(configXML);
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig
					.buildSessionFactory(serviceRegistry);
		}

		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void create(AdModel adModel) throws Exception {
		// TODO Auto-generated method stub

	}

	// http://localhost:8080/50_finalProject/spring/webservice/ad/getAD?token=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad/getAD?token=XXXXXX
	@RequestMapping(value = "/getAD", method = RequestMethod.GET, produces = "application/json")
	public AdModel getAdByToken(String token) throws Exception {
		List<AdModel> memberModelList;

		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		
		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(AdModel.class);
		hibernateCriteria.add(Restrictions.ne("adOwnerToken", token));
		hibernateCriteria.add(Restrictions.le("adStartDate", Integer.valueOf(currentDate))); 
		hibernateCriteria.add(Restrictions.ge("adEndDate", Integer.valueOf(currentDate)));
		
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		AdModel adModel;

		if (iterator.hasNext()) {
			adModel = (AdModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		return adModel;
	}

	@ExceptionHandler(NullAccountException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleNullAccountException(NullAccountException e) {}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception e) {}
}
