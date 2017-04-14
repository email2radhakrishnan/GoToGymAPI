package com.gotogym.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.UserDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.User;
import com.gotogym.utils.ErrorConstants;

public class UserDAOImpl implements UserDAO {

	private SessionFactory factory;
	private static UserDAOImpl userDAOImpl;

	private UserDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static UserDAOImpl getObject() {

		if (userDAOImpl == null) {
			userDAOImpl = new UserDAOImpl();
		}

		return userDAOImpl;
	}

	@Override
	public List<User> getAllUser() throws ApplicationException {
		Session session = factory.openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			users = session.createQuery("from com.gotogym.model.User").list();
			return users;
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

		return users;
	}

	@Override
	public User getUserByEmail(String email) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from com.gotogym.model.User where email = :email");
			query.setParameter("email", email);
			List<User> users = query.list();
			if (users != null && !users.isEmpty()) {
				user = users.get(0);
			}
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

		return user;
	}

	@Override
	public User getUserByPhone(Long phone) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, phone);

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

		return user;
	}

	@Override
	public void createUser(User user) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
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
	public void updateUserByPhone(User user) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.update(user);
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
	public void deleteUserByPhone(User user) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
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
	public List<User> getSubscribedUsers() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from com.gotogym.model.User where validSubscription = 'Y'");
			users = query.list();

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

		return users;
	}

	@Override
	public List<User> getUnSubscribedUsers() throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from com.gotogym.model.User where validSubscription = 'N'");
			users = query.list();

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

		return users;
	}

	@Override
	public void updateSubscriptionFlagByPhone(Long phone, boolean validSubscription) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(
					"Update com.gotogym.model.User set validSubscription =  :validSubscription where phone = :phone ");
			query.setParameter("validSubscription", validSubscription);
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
}
