package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.Customers;

public class CustomerRowMapper implements RowMapper<Customers> {

	@Override
	public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customers c = new Customers();
		c.setCust_id(rs.getInt(1));
		c.setCust_firstname(rs.getString(2));
		c.setCust_lastname(rs.getString(3));
		c.setCust_dob(rs.getDate(4));
		c.setCust_address(rs.getString(5));
		c.setCust_gender(rs.getString(6));
		c.setCust_cdate(rs.getDate(7));
		c.setCust_aadhar(rs.getString(8));
		c.setCust_status(rs.getString(9));
		c.setCust_ludate(rs.getDate(10));
		c.setCust_luuser(rs.getInt(11));

		return c;
	}

}