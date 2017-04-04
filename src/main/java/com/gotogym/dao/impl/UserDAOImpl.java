package com.gotogym.dao.impl;

public class UserDAOImpl  {/*
 

	@Override
	public List<User> getAllUser() {
		String sql = "Select firstName, lastName, email, address, city, phone from User";
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setCity(rs.getString("city"));
				user.setPhone(rs.getLong("phone"));

				return user;
			}

		});
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "Select firstName, lastName, email, address, city, phone from User where email = ?";
		jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();

					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setEmail(rs.getString("email"));
					user.setAddress(rs.getString("address"));
					user.setCity(rs.getString("city"));
					user.setPhone(rs.getLong("phone"));

					return user;
				}
				return null;

			}

		});
		return null;
	}

	@Override
	public int createUser(User user) {
		String sql = "Insert into User values (?, ?, ?, ?, ?, ?)";
		int userId = jdbcTemplate.update(sql);
		return userId;
	}

	@Override
	public void updatedUser(User user) {
		String sql = "Insert into User values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql);
	}

	@Override
	public void deleteUser(String emailId) {
		String sql = "delete query";
		jdbcTemplate.update(sql);
	}

	@Override
	public void updatePassword(byte[] salt, byte[] hash, String userName) {
		String sql = "Insert into User values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql);
		
	}

	 

*/}
