package webservice.fee;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import exceptions.NullAccountException;
import model.FeeModel;

@RestController("Spring.webservice.HibernateFeeController")
@RequestMapping("/webservice/fee")
public class HibernateFee implements Fee {
	
	private Session hibernateSession;
	private SessionFactory hibernateSessionFactory;
	private Criteria hibernateCriteria;
	private ServiceRegistry serviceRegistry;

	public HibernateFee() throws Exception {

		String configXML = "webservice/fee/feehibernate-config.xml";
		try {
			Configuration hibernateConfig = new Configuration().configure(configXML);
			serviceRegistry =   new StandardServiceRegistryBuilder().
					applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);	
		} 
		
		catch (Exception e) {
			throw e;
		}
	}

	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/create
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/create
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody void create(@RequestBody FeeModel feeModel) throws Exception {
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			Transaction tx= hibernateSession.beginTransaction();
			hibernateSession.save(feeModel);
			tx.commit();	
			hibernateSession.close();
		} catch(Exception e){
			throw e;
		}
	}
	
	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/update
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody void update(@RequestBody FeeModel feeModel) throws Exception {
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx= hibernateSession.beginTransaction();
		hibernateSession.update(feeModel);
		tx.commit();
		hibernateSession.close();	
	}
	
	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/delete
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/delete
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestBody FeeModel feeModel) throws Exception {	
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.delete(feeModel);
		tx.commit();
		hibernateSession.close();	
	}
	
	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/find
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/find
	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody FeeModel find(@RequestBody FeeModel feeModel) throws Exception {	
		hibernateSession = hibernateSessionFactory.openSession();
		
		feeModel = (FeeModel) hibernateSession.get(FeeModel.class, feeModel.getId());
		
		if (feeModel == null) {
			throw new NullAccountException();
		}
		return feeModel;
	}
	
	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/list
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/list
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FeeModel> list() throws Exception {
		List<FeeModel> feeModelList;
		
		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(FeeModel.class);
		feeModelList = hibernateCriteria.list();
			
		return feeModelList;	
	}
	
	// http://localhost:8080/00_hw10_WebService/spring/webservice/fee/findByName
	// http://ilab.csie.ntut.edu.tw:8080/00_hw10_WebService/spring/webservice/fee/findByName
	@RequestMapping(value = "/findByName", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody FeeModel findByName(@RequestBody FeeModel feeModel) throws Exception {	
		List<FeeModel> feeModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(FeeModel.class);
		hibernateCriteria.add(Restrictions.eq("name", feeModel.getName()));
		feeModelList = hibernateCriteria.list();
		Iterator iterator = feeModelList.iterator();
		
		if (iterator.hasNext()) {
			feeModel = (FeeModel) iterator.next();
		} 
		else {
			throw new NullAccountException();
		}
		return feeModel;
	}
	
	@ExceptionHandler(NullAccountException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleNullAccountException(Exception e) {}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception e) {}
}
