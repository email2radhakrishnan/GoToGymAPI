package com.gotogym.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.GymLogsDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.ErrorConstants;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.GymLogs;

public class GymLogsDAOImpl implements GymLogsDAO {

	private SessionFactory factory;
	private static GymLogsDAOImpl gymLogsDAOImpl;

	private GymLogsDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static GymLogsDAOImpl getObject() {

		if (gymLogsDAOImpl == null) {
			gymLogsDAOImpl = new GymLogsDAOImpl();
		}

		return gymLogsDAOImpl;
	}

	@Override
	public List<GymLogs> getLogsByClientName(String clientName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where clientName = :clientName");
			query.setParameter("clientName", clientName);
			gymLogs = query.list();
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
		return gymLogs;
	}

	@Override
	public List<GymLogs> getLogsByUserName(String userName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where userName = :userName");
			query.setParameter("userName", userName);
			gymLogs = query.list();
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
		return gymLogs;
	}

	@Override
	public List<GymLogs> getLogsByPlanName(String planName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where planName = :planName");
			query.setParameter("planName", planName);
			gymLogs = query.list();
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
		return gymLogs;
	}

	@Override
	public List<GymLogs> getLogsByDate(Date logDate) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where dateVisited = :date_visited");
			query.setParameter("date_visited", logDate);
			gymLogs = query.list();
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
		return gymLogs;
	}

	@Override
	public List<GymLogs> getPaidLogs() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where isPaid = 'Y'");
			gymLogs = query.list();
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
		return gymLogs;
	}

	@Override
	public List<GymLogs> getUnPaidLogs() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<GymLogs> gymLogs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.GymLogs where isPaid = 'N'");
			gymLogs = query.list();
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
		return gymLogs;
	}

}
