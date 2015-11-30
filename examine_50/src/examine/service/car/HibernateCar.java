package examine.service.car;

import java.util.Iterator;
import java.util.List;

import examine.model.CarModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import examine.exceptions.NullCarException;

public class HibernateCar implements Car{

	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateCar() throws Exception {
		String configXML = "examine/service/car/carhibernate-config.xml";
		try {
			Configuration hibernateConfig = new Configuration().configure(configXML);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void create(CarModel carModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.save(carModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(CarModel carModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.update(carModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(CarModel carModel) throws Exception {
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			//Begin Transaction
			Transaction tx = hibernateSession.beginTransaction();
			//Save to DB from model
			hibernateSession.delete(carModel);
			tx.commit();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CarModel find(CarModel carModel) throws Exception {
		List<CarModel> carModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			hibernateCriteria.add(Restrictions.eq("id", carModel.getId()));
			carModelList = hibernateCriteria.list();
			Iterator iterator = carModelList.iterator();
			
			if (iterator.hasNext()) {
				carModel = (CarModel) iterator.next();
			}
			else {
				throw new NullCarException();
			}
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return carModel;
	}

	@Override
	public List<CarModel> list() throws Exception {
		List<CarModel> feeModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			feeModelList = hibernateCriteria.list();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return feeModelList;
	}

	@Override
	public List<CarModel> personalList(CarModel carModel) throws Exception {
		List<CarModel> carModelList;
		try {
			//Open Session
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			hibernateCriteria.add(Restrictions.eq("ownerId", carModel.getOwnerId()));
			carModelList = hibernateCriteria.list();
			hibernateSession.close();
		} catch (Exception e) {
			throw e;
		}
		return carModelList;
	}

}
