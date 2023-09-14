package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.Insurance;

public class InsuranceRowMapper implements RowMapper<Insurance> {

	@Override
	public Insurance mapRow(ResultSet rs, int rowNum) throws SQLException {

		Insurance i = new Insurance();
		i.setIns_id(rs.getInt(1));
		i.setIns_customer_id(rs.getInt(2));
		i.setIns_cdate(rs.getDate(3));
		i.setIns_sum_assured(rs.getDouble(4));
		i.setIns_insp_id(rs.getInt(5));
		i.setIns_yrly_prem_amt(rs.getDouble(6));
		i.setIns_st_date(rs.getDate(7));

		return i;
	}

}