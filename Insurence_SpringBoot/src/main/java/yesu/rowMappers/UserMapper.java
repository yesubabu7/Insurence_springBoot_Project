package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setUser_id(rs.getInt(1));
		u.setUser_name(rs.getString(2));
		u.setUser_cdate(rs.getDate(3));
		u.setUser_password(rs.getString(4));
		u.setUser_type(rs.getString(5));
		u.setUser_status(rs.getString(6));

		return u;

	}

}