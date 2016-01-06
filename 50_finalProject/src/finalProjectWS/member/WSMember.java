package finalProjectWS.member;

import java.util.Iterator;
import java.util.List;

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
import model.MemberModel;

@RestController("restfulWS.WSMember")
@RequestMapping("/webservice/member")
public class WSMember implements Member {

	private Session hibernateSession;
	private SessionFactory hibernateSessionFactory;
	private Criteria hibernateCriteria;
	private ServiceRegistry serviceRegistry;

	public WSMember() throws Exception {

		String configXML = "finalProjectWS/member/memberhibernate-config.xml";
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

	// http://localhost:8080/50_finalProject/spring/webservice/member
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody void create(@RequestBody MemberModel memberModel)
			throws Exception {
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.save(memberModel);
		tx.commit();
		hibernateSession.close();
	}

	// http://localhost:8080/50_finalProject/spring/webservice/member/{id}
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/{id}
	@RequestMapping(method = RequestMethod.PUT, value = "/{memberID}")
	public @ResponseBody void update(@PathVariable("memberID") int memberID, @RequestBody MemberModel memberModel) throws Exception {
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx= hibernateSession.beginTransaction();
		hibernateSession.update(memberModel);
		tx.commit();
		hibernateSession.close();
	}

	@Override
	public MemberModel find(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// http://localhost:8080/50_finalProject/spring/webservice/member/findByName?name=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/findByName?name=XXXXXX
	@RequestMapping(value = "/findByName", method = RequestMethod.GET, produces = "application/json")
	public MemberModel findByName(String name) throws Exception {
		List<MemberModel> memberModelList;
		System.out.print(name);
		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
		hibernateCriteria.add(Restrictions.eq("name", name));
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		MemberModel memberModel;

		if (iterator.hasNext()) {
			memberModel = (MemberModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		return memberModel;
	}

	// http://localhost:8080/50_finalProject/spring/webservice/member/activate?applicationToken=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/activate?applicationToken=XXXXXX
	@RequestMapping(value = "/activate", method = RequestMethod.GET, produces = "application/json")
	public void active(String applicationToken) throws Exception {
		List<MemberModel> memberModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
		hibernateCriteria.add(Restrictions.eq("applicationToken",
				applicationToken));
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		MemberModel memberModel;

		if (iterator.hasNext()) {
			memberModel = (MemberModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		memberModel.setPrevilege(111);

		hibernateSession.close();
		hibernateSession = hibernateSessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.update(memberModel);
		tx.commit();
		hibernateSession.close();
	}

	// http://localhost:8080/50_finalProject/spring/webservice/member/findByEmail?email=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/findByEmail?email=XXXXXX
	@RequestMapping(value = "/findByEmail", method = RequestMethod.GET, produces = "application/json")
	public MemberModel findByEmail(String email) throws Exception {
		List<MemberModel> memberModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
		hibernateCriteria.add(Restrictions.eq("email", email));
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		MemberModel memberModel;

		if (iterator.hasNext()) {
			memberModel = (MemberModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		return memberModel;
	}

	// http://localhost:8080/50_finalProject/spring/webservice/member/findByAccount?account=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/findByAccount?account=XXXXXX
	@RequestMapping(value = "/findByAccount", method = RequestMethod.GET, produces = "application/json")
	public MemberModel findByAccount(String account) throws Exception {
		List<MemberModel> memberModelList;

		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
		hibernateCriteria.add(Restrictions.eq("account", account));
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		MemberModel memberModel;

		if (iterator.hasNext()) {
			memberModel = (MemberModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		return memberModel;
	}
	
	// http://localhost:8080/50_finalProject/spring/webservice/member/findBySession?session=XXXXXX
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/findBySession?session=XXXXXX
	@RequestMapping(value = "/findBySession", method = RequestMethod.GET, produces = "application/json")
	public MemberModel findBySession(String session) throws Exception {
			List<MemberModel> memberModelList;

			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
			hibernateCriteria.add(Restrictions.eq("session", session));
			memberModelList = hibernateCriteria.list();
			Iterator iterator = memberModelList.iterator();

			MemberModel memberModel;

			if (iterator.hasNext()) {
				memberModel = (MemberModel) iterator.next();
			} else {
				throw new NullAccountException();
			}
			return memberModel;
		}

	// http://localhost:8080/50_finalProject/spring/webservice/member/authencate
	// http://ilab.csie.ntut.edu.tw:8080/50_finalProject/spring/webservice/member/authencate
	@RequestMapping(value = "/authencate", method = RequestMethod.POST, produces = "application/json")
	public boolean authencate(@RequestBody MemberModel input) throws Exception {
		List<MemberModel> memberModelList;
		System.out.println(input.getAccount());
		System.out.println(input.getPassword());
		hibernateSession = hibernateSessionFactory.openSession();
		hibernateCriteria = hibernateSession.createCriteria(MemberModel.class);
		hibernateCriteria.add(Restrictions.eq("account", input.getAccount()));
		memberModelList = hibernateCriteria.list();
		Iterator iterator = memberModelList.iterator();

		MemberModel memberModel;

		if (iterator.hasNext()) {
			memberModel = (MemberModel) iterator.next();
		} else {
			throw new NullAccountException();
		}
		System.out.println(memberModel.getPassword());
		if (memberModel.getPassword().equals(input.getPassword())) {
			return true;
		} else {
			return false;
		}
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
