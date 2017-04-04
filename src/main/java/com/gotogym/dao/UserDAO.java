package com.gotogym.dao;

import java.util.List;

import com.gotogym.model.User;

public interface UserDAO {

	List<User> getAllUser();

	User getUserByEmail(String email);

	int createUser(User user);

	void updatedUser(User user);

	void deleteUser(String emailId);

	void updatePassword(byte[] salt, byte[] hash, String userName);
}
