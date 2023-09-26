package com.example.insurence.rowmappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.insurence.models.UserLoginValidation;

public class UserLoginValidationRowMapper implements RowMapper<UserLoginValidation> {

	@Override
	public UserLoginValidation mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserLoginValidation userLoginValidation = new UserLoginValidation();
		userLoginValidation.setUserId(rs.getLong("user_id"));
		userLoginValidation.setLoginTimeFrom(rs.getTimestamp("login_time_from"));
		userLoginValidation.setLoginTimeTo(rs.getTimestamp("login_time_to"));
		return userLoginValidation;
	}
}