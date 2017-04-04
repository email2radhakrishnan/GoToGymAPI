package com.gotogym.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.gotogym.model.GymLogs;
import com.gotogym.model.PaymentDetails;
import com.gotogym.model.Plan;
import com.gotogym.model.UserSubscription;

public class HibernateConfig {

	private static SessionFactory factory;

	private HibernateConfig() {
	}

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			factory = new AnnotationConfiguration().configure().addAnnotatedClass(Plan.class)
					.addAnnotatedClass(GymLogs.class).addAnnotatedClass(PaymentDetails.class)
					.addAnnotatedClass(UserSubscription.class).buildSessionFactory();
		}
		return factory;
	}

}
