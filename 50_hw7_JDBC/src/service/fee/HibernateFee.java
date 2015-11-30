package service.fee;

import java.util.Iterator;
import java.util.List;

import model.FeeModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import exceptions.NullAccountException;

public class HibernateFee implements Fee{

	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateFee() throws Exception {
		String configXML = "service/fee/feehibernate-config.xml";
		try {
			Configuration hibernateConfig = new Configuration().configure(configXML);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void create(FeeModel feeModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.save(feeModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(FeeModel feeModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.update(feeModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(FeeModel feeModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.delete(feeModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public FeeModel find(FeeModel feeModel) throws Exception {
		List<FeeModel> feeModelList;
		try {
			//Open Session
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
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return feeModel;
	}

	@Override
	public List<FeeModel> list() throws Exception {
		List<FeeModel> feeModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(FeeModel.class);
			feeModelList = hibernateCriteria.list();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return feeModelList;
	}
}
