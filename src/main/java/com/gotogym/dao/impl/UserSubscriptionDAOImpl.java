package com.gotogym.dao.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.UserSubscriptionDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.UserSubscription;
import com.gotogym.utils.ErrorConstants;

public class UserSubscriptionDAOImpl implements UserSubscriptionDAO {

	private SessionFactory factory;
	private static UserSubscriptionDAOImpl subscriptionDAOImpl;

	private UserSubscriptionDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static UserSubscriptionDAOImpl getObject() {

		if (subscriptionDAOImpl == null) {
			subscriptionDAOImpl = new UserSubscriptionDAOImpl();
		}

		return subscriptionDAOImpl;
	}

	@Override
	public void subscripeForPlan(String userName, String planName, int numberOfEntries, int validity)
			throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// current date is purchased date
			Timestamp purchasedDate = new Timestamp(new Date().getTime());

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, validity);
			Timestamp expiryDate = new Timestamp(cal.getTime().getTime());

			UserSubscription subscription = new UserSubscription(userName, planName, numberOfEntries, 0, purchasedDate,
					expiryDate, Boolean.TRUE);

			session.save(subscription);
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
	public UserSubscription getSubscriptionByUserName(String userName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		UserSubscription subscription = null;

		try {
			tx = session.beginTransaction();
			subscription = (UserSubscription) session.get(UserSubscription.class, userName);
			return subscription;
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

		return subscription;
	}

	@Override
	public List<UserSubscription> getSubscriptionByPlanName(String planName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<UserSubscription> subscriptions = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from com.gotogym.model.UserSubscription where planName = :planName");
			query.setParameter("planName", planName);
			subscriptions = query.list();
			return subscriptions;
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

		return subscriptions;
	}

	@Override
	public List<UserSubscription> getSubscriptionByDate(Date purchasedDate) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<UserSubscription> subscriptions = null;

		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from com.gotogym.model.UserSubscription where purchasedDate = :purchasedDate");
			query.setParameter("purchasedDate", purchasedDate);
			subscriptions = query.list();
			return subscriptions;
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

		return subscriptions;
	}

	@Override
	public List<UserSubscription> getActiveSubscription() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<UserSubscription> subscriptions = null;

		try {
			tx = session.beginTransaction();
			subscriptions = session.createQuery("from com.gotogym.model.UserSubscription where isActive = 'Y'").list();
			return subscriptions;
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

		return subscriptions;
	}

	@Override
	public void updateEntry(String userName, String planName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<UserSubscription> subscriptions = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(
					"from com.gotogym.model.UserSubscription where userName = :userName and planName = :planName and active = 'Y'");
			query.setParameter("userName", userName);
			query.setParameter("planName", planName);

			subscriptions = query.list();
			if (subscriptions.size() >= 1) {
				query = session.createQuery(
						"Update com.gotogym.model.UserSubscription set usedEntries =  :usedEntries where userName = :userName and planName = :planName and active = 'Y'");
				query.setParameter("usedEntries", subscriptions.get(0).getUsedEntries() + 1);
				query.setParameter("userName", userName);
				query.setParameter("planName", planName);
				query.executeUpdate();
			}
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

}
