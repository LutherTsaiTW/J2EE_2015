package finalProjectWS.ad;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	// http://localhost:8080/50_finalProject/spring/webservice/ad
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody void create(@RequestBody AdModel adModel)
			throws Exception {
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.save(adModel);
		tx.commit();
		hibernateSession.close();
	}

	// http://localhost:8080/50_finalProject/spring/webservice/ad/{id}
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad/{id}
	@RequestMapping(method = RequestMethod.PUT, value = "/{AdID}")
	public @ResponseBody void update(@PathVariable("AdID") int AdID,
			@RequestBody AdModel adModel) throws Exception {
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.update(adModel);
		tx.commit();
		hibernateSession.close();
	}

	// http://localhost:8080/50_finalProject/spring/webservice/ad/getAdByID?adId=XXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad/getAdByID?adId=XXXXX
	@RequestMapping(value = "/getAdByID", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public AdModel getAdByID(String adId) throws Exception {
		List<AdModel> adModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(AdModel.class);
		hibernateCriteria.add(Restrictions.eq("AdID", Integer.valueOf(adId)));

		adModelList = hibernateCriteria.list();
		Iterator iterator = adModelList.iterator();
		AdModel adModel;

		if (iterator.hasNext()) {
			adModel = (AdModel) iterator.next();
		} else {
			throw new NullAccountException();
		}

		return adModel;
	}

	// http://localhost:8080/50_finalProject/spring/webservice/ad/getAD?token=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad/getAD?token=XXXXXX
	@RequestMapping(value = "/getAD", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getAdByToken(String token) throws Exception {
		List<AdModel> memberModelList;

		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar
				.getInstance().getTime());

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(AdModel.class);
		hibernateCriteria.add(Restrictions.ne("adOwnerToken", token));
		hibernateCriteria.add(Restrictions.le("adStartDate",
				Integer.valueOf(currentDate)));
		hibernateCriteria.add(Restrictions.ge("adEndDate",
				Integer.valueOf(currentDate)));

		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();
		AdModel adModel;

		if (memberModelList.size() != 0) {
			Random rand = new Random();
			int n = rand.nextInt(memberModelList.size());
			adModel = (AdModel) memberModelList.get(n);
		} else {
			adModel = new AdModel();
		}
		String linkString = "http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/finalProject/doClick?token="
				+ token + "&adId=" + adModel.getAdID();
		String htmlString = "<a target=\"_blank\" href=\"" + linkString
				+ "\"><img src=\"" + adModel.getAdImageLink() + "\" title=\""
				+ adModel.getAdTitle() + "\" alt=\"" + adModel.getAdDes()
				+ "\" height=\"100\" width=\"400\"></a>";
		System.out.println(htmlString);
		return htmlString;
	}

	// http://localhost:8080/50_finalProject/spring/webservice/ad/getSelfAD?token=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/ad/getSelfAD?token=XXXXXX
	@RequestMapping(value = "/getSelfAD", method = RequestMethod.GET, produces = "application/json")
	public List<AdModel> getSelfAdByToken(String token) throws Exception {
		List<AdModel> adModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(AdModel.class);
		hibernateCriteria.add(Restrictions.eq("adOwnerToken", token));

		adModelList = hibernateCriteria.list();

		return adModelList;
	}

	@ExceptionHandler(NullAccountException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleNullAccountException(NullAccountException e) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception e) {
	}
}
