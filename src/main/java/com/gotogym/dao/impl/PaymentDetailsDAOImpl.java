package com.gotogym.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.PaymentDetailsDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.ErrorConstants;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.PaymentDetails;

public class PaymentDetailsDAOImpl implements PaymentDetailsDAO {

	private SessionFactory factory;
	private static PaymentDetailsDAOImpl paymentDetailsDAOImpl;

	private PaymentDetailsDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static PaymentDetailsDAOImpl getObject() {

		if (paymentDetailsDAOImpl == null) {
			paymentDetailsDAOImpl = new PaymentDetailsDAOImpl();
		}

		return paymentDetailsDAOImpl;
	}

	@Override
	public List<PaymentDetails> getDetailsByClientName(String clientName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<PaymentDetails> paymentDetails = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.PaymentDetails where clientName = :clientName");
			query.setParameter("clientName", clientName);
			paymentDetails = query.list();
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
		return paymentDetails;
	}

	@Override
	public List<PaymentDetails> getDetailsByDate(Date payDate) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		List<PaymentDetails> paymentDetails = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(" from com.gotogym.model.PaymentDetails where paidDate = :payDate");
			query.setParameter("payDate", payDate);
			paymentDetails = query.list();
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
		return paymentDetails;
	}

}
