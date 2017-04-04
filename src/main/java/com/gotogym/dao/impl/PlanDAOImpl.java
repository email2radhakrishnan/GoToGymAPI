package com.gotogym.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gotogym.dao.PlanDAO;
import com.gotogym.error.ApplicationException;
import com.gotogym.hibernate.config.HibernateConfig;
import com.gotogym.model.Plan;
import com.gotogym.utils.ErrorConstants;

public class PlanDAOImpl implements PlanDAO {

	private SessionFactory factory;
	private static PlanDAOImpl planDAOImpl;

	private PlanDAOImpl() {
		factory = HibernateConfig.getSessionFactory();
	}

	public static PlanDAOImpl getObject() {

		if (planDAOImpl == null) {
			planDAOImpl = new PlanDAOImpl();
		}

		return planDAOImpl;
	}

	@Override
	public List<Plan> getAllPlans() throws ApplicationException {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Plan> plans = null;
		try {
			tx = session.beginTransaction();
			plans = session.createQuery("from com.gotogym.model.Plan").list();
			return plans;
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

		return plans;
	}

	@Override
	public Plan getPlanByName(String planName) throws ApplicationException {

		Session session = factory.openSession();
		Transaction tx = null;
		Plan plan = null;

		try {
			tx = session.beginTransaction();
			plan = (Plan) session.get(Plan.class, planName);
			return plan;
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

		return plan;
	}

}
