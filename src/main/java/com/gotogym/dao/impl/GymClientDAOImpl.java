package com.gotogym.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.GymClientDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.GymClient;
import com.gotogym.model.GymLogs;
import com.gotogym.utils.ErrorConstants;

public class GymClientDAOImpl implements GymClientDAO {

	private SessionFactory factory;
	private static GymClientDAOImpl gymClientDAOImpl;

	private GymClientDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static GymClientDAOImpl getObject() {
		if (gymClientDAOImpl == null) {
			gymClientDAOImpl = new GymClientDAOImpl();
		}
		return gymClientDAOImpl;
	}

	@Override
	public List<GymClient> getGymClientByName(String clientName) throws ApplicationException {
		System.out.println("test");
		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymClient where clientName = :clientName");
			query.setParameter("clientName", clientName);
			gymClients = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClients;
	}

	@Override
	public GymClient getGymClientByPhone(Long phone) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		GymClient gymClient = null;
		try {
			tx = session.beginTransaction();
			gymClient = (GymClient) session.get(GymClient.class, phone);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClient;
	}

	@Override
	public List<GymClient> getGymClientByEmail(String email) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymClient where email = :email");
			query.setParameter("email", email);
			gymClients = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClients;
	}

	@Override
	public List<GymClient> getGymClientByCity(String city) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymClient where city = :city");
			query.setParameter("city", city);
			gymClients = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClients;
	}

	@Override
	public List<GymClient> getGymClientByRegDate(Date regDate) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymClient where registerDate = :regDate");
			query.setParameter("regDate", regDate);
			gymClients = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClients;
	}

	public void registerGymClient(GymClient gymClient) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(gymClient);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
	}

	public void updateClientPassword(byte[] passHash, Long phone) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("Update com.gotogym.model.GymClient set hash = :hash where phone = :phone");
			query.setParameter("hash", passHash);
			query.setParameter("phone", phone);
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
	}

	@Override
	public List<GymClient> getAllGymClient() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymClient> gymClients = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymClient");
			gymClients = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			ApplicationException appExce = new ApplicationException(ErrorConstants.ERROR_CODE_GENERAL,
					ErrorConstants.ERROR_DESC_GENERAL);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw appExce;
		} finally {
			session.close();
		}
		return gymClients;

	}

}
