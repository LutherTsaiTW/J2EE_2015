package examine.service.account;

import java.util.Iterator;
import java.util.List;

import examine.model.AccountModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import examine.exceptions.NullAccountException;

public class HibernateAccount implements Account{

	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateAccount() throws Exception {
		String configXML = "examine/service/account/accounthibernate-config.xml";
		try {
			Configuration hibernateConfig = new Configuration().configure(configXML);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<AccountModel> list() throws Exception {
		List<AccountModel> accountModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(AccountModel.class);
			accountModelList = hibernateCriteria.list();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return accountModelList;
	}

	@Override
	public void update(AccountModel accountModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.update(accountModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public AccountModel find(AccountModel accountModel) throws Exception {
		List<AccountModel> accountModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(AccountModel.class);
			hibernateCriteria.add(Restrictions.eq("id", accountModel.getId()));
			accountModelList = hibernateCriteria.list();
			Iterator iterator = accountModelList.iterator();
			
			if (iterator.hasNext()) {
				accountModel = (AccountModel) iterator.next();
			}
			else {
				throw new NullAccountException();
			}
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return accountModel;
	}

}
