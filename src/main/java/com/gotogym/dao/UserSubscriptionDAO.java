package com.gotogym.dao;

import java.util.Date;
import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.UserSubscription;

public interface UserSubscriptionDAO {

	UserSubscription getSubscriptionByUserName(String userName) throws ApplicationException;

	List<UserSubscription> getSubscriptionByPlanName(String planName) throws ApplicationException;

	List<UserSubscription> getSubscriptionByDate(Date purchasedDate) throws ApplicationException;

	List<UserSubscription> getActiveSubscription() throws ApplicationException;

	void subscripeForPlan(String userName, String planName, int numberOfEntries, int validity)
			throws ApplicationException;

	void updateEntry(String userName, String planName) throws ApplicationException;

}
