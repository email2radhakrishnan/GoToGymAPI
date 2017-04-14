package com.gotogym.dao;

import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.User;

public interface UserDAO {

	List<User> getAllUser() throws ApplicationException;

	User getUserByEmail(String email) throws ApplicationException;

	User getUserByPhone(Long phone) throws ApplicationException;

	void createUser(User user) throws ApplicationException;

	void updateUserByPhone(User user) throws ApplicationException;

	void deleteUserByPhone(User user) throws ApplicationException;

	List<User> getSubscribedUsers() throws ApplicationException;

	List<User> getUnSubscribedUsers() throws ApplicationException;

	void updateSubscriptionFlagByPhone(Long phone, boolean validSubscription) throws ApplicationException;

}
